package com.colne.kubra.dao;

import com.colne.kubra.beans.*;

import javax.sound.sampled.Port;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import static com.colne.kubra.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.colne.kubra.dao.DAOUtilitaire.initialisationRequetePreparee;

public class PorteactionDaoImpl implements PorteactionDao {
    private DAOFactory          daoFactory;
    private static final String SQL_INSERT          =   " INSERT INTO Porteaction (id_utilisateur,id_action,quantite, valeur) VALUES (?,?,?,?)";
    private static final String SQL_SELECT_PAR_ID   =   " SELECT * FROM Porteaction JOIN Action USING(id_action) WHERE id_utilisateur = ? ORDER BY valeur;";
    private static final String SQL_DELETE_PAR_ID 	= 	" DELETE FROM Porteaction WHERE id_utilisateur = ?";
    private static final String SQL_DELETE_ROW      =   " DELETE FROM Porteaction WHERE id_utilisateur = ? AND id_action = ?";
    private static final String SQL_UPDATE_ACHAT    =   " UPDATE Porteaction" +
                                                        " SET quantite = quantite + ?, valeur = valeur + ?" +
                                                        " WHERE id_utilisateur = ? AND id_action = ?";
    private static final String SQL_UPDATE_VENTE    =   " UPDATE Porteaction" +
                                                        " SET quantite = quantite - ?, valeur = valeur - ?" +
                                                        " WHERE id_utilisateur = ? AND id_action = ?";
    PorteactionDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Porteaction trouver(Utilisateur utilisateur) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Porteaction porteaction = new Porteaction();
        HashMap<Action,Integer> hashmap = new HashMap<>();
        HashMap<Action,Double> hashmap2 = new HashMap<>();
        Double valeurTotal = 0.;
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_PAR_ID, false, utilisateur.getId() );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            while ( resultSet.next() ) {
                Action action = map( resultSet );
                Integer quantite = resultSet.getInt("quantite");
                Double valeur = resultSet.getDouble("valeur");
                hashmap.put(action,quantite);
                hashmap2.put(action,valeur);
                valeurTotal += valeur;
            }
            porteaction.setActions_quantites(hashmap);
            porteaction.setActions_valeur(hashmap2);
            porteaction.setValeurTotale(valeurTotal);
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return porteaction;
    }
    @Override
    public Porteaction ajouter(Porteaction porteaction, Transaction transaction){
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, false, transaction.getId_portefeuille(), transaction.getAction().getId_action(), transaction.getQuantite(), transaction.getPrix_total() );
            int statut = preparedStatement.executeUpdate();
            /* Analyse du statut retourné par la requête d'insertion */
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la création de de la liasion action/quantite, aucune ligne ajoutée dans la table." );
            }
            porteaction.ajouter(transaction);
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion );
        }
        return porteaction;
    }

    @Override
    public Porteaction supprimerAction(Porteaction porteaction, Transaction transaction) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_DELETE_ROW, false, transaction.getId_portefeuille(),transaction.getAction().getId_action());
            int statut = preparedStatement.executeUpdate();
            /* Analyse du statut retourné par la requête d'insertion */
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la suppression de la ligne souhaité, aucune ligne supprimée dans la table." );
            }
            porteaction.supprimer(transaction.getAction());
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion );
        }
        return porteaction;
    }

    @Override
    public void supprimer(Utilisateur utilisateur) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_DELETE_PAR_ID, false, utilisateur.getId());
            int statut = preparedStatement.executeUpdate();
            /* Analyse du statut retourné par la requête d'insertion */
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la suppression du portefeuille courant, aucune ligne supprimée dans la table." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion );
        }
    }

    @Override
    public Porteaction modifier(Porteaction porteaction, Transaction transaction) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        String requete;
        if (transaction.getType().equals("VENTE")) {
            requete = SQL_UPDATE_VENTE;
        } else {
            requete = SQL_UPDATE_ACHAT;
        }
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, requete, false, transaction.getQuantite(),transaction.getPrix_total(),transaction.getId_portefeuille(),transaction.getAction().getId_action());
            int statut = preparedStatement.executeUpdate();
            /* Analyse du statut retourné par la requête d'insertion */
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la suppression du portefeuille courant, aucune ligne supprimée dans la table." );
            }
            porteaction.modifier(transaction);
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion );
        }
        return porteaction;
    }

    private Action map(ResultSet resultSet) throws SQLException {
        Action action = new Action();
        action.setId_action( resultSet.getLong("id_action") );
        action.setNom( resultSet.getString("nom") );
        action.setSymbole( resultSet.getString("symbole") );
        return action;
    }
}

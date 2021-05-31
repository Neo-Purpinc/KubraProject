package com.colne.kubra.dao;

import com.colne.kubra.beans.Action;
import com.colne.kubra.beans.Portefeuille;
import com.colne.kubra.beans.Utilisateur;

import java.sql.*;

import static com.colne.kubra.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.colne.kubra.dao.DAOUtilitaire.initialisationRequetePreparee;

public class PortefeuilleDaoImpl implements PortefeuilleDao{
    private DAOFactory          daoFactory;
    private static final String SQL_INSERTION_TRANSACTION   = 	" INSERT INTO Portefeuille (id_portefeuille, id_action, quantite, date, prix_unitaire, type)" +
                                                                " VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_PAR_ID 	        = 	" SELECT *" +
                                                                " FROM Portefeuille" +
                                                                " WHERE id_portefeuille = ?" +
                                                                " ORDER BY date DESC";
    private static final String SQL_DELETE_PAR_ID 	        = 	" DELETE *" +
                                                                " FROM Portefeuille WHERE id_portefeuille = ?";

    PortefeuilleDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Portefeuille trouver(Utilisateur utilisateur) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Portefeuille portefeuille = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_PAR_ID, false, utilisateur.getId() );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            while ( resultSet.next() ) {
                portefeuille = add( resultSet );
            }
            portefeuille.setId_portefeuille( resultSet.getLong( "id_portefeuille" ) );
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return portefeuille;
    }

    @Override
    public void acheterAction(Utilisateur utilisateur, Action action, Integer quantite, Double prix, Timestamp date_achat ) throws DAOException {
        transaction(utilisateur.getId(), action.getId_action(), quantite, prix, date_achat, "ACHAT" );
    }

    @Override
    public void vendreAction(Utilisateur utilisateur, Action action, Integer quantite, Double prix, Timestamp date_vente) throws DAOException {
        transaction(utilisateur.getId(), action.getId_action(), quantite,  prix, date_vente, "VENTE" );
    }

    @Override
    public void supprimer(Portefeuille portefeuille) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_DELETE_PAR_ID, false, portefeuille.getId_portefeuille());
            int statut = preparedStatement.executeUpdate();
            /* Analyse du statut retourné par la requête d'insertion */
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la suppression du portefeuille, aucune ligne supprimée dans la table." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion );
        }
    }

    private void transaction(Long id_portefeuille, Long id_action, Integer quantite, Double prix, Timestamp date, String type){
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERTION_TRANSACTION, false, id_portefeuille, id_action, quantite, prix, date, type);
            int statut = preparedStatement.executeUpdate();
            /* Analyse du statut retourné par la requête d'insertion */
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la création de la transaction, aucune ligne ajoutée dans la table." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion );
        }
    }

    /*
     * Méthode utilitaire permettant de faire l'ajout
     * d'une ligne issue de la table Portefeuille
     * (un ResultSet) dans un bean Portefeuille.
     */
    private static Portefeuille add( ResultSet resultSet ) throws SQLException {
        Portefeuille portefeuille = new Portefeuille();
        portefeuille.addAction( resultSet.getLong("id_action") );
        portefeuille.addQuantite( resultSet.getInt("quantite") );
        portefeuille.addDate( resultSet.getTimestamp( "date"));
        portefeuille.addPrix_unitaire( resultSet.getDouble("prix_unitaire"));
        portefeuille.addPrix_total( resultSet.getDouble("prix_total"));
        portefeuille.addType( resultSet.getString("type") );
        return portefeuille;
    }
}

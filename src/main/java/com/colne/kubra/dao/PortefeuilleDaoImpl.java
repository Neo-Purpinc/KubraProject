package com.colne.kubra.dao;

import com.colne.kubra.beans.*;

import java.sql.*;

import static com.colne.kubra.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.colne.kubra.dao.DAOUtilitaire.initialisationRequetePreparee;

public class PortefeuilleDaoImpl implements PortefeuilleDao{
    private DAOFactory          daoFactory;                     //TODO Rajouter date
    private static final String SQL_INSERTION_TRANSACTION   = 	" INSERT INTO Portefeuille (id_portefeuille, id_action, quantite, date, prix_unitaire, type)" +
                                                                " VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_PAR_ID 	        = 	" SELECT *" +
                                                                " FROM Portefeuille JOIN Action USING(id_action)" +
                                                                " WHERE id_portefeuille = ?" +
                                                                " ORDER BY date DESC";
    private static final String SQL_DELETE_PAR_ID 	        = 	" DELETE FROM Portefeuille WHERE id_portefeuille = ?";

    PortefeuilleDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Portefeuille trouver(Utilisateur utilisateur) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Portefeuille portefeuille = new Portefeuille();
        portefeuille.setId_portefeuille(utilisateur.getId());
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_PAR_ID, false, utilisateur.getId() );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            while ( resultSet.next() ) {
                Transaction transaction = map( resultSet );
                portefeuille.addTransaction( transaction );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return portefeuille;
    }

    public Portefeuille addTransaction(Portefeuille portefeuille, Transaction transaction){
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERTION_TRANSACTION, false, portefeuille.getId_portefeuille(), transaction.getAction().getId_action(), transaction.getQuantite(), transaction.getDate(), transaction.getPrix_unitaire(), transaction.getType());
            int statut = preparedStatement.executeUpdate();
            /* Analyse du statut retourné par la requête d'insertion */
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la création de la transaction, aucune ligne ajoutée dans la table." );
            }
            portefeuille.addTransaction( transaction );

        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion );
        }
        return portefeuille;
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

    /*
     * Simple méthode utilitaire permettant de faire la correspondance (le
     * mapping) entre une ligne issue de la table Portefeuille (un
     * ResultSet) et un bean Transaction.
     */
    private static Transaction map(ResultSet resultSet ) throws  SQLException {
        Action action = new Action();
        action.setId_action( resultSet.getLong("id_action") );
        action.setNom( resultSet.getString("nom") );
        action.setSymbole( resultSet.getString("symbole") );
        Transaction transaction = new Transaction();
        transaction.setId_portefeuille( resultSet.getLong( "id_portefeuille") );
        transaction.setAction( action );
        transaction.setQuantite( resultSet.getInt("quantite") );
        transaction.setDate( resultSet.getTimestamp("date") );
        transaction.setPrix_unitaire( resultSet.getDouble("prix_unitaire") );
        transaction.setPrix_total( resultSet.getDouble("prix_total") );
        transaction.setType( resultSet.getString("type") );
        return transaction;
    }
}

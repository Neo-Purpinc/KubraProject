package com.colne.kubra.dao;

import com.colne.kubra.beans.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import static com.colne.kubra.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.colne.kubra.dao.DAOUtilitaire.initialisationRequetePreparee;

public class PorteactionDaoImpl implements PorteactionDao {
    private DAOFactory          daoFactory;
    private static final String SQL_SELECT_PAR_ID           =   " SELECT * FROM Porteaction JOIN Action USING(id_action) WHERE id_utilisateur = ?;";
    private static final String SQL_DELETE_PAR_ID 	        = 	" DELETE FROM Porteaction WHERE id_utilisateur = ?";

    PorteactionDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Porteaction trouver(Utilisateur utilisateur) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Porteaction porteaction = new Porteaction();
        HashMap<Action,Integer> hashmap = porteaction.getActions_quantites();
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_PAR_ID, false, utilisateur.getId() );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            while ( resultSet.next() ) {
                Action action = map( resultSet );
                Integer quantite = resultSet.getInt("quantite");
                hashmap.put(action,quantite);
            }
            porteaction.setActions_quantites(hashmap);
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
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

    private Action map(ResultSet resultSet) throws SQLException {
        Action action = new Action();
        action.setId_action( resultSet.getLong("id_action") );
        action.setNom( resultSet.getString("nom") );
        action.setSymbole( resultSet.getString("symbole") );
        return action;
    }
}

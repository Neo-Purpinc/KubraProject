package com.colne.kubra.dao;

import com.colne.kubra.beans.Action;
import com.colne.kubra.beans.Porteaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import static com.colne.kubra.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.colne.kubra.dao.DAOUtilitaire.initialisationRequetePreparee;

public class ActionDaoImpl implements ActionDao{
    private DAOFactory          daoFactory;
    private static final String SQL_SELECT_PAR_NOM           =   " SELECT * FROM Action WHERE nom = ?;";

    public ActionDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Action trouver(String nom) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Action action = null;
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_PAR_NOM, false, nom );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if ( resultSet.next() ) {
                action = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return action;
    }

    private Action map(ResultSet resultSet) throws SQLException {
        Action action = new Action();
        action.setId_action( resultSet.getLong("id_action") );
        action.setNom( resultSet.getString("nom") );
        action.setSymbole( resultSet.getString("symbole") );
        return action;
    }
}

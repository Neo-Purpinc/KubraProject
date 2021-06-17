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
    /* **************************************************************/
    /* ************************ ATTRIBUTES **************************/
    /* **************************************************************/
    private DAOFactory          daoFactory;
    private static final String SQL_SELECT          =   "SELECT * FROM Action WHERE symbole = ?;";
    private static final String COLONNE_ID_ACTION   =   "id_action";
    private static final String COLONNE_NOM         =   "nom";
    private static final String COLONNE_SYMBOLE     =   "symbole";

    /**
     * Constructeur
     * @param daoFactory la Factory permettant la communication avec la base de données
     */
    public ActionDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /* **************************************************************/
    /* ********************* PUBLIC FUNCTIONS ***********************/
    /* **************************************************************/
    @Override
    public Action trouver(String symbole) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Action action = null;
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT, false, symbole );
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

    /* **************************************************************/
    /* ********************* PRIVATE FUNCTIONS **********************/
    /* **************************************************************/
    /**
     * Simple méthode utilitaire permettant de faire la correspondance (le
     * mapping) entre une ligne issue de la table Action (un
     * ResultSet) et un bean Action
     * @param resultSet le résultat de la requête SQL
     * @return l'action associée
     * @throws SQLException
     */
    private static Action map(ResultSet resultSet) throws SQLException {
        Action action = new Action();
        action.setId_action( resultSet.getLong(COLONNE_ID_ACTION) );
        action.setNom( resultSet.getString(COLONNE_NOM) );
        action.setSymbole( resultSet.getString(COLONNE_SYMBOLE) );
        return action;
    }
}

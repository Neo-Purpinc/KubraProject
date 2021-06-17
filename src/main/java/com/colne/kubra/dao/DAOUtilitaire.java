package com.colne.kubra.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class DAOUtilitaire {
    /**
     * Constructeur caché par défaut (car c'est une classe finale utilitaire,
     * contenant uniquement des méthodes appelées de manière statique)
     */
    private DAOUtilitaire() {
    }

    /* **************************************************************/
    /* ********************* PUBLIC FUNCTIONS ***********************/
    /* **************************************************************/
    /**
     * Fermeture silencieuse du resultset
     * @param resultSet le resultSet à fermer
     */
    public static void fermetureSilencieuse( ResultSet resultSet ) {
        if ( resultSet != null ) {
            try {
                resultSet.close();
            } catch ( SQLException e ) {
                System.out.println( "Echec de la fermeture du ResultSet : " + e.getMessage() );
            }
        }
    }

    /**
     * Fermeture silencieuse du statement
     * @param statement le resultSet à fermer
     */
    public static void fermetureSilencieuse( Statement statement ) {
        if ( statement != null ) {
            try {
                statement.close();
            } catch ( SQLException e ) {
                System.out.println( "Echec de la fermeture du Statement : " + e.getMessage() );
            }
        }
    }

    /**
     * Fermeture silencieuse de la connexion
     * @param connexion la connexion à fermer
     */
    public static void fermetureSilencieuse( Connection connexion ) {
        if ( connexion != null ) {
            try {
                connexion.close();
            } catch ( SQLException e ) {
                System.out.println( "Echec de la fermeture de la connexion : " + e.getMessage() );
            }
        }
    }

    /**
     * Fermetures silencieuses du statement et de la connexion
     * @param statement le statement à fermer
     * @param connexion la connexion à fermer
     */
    public static void fermeturesSilencieuses( Statement statement, Connection connexion ) {
        fermetureSilencieuse( statement );
        fermetureSilencieuse( connexion );
    }

    /**
     * Fermetures silencieuses du resultset, du statement et de la connexion
     * @param resultSet le resultSet à fermer
     * @param statement le statement à fermer
     * @param connexion la connexion à fermer
     */
    public static void fermeturesSilencieuses( ResultSet resultSet, Statement statement, Connection connexion ) {
        fermetureSilencieuse( resultSet );
        fermetureSilencieuse( statement );
        fermetureSilencieuse( connexion );
    }

    /**
     * Initialise la requête préparée basée sur la connexion passée en argument, avec la requête SQL et les objets données
     * @param connexion
     * @param sql requête SQL à effectuer
     * @param returnGeneratedKeys
     * @param objets les différents paramètres
     * @return un objet PreparedStatement
     * @throws SQLException
     */
    public static PreparedStatement initialisationRequetePreparee( Connection connexion, String sql, boolean returnGeneratedKeys, Object... objets ) throws SQLException {
        PreparedStatement preparedStatement = connexion.prepareStatement( sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS );
        for ( int i = 0; i < objets.length; i++ ) {
            preparedStatement.setObject( i + 1, objets[i] );
        }
        return preparedStatement;
    }
}

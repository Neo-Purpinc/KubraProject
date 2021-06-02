package com.colne.kubra.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DAOFactory {

    private static final String     FICHIER_PROPERTIES       = "dao.properties";
    private static final String     PROPERTY_URL             = "url";
    private static final String     PROPERTY_DRIVER          = "driver";
    private static final String     PROPERTY_NOM_UTILISATEUR = "nomutilisateur";
    private static final String     PROPERTY_MOT_DE_PASSE    = "motdepasse";
    /* package */
    HikariDataSource                connectionPool           = null;

    /* package */
    DAOFactory( HikariDataSource connectionPool ) {
        this.connectionPool = connectionPool;
    }

    /*
     * Méthode chargée de récupérer les informations de connexion à la base de
     * données, charger le driver JDBC et retourner une instance de la Factory
     */
    public static DAOFactory getInstance() throws DAOConfigurationException {
        Properties properties = new Properties();
        String url;
        String driver;
        String nomUtilisateur;
        String motDePasse;
        
        HikariDataSource connectionPool = null;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );

        if ( fichierProperties == null ) {
            throw new DAOConfigurationException( "Le fichier properties " + FICHIER_PROPERTIES + " est introuvable." );
        }

        try {
            properties.load( fichierProperties );
            url = properties.getProperty( PROPERTY_URL );
            driver = properties.getProperty( PROPERTY_DRIVER );
            nomUtilisateur = properties.getProperty( PROPERTY_NOM_UTILISATEUR );
            motDePasse = properties.getProperty( PROPERTY_MOT_DE_PASSE );
        } catch ( FileNotFoundException e ) {
            throw new DAOConfigurationException( "Le fichier properties " + FICHIER_PROPERTIES + " est introuvable.", e );
        } catch ( IOException e ) {
            throw new DAOConfigurationException( "Impossible de charger le fichier properties " + FICHIER_PROPERTIES, e );
        }

        try {
            Class.forName( driver );
        } catch ( ClassNotFoundException e ) {
            throw new DAOConfigurationException( "Le driver est introuvable dans le classpath.", e );
        }

        try {
            /*
             * Création d'une configuration de pool de connexions via l'objet
             * HikariConfig et les différents setters associés.
             */
        	HikariConfig config = new HikariConfig();
            /* Mise en place de l'URL, du nom et du mot de passe */
            config.setJdbcUrl( url );
            config.setUsername( nomUtilisateur );
            config.setPassword( motDePasse );
            /* Paramétrage de la taille du pool */
            config.setMaximumPoolSize(20);
            /* Création du pool à partir de la configuration, via l'objet HikariDataSource */
            connectionPool = new HikariDataSource( config );
        } catch ( Exception e ) {
            e.printStackTrace();
            throw new DAOConfigurationException( "Erreur de configuration du pool de connexions.", e );
        }
        /*
         * Enregistrement du pool créé dans une variable d'instance via un appel
         * au constructeur de DAOFactory
         */
        DAOFactory instance = new DAOFactory( connectionPool );
        return instance;
    }

    /* Méthode chargée de fournir une connexion à la base de données */
    /* package */
    Connection getConnection() throws SQLException { return connectionPool.getConnection(); }

    /*
     * Méthodes de récupération de l'implémentation des différents DAO
     */
    public UtilisateurDao getUtilisateurDao() { return new UtilisateurDaoImpl( this ); }

    public PortefeuilleDao getPortefeuilleDao() { return new PortefeuilleDaoImpl(this); }

    public PorteactionDao getPorteactionDao() { return new PorteactionDaoImpl(this); }
}
package com.colne.kubra.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.colne.kubra.beans.Utilisateur;
import static com.colne.kubra.dao.DAOUtilitaire.*;
public class UtilisateurDaoImpl implements UtilisateurDao {
	/* **************************************************************/
	/* ************************ ATTRIBUTES **************************/
	/* **************************************************************/
	private DAOFactory          daoFactory;
	private static final String SQL_INSERT 				= 	" INSERT INTO Utilisateur (email, mot_de_passe, date_inscription)" +
															" VALUES (?, ?, NOW())";
	private static final String SQL_SELECT_PAR_EMAIL 	= 	" SELECT id, email, mot_de_passe, date_inscription " +
															" FROM Utilisateur WHERE email = ?";
	private static final String SQL_DELETE_PAR_ID 	= 		" DELETE FROM Utilisateur WHERE id = ?";
	private static final String SQL_UPDATE_PASS 		= 	" UPDATE Utilisateur" +
															" SET mot_de_passe = ?" +
															" WHERE email = ?";

	/**
	 * Constructeur
	 * @param daoFactory la Factory permettant la communication avec la base de données
	 */
	UtilisateurDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
	/* **************************************************************/
	/* ********************* PUBLIC FUNCTIONS ***********************/
	/* **************************************************************/
	@Override
	public Utilisateur trouver(String email) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Utilisateur utilisateur = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_PAR_EMAIL, false, email );
			resultSet = preparedStatement.executeQuery();
			/* Parcours de la ligne de données de l'éventuel ResulSet retourné */
			if ( resultSet.next() ) {
				utilisateur = map( resultSet );
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}

		return utilisateur;
	}
	@Override
	public void ajouter(Utilisateur utilisateur) throws DAOException {
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet valeursAutoGenerees = null;

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = daoFactory.getConnection();
	        preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, utilisateur.getEmail(), utilisateur.getMotDePasse());
	        int statut = preparedStatement.executeUpdate();
	        /* Analyse du statut retourné par la requête d'insertion */
	        if ( statut == 0 ) {
	            throw new DAOException( "Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table." );
	        }
	        /* Récupération de l'id auto-généré par la requête d'insertion */
	        valeursAutoGenerees = preparedStatement.getGeneratedKeys();
	        if ( valeursAutoGenerees.next() ) {
	            /* Puis initialisation de la propriété id du bean Utilisateur avec sa valeur */
	            utilisateur.setId( valeursAutoGenerees.getLong( 1 ) );
	        } else {
	            throw new DAOException( "Échec de la création de l'utilisateur en base, aucun ID auto-généré retourné." );
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
	    }

	}
	@Override
	public void modifier(Utilisateur utilisateur) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE_PASS, false, utilisateur.getMotDePasse(), utilisateur.getEmail());
			int statut = preparedStatement.executeUpdate();
			/* Analyse du statut retourné par la requête de modification */
			if ( statut == 0 ) {
				throw new DAOException( "Échec de la modification de l'utilisateur, aucune ligne modifiée dans la table." );
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses( preparedStatement, connexion );
		}
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
			/* Analyse du statut retourné par la requête de suppression */
			if ( statut == 0 ) {
				throw new DAOException( "Échec de la suppression de l'utilisateur, aucune ligne supprimée dans la table." );
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses( preparedStatement, connexion );
		}
	}

	/* **************************************************************/
	/* ********************* PRIVATE FUNCTIONS **********************/
	/* **************************************************************/
	/**
	 * Simple méthode utilitaire permettant de faire la correspondance (le
	 * mapping) entre une ligne issue de la table Utilisateur (un
	 * ResultSet) et un bean Utilisateur
	 * @param resultSet le résultat de la requête SQL
	 * @return l'utilisateur associé
	 * @throws SQLException
	 */
	private static Utilisateur map( ResultSet resultSet ) throws SQLException {
	    Utilisateur utilisateur = new Utilisateur();
	    utilisateur.setId( resultSet.getLong( "id" ) );
	    utilisateur.setEmail( resultSet.getString( "email" ) );
	    utilisateur.setMotDePasse( resultSet.getString( "mot_de_passe" ) );
	    utilisateur.setDateInscription( resultSet.getTimestamp( "date_inscription" ) );
	    return utilisateur;
	}
}

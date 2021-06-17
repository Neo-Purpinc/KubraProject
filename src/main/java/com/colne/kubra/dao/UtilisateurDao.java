package com.colne.kubra.dao;

import com.colne.kubra.beans.Utilisateur;

public interface UtilisateurDao {
    /**
     * Utilisé lors de l'inscription
     * Ajoute un utilisateur après une inscription valide
     * @param utilisateur l'utilisateur à ajouter
     * @throws DAOException
     */
    void ajouter( Utilisateur utilisateur ) throws DAOException;

    /**
     * Utilisé lors de la connexion
     * Récupère un bean utilisateur à partir d'un email
     * @param email l'adresse de l'utilisateur à récupérer
     * @return l'utilisateur associé à l'email
     * @throws DAOException
     */
    Utilisateur trouver( String email ) throws DAOException;

    /**
     * Utilisé lors de la modification du mot de passe
     * Mets à jour le mot de passe d'un utilisateur
     * @param utilisateur l'utilisateur à modifier
     * @throws DAOException
     */
    void modifier( Utilisateur utilisateur ) throws DAOException;

    /**
     * Utilisé lors de la suppression d'un compte
     * Supprime un utilisateur de la base de donnée
     * @param utilisateur l'utilisateur à supprimer
     * @throws DAOException
     */
    void supprimer( Utilisateur utilisateur ) throws DAOException;

}
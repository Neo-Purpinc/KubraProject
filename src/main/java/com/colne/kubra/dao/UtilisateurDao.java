package com.colne.kubra.dao;

import com.colne.kubra.beans.Utilisateur;

public interface UtilisateurDao {

    void creer( Utilisateur utilisateur ) throws DAOException;
    void modifier( Utilisateur utilisateur ) throws DAOException;
    Utilisateur trouver( String email ) throws DAOException;
    void supprimer( Utilisateur utilisateur ) throws DAOException;

}
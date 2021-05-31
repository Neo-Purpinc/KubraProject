package com.colne.kubra.dao;

import com.colne.kubra.beans.Action;
import com.colne.kubra.beans.Portefeuille;
import com.colne.kubra.beans.Utilisateur;

import java.sql.Timestamp;

public interface PortefeuilleDao {
    Portefeuille trouver( Utilisateur utilisateur ) throws DAOException;
    void acheterAction(Utilisateur utilisateur, Action action, Integer quantite, Double prix, Timestamp date_achat) throws  DAOException;
    void vendreAction( Utilisateur utilisateur, Action action, Integer quantite, Double prix, Timestamp date_vente ) throws DAOException;
    void supprimer( Portefeuille portefeuille ) throws DAOException;
}

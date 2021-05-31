package com.colne.kubra.dao;

import com.colne.kubra.beans.Portefeuille;
import com.colne.kubra.beans.Transaction;
import com.colne.kubra.beans.Utilisateur;

public interface PortefeuilleDao {
    Portefeuille trouver( Utilisateur utilisateur ) throws DAOException;
    Portefeuille addTransaction( Portefeuille portefeuille, Transaction transaction) throws DAOException;
    void supprimer( Portefeuille portefeuille ) throws DAOException;
}

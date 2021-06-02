package com.colne.kubra.dao;

import com.colne.kubra.beans.Porteaction;
import com.colne.kubra.beans.Portefeuille;
import com.colne.kubra.beans.Transaction;
import com.colne.kubra.beans.Utilisateur;

public interface PorteactionDao {
    Porteaction trouver( Utilisateur utilisateur ) throws DAOException;
    void supprimer( Utilisateur utilisateur ) throws DAOException;

}

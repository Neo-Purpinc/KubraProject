package com.colne.kubra.dao;

import com.colne.kubra.beans.*;

public interface PorteactionDao {
    Porteaction trouver( Utilisateur utilisateur ) throws DAOException;
    void supprimer( Utilisateur utilisateur ) throws DAOException;
    Porteaction  modifier(Porteaction porteaction, Transaction transaction) throws DAOException;
    Porteaction  ajouter(Porteaction porteaction, Transaction transaction) throws DAOException;
    Porteaction supprimerAction(Porteaction porteaction,Transaction transaction) throws DAOException;
}

package com.colne.kubra.dao;

import com.colne.kubra.beans.*;

public interface PorteactionDao {
    Porteaction trouver( Utilisateur utilisateur ) throws DAOException;
    void supprimer( Utilisateur utilisateur ) throws DAOException;
    void modifier(Transaction transaction, Integer quantite);
}

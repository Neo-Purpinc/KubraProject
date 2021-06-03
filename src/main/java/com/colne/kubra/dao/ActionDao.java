package com.colne.kubra.dao;

import com.colne.kubra.beans.Action;
import com.colne.kubra.beans.Porteaction;
import com.colne.kubra.beans.Utilisateur;

public interface ActionDao {
    Action trouver( String nom ) throws DAOException;
}

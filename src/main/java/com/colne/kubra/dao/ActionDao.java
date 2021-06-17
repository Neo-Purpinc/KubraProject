package com.colne.kubra.dao;

import com.colne.kubra.beans.Action;
import com.colne.kubra.beans.Porteaction;
import com.colne.kubra.beans.Utilisateur;

public interface ActionDao {
    /**
     * Fonction permettant de récupérer un objet Action à partir de son symbole
     * @param symbole le symbole de l'action à récupérer
     * @return l'action correspondante
     * @throws DAOException
     */
    Action trouver( String symbole ) throws DAOException;
}

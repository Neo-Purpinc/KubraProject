package com.colne.kubra.dao;

import com.colne.kubra.beans.*;

public interface PorteactionDao {
    /**
     * Utilisé à la connexion
     * Récupère le porteaction de l'utilisateur
     * @param utilisateur l'utilisateur dont on veut récupérer le porteaction
     * @return le porteaction associé à l'utilisateur
     * @throws DAOException
     */
    Porteaction trouver( Utilisateur utilisateur ) throws DAOException;
    /**
     * Utilisé lors de l'achat ou la vente d'une action déjà dans le porteaction
     * mais dont la quantité sera supérieur à 0 après la transaction
     * Mets à jour la quantité et la valeur d'une action
     * @param porteaction le porteaction à mettre à jour
     * @param transaction la transaction qui vient d'être effectué
     * @return le porteaction mis à jour
     * @throws DAOException
     */
    Porteaction modifier(Porteaction porteaction, Transaction transaction) throws DAOException;
    /**
     * Utilisé lors de l'achat d'une action si elle n'est pas déjà dans le porteaction
     * Ajoute une action, une quantité et une valeur dans le porteaction
     * @param porteaction le porteaction auquel ajouter l'action
     * @param transaction la transaction qui vient d'être effectué
     * @return le porteaction mis à jour
     * @throws DAOException
     */
    Porteaction  ajouter(Porteaction porteaction, Transaction transaction) throws DAOException;
    /**
     * Utilisé lors de la vente d'une action s'il en reste 0 après la transaction
     * Supprime une action dans le porteaction
     * @param porteaction le porteaction auquel supprimer l'action
     * @param transaction la transaction qui vient d'être effectué
     * @return le porteaction mis à jour
     * @throws DAOException
     */
    Porteaction supprimerAction(Porteaction porteaction,Transaction transaction) throws DAOException;
    /**
     * Utilisé à la suppression d'un compte
     * Supprime toutes les lignes associés à l'utilisateur
     * @param utilisateur
     * @throws DAOException
     */
    void supprimer( Utilisateur utilisateur ) throws DAOException;
}

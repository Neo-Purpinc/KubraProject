package com.colne.kubra.dao;

import com.colne.kubra.beans.Portefeuille;
import com.colne.kubra.beans.Transaction;
import com.colne.kubra.beans.Utilisateur;

public interface PortefeuilleDao {
    /**
     * Utilisé à la connexion
     * Récupère le portefeuille de l'utilisateur
     * @param utilisateur l'utilisateur dont on veut récupérer le portefeuille
     * @return le portefeuille associé à l'utilisateur
     * @throws DAOException
     */
    Portefeuille trouver( Utilisateur utilisateur ) throws DAOException;
    /**
     * Utilisé lors d'une transaction
     * Ajoute la transaction effectuée dans le portefeuille de l'utilisateur
     * @param portefeuille le portefeuille à mettre à jour
     * @param transaction la transaction qui vient d'être effectué
     * @return le portefeuille mis à jour
     * @throws DAOException
     */
    Portefeuille ajouter(Portefeuille portefeuille, Transaction transaction) throws DAOException;
    /**
     * Utilisé lors de la suppression d'un compte
     * Supprime toutes les transactions associés au portefeuille dans l'historique général
     * @param portefeuille le portefeuille à supprimer
     * @throws DAOException
     */
    void supprimer( Portefeuille portefeuille ) throws DAOException;
}

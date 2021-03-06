package com.colne.kubra.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static java.lang.Integer.sum;

public class Porteaction {
    /* **************************************************************/
    /* ************************ ATTRIBUTES **************************/
    /* **************************************************************/
    private HashMap<Action,Integer> actions_quantites = new HashMap<>();    // Fait le lien entre une action et la quantité possédée
    private HashMap<Action,Double> actions_valeur = new HashMap<>();        // Fait le lien entre une action et la valeur totale associée
    private Double benefice_perte = 0.;                                       // Permet de mettre à jour

    /* **************************************************************/
    /* ********************* GETTERS & SETTERS **********************/
    /* **************************************************************/
    public HashMap<Action, Integer> getActions_quantites() { return actions_quantites; }
    public void setActions_quantites(HashMap<Action, Integer> actions_quantites) { this.actions_quantites = actions_quantites; }

    public HashMap<Action, Double> getActions_valeur() { return actions_valeur; }
    public void setActions_valeur(HashMap<Action, Double> actions_valeur) { this.actions_valeur = actions_valeur; }

    public Double getBenefice_perte() { return benefice_perte; }
    public void setBenefice_perte(Double valeurTotale) { this.benefice_perte = valeurTotale; }

    public Integer getQuantite(Action action){
        Integer retour = null;
        for (Map.Entry<Action, Integer> entry : actions_quantites.entrySet()) {
            Action key = entry.getKey();
            Integer value = entry.getValue();
            if (key.getId_action().equals(action.getId_action())){
                retour = value;
                break;
            }
        }
        return retour;
    }

    public Double getValeur(Action action){
        Double retour = null;
        for (Map.Entry<Action, Double> entry : actions_valeur.entrySet()) {
            Action key = entry.getKey();
            Double value = entry.getValue();
            if (key.getId_action().equals(action.getId_action())){
                retour = value;
                break;
            }
        }
        return retour;
    }
    /* **************************************************************/
    /* ********************* PUBLIC FUNCTIONS ***********************/
    /* **************************************************************/

    /**
     * Indique si le porteaction est vide ou non
     * @return un booléen
     */
    public boolean isEmpty(){ return actions_quantites.isEmpty(); }

    /**
     * Indique l'existence d'une action dans le porteaction ou non
     * @param action l'action dont on veut vérifier la présence
     * @return un booléen
     */
    public boolean existe(Action action){
        boolean retour = false;
        for (Action key : actions_quantites.keySet()) {
            if (key.getId_action().equals(action.getId_action())) {
                retour = true;
                break;
            }
        }
        return retour;
    }

    /**
     * Ajoute une action dans le porteaction et y attache une quantité et une valeur
     * @param transaction la transaction contenant les informations à ajouter dans le porteaction
     */
    public void ajouter(Transaction transaction) {
        Action action = transaction.getAction();
        Double valeur = transaction.getPrix_total();
        benefice_perte -= valeur;
        actions_quantites.put(action,transaction.getQuantite());
        actions_valeur.put(action,valeur);
    }

    /**
     * Modifie une action dans le porteaction en mettant à jour la quantité et la valeur
      * @param transaction la transaction contenant les informations à modifier dans le porteaction
     */
    public void modifier(Transaction transaction) {
        switch (transaction.getType()){
            case "ACHAT":
                acheter( transaction );
                break;
            case "VENTE":
                vendre( transaction );
                break;
        }
    }

    /**
     * Supprime une action du porteaction
     * @param action l'action a retirer du porteaction
     */
    public void supprimer(Action action){
        Iterator<Map.Entry<Action,Integer>> iter = actions_quantites.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry<Action,Integer> current = iter.next();
            if(current.getKey().getId_action().equals(action.getId_action())){
                iter.remove();
                break;
            }
        }
        Iterator<Map.Entry<Action,Double>> iter2 = actions_valeur.entrySet().iterator();
        while(iter2.hasNext()){
            Map.Entry<Action,Double> current2 = iter2.next();
            if(current2.getKey().getId_action().equals(action.getId_action())){
                iter2.remove();
                setBenefice_perte(benefice_perte+current2.getValue());
                break;
            }
        }
    }



    /* **************************************************************/
    /* ******************** PRIVATES FUNCTIONS **********************/
    /* **************************************************************/

    /**
     * Simule l'achat d'une action dans l'instance en mettant à jour la valeur et la quantité
     * @param transaction la transaction qui vient d'être effectuée.
     */
    private void acheter(Transaction transaction) {
        Action action = transaction.getAction();
        Integer quantite = transaction.getQuantite();
        Integer ancienneQuantite = getQuantite( action );
        Integer nouvelleQuantite = sum(quantite,ancienneQuantite);
        Double valeur =  transaction.getPrix_total();
        Double ancienneValeur = getValeur( action );
        Double nouvelleValeur = ancienneValeur + valeur;
        setBenefice_perte(benefice_perte-transaction.getPrix_total());
        setQuantite(action,nouvelleQuantite);
        setValeur(action, nouvelleValeur );
    }
    /**
     * Simule la vente d'une action dans l'instance en mettant à jour la valeur et la quantité
     * @param transaction la transaction qui vient d'être effectuée.
     */
    private void vendre(Transaction transaction) {
        Action action = transaction.getAction();
        Integer quantite = transaction.getQuantite();
        Integer ancienneQuantite = getQuantite(action);
        Integer nouvelleQuantite = ancienneQuantite - quantite;
        Double valeur =  transaction.getPrix_total();
        Double ancienneValeur = getValeur( action );
        Double nouvelleValeur = ancienneValeur - valeur;
        setBenefice_perte(benefice_perte+transaction.getPrix_total());
        setQuantite(action,nouvelleQuantite);
        setValeur(action,nouvelleValeur);
    }

    /**
     * Mets à jour la quantité d'une action
     * @param action l'action pour laquelle il faut modifier la quantité
     * @param quantite la nouvelle quantité à attribuer à l'action
     */
    private void setQuantite(Action action, Integer quantite){
        for (Map.Entry<Action, Integer> current : actions_quantites.entrySet()) {
            if (current.getKey().getId_action().equals(action.getId_action())) {
                current.setValue(quantite);
                break;
            }
        }
    }
    /**
     * Mets à jour la valeur d'une action
     * @param action l'action pour laquelle il faut modifier la valeur
     * @param valeur la nouvelle valeur à attribuer à l'action
     */
    private void setValeur(Action action, Double valeur){
        for (Map.Entry<Action, Double > current : actions_valeur.entrySet()) {
            if (current.getKey().getId_action().equals(action.getId_action())) {
                current.setValue(valeur);
                break;
            }
        }
    }
}

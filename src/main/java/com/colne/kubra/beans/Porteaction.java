package com.colne.kubra.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static java.lang.Integer.sum;

public class Porteaction {
    /****************************************************************/
    /************************** ATTRIBUTES **************************/
    /****************************************************************/
    private HashMap<Action,Integer> actions_quantites = new HashMap<>();
    private HashMap<Action,Double> actions_valeur = new HashMap<>();
    private Double valeurTotale = 0.;

    /****************************************************************/
    /*********************** GETTERS & SETTERS **********************/
    /****************************************************************/
    public HashMap<Action, Integer> getActions_quantites() { return actions_quantites; }
    public void setActions_quantites(HashMap<Action, Integer> actions_quantites) { this.actions_quantites = actions_quantites; }

    public HashMap<Action, Double> getActions_valeur() { return actions_valeur; }
    public void setActions_valeur(HashMap<Action, Double> actions_valeur) { this.actions_valeur = actions_valeur; }

    public Double getValeurTotale() { return valeurTotale; }
    public void setValeurTotale(Double valeurTotale) { this.valeurTotale = valeurTotale; }

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
    public boolean isEmpty(){ return actions_quantites.isEmpty();
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
    private void setQuantite(Action action, Integer quantite){
        for (Map.Entry<Action, Integer> current : actions_quantites.entrySet()) {
            if (current.getKey().getId_action().equals(action.getId_action())) {
                current.setValue(quantite);
                break;
            }
        }
    }
    private void setValeur(Action action, Double valeur){
        for (Map.Entry<Action, Double > current : actions_valeur.entrySet()) {
            if (current.getKey().getId_action().equals(action.getId_action())) {
                current.setValue(valeur);
                break;
            }
        }
    }
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
        while(iter.hasNext()){
            Map.Entry<Action,Double> current2 = iter2.next();
            if(current2.getKey().getId_action().equals(action.getId_action())){
                iter2.remove();
                break;
            }
        }
    }

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
    private void acheter(Transaction transaction) {
        Action action = transaction.getAction();
        Integer quantite = transaction.getQuantite();
        Integer ancienneQuantite = getQuantite( action );
        Integer nouvelleQuantite = sum(quantite,ancienneQuantite);
        Double valeur =  transaction.getPrix_total();
        Double ancienneValeur = getValeur( action );
        Double nouvelleValeur = ancienneValeur + valeur;
        setValeurTotale(valeurTotale+transaction.getPrix_total());
        setQuantite(action,nouvelleQuantite);
        setValeur(action, nouvelleValeur );
    }
    private void vendre(Transaction transaction) {
        Action action = transaction.getAction();
        Integer quantite = transaction.getQuantite();
        Integer ancienneQuantite = getQuantite(action);
        Integer nouvelleQuantite = ancienneQuantite - quantite;
        Double valeur =  transaction.getPrix_total();
        Double ancienneValeur = getValeur( action );
        Double nouvelleValeur = ancienneValeur - valeur;
        setValeurTotale(valeurTotale-transaction.getPrix_total());
        setQuantite(action,nouvelleQuantite);
        setValeur(action,nouvelleValeur);
    }

    public void ajouter(Transaction transaction) {
        Action action = transaction.getAction();
        Double valeur = transaction.getPrix_total();
        valeurTotale += valeur;
        actions_quantites.put(action,transaction.getQuantite());
        actions_valeur.put(action,valeur);
    }
}

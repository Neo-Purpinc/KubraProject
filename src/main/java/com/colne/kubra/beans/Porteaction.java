package com.colne.kubra.beans;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Integer.sum;

public class Porteaction {
    private HashMap<Action,Integer> actions_quantites = new HashMap<>();

    public HashMap<Action, Integer> getActions_quantites() { return actions_quantites; }
    public void setActions_quantites(HashMap<Action, Integer> actions_quantites) { this.actions_quantites = actions_quantites; }

    public Integer acheter(Transaction transaction) {
        Integer val;
        Action action = transaction.getAction();
        Integer quantite = transaction.getQuantite();
        if(!actions_quantites.containsKey(action)){
            actions_quantites.put(action,quantite);
            val = quantite;
        } else {
            Integer ancienneValeur = actions_quantites.get(action);
            Integer nouvelleValeur = sum(quantite,ancienneValeur);
            actions_quantites.put(action,nouvelleValeur);
            val = nouvelleValeur;
        }
        return val;
    }
    public Integer vendre(Transaction transaction) {
        Action action = transaction.getAction();
        Integer quantite = transaction.getQuantite();
        Integer ancienneValeur = actions_quantites.get(action);
        Integer nouvelleValeur = ancienneValeur - quantite;
        actions_quantites.put(action,nouvelleValeur);
        if(nouvelleValeur == 0) {
            actions_quantites.remove(action);
        }
        return nouvelleValeur;
    }

}

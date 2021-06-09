package com.colne.kubra.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static java.lang.Integer.sum;

public class Porteaction {
    private HashMap<Action,Integer> actions_quantites = new HashMap<>();

    public HashMap<Action, Integer> getActions_quantites() { return actions_quantites; }
    public void setActions_quantites(HashMap<Action, Integer> actions_quantites) { this.actions_quantites = actions_quantites; }
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
    private boolean existe(Action action){
        return actions_quantites.keySet().stream().anyMatch(key -> key.getId_action().equals(action.getId_action()));
    }
    private void setQuantite(Action action, Integer quantite){
        for (Map.Entry<Action, Integer> current : actions_quantites.entrySet()) {
            if (current.getKey().getId_action().equals(action.getId_action())) {
                current.setValue(quantite);
                break;
            }
        }
    }
    private void supprimer(Action action){
        Iterator<Map.Entry<Action,Integer>> iter = actions_quantites.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry<Action,Integer> current = iter.next();
            if(current.getKey().getId_action().equals(action.getId_action())){
                iter.remove();
                break;
            }
        }
    }
    private void acheter(Transaction transaction) {
        Action action = transaction.getAction();
        Integer quantite = transaction.getQuantite();
        if(!existe(action)){
            actions_quantites.put(action,quantite);
        } else {
            Integer ancienneValeur = getQuantite( action );
            Integer nouvelleValeur = sum(quantite,ancienneValeur);
            setQuantite(action,nouvelleValeur);
        }
    }
    private void vendre(Transaction transaction) {
        Action action = transaction.getAction();
        Integer quantite = transaction.getQuantite();
        Integer ancienneValeur = getQuantite(action);
        Integer nouvelleValeur = ancienneValeur - quantite;
        if(nouvelleValeur == 0) {
            supprimer(action);
        } else{
            setQuantite(action,nouvelleValeur);
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
}

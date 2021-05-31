package com.colne.kubra.beans;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Portefeuille {
    /****************************************************************/
    /************************** ATTRIBUTES **************************/
    /****************************************************************/
    private Long id_portefeuille;
    private ArrayList<Long> id_action;
    private ArrayList<Integer>  quantite;
    private ArrayList<Timestamp> date;
    private ArrayList<Double> prix_unitaire;
    private ArrayList<Double> prix_total;
    private ArrayList<String> type;
    /****************************************************************/
    /*********************** GETTERS & SETTERS **********************/
    /****************************************************************/
    public Long getId_portefeuille() { return id_portefeuille; }
    public void setId_portefeuille(Long id_portefeuille) { this.id_portefeuille = id_portefeuille; }

    public ArrayList<Long> getId_action() { return id_action; }
    public void setId_action(ArrayList<Long> id_action) { this.id_action = id_action; }
    public void addAction(Long id_action) { this.id_action.add(id_action);}

    public ArrayList<Integer> getQuantite() { return quantite; }
    public void setQuantite(ArrayList<Integer> quantite) { this.quantite = quantite; }
    public void addQuantite(Integer quantite) { this.quantite.add(quantite); }

    public ArrayList<Timestamp> getDate() { return date; }
    public void setDate(ArrayList<Timestamp> date) { this.date = date; }
    public void addDate(Timestamp date) { this.date.add(date); }

    public ArrayList<Double> getPrix_unitaire() { return prix_unitaire; }
    public void setPrix_unitaire(ArrayList<Double> prix_unitaire) { this.prix_unitaire = prix_unitaire; }
    public void addPrix_unitaire(Double prix_unitaire) { this.prix_unitaire.add(prix_unitaire); }

    public ArrayList<Double> getPrix_total() { return prix_total; }
    public void setPrix_total(ArrayList<Double> prix_total) { this.prix_total = prix_total; }
    public void addPrix_total(Double prix_total) { this.prix_total.add(prix_total); }

    public ArrayList<String> getType() { return type; }
    public void setType(ArrayList<String> type) { this.type = type; }
    public void addType(String type) { this.type.add(type); }
}
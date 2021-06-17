package com.colne.kubra.beans;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Transaction {
    /* **************************************************************/
    /* ************************ ATTRIBUTES **************************/
    /* **************************************************************/
    private Long        id_portefeuille;
    private Action      action;
    private Integer     quantite;
    private Timestamp   date;
    private Double      prix_unitaire;
    private Double      prix_total;
    private String      type;

    /* **************************************************************/
    /* ********************* GETTERS & SETTERS **********************/
    /* **************************************************************/
    public Long getId_portefeuille() { return this.id_portefeuille; }
    public void setId_portefeuille(Long id_portefeuille) { this.id_portefeuille = id_portefeuille; }

    public Action getAction() { return action; }
    public void setAction(Action action) { this.action = action; }

    public Integer getQuantite() { return quantite; }
    public void setQuantite(Integer quantite) { this.quantite = quantite; }

    public Timestamp getDate() { return date; }
    public void setDate(Timestamp date) { this.date = date; }

    public Double getPrix_unitaire() { return prix_unitaire; }
    public void setPrix_unitaire(Double prix_unitaire) { this.prix_unitaire = prix_unitaire; }

    public Double getPrix_total() { return prix_total; }
    public void setPrix_total(Double prix_total) { this.prix_total = prix_total; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }


}

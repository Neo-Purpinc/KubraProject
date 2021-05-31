package com.colne.kubra.beans;

import java.sql.Timestamp;

public class Portefeuille {
    private Long id_portefeuille;
    private Long id_action;
    private Integer quantite;
    private Timestamp date_achat;
    private Double prix_achat;
    private Timestamp date_revente;
    private Double prix_revente;
    private Double evolution;

    public Long getId_portefeuille() { return id_portefeuille; }
    public void setId_portefeuille(Long id_portefeuille) { this.id_portefeuille = id_portefeuille; }

    public Long getId_action() { return id_action; }
    public void setId_action(Long id_action) { this.id_action = id_action; }

    public Integer getQuantite() { return quantite; }
    public void setQuantite(Integer quantite) { this.quantite = quantite; }

    public Timestamp getDate_achat() { return date_achat; }
    public void setDate_achat(Timestamp date_achat) { this.date_achat = date_achat; }

    public Double getPrix_achat() { return prix_achat; }
    public void setPrix_achat(Double prix_achat) { this.prix_achat = prix_achat; }

    public Timestamp getDate_revente() { return date_revente; }
    public void setDate_revente(Timestamp date_revente) { this.date_revente = date_revente; }

    public Double getPrix_revente() { return prix_revente; }
    public void setPrix_revente(Double prix_revente) { this.prix_revente = prix_revente; }

    public Double getEvolution() { return evolution; }
    public void setEvolution(Double evolution) { this.evolution = evolution; }
}
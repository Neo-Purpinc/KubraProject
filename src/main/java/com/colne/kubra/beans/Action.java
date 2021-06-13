package com.colne.kubra.beans;

import java.sql.Timestamp;

public class Action {
    /****************************************************************/
    /************************** ATTRIBUTES **************************/
    /****************************************************************/
    private Long      id_action;
    private String    symbole;
    private String    nom;

    /****************************************************************/
    /*********************** GETTERS & SETTERS **********************/
    /****************************************************************/
    public Long getId_action() { return id_action; }
    public void setId_action(Long id_action) { this.id_action = id_action; }

    public String getSymbole() { return symbole; }
    public void setSymbole(String symbole) { this.symbole = symbole; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

}
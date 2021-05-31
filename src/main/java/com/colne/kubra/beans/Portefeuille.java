package com.colne.kubra.beans;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Portefeuille {
    /****************************************************************/
    /************************** ATTRIBUTES **************************/
    /****************************************************************/
    private Long                    id_portefeuille;
    private ArrayList<Transaction>  transactions;

    /****************************************************************/
    /*********************** GETTERS & SETTERS **********************/
    /****************************************************************/
    public Long getId_portefeuille() { return id_portefeuille; }
    public void setId_portefeuille(Long id_portefeuille) { this.id_portefeuille = id_portefeuille; }

    public ArrayList<Transaction> getTransactions() { return transactions; }
    public void setTransactions(ArrayList<Transaction> transactions) { this.transactions = transactions; }
    public void addTransaction(Transaction transaction) { this.transactions.add( transaction ); }
}
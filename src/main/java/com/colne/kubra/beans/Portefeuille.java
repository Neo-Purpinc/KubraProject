package com.colne.kubra.beans;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Portefeuille {
    /* **************************************************************/
    /* ************************ ATTRIBUTES **************************/
    /* **************************************************************/
    private Long                    id_portefeuille;
    private Porteaction             porteaction;
    private ArrayList<Transaction>  transactions = new ArrayList<>();   /* Contient */

    /* **************************************************************/
    /* ********************* GETTERS & SETTERS **********************/
    /* **************************************************************/
    public Long getId_portefeuille() { return id_portefeuille; }
    public void setId_portefeuille(Long id_portefeuille) { this.id_portefeuille = id_portefeuille; }

    public Porteaction getPorteaction() { return porteaction; }
    public void setPorteaction(Porteaction porteaction) { this.porteaction = porteaction; }

    public ArrayList<Transaction> getTransactions() { return transactions; }
    public void setTransactions(ArrayList<Transaction> transactions) { this.transactions = transactions; }

    /* **************************************************************/
    /* ********************* PUBLIC FUNCTIONS ***********************/
    /* **************************************************************/
    public void addTransaction(Transaction transaction) {
        this.transactions.add( transaction );
    }
    public boolean isEmpty(){ return transactions.isEmpty(); }
}
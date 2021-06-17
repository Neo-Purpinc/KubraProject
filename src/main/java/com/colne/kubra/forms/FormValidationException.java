package com.colne.kubra.forms;

public class FormValidationException extends Exception {
    /**
     * Constructeur
     * @param message de l'erreur
     */
    public FormValidationException( String message ) {
        super( message );
    }
}

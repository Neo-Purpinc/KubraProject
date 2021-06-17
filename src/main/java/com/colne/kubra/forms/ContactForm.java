package com.colne.kubra.forms;

import com.colne.kubra.beans.Utilisateur;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

import javax.servlet.http.HttpServletRequest;

public final class ContactForm {
    /* **************************************************************/
    /* ************************ ATTRIBUTES **************************/
    /* **************************************************************/
    public static final String CHAMP_MAIL       = "mailContact";
    public static final String CHAMP_MESSAGE    = "messageContact";

    /**
     * Constructeur vide
     */
    public ContactForm() { }

    /* **************************************************************/
    /* ********************* PUBLIC FUNCTIONS ***********************/
    /* **************************************************************/
    /**
     * Envoie un mail sur l'adresse de l'entreprise contenant l'adresse de l'utilisateur et un message
      * @param request la requête qui contient le message et éventuellement l'adresse de l'utilisateur
     * @param utilisateur le bean si, éventuellement, l'utilisateur est connecté
     */
    public void envoyerMail(HttpServletRequest request, Utilisateur utilisateur){
        String message = (String) request.getAttribute( CHAMP_MESSAGE );
        String mail;
        if(utilisateur == null){
            mail = (String) request.getAttribute( CHAMP_MAIL );
        } else {
            mail = utilisateur.getEmail();
        }
        Email email = new SimpleEmail();
        email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(465);
        email.setSSLOnConnect(true);
        //TODO FINISH
    }
}

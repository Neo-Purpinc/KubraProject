package com.colne.kubra.servlets;

import com.colne.kubra.beans.Utilisateur;
import com.colne.kubra.forms.ContactForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Contact extends HttpServlet {
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String VUE              = "/home";
    public static final String REDIRECTION      = "/";

    public void doPost(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        ContactForm form = new ContactForm();
        Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute( ATT_SESSION_USER );
        form.envoyerMail(request,utilisateur);
        if(utilisateur == null){
            this.getServletContext().getRequestDispatcher( REDIRECTION ).forward( request, response );
        } else {
            this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
        }
    }
}

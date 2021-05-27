package com.colne.kubra.servlets;

import com.colne.kubra.beans.Utilisateur;
import com.colne.kubra.forms.InscriptionForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet ("/home")
public class Home extends HttpServlet {
    public static final String VUE              = "/WEB-INF/jsp/restricted/home.jsp";
    public void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Affichage de la page home de l'espace membre */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
}

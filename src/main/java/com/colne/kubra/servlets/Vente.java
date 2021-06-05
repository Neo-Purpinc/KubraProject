package com.colne.kubra.servlets;

import com.colne.kubra.beans.Portefeuille;
import com.colne.kubra.beans.Transaction;
import com.colne.kubra.beans.Utilisateur;
import com.colne.kubra.dao.*;
import com.colne.kubra.forms.VenteForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/vente")
public class Vente extends HttpServlet {
    public static final String          CONF_DAO_FACTORY            = "daofactory";
    public static final String          VUE                         = "/home";
    private             ActionDao       actionDao;
    private             PortefeuilleDao portefeuilleDao;
    private             PorteactionDao  porteactionDao;
    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        DAOFactory factory = (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY );
        this.actionDao =  factory.getActionDao();
        this.portefeuilleDao = factory.getPortefeuilleDao();
        this.porteactionDao = factory.getPorteactionDao();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Affichage de la page d'inscription */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        VenteForm form = new VenteForm(portefeuilleDao,porteactionDao,actionDao);
        form.ajouterVente(request);
        response.sendRedirect( request.getContextPath() + VUE );
    }


}

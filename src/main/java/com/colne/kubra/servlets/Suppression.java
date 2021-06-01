package com.colne.kubra.servlets;

import com.colne.kubra.beans.Portefeuille;
import com.colne.kubra.beans.Utilisateur;
import com.colne.kubra.dao.DAOFactory;
import com.colne.kubra.dao.PortefeuilleDao;
import com.colne.kubra.dao.UtilisateurDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/suppression" )
public class Suppression extends HttpServlet {
    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String ATT_SESSION_PORTEFEUILLE = "sessionPortefeuille";
    public static final String VUE              = "/";
    private UtilisateurDao utilisateurDao;
    private PortefeuilleDao portefeuilleDao;

    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.utilisateurDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUtilisateurDao();
        this.portefeuilleDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getPortefeuilleDao();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utilisateur utilisateur = (Utilisateur) session.getAttribute( ATT_SESSION_USER );
        Portefeuille portefeuille = (Portefeuille) session.getAttribute( ATT_SESSION_PORTEFEUILLE );
        if( !portefeuille.isEmpty() ){
            portefeuilleDao.supprimer(portefeuille);
        }

        utilisateurDao.supprimer(utilisateur);
        session.invalidate();
        /* Affichage de la page d'accueil */
        response.sendRedirect( request.getContextPath() + VUE );
        System.out.println("doGet");
    }

}

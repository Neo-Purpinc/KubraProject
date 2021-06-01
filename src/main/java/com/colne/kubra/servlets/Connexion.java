package com.colne.kubra.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.colne.kubra.beans.Portefeuille;
import com.colne.kubra.beans.Utilisateur;
import com.colne.kubra.dao.DAOFactory;
import com.colne.kubra.dao.PortefeuilleDao;
import com.colne.kubra.dao.UtilisateurDao;
import com.colne.kubra.forms.ConnexionForm;

@WebServlet("/connexion" )
public class Connexion extends HttpServlet {
    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String ATT_USER         = "utilisateur";
    public static final String ATT_FORM         = "form";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String ATT_SESSION_PORTEFEUILLE = "sessionPortefeuille";
    public static final String VUE              = "/WEB-INF/jsp/restricted/home.jsp";
    public static final String REDIRECTION      = "/";
    private UtilisateurDao utilisateurDao;
    private PortefeuilleDao portefeuilleDao;

    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.utilisateurDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUtilisateurDao();
        this.portefeuilleDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getPortefeuilleDao();
    }

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Affichage de la page home de l'espace membre */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Préparation de l'objet formulaire */
        ConnexionForm form = new ConnexionForm( utilisateurDao);

        /* Traitement de la requête et récupération des beans en résultant */
        Utilisateur utilisateur = form.connecterUtilisateur( request );


        /* Stockage du formulaire et des beans dans l'objet request */
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_USER, utilisateur );
        /**
         * Si aucune erreur de validation n'a eu lieu, alors ajout du bean
         * Utilisateur à la session, sinon suppression du bean de la session.
         */
        if ( form.getErreurs().isEmpty() ) {
            /* Récupération du portefeuille */
            Portefeuille portefeuille = portefeuilleDao.trouver(utilisateur);
            /* Récupération de la session depuis la requête */
            HttpSession session = request.getSession();
            /* Ajout des variables sessions contenant le
               bean utilisateur et le bean portefeuille */
            session.setAttribute( ATT_SESSION_USER, utilisateur );
            session.setAttribute( ATT_SESSION_PORTEFEUILLE, portefeuille);
            session.setAttribute( "first_time",1);
            this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
        } else {
            request.setAttribute( ATT_SESSION_USER, null );
            response.sendRedirect( request.getContextPath() + REDIRECTION );
        }
    }
}
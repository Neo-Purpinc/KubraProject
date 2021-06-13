package com.colne.kubra.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.colne.kubra.beans.Porteaction;
import com.colne.kubra.beans.Portefeuille;
import com.colne.kubra.beans.Utilisateur;
import com.colne.kubra.dao.DAOFactory;
import com.colne.kubra.dao.PorteactionDao;
import com.colne.kubra.dao.PortefeuilleDao;
import com.colne.kubra.dao.UtilisateurDao;
import com.colne.kubra.forms.ConnexionForm;

@WebServlet("/connexion" )
public class Connexion extends HttpServlet {
    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String ATT_SESSION_PORTEFEUILLE = "sessionPortefeuille";
    public static final String VUE              = "/home";
    public static final String REDIRECTION      = "/";
    private UtilisateurDao utilisateurDao;
    private PortefeuilleDao portefeuilleDao;
    private PorteactionDao porteactionDao;

    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.utilisateurDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUtilisateurDao();
        this.portefeuilleDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getPortefeuilleDao();
        this.porteactionDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getPorteactionDao();
    }

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Affichage de la page home de l'espace membre */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Préparation de l'objet formulaire */
        ConnexionForm form = new ConnexionForm( utilisateurDao );

        /* Traitement de la requête et récupération des beans en résultant */
        Utilisateur utilisateur = form.connecterUtilisateur( request );

        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();
        /**
         * Si aucune erreur de validation n'a eu lieu, alors ajout du bean
         * Utilisateur à la session, sinon suppression du bean de la session.
         */
        if ( form.getErreurs().isEmpty() ) {
            /* Récupération du portefeuille */
            Portefeuille portefeuille = portefeuilleDao.trouver(utilisateur);
            Porteaction porteaction = porteactionDao.trouver(utilisateur);
            portefeuille.setPorteaction(porteaction);
            /* Ajout des variables sessions contenant le
               bean utilisateur et le bean portefeuille */
            session.setAttribute( ATT_SESSION_USER, utilisateur );
            session.setAttribute( ATT_SESSION_PORTEFEUILLE, portefeuille);
            session.setAttribute( "first_time",1);
            response.sendRedirect( request.getContextPath() + VUE );
        } else {
            session.setAttribute( "error",2);
            response.sendRedirect( request.getContextPath() + REDIRECTION );
        }
    }
}
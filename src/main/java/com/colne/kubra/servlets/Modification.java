package com.colne.kubra.servlets;

import com.colne.kubra.beans.Utilisateur;
import com.colne.kubra.dao.DAOFactory;
import com.colne.kubra.dao.UtilisateurDao;
import com.colne.kubra.forms.CompteForm;
import com.colne.kubra.forms.InscriptionForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/modification" )
public class Modification extends HttpServlet {
    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String ATT_USER         = "utilisateur";
    public static final String ATT_FORM         = "form";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String VUE              = "/WEB-INF/jsp/restricted/home.jsp";
    public static final String ATT_MODIFICATION     = "modification";
    private UtilisateurDao utilisateurDao;

    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.utilisateurDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUtilisateurDao();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Affichage de la page d'inscription */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Préparation de l'objet formulaire */
        CompteForm form = new CompteForm( utilisateurDao );

        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

        /* Traitement de la requête et récupération du bean en résultant */
        Utilisateur utilisateur = form.modifierInformations( request );

        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_USER, utilisateur );

        if ( form.getErreurs().isEmpty() ) {
            session.setAttribute( ATT_MODIFICATION, 1);
            session.setAttribute( ATT_SESSION_USER, utilisateur );
            this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
        } else {
            session.setAttribute( ATT_MODIFICATION, 2);
            session.setAttribute( ATT_SESSION_USER, utilisateur );
            this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
        }
    }
}

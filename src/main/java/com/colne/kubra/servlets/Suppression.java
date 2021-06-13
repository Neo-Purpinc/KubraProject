package com.colne.kubra.servlets;

import com.colne.kubra.beans.Porteaction;
import com.colne.kubra.beans.Portefeuille;
import com.colne.kubra.beans.Utilisateur;
import com.colne.kubra.dao.DAOFactory;
import com.colne.kubra.dao.PorteactionDao;
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
    private PorteactionDao porteactionDao;
    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        DAOFactory fac = (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY );
        this.utilisateurDao = fac.getUtilisateurDao();
        this.portefeuilleDao = fac.getPortefeuilleDao();
        this.porteactionDao = fac.getPorteactionDao();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utilisateur utilisateur = (Utilisateur) session.getAttribute( ATT_SESSION_USER );
        Portefeuille portefeuille = (Portefeuille) session.getAttribute( ATT_SESSION_PORTEFEUILLE );
        Porteaction porteaction = portefeuille.getPorteaction();
        if( !porteaction.isEmpty()) {
            porteactionDao.supprimer(utilisateur);
        }
        if( !portefeuille.isEmpty() ){
            portefeuilleDao.supprimer(portefeuille);
        }
        utilisateurDao.supprimer(utilisateur);
        session.invalidate();
        /* Affichage de la page d'accueil */
        response.sendRedirect( request.getContextPath() + VUE );
    }

}

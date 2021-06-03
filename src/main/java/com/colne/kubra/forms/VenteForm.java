package com.colne.kubra.forms;

import com.colne.kubra.beans.Action;
import com.colne.kubra.beans.Portefeuille;
import com.colne.kubra.beans.Transaction;
import com.colne.kubra.beans.Utilisateur;
import com.colne.kubra.dao.ActionDao;
import com.colne.kubra.dao.PorteactionDao;
import com.colne.kubra.dao.PortefeuilleDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

public class VenteForm {
    public static final String          ATT_SESSION_USER            = "sessionUtilisateur";
    public static final String          ATT_SESSION_PORTEFEUILLE    = "sessionPortefeuille";
    public static final String          CHAMP_NOM                   = "venteNom";
    public static final String          CHAMP_PRIX                  = "ventePrix";
    public static final String          CHAMP_QUANTITE              = "venteQuantite";
    public static final String          CHAMP_MAX                   = "venteQuantiteMax";
    public static final String          CHAMP_DATE                  = "venteDate";
    public static final String          CHAMP_TYPE                  = "venteType";
    private PortefeuilleDao portefeuilleDao;
    private PorteactionDao porteactionDao;
    private ActionDao actionDao;
    public VenteForm(PortefeuilleDao portefeuilleDao, PorteactionDao porteactionDao, ActionDao actionDao) {
        this.portefeuilleDao = portefeuilleDao;
        this.porteactionDao = porteactionDao;
        this.actionDao = actionDao;
    }

    public void ajouterVente(HttpServletRequest request){
        HttpSession session = request.getSession();
        Utilisateur utilisateur = (Utilisateur) session.getAttribute( ATT_SESSION_USER );
        Portefeuille portefeuille = (Portefeuille) session.getAttribute( ATT_SESSION_PORTEFEUILLE );
        String nom = request.getParameter(CHAMP_NOM) ;
        String prix = request.getParameter(CHAMP_PRIX );
        String quantite = request.getParameter(CHAMP_QUANTITE );
        String quantiteMax = request.getParameter(CHAMP_MAX );
        String type = request.getParameter( CHAMP_TYPE );
        Integer nouvelleQuantite = Integer.parseInt(quantiteMax)-Integer.parseInt(quantite);
        String date = request.getParameter(CHAMP_DATE );
        Transaction transaction = new Transaction();
        transaction.setId_portefeuille( utilisateur.getId() );
        transaction.setAction( actionDao.trouver(nom) );
        transaction.setPrix_unitaire( Double.valueOf(prix) );
        transaction.setQuantite( Integer.valueOf(quantite) );
        transaction.setDate( Timestamp.valueOf(date) );
        transaction.setType( type );
        portefeuilleDao.addTransaction( portefeuille,transaction );
        porteactionDao.modifier( transaction,nouvelleQuantite );
    }
}

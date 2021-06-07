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
        //Récupération des champs et attributs nécessaires
        HttpSession session = request.getSession();
        Utilisateur utilisateur = (Utilisateur) session.getAttribute( ATT_SESSION_USER );
        Portefeuille portefeuille = (Portefeuille) session.getAttribute( ATT_SESSION_PORTEFEUILLE );
        String nom = request.getParameter( CHAMP_NOM ) ;
        Double prix = Double.valueOf( request.getParameter( CHAMP_PRIX ) );
        Integer quantite = Integer.valueOf( request.getParameter( CHAMP_QUANTITE ) );
        Integer quantiteMax = Integer.valueOf( request.getParameter( CHAMP_MAX ) );
        String type = request.getParameter( CHAMP_TYPE );
        Integer nouvelleQuantite = quantiteMax - quantite;
        String date = request.getParameter( CHAMP_DATE );

        //Récupération de l'action
        Action action = actionDao.trouver(nom);
        //Création de la transaction
        Transaction transaction = new Transaction();
        transaction.setId_portefeuille( utilisateur.getId() );
        transaction.setAction( action );
        transaction.setPrix_unitaire( prix );
        transaction.setQuantite( quantite );
        transaction.setDate( Timestamp.valueOf(date) );
        transaction.setType( type );

        portefeuilleDao.addTransaction( portefeuille,transaction );
        porteactionDao.modifier( transaction,nouvelleQuantite );
    }
}

package com.colne.kubra.forms;

import com.colne.kubra.beans.*;
import com.colne.kubra.dao.ActionDao;
import com.colne.kubra.dao.PorteactionDao;
import com.colne.kubra.dao.PortefeuilleDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

public class TransactionForm {
    /****************************************************************/
    /************************** ATTRIBUTES **************************/
    /****************************************************************/
    public static final String          ATT_SESSION_USER            = "sessionUtilisateur";
    public static final String          ATT_SESSION_PORTEFEUILLE    = "sessionPortefeuille";
    public static final String          CHAMP_SYMBOLE               = "transactionSymbole";
    public static final String          CHAMP_PRIX                  = "transactionPrix";
    public static final String          CHAMP_QUANTITE              = "transactionQuantite";
    public static final String          CHAMP_MAX                   = "transactionQuantiteMax";
    public static final String          CHAMP_DATE                  = "transactionDate";
    public static final String          CHAMP_TYPE                  = "transactionType";
    private PortefeuilleDao portefeuilleDao;
    private PorteactionDao porteactionDao;
    private ActionDao actionDao;
    
    public TransactionForm(PortefeuilleDao portefeuilleDao, PorteactionDao porteactionDao, ActionDao actionDao) {
        this.portefeuilleDao = portefeuilleDao;
        this.porteactionDao = porteactionDao;
        this.actionDao = actionDao;
    }

    public void ajouterTransaction(HttpServletRequest request){
        //Récupération des champs et attributs nécessaires
        HttpSession session = request.getSession();
        Utilisateur utilisateur = (Utilisateur) session.getAttribute( ATT_SESSION_USER );
        Portefeuille portefeuille = (Portefeuille) session.getAttribute( ATT_SESSION_PORTEFEUILLE );
        Porteaction porteaction = portefeuille.getPorteaction();
        String symbole = request.getParameter( CHAMP_SYMBOLE );
        Double prix = Double.valueOf( request.getParameter( CHAMP_PRIX ) );
        Integer quantite = Integer.valueOf( request.getParameter( CHAMP_QUANTITE ) );   //Acheté ou vendu
        String type = request.getParameter( CHAMP_TYPE );                               //ACHAT ou VENTE
        String date = request.getParameter( CHAMP_DATE );       //TODO régler le pb de date
        //Récupération de l'action
        Action action = actionDao.trouver(symbole);

        //Calcul de la nouvelle quantité et la nouvelle valeur pour le bean
        Integer nouvelleQuantite;
        if (type.equals("VENTE")) {
            Integer quantiteMax = Integer.valueOf(request.getParameter(CHAMP_MAX));
            nouvelleQuantite = quantiteMax - quantite;
        } else {
            nouvelleQuantite = quantite;
            if(porteaction.existe(action)){
                nouvelleQuantite += porteaction.getQuantite(action);
            }
        }

        //Création de la transaction
        Transaction transaction = new Transaction();
        transaction.setId_portefeuille( utilisateur.getId() );
        transaction.setAction( action );
        transaction.setPrix_unitaire( prix );
        transaction.setQuantite( quantite );
        transaction.setPrix_total( quantite*prix );
        transaction.setDate( Timestamp.valueOf(date) );
        transaction.setType( type );

        if( !porteaction.existe(transaction.getAction()) ){
            porteactionDao.ajouter(porteaction,transaction);
        }
        else{
            if(nouvelleQuantite == 0){
                porteactionDao.supprimerAction(porteaction,transaction);
            } else{
                porteactionDao.modifier( porteaction,transaction );
            }
        }
        portefeuilleDao.addTransaction( portefeuille,transaction );
    }
}

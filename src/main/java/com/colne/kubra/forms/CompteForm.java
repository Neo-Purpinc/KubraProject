package com.colne.kubra.forms;

import com.colne.kubra.beans.Utilisateur;
import com.colne.kubra.dao.UtilisateurDao;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public final class CompteForm {
    private static final String ALGO_CHIFFREMENT    = "SHA-256";
    private static final String CHAMP_ANCIEN_PASS   = "motDePasseInfos";
    private static final String CHAMP_NOUVEAU_PASS  = "nouveauMdpInfos";
    private static final String ATT_SESSION_USER    = "sessionUtilisateur";
    private Map<String, String> erreurs      = new HashMap<String, String>();
    private UtilisateurDao utilisateurDao;

    public CompteForm(UtilisateurDao utilisateurDao) { this.utilisateurDao = utilisateurDao; }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public Utilisateur modifierInformations(HttpServletRequest request ) {
        Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute(ATT_SESSION_USER);
        /* Récupération des champs du formulaire*/
        String ancienMotDePasse = getValeurChamp( request, CHAMP_ANCIEN_PASS );
        String nouveauMotDePasse = getValeurChamp( request, CHAMP_NOUVEAU_PASS );

        try {
            utilisateur = modifierMotDePasse(utilisateur,ancienMotDePasse,nouveauMotDePasse);
        } catch (FormValidationException e) {
            setErreur(CHAMP_ANCIEN_PASS, "Une erreur s'est produite.");
        }
        return utilisateur;
    }

    private Utilisateur modifierMotDePasse(Utilisateur utilisateur, String ancienMotDePasse, String nouveauMotDePasse) throws FormValidationException {
        String email = utilisateur.getEmail();
        /*
         * Utilisation de la bibliothèque Jasypt pour chiffrer le mot de passe
         * efficacement.
         *
         * L'algorithme SHA-256 est ici utilisé, avec par défaut un salage
         * aléatoire et un grand nombre d'itérations de la fonction de hashage.
         *
         * La String retournée est de longueur 56 et contient le hash en Base64.
         */
        ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
        passwordEncryptor.setAlgorithm( ALGO_CHIFFREMENT );
        passwordEncryptor.setPlainDigest( false );
        String mdpBDD = null;
        try{
            mdpBDD = utilisateurDao.trouver(email).getMotDePasse();
        } catch (NullPointerException e){
            System.out.println("ERROR");
        }
        if(!passwordEncryptor.checkPassword(ancienMotDePasse,mdpBDD)) {
            throw new FormValidationException("La combinaison email/mot de passe est inconnue.");
        } else{
            String nouveauMotDePasseHashe = passwordEncryptor.encryptPassword( nouveauMotDePasse );
            utilisateur.setMotDePasse(nouveauMotDePasseHashe);
            utilisateurDao.modifier(utilisateur);
        }
        return utilisateur;
    }
    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErreur( String champ, String message ) { erreurs.put( champ, message ); }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getValeurChamp(HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
}

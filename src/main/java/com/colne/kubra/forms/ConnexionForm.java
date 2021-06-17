package com.colne.kubra.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.colne.kubra.beans.Utilisateur;
import com.colne.kubra.dao.DAOException;
import com.colne.kubra.dao.UtilisateurDao;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;

public final class ConnexionForm {
    /* **************************************************************/
    /* ************************ ATTRIBUTES **************************/
    /* **************************************************************/
    private static final String CHAMP_EMAIL  = "emailLogin";
    private static final String CHAMP_PASS   = "motdepasseLogin";
    private static final String ALGO_CHIFFREMENT = "SHA-256";
    private String              resultat;
    private Map<String, String> erreurs      = new HashMap<String, String>();
    private UtilisateurDao      utilisateurDao;

    /**
     * Constructeur
     * @param utilisateurDao
     */
    public ConnexionForm(UtilisateurDao utilisateurDao) { this.utilisateurDao = utilisateurDao; }

    /* **************************************************************/
    /* ************************** GETTER ****************************/
    /* **************************************************************/
    public Map<String, String> getErreurs() {
        return erreurs;
    }

    /* **************************************************************/
    /* ********************* PUBLIC FUNCTIONS ***********************/
    /* **************************************************************/

    /**
     * Connecte un utilisateur
     * @param request
     * @return le bean Utilisateur correspondant au compte connecté
     */
    public Utilisateur connecterUtilisateur( HttpServletRequest request ) {
        /* Récupération des champs du formulaire */
        String email = getValeurChamp( request, CHAMP_EMAIL );
        String motDePasse = getValeurChamp( request, CHAMP_PASS );
        Utilisateur utilisateur = null;

        try{
            traiterEmail(email);
            traiterMotDePasse(motDePasse,email);
            /* Initialisation du résultat global de la validation. */
            if (erreurs.isEmpty()){
                resultat = "<div class=\"form-group has-success\">\n" +
                            "   <input type=\"text\" value=\"Succès de la connexion\" class=\"form-control form-control-success\" />\n" +
                            "</div>.";
                /* Connexion de l'utilisateur*/
                utilisateur = utilisateurDao.trouver(email);
            } else {
                resultat =  "<div class=\"form-group has-danger\">\n" +
                            "   <input type=\"email\" value=\"Echec de la connexion.\" class=\"form-control form-control-danger\" />\n" +
                            "</div>";
            }
        } catch (DAOException e){
            resultat =  "<div class=\"form-group has-danger\">\n" +
                        "   <input type=\"email\" value=\"Échec de la connexion : une erreur imprévue est survenue, merci de réessayer dans quelques instants.\" class=\"form-control form-control-danger\" />\n" +
                        "</div>";
            e.printStackTrace();
        }

        return utilisateur;
    }

    /* **************************************************************/
    /* ********************* PRIVATE FUNCTIONS **********************/
    /* **************************************************************/
    /**
     * Appel à la validation de l'adresse email reçue et initialisation de la
     * propriété email du bean
     * @param email
     */
    private void traiterEmail( String email ) {
        try {
            validationEmail( email );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_EMAIL, e.getMessage() );
        }
    }

    /**
     * Appel à la validation des mots de passe reçus, chiffrement du mot de
     * passe et initialisation de la propriété motDePasse du bean
     * @param motDePasse
     * @param email
     */
    private void traiterMotDePasse(String motDePasse, String email) {
        try {
            validationMotDePasse(motDePasse,email);
        } catch (FormValidationException e){
            setErreur(CHAMP_EMAIL, e.getMessage());
        }
    }

    /**
     * Validation des mots de passe
     * @param motDePasse
     * @param email
     * @throws FormValidationException
     */
    private void validationMotDePasse(String motDePasse,String email) throws FormValidationException {
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
            //TODO
        }
        if(!passwordEncryptor.checkPassword(motDePasse,mdpBDD)) {
            throw new FormValidationException("La combinaison email/mot de passe est inconnue.");
        }
    }

    /**
     * Validation de l'adresse email
     * @param email
     * @throws FormValidationException
     */
    private void validationEmail( String email ) throws FormValidationException {
        if(utilisateurDao.trouver(email) == null ){
            throw new FormValidationException("L'adresse e-mail est inconnue.");
        }
    }

    /**
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     * @param champ
     * @param message
     */
    private void setErreur( String champ, String message ) { erreurs.put( champ, message ); }

    /**
     * @param request
     * @param nomChamp
     * @return null si le champ est vide, son contenu sinon
     */
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
}

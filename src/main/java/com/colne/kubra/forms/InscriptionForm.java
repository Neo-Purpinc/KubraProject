package com.colne.kubra.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import com.colne.kubra.beans.Utilisateur;
import com.colne.kubra.dao.DAOException;
import com.colne.kubra.dao.UtilisateurDao;

public final class InscriptionForm {
    /* **************************************************************/
    /* ************************ ATTRIBUTES **************************/
    /* **************************************************************/
    private static final String CHAMP_EMAIL      = "email";
    private static final String CHAMP_PASS       = "motdepasse";
    private static final String CHAMP_CONF       = "confirmation";
    private static final String ALGO_CHIFFREMENT = "SHA-256";
    private String              resultat;
    private Map<String, String> erreurs          = new HashMap<String, String>();
    private UtilisateurDao      utilisateurDao;

    /**
     * Constructeur
     * @param utilisateurDao
     */
    public InscriptionForm( UtilisateurDao utilisateurDao ) {
        this.utilisateurDao = utilisateurDao;
    }
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
     * Inscrit un nouvel utilisateur en BDD
     * @param request
     * @return un bean Utilisateur
     */
    public Utilisateur inscrireUtilisateur( HttpServletRequest request ) {
        String email = getValeurChamp( request, CHAMP_EMAIL );
        String motDePasse = getValeurChamp( request, CHAMP_PASS );
        String confirmation = getValeurChamp( request, CHAMP_CONF );

        Utilisateur utilisateur = new Utilisateur();
        try {
            traiterEmail( email, utilisateur );
            traiterMotsDePasse( motDePasse, confirmation, utilisateur );

            if ( erreurs.isEmpty() ) {
                utilisateurDao.ajouter( utilisateur );
                resultat =  "<div class=\"form-group has-success\">\n" +
                            "   <input type=\"text\" value=\"Succ??s de l'inscription\" class=\"form-control form-control-success\" />\n" +
                            "</div>.";
            } else {
                resultat =  "<div class=\"form-group has-danger\">\n" +
                            "   <input type=\"email\" value=\"Echec de l'inscription.\" class=\"form-control form-control-danger\" />\n" +
                            "</div>";
            }
        } catch ( DAOException e ) {
            resultat =  "<div class=\"form-group has-danger\">\n" +
                        "   <input type=\"email\" value=\"??chec de l'inscription : une erreur impr??vue est survenue, merci de r??essayer dans quelques instants.\" class=\"form-control form-control-danger\" />\n" +
                        "</div>";
            e.printStackTrace();
        }

        return utilisateur;
    }

    /* **************************************************************/
    /* ********************* PRIVATE FUNCTIONS **********************/
    /* **************************************************************/
    /**
     * Appel ?? la validation de l'adresse email re??ue et initialisation de la
     * propri??t?? email du bean
     * @param email
     * @param utilisateur
     */
    private void traiterEmail( String email, Utilisateur utilisateur ) {
        try {
            validationEmail( email );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_EMAIL, e.getMessage() );
        }
        utilisateur.setEmail( email );
    }

    /**
     * Appel ?? la validation des mots de passe re??us, chiffrement du mot de
     * passe et initialisation de la propri??t?? motDePasse du bean
     * @param motDePasse
     * @param confirmation
     * @param utilisateur
     */
    private void traiterMotsDePasse( String motDePasse, String confirmation, Utilisateur utilisateur ) {
        try {
            validationMotsDePasse( motDePasse, confirmation );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_PASS, e.getMessage() );
            setErreur( CHAMP_CONF, null );
        }

        /*
         * Utilisation de la biblioth??que Jasypt pour chiffrer le mot de passe
         * efficacement.
         * 
         * L'algorithme SHA-256 est ici utilis??, avec par d??faut un salage
         * al??atoire et un grand nombre d'it??rations de la fonction de hashage.
         * 
         * La String retourn??e est de longueur 56 et contient le hash en Base64.
         */
        ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
        passwordEncryptor.setAlgorithm( ALGO_CHIFFREMENT );
        passwordEncryptor.setPlainDigest( false );
        String motDePasseChiffre = passwordEncryptor.encryptPassword( motDePasse );

        utilisateur.setMotDePasse( motDePasseChiffre );
    }

    /**
     * Validation de l'adresse email
     * @param email
     * @throws FormValidationException
     */
    private void validationEmail( String email ) throws FormValidationException {
        if ( email != null ) {
            if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
                throw new FormValidationException( "Merci de saisir une adresse mail valide." );
            } else if ( utilisateurDao.trouver( email ) != null ) {
                throw new FormValidationException( "Cette adresse email est d??j?? utilis??e, merci d'en choisir une autre." );
            }
        } else {
            throw new FormValidationException( "Merci de saisir une adresse mail." );
        }
    }

    /**
     * Validation des mots de passe
     * @param motDePasse
     * @param confirmation
     * @throws FormValidationException
     */
    private void validationMotsDePasse( String motDePasse, String confirmation ) throws FormValidationException {
        if ( motDePasse != null && confirmation != null ) {
            if ( !motDePasse.equals( confirmation ) ) {
                throw new FormValidationException( "Les mots de passe entr??s sont diff??rents, merci de les saisir ?? nouveau." );
            } else if ( motDePasse.length() < 3 ) {
                throw new FormValidationException( "Les mots de passe doivent contenir au moins 3 caract??res." );
            }
        } else {
            throw new FormValidationException( "Merci de saisir et confirmer votre mot de passe." );
        }
    }

    /**
     * Ajoute un message correspondant au champ sp??cifi?? ?? la map des erreurs.
     * @param champ
     * @param message
     */
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

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
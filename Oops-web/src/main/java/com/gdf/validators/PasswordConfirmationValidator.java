package com.gdf.validators;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author nicolas
 */
@FacesValidator("com.gdf.passwordConfirmationValidator")
public class PasswordConfirmationValidator implements Validator {

    private static final String CHAMP_MOT_DE_PASSE = "confirm";
    private static final String MOTS_DE_PASSE_DIFFERENTS = "Les mots de passe ne correspondent pas !";

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {   

        if (value != null) {
            
            /* Récupération de la valeur du champ confirmation */
            String confirmation = (String) value;

            /*
             * Récupération de l'attribut mot de passe parmi la liste des attributs
             * du composant confirmation
             */
            UIInput composantMotDePasse = (UIInput) component.getAttributes().get(CHAMP_MOT_DE_PASSE);
            /*
             * Récupération de la valeur du champ, c'est-à-dire le mot de passe
             * saisi
             */
            String motDePasse = (String) composantMotDePasse.getValue();

            if (!confirmation.equals(motDePasse)) {
                /*
                 * Envoi d'une exception contenant une erreur de validation JSF
                 * initialisée avec le message destiné à l'utilisateur, si les mots
                 * de passe sont différents
                 */
                throw new ValidatorException(
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, MOTS_DE_PASSE_DIFFERENTS, null));
            }
        }
    }
}

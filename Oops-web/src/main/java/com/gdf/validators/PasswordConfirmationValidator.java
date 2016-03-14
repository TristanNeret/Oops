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
 * Test is both password match
 * @author nicolas
 */
@FacesValidator("com.gdf.passwordConfirmationValidator")
public class PasswordConfirmationValidator implements Validator {

    private static final String CHAMP_MOT_DE_PASSE = "confirm";
    private static final String MOTS_DE_PASSE_DIFFERENTS = "Les mots de passe ne correspondent pas !";

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        if (value != null) {

            String password = (String) value;
            String confirmation = (String) component.getAttributes().get(CHAMP_MOT_DE_PASSE);
            if (!confirmation.equals(password)) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, MOTS_DE_PASSE_DIFFERENTS, null));
            }
        }
    }
}

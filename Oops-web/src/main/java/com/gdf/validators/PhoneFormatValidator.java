package com.gdf.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Test if the phone format is correct
 * @author bibo
 */
@FacesValidator("com.gdf.phoneFormatValidator")
public class PhoneFormatValidator implements Validator {

    private static final String FORMAT = "Le numéro de téléphone doit contenir 10 chiffres !";

    /**
     * Creates a new instance of MailFormatValidator
     */
    public PhoneFormatValidator() {

    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        if (value != null) {

            String telephone = value.toString();
            if (!telephone.matches("[0-9]{10}")) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, FORMAT, null));
            }
        }
    }
}

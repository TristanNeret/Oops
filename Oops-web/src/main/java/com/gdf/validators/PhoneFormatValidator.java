package com.gdf.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author bibo
 */

@FacesValidator("com.gdf.phoneFormatValidator")
public class PhoneFormatValidator implements Validator {
    
    private static final String BAD_PHONE_FORMAT = "Le numéro de téléphone ne doit contenir que des chiffres";
    private static final String BAD_SIZE = "Le numéro de téléphone ne doit contenir en moins 6 chiffres";
    private static final String EMPTY = "Merci d'indiquer une numéro de téléphone";

    /**
     * Creates a new instance of MailFormatValidator
     */
    public PhoneFormatValidator() {
        
    }
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
         if ( value != null ) {

            String telephone = value.toString();   
            if ( !telephone.matches( "^\\d+$" ) ) {
                        throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, BAD_PHONE_FORMAT, null));
            } else if ( telephone.length() < 4 ) {
                    throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, BAD_SIZE, null));
            }

        } else {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, EMPTY, null));
        }            
    }
}
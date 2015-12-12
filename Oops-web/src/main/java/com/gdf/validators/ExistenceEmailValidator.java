/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.validators;

import com.gdf.ejb.SearchBean;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

/**
 *
 * @author borui
 */

@FacesValidator("existenceEmailValidator")
public class ExistenceEmailValidator implements Validator {

    private static final String EMAIL_ALREADY_USED = "Cette adresse email est déjà utilisée";

    @Inject
    SearchBean sb;
    
    /**
     * Creates a new instance of ExistenceEmailValidator
     */
    public ExistenceEmailValidator() {
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        //To change body of generated methods, choose Tools | Templates.
        String email = (String) value;

        
        if (sb.searchContractorByEmail(email).get(0) != null) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, EMAIL_ALREADY_USED, null));

        }

    }
}

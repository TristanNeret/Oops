/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.validators;

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
 * @author aziz 
 */
@ManagedBean
@RequestScoped
@FacesValidator("com.gdf.notNullnBSValidator")
public class NotNullnBSValidator implements Validator {
    
    private static final String MSG = "Veuillez indiquer une valeur";
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        if (value == null || ((String) value).trim().isEmpty()) {
            
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, MSG, null));

        }

    }
}

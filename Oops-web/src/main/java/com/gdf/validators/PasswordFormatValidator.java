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
 * PasswordFormatValidator
 * @author bibo
 */
@ManagedBean
@RequestScoped
@FacesValidator("com.gdf.passwordFormatValidator")
public class PasswordFormatValidator implements Validator {
    
    private static final String PASSWORD_FORMAT = "Le mot de passe doit contenir entre 8 et 20 caractères.";
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        if(value.toString().length() < 8 || value.toString().length() > 20) {
            
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, PASSWORD_FORMAT, null));
            
        }
        
    }
    
}

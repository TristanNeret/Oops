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
@FacesValidator("com.gdf.sirenValidator")
public class SirenValidator implements Validator {

    private static final String SIREN_FORMAT = "Le n° SIREN doit contenir 9 chiffres";
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
         if(value == null || !value.toString().matches("[0-9]{9}")) {
            
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, SIREN_FORMAT, null));
            
        }
    }
    
}

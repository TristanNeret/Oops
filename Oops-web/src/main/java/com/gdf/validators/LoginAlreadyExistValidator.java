/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.validators;

import com.gdf.ejb.SearchBean;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Test if a login is already used by another user
 * @author Tristan
 */
@ManagedBean
@RequestScoped
@FacesValidator(value="com.gdf.loginAlreadyExistValidator")
public class LoginAlreadyExistValidator implements Validator {

    private static final String LOGIN_ALREADY_USED = "Ce login est déjà utilisé !";

    @EJB
    SearchBean sb;
    
    /**
     * Creates a new instance of LoginAlreadyExistValidator
     */
    public LoginAlreadyExistValidator() {
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        String login = (String)value;
        
        // Test if a Tenderer or a Contractor already uses this login
        if(this.sb.searchContractorByLogin(login) != null || this.sb.searchTendererByLogin(login) != null) {
            
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, LOGIN_ALREADY_USED, null));
            
        }
        
    }
    
}


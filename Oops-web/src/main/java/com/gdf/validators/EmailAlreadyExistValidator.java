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
 * Test if a email is already used by another user
 * @author Nicolas
 */
@ManagedBean
@RequestScoped
@FacesValidator(value="com.gdf.emailAlreadyExistValidator")
public class EmailAlreadyExistValidator implements Validator {

    private static final String EMAIL_ALREADY_USED = "Cet email est déjà utilisé !";

    @EJB
    SearchBean sb;
    
    /**
     * Creates a new instance of EmailAlreadyExistValidator
     */
    public EmailAlreadyExistValidator() {
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        String email = (String)value;
        
        // Test if a Tenderer or a Contractor already uses this email
        if(this.sb.searchContractorByEmail(email) != null || this.sb.searchTendererByEmail(email) != null) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, EMAIL_ALREADY_USED, null));
        }
        
    }
        
}

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
 * Test if the entreprise is already register
 * @author Tristan
 */
@ManagedBean
@RequestScoped
@FacesValidator(value="com.gdf.sirenAlreadyExistValidator")
public class SirenAlreadyExistValidator implements Validator {

    private static final String SIREN_ALREADY_EXIST = "L'entreprise correspondant à ce numéro SIREN est déjà inscrite.";

    @EJB
    SearchBean sb;
    
    /**
     * Creates a new instance of LoginAlreadyExistValidator
     */
    public SirenAlreadyExistValidator() {
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        String siren = (String)value;
        
        // Test if a Contractor with this SIREN value already exist
        if(this.sb.searchContractorBySiren(siren) != null) {
            
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, SIREN_ALREADY_EXIST, null));
            
        }
        
    }
    
}


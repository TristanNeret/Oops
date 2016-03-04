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
 * Test if a String is only composed by whitespace
 * @author Tristan
 */
@ManagedBean
@RequestScoped
@FacesValidator("com.gdf.noBlankSpaceValidator")
public class NoBlankSpaceValidator implements Validator {

    private static final String NO_BLANK_SPACE = "Les champs de saisie ne peuvent contenir que des espaces !";

    @EJB
    SearchBean sb;
    
    /**
     * Creates a new instance of NoBlankSpaceValidator
     */
    public NoBlankSpaceValidator() {
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        if (value == null || ((String) value).trim().isEmpty()) {

            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, NO_BLANK_SPACE, null));

        }

    }
    
}

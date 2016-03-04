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
 *
 * @author aziz
 */
@ManagedBean
@RequestScoped
@FacesValidator(value = "com.gdf.rcsAlreadyExistValidator")
public class RcsAlreadyExistValidator implements Validator {

    private static final String SIREN_ALREADY_EXIST = "L'entreprise correspondant à ce numéro RCS est déjà inscrite !";

    @EJB
    SearchBean sb;

    public RcsAlreadyExistValidator() {

    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        String rcs = (String) value;

        // Test if a Contractor with this RCS value already exist
        if (this.sb.searchContractorByRcs(rcs) != null) {

            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, SIREN_ALREADY_EXIST, null));

        }

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.validators;

import com.gdf.ejb.SearchBean;
import com.gdf.persistence.Contractor;
import com.gdf.session.SessionBean;
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
 * Test if Rcs already exist for the modification
 * @author aziz
 */
@ManagedBean
@RequestScoped
@FacesValidator(value = "com.gdf.rcsAlreadyExistForModificationValidator")
public class RcsAlreadyExistForModificationValidator implements Validator {

    private static final String RCS_ALREADY_EXIST = "L'entreprise correspondant à ce numéro RCS est déjà inscrite !";
    private static final String RCS_NULL = "Veuillez indiquer un numéro RCS !";

    @EJB
    SearchBean sb;

    public RcsAlreadyExistForModificationValidator() {

    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        String newRcs = (String) value;

        if (newRcs == null) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, RCS_NULL, null));
        }

        long id = SessionBean.getUserId();
        String formerRcs = sb.searchContractorById(id).getLegalInformation().getRcs();

        if (!newRcs.equals(formerRcs)) {
            // Test if a Contractor with this RCS value already exist
            if (this.sb.searchContractorByRcs(newRcs) != null) {

                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, RCS_ALREADY_EXIST, null));

            }
        }

    }

}

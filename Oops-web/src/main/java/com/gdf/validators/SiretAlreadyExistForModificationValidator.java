/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.validators;

import com.gdf.ejb.SearchBean;
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
 *
 * @author aziz
 */
@ManagedBean
@RequestScoped
@FacesValidator(value = "com.gdf.siretAlreadyExistForModificationValidator")
public class SiretAlreadyExistForModificationValidator implements Validator {

    private static final String SIRET_ALREADY_EXIST = "L'entreprise correspondant à ce numéro SIRET est déjà inscrite !";
    private static final String SIRET_NULL = "Veuillez entrer un numéro SIRET !";

    @EJB
    SearchBean sb;

    public SiretAlreadyExistForModificationValidator() {

    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        String newSiret = (String) value;

        if (newSiret == null) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, SIRET_NULL, null));
        }

        long id = SessionBean.getUserId();
        String formerSiret = sb.searchContractorById(id).getLegalInformation().getSiret();

        if (!newSiret.equals(formerSiret)) {
            if (this.sb.searchContractorByRcs(newSiret) != null) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, SIRET_ALREADY_EXIST, null));
            }
        }
    }
}

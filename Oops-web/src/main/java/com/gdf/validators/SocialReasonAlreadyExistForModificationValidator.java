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
@FacesValidator(value = "com.gdf.socialReasonAlreadyExistForModificationValidator")
public class SocialReasonAlreadyExistForModificationValidator implements Validator {

    private static final String SOCIAL_REASON_ALREADY_EXIST = "L'entreprise correspondant à cette raison sociale est déjà inscrite !";
    private static final String SOCIAL_REASON_NULL = "Veuillez enter une raison sociale  !";

    @EJB
    SearchBean sb;

    public SocialReasonAlreadyExistForModificationValidator() {

    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        String newSocialReason = (String) value;

        if (newSocialReason == null) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, SOCIAL_REASON_NULL, null));
        }

        long id = SessionBean.getUserId();
        String formerSocialReason = sb.searchContractorById(id).getSocialReason();

        if (!newSocialReason.equals(formerSocialReason)) {
            if (this.sb.searchContractorByRcs(newSocialReason) != null) {

                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, SOCIAL_REASON_ALREADY_EXIST, null));

            }
        }

    }

}


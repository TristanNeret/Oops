/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.validators;


import com.gdf.ejb.SearchBean;
import com.gdf.persistence.Contractor;
import com.gdf.persistence.Tenderer;
import com.gdf.session.SessionBean;
import java.util.Map;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
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
@FacesValidator(value="com.gdf.loginAlreadyExistForModificationValidator")
public class LoginAlreadyExistForModificationValidator implements Validator {

    private static final String LOGIN_ALREADY_USED = "Ce login est déjà utilisé !";

    @EJB
    SearchBean sb;
    
    /**
     * Creates a new instance of LoginAlreadyExistValidator
     */
    public LoginAlreadyExistForModificationValidator() {
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        String newLogin = (String)value;

        long id = SessionBean.getUserId();
        String cat = SessionBean.getUserCategory();
        String formerLogin = null;
        
        if(cat.equals(Contractor.userCategory))
            formerLogin = sb.searchContractorById(id).getLogin();
        else
            formerLogin = sb.searchTendererById(id).getLogin();
              
        if(!newLogin.equals(formerLogin)){
        // Test if a Tenderer or a Contractor already uses this login
            if(this.sb.searchContractorByLogin(newLogin) != null || this.sb.searchTendererByLogin(newLogin) != null) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, LOGIN_ALREADY_USED, null));
            }
        }    
        
    }
    
}
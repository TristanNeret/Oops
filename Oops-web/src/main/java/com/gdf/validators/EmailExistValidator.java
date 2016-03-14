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
 * Test if an email is already used by another user 
 * @author aziz
 */
@ManagedBean
@RequestScoped
@FacesValidator(value="com.gdf.emailExistValidator")
public class EmailExistValidator implements Validator {

    private static final String EMAIL_ALREADY_USED = "Aucun compte ne correspond à cet email !";

    @EJB
    SearchBean sb;

    public EmailExistValidator() {
        
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        String email = (String)value;
        
        if(this.sb.searchContractorByEmail(email) == null && this.sb.searchTendererByEmail(email) == null) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, EMAIL_ALREADY_USED, null));
        }   
    }      
}


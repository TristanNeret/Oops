/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.validators;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


/**
 *
 * @author bettaieb
 */
@ManagedBean
@RequestScoped
@FacesValidator("com.gdf.phoneFormatValidator")
public class phoneFormatValidator implements Validator {

    private static final String BAD_MAIL_FORMAT = "Le format de numéro de téléphone est incorrect.";
        
        public phoneFormatValidator()
            {
     
             }
         @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        String phoneformat = "(0|(\\+33)|(0033))[1-9][0-9]{8}";
        Pattern pattern = Pattern.compile(phoneformat); 
        Matcher matcher = pattern.matcher(value.toString().toUpperCase());
        
        if(!matcher.matches()) {
            
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, BAD_MAIL_FORMAT, null));
        }
        
    }
    
}



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.validators;
import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.omnifaces.util.Faces;
/**
 *
 * @author bettaieb
 * 
 * 
 */
@ManagedBean
@RequestScoped
@FacesValidator(value="com.gdf.PasswordFormatValidator")
public class PasswordFormatValidator implements Validator{
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
     try {
        if((value.toString().length() < 6)||(null==value.toString())) {
            FacesMessage msg = new FacesMessage("Le mot de passe doit faire au moins 6 caractères.","Le mot de passe doit faire au moins 6 caractères.");
            throw new ValidatorException(msg);
        }
            
        //if (value.toString().equals(com.gdf.managedBean.AuthenticationBean.class.))
     } catch (Exception e) {FacesMessage msg1 = new FacesMessage("Le mot de passe doit faire au moins 6 caractères.","Le mot de passe doit faire au moins 6 caractères.");
            throw new ValidatorException(msg1);}
    
    
}}
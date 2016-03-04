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
 * @author bibo
 */
@ManagedBean
@RequestScoped
@FacesValidator("com.gdf.mailFormatValidator")
public class MailFormatValidator implements Validator {

    private static final String BAD_MAIL_FORMAT = "Le format de l'addresse mail est incorrect !";

    /**
     * Creates a new instance of MailFormatValidator
     */
    public MailFormatValidator() {

    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        if (value != null) {

            String emailRegEx = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            Pattern pattern = Pattern.compile(emailRegEx);
            Matcher matcher = pattern.matcher(value.toString().toUpperCase());

            if (!matcher.matches()) {

                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, BAD_MAIL_FORMAT, null));
            }
        }
    }
}

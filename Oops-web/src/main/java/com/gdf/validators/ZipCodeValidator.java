package com.gdf.validators;

import com.gdf.singleton.PopulateDB;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Test if the zip code is correct
 * @author bibo
 */
@FacesValidator("com.gdf.zipValidator")
public class ZipCodeValidator implements Validator {

    private final static String NOT_VALIDE = "Veuillez saisir un code postal valide ! ";
    private final static String EMPTY = "Veuillez saisir un code postal ! ";

    @EJB
    private PopulateDB pdb;

    /**
     * Creates a new instance of ZipCodeValidator
     */
    public ZipCodeValidator() {

    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        if (value != null) {

            String code = value.toString();

            if (code.length() == 5 && pdb.getAllTown(code) == null) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, NOT_VALIDE, null));
            }
        }
    }
}

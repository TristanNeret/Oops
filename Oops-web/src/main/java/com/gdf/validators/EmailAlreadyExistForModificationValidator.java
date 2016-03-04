
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
 * Test if a email is already used by another user
 * @author Nicolas
 */
@ManagedBean
@RequestScoped
@FacesValidator(value="com.gdf.emailAlreadyExistForModificationValidator")
public class EmailAlreadyExistForModificationValidator implements Validator {

    private static final String EMAIL_ALREADY_USED = "Cet email est déjà utilisé !";
    private static final String EMAIL_NULL ="Veuillez indiquer un email !";

    @EJB
    SearchBean sb;
    
    /**
     * Creates a new instance of EmailAlreadyExistForModificationValidator
     */
    public EmailAlreadyExistForModificationValidator() {
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        String newEmail = (String)value;
        
        if(newEmail == null){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, EMAIL_NULL, null));
        }
       
        long id = SessionBean.getUserId();
        String cat = SessionBean.getUserCategory();;
        String formerEmail = null ;
        
        if(cat.equals(Contractor.userCategory))
            formerEmail = sb.searchContractorById(id).getEmail();
        else
            formerEmail = sb.searchTendererById(id).getEmail();
              
        if(!newEmail.equals(formerEmail)){
        // Test if a Tenderer or a Contractor already uses this login
            if(this.sb.searchContractorByEmail(newEmail) != null || this.sb.searchTendererByEmail(newEmail) != null) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, EMAIL_ALREADY_USED, null));
            }
        }   
        
    }
        
}

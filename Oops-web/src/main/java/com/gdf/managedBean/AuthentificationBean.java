/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import com.gdf.persistence.Contractor;
import com.gdf.persistence.Moderator;
import com.gdf.persistence.Tenderer;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * AuthentificationBean
 * @author Tristan
 */
@Named(value = "authentificationBean")
@SessionScoped
public class AuthentificationBean implements Serializable {
    
    @EJB
    private com.gdf.ejb.AuthentificationBean ab;
    
    private String userName;
    private String userPassword;
    
    /**
     * Test Tenderer authentification
     */
    public void validateTendererAuthentification() {
        
        Long userId = this.ab.isTendererValid(userName, userPassword);
        if (userId != null) {
            
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userID", userId);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userCategory", Tenderer.userCategory);
            
        } else {
            
            // Bad login/password
            
        }
        
    }
    
    /**
     * Test Contractor authentification
     */
    public void validateContractorAuthentification() {
        
        Long userId = this.ab.isContractorValid(userName, userPassword);
        if (userId != null) {
            
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userID", userId);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userCategory", Contractor.userCategory);
            
        } else {
            
            // Bad login/password
            
        }
        
    }
    
    /**
     * Test Moderator authentification
     */
    public void validateModeratorAuthentification() {
        
        Long userId = this.ab.isModeratorValid(userName, userPassword);
        if (userId != null) {
            
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userID", userId);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userCategory", Moderator.userCategory);
            
        } else {
            
            // Bad login/password
            
        }
        
    }
 
    /**
     * Logout the user
     * @return 
     */
    public String logout() {
        
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        
        return "index?faces-redirect=true";
        
    }
    
}

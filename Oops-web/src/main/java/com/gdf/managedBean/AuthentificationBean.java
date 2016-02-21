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
     * Test if a Tenderer is connected
     * @return True is a Tenderer is connected, or False
     */
    public boolean isTendererConnected() {
        
        String userCategory = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("userCategory");
        if (userCategory != null) {
            if (userCategory.equals(Tenderer.userCategory)) {
                
                return true;
                
            } 
        } 
        
        return false;
        
    }
    
    /**
     * Test if a Contractor is connected
     * @return True is a Tenderer is connected, or False
     */
    public boolean isContractorConnected() {
        
        String userCategory = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("userCategory");
        if (userCategory != null) {
            if (userCategory.equals(Contractor.userCategory)) {
                
                return true;
                
            } 
        } 
        
        return false;
        
    }
    
    /**
     * Test if a Moderator is connected
     * @return True is a Moderator is connected, or False
     */
    public boolean isModeratorConnected() {
        
        String userCategory = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("userCategory");
        if (userCategory != null) {
            if (userCategory.equals(Moderator.userCategory)) {
                
                return true;
                
            } 
        } 
        
        return false;
        
    }
 
    /**
     * Logout the user
     * @return 
     */
    public String logout() {
        
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        
        return "index?faces-redirect=true";
        
    }
    
    // GETTER/SETTER

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    
}

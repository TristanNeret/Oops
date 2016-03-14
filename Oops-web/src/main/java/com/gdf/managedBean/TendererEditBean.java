/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import com.gdf.ejb.TendererManagerBean;
import com.gdf.persistence.Tenderer;
import com.gdf.session.SessionBean;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * TendererEditBean
 * @author hamou
 */
@Named
@ViewScoped
public class TendererEditBean implements Serializable {

    @EJB
    TendererManagerBean tmb;

    private Tenderer tenderer;
    
    @NotNull(message = "Veuillez saisir un de mot de passe !")
    @Size(min = 6, message = "Le mot de passe doit contenir au moins 6 caractères !")
    private String password;

    @NotNull(message = "Veuillez saisir une confirmation de mot de passe !")
    private String passwordConfirm;

    public TendererEditBean() {     
        
    }

    /**
     * Initialize Tenderer to be connected
     */
    @PostConstruct
    public void initBean() {
        
        // Get the current tenderer connected from database
        tenderer = tmb.getTendererById(SessionBean.getUserId());
        
    }
    
    /**
     * Test if the Tenderer has an avatar
     * @return True if Tenderer has an avatar, or False
     */
    public boolean isAvatar() {
        
        return this.tenderer.getAvatar() != null && !this.tenderer.getAvatar().equals("");
        
    }
    
    /**
     * Update the informations of the tenderer
     */
    public void update(){     
        this.tenderer = tmb.update(this.tenderer);
    }
    
    public void updatePassword() {
        tenderer.setPassword(password);
        tmb.update(tenderer);
        // reset
        password = "";
        passwordConfirm = "";
    }
    
    // GETTER / SETTER

    public Tenderer getTenderer() {
        return tenderer;
    }

    public void setTenderer(Tenderer tenderer) {
        this.tenderer = tenderer;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}

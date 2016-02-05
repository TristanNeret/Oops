/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import com.gdf.ejb.TendererManagerBean;
import com.gdf.persistence.Tenderer;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.validation.constraints.Size;

/**
 *
 * @author hamou
 */
@Named
@ViewScoped
public class TendererInfoMngBean implements Serializable {

    @EJB
    TendererManagerBean tmb;

    private Tenderer tenderer;
    @Size(min = 8, max = 20, message = "Le mot de passe doit contenir entre 8 et 20 caractères.")
    private String password;
    private String passConfirm;


    public TendererInfoMngBean() {
        tenderer = null;
    }

    /**
     * Initialize Tenderer to be connected
     */
    @PostConstruct
    public void initBean() {
        
        // Temporary used to connect a Contractor
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userID", "1");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userCategory", Tenderer.userCategory);
        
        // Check if a user is connected
        String userID = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
      
        // get the current tenderer connected from database
        tenderer = tmb.getTendererById(new Long(userID));
        
    }
    
    /**
     * update the informations of the tenderer
     */
    public void update(){
        if(password != null){
            this.tenderer.setPassword(password);
        }     
        this.tenderer = tmb.update(this.tenderer);
    }

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

    public String getPassConfirm() {
        return passConfirm;
    }

    public void setPassConfirm(String passConfirm) {
        this.passConfirm = passConfirm;
    }

}

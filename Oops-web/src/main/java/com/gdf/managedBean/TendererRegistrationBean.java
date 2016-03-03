/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import com.gdf.ejb.RegistrationBean;
import com.gdf.persistence.Tenderer;
import com.gdf.session.SessionBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

/**
 * Manage Tenderer registration
 *
 * @author bibo
 */
@Named(value = "tendererRegistrationBean")
@RequestScoped
public class TendererRegistrationBean {

    @EJB
    RegistrationBean rb;
    
    private Tenderer tenderer;
    
    @NotNull(message = "Veuillez saisir une confirmation de mot de passe")
    private String passwordConfirm;  
    
    /**
     * Creates a new instance of TendererRegisterBean
     */
    public TendererRegistrationBean() {
        
    }
    
    @PostConstruct
    public void init() {
        tenderer = new Tenderer();
    }

    /**
     * Register a new Tenderer
     */
    public void submit() {
        Long id = this.rb.register(tenderer);
        // Connect the Tenderer
        HttpSession session = SessionBean.getSession();
        session.setAttribute("userID", id);
        session.setAttribute("userCategory", Tenderer.userCategory);
        session.setAttribute("userName", tenderer.getLogin());
        session.setAttribute("userAvatar", tenderer.getAvatar());
    }

    public Tenderer getTenderer() {
        return tenderer;
    }

    public void setTenderer(Tenderer tenderer) {
        this.tenderer = tenderer;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
    
}

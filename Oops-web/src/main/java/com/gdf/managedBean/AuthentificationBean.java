/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import com.gdf.persistence.Contractor;
import com.gdf.persistence.Moderator;
import com.gdf.persistence.Tenderer;
import com.gdf.session.SessionBean;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * AuthentificationBean
 *
 * @author Tristan
 */
@ManagedBean
@SessionScoped
public class AuthentificationBean implements Serializable {

    @EJB
    private com.gdf.ejb.AuthentificationBean ab;
    
    @EJB
    private com.gdf.ejb.SearchBean sb;

    private boolean authentificationVisible;
    
    private String userName;
    private String userPassword;

    @PostConstruct
    public void initBean() {

        this.userName = "";
        this.userPassword = "";
        this.authentificationVisible = false;

    }
    
    /**
     * Return avatar link of connected User
     * @return avatar link
     */
    public String getAvatar() {
        
        String userAvatar = SessionBean.getUserAvatar();
        if (userAvatar != null) {
            if (!"".equals(userAvatar)) {
            
                return userAvatar;
                
            }
        }
        
        return null;
        
    }
    
    /**
     * Return Tenderer/Contractor name
     * @return the login or the social reason of Tenderer/Contractor
     */
    public String getName() {
        
        String userLogin = SessionBean.getUserName();
        if (userLogin != null) {
            if (!"".equals(userLogin)) {
            
                return userLogin;
                
            }
        }
        
        return "User";
        
    }
    
    /**
     * Show authentifications inputs
     * @return link to same page
     */
    public String showAuthentification() {
        
        this.setAuthentificationVisible(true);
        
        return "#";
        
    }
    
    /**
     * Hide authentifications inputs
     * @return link to same page
     */
    public String hideAuthentification() {
        
        this.setAuthentificationVisible(false);
        
        return "#";
        
    }
    
    /**
     * Hide authentifications inputs and go to password forgotten page
     * @return link to password forgotten page
     */
    public String hideAuthentificationAndGoFP() {
        
        this.setAuthentificationVisible(false);
        
        return "/passwordForgotten.xhtml?faces-redirect=true";
        
    }
    
    /**
     * Go to account user page
     * @throws java.io.IOException exception on URL management
     */
    public void goToAccount() throws IOException {
        
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        
        String userCategory = SessionBean.getUserCategory();
        if (userCategory != null) {
            switch (userCategory) {
                case Tenderer.userCategory:
                    context.redirect(context.getRequestContextPath().replaceFirst("/Oops-web*", "/Oops-web") + "/views/tendererManagement.xhtml");
                    break;
                case Contractor.userCategory:
                    context.redirect(context.getRequestContextPath().replaceFirst("/Oops-web*", "/Oops-web") + "/views/contractorManagement.xhtml");
                    break;
                case Moderator.userCategory:
                    context.redirect(context.getRequestContextPath().replaceFirst("/Oops-web*", "/Oops-web") + "/views/adminManager.xhtml");
                    break;
            }
        }
        
    }
    
    /**
     * Return current url
     * @return current url path
     */
    public String getCurrentUrl() {
        
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        //String url = ((HttpServletRequest)context.getRequest()).getRequestURI();

        return context.getRequestContextPath().replaceFirst("/Oops-web*", "/Oops-web") + "/";
        
    }
    
    // AUTHENTIFICATION

    /**
     * Test Tenderer/Contractor authentification
     *
     * @return link to same page
     */
    public String validateUserAuthentification() {

        Long userId = this.ab.isTendererValid(userName, userPassword);
        if (userId != null) {

            // Tenderer connection
            Tenderer tenderer = this.sb.searchTendererById(userId);
            HttpSession session = SessionBean.getSession();
            session.setAttribute("userID", userId);
            session.setAttribute("userCategory", Tenderer.userCategory);
            session.setAttribute("userName", tenderer.getLogin());
            session.setAttribute("userAvatar", tenderer.getAvatar());
            
            // Refresh page
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            try {
                context.redirect(this.getCurrentUrl());
            } catch (IOException ex) {
                Logger.getLogger(AuthentificationBean.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

            userId = this.ab.isContractorValid(userName, userPassword);
            if (userId != null) {

                // Contractor connection
                Contractor contractor = this.sb.searchContractorById(userId);
                HttpSession session = SessionBean.getSession();
                session.setAttribute("userID", userId);
                session.setAttribute("userCategory", Contractor.userCategory);
                session.setAttribute("userName", contractor.getSocialReason());
                session.setAttribute("userAvatar", contractor.getLogo());

                // Refresh page
                ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                try {
                    context.redirect(this.getCurrentUrl());
                } catch (IOException ex) {
                    Logger.getLogger(AuthentificationBean.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            } else {

                // Bad Tenderer/Contractor id
                FacesContext.getCurrentInstance().addMessage("growlAuthentification", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login et/ou mot de passe incorrect(s) !", ""));

            }

        }
        
        return "#";

    }

    /**
     * Test Moderator authentification
     *
     * @return link to same page
     */
    public String validateModeratorAuthentification() {

        Long userId = this.ab.isModeratorValid(userName, userPassword);
        if (userId != null) {

            HttpSession session = SessionBean.getSession();
            session.setAttribute("userID", userId);
            session.setAttribute("userCategory", Moderator.userCategory);
            
            return "/views/adminManager.xhtml";

        } else {

            // Bad Moderator id
            FacesContext.getCurrentInstance().addMessage("growlAuthentification", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login et/ou mot de passe incorrect(s) !", ""));

            return "/";
            
        }

    }

    /**
     * Test if a Tenderer is connected
     *
     * @return True is a Tenderer is connected, or False
     */
    public boolean isTendererConnected() {

        String userCategory = SessionBean.getUserCategory();
        if (userCategory != null) {
            if (userCategory.equals(Tenderer.userCategory)) {

                return true;

            }
        }

        return false;

    }

    /**
     * Test if a Contractor is connected
     *
     * @return True is a Tenderer is connected, or False
     */
    public boolean isContractorConnected() {

        String userCategory = SessionBean.getUserCategory();
        if (userCategory != null) {
            if (userCategory.equals(Contractor.userCategory)) {

                return true;

            }
        }

        return false;

    }

    /**
     * Test if a Moderator is connected
     *
     * @return True is a Moderator is connected, or False
     */
    public boolean isModeratorConnected() {

        String userCategory = SessionBean.getUserCategory();
        if (userCategory != null) {
            if (userCategory.equals(Moderator.userCategory)) {

                return true;

            }
        }

        return false;

    }

    /**
     * Logout the user
     *
     * @return the redirection page
     */
    public String logout() {

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        // Main page
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try {
            context.redirect(context.getRequestContextPath().replaceFirst("/Oops-web*", "/Oops-web") + "/");
        } catch (IOException ex) {
            Logger.getLogger(AuthentificationBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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

    public boolean isAuthentificationVisible() {
        return authentificationVisible;
    }

    public void setAuthentificationVisible(boolean authentificationVisible) {
        this.authentificationVisible = authentificationVisible;
    }

}

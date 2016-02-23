/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import com.gdf.ejb.RegistrationBean;
import com.gdf.persistence.Contractor;
import com.gdf.persistence.Tenderer;
import com.gdf.session.SessionBean;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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

    @NotNull(message = "Veuillez saisir un login")
    @Size(min = 4, max = 20, message = "Votre login doit contenir entre 5 et 20 caractères.")
    private String login;
    
    @NotNull(message = "Veuillez saisir un mot de passe")
    @Size(min = 6, message = "Le mot de passe doit contenir au moins 6 caractères")
    private String password;
    
    @NotNull(message = "Veuillez saisir un email")
    private String email;
    
    @NotNull(message = "Veuillez saisir un prénom")
    @Size(min = 3, message = "Le prénom doit contenir au moins 3 caractères")
    private String firstname;
    
    @NotNull(message = "Veuillez saisir un nom")
    @Size(min = 3, message = "Le nom doit contenir au moins 3 caractères")
    private String lastname;
    
    @NotNull(message = "Veuillez saisir une confirmation de mot de passe")
    private String passwordConfirm;  
    
    // there is already a validator
    private String avatar;
    
    @Pattern(regexp = "[0-9]{10}", message = "Le numéro de téléphone doit contenir 10 chiffres")
    private String phone;

    private int step = 1;
    
    /**
     * Creates a new instance of TendererRegisterBean
     */
    public TendererRegistrationBean() {
    }

    /**
     * Register a new Tenderer
     */
    public void submit() {

        Tenderer t = new Tenderer();
        t.setLogin(this.login);
        t.setPassword(this.password);
        t.setEmail(this.email);
        t.setFirstname(this.firstname);
        t.setLastname(this.lastname);
        t.setPhone(this.phone);
        t.setAvatar(this.avatar);

        Long id = this.rb.register(t);
        // Connect the Tenderer
        HttpSession session = SessionBean.getSession();
        session.setAttribute("userID", id);
        session.setAttribute("userCategory", Tenderer.userCategory);
        session.setAttribute("userName", t.getLogin());
        session.setAttribute("userAvatar", t.getAvatar());
        
        this.step = 2;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import com.gdf.ejb.RegistrationBean;
import com.gdf.persistence.Tenderer;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.validation.constraints.Size;

/**
 * Manage Tenderer registration
 * @author bibo
 */
@Named(value="tendererRegisterBean")
@RequestScoped
public class TendererRegisterBean {

    @EJB
    RegistrationBean rb;
    
    @Size(min = 4, max = 20, message = "Votre login doit contenir entre 5 et 20 caractères.")
    private String login;
    @Size(min = 8, max = 20, message = "Le mot de passe doit contenir entre 8 et 20 caractères.")
    private String password; 
    private String passwordConfirm;
    private String email;
    private String firstname;
    private String lastname;
    private String avatar;
    private String phone;
        
    /**
     * Creates a new instance of TendererRegisterBean
     */
    public TendererRegisterBean() {
    }

    /**
     * Register a new Tenderer
     * @return 
     */
    public String submit(){
       
        Tenderer t = new Tenderer();
        t.setLogin(this.login);
        t.setPassword(this.password);
        t.setEmail(this.email);
        t.setFirstname(this.firstname);
        t.setLastname(this.lastname);
        t.setPhone(this.phone);
        t.setAvatar(this.avatar);

        this.rb.register(t);

        return "register";
        
    }
    
    // GETTER / SETTER

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

}

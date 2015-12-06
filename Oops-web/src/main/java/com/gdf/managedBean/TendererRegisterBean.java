/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import com.gdf.ejb.TendererManagerBean;
import com.gdf.persistence.Tenderer;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author bibo
 */
@Named(value="tendererRegisterBean")
@RequestScoped
public class TendererRegisterBean {

    /**
     * Creates a new instance of TendererRegisterBean
     */
    @EJB
    TendererManagerBean tb;
    
    
    private String login;
    private String password; 
    private String passwordConfirm;
    private String email;
    private String firstname;
    private String lastname;
    private String avatar;
    private String phone;
    
    
    public TendererRegisterBean() {
    }

    
    
    
    public String submit(){
       
            // Check login taken        
            List<Tenderer> res = tb.findByLogin(login);
            if (res.size() > 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("", "Ce pseudonyme est déjà utilisé par un autre soumissionnaire"));
                return "register";
            }

            // Check mail taken    
            res = tb.findByEmail(email);
            if (res.size() > 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("", "Ce mail est déjà utilisé par un autre soumissionnaire"));
                return "register";
            }

            // else : add Tenderer to db
            Tenderer t = new Tenderer();
            t.setLogin(login);
            t.setPassword(password);
            t.setEmail(email);
            t.setFirstname(firstname);
            t.setLastname(lastname);
            t.setPhone(phone);
            t.setAvatar(avatar);
            
            tb.register(t);
            return "register";
        
        
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
    

}

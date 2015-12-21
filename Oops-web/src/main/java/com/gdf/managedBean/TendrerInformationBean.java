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
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author hamou
 */
@Named(value = "tendrerInformationBean")
@SessionScoped
public class TendrerInformationBean {

    @EJB
    TendererManagerBean tb;

    private String login;
    private String password;
    private String email;
    private String firstname;
    private String lastname;
    private String avatar;
    private String phone;

    public TendrerInformationBean() {

    }

    public void initTendrerIformation() {
        //Dans le cas où vous sauvegardez le soumissionnaire
        Tenderer tenderer = (Tenderer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("tenderer");

//Dans le cas où vous auvegardez le login 
//        String loginTenderer = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Logintenderer");
//        Tenderer tenderer = tb.findByLogin(loginTenderer).get(0);
        login = tenderer.getLogin();
        login = tenderer.getPassword();
        login = tenderer.getEmail();
        login = tenderer.getFirstname();
        login = tenderer.getLastname();
        login = tenderer.getAvatar();
        login = tenderer.getPhone();

    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getPhone() {
        return phone;
    }

}

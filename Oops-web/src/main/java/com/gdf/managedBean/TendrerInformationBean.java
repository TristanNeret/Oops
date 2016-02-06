/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import com.gdf.ejb.TendererManagerBean;
import com.gdf.persistence.Tenderer;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

/**
 *
 * @author hamou
 */
@Named(value="tendrerInformationBean")
@ViewScoped
public class TendrerInformationBean implements Serializable {

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
        password = tenderer.getPassword();
        email = tenderer.getEmail();
        firstname = tenderer.getFirstname();
        lastname = tenderer.getLastname();
        avatar = tenderer.getAvatar();
        phone = tenderer.getPhone();

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
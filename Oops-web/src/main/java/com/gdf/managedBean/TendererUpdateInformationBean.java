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
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author hamou
 */
@Named(value = "tendererUpdateInformation")
@SessionScoped
public class TendererUpdateInformationBean implements Serializable {

    @EJB
    TendererManagerBean tb;

    private String login;
    private String email;
    private String firstname;
    private String lastname;
    private String avatar;
    private String phone;
    private String password;

    public void initTendererInformation() {
        Tenderer tenderer = (Tenderer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("tenderer");
//        String loginTenderer = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Logintenderer");
//        Tenderer tenderer = tb.findByLogin(loginTenderer).get(0);
        login = tenderer.getLogin();
        email = tenderer.getEmail();
        firstname = tenderer.getFirstname();
        lastname = tenderer.getLastname();
        avatar = tenderer.getAvatar();
        phone = tenderer.getPhone();

    }

    public TendererUpdateInformationBean() {

    }

    public void applyUpdate() {
        
        Tenderer tnd = (Tenderer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("tenderer");
//        String loginTenderer = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Logintenderer");
//        Tenderer tnd = tb.findByLogin(loginTenderer).get(0);

   
        tnd.setEmail(email);
        tnd.setLogin(login);
        tnd.setFirstname(firstname);
        tnd.setLastname(lastname);
        tnd.setAvatar(avatar);
        tnd.setPhone(phone);
        tnd.setPassword(password);

        tb.update(tnd);

    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getAvatar() {
        return avatar;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }
}

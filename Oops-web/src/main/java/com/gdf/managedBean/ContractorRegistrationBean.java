/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import com.gdf.ejb.RegistrationBean;
import com.gdf.persistence.Contractor;
import com.gdf.persistence.LegalInformation;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.validation.constraints.Size;

/**
 * Manage Contrator registration
 * @author borui
 */
@Named(value = "contractorRegistrationBean")
@RequestScoped
public class ContractorRegistrationBean {

    @Size(min = 4, max = 20, message = "Votre login doit contenir entre 5 et 20 caractères.")
    private String login;
    @Size(min = 8, max = 20, message = "Le mot de passe doit contenir entre 8 et 20 caractères.")
    private String password;
    private String passwordConfirm;
    @Size(min = 1, max = 30)
    private String lastname;
    @Size(min = 1, max = 30)
    private String firstname;
    private String email;
    @Size(min = 10, max = 15, message = "Le format du numéro de téléphone est incorect.")
    private String phone;
    private String socialReason;
    private String legalForm;
    private int turnover;
    private int nbEmployees;
    @Size(min = 9, max = 9, message = "Le numéro SIREN doit contenir 9 caractères.")
    private String siren;
    @Size(min = 14, max = 14, message = "Le numéro SIRET doit contenir 14.")
    private String siret;
    
    @EJB
    RegistrationBean rb;
    
    /**
     * Creates a new instance of ContractorRegistrationBean
     */
    public ContractorRegistrationBean() {
        
    }
    
    /**
     * Register a new Contractor into DataBase
     */
    public void submit(){
        
        LegalInformation li = new LegalInformation();
        li.setSiren(this.siren);
        li.setSiret(this.siret);
        
        Contractor c = new Contractor();
        c.setLogin(this.login);
        c.setPassword(this.password);
        c.setRepresentatorFirstname(this.firstname);
        c.setRepresentatorLastname(this.lastname);
        c.setEmail(this.email);
        c.setPhone(this.phone);
        c.setSocialReason(this.socialReason);
        c.setLegalForm(this.legalForm);
        c.setTurnover(this.turnover);
        c.setNbEmployees(this.nbEmployees);
        c.setLegalInformation(li);
        
        this.rb.register(c);
        
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSocialReason() {
        return socialReason;
    }

    public void setSocialReason(String socialReason) {
        this.socialReason = socialReason;
    }

    public String getLegalForm() {
        return legalForm;
    }

    public void setLegalForm(String legalForm) {
        this.legalForm = legalForm;
    }

    public int getTurnover() {
        return turnover;
    }

    public void setTurnover(int turnover) {
        this.turnover = turnover;
    }

    public int getNbEmployees() {
        return nbEmployees;
    }

    public void setNbEmployees(int nbEmployees) {
        this.nbEmployees = nbEmployees;
    }

    public String getSiren() {
        return siren;
    }

    public void setSiren(String siren) {
        this.siren = siren;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

}

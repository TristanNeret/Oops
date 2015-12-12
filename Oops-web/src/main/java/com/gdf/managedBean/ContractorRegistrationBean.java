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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author borui
 */
@Named(value = "contractorRegistrationBean")
@RequestScoped
public class ContractorRegistrationBean {

    
    
    @Size(min = 5, max = 20, message = "Login size shold between 5 and 20")
    private String login;
    private String password;
    @Size(min = 1, max = 30)
    private String lastname;
    @Size(min = 1, max = 30)
    private String firstname;
    @Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    private String email;
    @Size(min = 10, max = 15, message = "Phone number isn't in good format")
    private String phone;
    private String socialReason;
    private String legalForm;
    private int turnover;
    private int nbEmployees;
    @Size(min = 9, max = 9, message = "Siren length shold be 9")
    private String siren;
    @Size(min = 14, max = 14, message = "Siret length should be 14")
    private String siret;
    
    @EJB
    RegistrationBean rb;
    
    /**
     * Creates a new instance of ContractorRegistrationBean
     */
    public ContractorRegistrationBean() {
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
    
    public void submit(){
        
        //validator not implemented yet
        
        LegalInformation li = new LegalInformation();
        li.setSiren(siren);
        li.setSiret(siret);
        
        Contractor c = new Contractor();
        c.setLogin(login);
        c.setPassword(password);
        c.setRepresentatorFirstname(firstname);
        c.setRepresentatorLastname(lastname);
        c.setEmail(email);
        c.setPhone(phone);
        c.setSocialReason(socialReason);
        c.setLegalForm(legalForm);
        c.setTurnover(turnover);
        c.setNbEmployees(nbEmployees);
        c.setLegalInformation(li);
        
        rb.register(c);
        
    }
    
   
    
}

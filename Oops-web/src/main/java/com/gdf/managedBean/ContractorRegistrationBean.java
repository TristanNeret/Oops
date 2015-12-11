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
import org.jasypt.util.password.ConfigurablePasswordEncryptor;

/**
 *
 * @author borui
 */
@Named(value = "contractorRegistrationBean")
@RequestScoped
public class ContractorRegistrationBean {

    private static final String ENCRYPTION_ALGORITHM = "SHA-256";
    
    private String login;
    private String password;
    private String lastname;
    private String firstname;
    private String email;
    private String phone;
    private String socialReason;
    private String legalForm;
    private int turnover;
    private int nbEmployees;
    private String siren;
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
        c.setPassword(encryptPassword());
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
    
    private String encryptPassword(){
        ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
        passwordEncryptor.setAlgorithm( ENCRYPTION_ALGORITHM );
        passwordEncryptor.setPlainDigest( true );
        return passwordEncryptor.encryptPassword(this.password);
    }
    
}

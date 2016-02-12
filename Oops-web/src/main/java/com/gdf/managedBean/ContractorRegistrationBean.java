/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import com.gdf.ejb.RegistrationBean;
import com.gdf.ejb.SearchBean;
import com.gdf.persistence.Contractor;
import com.gdf.persistence.LegalInformation;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.validation.constraints.Size;

/**
 * Manage Contrator registration
 * @author borui
 */
@Named(value = "contractorRegistrationBean")
@RequestScoped
public class ContractorRegistrationBean {

    private String login;
    private String password;
    private String passwordConfirm;
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
    private String rcs;
    private String insurrance;
    private String logo;
    
    @EJB
    RegistrationBean rb;
    @EJB
    SearchBean sb;
    
    private List<SelectItem> legalForms;

    private final SelectItem[] nonTeamCompanies = new SelectItem[]{
        new SelectItem("Auto-entrepreneur", "Auto-entrepreneur"),
        new SelectItem("Entrepreneur individuel", "Entrepreneur individuel"),
        new SelectItem("EIRL", "EIRL"),
        new SelectItem("EURL", "EURL"),
        new SelectItem("SASU", "SASU")
    };

    private final SelectItem[] teamCompanies = new SelectItem[]{
        new SelectItem("SNC", "SNC"),
        new SelectItem("SARL", "SARL"),
        new SelectItem("SA", "SA"),
        new SelectItem("SAS", "SAS"),
        new SelectItem("SCA", "SCA")
    };
    
    @PostConstruct
    public void init() {

        SelectItemGroup g1 = new SelectItemGroup("Entreprise individuelle");
        g1.setSelectItems(nonTeamCompanies);

        SelectItemGroup g2 = new SelectItemGroup("Entreprise non-individuelle");
        g2.setSelectItems(teamCompanies);

        legalForms = new ArrayList<>();
        legalForms.add(g1);
        legalForms.add(g2);
    }
    
    /**
     * Creates a new instance of ContractorRegistrationBean
     */
    public ContractorRegistrationBean() {
        
    }
    
    public void step1(){
        Contractor c = new Contractor();
        c.setLogin(this.login);
        c.setPassword(this.password);
        c.setRepresentatorFirstname(this.firstname);
        c.setRepresentatorLastname(this.lastname);
        c.setEmail(this.email);
        c.setPhone(this.phone);
        
        Long id = this.rb.register(c);     
        // Connect the contractor
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userID", id);       
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userCategory", Contractor.userCategory);
    }
    
    public void step2(){
        // Get the connected contractor
        Long userID = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
        Contractor c = sb.searchContractorById(userID);
        
        c.setLegalForm(legalForm);
        c.setLogo(logo);
        c.setSocialReason(socialReason);
        c.setTurnover(turnover);
        c.setNbEmployees(nbEmployees);
        c.setLegalInformation(new LegalInformation(siret, siren, rcs, insurrance));
        
        this.rb.update(c); 
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

    public boolean isATeamCompanySelected() {
        for(SelectItem si : teamCompanies){
            if(si.getLabel().equals(legalForm)){
                return true;
            }
        }
        return false;
    }

    public String getRcs() {
        return rcs;
    }

    public void setRcs(String rcs) {
        this.rcs = rcs;
    }

    public String getInsurrance() {
        return insurrance;
    }

    public void setInsurrance(String insurrance) {
        this.insurrance = insurrance;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<SelectItem> getLegalForms() {
        return legalForms;
    }

    public void setLegalForms(List<SelectItem> legalForms) {
        this.legalForms = legalForms;
    }
}

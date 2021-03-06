/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import com.gdf.ejb.RegistrationBean;
import com.gdf.persistence.Address;
import com.gdf.persistence.Contractor;
import com.gdf.persistence.LegalInformation;
import com.gdf.session.SessionBean;
import com.gdf.singleton.PopulateDB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Manage Contrator registration
 *
 * @author borui
 */
@Named(value = "contractorRegistrationBean")
@ViewScoped
public class ContractorRegistrationBean implements Serializable {

    @EJB
    private RegistrationBean rb;
    @EJB
    private PopulateDB pdb;

    private Contractor contractor;

    @NotNull(message = "Veuillez saisir un mot de passe !")
    @Size(min = 5, message = "Le mot de passe doit contenir au moins 6 caractères !")
    private String password;

    @NotNull(message = "Veuillez saisir une confirmation de mot de passe !")
    private String passwordConfirm;

    @NotNull(message = "Veuillez saisir un code postal !")
    @Size(min = 5, max = 5, message = "Le code postal doit contenir 5 chiffres !")
    private String zipCode;
    private boolean code;

    private List<SelectItem> legalForms;

    /**
     * All countries available for the registration
     */
    private List<SelectItem> allCountry;
    private List<SelectItem> allTown;

    /**
     * Creates a new instance of ContractorRegistrationBean
     */
    public ContractorRegistrationBean() {

    }

    @PostConstruct
    public void init() {
        contractor = new Contractor();
        contractor.setAddress(new Address());
        contractor.setLegalInformation(new LegalInformation());
        contractor.getAddress().setCountry("France");
        code = false;
        legalForms = pdb.getLegalForms();
    }

    
    /**
     * Registration of a new contractor 
     */
    public void register() {
        contractor.setPassword(password);
        Long id = this.rb.register(this.contractor);
        // Connect the contractor
        HttpSession session = SessionBean.getSession();
        session.setAttribute("userID", id);
        session.setAttribute("userCategory", Contractor.userCategory);
        session.setAttribute("userName", this.contractor.getSocialReason());
        session.setAttribute("userAvatar", this.contractor.getLogo());
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public boolean isATeamCompanySelected() {
        return pdb.isATeamCompany(contractor.getLegalForm());
    }

    public boolean isCode() {
        return code;
    }

    public void setCode(boolean code) {
        this.code = code;
    }

    public List<SelectItem> getLegalForms() {
        return legalForms;
    }

    public void setLegalForms(List<SelectItem> legalForms) {
        this.legalForms = legalForms;
    }

    public void setZipCode(String zipCode) {
        this.code = true;
        this.zipCode = zipCode;
        this.contractor.getAddress().setZipCode(Integer.parseInt(zipCode));
        this.contractor.getAddress().setRegion(pdb.getRegion(zipCode));
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public void setCountry(String country) {
        this.contractor.getAddress().setCountry(country);
    }

    public String getCountry() {
        return this.contractor.getAddress().getCountry();
    }

    /**
     * Return the list of all countries of the world  
     * @return List of countries  
     */
    public List<SelectItem> getAllCountry() {
        List<String> lcountries = pdb.getAllCountries();

        List<SelectItem> li = new ArrayList<>();

        for (String country : lcountries) {
            if (country != null) {
                li.add(new SelectItem(country));
            }
        }

        return li;
    }

     /**
     * Return a town list who correspond to a contractor's zip code 
     * @return list of all towns
     */
    public List<SelectItem> getAllTown() {
            
        List<String> ltowns = pdb.getAllTown(Integer.toString(this.contractor.getAddress().getZipCode()));
        List<SelectItem> li = new ArrayList<>();

        if(ltowns != null){
            for (String town : ltowns) {
                li.add(new SelectItem(town));
            }
        }

        return li;
    }

    public void setAllCountry(List<SelectItem> allCountry) {
        this.allCountry = allCountry;
    }

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

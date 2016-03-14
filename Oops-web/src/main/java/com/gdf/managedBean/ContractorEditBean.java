/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import com.gdf.ejb.ContractorManagerBean;
import com.gdf.ejb.SearchBean;
import com.gdf.persistence.Contractor;
import com.gdf.session.SessionBean;
import com.gdf.singleton.PopulateDB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * ContractorEditBean
 *
 * @author bibo
 */
@Named(value = "contractorEditBean")
@ViewScoped
public class ContractorEditBean implements Serializable {

    private Contractor contractor;

    private List<SelectItem> legalForms;

    @EJB
    private PopulateDB pdb;
    private List<SelectItem> allCountry;
    private List<SelectItem> allTown;

    @EJB
    SearchBean sb;

    @EJB
    ContractorManagerBean cm;

    @NotNull(message = "Veuillez saisir un mot de passe !")
    @Size(min = 5, message = "Le mot de passe doit contenir au moins 6 caractères !")
    private String password;

    @NotNull(message = "Veuillez saisir une confirmation de mot de passe !")
    private String passwordConfirm;

    @NotNull(message = "Veuillez saisir un code postal !")
    @Size(min = 5, max = 5, message = "Le code postal doit contenir 5 chiffres !")
    private String zipCode;
    private boolean code;

    @PostConstruct
    public void init() {

        this.contractor = sb.searchContractorById(SessionBean.getUserId());
        this.zipCode = ""+contractor.getAddress().getZipCode();
        this.code = false;
        this.legalForms = pdb.getLegalForms();

    }

    /**
     * Creates a new instance of ContractorEditBean
     */
    public ContractorEditBean() {

    }

    /*
     * Contractor setters and getters
     */
    public Contractor getContractor() {
        return this.contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    public void undo() {
        this.contractor = sb.searchContractorById(SessionBean.getUserId());
    }

    public void update() {

        if ((!isATeamCompanySelected()) && (contractor.getNbEmployees() > 0)) {
            contractor.setNbEmployees(1);
        }

        cm.update(contractor);
    }

    public void updatePassword() {
        this.contractor.setPassword(password);
        this.cm.update(contractor);
        // reset
        this.password = "";
        this.passwordConfirm = "";
    }

    public boolean isLogo() {
        return this.contractor.getLogo() != null && !this.contractor.getLogo().equals("");
    }

    public List<SelectItem> getLegalForms() {
        return this.legalForms;
    }

    public boolean isATeamCompanySelected() {
        return this.pdb.isATeamCompany(contractor.getLegalForm());
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

    public void setAllCountry(List<SelectItem> allCountry) {
        this.allCountry = allCountry;
    }

    public List<SelectItem> getAllTown() {
        List<String> ltowns = pdb.getAllTown(Integer.toString(this.contractor.getAddress().getZipCode()));

        List<SelectItem> li = new ArrayList<>();

        if (ltowns != null) {

            for (String town : ltowns) {
                li.add(new SelectItem(town));
            }

        }

        return li;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return this.passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public boolean isCode() {
        return code;
    }
}

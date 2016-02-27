/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import com.gdf.ejb.RegistrationBean;
import com.gdf.ejb.SearchBean;
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
import javax.faces.model.SelectItemGroup;
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
    private SearchBean sb;
    @EJB
    private PopulateDB pdb;

    private Contractor contractor;

    @NotNull(message = "Veuillez saisir un mot de passe")
    @Size(min = 6, message = "Le mot de passe doit contenir au moins 6 caractères")
    private String password;
    @NotNull(message = "Veuillez saisir une confirmation de mot de passe")
    private String passwordConfirm;

    private boolean code;

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

    /**
     * All countries available for the registration
     */
    private List<SelectItem> allCountry;
    private List<SelectItem> allTown;

    @PostConstruct
    public void init() {

        contractor = new Contractor();
        contractor.setAddress(new Address());
        contractor.setLegalInformation(new LegalInformation());

        code = false;

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

    public void register() {
        this.contractor.setPassword(this.password);
        Long id = this.rb.register(this.contractor);
        // Connect the contractor
        HttpSession session = SessionBean.getSession();
        session.setAttribute("userID", id);
        session.setAttribute("userCategory", Contractor.userCategory);
        session.setAttribute("userName", this.contractor.getSocialReason());
        session.setAttribute("userAvatar", this.contractor.getLogo());
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

    public boolean isATeamCompanySelected() {
        for (SelectItem si : teamCompanies) {
            if (si.getLabel().equals(contractor.getLegalForm())) {
                return true;
            }
        }
        return false;
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

    public void setZipCode(int zipCode) {
        this.code = true;
        this.contractor.getAddress().setZipCode(zipCode);
        this.contractor.getAddress().setRegion(pdb.getRegion(Integer.toString(zipCode)));
    }

    public int getZipCode() {
        return this.contractor.getAddress().getZipCode();
    }

    public void setCountry(String country) {
        this.contractor.getAddress().setCountry(country);
        this.contractor.getAddress().setZipCode(0);
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

    public List<SelectItem> getAllTown() {
        List<String> ltowns = pdb.getAllTown(Integer.toString(this.contractor.getAddress().getZipCode()));

        List<SelectItem> li = new ArrayList<>();

        for (String town : ltowns) {
            li.add(new SelectItem(town));
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
}

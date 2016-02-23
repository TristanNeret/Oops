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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

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

    private boolean code;
    @EJB
    private PopulateDB pdb;
    private List<SelectItem> allCountry;
    private List<SelectItem> allTown;

    @EJB
    SearchBean sb;

    @EJB
    ContractorManagerBean cm;

    @PostConstruct
    public void init() {
        
        this.contractor = sb.searchContractorById(SessionBean.getUserId());

        SelectItemGroup g1 = new SelectItemGroup("Entreprise individuelle");
        g1.setSelectItems(nonTeamCompanies);

        SelectItemGroup g2 = new SelectItemGroup("Entreprise non-individuelle");
        g2.setSelectItems(teamCompanies);

        legalForms = new ArrayList<>();
        legalForms.add(g1);
        legalForms.add(g2);

        code = false;
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
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    public void undo() {
        contractor = cm.undo(contractor);
    }

    public void update() {

        if ((!isATeamCompanySelected()) && (contractor.getNbEmployees() > 0)) {
            contractor.setNbEmployees(1);
        }

        cm.update(contractor);
    }

    public boolean isLogo() {
        return this.contractor.getLogo() != null && !this.contractor.getLogo().equals("");
    }

    public List<SelectItem> getLegalForms() {
        return legalForms;
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

    public void setAllCountry(List<SelectItem> allCountry) {
        this.allCountry = allCountry;
    }

    public List<SelectItem> getAllTown() {
        List<String> ltowns = pdb.getAllTown(Integer.toString(this.contractor.getAddress().getZipCode()));

        List<SelectItem> li = new ArrayList<>();

        for (String town : ltowns) {
            li.add(new SelectItem(town));
        }

        return li;
    }
}

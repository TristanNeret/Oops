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

    @PostConstruct
    public void init() {
        
        this.contractor = sb.searchContractorById(SessionBean.getUserId());

        legalForms = pdb.getLegalForms();

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
        System.out.println("entre");
        this.contractor = sb.searchContractorById(SessionBean.getUserId());
        //contractor = cm.undo(contractor);
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
        return pdb.isATeamCompany(contractor.getLegalForm());
    }

    public void setZipCode(int zipCode) {
        this.contractor.getAddress().setZipCode(zipCode);
        this.contractor.getAddress().setRegion(pdb.getRegion(Integer.toString(zipCode)));
    }

    public int getZipCode() {
        return this.contractor.getAddress().getZipCode();
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

        if(ltowns != null){
        
            for (String town : ltowns) {
                li.add(new SelectItem(town));
            }
        
        }    

        return li;
    }
}

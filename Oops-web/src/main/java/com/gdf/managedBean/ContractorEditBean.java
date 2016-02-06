/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;


import com.gdf.ejb.AddressBean;
import com.gdf.ejb.ContractorManagerBean;
import com.gdf.ejb.LegalInformationBean;
import com.gdf.ejb.RegistrationBean;
import com.gdf.ejb.SearchBean;
import com.gdf.persistence.Contractor;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * ContractorEditBean
 * @author bibo
 */
@Named(value = "contractorEditBean")
@ViewScoped
public class ContractorEditBean implements Serializable{
    

    private Contractor contractor;

    @EJB
    SearchBean sb;
    
    @EJB
    ContractorManagerBean cm;
       
    
    @PostConstruct
    public void init() { 
       this.contractor = sb.searchContractorById(10);
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

    
    public void undo(){
       contractor =  cm.undo(contractor);
    }
    
    public void update(){
        cm.update(contractor);
    }

}

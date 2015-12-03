/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import com.gdf.ejb.CustomerManagerBean;
import com.gdf.persistence.Contractor;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author nicolas
 */
@Named(value = "contractorInformationsBean")
@RequestScoped
public class ContractorInformationsBean {

    /**
     * Creates a new instance of ContractorInformationsBean
     */
    
    
    @ManagedProperty(value="#{param.id}") 
    private long id;
    
    @EJB
    CustomerManagerBean cm;
    
    private Contractor contractor;
    
    public ContractorInformationsBean() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Contractor getContractor() {
        return cm.searchContractorById(id);
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }
    
    
    
    
    
    
}

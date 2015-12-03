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

/**
 *
 * @author nicolas
 */
@Named(value = "contractorInformationsBean")
@RequestScoped
public class ContractorInformationsBean {

    private Long id;
    
    @EJB
    private CustomerManagerBean cm;
    
    private Contractor contractor;
    
    public ContractorInformationsBean() {
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Contractor getContractor() {
        if(contractor == null)
            contractor = cm.searchContractorById(id);
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }    
}

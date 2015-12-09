/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import com.gdf.ejb.CustomerManagerBean;
import com.gdf.persistence.Contractor;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author nicolas
 */
@ManagedBean
@ViewScoped
public class ContractorInformationsBean {

    @EJB
    private CustomerManagerBean cm;
    
    private long id;
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
        if(contractor == null)
            contractor = cm.searchContractorById(id);
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }    
}

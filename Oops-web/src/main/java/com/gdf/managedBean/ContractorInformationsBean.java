/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import com.gdf.ejb.SearchBean;
import com.gdf.persistence.Contractor;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 * ManagedBean allowing to seach a Contractor
 * @author nicolas
 */
@Named(value = "contractorInformationsBean")
@RequestScoped
public class ContractorInformationsBean {

    /**
     * Id of the searched Contractor 
     */
    private long id;
    
    /**
     * Injected EJB giving the search method
     */
    @EJB
    private SearchBean searchBean;
    
    /**
     * Set the id of the Contrator to search
     * @param id the id of the Contractor
     */
    public void setId(long id) {
        this.id = id;
    }
    
    /**
     * Get the id of the Contrator to search
     * @return the id of the Contractor
     */
    public long getId() {
        return id;
    }

    /**
     * Get the searched Contractor
     * @return the Contractor identified by the id
     */
    public Contractor getContractor() {
        return searchBean.searchContractorById(id);
    }
}

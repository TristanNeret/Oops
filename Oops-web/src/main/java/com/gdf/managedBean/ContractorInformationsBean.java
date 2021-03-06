/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import com.gdf.ejb.SearchBean;
import com.gdf.persistence.Contractor;
import com.gdf.persistence.Review;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * ManagedBean providing contractor informations
 * @author nicolas
 */
@Named(value = "contractorInformationsBean")
@ViewScoped
public class ContractorInformationsBean implements Serializable {

    /**
     * Id of the searched Contractor 
     */
    private long id;
    /**
     * Searched Contractor
     */
    private Contractor contractor;
    /**
     * Contractor reviews
     */
    private List<Review> reviews;
    
    /**
     * Injected EJB giving the search methods
     */
    @EJB
    private SearchBean searchBean;
    
    /**
     * Initialize Contractor informations
     */
    public void init() {
        
        this.contractor = this.searchBean.searchContractorById(id);
        this.reviews = this.searchBean.searchAcceptedContratorReviews(this.id);
        
    }
    
    /**
     * Return well formed price
     * @param price selected Contractor price
     * @return N.C. if no price exist, or Contractor price
     */
    public String getWellPrice(Double price) {
        
        if (price.equals(0.0)) {
            
            return "N.C.";
            
        } else {
            
            return price + " €";
            
        }
        
    }
    
    // GETTER/SETTER
    
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

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }
    
    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
    
}

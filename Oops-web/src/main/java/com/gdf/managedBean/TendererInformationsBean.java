/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import com.gdf.ejb.SearchBean;
import com.gdf.persistence.Review;
import com.gdf.persistence.Tenderer;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * ManagedBean providing tenderer informations
 * @author borui
 */
@Named(value = "tendererInformationsBean")
@ViewScoped
public class TendererInformationsBean implements Serializable {

    /**
     * Id of the searched Tenderer 
     */
    private long id;
    /**
     * Searched Tenderer
     */
    private Tenderer tenderer;
    /**
     * Tenderer reviews
     */
    private List<Review> reviews;
    
    /**
     * Injected EJB giving the search methods
     */
    @EJB
    private SearchBean searchBean;
    
    /**
     * Create en instance of TendererInformationsBean
     */
    public TendererInformationsBean() {
        
    }
    
    /**
     * Initialize Tenderer informations
     */
    public void init() {
        this.tenderer = this.searchBean.searchTendererById(this.id);
        this.reviews = this.searchBean.searchAcceptedTendererReviews(this.id);
    }
    
    /**
     * Set the id of the Tenderer to search
     * @param id the id of the Tenderer
     */
    public void setId(long id) {
        this.id = id;
    }
    
    /**
     * Get the id of the Tenderer to search
     * @return the id of the Tenderer
     */
    public long getId() {
        return id;
    }

    public Tenderer getTenderer() {
        return tenderer;
    }

    public void setTenderer(Tenderer tenderer) {
        this.tenderer = tenderer;
    }
    
    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
    
}

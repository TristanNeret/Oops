/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import com.gdf.ejb.SearchBean;
import com.gdf.persistence.Review;
import com.gdf.persistence.ReviewState;
import com.gdf.persistence.Tenderer;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * TendererReviewBean
 * @author Tristan
 */
@Named(value = "tendererReviewBean")
@ViewScoped
public class TendererReviewBean implements Serializable {
 
    private long id;
    private List<Review> reviews;
    
    /**
     * Injected EJB giving the search methods
     */
    @EJB
    private SearchBean searchBean;
    
    /**
     * Create en instance of TendererReviewBean
     */
    public TendererReviewBean() {
        
    }
    
    /**
     * Initialize Tenderer reviews
     */
    @PostConstruct
    public void initBean() {
        
        // Temporary used to connect a Contractor
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userID", "1");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userCategory", Tenderer.userCategory);
        
        // Check if a user is connected
        String userID = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
      
        if (userID != null && !userID.isEmpty()) {

            this.id = new Long(userID);
            this.reviews = this.searchBean.searchTendererById(this.id).getReviews();
        
        }
        
    }
    
    /**
     * Test if the Tenderer has Reviews
     * @return 
     */
    public boolean areReviews() {
        
        return !this.reviews.isEmpty();
        
    }
    
    /**
     * Return an understandable string for the ReviewState
     * @param rs the ReviewState concerned
     * @return an understandable string for the ReviewState
     */
    public String getReviewState(ReviewState rs) {
        
        String rss = "Unknow";
        if (rs.equals(ReviewState.ACCEPTED)) {
            rss = "En ligne";
        } else if (rs.equals(ReviewState.DELIVERED)) {
            rss = "En cours de validation";
        } else if (rs.equals(ReviewState.NOT_ACCEPTED)) {
            rss = "Rejeté";
        } else if (rs.equals(ReviewState.TO_BE_MODIFIED)) {
            rss = "À préciser";
        }
        
        return rss;
        
    }
    
    // GETTER/SETTER
    
    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
    
}
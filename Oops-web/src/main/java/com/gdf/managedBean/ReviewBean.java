/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import com.gdf.ejb.CustomerManagerBean;
import com.gdf.ejb.EvaluationBean;
import com.gdf.persistence.Tenderer;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Tristan
 */
@Named(value = "reviewBean")
@RequestScoped
public class ReviewBean {

    @EJB
    private EvaluationBean eb;
    
    private String reviewAppreciation;
    private String reviewContent;
    private int reviewRating;
    
    public ReviewBean() {
        
        // Temporary used to connect a Tenderer
        FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("userID", "1");
        FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("userCategory", Tenderer.userCategory);
        
    }
    
    /**
     * Submitting the review
     * @param contractorID id of the Contractor concerning by the Review
     */
    public void addReview(Long contractorID) {
        
        // Check if connected a Tenderer is connected
        if (this.isTendererConnected()) {

            // Add the new Review
            String userID = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("userID");
            eb.addReview(new Long(userID), contractorID, reviewAppreciation, reviewContent, reviewRating);
            // Reset fields
            reviewAppreciation = "";
            reviewContent = "";
            reviewRating = 0;
        }
    }
    
    /**
     * Allow to know if a Tenderer is connected
     * @return True if a Tenderer is connected, False otherwise
     */
    public Boolean isTendererConnected() {
        
        // Check if a user is connected
        String userID = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("userID");
        // Check if connected user is a Tenderer
        String userCategory = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("userCategory");
        
        return userID != null && !userID.equals("") && userCategory.equals(Tenderer.userCategory);
        
    }

    public String getReviewAppreciation() {
        return reviewAppreciation;
    }

    public void setReviewAppreciation(String reviewAppreciation) {
        this.reviewAppreciation = reviewAppreciation;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public int getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(int reviewRating) {
        this.reviewRating = reviewRating;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import com.gdf.ejb.EvaluationBean;
import com.gdf.persistence.Tenderer;
import com.gdf.session.SessionBean;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 * ReviewBean
 * @author Tristan
 */
@Named(value = "reviewBean")
@RequestScoped
public class ReviewBean {

    @EJB
    private EvaluationBean eb;
    
    private String reviewAppreciation;
    private String reviewContent;
    private int reviewRating = 3;
    
    /**
     * Submitting the review
     * @param contractorID id of the Contractor concerning by the Review
     */
    public void addReview(Long contractorID) {
        
        // Check if connected a Tenderer is connected
        if (this.isTendererConnected()) {

            // Add the new Review
            eb.addReview(SessionBean.getUserId(), contractorID, reviewAppreciation, reviewContent, reviewRating);

        }
        
    }
    
    /**
     * Allow to know if a Tenderer is connected
     * @return True if a Tenderer is connected, False otherwise
     */
    public Boolean isTendererConnected() {
        
        String userCategory = SessionBean.getUserCategory();
        if (userCategory != null) {
            if (userCategory.equals(Tenderer.userCategory)) {
                
                return true;
                
            }
        }
        
        return false;
        
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

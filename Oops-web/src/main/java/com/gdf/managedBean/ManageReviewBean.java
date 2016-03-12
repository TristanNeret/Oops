/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import com.gdf.ejb.AdministratorBean;
import com.gdf.ejb.SearchBean;
import com.gdf.persistence.ReviewState;
import com.gdf.session.SessionBean;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

/**
 * Manage Tenderer Reviews
 * @author Tristan
 */
@Named(value = "manageReviewBean")
@ViewScoped
public class ManageReviewBean implements Serializable {

    @EJB
    private AdministratorBean ab;
    @EJB
    private SearchBean sb;
    
    private boolean areReviews;
    private List reviewList;
    private Map<Long,String> contentReview;
    private Map<Long,ReviewState> decisionReview;
    
    /**
     * Create an instance of ManageReviewBean
     */
    public ManageReviewBean() {
        
    }
    
    /**
     * Initialize ManageReviewBean by getting waiting reviews
     */
    @PostConstruct
    public void initBean() {
        
        this.updateWaitingReviews();
        
    }
    
    /**
     * Update waiting Reviews to manage
     */
    public void updateWaitingReviews() {
        
        this.reviewList = this.sb.searchWaitingReviews();
        this.contentReview = new HashMap<>();
        this.decisionReview = new HashMap<>();
        this.areReviews = this.reviewList.isEmpty();
        
    }
    
    /**
     * Keep Moderator decision ACCEPTED for the Review
     * @param id id of the Review to manage
     */
    public void validateReview(Long id) {
        
        this.decisionReview.put(id, ReviewState.ACCEPTED);
        
    }
    
    /**
     * Keep Moderator decision TO BE MODIFIED for the Review
     * @param id id of the Review to manage
     */
    public void modifyReview(Long id) {
        
        this.decisionReview.put(id, ReviewState.TO_BE_MODIFIED);
        
    }
    
    /**
     * Keep Moderator decision NOT ACCEPTED for the Review
     * @param id id of the Review to manage
     */
    public void rejectReview(Long id) {
        
        this.decisionReview.put(id, ReviewState.NOT_ACCEPTED);
        
    }
    
    /**
     * Manage the Review as Moderator decided
     * @param id id of the Review to manage
     */
    public void manageReview(Long id) {
        
        if(this.decisionReview.get(id) != null) {
            
            this.ab.manageReview(id, SessionBean.getUserId(), this.decisionReview.get(id), this.contentReview.get(id));

            // Update the waiting Reviews list
            this.updateWaitingReviews();
            FacesContext.getCurrentInstance().addMessage("growlReviewContractor", new FacesMessage("Avis traité avec succès !", ""));

        } else {

            // Moderator must take a decision
            FacesContext.getCurrentInstance().addMessage("growlReviewContractor", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Vous devez prendre une décision !", ""));

        } 

    }
    
    /**
     * Return a short version of the Review description
     * @param review description of the Review
     * @return short version of the Review description
     */
    public String getShortReviewDescription(String review) {
        
        if (review.length() > 58) { 
            return review.substring(0, 55) + "...";
        }
        
        return review;
        
    }
    
    // GETTER/SETTER

    public List getReviewList() {
        return reviewList;
    }

    public void setReviewList(List reviewList) {
        this.reviewList = reviewList;
    }

    public Map<Long, String> getContentReview() {
        return contentReview;
    }

    public void setContentReview(Map<Long, String> contentReview) {
        this.contentReview = contentReview;
    }
    
    public boolean isAreReviews() {
        return areReviews;
    }

    public void setAreReviews(boolean areReviews) {
        this.areReviews = areReviews;
    }
    
}

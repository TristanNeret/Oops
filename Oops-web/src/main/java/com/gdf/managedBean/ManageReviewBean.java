/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import com.gdf.ejb.AdministratorBean;
import com.gdf.ejb.SearchBean;
import com.gdf.persistence.Moderator;
import com.gdf.persistence.ReviewState;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * Manage Tenderer Reviews
 * @author Tristan
 */
@Named(value = "manageReviewBean")
@RequestScoped
public class ManageReviewBean {

    @EJB
    private AdministratorBean ab;
    @EJB
    private SearchBean sb;
    
    private List reviewList;
    private Map<Long,String> contentReview;
    private Map<Long,ReviewState> decisionReview;
    
    /**
     * Create an instance of ManageReviewBean
     */
    public ManageReviewBean() {
        
        // Temporary used to connect a Moderator
        FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("userID", "1");
        FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("userCategory", Moderator.userCategory);
        
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
        
        this.reviewList = this.sb.getWaitingReviews();
        this.contentReview = new HashMap<>();
        this.decisionReview = new HashMap<>();
        
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
        
        // Check if a user is connected
        String userID = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("userID");
        // Check if connected user is a Moderator
        String userCategory = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("userCategory");
        
        if(userID != null && !userID.equals("") && userCategory.equals(Moderator.userCategory)) {
        
            this.ab.manageReview(id, Long.parseLong(userID), this.decisionReview.get(id), this.contentReview.get(id));
            
            // Update the waiting Reviews list
            this.updateWaitingReviews();
        
        }
        
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
    
}

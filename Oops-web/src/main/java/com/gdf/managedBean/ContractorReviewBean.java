/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import com.gdf.ejb.EvaluationBean;
import com.gdf.ejb.SearchBean;
import com.gdf.persistence.Review;
import com.gdf.session.SessionBean;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * ContractorReviewBean
 * @author chris
 */
@Named(value = "contractorReviewBean")
@ViewScoped
public class ContractorReviewBean implements Serializable {
    
    @EJB
    private EvaluationBean eb;
    @EJB
    private SearchBean searchBean;
    
    private List<Review> reviews;
    private Map<Long, String> answersReview;

    /**
     * Creates a new instance of ContractorsReviewBean
     */
    public ContractorReviewBean() {
        
    }
    
    @PostConstruct
    public void initBean() {
        
        // DELETE AND MODIFY WHEN WE WILL HAVE A CURRENT CONTRACTROR CONNECTED
        this.reviews = this.searchBean.searchAcceptedContratorReviews(SessionBean.getUserId());
        this.answersReview = new HashMap<>();
        for(Review r : this.reviews) {
            this.answersReview.put(r.getId(), r.getContractorAnswer());
        }
        
    }
    
    /**
     * Add or update the Review comment
     * @param review the Review on which the comment is added or updated
     */
    public void updateReviewsAnswer(Review review){
        
        this.eb.updateContractorsAnswer(review, this.answersReview.get(review.getId()));
        
        this.initBean();
        
        // Notify the Contractor that the Review comment is added or updated
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Votre réponse a été modifiée avec succès !", ""));
    
    }
    
    /**
     * Remove the Review Contractor Comment
     * @param review the Review on which the comment is deleted
     */
    public void deleteReviewsAnswer(Review review){
        
        this.eb.deleteContractorsAnswer(review);
        
        this.initBean();
        
        // Notify the Contractor that the Review comment is deleted
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Votre réponse a été supprimée avec succès !", ""));
        
    }
    
    /**
     * Test if exist reviews for the register Contractor
     * @return True if reviews exit, or False
     */
    public boolean areReviews() {
        return !this.reviews.isEmpty();
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Map<Long, String> getAnswersReview() {
        return answersReview;
    }

    public void setAnswersReview(Map<Long, String> answersReview) {
        this.answersReview = answersReview;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import com.gdf.ejb.SearchBean;
import com.gdf.ejb.TendererManagerBean;
import com.gdf.persistence.Review;
import com.gdf.persistence.ReviewState;
import com.gdf.persistence.Tenderer;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
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
    
    // Edit Review
    private Review editReview;
    private String editReviewAppreciation;
    private String editReviewContent;
    private int editReviewRating;
    
    /**
     * Injected EJB giving the search methods
     */
    @EJB
    private SearchBean searchBean;
    /**
     * Injected EJB giving methods to manage Tenderer
     */
    @EJB
    private TendererManagerBean tendererManagerBean;
    
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
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userID", new Long("1"));
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userCategory", Tenderer.userCategory);
        
        // Check if a user is connected
        Long userID = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");
      
        if (userID != null) {

            this.id = userID;
            this.reviews = null;
            this.reviews = this.searchBean.searchTendererById(this.id).getReviews();
        
        }
        
    }
    
    /**
     * Save Review changes
     */
    public void editReview() {
        
        this.editReview.setAppreciation(this.editReviewAppreciation);
        this.editReview.setContent(this.editReviewContent);
        this.editReview.setRating(this.editReviewRating);
        
        this.tendererManagerBean.editReview(this.editReview);
        this.reviews = this.searchBean.searchTendererById(this.id).getReviews();
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Avis soumis !", "Un modérateur va se charger de vérifier sa validité."));

    }
    
    /**
     * Remove a Tenderer Review
     * @param review Review to remove
     */
    public void removeReview(Review review) {
        
        this.tendererManagerBean.removeReview(this.id, review);
        int i = 0;
        while (i<this.reviews.size() && !this.reviews.get(i).getId().equals(review.getId())) {
            i++;
        }
        this.reviews.remove(i);// = this.searchBean.searchTendererById(this.id).getReviews();
        
        FacesContext.getCurrentInstance().addMessage("growlReviewTenderer", new FacesMessage("Votre avis a été supprimé avec succès.", ""));
        
    }
    
    /**
     * Test if the Tenderer has Reviews
     * @return True if reviews exist, or False
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

    public Review getEditReview() {
        return editReview;
    }

    public void setEditReview(Review editReview) {
        this.editReview = editReview;
        this.editReviewAppreciation = editReview.getAppreciation();
        this.editReviewContent = editReview.getContent();
        this.editReviewRating = editReview.getRating();
    }

    public String getEditReviewAppreciation() {
        return editReviewAppreciation;
    }

    public void setEditReviewAppreciation(String editReviewAppreciation) {
        this.editReviewAppreciation = editReviewAppreciation;
    }

    public String getEditReviewContent() {
        return editReviewContent;
    }

    public void setEditReviewContent(String editReviewContent) {
        this.editReviewContent = editReviewContent;
    }

    public int getEditReviewRating() {
        return editReviewRating;
    }

    public void setEditReviewRating(int editReviewRating) {
        this.editReviewRating = editReviewRating;
    }
    
}
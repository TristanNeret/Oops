/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Contractor;
import com.gdf.persistence.Notification;
import com.gdf.persistence.NotificationType;
import com.gdf.persistence.Review;
import com.gdf.persistence.ReviewState;
import com.gdf.persistence.Tenderer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Class managing Tenderer
 * @author Tristan
 */
@Stateless
public class TendererManagerBeanImpl implements TendererManagerBean {
    
    /**
     * Injected EntityManager giving access to the database
     */
    @PersistenceContext(unitName = "OopsPU")
    private EntityManager em;  
    
    @Override
    public void editReview(Review review) {
        
        Tenderer tenderer =  em.find(Tenderer.class, review.getTenderer().getId());
        Contractor contractor =  em.find(Contractor.class, review.getContractor().getId());
        
        review.setReviewState(ReviewState.DELIVERED);
        this.em.merge(review);
        
        Tenderer attachedTenderer = em.merge(tenderer);
        Contractor attachedContractor = em.merge(contractor);
        
        // Send notification for new Notification to Moderator
        this.sendNotification(review, attachedTenderer, attachedContractor, NotificationType.TO_MODERATOR);

    }
    
    public void sendNotification(Review review, Tenderer tenderer, Contractor contractor, NotificationType notificationType) {

        // Get attached entities concerned
        Tenderer attachedTenderer = em.merge(tenderer);
        Contractor attachedContractor = em.merge(contractor);
        Review attachedReview = em.merge(review);

        // Create and persist the new Notification
        Notification newNotification = new Notification(attachedReview, attachedTenderer, attachedContractor, notificationType);
        newNotification.setDescription("Un nouvel avis en attente de traitement !");
        em.persist(newNotification);

        // Add the new Notification to others entities
        attachedTenderer.addNotification(newNotification);
        attachedContractor.addNotification(newNotification);
        attachedReview.addNotification(newNotification);

    }

    @Override
    public void removeReview(long tendererId, long reviewId) {
        
        Review reviewToRemove = em.find(Review.class, reviewId);
        if (reviewToRemove != null) {
            
            // Remove the Review from the Tenderer Review list
            Tenderer tendererToUpdate = em.find(Tenderer.class, tendererId);
            if(tendererToUpdate != null) {

                tendererToUpdate.getReviews().remove(reviewToRemove);
                this.em.merge(tendererToUpdate);

            }
            this.em.remove(reviewToRemove);
            
        }
        
    }
    
    @Override
    public void update(Tenderer t) {
        
        this.em.merge(t);
        
    }

    @Override
    public void delete(Tenderer t) {
        
        Tenderer tendererToRemove = em.find(Tenderer.class, t.getId());
        if(tendererToRemove != null) {
        
            this.em.remove(tendererToRemove);
        
        }
        
    }

}

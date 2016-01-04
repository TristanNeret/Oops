/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Category;
import com.gdf.persistence.Contractor;
import com.gdf.persistence.Moderator;
import com.gdf.persistence.ModeratorReview;
import com.gdf.persistence.Notification;
import com.gdf.persistence.NotificationType;
import com.gdf.persistence.Review;
import com.gdf.persistence.ReviewState;
import static com.gdf.persistence.ReviewState.ACCEPTED;
import com.gdf.persistence.Tenderer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Class supplying methods for the administrator
 * @author aziz
 */
@Stateless
public class AdministratorBeanImpl implements AdministratorBean {

    /**
     * Injected EntityManager giving access to the database
     */
    @PersistenceContext(unitName = "OopsPU")
    private EntityManager em;  
    
    @Override
    public void addCategory(Category category) {
        
        em.persist(category);
    
    }

    @Override
    public void manageReview(long reviewId, long moderatorId, ReviewState reviewState, String content) {
      
        // Update the Review
        Review review =  em.find(Review.class, reviewId);
        review.setReviewState(reviewState);
        em.merge(review);
        
        // Get the Moderator from his id and save the ModeratorReview
        Moderator moderator = em.find(Moderator.class, moderatorId);
        ModeratorReview moderatorReview = new ModeratorReview(review, moderator);
        moderatorReview.setContent(content);
        em.persist(moderatorReview);
        
        // Send Notification to prevent the Tenderer
        Tenderer tenderer = em.find(Tenderer.class, review.getTenderer().getId());
        this.sendNotificationToTenderer(moderator, review, tenderer, NotificationType.TO_TENDERER);
        
        // Send Notification to prevent the Contractor if the Review has been accepted
        Contractor contractor = em.find(Contractor.class, review.getContractor().getId());
        if(review.getReviewState() != null && review.getReviewState() == ACCEPTED) {
            this.sendNotificationToContractor(moderator, review, contractor, NotificationType.TO_CONTRACTOR);
        }
        
        
    }
    
    /**
     * Send a new Notification to a Tenderer
     * @param moderator Moderator who send the Review
     * @param review Review concerned by the Notification
     * @param tenderer Tenderer concerned by the Notification
     * @param notificationType type of the Notification
     */
    public void sendNotificationToTenderer(Moderator moderator, Review review, Tenderer tenderer, NotificationType notificationType) {
        
        // Get attached entities concerned
        Moderator attachedModerator = em.merge(moderator);
        Tenderer attachedTenderer = em.merge(tenderer);
        Review attachedReview = em.merge(review);
        
        // Create and persist the new Notification
        Notification newNotification = new Notification(attachedReview, attachedTenderer, attachedModerator, notificationType);
        switch(review.getReviewState()) {
            case ACCEPTED:
                newNotification.setDescription("Votre avis sur " + review.getContractor().getSocialReason() + " a été validé !");
                break;
            case TO_BE_MODIFIED:
                newNotification.setDescription("Vous devez modifier avis sur " + review.getContractor().getSocialReason() + ".");
                break;
            case NOT_ACCEPTED:
                newNotification.setDescription("Votre avis sur " + review.getContractor().getSocialReason() + " a été rejeté !");
                break;
        }
        em.persist(newNotification);
        
        // Add the new Notification to others entities
        attachedTenderer.addNotification(newNotification);
        attachedModerator.addNotification(newNotification);
        attachedReview.addNotification(newNotification);
        
    }
    
    /**
     * Send a new Notification to a Contractor
     * @param moderator Moderator who send the Review
     * @param review Review concerned by the Notification
     * @param contractor Contractor concerned by the Notification
     * @param notificationType type of the Notification
     */
    public void sendNotificationToContractor(Moderator moderator, Review review, Contractor contractor, NotificationType notificationType) {
        
        // Get attached entities concerned
        Moderator attachedModerator = em.merge(moderator);
        Contractor attachedContractor = em.merge(contractor);
        Review attachedReview = em.merge(review);
        
        // Create and persist the new Notification
        Notification newNotification = new Notification(attachedReview, attachedContractor, attachedModerator, notificationType);
        newNotification.setDescription("Votre avez reçu un nouvel avis !");
        em.persist(newNotification);
        
        // Add the new Notification to others entities
        attachedContractor.addNotification(newNotification);
        attachedModerator.addNotification(newNotification);
        attachedReview.addNotification(newNotification);
        
    }
    
}

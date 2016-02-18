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
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Class supplying methods for the administrator
 * @author aziz
 */
@Stateless
public class AdministratorBeanImpl implements AdministratorBean, Serializable {

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
                // update the number of reviews given by the tenderer and accepted by moderator
                attachedTenderer.updateNbReviews(); 
                newNotification.setDescription("Votre avis sur " + review.getContractor().getSocialReason() + " a été validé !");
                newNotification.setLink("/views/contractorInformation.xhtml?id=" + review.getContractor().getId());
                break;
            case TO_BE_MODIFIED:
                newNotification.setDescription("Vous devez modifier avis sur " + review.getContractor().getSocialReason() + ".");
                newNotification.setLink("/views/tendererManagement.xhtml?tabIndex=1");
                break;
            case NOT_ACCEPTED:
                newNotification.setDescription("Votre avis sur " + review.getContractor().getSocialReason() + " a été rejeté !");
                newNotification.setLink("/views/tendererManagement.xhtml?tabIndex=1");
                break;
            default:
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
        newNotification.setLink("/views/contractorManagement.xhtml?tabIndex=3");
        em.persist(newNotification);
        
        // Add the new Notification to others entities
        attachedContractor.addNotification(newNotification);
        attachedModerator.addNotification(newNotification);
        attachedReview.addNotification(newNotification);
        
    }
    
    @Override
    public void sendMessageNotificationToContractor(long idModerator,Contractor contractor,String message){
       
        // Get attached entities concerned
        Moderator attachedModerator = em.find(Moderator.class, idModerator);
        Contractor attachedContractor = em.merge(contractor);
        
        
        // Create and persist the new Notification
        Notification newNotification = new Notification(attachedContractor,attachedModerator ,message);
        newNotification.setCategory(NotificationType.TO_CONTRACTOR);
        newNotification.setLink("/views/contractorManagement.xhtml?tabIndex=4");
        em.persist(newNotification);
        
        // Add the new Notification to others entities
        attachedContractor.addNotification(newNotification);
        attachedModerator.addNotification(newNotification);     
    }
    
        
    @Override
    public void sendMessageNotificationToTenderer(long idModerator,Tenderer tenderer,String message){
       
        
        System.out.println("entre ou il faut");
        // Get attached entities concerned
        Moderator attachedModerator = em.find(Moderator.class, idModerator);
        Tenderer attachedTenderer  = em.merge(tenderer);
        
        
        // Create and persist the new Notification
        Notification newNotification = new Notification(attachedTenderer,attachedModerator,message);
        newNotification.setCategory(NotificationType.TO_TENDERER);
        newNotification.setLink("/views/tendererManagement.xhtml?tabIndex=2");
        em.persist(newNotification);
        
        // Add the new Notification to others entities
        attachedTenderer.addNotification(newNotification);
        attachedModerator.addNotification(newNotification);     
    }
    
}

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
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Class managing Tenderer
 * @author Tristan
 */
@Stateless
public class TendererManagerBeanImpl implements TendererManagerBean, Serializable {
    
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
    
    /**
     * Send notification to Moderator for new Review
     * @param review Review to manage
     * @param tenderer Tenderer who wrote the Review
     * @param contractor Contractor concerned by the Review
     * @param notificationType type of the Notification
     */
    public void sendNotification(Review review, Tenderer tenderer, Contractor contractor, NotificationType notificationType) {

        // Get attached entities concerned
        Tenderer attachedTenderer = em.merge(tenderer);
        Contractor attachedContractor = em.merge(contractor);
        Review attachedReview = em.merge(review);

        // Create and persist the new Notification
        Notification newNotification = new Notification(attachedReview, attachedTenderer, attachedContractor, notificationType);
        newNotification.setDescription("Un nouvel avis en attente de traitement !");
        newNotification.setLink("/views/adminManager.xhtml");
        em.persist(newNotification);

        // Add the new Notification to others entities
        attachedTenderer.addNotification(newNotification);
        attachedContractor.addNotification(newNotification);
        attachedReview.addNotification(newNotification);

    }

    @Override
    public void removeReview(long tendererId, Review review) {
        
        Review reviewToRemove = em.find(Review.class, review.getId());
        if (reviewToRemove != null) {
            
            reviewToRemove.setReviewEnabled(false);
                    
        }
        
    }
    
    /**
     * merge the tenderer to the base for update informations
     * @param t the tenderer to merge
     * @return return the tenderer updated
     */
    @Override
    public Tenderer update(Tenderer t) {
        // Get current date 
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        t.setUpdateDate(dateFormat.format(cal.getTime()));
        Tenderer update = em.merge(t);
        return update;
    }
    
    /**
     * delete a tenderer of the base
     * @param t the tenderer to delete
     */
    @Override
    public void delete(Tenderer t) {
        
        Tenderer tendererToRemove = em.find(Tenderer.class, t.getId());
        if(tendererToRemove != null) {
        
            this.em.remove(tendererToRemove);
        
        }
        
    }
    
    /**
     * get a tenderer from the base by his id
     * @param id the id of the tenderer
     * @return return the tenderer of the base
     */
    @Override
    public Tenderer getTendererById(long id) {
        return em.find(Tenderer.class, id);
    }

}

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
import com.gdf.persistence.Tenderer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Class supplying methods to manage Reviews
 * @author aziz
 */
@Stateless
public class EvaluationBeanImpl implements EvaluationBean {

    /**
     * Injected EntityManager giving access to the database
     */
    @PersistenceContext(unitName = "OopsPU")
    private EntityManager em;
    
    /**
     * Add a review given by a Tenderer to a Contractor
     * @param tenderer the Tenderer who wrote the Review
     * @param contractor the Contractor who receives the Review
     * @param review the Review to add
     */
    @Override
    public void addReview(Tenderer tenderer, Contractor contractor, Review review) {
        
        Tenderer attachedTenderer = em.merge(tenderer);
        Contractor attachedContractor = em.merge(contractor);
        
        attachedTenderer.addReview(review);
        attachedContractor.addReview(review);
    }
    
    @Override
    public void addReview(Long tendererID, Long contractorID, String reviewAppreciation, String reviewContent, int reviewRate) {
        
        // Get attached entities concerned
        Tenderer attachedTenderer = em.merge(em.find(Tenderer.class, tendererID));
        Contractor attachedContractor = em.merge(em.find(Contractor.class, contractorID));
        
        // Create and persist the new Review
        Review newReview = new Review(reviewAppreciation, reviewContent, reviewRate);
        em.persist(newReview);
        
        // Add the new Review to others entities
        attachedTenderer.addReview(newReview);
        attachedContractor.addReview(newReview);
        
        // Send notification for new Notification to Moderator
        this.sendNotification(newReview, attachedTenderer, attachedContractor, NotificationType.TO_MODERATOR);
        
    }
    
    @Override
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
    
}

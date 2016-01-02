/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Contractor;
import com.gdf.persistence.Notification;
import com.gdf.persistence.NotificationType;
import static com.gdf.persistence.NotificationType.TO_TENDERER;
import com.gdf.persistence.Review;
import com.gdf.persistence.ReviewState;
import com.gdf.persistence.Tenderer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Class supplying methods to manage Reviews
 *
 * @author aziz
 */
@Stateless
public class EvaluationBeanImpl implements EvaluationBean {

    /**
     * Injected EntityManager giving access to the database
     */
    @PersistenceContext(unitName = "OopsPU")
    private EntityManager em;

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

    @Override
    public void updateContractorsAnswer(Review review, String contractorsAnswer) {
        review.setContractorAnswer(contractorsAnswer);

        if (!contractorsAnswer.equals("") || contractorsAnswer != null) {
            // Get attached entities concerned
            Tenderer attachedTenderer = em.merge(review.getTenderer());
            Contractor attachedContractor = em.merge(review.getContractor());
            Review attachedReview = em.merge(review);

            // Create and persist the new Notification
            Notification newNotification = new Notification(review, review.getTenderer(), review.getContractor(), TO_TENDERER);
            newNotification.setDescription(attachedContractor.getRepresentatorFirstname() + " de " + attachedContractor.getSocialReason() + " a répondu à votre avis !");
            em.persist(newNotification);

            // Add the new Notification to others entities
            attachedTenderer.addNotification(newNotification);
            attachedReview.addNotification(newNotification);
            em.merge(attachedReview);
            
        }
        em.merge(review);
    }
}

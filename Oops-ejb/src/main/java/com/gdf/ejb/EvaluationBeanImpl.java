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
import static com.gdf.persistence.NotificationState.NOT_READ;
import com.gdf.persistence.Review;
import com.gdf.persistence.ReviewState;
import com.gdf.persistence.Tenderer;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
    
    
    @PostConstruct
    private void init(){
    }

    @Override
    public void addReview(Tenderer tenderer, Contractor contractor, Review review) {

        Tenderer attachedTenderer = em.merge(tenderer);
        Contractor attachedContractor = em.merge(contractor);
        
        em.persist(review);
        
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
        newReview.setReviewState(ReviewState.DELIVERED);
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

        if (contractorsAnswer != null && !contractorsAnswer.equals("")) {
            
            review.setContractorAnswer(contractorsAnswer);
            
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
            
            em.merge(review);
            
        }
        
    }

    @Override
    public void deleteContractorsAnswer(Review review) {
        review.setContractorAnswer(null);
        em.merge(review);
    }

    @Override
    public void askForReview(Long contractorID, Long tendererID) {
        Notification n = new Notification();
        java.util.Date date = new java.util.Date();
        n.setDate(new java.sql.Date(date.getTime()));
        n.setCategory(TO_TENDERER);
        n.setState(NOT_READ);
        Contractor contractor = em.find(Contractor.class, contractorID);
        n.setContractor(contractor);
        Tenderer tenderer = em.find(Tenderer.class, tendererID);
        n.setTenderer(tenderer);
        n.setDescription(contractor.getSocialReason()+" vous demande de le noter");
        em.persist(n);
    }

    @Override
    public Notification getLastNotificationSent(Long contractorID, Long tendererID){
        Query q = em.createNamedQuery("Notification.findByContractorAndTenderer", Notification.class);
        Contractor contractor = em.find(Contractor.class, contractorID);
        Tenderer tenderer = em.find(Tenderer.class, tendererID);
        q.setParameter(1, contractor);
        q.setParameter(2, tenderer);
        List<Notification> ln = q.getResultList();
        if(!ln.isEmpty())
            return ln.get(0);
        else
            return null;                   
    }
    
}

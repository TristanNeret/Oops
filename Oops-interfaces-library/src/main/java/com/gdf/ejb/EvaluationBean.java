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
import javax.ejb.Remote;

/**
 * Interface supplying methods for the evaluation of contractors
 * @author aziz
 */
@Remote
public interface EvaluationBean {
    
    /**
     * Add a review given by a Tenderer to a Contractor
     * @param tenderer the Tenderer who wrote the Review
     * @param contractor the Contractor who receives the Review
     * @param review the Review to add
     */
    public void addReview(Tenderer tenderer, Contractor contractor, Review review);
    
    /**
     * Add a new Review
     * @param tendererID id of the Tenderer who write the Review
     * @param contractorID id of the Contractor concerning by the Review
     * @param reviewAppreciation appreciation of the Review
     * @param reviewContent content of the Review
     * @param reviewRate ratting for the Review
     */
    public void addReview(Long tendererID, Long contractorID, String reviewAppreciation, String reviewContent, int reviewRate);

    /**
     * Send a new Notification
     * @param review Review concerned by the Notification
     * @param tenderer Tenderer concerned by the Notification
     * @param contractor Contractor concerned by the Notification
     * @param notificationType type of the Notification
     */
    public void sendNotification(Review review, Tenderer tenderer, Contractor contractor, NotificationType notificationType);
    
    /**
     * Update Review Contractor's answer 
     * @param review Review on which update the Contractor's answer
     * @param contractorsAnswer Contractor's answer
     */
    public void updateContractorsAnswer(Review review, String contractorsAnswer);
    
    /**
     * Delete Review Contractor's answer
     * @param review Review on which delete the Contractor's answer
     */
    public void deleteContractorsAnswer(Review review);

    /**
     * A contractor asks a tenderer for a review
     * @param contractorID the contractor who wants the review
     * @param tendererID the tender who will give the review to the contractor
     * @param message the message given by the contractor to the tenderer
     */
    public void askForReview(Long contractorID, Long tendererID, String message);
    
    /**
     * Return the last Notification sent
     * @param contractorID id of the Contractor concerned by the Notification
     * @param tendererID  id of the Tenderer concerned by the Notification
     * @return the last Notification which the Tenderer and the Contractor 
     * are concerned
     */
    public Notification getLastNotificationSent(Long contractorID, Long tendererID);

}

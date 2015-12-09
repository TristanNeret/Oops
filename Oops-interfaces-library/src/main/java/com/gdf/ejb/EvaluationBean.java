/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Category;
import com.gdf.persistence.Contractor;
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
}

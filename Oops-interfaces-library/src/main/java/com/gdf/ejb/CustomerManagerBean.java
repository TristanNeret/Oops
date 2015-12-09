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
 *
 * @author nicolas
 */
@Remote
public interface CustomerManagerBean {
    
    public void register(Contractor c); 
    public void register(Tenderer t);
    public Contractor searchContractorById(long id);
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
     * Add a Review
     * @param review the Review to Add
     * @param tenderer the Tenderer who write the Review
     * @param contractor Contractor concerning by the Review
     */
    public void addReview(Review review, Tenderer tenderer, Contractor contractor);
    public void addCategory(Category category);
    /**
     * Send a new Notification
     * @param review Review concerned by the Notification
     * @param tenderer Tenderer concerned by the Notification
     * @param contractor Contractor concerned by the Notification
     * @param notificationType type of the Notification
     */
    public void sendNotification(Review review, Tenderer tenderer, Contractor contractor, NotificationType notificationType);
    
}

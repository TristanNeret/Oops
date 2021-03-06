/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Category;
import com.gdf.persistence.Contractor;
import com.gdf.persistence.ReviewState;
import com.gdf.persistence.Tenderer;
import javax.ejb.Remote;

/**
 * Interface supplying methods for the administrator  
 * @author aziz
 */
@Remote
public interface AdministratorBean {
    
    /**
     * Add a Category of Service given by the Contractor
     * @param category the Category to add
     */
    public void addCategory(Category category);
    
    /**
     * Manage the Review as Moderator decided and send a Notification to the
     * Tenderer who wrote it 
     * @param reviewId id of the Review managed by the Moderator
     * @param moderatorId id of the Moderator who manage the Review
     * @param reviewState new state for the Review
     * @param content optional content for the Tenderer who wrote the Review
     */
    public void manageReview(long reviewId, long moderatorId, ReviewState reviewState, String content);

    
    /**
     * Send a message from the Moderator to a Tenderer
     * @param moderatorID id of the Moderator who send the notification
     * @param tendererSelected id of the tenderer who receive the notification 
     * @param message message content
     */
    public void sendMessageNotificationToTenderer(long moderatorID, Tenderer tendererSelected, String message);

    
    /**
     * Send a message from the Moderator to a Contractor
     * @param moderatorID  id of the Moderator who send the notification
     * @param contractorSelected id of the contractor who receive the notification 
     * @param message message content
     */
    public void sendMessageNotificationToContractor(long moderatorID, Contractor contractorSelected, String message);
    
}

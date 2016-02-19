/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Notification;
import java.util.List;
import javax.ejb.Remote;

/**
 * NotificationBean
 * @author Tristan
 */
@Remote
public interface NotificationBean {
     
    /**
     * Return list of unread Tenderer notifications
     * @param tendererId id of the Tenderer
     * @return list of unread Tenderer notifications
     */
    public List<Notification> findUnreadTendererNotification(Long tendererId);
    
    /**
     * Return list of unread Contractor notifications
     * @param contractorId id of the Contractor
     * @return list of unread Contractor notifications
     */
    public List<Notification> findUnreadContractorNotification(Long contractorId);
    
    /**
     * Return list of unread Moderator notifications
     * @param moderatorId id of the Moderator
     * @return list of unread Moderator notifications
     */
    public List<Notification> findUnreadModeratorNotification(Long moderatorId);
    
    /**
     * Return list of all Tenderer notifications
     * @param tendererId id of the Tenderer
     * @return list of all Tenderer notifications
     */
    public List<Notification> findAllTendererNotification(Long tendererId);
    
    /**
     * Return list of all Contractor notifications
     * @param contractorId id of the Contractor
     * @return list of all Contractor notifications
     */
    public List<Notification> findAllContractorNotification(Long contractorId);
    
    /**
     * Return list of all Moderator notifications
     * @param moderatorId id of the Moderator
     * @return list of all Moderator notifications
     */
    public List<Notification> findAllModeratorNotification(Long moderatorId);
    
    /**
     * Mark all notifications as read
     * @param notifications list of notifications to mark as read
     */
    public void markAsRead(List<Notification> notifications);
    
    /**
     * Mark the Notification as read
     * @param notification Notification to mark as read
     */
    public void markAsRead(Notification notification);
    
}

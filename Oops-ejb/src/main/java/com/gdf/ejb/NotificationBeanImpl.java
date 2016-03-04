/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Contractor;
import com.gdf.persistence.Notification;
import com.gdf.persistence.NotificationState;
import com.gdf.persistence.NotificationType;
import com.gdf.persistence.Tenderer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * NotificationBeanImpl
 * @author Tristan
 */
@Stateless
public class NotificationBeanImpl implements NotificationBean, Serializable {

    /**
     * Injected EntityManager giving access to the database
     */
    @PersistenceContext(unitName = "OopsPU")
    private EntityManager em;

    /**
     * method to find unread notification of a tenderer
     * @param tendererId id of the tenderer to get notifications
     * @return return a list of notifications
     */
    @Override
    public List<Notification> findUnreadTendererNotification(Long tendererId) {

        List<Notification> notificationList = new ArrayList<>();
        Tenderer tenderer = em.find(Tenderer.class, tendererId);

        if (tenderer != null) {

            for (Notification notification : tenderer.getNotifications()) {

                // Only add unread notifications
                if (notification.getState().equals(NotificationState.NOT_READ) && notification.getCategory().equals(NotificationType.TO_TENDERER)) {

                    notificationList.add(notification);

                }

            }

        }

        return notificationList;

    }
    
    /**
     * method to find unread notifications of a contractor
     * @param contractorId id of the contractor to get notifications
     * @return a list of notifications
     */
    @Override
    public List<Notification> findUnreadContractorNotification(Long contractorId) {

        List<Notification> notificationList = new ArrayList<>();
        Contractor contractor = em.find(Contractor.class, contractorId);

        if (contractor != null) {

            for (Notification notification : contractor.getNotifications()) {

                // Only add unread notifications
                if (notification.getState().equals(NotificationState.NOT_READ) && notification.getCategory().equals(NotificationType.TO_CONTRACTOR)) {

                    notificationList.add(notification);

                }

            }

        }

        return notificationList;

    }

    /**
     * method to find unread notifications of a moderator
     * @param moderatorId the id of the moderator to get notifications
     * @return a list of notifications
     */
    @Override
    public List<Notification> findUnreadModeratorNotification(Long moderatorId) {

        Query query = em.createNamedQuery("Notification.findUnreadForModerator", Notification.class);
        List<Notification> notificationList = query.getResultList();

        return notificationList;

    }
    
    /**
     * method to find all tenderer's notifications
     * @param tendererId
     * @return a list of notifications
     */
    @Override
    public List<Notification> findAllTendererNotification(Long tendererId) {
        
        List<Notification> notificationList = new ArrayList<>();
        Tenderer tenderer = em.find(Tenderer.class, tendererId);

        if (tenderer != null) {

            for (Notification notification : tenderer.getNotifications()) {

                // Only Tenderer notifications
                if (notification.getCategory().equals(NotificationType.TO_TENDERER)) {

                    notificationList.add(notification);

                }

            }

        }

        return notificationList;

    }

    /**
     * method to find all notifications of a contractor
     * @param contractorId
     * @return a list of notifications
     */
    @Override
    public List<Notification> findAllContractorNotification(Long contractorId) {
        
        List<Notification> notificationList = new ArrayList<>();
        Contractor contractor = em.find(Contractor.class, contractorId);

        if (contractor != null) {

            for (Notification notification : contractor.getNotifications()) {

                // Only Contractor notifications
                if (notification.getCategory().equals(NotificationType.TO_CONTRACTOR)) {

                    notificationList.add(notification);

                }

            }

        }

        return notificationList;

    }

    /**
     * method to find all moderator's notifications
     * @param moderatorId
     * @return a list of notifications
     */
    @Override
    public List<Notification> findAllModeratorNotification(Long moderatorId) {
        
        Query query = em.createNamedQuery("Notification.findForModerator", Notification.class);
        List<Notification> notificationList = query.getResultList();

        return notificationList;
        
    }
    
    /**
     * method to mark as read a list notifications
     * @param notifications 
     */
    @Override
    public void markAsRead(List<Notification> notifications) {

        for (Notification notification : notifications) {

            Notification attachNotification = em.find(Notification.class, notification.getId());
            if (attachNotification != null) {

                attachNotification.setState(NotificationState.READ);

            }

        }

    }
    
    /**
     * method to mark as read a notifications
     * @param notification 
     */
    @Override
    public void markAsRead(Notification notification) {

        Notification attachNotification = em.find(Notification.class, notification.getId());
        if (attachNotification != null) {

            attachNotification.setState(NotificationState.READ);

        }

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Contractor;
import com.gdf.persistence.Moderator;
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

    @Override
    public List<Notification> findUnreadModeratorNotification(Long moderatorId) {

        Query query = em.createNamedQuery("Notification.findUnreadForModerator", Notification.class);
        List<Notification> notificationList = query.getResultList();

        return notificationList;

    }

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

    @Override
    public List<Notification> findAllModeratorNotification(Long moderatorId) {
        
        Query query = em.createNamedQuery("Notification.findForModerator", Notification.class);
        List<Notification> notificationList = query.getResultList();

        return notificationList;
        
    }

    @Override
    public void markAsRead(List<Notification> notifications) {

        for (Notification notification : notifications) {

            Notification attachNotification = em.find(Notification.class, notification.getId());
            if (attachNotification != null) {

                attachNotification.setState(NotificationState.READ);

            }

        }

    }

    @Override
    public void markAsRead(Notification notification) {

        Notification attachNotification = em.find(Notification.class, notification.getId());
        if (attachNotification != null) {

            attachNotification.setState(NotificationState.READ);

        }

    }

}

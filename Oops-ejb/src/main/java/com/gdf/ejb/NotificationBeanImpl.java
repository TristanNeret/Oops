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
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * NotificationBeanImpl
 * @author Tristan
 */
@Stateless
public class NotificationBeanImpl implements NotificationBean {

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

        List<Notification> notificationList = new ArrayList<>();
        Moderator moderator = em.find(Moderator.class, moderatorId);

        if (moderator != null) {

            for (Notification notification : moderator.getNotifications()) {

                // Only add unread notifications
                if (notification.getState().equals(NotificationState.NOT_READ) && notification.getCategory().equals(NotificationType.TO_MODERATOR)) {

                    notificationList.add(notification);

                }

            }

        }

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
        
        List<Notification> notificationList = new ArrayList<>();
        Moderator moderator = em.find(Moderator.class, moderatorId);

        if (moderator != null) {

            for (Notification notification : moderator.getNotifications()) {

                // Only Moderator notifications
                if (notification.getCategory().equals(NotificationType.TO_MODERATOR)) {

                    notificationList.add(notification);

                }

            }

        }

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

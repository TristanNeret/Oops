/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import com.gdf.ejb.NotificationBean;
import com.gdf.persistence.Contractor;
import com.gdf.persistence.Moderator;
import com.gdf.persistence.Notification;
import com.gdf.persistence.Tenderer;
import com.gdf.session.SessionBean;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * ManageNotificationBean
 *
 * @author Tristan
 */
@Named(value = "manageNotificationBean")
@ViewScoped
public class ManageNotificationBean implements Serializable {

    @EJB
    private NotificationBean nb;

    private List<Notification> notificationsList;
    private List<Notification> allNotificationsList;
    private boolean unreadNotifications = true;

    /**
     * Initialize bean
     */
    @PostConstruct
    public void initBean() {
        
        this.notificationsList = new ArrayList<>();
        
        // Test if a User is connected
        if (SessionBean.getUserId() != null) {

            this.findNotifications();
        
        }

    }

    /**
     * Initialize Notifications for the connected person
     */
    public void findNotifications() {

        this.notificationsList = null;
        this.allNotificationsList = null;
        Long id = SessionBean.getUserId();
        switch (SessionBean.getUserCategory()) {

            case Tenderer.userCategory:
                this.notificationsList = Lists.reverse(this.nb.findUnreadTendererNotification(id));
                this.allNotificationsList = Lists.reverse(this.nb.findAllTendererNotification(id));
                break;

            case Contractor.userCategory:
                this.notificationsList = Lists.reverse(this.nb.findUnreadContractorNotification(id));
                this.allNotificationsList = Lists.reverse(this.nb.findAllContractorNotification(id));
                break;

            case Moderator.userCategory:
                this.notificationsList = Lists.reverse(this.nb.findUnreadModeratorNotification(id));
                this.allNotificationsList = Lists.reverse(this.nb.findAllModeratorNotification(id));
                break;

            default:
                break;

        }

        // Update unread Notification existence
        this.unreadNotifications = this.notificationsList.size() > 0;

    }

    /**
     * Mark all notifications as read
     */
    public void markAsRead() {

        this.nb.markAsRead(this.notificationsList);
        this.findNotifications();

    }

    /**
     * Go to Notification destination
     *
     * @param notification Notification the user wants to see
     */
    public void clickOnNotification(Notification notification) {

        try {

            this.nb.markAsRead(notification);
            this.findNotifications();

            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath().replaceFirst("/Oops-web*", "/Oops-web") + notification.getLink());

        } catch (IOException ex) {
            Logger.getLogger(ManageNotificationBean.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Return the well name of the Notification image
     *
     * @return the name of the Notification image
     */
    public String getNotificationImageName() {

        String imageName = "img/Notification_10.png";
        if (this.notificationsList.size() < 10) {

            imageName = "img/Notification_" + this.notificationsList.size() + ".png";

        }

        return imageName;

    }

    /**
     * Return complete Notification image url
     *
     * @param imageState 0 if mouse over, or check well image
     * @return complete Notification image url
     */
    public String getNotificationImageURL(int imageState) {

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        String link = context.getRequestContextPath().replaceFirst("/Oops-web*", "/Oops-web/");
        switch (imageState) {
            case 0:
                link += "resources/default/img/Notification_selected.png";
                break;
            default:
                link += "resources/default/" + this.getNotificationImageName();
                break;
        }

        return link;

    }

    // GETTER/SETTER
    
    public List<Notification> getNotificationsList() {
        return notificationsList;
    }

    public void setNotificationsList(List<Notification> notificationsList) {
        this.notificationsList = notificationsList;
    }

    public List<Notification> getAllNotificationsList() {
        return allNotificationsList;
    }

    public void setAllNotificationsList(List<Notification> allNotificationsList) {
        this.allNotificationsList = allNotificationsList;
    }

    public boolean isUnreadNotifications() {
        return unreadNotifications;
    }

    public void setUnreadNotifications(boolean unreadNotifications) {
        this.unreadNotifications = unreadNotifications;
    }

}

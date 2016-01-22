/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.persistence;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author aziz
 */
@NamedQueries({
    @NamedQuery(name = "Notification.findByContractorAndTenderer",
            query = "Select n FROM Notification n WHERE n.contractor=?1 "
                    + "AND n.tenderer=?2 ORDER BY n.date DESC")
})
@Entity
public class Notification implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;
    @Column(name = "NOTIFICATION_DATE")
    private Date date;
    @Column(name = "NOTIFICATION_STATE")
    private NotificationState state;
    private NotificationType category;
    
    @ManyToOne
    private Contractor contractor;
    
    @ManyToOne
    private Tenderer tenderer;
    
    @ManyToOne
    private Moderator moderator;
    
    @ManyToOne
    private Review review;

    /**
     * Empty constructor of Notification
     */
    public Notification() {
    }
    
    /**
     * Create a new Notification with parameters
     * @param review the Review concerned by the Notification
     * @param tenderer the Tenderer concerned by the Notification
     * @param contractor the Contractor concerned by the Notification
     * @param notificationType type of the new Notification
     */
    public Notification(Review review, Tenderer tenderer, Contractor contractor, NotificationType notificationType) {
        
        this.review = review;
        this.tenderer = tenderer;
        this.contractor = contractor;
        this.category = notificationType;
        this.state = NotificationState.NOT_READ;
        this.date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        
    }
    
    /**
     * Create a new Notification with parameters
     * @param review the Review concerned by the Notification
     * @param tenderer the Tenderer concerned by the Notification
     * @param moderator the Moderator concerned by the Notification
     * @param notificationType type of the new Notification
     */
    public Notification(Review review, Tenderer tenderer, Moderator moderator, NotificationType notificationType) {
        
        this.review = review;
        this.tenderer = tenderer;
        this.moderator = moderator;
        this.category = notificationType;
        this.state = NotificationState.NOT_READ;
        this.date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        
    }
    
    /**
     * Create a new Notification with parameters
     * @param review the Review concerned by the Notification
     * @param contractor the Contrcator concerned by the Notification
     * @param moderator the Moderator concerned by the Notification
     * @param notificationType type of the new Notification
     */
    public Notification(Review review, Contractor contractor, Moderator moderator, NotificationType notificationType) {
        
        this.review = review;
        this.contractor = contractor;
        this.moderator = moderator;
        this.category = notificationType;
        this.state = NotificationState.NOT_READ;
        this.date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public NotificationType getCategory() {
        return category;
    }

    public void setCategory(NotificationType category) {
        this.category = category;
    }

    public NotificationState getState() {
        return state;
    }

    public void setState(NotificationState state) {
        this.state = state;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    public Tenderer getTenderer() {
        return tenderer;
    }

    public void setTenderer(Tenderer tenderer) {
        this.tenderer = tenderer;
    }

    public Moderator getModerator() {
        return moderator;
    }

    public void setModerator(Moderator moderator) {
        this.moderator = moderator;
    }
    
}

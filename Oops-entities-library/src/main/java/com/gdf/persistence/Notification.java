/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.persistence;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Notification
 *
 * @author aziz
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Notification.deleteByReviewId", query = "DELETE FROM Notification n WHERE n.review.id=?1"),
    @NamedQuery(name = "Notification.findByContractorAndTenderer", query = "Select n FROM Notification n WHERE n.contractor=?1 " + "AND n.tenderer=?2 ORDER BY n.date DESC"),
    @NamedQuery(name = "Notification.findForModerator", query = "SELECT n FROM Notification n WHERE n.category=com.gdf.persistence.NotificationType.TO_MODERATOR"),
    @NamedQuery(name = "Notification.findUnreadForModerator", query = "SELECT n FROM Notification n WHERE n.state=com.gdf.persistence.NotificationState.NOT_READ AND n.category=com.gdf.persistence.NotificationType.TO_MODERATOR")
})
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

    private String link;

    @ManyToOne(fetch = EAGER, cascade = {CascadeType.MERGE})
    private Contractor contractor;

    @ManyToOne(fetch = EAGER, cascade = {CascadeType.MERGE})
    private Tenderer tenderer;

    @ManyToOne(fetch = EAGER, cascade = {CascadeType.MERGE})
    private Moderator moderator;

    @ManyToOne(fetch = EAGER, cascade = {CascadeType.MERGE})
    private Review review;

    /**
     * Empty constructor of Notification
     */
    public Notification() {
    }

    /**
     * Create a new Notification with parameters
     *
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
     *
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
     *
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

    /**
     * Create a new Notification with parameters
     *
     * @param message the content of the message sent to the contractor from the
     * Moderator
     * @param contractor the Contrcator concerned by the Notification
     * @param moderator the Moderator concerned by the Notification
     */
    public Notification(Contractor contractor, Moderator moderator, String message) {
        this.contractor = contractor;
        this.moderator = moderator;
        this.description = message;
        this.state = NotificationState.NOT_READ;
        this.date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
    }

    public Notification(Tenderer tenderer, Moderator moderator, String message) {
        this.tenderer = tenderer;
        this.moderator = moderator;
        this.description = message;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}

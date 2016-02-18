/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * Review
 * @author aziz
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Review.findWaitingReviews", query = "SELECT r FROM Review r WHERE r.reviewState=com.gdf.persistence.ReviewState.DELIVERED AND r.reviewEnabled=true"),
    @NamedQuery(name = "Review.findAcceptedReviews", query = "SELECT r FROM Review r WHERE r.reviewState=com.gdf.persistence.ReviewState.ACCEPTED AND r.contractor.id=?1 AND r.reviewEnabled=true"),
    @NamedQuery(name = "Review.findTendererReviews", query = "SELECT r FROM Review r WHERE r.tenderer.id=?1 AND r.reviewEnabled=true"),
    @NamedQuery(name = "Review.deleteReviewById", query = "DELETE FROM Review r WHERE r.id=?1")
})
public class Review implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String appreciation, content, contractorAnswer;
    private int rating;
    @Column(name = "REVIEW_DATE")
    private String date;
    private ReviewState reviewState;
    private boolean reviewEnabled;
    
    @ManyToOne(fetch = EAGER)
    private Tenderer tenderer;
    
    @ManyToOne(fetch = EAGER)
    private Contractor contractor;
    
    @OneToMany(mappedBy = "review", fetch = EAGER)
    private List<ModeratorReview> moderatorReviews = new ArrayList<>();
    
    @OneToMany(fetch = EAGER)
    private List<Notification> notifications = new ArrayList<>();
    
    /**
     * Create an instance of a Review
     */
    public Review() {
        
        this.reviewEnabled = true;
        
    }
    
    /**
     * Initialize a new Review (DELIVERED state)
     * @param reviewAppreciation appreciation of the Review
     * @param reviewContent content of the Review
     * @param reviewRate ratting for the Review
     */
    public Review(String reviewAppreciation, String reviewContent, int reviewRate) {
        
        this.appreciation = reviewAppreciation;
        this.content = reviewContent;
        this.rating = reviewRate;
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        this.date = dateFormat.format(cal.getTime());
        
        this.reviewEnabled = true;
        this.reviewState = ReviewState.DELIVERED;
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppreciation() {
        return appreciation;
    }

    public void setAppreciation(String appreciation) {
        this.appreciation = appreciation;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContractorAnswer() {
        return contractorAnswer;
    }

    public void setContractorAnswer(String contractorAnswer) {
        this.contractorAnswer = contractorAnswer;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ReviewState getReviewState() {
        return reviewState;
    }

    public void setReviewState(ReviewState reviewState) {
        this.reviewState = reviewState;
    }

    public Tenderer getTenderer() {
        return tenderer;
    }

    public void setTenderer(Tenderer tenderer) {
        this.tenderer = tenderer;
    }

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    public List<ModeratorReview> getModeratorReviews() {
        return moderatorReviews;
    }

    public void setModeratorReviews(List<ModeratorReview> moderatorReviews) {
        this.moderatorReviews = moderatorReviews;
    }

    public List<Notification> getNotifications() {
        List<Notification> returnNotifications = new ArrayList();
        for (Notification notification : this.notifications) {
            if (notification.getReview() != null) {
                if (notification.getReview().isReviewEnabled()) {
                    returnNotifications.add(notification);
                }
            } else {
                returnNotifications.add(notification);
            }
        }
        return returnNotifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
    
    public void addNotification(Notification n){
        this.notifications.add(n);
        n.setReview(this);
    }
    
    public void removeNotificationByReviewId(long reviewId) {
        for (ListIterator<Notification> iter = this.notifications.listIterator(); iter.hasNext(); ) {
            Notification n = iter.next();
            if (n.getReview().getId().equals(reviewId)) {
                iter.remove();
            }
        }
    }

    public boolean isReviewEnabled() {
        return reviewEnabled;
    }

    public void setReviewEnabled(boolean reviewEnabled) {
        this.reviewEnabled = reviewEnabled;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.date);
        hash = 97 * hash + Objects.hashCode(this.tenderer);
        hash = 97 * hash + Objects.hashCode(this.contractor);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Review other = (Review) obj;
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.tenderer, other.tenderer)) {
            return false;
        }
        if (!Objects.equals(this.contractor, other.contractor)) {
            return false;
        }
        return true;
    }
    
}

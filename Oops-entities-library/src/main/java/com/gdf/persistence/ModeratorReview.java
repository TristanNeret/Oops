/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.persistence;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * ModeratorReview
 * @author aziz
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "ModeratorReview.deleteByReviewId", query = "DELETE FROM ModeratorReview mr WHERE mr.review.id=?1")
})
public class ModeratorReview implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String content;
    @Column(name = "REVIEW_DATE")
    private Date date;

    @ManyToOne
    private Moderator moderator;
    
    @ManyToOne(cascade = {CascadeType.REMOVE})
    private Review review;
    
    /**
     * Create an instance of ModeratorReview
     */
    public ModeratorReview() {
        
    }
    
    /**
     * Initialize a new ModeratorReview from a Review and a Moderator
     * @param review managed Review
     * @param moderator Moderator who managed the Review
     */
    public ModeratorReview(Review review, Moderator moderator) {
        
        this.review = review;
        this.moderator = moderator;
        this.date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Moderator getModerator() {
        return moderator;
    }

    public void setModerator(Moderator moderator) {
        this.moderator = moderator;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.date);
        hash = 67 * hash + Objects.hashCode(this.moderator);
        hash = 67 * hash + Objects.hashCode(this.review);
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
        final ModeratorReview other = (ModeratorReview) obj;
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.moderator, other.moderator)) {
            return false;
        }
        if (!Objects.equals(this.review, other.review)) {
            return false;
        }
        return true;
    }
}

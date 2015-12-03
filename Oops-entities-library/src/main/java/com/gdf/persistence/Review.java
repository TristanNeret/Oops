/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author aziz
 */
@Entity
public class Review implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String appreciation,content, contractorAnswer;
    private int rating;
    @Column(name = "REVIEW_DATE")
    private Date date;
    private ReviewState reviewState;
    
    @ManyToOne
    private Tenderer tenderer;
    
    @ManyToOne
    private Contractor contractor;
    
    @OneToMany(mappedBy = "review")
    private List<ModeratorReview> moderatorReviews = new ArrayList<>();

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

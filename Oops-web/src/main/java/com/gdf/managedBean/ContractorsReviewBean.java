/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import com.gdf.ejb.EvaluationBean;
import com.gdf.ejb.SearchBean;
import com.gdf.persistence.Contractor;
import com.gdf.persistence.Review;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author chris
 */
@Named(value = "contractorsReviewBean")
@ViewScoped
public class ContractorsReviewBean implements Serializable{
    
    @EJB
    private EvaluationBean eb;
    @EJB
    private SearchBean searchBean;
    
    private Contractor contractor;
    private List<Review> reviews;
    private Map<Long, String> answersReview;

    /**
     * Creates a new instance of ContractorsReviewBean
     */
    public ContractorsReviewBean() {
        
    }
    
    @PostConstruct
    public void initBean() {
        //DELETE AND MODIFY WHEN WE WILL HAVE A CURRENT CONTRACTROR CONNECTED
        this.contractor = searchBean.searchContractorById(1);
        this.reviews = contractor.getReviews();
        this.answersReview = new HashMap<>();
        for(Review r : reviews){
            answersReview.put(r.getId(), r.getContractorAnswer());
        }
    }
    
    public void updateReviewsAnswer(Review review){
        //System.out.println(this.answersReview.get(review.getId()));
        eb.updateContractorsAnswer(review, this.answersReview.get(review.getId()));
    }
    
    public void deleteReviewsAnswer(Review review){
        eb.updateContractorsAnswer(review, null);
    }

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Map<Long, String> getAnswersReview() {
        return answersReview;
    }

    public void setAnswersReview(Map<Long, String> answersReview) {
        this.answersReview = answersReview;
    }
    
}

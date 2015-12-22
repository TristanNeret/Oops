/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import com.gdf.ejb.AdministratorBean;
import com.gdf.ejb.SearchBean;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 * Manage Tenderer Reviews
 * @author Tristan
 */
@Named(value = "manageReviewBean")
@RequestScoped
public class ManageReviewBean {

    @EJB
    private AdministratorBean ab;
    @EJB
    private SearchBean sb;
    
    private List reviewList;
    
    /**
     * Create an instance of ManageReviewBean
     */
    public ManageReviewBean() {
        
    }
    
    /**
     * Initialize ManageReviewBean by getting waiting reviews
     */
    @PostConstruct
    public void initBean() {
        
        this.reviewList = this.sb.getWaitingReviews();
        
    }

    public List getReviewList() {
        return reviewList;
    }

    public void setReviewList(List reviewList) {
        this.reviewList = reviewList;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Contractor;
import com.gdf.persistence.Review;
import com.gdf.persistence.Tenderer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Class supplying methods to manage Reviews
 * @author aziz
 */
@Stateless
public class EvaluationBeanImpl implements EvaluationBean {

    /**
     * Injected EntityManager giving access to the database
     */
    @PersistenceContext(unitName = "OopsPU")
    private EntityManager em;
    
    /**
     * Add a review given by a Tenderer to a Contractor
     * @param tenderer the Tenderer who wrote the Review
     * @param contractor the Contractor who receives the Review
     * @param review the Review to add
     */
    @Override
    public void addReview(Tenderer tenderer, Contractor contractor, Review review) {
        
        Tenderer attachedTenderer = em.merge(tenderer);
        Contractor attachedContractor = em.merge(contractor);
        
        attachedTenderer.addReview(review);
        attachedContractor.addReview(review);
    }
}

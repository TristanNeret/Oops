/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Contractor;
import com.gdf.persistence.Review;
import com.gdf.persistence.Tenderer;
import javax.ejb.Remote;

/**
 * Interface supplying methods for the evaluation of contractors
 * @author aziz
 */
@Remote
public interface EvaluationBean {
    
    /**
    * Add a review given by a Tenderer to a Contractor
    * @param tenderer the Tenderer who wrote the Review
    * @param contractor the Contractor who receives the Review
    * @param review the Review to add
    */
    public void addReview(Tenderer tenderer, Contractor contractor, Review review);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Review;
import com.gdf.persistence.Tenderer; 
import javax.ejb.Remote;

/**
 * Manage Tenderers
 * @author bibo
 */
@Remote
public interface TendererManagerBean {
    
    /**
     * Edit a Tenderer Review
     * @param review the Review to update
     */
    public void editReview(Review review);
    
    /**
     * Remove a Tenderer Review
     * @param tendererId id of the Tenderer who wrote the Review
     * @param review Review to remove
     */
    public void removeReview(long tendererId, Review review);
    
    /**
     * Get a tenderer by id
     * @param id the id of the tenderer to get
     * @return return the tenderer of the id 
     */
    public Tenderer getTendererById(long id);
    
    /**
     * Update Tenderer informations
     * @param t the Tenderer to update
     * @return return the tenderer updated
     */
    public Tenderer update(Tenderer t);
    
    /**
     * Delete a Tenderer
     * @param t the Tenderer to delete
     */
    public void delete(Tenderer t); 
    
}

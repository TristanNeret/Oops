/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Category;
import com.gdf.persistence.Contractor;
import com.gdf.persistence.Review;
import com.gdf.persistence.Tenderer;
import javax.ejb.Remote;

/**
 *
 * @author nicolas
 */
@Remote
public interface CustomerManagerBean {
    
    public void register(Contractor c); 
    public void register(Tenderer t);
    public Contractor searchContractorById(long id);
    public void addReview(Review review, Tenderer tenderer, Contractor contractor);
    public void addCategory(Category category);
    
}

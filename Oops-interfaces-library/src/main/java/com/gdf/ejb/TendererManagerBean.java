/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Tenderer; 
import javax.ejb.Remote;

/**
 * Manage Tenderers
 * @author bibo
 */
@Remote
public interface TendererManagerBean {
    
    /**
     * Update Tenderer informations
     * @param t the Tenderer to update
     */
    public void update(Tenderer t);
    
    /**
     * Delete a Tenderer
     * @param t the Tenderer to delete
     */
    public void delete(Tenderer t); 
    
}

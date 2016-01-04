/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Contractor;
import javax.ejb.Remote;

/**
 * Manage Contractors
 * @author bibo
 */
@Remote
public interface ContractorManagerBean {
    
    /**
     * Update Contractor informations
     * @param c the Contractor to update
     */
    public void update(Contractor c);
    
    /**
     * Delete a Contractor
     * @param c the Contractor to delete
     */
    public void delete(Contractor c); 
    
}

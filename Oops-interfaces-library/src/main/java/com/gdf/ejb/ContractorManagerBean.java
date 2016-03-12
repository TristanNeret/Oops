/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Contractor;
import com.gdf.persistence.PortfolioImage;
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
    
    /**
     * Undo changes of a contractor
     * @param c the contractor we want to undo changes
     * @return the contractor unchanged from the base
     */
    public Contractor undo(Contractor c);
    
    /**
     * Add an image to the Contractor Portfolio
     * @param id Contractor id
     * @param image byte[] representation of the image
     * @param name image name
     * @param description image description
     */
    public void addPortfolioImage(Long id, byte[] image, String name, String description);
    
    /**
     * Edit a Portfolio image
     * @param image PortfolioImage to edit
     */
    public void editPortfolioImage(PortfolioImage image);
    
    /**
     * Delete a Portfolio image
     * @param image PortfolioImage to delete
     */
    public void deletePortfolioImage(PortfolioImage image);
    
}

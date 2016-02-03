/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Contractor;
import com.gdf.persistence.Moderator;
import com.gdf.persistence.Tenderer;
import javax.ejb.Remote;

/**
 * Interface supplying methods for the registation of contractors and tenderers  
 * @author aziz
 */
@Remote
public interface RegistrationBean {
    
    /**
     * Register a Contractor 
     * @param c the Contractor to register
     */
    public void register(Contractor c); 
    
    /**
     * Register a Tenderer
     * @param t the Tenderer to register
     */
    public void register(Tenderer t);
    
    /**
<<<<<<< HEAD
     * Register a Moderator
=======
     * Register a Contractor
     * @param c the Contractor to update
     */
    
    public void update(Contractor c);
    
    /**
     * Register a Tenderer
     * @param t the Tenderer to update
     */
    
    public void update(Tenderer t);
     /** Register a Moderator
>>>>>>> origin/featureManageContractor
     * @param m the Moderator to register
     */
    public void register(Moderator m);
    
}

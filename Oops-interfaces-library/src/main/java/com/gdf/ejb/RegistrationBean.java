/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Contractor;
import com.gdf.persistence.Moderator;
import com.gdf.persistence.Service;
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
     * Update a Contractor
     * @param c the Contractor to update
     */
    public void update(Contractor c);
    
    /**
     * Update a Tenderer
     * @param t the Tenderer to update
     */
    public void update(Tenderer t);

    
    /** Register a Moderator
     * @param m the Moderator to register
     */
    public void register(Moderator m);

    /**
     * Update a Serice
     * @param s the Service to update
     */
    public void update(Service s);

}

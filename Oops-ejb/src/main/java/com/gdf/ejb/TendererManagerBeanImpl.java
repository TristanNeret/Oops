/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Tenderer;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Class managing Tenderer
 * @author Tristan
 */
public class TendererManagerBeanImpl implements TendererManagerBean {
    
    /**
     * Injected EntityManager giving access to the database
     */
    @PersistenceContext(unitName = "OopsPU")
    private EntityManager em;  

    @Override
    public void update(Tenderer t) {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    }

    @Override
    public void delete(Tenderer t) {
        
        Tenderer tendererToRemove =  em.find(Tenderer.class, t.getId());
        if(tendererToRemove != null) {
        
            this.em.remove(tendererToRemove);
        
        }
        
    }
    
}

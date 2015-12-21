/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Contractor;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Class managing Contractor
 * @author Tristan
 */
public class ContractorManagerBeanImpl implements ContractorManagerBean {

    /**
     * Injected EntityManager giving access to the database
     */
    @PersistenceContext(unitName = "OopsPU")
    private EntityManager em;  

    
    @Override
    public void update(Contractor c) {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    }

    @Override
    public void delete(Contractor c) {
        
        Contractor contractorToRemove =  em.find(Contractor.class, c.getId());
        if(contractorToRemove != null) {
        
            this.em.remove(contractorToRemove);
        
        }
        
    }
    
}

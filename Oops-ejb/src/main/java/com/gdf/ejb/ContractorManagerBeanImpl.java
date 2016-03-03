/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Contractor;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Class managing Contractor
 * @author Tristan
 */
@Stateless
public class ContractorManagerBeanImpl implements ContractorManagerBean, Serializable {

    /**
     * Injected EntityManager giving access to the database
     */
    @PersistenceContext(unitName = "OopsPU")
    private EntityManager em;  

    
    @Override
    public void update(Contractor c) {
        em.merge(c);
    }

    @Override
    public void delete(Contractor c) {
        Contractor contractorToRemove =  em.find(Contractor.class, c.getId());
        if(contractorToRemove != null) {
            this.em.remove(contractorToRemove);
        }
        
    }
    
    @Override
    public Contractor undo(Contractor c) {
        Contractor attachedContractor = em.merge(c);
        em.refresh(attachedContractor);
        return attachedContractor;
    }
    
}

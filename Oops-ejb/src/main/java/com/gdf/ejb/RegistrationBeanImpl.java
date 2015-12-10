/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Contractor;
import com.gdf.persistence.Tenderer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Class supplying registration methods to contractors and tenderers
 * @author aziz
 */
@Stateless
public class RegistrationBeanImpl implements RegistrationBean {

    /**
     * Injected EntityManager giving access to the database
     */
    @PersistenceContext(unitName = "OopsPU")
    private EntityManager em; 
    
    /**
     * Register a Contractor 
     * @param c the Contractor to register
     */
    @Override
    public void register(Contractor c) {
        em.persist(c);
    }
    
    /**
     * Register a Tenderer
     * @param t the Tenderer to register
     */
    @Override
    public void register(Tenderer t) {
        em.persist(t);
    }
}

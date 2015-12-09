/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Category;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Class supplying methods for the administrator
 * @author aziz
 */
@Stateless
public class AdministratorBeanImpl implements AdministratorBean {

    /**
     * Injected EntityManager giving access to the database
     */
    @PersistenceContext(unitName = "OopsPU")
    private EntityManager em;  
    
    /**
     * Add a Category of Service given by the Contractor
     * @param category to add
     */
    @Override
    public void addCategory(Category category) {
        em.persist(category);
    }
}

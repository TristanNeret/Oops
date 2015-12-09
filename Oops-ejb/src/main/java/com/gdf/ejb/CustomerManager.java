/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Category;
import com.gdf.persistence.Contractor;
import com.gdf.persistence.Notification;
import com.gdf.persistence.NotificationType;
import com.gdf.persistence.Review;
import com.gdf.persistence.Tenderer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nicolas
 */
@Stateless
public class CustomerManager implements CustomerManagerBean {

    @PersistenceContext(unitName = "OopsPU")
    private EntityManager em;  
    
    @Override
    public void register(Contractor c) {
        em.persist(c);
    }
    

    @Override
    public Contractor searchContractorById(long id) {
        
        Contractor contractor =  em.find(Contractor.class, id);
        contractor.getServices().size(); //The lazy relationships must be traversed before exiting the scope of the JPA Session to avoid the Exception.
        contractor.getReviews().size(); //
        
        return contractor;
    }

    @Override
    public void register(Tenderer t) {
        em.persist(t);
    }

    

    @Override
    public void addCategory(Category category) {
        em.persist(category);
    }

}

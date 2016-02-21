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
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * AuthentificationBeanImpl
 * @author Tristan
 */
@Stateless
public class AuthentificationBeanImpl implements AuthentificationBean {

    /**
     * Injected EntityManager giving access to the database
     */
    @PersistenceContext(unitName = "OopsPU")
    private EntityManager em;  
    
    @Override
    public Long isTendererValid(String userName, String userPassword) {
        
        Query query = em.createNamedQuery("Tenderer.authentification", Tenderer.class);
        query.setParameter(1, userName);
        query.setParameter(2, userPassword);
        
        if (query.getResultList().isEmpty()) {
            
            return null;
            
        } else {
            
            return (Long)query.getSingleResult();
            
        }
        
    }

    @Override
    public Long isContractorValid(String userName, String userPassword) {
        
        Query query = em.createNamedQuery("Contractor.authentification", Tenderer.class);
        query.setParameter(1, userName);
        query.setParameter(2, userPassword);
        
        if (query.getResultList().isEmpty()) {
            
            return null;
            
        } else {
            
            return (Long)query.getSingleResult();
            
        }
        
    }

    @Override
    public Long isModeratorValid(String userName, String userPassword) {
        
        Query query = em.createNamedQuery("Moderator.authentification", Tenderer.class);
        query.setParameter(1, userName);
        query.setParameter(2, userPassword);
        
        if (query.getResultList().isEmpty()) {
            
            return null;
            
        } else {
            
            return (Long)query.getSingleResult();
            
        }
        
    }
    
}

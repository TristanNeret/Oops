/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Tenderer;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author bibo
 */
@Stateless
public class TendererBean implements TendererManagerBean{

    @PersistenceContext(unitName = "OopsPU")
    private EntityManager em;
    
    
    @Override
    public void register(Tenderer t) {
        em.persist(t);
    }

    @Override
    public List<Tenderer> findAll() {
        
        TypedQuery<Tenderer> query = em.createNamedQuery("Tenderer.findAll", Tenderer.class);
        return query.getResultList();    
    
    }

    @Override
    public List<Tenderer> findByLogin(String login) {
        
        TypedQuery<Tenderer> query = em.createNamedQuery("Tenderer.findByLogin", Tenderer.class);
        query.setParameter(1, login);
        
        List results = query.getResultList();
        if(results.isEmpty()) {
            
            return null;
            
        } else {
            
            return query.getResultList();
                    
        }
        
    }

    @Override
    public List<Tenderer> findByEmail(String email) {
        
        TypedQuery<Tenderer> query = em.createNamedQuery("Tenderer.findByEmail", Tenderer.class);
        query.setParameter(1, email.toLowerCase());
        
        List results = query.getResultList();
        if(results.isEmpty()) {
            
            return null;
            
        } else {
            
            return query.getResultList();
                    
        }
        
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}

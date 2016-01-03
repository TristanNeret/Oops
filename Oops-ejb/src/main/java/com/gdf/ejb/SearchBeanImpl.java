/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Contractor;
import com.gdf.persistence.Review;
import com.gdf.persistence.Tenderer;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Class supplying searching methods 
 * @author nicolas
 */
@Stateless
public class SearchBeanImpl implements SearchBean {

    /**
     * Injected EntityManager giving access to the database
     */
    @PersistenceContext(unitName = "OopsPU")
    private EntityManager em;  
    
    // CONTRACTOR
    
    /**
     * SearchBean a Contractor by his id
     * @param id the id of the Contractor
     * @return the Contractor identified by the id if any or null if he doesn't exist
     */
    @Override
    public Contractor searchContractorById(long id) {
        
        Contractor contractor =  em.find(Contractor.class, id);
        
        if(contractor != null) {  
            //The lazy relationships must be traversed before exiting the scope of the JPA Session to avoid the Exception.
            contractor.getServices().size(); 
            contractor.getReviews().size(); 
        }
        
        return contractor;
    }
    
    @Override
    public Contractor searchContractorByLogin(String login) {
        
        Query query = em.createNamedQuery("Contractor.findByLogin");
        query.setParameter(1, login);
        if (query.getResultList().isEmpty()) {
            
            return null;
            
        } else {
            
            return (Contractor) query.getSingleResult();
            
        }
        
    }
    
    @Override
    public Contractor searchContractorBySiren(String siren) {
       
        Query query = em.createNamedQuery("Contractor.findBySiren");
        query.setParameter(1, siren);
        if (query.getResultList().isEmpty()) {
            
            return null;
            
        } else {
            
            return (Contractor) query.getSingleResult();
            
        }
        
    }

    @Override
    public List<Contractor> searchContractorByEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    // TENDERER
    
    @Override
    public Tenderer searchTendererByLogin(String login) {
        
        Query query = em.createNamedQuery("Tenderer.findByLogin");
        query.setParameter(1, login);
        if (query.getResultList().isEmpty()) {
            
            return null;
            
        } else {
            
            return (Tenderer) query.getSingleResult();
            
        }
        
    }
    
    // REVIEW

    @Override
    public List<Review> searchWaitingReviews() {
       
        Query query = em.createNamedQuery("Review.findWaitingReviews");
        
        return query.getResultList();
        
    }

    @Override
    public List<Review> searchAcceptedReviews(long id) {
       
        Query query = em.createNamedQuery("Review.findAcceptedReviews");
        query.setParameter(1, id);
        
        return query.getResultList();
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Contractor;
import com.gdf.persistence.Tenderer;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
    public List<Contractor> searchContractorByEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Contractor> searchContractorByKeyWord(String keyWord) {
        TypedQuery<Contractor> query;
        query = em.createQuery("SELECT c FROM Contractor c WHERE c.login LIKE :word OR c.email LIKE :word Or c.representatorFirstname LIKE :word OR c.representatorLastname LIKE :word Or c.socialReason LIKE :word Or c.phone LIKE :word",Contractor.class);
        query.setParameter("word", "%" + keyWord + "%");
        return query.getResultList();    
    }
    
    @Override
    public List<Tenderer> searchTendererByKeyWord(String keyWord) {
        TypedQuery<Tenderer> query;
        query = em.createQuery("SELECT t FROM Tenderer t WHERE t.login LIKE :word OR t.email LIKE :word Or t.firstname LIKE :word Or t.lastname LIKE :word Or t.phone LIKE :word",Tenderer.class);
        query.setParameter("word","%" + keyWord + "%");
        return query.getResultList();   
    }

    @Override
    public List<Tenderer> findAllTenderer() {
        TypedQuery<Tenderer> query = em.createNamedQuery("Tenderer.findAll", Tenderer.class);
        return query.getResultList(); 
    }

    @Override
    public List<Contractor> findAllContractor() {
        Query query = em.createNamedQuery("Contractor.findAll");
        return query.getResultList(); 
    }

  
}

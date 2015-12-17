/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Contractor;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author bibo
 */
@Stateless
public class ContractorBean implements ContractorManagerBean{
    
    @PersistenceContext(unitName = "OopsPU")
    private EntityManager em;
    
    
    
    @Override
    public void register(Contractor c) {
        em.persist(c);
    }

    @Override
    public List<Contractor> findAll() {
        Query query = em.createNamedQuery("Contractor.findAll");
        return query.getResultList(); 
    }

    @Override
    public List<Contractor> findByLogin(String login) {
        Query query = em.createNamedQuery("Contractor.findByLogin");
        query.setParameter(1, login);
        return query.getResultList();
    }

    @Override
    public List<Contractor> findByEmail(String email) {
        Query query = em.createNamedQuery("Contractor.findByEmail");
        query.setParameter(1, email.toLowerCase());
        return query.getResultList();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}

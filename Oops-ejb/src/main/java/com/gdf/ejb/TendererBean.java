/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Tenderer;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
        Query query = em.createNamedQuery("Tenderer.findAll");
        return query.getResultList();    }

    @Override
    public List<Tenderer> findByLogin(String login) {
        Query query = em.createNamedQuery("Tenderer.findByLogin");
        query.setParameter(1, login);
        return query.getResultList();
    }

    @Override
    public List<Tenderer> findByEmail(String email) {
        Query query = em.createNamedQuery("Tenderer.findByEmail");
        query.setParameter(1, email.toLowerCase());
        return query.getResultList();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}

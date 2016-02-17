/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Contractor;
import com.gdf.persistence.Service;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author bibo
 */
@Stateless
public class ServiceBeanImpl implements ServiceBean, Serializable {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PersistenceContext(unitName = "OopsPU")
    private EntityManager em;
    
    @Override
    public void register(Service service) {
        em.persist(service);
    }

    @Override
    public List<Service> findAll() {
        TypedQuery<Service> query = em.createNamedQuery("Service.findAll", Service.class);
        return query.getResultList();
    }
    
    
    @Override
    public void deleteContractorService(Contractor contractor,Service service) {
            service.setCategory(null);
            service.setContractor(null);
            em.merge(service);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.LegalInformation;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * LegalInformationBeanImpl
 * @author bibo
 */
@Stateless
public class LegalInformationBeanImpl implements LegalInformationBean, Serializable {

    @PersistenceContext(unitName = "OopsPU")
    private EntityManager em;
    
    @Override
    public void register(LegalInformation linfo) {
        em.persist(linfo);
    }

    @Override
    public List<LegalInformation> findAll() {
        TypedQuery<LegalInformation> query = em.createNamedQuery("LegalInformation.findAll", LegalInformation.class);
        return query.getResultList();
    }
    
    @Override
    public LegalInformation findById(long id) {
        LegalInformation legalInfo =  em.find(LegalInformation.class, id);
        return legalInfo;
    }

    /*
    @Override
    public List<LegalInformation> findBySiret(String siret) {
        TypedQuery<LegalInformation> query = em.createNamedQuery("LegalInformation.findBySiret", LegalInformation.class);
        query.setParameter(1, siret);
        return query.getResultList();
    }

    @Override
    public List<LegalInformation> findBySiren(String siren) {
        TypedQuery<LegalInformation> query = em.createNamedQuery("LegalInformation.findBySiren", LegalInformation.class);
        query.setParameter(1, siren);
        return query.getResultList();        
    }

    @Override
    public List<LegalInformation> findByRcs(String rcs) {
        TypedQuery<LegalInformation> query = em.createNamedQuery("LegalInformation.findByRcs", LegalInformation.class);
        query.setParameter(1, rcs);
        return query.getResultList(); 
    
    }

    @Override
    public List<LegalInformation> findByInsurrance(String insurrance) {
        TypedQuery<LegalInformation> query = em.createNamedQuery("LegalInformation.findByInsurrance", LegalInformation.class);
        query.setParameter(1, insurrance);
        return query.getResultList(); 
    
    }*/

    @Override
    public void update(LegalInformation linfo) {
        em.merge(linfo);
    }

    @Override
    public void delete(LegalInformation linfo) {
        em.remove(linfo);
    }
  
}

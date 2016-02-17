/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Address;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * AddressBeanImpl
 * @author bibo
 */
@Stateless
public class AddressBeanImpl implements AddressBean, Serializable {
    
    @PersistenceContext(unitName = "OopsPU")
    private EntityManager em;
        
    @Override
    public void register(Address address) {
        em.persist(address);
    }

    @Override
    public List<Address> findAll() {
        TypedQuery<Address> query = em.createNamedQuery("Address.findAll", Address.class);
        return query.getResultList();
    }

    @Override
    public List<Address> findByZip(String zip) {
        TypedQuery<Address> query = em.createNamedQuery("Address.findByZip", Address.class);
        query.setParameter(1, zip);
        return query.getResultList();
    }


    @Override
    public void update(Address address) {
        em.merge(address);
    }

    @Override
    public void delete(Address address) {
        em.remove(address);
    }

    @Override
    public Address findById(long id) {
        Address address =  em.find(Address.class, id);
        return address;     
    }

    
}

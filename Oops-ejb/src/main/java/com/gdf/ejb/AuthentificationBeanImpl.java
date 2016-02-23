/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Contractor;
import com.gdf.persistence.Moderator;
import com.gdf.persistence.Tenderer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;

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
        query.setParameter(2, this.encryptPassword(userPassword));
        
        if (query.getResultList().isEmpty()) {
            
            return null;
            
        } else {
            
            return (Long)query.getSingleResult();
            
        }
        
    }

    @Override
    public Long isContractorValid(String userName, String userPassword) {
        
        Query query = em.createNamedQuery("Contractor.authentification", Contractor.class);
        query.setParameter(1, userName);
        query.setParameter(2, this.encryptPassword(userPassword));
        
        if (query.getResultList().isEmpty()) {
            
            return null;
            
        } else {
            
            return (Long)query.getSingleResult();
            
        }
        
    }

    @Override
    public Long isModeratorValid(String userName, String userPassword) {
        
        Query query = em.createNamedQuery("Moderator.authentification", Moderator.class);
        query.setParameter(1, userName);
        query.setParameter(2, this.encryptPassword(userPassword));
        
        if (query.getResultList().isEmpty()) {
            
            return null;
            
        } else {
            
            return (Long)query.getSingleResult();
            
        }
        
    }
    
    /**
     * Return encrypted password 
     * @param password password to encrypt
     * @return the password encrypted
     */
    public String encryptPassword(String password) {
        
        ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
        passwordEncryptor.setAlgorithm("SHA-256");
        passwordEncryptor.setPlainDigest( true );
        
        return passwordEncryptor.encryptPassword(password);
        
    } 
               
}

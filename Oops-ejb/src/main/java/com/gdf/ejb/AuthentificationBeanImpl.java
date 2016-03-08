/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Contractor;
import com.gdf.persistence.Moderator;
import com.gdf.persistence.PasswordRequest;
import com.gdf.persistence.Tenderer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;

/**
 * AuthentificationBeanImpl
 * Class which implements the authentification
 * @author Tristan
 */
@Stateless
public class AuthentificationBeanImpl implements AuthentificationBean {

    /**
     * Injected EntityManager giving access to the database
     */
    @PersistenceContext(unitName = "OopsPU")
    private EntityManager em;  
    
    @EJB
    MailServiceBean msb;
    
    @EJB 
    SearchBean sb;
    
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
     
    /**
     * Send a link to reset the password of an account
     * @param email the email to send the link
     */
    @Override
    public void passwordForgotten(String email){      
         // Get current date 
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String date = dateFormat.format(cal.getTime());
        
        // Generate an id for the link
        String id = generateID(date+email);
        
        // Enter the request in the database
        PasswordRequest pr = new PasswordRequest();
        pr.setId(id);
        pr.setDate(date);
        pr.setUserEmail(email);       
        em.persist(pr);
        
        // Send an email with the link
        String userName = "";     
        Tenderer tenderer = sb.searchTendererByEmail(email);
        if(tenderer != null) userName = tenderer.getLogin();
        else{
              Contractor contractor = sb.searchContractorByEmail(email);
              if(contractor != null) userName = contractor.getLogin();
        }
        msb.sendEmail(
                email, 
                "Réinitialisation de votre mot de passe Oops", 
                "Bonjour "+userName+" !\n\n"
                +"Cliquez sur ce lien pour réinitialiser votre mot de passe :\n"
                +"http://localhost:8080/Oops-web/passwordForgotten.xhtml?id="+id // temporary used to go to the new password page
                +"\n\nCordialement,\n" +
                "L'équipe Oops."
        );
    }
    
    private String generateID(String value) {   
        ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
        passwordEncryptor.setAlgorithm("SHA-256");
        passwordEncryptor.setPlainDigest( true );     
        
        return passwordEncryptor.encryptPassword(value);    
    } 
    
    /**
     * Get a request identified by id
     * @param id the id generated for the request
     * @return the request
     */
    @Override
    public PasswordRequest getPasswordRequest(String id){
        PasswordRequest pr = em.find(PasswordRequest.class, id);
        // TODO delete the  password request
        return pr;
    }
}

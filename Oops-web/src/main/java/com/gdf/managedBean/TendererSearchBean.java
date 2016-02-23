/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import com.gdf.ejb.EvaluationBean;
import com.gdf.persistence.Contractor;
import com.gdf.persistence.Notification;
import com.gdf.persistence.Tenderer;
import com.gdf.session.SessionBean;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 * TendererSearchBean
 * @author nicolas
 */
@Named(value = "tendererSearchBean")
@ViewScoped
public class TendererSearchBean implements Serializable {
    
    @EJB
    private EvaluationBean ebi;
    
    /**
     * Interval between two requests
     */
    private static final long INTERVAL = 7*24*60*60*1000;
    
    /**
     * Message given by the contractor to the tenderer
     */
    private String message;
    
    /**
     * The tenderer who is going to be asked to give a review
     */
    private Tenderer tenderer;
    
    /**
     * Id of the current user connected
     */
    private Long userID;
    
    @PostConstruct
    public void initBean() {
        
        if (SessionBean.getUserCategory() != null) {
            
            if (SessionBean.getUserCategory().equals(Contractor.userCategory)) {
                
                userID = SessionBean.getUserId();
                
            }
            
        }
        
    }
    
    /**
     * Tests whether or not if the id of the connected user is a contractor
     * @param contractorID the id of the contractor
     * @return true if the id of the connected user is a contractor
     */
    private Boolean isContractorConnected() {
        
        // Check if connected user is a contrator
        if (SessionBean.getUserCategory() != null) {
            
            if (SessionBean.getUserCategory().equals(Contractor.userCategory)) {
                
                return true;
                
            }
            
        }
        return false;
        
    }

    /**
     * Send a request for review to a tenderer
     */
    public void askReview(){ 
        
        if(tenderer != null) {
            
            ebi.askForReview(userID, tenderer.getId(), message);
            // raz
            tenderer = null;
            message = "";
            
        }    
    }
    
    /**
     * Tests if the period of time between now and the last request for review sent by the contractor is superior than the interval
     * @param contractorID the id of the contractor
     * @param tendererID the id of the tenderer
     * @return true if the  contractor can send a request for review to the tenderer
     */
    private boolean isValidAskReview(Long contractorID, Long tendererID) {
        
        Notification n = ebi.getLastNotificationSent(contractorID, tendererID); 
        if(n != null){
            java.util.Date date = new java.util.Date();
            long current = date.getTime();
            return n.getDate().getTime()+INTERVAL <=  current;
        }
        return true;
        
    }
    
    /**
     * Tests if the "Demander un avis" button can be displayed
     * @param tendererID the id of the tenderer concerned by the request
     * @return true if the "Demander un avis" button can be displayed
     */
    public boolean display(Long tendererID) {
        
        return isContractorConnected() && isValidAskReview(userID, tendererID);
        
    }
    
    // GETTER/SETTER
    
    public Tenderer getTenderer() { 
        return tenderer;
    }

    public void setTenderer(Tenderer tenderer) { 
        this.tenderer = tenderer;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}

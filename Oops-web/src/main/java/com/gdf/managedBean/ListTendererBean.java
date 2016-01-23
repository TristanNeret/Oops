/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import com.gdf.ejb.EvaluationBean;
import com.gdf.persistence.Contractor;
import com.gdf.persistence.Notification;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author nicolas
 */
@Named(value = "listTendererBean")
@RequestScoped
public class ListTendererBean {

    /**
     * Creates a new instance of ListTendererBean
     */
    private static final long INTERVAL = 7*24*60*60*1000;
    
    @EJB
    private EvaluationBean ebi;
    
    

    public ListTendererBean() {
        FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("userID", (long)10);
        FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("userCategory", Contractor.userCategory);    
    }

    
    public Boolean isContractorConnected(Long contractorID) {
        // Check if connected user is a contrator
        String userCategory = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("userCategory");      
        return contractorID != null && !contractorID.equals("") && userCategory.equals(Contractor.userCategory);
    }

    
    public void askReview(long tendererID){
        Long contractorID = (Long) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("userID");
        ebi.askForReview(contractorID, tendererID);
        // open a dialog
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Avis envoyé avec succès !",null );
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public boolean isValidAskReview(long contractorID, long tendererID){
        Notification n = ebi.getLastNotificationSent(contractorID, tendererID); 
        if(n != null){
            java.util.Date date = new java.util.Date();
            long current = date.getTime();
            return n.getDate().getTime()+INTERVAL <=  current;
        }
        return true;
    }
    
    public boolean display(long tendererID){
        long contractorID = (Long) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("userID");
        return isContractorConnected(contractorID) && isValidAskReview(contractorID,(Long)tendererID);
    }
}
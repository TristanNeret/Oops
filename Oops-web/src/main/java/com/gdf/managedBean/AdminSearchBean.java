/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import com.gdf.ejb.AdministratorBean;
import com.gdf.ejb.SearchBean;
import com.gdf.persistence.Contractor;
import com.gdf.persistence.Moderator;
import com.gdf.persistence.Tenderer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author nicolas
 */
@Named(value = "adminSearchBean")
@ViewScoped
public class AdminSearchBean implements Serializable {

    /**
     * Creates a new instance of AdminSearchBean
     */
    
    private String message;
    private String keyWord;
    private String type;
    private Contractor contractorSelected;
    private Tenderer tendererSelected;
  
    
    /**
     * Id of the current user connected
     */
    private long moderatorID;
    
  
    /**
     * List of the contractors resulting of the search
     */
    private List<Contractor> lc;
    
    /**
     * List of the tenderers resulting of the search
     */
    private List<Tenderer> ltd;
    
    @EJB
    private SearchBean sb;
    
    @EJB
    private AdministratorBean ab;
    
       @PostConstruct
    public void initBean() {
        // Temporary used to connect a Contractor
        FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("moderatorID", Long.parseLong("1"));
        moderatorID = (Long) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("moderatorID");
        
        //System.out.println(userID+" ------------------------------------ "+userCategory);
    }
 
    public AdminSearchBean() {
         // Temporary used to connect a Tenderer
        ltd = new ArrayList();
        lc = new ArrayList();
    }

    public List<Contractor> getLc() {
        return lc;
    }

    public void setLc(List<Contractor> lc) {
        this.lc = lc;
    }

    public List<Tenderer> getLtd() {
        return ltd;
    }

    public void setLtd(List<Tenderer> ltd) {
        this.ltd = ltd;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public List<String> completeQuery(String query) {
       
        List<String> results;
       
        /*
            In the initial Case, a tenderer is searched
        */
        if(type==null){
            type="tend";
        }
        
        if(type.equals("tend")){
            results = sb.findTendererBeginBy(query);
        }else{
            results = sb.findContractorBeginBy(query);
        }
        return results;   
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Contractor getContractorSelected() {
        return contractorSelected;
    }

    public void setContractorSelected(Contractor contractorSelected) {
        this.contractorSelected = contractorSelected;
    }

    public Tenderer getTendererSelected() {
        return tendererSelected;
    }

    public void setTendererSelected(Tenderer tendererSelected) {
        this.tendererSelected = tendererSelected;
    }
    
   
    public void search(){
       
        /*reinitalise Arayl
        
        */
        ltd = new ArrayList();
        lc = new ArrayList();
        
        if(type.equals("tend")){
            ltd = sb.findTenderers(keyWord);
        }else{
            lc = sb.findContractors(keyWord, 0, null, null, "ALPHABETICAL");
        }
    }
    
    
    public void sendMessage(){
          
        if(type.equals("tend"))
            ab.sendMessageNotificationToTenderer(moderatorID, tendererSelected, message);
        else
            ab.sendMessageNotificationToContractor(moderatorID, contractorSelected, message);
            
            
    }
        
    
    
    
    
    
}

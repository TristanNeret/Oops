/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import com.gdf.ejb.AdministratorBean;
import com.gdf.ejb.SearchBean;
import com.gdf.persistence.Contractor;
import com.gdf.persistence.Tenderer;
import com.gdf.session.SessionBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 * AdminSearchBean
 *
 * @author nicolas
 */
@Named(value = "adminSearchBean")
@ViewScoped
public class AdminSearchBean implements Serializable {

    private String message;
    private String keyWord;
    private String type;
    private boolean success;
    private Contractor contractorSelected;
    private Tenderer tendererSelected;
    private String targetGroup;

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
      
        moderatorID = SessionBean.getUserId();
        this.targetGroup = "ALL";
        this.message = "";
        this.success = false;

    }
    
    public AdminSearchBean() {
        
        ltd = new ArrayList();
        lc = new ArrayList();
        
    }

    public List<String> completeQuery(String query) {

        List<String> results;

        /*
         In the initial Case, a tenderer is searched
         */
        if (type == null) {
            type = "tend";
        }

        if (type.equals("tend")) {
            results = sb.findTendererBeginBy(query);
        } else {
            results = sb.findContractorBeginBy(query);
        }
        return results;
        
    }

    public void search() {

        if (type != null) {
            
            ltd = new ArrayList();
            lc = new ArrayList();

            if (type.equals("tend")) {
                ltd = sb.findTenderers(keyWord);
            } else {
                lc = sb.findContractors(keyWord, 0, null, null, "ALPHABETICAL",null);
            }
        
        }

        message = "";
        
    }

    public void sendMessage() {

        if (type.equals("tend")) {
            ab.sendMessageNotificationToTenderer(moderatorID, tendererSelected, message);
        } else {
            ab.sendMessageNotificationToContractor(moderatorID, contractorSelected, message);
        }

        // reset
        tendererSelected = null;
        contractorSelected = null;
        message = "";
        
    }

    public void sendGroupMessage() {

        switch (this.targetGroup) {
            case "TEND":

                for (Tenderer t : sb.findAllTenderer()) {
                    ab.sendMessageNotificationToTenderer(moderatorID, t, message);
                }

                break;

            case "CONT":

                for (Contractor c : sb.findAllContractor()) {
                    ab.sendMessageNotificationToContractor(moderatorID, c, message);
                }

                break;

            case "ALL":

                for (Tenderer t : sb.findAllTenderer()) {
                    ab.sendMessageNotificationToTenderer(moderatorID, t, message);
                }
                for (Contractor c : sb.findAllContractor()) {
                    ab.sendMessageNotificationToContractor(moderatorID, c, message);
                }

                break;
        }
        FacesContext.getCurrentInstance().addMessage("growlSuccess", new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès !", "Votre message a été envoyé !"));
        this.success = true;

        this.message = "";
        this.targetGroup = "";
    }
    
    // GETTER/SETTER
    
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getTargetGroup() {
        return targetGroup;
    }

    public void setTargetGroup(String targetGroup) {
        this.targetGroup = targetGroup;
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
        this.message = null;
        this.contractorSelected = contractorSelected;
    }

    public Tenderer getTendererSelected() {
        return tendererSelected;
    }

    public void setTendererSelected(Tenderer tendererSelected) {
        this.message = null;
        this.tendererSelected = tendererSelected;
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

}

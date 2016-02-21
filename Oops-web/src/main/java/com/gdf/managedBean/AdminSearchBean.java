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
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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
        
        // Temporary used to connect a Moderator
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userID", new Long("1"));
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userCategory", Moderator.userCategory);

        moderatorID = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");

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

    public void search() {

        ltd = new ArrayList();
        lc = new ArrayList();

        if (type.equals("tend")) {
            ltd = sb.findTenderers(keyWord);
        } else {
            lc = sb.findContractors(keyWord, 0, null, null, "ALPHABETICAL",null);
        }

        message = null;
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

    public String getTargetGroup() {
        return targetGroup;
    }

    public void setTargetGroup(String targetGroup) {
        this.targetGroup = targetGroup;
    }

    public void init() {
        this.targetGroup = null;
        this.message = null;
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

        this.message = "";
        this.targetGroup = "";
    }
}

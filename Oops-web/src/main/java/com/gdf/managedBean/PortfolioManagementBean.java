/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import com.gdf.ejb.ContractorManagerBean;
import com.gdf.ejb.SearchBean;
import com.gdf.persistence.Contractor;
import com.gdf.persistence.PortfolioImage;
import com.gdf.session.SessionBean;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 * PortfolioManagementBean
 * @author Tristan
 */
@Named(value = "portfolioManagementBean")
@ViewScoped
public class PortfolioManagementBean implements Serializable {

    @EJB
    private SearchBean searchBean;
    
    @EJB
    private ContractorManagerBean contractorManagerBean;
    
    private Contractor contractor;
    
    private List<PortfolioImage> images;
    
    @PostConstruct
    public void initBean() {
        
        this.contractor = this.searchBean.searchContractorById(SessionBean.getUserId());
        this.images = this.contractor.getImages();

    }
    
    /**
     * Event on file upload
     * @param event
     */
    public void handleFileUpload(FileUploadEvent event) {
        
        // Get uploaded file
        UploadedFile uploadedFile = event.getFile();
        String fileName = uploadedFile.getFileName();
        byte[] contents = uploadedFile.getContents();
        
        // Add image and update view
        this.contractorManagerBean.addPortfolioImage(this.contractor.getId(), contents, fileName, "");
        this.initBean();
        
    }
    
    /**
     * Remove an image
     * @param image PortfolioImage to remove
     */
    public void removeImage(PortfolioImage image) {

        this.contractor.removeImage(image);
        this.contractorManagerBean.update(this.contractor);
        this.contractorManagerBean.deletePortfolioImage(image);
        this.initBean();
        
    }
    
    /**
     * Edit an image
     * @param image PortfolioImage to edit
     */
    public void editImage(PortfolioImage image) {
        
        this.contractorManagerBean.editPortfolioImage(image);
        this.initBean();
        FacesContext.getCurrentInstance().addMessage("growlPortfolio", new FacesMessage("Description de l'image modifiée !", ""));
        
    }
    
    // GETTER/SETTER

    public List<PortfolioImage> getImages() {
        return images;
    }

    public void setImages(List<PortfolioImage> images) {
        this.images = images;
    }

}
    
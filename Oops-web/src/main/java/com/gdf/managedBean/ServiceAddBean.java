/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import com.gdf.ejb.RegistrationBean;
import com.gdf.ejb.SearchBean;
import com.gdf.ejb.ServiceBean;
import com.gdf.persistence.Category;
import com.gdf.persistence.Contractor;
import com.gdf.persistence.Service;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * ServiceAddBean
 * @author bibo
 */
@Named(value = "serviceAddBean")
@ViewScoped
public class ServiceAddBean implements Serializable {

    @EJB
    private SearchBean searchBean;
    
    @EJB
    private ServiceBean serviceBean;
    
    @EJB
    private RegistrationBean rb;
    
    private String title;
    private String description;
    private long idCategory;
    private double price;
    private Contractor contractor;
    private List<Category> categories;
    private Service editService;
    
    @PostConstruct
    public void init() {

        this.setContractor(searchBean.searchContractorById(50));
        categories = new ArrayList<>();
        this.setCategories(searchBean.getCategories());
        
        this.setTitle(null);
        this.setDescription(null);
        this.setPrice(0.0);
        
    }
    
    /**
     * Test if the Contractor has Services 
     * @return True if the Contractor has Service, or False 
     */
    public boolean areServices() {
        
        return !this.contractor.getServices().isEmpty();
    
    }
    
    /**
     * Add a new Service
     */
    public void addService(){

        Service service = new Service();
        service.setTitle(getTitle());
        service.setDescription(getDescription());
        if (this.getPrice() != 0.0) {
            service.setPrice(getPrice());
        }
        service.setCategory(this.searchBean.searchCategoryById(this.idCategory));
        contractor.addService(service);
        rb.update(contractor);
        
        this.init();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Votre prestation a été ajoutée avec succès !", ""));
        
    }
    
    /**
     * Delete a Contractor's Service
     * @param service Service to Remove
     */
    public void deleteService(Service service){
        
        contractor.removeService(service);
        rb.update(contractor);
        this.init();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Votre prestation a été supprimé avec succès !", ""));
 
    }
    
    /**
     * Uppdate a Contractor's Service
     */
    public void updateService(){
        
        rb.update(this.editService);
        this.init();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Votre prestation a été modifiée avec succès !", ""));
    
    }

    /*
     * GETTER and SETTER
     */
    
    public List<Category> getCategories() {
        return categories;
    }
    
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    public long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(long idCategory) {
        this.idCategory = idCategory;
    }

    public Service getEditService() {
        return editService;
    }

    public void setEditService(Service editService) {
        this.editService = editService;
    }
    
}
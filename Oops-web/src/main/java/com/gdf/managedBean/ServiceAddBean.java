/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import com.gdf.ejb.RegistrationBean;
import com.gdf.ejb.SearchBean;
import com.gdf.persistence.Category;
import com.gdf.persistence.Contractor;
import com.gdf.persistence.Service;
import com.gdf.session.SessionBean;
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
 *
 * @author bibo
 */
@Named(value = "serviceAddBean")
@ViewScoped
public class ServiceAddBean implements Serializable {

    @EJB
    private SearchBean searchBean;

    @EJB
    private RegistrationBean rb;

    private Service newService;
    private long idCategory;
    private Double price;
    private Contractor contractor;
    private List<Category> categories;
    private Service editService;
    private boolean success;

    @PostConstruct
    public void init() {

        this.setContractor(searchBean.searchContractorById(SessionBean.getUserId()));
        categories = new ArrayList<>();
        this.setCategories(searchBean.getCategories());
        
        this.newService = new Service();
        this.setPrice(0.0);
        this.success = false;

    }

    /**
     * Test if the Contractor has Services
     *
     * @return True if the Contractor has Service, or False
     */
    public boolean areServices() {
        return !this.contractor.getServices().isEmpty();
    }

    /**
     * Add a new Service
     */
    public void addService() {

        if (this.getPrice() != null) {
            newService.setPrice(getPrice());
        }
        newService.setCategory(this.searchBean.searchCategoryById(this.idCategory));
        contractor.addService(newService);
        rb.update(contractor);

        this.init();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Votre prestation a été ajoutée avec succès !", ""));
        this.success = true;

        //reset
        this.newService = new Service();
    }

    /**
     * Delete a Contractor's Service
     *
     * @param service Service to Remove
     */
    public void deleteService(Service service) {

        contractor.removeService(service);
        rb.update(contractor);
        this.init();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Votre prestation a été supprimé avec succès !", ""));

    }

    /**
     * Uppdate a Contractor's Service
     */
    public void updateService() {

        rb.update(this.editService);
        this.init();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Votre prestation a été modifiée avec succès !", ""));

    }

    // GETTER/SETTER
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Service getNewService() {
        return newService;
    }

    public void setNewService(Service newService) {
        this.newService = newService;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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

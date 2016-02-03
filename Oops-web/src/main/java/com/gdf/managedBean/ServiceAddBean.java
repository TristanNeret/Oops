/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

 
 
import com.gdf.ejb.CategoryBean;
import com.gdf.ejb.ContractorManagerBean;
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
 *
 * @author bibo
 */

@Named(value = "serviceAddBean")
@ViewScoped
public class ServiceAddBean implements Serializable {

    /**
     * Creates a new instance of ServiceAddBean
     */
    
    @EJB
    private SearchBean searchBean;
    
    @EJB
    private CategoryBean categoryBean;
    
    @EJB
    private ServiceBean serviceBean;
    
    @EJB
    private RegistrationBean rb;
    
    
    private String title;
    private String description;
    private double price;
    private Contractor contractor;
    private Category category;
    private List<Category> categories;
    
    
    
    @PostConstruct
    public void init() {

        this.setContractor(searchBean.searchContractorById(50));
        //this.setCategory(categoryBean.findById((long) 9));
        categories = new ArrayList<>();
        setCategories(categoryBean.findAll());
        
   
    }
    
    
    public boolean areServices() {
        return !this.contractor.getServices().isEmpty();
    }
    
    public void addService(){

        Service service =new Service();
        service.setTitle(getTitle());
        service.setDescription(getDescription());
        service.setPrice(getPrice());
        service.setCategory(getCategory());
        //serviceBean.register(service);
        contractor.addService(service);
        rb.update(contractor);
        
        this.init();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Votre service a été ajoutée avec succès !", ""));
    }
    
    
    public void deleteService(Service service){
        
        contractor.removeService(service);
        rb.update(contractor);
        //serviceBean.deleteContractorService(service);
        this.init();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Votre service a été supprimé avec succès !", ""));
 
    }
    
    public void updateService(Service service){
        
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Votre service a été modifiée avec succès !", ""));
    }


  
   

    /*
    getters and setters
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
    

   
    
}
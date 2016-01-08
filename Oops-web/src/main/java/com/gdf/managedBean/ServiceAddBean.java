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
import com.gdf.persistence.Category;
import com.gdf.persistence.Contractor;
import com.gdf.persistence.Service;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author bibo
 */

@Named(value = "serviceAddBean")
@RequestScoped
public class ServiceAddBean {

    /**
     * Creates a new instance of ServiceAddBean
     */
    
    @EJB
    SearchBean searchBean;
    
    @EJB
    CategoryBean categoryBean;
    
    @EJB
    private RegistrationBean rb;
    
    
    private String title;
    private String description;
    private double price;
    private Contractor contractor;
    private Category category;
    private boolean editable;
    
    
    
    @PostConstruct
    public void init() {

        this.setContractor(searchBean.searchContractorById(1));
        this.setCategory(categoryBean.findById((long) 1));
        
    }
    
    public String add(){

        Service service =new Service();
        service.setTitle(title);
        service.setDescription(description);
        service.setPrice(price);
        service.setCategory(category);
        contractor.addService(service);
        rb.update(contractor);
        System.out.println("saved");
        return "register";
    }
    
    
    
    /*
        Modifications
    */
    
    public boolean isEditable() {
	return editable;
    }
    
    public void setEditable(boolean editable) {
	this.editable = editable;
    } 
    
    
    /*
     getters and setters
    */

  
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
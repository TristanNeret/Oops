/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import com.gdf.ejb.SearchBean;
import com.gdf.persistence.Contractor;
import com.gdf.persistence.Tenderer;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

/**
 * ManagedBean providing search functionalities 
 * @author nicolas
 */
@Named(value = "searchManagedBean")
@ApplicationScoped
public class SearchManagedBean implements Serializable {
    
    /**
     * A keyword used to find a contractor or tenderer
     */
    private String keyWord;
    
    /**
     * The type of the user we are looking for : contractor or tenderer
     */
    private String type;
    
    /**
     * Minimum rating of the contractor
     */
    private int rating;
    
    /**
     * Country of the contractor
     */
    private String country;
    
    /**
     * Category of the services given by the contractor
     */
    private String category;
    
    /**
     * List of the contractors resulting of the search
     */
    private List<Contractor> lc;
    
    /**
     * List of the tenderers resulting of the search
     */
    private List<Tenderer> ltd;
    
    /**
     * All countries of the contractors
     */
    private  List<SelectItem> allCountry;
    
    /*
    * All categories of services given by the contractors 
    */
    private  List<SelectItem> allCategory;
    
    /**
     * Injected EJB giving the search methods
     */
    @EJB
    private SearchBean sb;

    private Map<String,String> orders;
    
    private String order = "ALPHABETICAL"; // default value
    
    @PostConstruct
    public void setup(){
        orders = new LinkedHashMap<>();
        orders.put("Nom", "ALPHABETICAL"); // label, value
        orders.put("Note", "RATINGS");
    }
    
    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<SelectItem> getAllCountry() {
        
        List<String> listC = sb.getAllCountry();
        List<SelectItem> li = new ArrayList<>();
        
        for(String country : listC)
                li.add(new SelectItem(country)); 
        
        return li;
    }

    public void setAllCountry(List<SelectItem> allCountry) {
        this.allCountry = allCountry;
    }

    public List<SelectItem> getAllCategory() {
        
        List<String> listC = sb.getAllCategory();   
        List<SelectItem> li = new ArrayList<>();
        
        for(String category : listC)
                li.add(new SelectItem(category)); 
                 
        return li;
    }

    public void setAllCategory(List<SelectItem> allCategory) {
        this.allCategory = allCategory;
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

    public void setLt(List<Tenderer> ltd) {
        this.ltd = ltd;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    /**
     * Starts the search given all the criterias
     * @return the view where the results will be displayed
     */
    public String search(){
  
        if(type.equals("tend"))
        {
            ltd =  sb.findTenderers(keyWord);
            return "/views/listTenderer.xhtml?faces-redirect=true";
        }
        else
        {
            lc = sb.findContractors(keyWord,rating,country,category, order);            
            return "/views/listContractor.xhtml?faces-redirect=true";       
        }        
    }
    
    public void valueChangeMethod(ValueChangeEvent e){
        order = e.getNewValue().toString();
	search();
    }

    public Map<String, String> getOrders() {
        return orders;
    }

    public void setOrders(Map<String, String> orders) {
        this.orders = orders;
    }
}

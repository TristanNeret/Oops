/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;


import com.gdf.ejb.SearchBean;
import com.gdf.persistence.Contractor;
import com.gdf.persistence.Review;
import com.gdf.persistence.Tenderer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;

/**
 * ManagedBean providing search functionalities 
 * @author nicolas
 */
@Named(value = "searchManagedBean")
@SessionScoped
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
    
    private String region;
    
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
    private  List<SelectItem> allRegions;
    
    
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

    private String order;
    private String categoryParam;
    private List<Review> reviewsToShow;
    private List<String> listC;
    
    @PostConstruct
    public void setup() {
        
        region = null;
        this.type = "cont";
        this.order = "ALPHABETICAL";
        this.listC = sb.getAllCategory();
        this.reviewsToShow = this.sb.getThreeReviewsToShow();
        
    }
    
    /**
     * Launch category search from url if param not null
     */
    public void serachCategory() {
        
        if (this.categoryParam != null) {
            
            if (this.listC.contains(this.categoryParam)) {
                
                this.setCategory(this.categoryParam);
                this.search();
                
            }
            
        }
        
    }
    
    // GETTER/SETTER
    
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

    public String getCategoryParam() {
        return categoryParam;
    }

    public void setCategoryParam(String categoryParam) {
        this.categoryParam = categoryParam;
    }

    public List<Review> getReviewsToShow() {
        return reviewsToShow;
    }

    public void setReviewsToShow(List<Review> reviewsToShow) {
        this.reviewsToShow = reviewsToShow;
    }

    public List<SelectItem> getAllCountry() {
        List<SelectItem> li = new ArrayList<>();
        
        for(String localCountry : listC)
                li.add(new SelectItem(localCountry)); 
        
        return li;
    }

    public List<SelectItem> getAllRegions() {
        List<String> listR = sb.getAllStates();
        List<SelectItem> li = new ArrayList<>();
        
        for(String localRegion : listR){
            if(localRegion != null)
                li.add(new SelectItem(localRegion)); 
        }        
        
        return li;
    }

    public void setAllRegions(List<SelectItem> allRegions) {
        this.allRegions = allRegions;
    }
    
    public void setAllCountry(List<SelectItem> allCountry) {
        this.allCountry = allCountry;
    }

    public List<SelectItem> getAllCategory() {
        List<SelectItem> li = new ArrayList<>();
        
        for(String localCategory : listC)
                li.add(new SelectItem(localCategory)); 
                 
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
    
    /**
     * Starts the search given all the criterias
     * @return the view where the results will be displayed
     */
    public String search(){
        
        if(type.equals("tend"))
        {
            ltd =  sb.findTenderers(keyWord);
            return "/views/tendererSearch.xhtml?faces-redirect=true";
        }
        else
        {
            lc = sb.findContractors(keyWord,rating,country,category, order,region);  
            return "/views/contractorSearch.xhtml?faces-redirect=true";       
        }        
    }
    
    public List<String> completeQuery(String query) {
       
        List<String> results;
        
        if(type.equals("tend")){
            results = sb.findTendererBeginBy(query);
        }else{
            results = sb.findContractorBeginBy(query);
        }
        return results;   
    }
    
    public void valueChangeMethod(){
	search();
    }

}

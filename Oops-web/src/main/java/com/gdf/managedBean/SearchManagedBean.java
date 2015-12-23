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
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author nicolas
 */
@Named(value = "searchManagedBean")
@ApplicationScoped
public class SearchManagedBean implements Serializable {

    /**
     * Creates a new instance of SearchBean
    */
    
    private String keyWord;
    private String type;
    private int rating;
    private String country;
    private String category;
    private List<Contractor> lc;
    private List<Tenderer> ltd;
    private  List<SelectItem> allCountry;
    private  List<SelectItem> allCategory;
    
    @EJB
    SearchBean sb;
    
    public SearchManagedBean() {
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
        List<SelectItem> li =new ArrayList<>();
        li.add(new SelectItem(""));
        
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
        li.add(new SelectItem(""));
        
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
    
    public String search(){
        
        if(type.equals("tend"))
        {
            ltd =  sb.findTenderers(keyWord);
            return "/views/listTenderer.xhtml?faces-redirect=true";
        }
        else
        {
            lc = sb.findContractors(keyWord,rating,country,category);            
            return "/views/listContractor.xhtml?faces-redirect=true";       
        }        
    }
    

  
}

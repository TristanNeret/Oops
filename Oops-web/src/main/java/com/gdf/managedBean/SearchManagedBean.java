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
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

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
    private String selectedItem;
    private String selectedItemRate;
    private String selectedItemCountry;
    private String selectedItemCategory;
    private List<Contractor> lc;
    private List<Tenderer> ltd;
    
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

    public String getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getSelectedItemRate() {
        return selectedItemRate;
    }

    public void setSelectedItemRate(String selectedItemRate) {
        this.selectedItemRate = selectedItemRate;
    }

    public String getSelectedItemCountry() {
        return selectedItemCountry;
    }

    public void setSelectedItemCountry(String selectedItemCountry) {
        this.selectedItemCountry = selectedItemCountry;
    }

    public String getSelectedItemCategory() {
        return selectedItemCategory;
    }

    public void setSelectedItemCategory(String selectedItemCategory) {
        this.selectedItemCategory = selectedItemCategory;
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
        
        if(selectedItem.equals("tend")){
            
            if(keyWord == null)
                ltd = sb.findAllTenderer();
            else
                ltd =  sb.searchTendererByKeyWord(keyWord);
                
            System.out.println("SIZZE = " + ltd.size());
            return "/views/listTenderer.xhtml?faces-redirect=true";
        }
        else
        {
            if(keyWord == null)
                lc = sb.findAllContractor();
            else
                lc = sb.searchContractorByKeyWord(keyWord);    
                
            return "/views/listContractor.xhtml?faces-redirect=true";       
        }     
    }
    
    
}

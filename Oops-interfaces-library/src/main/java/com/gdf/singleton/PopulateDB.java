/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.singleton;

import java.util.List;
import javax.faces.model.SelectItem;

/**
 *
 * @author nicolas
 */
public interface PopulateDB {
    
    public List<String> getAllCountries();

    public List<String> getAllTown(String code);
    
    public String getRegion(String code);
    
    public List<SelectItem> getLegalForms();
    
    public boolean isATeamCompany(String teamCompany);
    
}

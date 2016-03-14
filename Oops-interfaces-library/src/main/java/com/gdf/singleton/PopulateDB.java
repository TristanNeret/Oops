/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.singleton;

import java.util.List;
import javax.faces.model.SelectItem;

/**
 * Populate the Database
 * @author nicolas
 */
public interface PopulateDB {
    
    /**
     * Return List of countries
     * @return List of countries
     */
    public List<String> getAllCountries();

    /**
     * Return a town list who match with a zip code
     * @param code zip code
     * @return town list
     */
    public List<String> getAllTown(String code);
    
    /**
     * Return a region who match with a zip code
     * @param code zip code
     * @return a region who match with a zip code
     */
    public String getRegion(String code);
    
    /**
     * Return a List of type of legal form
     * @return List<SelectItem> of type of legal forms
     */
    public List<SelectItem> getLegalForms();
    
    /**
     * @param teamCompany
     * @return true is the compagny is a team compagny
     */
    public boolean isATeamCompany(String teamCompany);
    
}

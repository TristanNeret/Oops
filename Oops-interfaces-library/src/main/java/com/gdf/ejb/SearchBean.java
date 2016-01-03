/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Contractor;
import com.gdf.persistence.Tenderer;
import java.util.List;
import javax.ejb.Remote;

/**
 * Interface supplying searching methods 
 * @author aziz
 */
@Remote
public interface SearchBean {
    
    /**
     * SearchBean a Contractor by his id
     * @param id the id of the Contractor
     * @return the Contractor identified by the id if any or null if he doesn't exist
     */
    public Contractor searchContractorById(long id);
    
    /**
     * Search contractors using an email
     * @param email the email
     * @return the list of contractors
     */
    public List<Contractor> searchContractorByEmail(String email);
    
    /**
     * Search tenderers by keyword
     * @param keyWord a keyword which could be present on the tenderer informations
     * @return the tenderer matching with the keyword or a list of all tenderers if keyword is empty
     */
    public List<Tenderer> findTenderers(String keyWord);
 
    /**
     * Search tenderers by keyword
     * @param keyWord a keyword which could be present on the tenderer informations
     * @return the list of tenderers
     */
    public List<Tenderer> searchTendererByKeyWord(String keyWord);
    
    /**
     * Get all the tenderers
     * @return all the tenderers
     */
    public List<Tenderer> findAllTenderer();
    
    /**
     * Search contractors by several criteria 
     * @param keyWord a keyword which could be present on the contractor informations
     * @param rating the rating of the contractor 
     * @param country the country of the contractor
     * @param category the category of service given by the contractor
     * @param order the order used to display the results
     * @return the list of contractors
     */
    public List<Contractor> findContractors(String keyWord,int rating, String country, String category, String order);
    
    /**
     * Get all countries
     * @return all countries
     */
    public List<String> getAllCountry();
    
    /**
     * Get all categories
     * @return all categories
     */
    public List<String> getAllCategory();
      
}

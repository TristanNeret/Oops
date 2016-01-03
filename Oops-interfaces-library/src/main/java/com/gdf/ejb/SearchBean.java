/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Contractor;
import com.gdf.persistence.Review;
import com.gdf.persistence.Tenderer;
import java.util.List;
import javax.ejb.Remote;

/**
 * Interface supplying searching methods 
 * @author aziz
 */
@Remote
public interface SearchBean {
    
    // CONTRACTOR
    
    /**
     * Search a Contractor by his id
     * @param id the id of the Contractor
     * @return the Contractor identified by the id if any or null if he doesn't exist
     */
    public Contractor searchContractorById(long id);
    
    
    /**
     * Search a Contractor by his login
     * @param login the login of the Contractor
     * @return the Contractor identified by the login if any or null if he doesn't exist
     */
    public Contractor searchContractorByLogin(String login);
    
    
    /**
     * Search a Contractor by his SIREN
     * @param siren the SIREN number of the Contractor
     * @return the Contractor ientified by the SIREN if any or null if he doesn't exist
     */
    public Contractor searchContractorBySiren(String siren);
    
    
    /**
     * Search a Contractor by his email
     * @param email the email of the Contractor
     * @return the Contractor identified by the email if any or null if doesn't exist
     */
    public List<Contractor> searchContractorByEmail(String email);
    
    // TENDERER
    
    /**
     * Search a Tenderer by his login
     * @param login the login of the Tenderer
     * @return the Tenderer identified by the login if any or null if he doesn't exist
     */
    public Tenderer searchTendererByLogin(String login);
    
    // REVIEW
    
    /**
     * Search waiting Reviews
     * @return list of waiting reviews
     */
    public List<Review> searchWaitingReviews();
    
    /**
     * Search accepted Reviews for a Contractor
     * @param id id of the Contractor
     * @return the accepted Reviews List of the Contractor
     */
    public List<Review> searchAcceptedReviews(long id);
    
}

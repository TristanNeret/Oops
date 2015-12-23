/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Contractor;
<<<<<<< Upstream, based on origin/develop
import com.gdf.persistence.Review;
=======
>>>>>>> 7fe1550 search Tenderer and Contractor V1
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
<<<<<<< HEAD
    
    
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
    
<<<<<<< Upstream, based on origin/develop
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
    
=======
    public List<Contractor> searchContractorByKeyWord(String keyWord);
    
=======
    public List<Contractor> searchContractorByEmail(String email);
    public List<Tenderer> findTenderers(String keyWord);
>>>>>>> bdc548c... Search feature V2
    public List<Tenderer> searchTendererByKeyWord(String keyWord);
    public List<Tenderer> findAllTenderer();
    public List<Contractor> findContractors(String keyWord,int rate, String country, String category);
    public List<String> getAllCountry();
    public List<String> getAllCategory();
      
>>>>>>> 7fe1550 search Tenderer and Contractor V1
}

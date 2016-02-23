/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Category;
import com.gdf.persistence.Contractor;
import com.gdf.persistence.Review;
import com.gdf.persistence.Tenderer;
import com.gdf.persistence.Moderator;
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

    /**
     * Search contractors by several criteria 
     * @param keyWord a keyword which could be present on the contractor informations
     * @param rating the rating of the contractor 
     * @param country the country of the contractor
     * @param category the category of service given by the contractor
     * @param order the order used to display the results
     * @param region
     * @return the list of contractors
     */
    public List<Contractor> findContractors(String keyWord,int rating, String country, String category, String order, String region);

    // TENDERER
    
    /**
     * Search a Tenderer by his id
     * @param id id of the Tenderer
     * @return the Tenderer identified by the id if any or null if he doesn't exist
     */
    public Tenderer searchTendererById(Long id);
    
    /**
     * Search a Tenderer by his login
     * @param login the login of the Tenderer
     * @return the Tenderer identified by the login if any or null if he doesn't exist
     */
    public Tenderer searchTendererByLogin(String login);

    // MODERATOR
    
    /**
     * Search a Moderator by his id
     * @param id id of the Moderator
     * @return the Moderator identified by the id if any or null if he doesn't exist
     */
    public Moderator searchModeratorById(Long id);
    
    public Moderator searchModeratorById(long id);
    
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
    public List<Review> searchAcceptedContratorReviews(long id);
    
    /**
     * Search accepted Reviews for a Tenderer
     * @param id id of the Tenderer
     * @return the accepted Reviews List of the Tenderer
     */
    public List<Review> searchAcceptedTendererReviews(long id);
    
    /**
     * Search three Reviews to show on main screen
     * @return list of three Reviews to show
     */
    public List<Review> getThreeReviewsToShow();

    // SEARCH
    
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
     * Get all the contractor
     * @return all the contractor
     */
    public List<Contractor> findAllContractor();
    
    /**
     * Return tenderers name begin by the query
     * @param query query the Tenderer name begin
     * @return list of tenderers name found
     */

    public List<String> findTendererBeginBy(String query);
    
    /**
     * Return tenderers name ending by the query
     * @param query query the Tenderer name ends
     * @return list of tenderers name found
     */
    public List<String> findContractorBeginBy(String query);

    // CATEGORY
    
    /**
     * Get a Category from it's id
     * @param idCategory id of the Category to return 
     * @return the Category of the id
     */
    public Category searchCategoryById(long idCategory);
    
    /**
     * Get all categories name
     * @return list of all categories name
     */
    public List<String> getAllCategory();  
    
    /**
     * Get all categories
     * @return list of all categories
     */
    public List<Category> getCategories();
   
    /**
     * Get all countries
     * @return all countries
     */
    public List<String> getAllCountry();

    /**
     * Get all states
     * @return all states
     */
    public List<String> getAllStates(); 
    
}

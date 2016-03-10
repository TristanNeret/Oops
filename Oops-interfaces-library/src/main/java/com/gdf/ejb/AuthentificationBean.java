/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.PasswordRequest;
import javax.ejb.Remote;

/**
 * AuthentificationBean
 * @author Tristan
 */
@Remote
public interface AuthentificationBean {
    
    /**
     * Test if Tenderer informations are corrects
     * @param userName Tenderer login
     * @param userPassword Tenderer password
     * @return Tenderer id if informations are corrects, or null
     */
    public Long isTendererValid(String userName, String userPassword);
    
    /**
     * Test if Contractor informations are corrects
     * @param userName Contractor login
     * @param userPassword Contractor password
     * @return Contractor id if informations are corrects, or null
     */
    public Long isContractorValid(String userName, String userPassword);
    
    /**
     * Test if Moderator informations are corrects
     * @param userName Moderator login
     * @param userPassword Moderator password
     * @return Moderator id if informations are corrects, or null
     */
    public Long isModeratorValid(String userName, String userPassword);
    
    /**
     * Send a link to reset the password of an account
     * @param email the email to send the link
     */
    public void passwordForgotten(String email);
    
    /**
     * Get a request identified by id
     * @param id the id generated for the request
     * @return the request
     */
    public PasswordRequest getPasswordRequest(String id);
}

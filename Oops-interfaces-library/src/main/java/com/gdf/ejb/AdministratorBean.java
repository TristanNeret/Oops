/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Category;
import javax.ejb.Remote;

/**
 * Interface supplying methods for the administrator  
 * @author aziz
 */
@Remote
public interface AdministratorBean {
    
    /**
     * Add a Category of Service given by the Contractor
     * @param category to add
     */
    public void addCategory(Category category);
}

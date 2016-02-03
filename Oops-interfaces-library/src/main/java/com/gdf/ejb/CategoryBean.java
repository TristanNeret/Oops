/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Category;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author user
 */
@Remote
public interface CategoryBean {

    public List<Category> findAll();
    public Category findById(long id); 
}

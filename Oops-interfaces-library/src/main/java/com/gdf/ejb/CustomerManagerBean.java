/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Contractor;
import javax.ejb.Remote;

/**
 *
 * @author nicolas
 */
@Remote
public interface CustomerManagerBean {
    
    public void register(Contractor c);

    public Contractor searchContractorById(long id);
    
}

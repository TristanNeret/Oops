/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Contractor;
import com.gdf.persistence.Service;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author bibo
 */
@Remote
public interface ServiceBean {
    
    public void deleteContractorService(Contractor contractor,Service service);
    public void register(Service service);
    public List<Service> findAll();
}

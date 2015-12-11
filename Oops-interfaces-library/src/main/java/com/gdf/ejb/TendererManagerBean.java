/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Tenderer; 
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author bibo
 */
@Remote
public interface TendererManagerBean {
    
    public void register(Tenderer t);
    public List<Tenderer> findAll();
    public List<Tenderer> findByLogin(String login);
    public List<Tenderer> findByEmail(String email);
    public void update(Tenderer t);
    public void delete(Tenderer t); 
}

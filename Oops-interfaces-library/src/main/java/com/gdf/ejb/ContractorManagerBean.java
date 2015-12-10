/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Contractor;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author bibo
 */
@Remote
public interface ContractorManagerBean {
    public void register(Contractor c);
    public List<Contractor> findAll();
    public List<Contractor> findByLogin(String login);
    public List<Contractor> findByEmail(String email);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Address;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author bibo
 */
@Remote
public interface AddressBean {
    public void register(Address address);
    public List<Address> findAll();
    public Address findById(long id);
    public List<Address> findByZip(String zip);
    public void update(Address address); 
    public void delete(Address address); 
}

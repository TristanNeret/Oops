/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Contractor;
import com.gdf.persistence.Tenderer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Class supplying registration methods to contractors and tenderers
 * @author aziz
 */
@Stateless
public class RegistrationBeanImpl implements RegistrationBean {

    /**
     * Injected EntityManager giving access to the database
     */
    @PersistenceContext(unitName = "OopsPU")
    private EntityManager em; 
    
    @Override
    public void register(Contractor c) {
        
        // Get current date 
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        c.setRegistrationDate(dateFormat.format(cal.getTime()));
        c.setUpdateDate(dateFormat.format(cal.getTime()));
        
        em.persist(c);
        
    }
    
    @Override
    public void register(Tenderer t) {
        
        // Get current date 
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        t.setRegistrationDate(dateFormat.format(cal.getTime()));
        t.setUpdateDate(dateFormat.format(cal.getTime()));
        
        em.persist(t);

    }

}

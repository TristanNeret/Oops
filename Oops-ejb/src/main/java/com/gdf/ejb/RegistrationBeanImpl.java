/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Contractor;
import com.gdf.persistence.Moderator;
import com.gdf.persistence.Service;
import com.gdf.persistence.Tenderer;
import java.io.Serializable;
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
public class RegistrationBeanImpl implements RegistrationBean, Serializable {

    /**
     * Injected EntityManager giving access to the database
     */
    @PersistenceContext(unitName = "OopsPU")
    private EntityManager em; 
    
    private final String dateMode = "dd/MM/yyyy HH:mm:ss";
    
    @Override
    public Long register(Contractor c) {
        
        // Get current date 
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat(dateMode);
        c.setRegistrationDate(dateFormat.format(cal.getTime()));
        c.setUpdateDate(dateFormat.format(cal.getTime()));
        
        em.persist(c);
 
        return em.merge(c).getId();
    }
    
    @Override
    public Long register(Tenderer t) {
        
        // Get current date 
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        t.setRegistrationDate(dateFormat.format(cal.getTime()));
        t.setUpdateDate(dateFormat.format(cal.getTime()));
        
        em.persist(t);

        return em.merge(t).getId();
    }
    
    
    @Override
    public void update(Contractor c) {
        
        // Get current date 
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        c.setUpdateDate(dateFormat.format(cal.getTime()));
        
        em.merge(c);
        
    }
    
    @Override
    public void update(Tenderer t) {
        
        // Get current date 
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        t.setUpdateDate(dateFormat.format(cal.getTime()));
        
        em.merge(t);

    }

    @Override
    public void register(Moderator m) {
        
        em.persist(m);
        
    }


    @Override
    public void update(Service s) {
        
        em.merge(s);     
    }


}

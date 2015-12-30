/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.listener;

import com.gdf.ejb.AdministratorBean;
import com.gdf.ejb.EvaluationBean;
import com.gdf.ejb.RegistrationBean;
import com.gdf.persistence.Address;
import com.gdf.persistence.Category;
import com.gdf.persistence.Contractor;
import com.gdf.persistence.LegalInformation;
import com.gdf.persistence.Review;
import com.gdf.persistence.ReviewState;
import com.gdf.persistence.Service;
import com.gdf.persistence.Tenderer;
import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author nicolas
 */
public class Listener implements ServletContextListener {

    @EJB
    private RegistrationBean registrationBean;
    @EJB
    private EvaluationBean evalBean;
    @EJB
    private AdministratorBean adminBean;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}

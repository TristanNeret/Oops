/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.listener;





import com.gdf.ejb.CustomerManagerBean;
import com.gdf.persistence.Address;
import com.gdf.persistence.Category;
import com.gdf.persistence.Contractor;
import com.gdf.persistence.LegalInformation;
import com.gdf.persistence.Service;
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
    CustomerManagerBean cm;
    
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
    
        Contractor contractor = new Contractor();
        contractor.setEmail("tim.cook@apple.com");
        contractor.setLegalForm("legal form");
        contractor.setLogo("src/logo");
        contractor.setNbEmployees(50);
        contractor.setPassword("appelForEver");
        contractor.setPhone("07 70 28 21 99");
        contractor.setRepresentatorFirstname("Tim");
        contractor.setRepresentatorLastname("Cook");
        contractor.setSocialReason("Appel");
        contractor.setTurnover(500000000);
        
        LegalInformation legalInformation = new LegalInformation();
        legalInformation.setInsurrance("Axa");
        legalInformation.setRcs("224255555555551");
        legalInformation.setSiret("5854117145417442");
        legalInformation.setSiren("4511111124");
        
        Category category = new Category();
        category.setImage("src/cat1");
        category.setKeywords(null);
        category.setName("computer science");
        
        Service service = new Service();
        service.setDescription("On produit des logiciel sur contre d'argent! ");
        service.setTitle("Réalisation et maintenance de logiciel");
        service.setPrice(100);
        service.setCategory(category);
        
        Address adress = new Address();
        adress.setCountry("USA");
        adress.setStreet("Apple Street");
        adress.setTown("Cupertino");
        adress.setZipCode(408);
        adress.setStreetNumber(1);
        
        contractor.addAddress(adress);
        contractor.setLegalInformation(legalInformation);
        contractor.addService(service);
        
        cm.register(contractor);
      
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}

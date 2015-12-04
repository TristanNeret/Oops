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
import com.gdf.persistence.Review;
import com.gdf.persistence.Service;
import com.gdf.persistence.Tenderer;
import java.sql.Date;
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
        contractor.setDescription("Nous somme apple, des petits chinois fabriquent nos produits");
        
        LegalInformation legalInformation = new LegalInformation();
        legalInformation.setInsurrance("Axa");
        legalInformation.setRcs("224255555555551");
        legalInformation.setSiret("5854117145417442");
        legalInformation.setSiren("4511111124");
        
        Category category = new Category();
        category.setImage("src/cat1");
        category.setKeywords(null);
        category.setName("computer science");
        cm.addCategory(category);
        
        Service service = new Service();
        service.setDescription("On produit des logiciel sur contre d'argent! ");
        service.setTitle("Réalisation et maintenance de logiciel");
        service.setPrice(100);
        //service.setCategory(category);
        
        Category category2 = new Category();
        category2.setImage("src/cat1");
        category2.setKeywords(null);
        category2.setName("Selling products");
        cm.addCategory(category2);
        
        Service service2 = new Service();
        service2.setDescription("We sell tech products");
        service2.setTitle("Selling");
        service2.setPrice(1000);
      //  service2.setCategory(category2);
        
        Address adress = new Address();
        adress.setCountry("USA");
        adress.setStreet("Apple Street");
        adress.setTown("Cupertino");
        adress.setZipCode(408);
        adress.setStreetNumber(1);
        
        contractor.setAddress(adress);
        contractor.setLegalInformation(legalInformation);
        contractor.addService(service);
        contractor.addService(service2);
        
        Review review = new Review();
        review.setAppreciation("Très professionel");
        review.setContent("Travail super !");
        review.setRating(1);
        review.setContractorAnswer("Je suis très content !");
        review.setDate(new Date(10));
               
        Tenderer tenderer = new Tenderer();
        tenderer.setEmail("oo@oo.om");
        tenderer.setLogin("Julie Johson");

                
        cm.register(tenderer); 
        cm.register(contractor);
        
        cm.addReview(review, tenderer, contractor);
        
        
    }
    
  

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}

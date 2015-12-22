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
import com.gdf.persistence.Moderator;
import com.gdf.persistence.Review;
import com.gdf.persistence.ReviewState;
import com.gdf.persistence.Service;
import com.gdf.persistence.Tenderer;
import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
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

        /*
        Contractor contractor = new Contractor();
        contractor.setEmail("tim.cook@apple.com");
        contractor.setLegalForm("legal form");
        contractor.setLogo("http://logok.org/wp-content/uploads/2014/04/Apple-logo-grey-880x625.png");
        contractor.setNbEmployees(50);
        contractor.setPassword("appelForEver");
        contractor.setPhone("07 70 28 21 99");
        contractor.setRepresentatorFirstname("Tim");
        contractor.setRepresentatorLastname("Cook");
        contractor.setSocialReason("Apple");
        contractor.setTurnover(500000000);
        contractor.setDescription(" Apple Inc. est une entreprise multinationale américaine qui conçoit et commercialise des produits électroniques grand public");
        contractor.setId((long)1);
        
        LegalInformation legalInformation = new LegalInformation();
        legalInformation.setInsurrance("Axa");
        legalInformation.setRcs("224255555555551");
        legalInformation.setSiret("5854117145417442");
        legalInformation.setSiren("4511111124");
        
        Category category = new Category();
        category.setImage("src/cat1");
        category.setKeywords(null);
        category.setName("Informatique");
        category.setId((long)1);
        adminBean.addCategory(category);
        
        Service service = new Service();
        service.setDescription("Nous developpons pour nos clients des projets informatiques multi-plateformes.");
        service.setTitle("Developpement");
        service.setPrice(100);
        service.setCategory(category);
        
        Category category2 = new Category();
        category2.setImage("img");
        category2.setKeywords(null);
        category2.setName("Télécomunication");
        category2.setId((long)2);
        adminBean.addCategory(category2);
        
        Service service2 = new Service();
        service2.setDescription("Nous installons et maintenons des infrastructures réseaux d'entreprise.");
        service2.setTitle("Administration réseaux");
        service2.setPrice(1000);
        service2.setCategory(category2);
        
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
        review.setContent("Travail réalisé dans les temps et de qualité !");
        review.setRating(4);
        review.setContractorAnswer("Merci et à bientôt !");
        review.setReviewState(ReviewState.ACCEPTED);
        
        String format = "dd/MM/yy H:mm:ss";
        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(format);
        java.util.Date date = new java.util.Date();
        review.setDate(formater.format(date));
        
        Tenderer tenderer = new Tenderer();
        tenderer.setEmail("oo@oo.om");
        tenderer.setLogin("Julie Johnson");
        tenderer.setId((long)1);
        
        Moderator moderator = new Moderator();
        moderator.setLogin("SuperModerator");
        moderator.setId((long)1);
 
        registrationBean.register(tenderer); 
        registrationBean.register(contractor);
        registrationBean.register(moderator);
        
        evalBean.addReview(tenderer, contractor, review);
        
     */           
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
    
}

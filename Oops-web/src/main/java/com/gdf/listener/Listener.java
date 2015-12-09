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
import javax.faces.context.FacesContext;
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
        Contractor contractor = new Contractor();
        contractor.setEmail("tim.cook@apple.com");
        contractor.setLegalForm("legal form");
        contractor.setLogo("http://1.bp.blogspot.com/-aMu3ZJ7WRo4/VdQUN1U6QvI/AAAAAAAACdk/zpRa_ayf4-4/s1600/aapl-logo.png");
        contractor.setNbEmployees(50);
        contractor.setPassword("appelForEver");
        contractor.setPhone("07 70 28 21 99");
        contractor.setRepresentatorFirstname("Tim");
        contractor.setRepresentatorLastname("Cook");
        contractor.setSocialReason("Apple");
        contractor.setTurnover(500000000);
        contractor.setDescription("Nous somme apple, des petits chinois fabriquent nos produits");
        contractor.setRating(3);
        contractor.setId((long)1);
        
        LegalInformation legalInformation = new LegalInformation();
        legalInformation.setInsurrance("Axa");
        legalInformation.setRcs("224255555555551");
        legalInformation.setSiret("5854117145417442");
        legalInformation.setSiren("4511111124");
        
        Category category = new Category();
        category.setImage("src/cat1");
        category.setKeywords(null);
        category.setName("computer science");
        category.setId((long)1);
        adminBean.addCategory(category);
        
        Service service = new Service();
        service.setDescription("On produit des logiciel sur contre d'argent! ");
        service.setTitle("Réalisation et maintenance de logiciel");
        service.setPrice(100);
        service.setCategory(category);
        
        Category category2 = new Category();
        category2.setImage("img");
        category2.setKeywords(null);
        category2.setName("Selling products");
        category2.setId((long)2);
        adminBean.addCategory(category2);
        
        Service service2 = new Service();
        service2.setDescription("We sell tech products");
        service2.setTitle("Selling");
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
        review.setContent("Travail super !");
        review.setRating(4);
        review.setContractorAnswer("Je suis très content !");
        review.setReviewState(ReviewState.ACCEPTED);
        
        Review review2 = new Review();
        review2.setAppreciation("pas Très professionel");
        review2.setContent("pas 1 Travail super !");
        review2.setRating(2);
        review2.setContractorAnswer("Je suis pas très content !");
        review2.setReviewState(ReviewState.ACCEPTED);
        
        Review review1 = new Review();
        review1.setAppreciation("Ras le bol");
        review1.setContent("merde alors!");
        review1.setRating(1);
        review1.setReviewState(ReviewState.NOT_ACCEPTED);
        
        String format = "dd/MM/yy H:mm:ss";
        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(format);
        java.util.Date date = new java.util.Date();
        review.setDate(formater.format(date));
        review1.setDate(formater.format(date));
        review2.setDate(formater.format(date));
        
        Tenderer tenderer = new Tenderer();
        tenderer.setEmail("oo@oo.om");
        tenderer.setLogin("Julie Johson");
        tenderer.setId((long)1);

                
        registrationBean.register(tenderer); 
        registrationBean.register(contractor);
        
        evalBean.addReview(tenderer, contractor, review);
        evalBean.addReview(tenderer, contractor, review1);
        evalBean.addReview(tenderer, contractor, review2);
    }
    
  

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}

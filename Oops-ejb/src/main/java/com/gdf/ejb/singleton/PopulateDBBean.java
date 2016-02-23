/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb.singleton;

import com.gdf.persistence.Address;
import com.gdf.persistence.Category;
import com.gdf.persistence.Contractor;
import com.gdf.persistence.LegalInformation;
import com.gdf.persistence.Moderator;
import com.gdf.persistence.Review;
import com.gdf.persistence.ReviewState;
import com.gdf.persistence.Service;
import com.gdf.persistence.Tenderer;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Class used to populate the database for selenium tests
 * @author aziz
 */
@Singleton
@Startup
public class PopulateDBBean {

    @PersistenceContext(unitName = "OopsPU")
    private EntityManager em;  
    
    /**
     * Populate the database for the selenium tests
     */
    @PostConstruct
    private void populateDatabase(){
        
        // REVIEWS
        
        Review review = new Review();
        review.setAppreciation("Très professionel");
        review.setContent("Travail réalisé dans les temps et de qualité !");
        review.setRating(4);
        review.setContractorAnswer("Merci et à bientôt !");
        review.setReviewState(ReviewState.ACCEPTED);
        
        Review review1 = new Review();
        review1.setAppreciation("Nul");
        review1.setContent("Vraiment pas intéressant, passez votre chemin...");
        review1.setRating(1);
        review1.setReviewState(ReviewState.DELIVERED);
        
        Review review2 = new Review();
        review2.setAppreciation("Génial !!");
        review2.setContent("Un travail merveilleux dans une super équipe ! !");
        review2.setRating(5);
        review2.setContractorAnswer(null);
        review2.setReviewState(ReviewState.DELIVERED);
        
        Review review3 = new Review();
        review3.setAppreciation("Pas mal..");
        review3.setContent("Résultat satisfaisant qui mériterait plus d'attention.");
        review3.setRating(3);
        review3.setReviewState(ReviewState.ACCEPTED);
        
        Review review4 = new Review();
        review4.setAppreciation("Parfait !");
        review4.setContent("Excellent travail ! Mon application est top !");
        review4.setRating(5);
        review4.setReviewState(ReviewState.ACCEPTED);
 
        String format = "dd/MM/yy H:mm:ss";
        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(format);
        java.util.Date date = new java.util.Date();
        review.setDate(formater.format(date));
        review1.setDate(formater.format(date));
        review2.setDate(formater.format(date));
        review3.setDate(formater.format(date));
        review4.setDate(formater.format(date));
        
        em.persist(review);
        em.persist(review1);
        em.persist(review2);
        em.persist(review3);
        em.persist(review4);
        
        // TENDERERS
        
        Tenderer tenderer = new Tenderer("Michmich", "michou.dupond@gmail.com", 
                "password", "Michel", "Dupond", "http://goodfilmguide.co.uk/wp-content/uploads/2010/04/avatar12.jpg", "3988937", "01/01/2016");
        tenderer.setId(1);
        tenderer.addReview(review);
        tenderer.addReview(review1);
        tenderer.addReview(review2);
        tenderer.updateNbReviews();
        em.persist(tenderer);
        
        tenderer = new Tenderer("Dede", "dede.legrand@gmail.com", "password", "Didier", "Legrand", 
                "", "9837937", "01/01/2016");
        em.persist(tenderer);
        
        // CONTRACTOR
        
        Category category = new Category("Informatique", "image"); 
        Contractor contractor = new Contractor("ITContractor", "contact@itcontractor.com", "password", "IT Contractor Inc.",
        "SA", "Nous sommes ITContractor nous vous offront different types de services IT,"
                + " n'hesitez pas a nous contacter pour plus d'informations.", 
                "937937820", "http://www.ut-capitole.fr/medias/photo/it-128_1413288947855-png", "ITContractor", "Representant", 100000, 100, 3,
        new Address(3, "beans street", 2648, "New York", "USA"), new LegalInformation("12345678900987", "123456789", "RCSITC123456789", "AssureTout"));
        Service service = new Service("Developpement Informatique", "Nous developpons vos projets informatique, sous forme d'applications "
                + "web et mobiles.", 500.0, contractor, category);
        contractor.setId(50);
        contractor.addService(service);
        contractor.addReview(review);
        contractor.addReview(review1);
        contractor.addReview(review2);
        contractor.addReview(review4);

        em.persist(category);
        em.persist(contractor);
        
        category = new Category("Batiment", "image"); 
        contractor = new Contractor("FranceBTP", "contact@francebtp.com", "password", "FranceBTP Sarl",
        "SARL", "Nous sommes FranceBTP nous vous offront different services dans le domaine de la construction et du batiment,"
                + " n'hesitez pas a nous contacter pour plus d'informations."
                , "937937820", "http://www.cibtp.fr/fileadmin/templates/portail/img/logo_charte_qualite.png", "FranceBTP", "Representant", 500000, 500, 2,
        new Address(3, "rue des haricots", 2648, "Paris", "France"), new LegalInformation("12345678903987", "126456789", "RCSITC113456789", "Assurancetoutrix"));
        service = new Service("Démolition", "Nous réalisons vos travaux de démolition", 100.0, contractor, category);
        contractor.addService(service); 
        contractor.addReview(review3);
        
        em.persist(category);
        em.persist(contractor);
        
        category = new Category("Divers", "image"); 
        em.persist(category);
        category = new Category("Restauration", "image"); 
        em.persist(category);
        category = new Category("Animation", "image"); 
        em.persist(category);
        
        Moderator moderator = new Moderator();
        moderator.setLogin("SuperModerator");
        moderator.setPassword("admin");
        moderator.setId((long)1);
        
        em.persist(moderator);
        
    }
    
}

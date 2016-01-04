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
import com.gdf.persistence.Service;
import com.gdf.persistence.Tenderer;
import java.util.Calendar;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Class used to populate the database for selenium tests,
 * uncomment @PostConstruct to execute the populateDatabase method on stratup
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
        
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        String format = "dd/MM/yy H:mm:ss";
        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(format);
        java.util.Date date = new java.util.Date();
        
        // TENDERERS
        
        Tenderer tenderer = new Tenderer("Michmich", "michou.dupond@gmail.com", 
                "password", "Michel", "Dupond", "avatar", "3988937", formater.format(date));
        em.persist(tenderer);
        
        tenderer = new Tenderer("Dede", "dede.legrand@gmail.com", "password", "Didier", "Legrand", 
                "avatar", "9837937", formater.format(date));
        em.persist(tenderer);
        
        // CONTRACTOR
        
        Category category = new Category("Informatique", "image"); 
        Contractor contractor = new Contractor("ITContractor", "contact@itcontractor.com", "password", "IT Contractor Inc.",
        "Société de service", "Nous sommes ITContractor nous vous offront different types de services IT,"
                + " n'hesitez pas a nous contacter pour plus d'informations.", 
                "937937820", "logo", "ITContractor", "Representant", 309293, 18, 3,
        new Address(3, "beans street", 2648, "New York", "USA"), new LegalInformation("siret", "siren", "rcs", "assurance"));
        Service service = new Service("Developpement Informatique", "Nous developpons vos projets informatique, sous forme d'applications "
                + "web et mobiles.", 500.0, contractor, category);
        contractor.addService(service);
        /**try {
            Query query = em.createNamedQuery("Category.findByName");
            query.setParameter(1, category.getName());
            Category c = (Category) query.getSingleResult();
            service.setCategory(c);
        } catch(NoResultException e){**/
        em.persist(category);
        //}
        em.persist(contractor);
        
        category = new Category("Batiment", "image"); 
        contractor = new Contractor("FranceBTP", "contact@francebtp.com", "password", "FranceBTP Sarl",
        "Societe", "Nous sommes FranceBTP nous vous offront different services dans le domaine de la construction et du batiment,"
                + " n'hesitez pas a nous contacter pour plus d'informations."
                , "937937820", "logo", "FranceBTP", "Representant", 309293, 18, 2,
        new Address(3, "rue des haricots", 2648, "Paris", "France"), new LegalInformation("siret", "siren", "rcs", "assurance"));
        service = new Service("Démolition", "Nous réalisons vos travaux de démolition", 100.0, contractor, category);
        contractor.addService(service);  
        /**try {
            Query query = em.createNamedQuery("Category.findByName");
            query.setParameter(1, category.getName());
            Category c = (Category) query.getSingleResult();
            service.setCategory(c);
        } catch(NoResultException e){**/
        em.persist(category);
        //}
        em.persist(contractor);
    }
}

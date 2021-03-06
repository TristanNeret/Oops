/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Category;
import com.gdf.persistence.Contractor;
import com.gdf.persistence.Moderator;
import com.gdf.persistence.PortfolioImage;
import com.gdf.persistence.Review;
import com.gdf.persistence.Service;
import com.gdf.persistence.Tenderer;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * Class supplying searching methods
 *
 * @author nicolas
 */
@Stateless
public class SearchBeanImpl implements SearchBean, Serializable {

    /**
     * Injected EntityManager giving access to the database
     */
    @PersistenceContext(unitName = "OopsPU")
    EntityManager em;

    // CONTRACTOR

    @Override
    public Contractor searchContractorById(long id) {

        Contractor contractor = em.find(Contractor.class, id);

        if (contractor != null) {
            //The lazy relationships must be traversed before exiting the scope of the JPA Session to avoid the Exception.
            contractor.getServices().size();
            contractor.getReviews().size();
        }
        return contractor;

    }

    @Override
    public Moderator searchModeratorById(long id) {

        Moderator moderator = em.find(Moderator.class, id);

        if (moderator != null) {
            //The lazy relationships must be traversed before exiting the scope of the JPA Session to avoid the Exception.
            moderator.getNotifications();

        }
        return moderator;

    }

    @Override
    public Contractor searchContractorByLogin(String login) {

        Query query = em.createNamedQuery("Contractor.findByLogin");
        query.setParameter(1, login);
        if (query.getResultList().isEmpty()) {

            return null;

        } else {

            return (Contractor) query.getSingleResult();

        }

    }
    
    @Override
    public Contractor searchContractorBySocialReason(String socialReason) {

        Query query = em.createNamedQuery("Contractor.findBySocialReason");
        query.setParameter(1, socialReason);
        if (query.getResultList().isEmpty()) {

            return null;

        } else {

            return (Contractor) query.getSingleResult();

        }

    }

    @Override
    public Contractor searchContractorBySiren(String siren) {

        Query query = em.createNamedQuery("Contractor.findBySiren");
        query.setParameter(1, siren);
        if (query.getResultList().isEmpty()) {

            return null;

        } else {

            return (Contractor) query.getSingleResult();

        }

    }
    
    @Override
    public Contractor searchContractorBySiret(String siret) {

        Query query = em.createNamedQuery("Contractor.findBySiret");
        query.setParameter(1, siret);
        if (query.getResultList().isEmpty()) {

            return null;

        } else {

            return (Contractor) query.getSingleResult();

        }

    }
    
    @Override
    public Contractor searchContractorByRcs(String rcs) {

        Query query = em.createNamedQuery("Contractor.findByRcs");
        query.setParameter(1, rcs);
        if (query.getResultList().isEmpty()) {

            return null;

        } else {

            return (Contractor) query.getSingleResult();

        }

    }
    
    @Override
    public Contractor searchContractorByEmail(String email) {
        
        Query query = em.createNamedQuery("Contractor.findByEmail");
        query.setParameter(1,email);
        if (query.getResultList().isEmpty()) {
            return null;
        } else {
            return (Contractor) query.getSingleResult();
        }
    }

    @Override
    public List<Contractor> findContractors(String keyWord, int rating, String country, String category, String order,String region) {

        boolean first = true;
        String requete;

        if (keyWord == null) {
            requete = " SELECT c FROM Contractor c ";
        } else {
            requete = " SELECT c FROM Contractor c WHERE (c.login LIKE :word OR c.email LIKE :word Or c.representatorFirstname LIKE :word OR c.representatorLastname LIKE :word Or c.socialReason LIKE :word Or c.phone LIKE :word) ";
            first = false;
        }

        if (rating != 0) {
            if (first) {
                requete = requete + " WHERE c.rating >= :rating ";
                first = false;
            } else {
                requete = requete + " AND c.rating >= :rating ";
            }
        }

        if (country != null) {
            if (first) {
                requete = requete + " WHERE ";
                first = false;
            } else {
                requete = requete + " AND ";
            }
            requete = requete + " c.address.country = :country ";
        }
        
        
        if (country != null && country.equals("France") && region != null) {
            if (first) {
                requete = requete + " WHERE ";
                first = false;
            } else {
                requete = requete + " AND ";
            }
            requete = requete + " c.address.region = :region ";
        }

        switch (order) {
            case "ALPHABETICAL":
                requete += " ORDER BY c.socialReason";
                break;
            case "RATINGS":
                requete += " ORDER BY c.rating DESC";
                break;
        }

        TypedQuery<Contractor> query;
        query = em.createQuery(requete, Contractor.class);

        if (rating != 0) {
            query.setParameter("rating", rating);
        }

        if (country != null) {
            query.setParameter("country", country);
        }
        
        if (region != null && country != null && country.equals("France")) {
            query.setParameter("region", region);
        }

        if (keyWord != null) {
            query.setParameter("word", "%" + keyWord + "%");
        }

        if (category != null) {
            return this.searchByCategories(query.getResultList(), category);
        } else {
            return query.getResultList();
        }
    }

    @Override
    public List<Tenderer> findTenderers(String keyWord) {
        if (keyWord == null) {
            return this.findAllTenderer();
        } else {
            return this.searchTendererByKeyWord(keyWord);
        }
    }

    @Override
    public List<Tenderer> searchTendererByKeyWord(String keyWord) {
        TypedQuery<Tenderer> query;
        query = em.createQuery("SELECT t FROM Tenderer t WHERE t.login LIKE :word OR t.email LIKE :word Or t.firstname LIKE :word Or t.lastname LIKE :word ORDER BY t.login ASC", Tenderer.class);
        query.setParameter("word", "%" + keyWord + "%");
        return query.getResultList();
    }

    @Override
    public List<Tenderer> findAllTenderer() {
        TypedQuery<Tenderer> query = em.createNamedQuery("Tenderer.findAll", Tenderer.class);
        return query.getResultList();
    }

    @Override
    public List<Contractor> findAllContractor() {
        TypedQuery<Contractor> query = em.createNamedQuery("Contractor.findAll", Contractor.class);
        return query.getResultList();
    }

    @Override
    public List<String> getAllCountry() {
        TypedQuery<String> query;
        query = em.createQuery("SELECT DISTINCT c.address.country FROM Contractor c ", String.class);
        return query.getResultList();
    }
    
    @Override
    public List<String> getAllStates() {
        TypedQuery<String> query;
        query = em.createQuery("SELECT DISTINCT c.address.region FROM Contractor c ", String.class);
        return query.getResultList();
    }

    /**
     * Get the contractors providing a category of service
     *
     * @param lc the list of contractors
     * @param category the catgory of service
     * @return the list of contractors providing the catgory of service
     */
    private List<Contractor> searchByCategories(List<Contractor> lc, String category) {

        Iterator<Contractor> iterator = lc.iterator();

        while (iterator.hasNext()) {

            Contractor contractor = iterator.next();
            List<Service> ls = contractor.getServices();
            boolean found = false;

            for (Service service : ls) {
                if (service.getCategory().getName().equals(category)) {
                    found = true;
                }
            }
            if (!found) {
                iterator.remove();
            }
        }
        return lc;
    }

    // TENDERER
    
    @Override
    public Tenderer searchTendererById(Long id) {

        Tenderer tenderer = em.find(Tenderer.class, id);

        if (tenderer != null) {
            // The lazy relationships must be traversed before exiting the scope of the JPA Session to avoid the Exception.
            tenderer.getReviews().size();
        }

        return tenderer;

    }

    @Override
    public Tenderer searchTendererByLogin(String login) {

        Query query = em.createNamedQuery("Tenderer.findByLogin");
        query.setParameter(1, login);
        if (query.getResultList().isEmpty()) {

            return null;

        } else {

            return (Tenderer) query.getSingleResult();
        }
    }

    // MODERATOR
    
    @Override
    public Moderator searchModeratorById(Long id) {

        Moderator moderator = em.find(Moderator.class, id);

        if (moderator != null) {
            // The lazy relationships must be traversed before exiting the scope of the JPA Session to avoid the Exception.
            moderator.getReviews().size();
        }
        return moderator;
    }

    // REVIEW
    
    @Override
    public List<Review> searchWaitingReviews() {
        Query query = em.createNamedQuery("Review.findWaitingReviews");
        return query.getResultList();
    }

    @Override
    public List<Review> searchAcceptedContratorReviews(long id) {
        Query query = em.createNamedQuery("Review.findAcceptedContractorReviews");
        query.setParameter(1, id);
        return query.getResultList();
    }

    @Override
    public List<Review> searchAcceptedTendererReviews(long id) {
        Query query = em.createNamedQuery("Review.findAcceptedTendererReviews");
        query.setParameter(1, id);
        return query.getResultList();
    }

    @Override
    public List<String> findTendererBeginBy(String first) {
        Query query;
        query = em.createNamedQuery("Tenderer.beginBy");
        query.setParameter(1, first + "%");
        return query.getResultList();
    }

    @Override
    public List<String> findContractorBeginBy(String first) {
        Query query;
        query = em.createNamedQuery("Contractor.beginBy");
        query.setParameter(1, first + "%");
        return query.getResultList();
    }

    // CATEGORY
    
    @Override
    public List<String> getAllCategory() {
        TypedQuery<String> query;
        query = em.createQuery("SELECT DISTINCT c.name FROM Category c ", String.class);
        return query.getResultList();
    }

    @Override
    public List<Category> getCategories() {
        TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c ", Category.class);
        return query.getResultList();
    }

    @Override
    public Category searchCategoryById(long idCategory) {
        return em.find(Category.class, idCategory);
    }

    @Override
    public List<Review> getThreeReviewsToShow() {    
        return em.createQuery("SELECT r FROM Review r WHERE r.reviewState=com.gdf.persistence.ReviewState.ACCEPTED ", Review.class).setFirstResult(0).setMaxResults(3).getResultList();
    }

    @Override
    public Tenderer searchTendererByEmail(String email) {
        
        Query query = em.createNamedQuery("Tenderer.findByEmail");
        query.setParameter(1,email);
        if (query.getResultList().isEmpty()) {
            return null;
        } else {
            return (Tenderer) query.getSingleResult();
        }
    }
    
    // PORTFOLIO

    @Override
    public PortfolioImage getPortfolioImageById(Long id) {
        
        return em.find(PortfolioImage.class, id);
    
    }
    
}

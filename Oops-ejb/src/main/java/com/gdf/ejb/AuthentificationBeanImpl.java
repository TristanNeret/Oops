/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb;

import com.gdf.persistence.Contractor;
import com.gdf.persistence.Moderator;
import com.gdf.persistence.PasswordRequest;
import com.gdf.persistence.Tenderer;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;

/**
 * AuthentificationBeanImpl Class which implements the authentification
 *
 * @author Tristan
 */
@Stateless
public class AuthentificationBeanImpl implements AuthentificationBean {

    /**
     * Injected EntityManager giving access to the database
     */
    @PersistenceContext(unitName = "OopsPU")
    private EntityManager em;

    @EJB
    MailServiceBean msb;

    @EJB
    SearchBean sb;

    /**
     * Validity time of a password request (24 hours)
     */
    private static final long INTERVAL = 24 * 60 * 60 * 1000;

    @Override
    public Long isTendererValid(String userName, String userPassword) {

        Query query = em.createNamedQuery("Tenderer.authentification", Tenderer.class);
        query.setParameter(1, userName);
        query.setParameter(2, this.encryptPassword(userPassword));

        if (query.getResultList().isEmpty()) {

            return null;

        } else {

            return (Long) query.getSingleResult();

        }

    }

    @Override
    public Long isContractorValid(String userName, String userPassword) {

        Query query = em.createNamedQuery("Contractor.authentification", Contractor.class);
        query.setParameter(1, userName);
        query.setParameter(2, this.encryptPassword(userPassword));

        if (query.getResultList().isEmpty()) {

            return null;

        } else {

            return (Long) query.getSingleResult();

        }

    }

    @Override
    public Long isModeratorValid(String userName, String userPassword) {

        Query query = em.createNamedQuery("Moderator.authentification", Moderator.class);
        query.setParameter(1, userName);
        query.setParameter(2, this.encryptPassword(userPassword));

        if (query.getResultList().isEmpty()) {

            return null;

        } else {

            return (Long) query.getSingleResult();

        }

    }

    /**
     * Return encrypted password
     *
     * @param password password to encrypt
     * @return the password encrypted
     */
    public String encryptPassword(String password) {

        ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
        passwordEncryptor.setAlgorithm("SHA-256");
        passwordEncryptor.setPlainDigest(true);

        return passwordEncryptor.encryptPassword(password);

    }

    /**
     * Send a link to reset the password of an account
     *
     * @param email the email to send the link
     */
    @Override
    public void passwordForgotten(String email) {
        // Get the system date and time.
        java.util.Date utilDate = new Date();
        // Convert it to java.sql.Date
        java.sql.Date date = new java.sql.Date(utilDate.getTime());
        String id = generateID(date + email); // Generate an id for the link

        // Check if there is already a request for this user
        PasswordRequest pr = em.find(PasswordRequest.class, id);
        if (pr == null) {
            // Enter the request in the database
            pr = new PasswordRequest();
            pr.setId(id);
            pr.setDate(date);
            pr.setUserEmail(email);
            em.persist(pr);

        } else {

            // Check the validity time of the password request
            java.util.Date now = new java.util.Date();
            long current = now.getTime();
            if (current - pr.getDate().getTime() >= INTERVAL) {

                // The time is elapsed, delete the former request and create a new one
                em.remove(pr);
                pr = new PasswordRequest();
                pr.setId(id);
                pr.setDate(date);
                pr.setUserEmail(email);
                em.persist(pr);

            } else {

                // The time is not elapsed, return the request 
                id = pr.getId();
            }
        }

        // Send an email with the link
        String userName = "";
        Tenderer tenderer = sb.searchTendererByEmail(email);
        if (tenderer != null) {
            userName = tenderer.getLogin();
        } else {
            Contractor contractor = sb.searchContractorByEmail(email);
            if (contractor != null) {
                userName = contractor.getLogin();
            }
        }

        msb.sendEmail(
                email,
                "Réinitialisation de votre mot de passe Oops",
                "Bonjour " + userName + " !\n\n"
                + "Vous avez demandé la réinitialisation de votre mot de passe.\n"
                + "Cliquez sur ce lien pour le réinitialiser :\n"
                + getAbsoluteApplicationUrl() + "/passwordForgotten.xhtml?id=" + id
                + "\n\nCordialement,\n"
                + "L'équipe Oops."
        );
    }

    private String generateID(String value) {
        ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
        passwordEncryptor.setAlgorithm("SHA-256");
        passwordEncryptor.setPlainDigest(true);

        return passwordEncryptor.encryptPassword(value);
    }

    /**
     * Get a request identified by id and delete it from the database
     *
     * @param id the id of the request
     * @return the request
     */
    @Override
    public PasswordRequest getPasswordRequest(String id) {
        PasswordRequest pr = em.find(PasswordRequest.class, id);
        if(pr != null)
            removePasswordRequest(pr);
        return pr;
    }

    private void removePasswordRequest(PasswordRequest pr) {
        em.remove(pr);
    }

    private String getAbsoluteApplicationUrl() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        URI newUri = null;
        try {
            URI uri = new URI(request.getRequestURL().toString());
            newUri = new URI(uri.getScheme(), null,
                    uri.getHost(),
                    uri.getPort(),
                    request.getContextPath(), null, null);
        } catch (URISyntaxException ex) {
            Logger.getLogger(AuthentificationBeanImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        if(newUri != null)
            return newUri.toString();
        else
            return "";
    }
}

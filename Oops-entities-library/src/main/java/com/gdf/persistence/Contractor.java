/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author aziz
 */
@Entity
public class Contractor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String password, socialReason, legalForm, description, phone, email, logo, representatorFirstname, representatorLastname;
    private int turnover, nbEmployees, rating;  
   
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Service> services = new ArrayList<>();

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Address address;
    
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LegalInformation legalInformation;
        
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Notification> notifications = new ArrayList<>();
  
    @OneToMany(mappedBy = "contractor",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Review> reviews = new ArrayList<>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSocialReason() {
        return socialReason;
    }

    public void setSocialReason(String socialReason) {
        this.socialReason = socialReason;
    }

    public String getLegalForm() {
        return legalForm;
    }

    public void setLegalForm(String legalForm) {
        this.legalForm = legalForm;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getRepresentatorFirstname() {
        return representatorFirstname;
    }

    public void setRepresentatorFirstname(String representatorFirstname) {
        this.representatorFirstname = representatorFirstname;
    }

    public String getRepresentatorLastname() {
        return representatorLastname;
    }

    public void setRepresentatorLastname(String representatorLastname) {
        this.representatorLastname = representatorLastname;
    }

    public int getTurnover() {
        return turnover;
    }

    public void setTurnover(int turnover) {
        this.turnover = turnover;
    }

    public int getNbEmployees() {
        return nbEmployees;
    }

    public void setNbEmployees(int nbEmployees) {
        this.nbEmployees = nbEmployees;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LegalInformation getLegalInformation() {
        return legalInformation;
    }

    public void setLegalInformation(LegalInformation legalInformation) {
        this.legalInformation = legalInformation;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
    
    public void addService(Service service){
       service.setContractor(this);
       this.services.add(service);
    }
    
    public void addReview(Review review){
        review.setContractor(this);
        this.reviews.add(review);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.socialReason);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Contractor other = (Contractor) obj;
        if (!Objects.equals(this.socialReason, other.socialReason)) {
            return false;
        }
        return true;
    }
}

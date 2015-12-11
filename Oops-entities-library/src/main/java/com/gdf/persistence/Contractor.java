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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;

/**
 *
 * @author aziz
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Contractor.findAll",
            query = "select c from Contractor c order by c.login ASC"),
    @NamedQuery(name = "Contractor.findByLogin",
            query = "select c from Contractor c where c.login=?1"),
    @NamedQuery(name = "Contractor.findByEmail",
            query = "select c from Contractor c where c.email=?1"),
})

public class Contractor implements Serializable {
    
    private static final String ENCRYPTION_ALGORITHM = "SHA-256";
    
    private static final long serialVersionUID = 1L;
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(unique=true)
    private String login;
    
    private String email, password, socialReason, legalForm, description, phone, logo, representatorFirstname, representatorLastname;
    private int turnover, nbEmployees, rating;  
   
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Service> services = new ArrayList<>();

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Address address;
    
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LegalInformation legalInformation;
        
    @OneToMany
    private List<Notification> notifications = new ArrayList<>();
  
    @OneToMany(mappedBy = "contractor",cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private List<Review> reviews = new ArrayList<>();
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = encryptPassword(password);
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
        return this.calculRate();
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
    
    public void addNotification(Notification n){
        this.notifications.add(n);
        n.setContractor(this);
    }

    public String getDescription() {
        return description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public int calculRate(){
        
        int res = 0;
        int nb = 0 ;
        
        for(Review r : reviews){
            if(r.getReviewState() == ReviewState.ACCEPTED)
               res =+ r.getRating(); nb++;
        }
        
        if(nb == 0 )
            return 0;
        else            
            return (int)(res/nb);
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    private String encryptPassword(String password){
        ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
        passwordEncryptor.setAlgorithm( ENCRYPTION_ALGORITHM );
        passwordEncryptor.setPlainDigest( true );
        return passwordEncryptor.encryptPassword(password);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.persistence;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;

/**
 * Contractor
 *
 * @author aziz
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Contractor.findAll", query = "select c from Contractor c order by c.email"),
    @NamedQuery(name = "Contractor.findByLogin", query = "select c from Contractor c where c.login=?1"),
    @NamedQuery(name = "Contractor.findByEmail", query = "select c from Contractor c where c.email=?1"),
    @NamedQuery(name = "Contractor.beginBy", query = "SELECT c.socialReason from Contractor c WHERE c.socialReason LIKE ?1"),
    @NamedQuery(name = "Contractor.authentification", query = "SELECT c.id from Contractor c WHERE c.login LIKE ?1 AND c.password LIKE ?2"),
    @NamedQuery(name = "Contractor.findBySocialReason", query = "select c from Contractor c where c.socialReason=?1"),
    @NamedQuery(name = "Contractor.findBySiren", query = "select c from Contractor c, LegalInformation l WHERE c.legalInformation.id = l.id AND l.siren=?1"),
    @NamedQuery(name = "Contractor.findBySiret", query = "select c from Contractor c, LegalInformation l WHERE c.legalInformation.id = l.id AND l.siret=?1"),
    @NamedQuery(name = "Contractor.findByRcs", query = "select c from Contractor c, LegalInformation l WHERE c.legalInformation.id = l.id AND l.rcs=?1"),})
public class Contractor implements Serializable {

    private static final String ENCRYPTION_ALGORITHM = "SHA-256";

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = "Veuillez saisir un login !")
    @Size(min = 5, message = "Le login doit contenir au moins 5 caractères !")
    @Column(unique = true)
    private String login;

    @NotNull(message = "Veuillez saisir un email !")
    private String email;

    @Lob
    @NotNull(message = "Veuillez saisir une description !")
    @Size(min = 30, message = "La description doit contenir au moins 30 caractères !")
    private String description;

    @NotNull(message = "Veuillez saisir un numéro de téléphone !")
    private String phone;

    @NotNull(message = "Veuillez saisir un logo !")
    private String logo;

    @NotNull(message = "Veuillez saisir une raison sociale !")
    @Size(min = 5, message = "La raison sociale doit contenir au moins 5 caractères !")
    private String socialReason;

    @NotNull(message = "Veuillez saisir une forme juridique !")
    @Size(min = 2, message = "La forme juridique doit contenir au moins 2 caractères !")
    private String legalForm;

    private int turnover, nbEmployees, rating;
    private String registrationDate, updateDate;

    @NotNull(message = "Veuillez saisir un mot de passe !")
    @Size(min = 6, message = "Le mot de passe doit contenir au moins 6 caractères !")
    private String password;

    @NotNull(message = "Veuillez saisir un prénom !")
    @Size(min = 3, message = "Le prénom doit contenir au moins 3 caractères !")
    private String representatorFirstname;

    @NotNull(message = "Veuillez saisir un nom !")
    @Size(min = 3, message = "Le nom doit contenir au moins 3 caractères !")
    private String representatorLastname;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Service> services = new ArrayList<>();

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Address address;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LegalInformation legalInformation;

    @OneToMany(fetch = EAGER)
    private List<Notification> notifications;

    @OneToMany(mappedBy = "contractor", fetch = EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Review> reviews = new ArrayList<>();
    
    @OneToMany(fetch = EAGER)
    private List<PortfolioImage> images;

    public static final String userCategory = "CONTRACTOR";

    /**
     * Default constructor
     */
    public Contractor() {
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        this.registrationDate = dateFormat.format(cal.getTime());
        
        this.images = new ArrayList<>();
        
    }

    /**
     * Contractor constructor
     * @param login
     * @param email
     * @param password
     * @param socialReason
     * @param legalForm
     * @param description
     * @param phone
     * @param logo
     * @param representatorFirstname
     * @param representatorLastname
     * @param turnover
     * @param nbEmployees
     * @param rating
     * @param address
     * @param legalInformation 
     */
    public Contractor(String login, String email, String password, String socialReason, String legalForm, String description, String phone, String logo, String representatorFirstname, String representatorLastname, int turnover, int nbEmployees, int rating, Address address, LegalInformation legalInformation) {
        
        this.login = login;
        this.email = email;
        this.password = this.encryptPassword(password);
        this.socialReason = socialReason;
        this.legalForm = legalForm;
        this.description = description;
        this.phone = phone;
        // Default image
        this.logo = "/resources/default/img/Contractor.png";
        if (logo != null) {
            if (!logo.equals("")) {
                this.logo = logo;
            }
        }
        this.logo = logo;
        this.representatorFirstname = representatorFirstname;
        this.representatorLastname = representatorLastname;
        this.turnover = turnover;
        this.nbEmployees = nbEmployees;
        this.rating = rating;
        this.address = address;
        this.legalInformation = legalInformation;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        this.registrationDate = dateFormat.format(cal.getTime());
        this.images = new ArrayList<>();
        
    }

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

    public void removeService(Service service) {
        this.services.remove(service);
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
        List<Notification> returnNotifications = new ArrayList<>();
        for (Notification notification : this.notifications) {
            if (notification.getReview() != null) {
                if (notification.getReview().isReviewEnabled()) {
                    returnNotifications.add(notification);
                }
            } else {
                returnNotifications.add(notification);
            }
        }
        return returnNotifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<Review> getReviews() {
        List<Review> returnReviews = new ArrayList<>();
        for (Review review : this.reviews) {
            if (review.isReviewEnabled()) {
                returnReviews.add(review);
            }
        }
        return returnReviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void addService(Service service) {
        service.setContractor(this);
        this.services.add(service);
    }

    public void addReview(Review review) {
        review.setContractor(this);
        this.reviews.add(review);
    }

    public void removeReview(Review r) {
        int i = 0;
        while (i < this.reviews.size() && !this.reviews.get(i).getId().equals(r.getId())) {
            i++;
        }
        this.reviews.get(i).setContractor(null);
        this.reviews.remove(i);
    }

    public void addNotification(Notification n) {
        this.notifications.add(n);
        n.setContractor(this);
    }

    public void removeNotificationByReviewId(long reviewId) {
        for (ListIterator<Notification> iter = this.notifications.listIterator(); iter.hasNext();) {
            Notification n = iter.next();
            if (n.getReview().getId().equals(reviewId)) {
                iter.remove();
            }
        }
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

    public int calculRate() {
  
        int rate = 0;
        for (Review r : reviews) {
            if (r.isReviewEnabled()) {
                rate += r.getRating();
            }
        }
        
        if(reviews.size() == 0)
            return 0;
        
        rate = rate / reviews.size();
        return rate;
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

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public List<PortfolioImage> getImages() {
        return images;
    }

    public void setImages(List<PortfolioImage> images) {
        this.images = images;
    }

    public void removeImage(PortfolioImage image) {
        this.images.remove(image);
    }
    
    private String encryptPassword(String password) {
        ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
        passwordEncryptor.setAlgorithm(ENCRYPTION_ALGORITHM);
        passwordEncryptor.setPlainDigest(true);
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

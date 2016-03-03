/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.persistence;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;

/**
 * Tenderer
 *
 * @author aziz
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Tenderer.findAll", query = "SELECT t FROM Tenderer t ORDER BY t.login ASC"),
    @NamedQuery(name = "Tenderer.findByLogin", query = "SELECT t FROM Tenderer t WHERE t.login=?1"),
    @NamedQuery(name = "Tenderer.findByEmail", query = "SELECT t FROM Tenderer t WHERE t.email=?1"),
    @NamedQuery(name = "Tenderer.beginBy", query = "SELECT t.login FROM Tenderer t WHERE t.login LIKE ?1"),
    @NamedQuery(name= "Tenderer.authentification", query = "SELECT t.id from Tenderer t WHERE t.login LIKE ?1 AND t.password LIKE ?2")  
})
public class Tenderer implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String ENCRYPTION_ALGORITHM = "SHA-256";

    @NotNull(message = "Veuillez saisir un login")
    @Size(min = 4, max = 20, message = "Votre login doit contenir entre 4 et 20 caractères.")
    @Column(unique = true)
    private String login;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = "Veuillez saisir un email")
    private String email;

    @NotNull(message = "Veuillez saisir un mot de passe")
    @Size(min = 6, message = "Le mot de passe doit contenir au moins 6 caractères")
    private String password;

    @NotNull(message = "Veuillez saisir un prénom")
    @Size(min = 3, message = "Le prénom doit contenir au moins 3 caractères")
    private String firstname;

    @NotNull(message = "Veuillez saisir un nom")
    @Size(min = 3, message = "Le nom doit contenir au moins 3 caractères")
    private String lastname;

    private String avatar;
    
    private String phone;

    private String registrationDate, updateDate;

    @OneToMany(mappedBy = "tenderer", fetch = EAGER, cascade = {CascadeType.ALL})
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(fetch = EAGER)
    private List<Notification> notifications = new ArrayList<>();

    public static final String userCategory = "TENDERER";

    private int nbReviews = 0; // number of reviews given by the tenderer and validated by a moderator

    public Tenderer() {

    }

    public Tenderer(String login, String email, String password, String firstname, String lastname, String avatar, String phone, String registrationDate) {
        this.login = login;
        this.email = email;
        this.password = this.encryptPassword(password);
        this.firstname = firstname;
        this.lastname = lastname;
        this.avatar = avatar;
        this.phone = phone;
        this.registrationDate = registrationDate;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = encryptPassword(password);
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public void addReview(Review r) {
        this.reviews.add(r);
        r.setTenderer(this);
    }

    public void removeReview(Review r) {
        int i = 0;
        while (i < this.reviews.size() && !this.reviews.get(i).getId().equals(r.getId())) {
            i++;
        }
        this.reviews.get(i).setTenderer(null);
        this.reviews.remove(i);

        // update the number of reviews given by the tenderer and accepted by moderator
        updateNbReviews();
    }

    public void addNotification(Notification n) {
        this.notifications.add(n);
        n.setTenderer(this);
    }

    public void removeNotificationByReviewId(long reviewId) {
        for (ListIterator<Notification> iter = this.notifications.listIterator(); iter.hasNext();) {
            Notification n = iter.next();
            if (n.getReview().getId().equals(reviewId)) {
                iter.remove();
            }
        }
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
        hash = 53 * hash + Objects.hashCode(this.login);
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
        final Tenderer other = (Tenderer) obj;
        return Objects.equals(this.login, other.login);
    }

    /**
     * Get the number of reviews given by the tenderer and accepted by moderator
     *
     * @return the number of reviews given by the tenderer and accepted by
     * moderator
     */
    public int getNbReviews() {
        return nbReviews;
    }

    public void setNbReviews(int nbReviews) {
        this.nbReviews = nbReviews;
    }

    /**
     * Update the number of reviews given by the tenderer and accepted by
     * moderator
     */
    public void updateNbReviews() {
        this.nbReviews = 0;
        for (Review r : reviews) {
            if (r.getReviewState().equals(ReviewState.ACCEPTED) && r.isReviewEnabled()) {
                this.nbReviews += 1;
            }
        }
    }
}

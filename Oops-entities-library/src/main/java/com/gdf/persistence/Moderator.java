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
import javax.persistence.Column;
import javax.persistence.Entity;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;

/**
 * Moderator
 * @author aziz
 */
@Entity
@NamedQueries({
    @NamedQuery(name= "Moderator.authentification", query = "SELECT m.id from Moderator m WHERE m.login LIKE ?1 AND m.password LIKE ?2")  
})
public class Moderator implements Serializable {
    
    private static final String ENCRYPTION_ALGORITHM = "SHA-256";
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    private String login;
    private String password;
    
    @OneToMany(mappedBy = "moderator", fetch = EAGER)
    private List<ModeratorReview> reviews = new ArrayList<>();
    
    @OneToMany(fetch = EAGER)
    private List<Notification> notifications = new ArrayList<>();
    
    public static final String userCategory = "MODERATOR";
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<ModeratorReview> getReviews() {
        List<ModeratorReview> returnModeratorReviews = new ArrayList<>();
        for (ModeratorReview review : this.reviews) {
            if (review.getReview().isReviewEnabled()) {
                returnModeratorReviews.add(review);
            }
        }
        return returnModeratorReviews;
    }

    public void setReviews(List<ModeratorReview> reviews) {
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
    
    public void addNotification(Notification n){
        this.notifications.add(n);
        n.setModerator(this);
    }
    
    public void removeNotificationByReviewId(long reviewId) {
        for (ListIterator<Notification> iter = this.notifications.listIterator(); iter.hasNext(); ) {
            Notification n = iter.next();
            if (n.getReview().getId().equals(reviewId)) {
                iter.remove();
            }
        }
    }
    
    private String encryptPassword(String password){
        ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
        passwordEncryptor.setAlgorithm( ENCRYPTION_ALGORITHM );
        passwordEncryptor.setPlainDigest( true );
        return passwordEncryptor.encryptPassword(password);
    }
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.login);
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
        final Moderator other = (Moderator) obj;
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        return true;
    }
}

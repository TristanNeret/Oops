/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.persistence;

import java.io.Serializable;
import java.sql.Date;
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
import org.jasypt.util.password.ConfigurablePasswordEncryptor;

/**
 *
 * @author aziz
 */

@Entity
@NamedQueries({
    @NamedQuery(name = "Tenderer.findAll", query = "SELECT t FROM Tenderer t ORDER BY t.login ASC"),
    @NamedQuery(name = "Tenderer.findByLogin", query = "SELECT t FROM Tenderer t WHERE t.login=?1"),
    @NamedQuery(name = "Tenderer.findByEmail", query = "SELECT t FROM Tenderer t WHERE t.email=?1"),
    @NamedQuery(name = "Tenderer.findBylogpass", query = "SELECT t FROM Tenderer t WHERE t.login=?1 and t.password=?2"),
    @NamedQuery(name = "Tenderer.findByphone", query = "SELECT t FROM Tenderer t WHERE t.phone=?1"),
    @NamedQuery(name = "Tenderer.findTenderBymail", query = "SELECT t FROM Tenderer t WHERE t.email=?1"),
    @NamedQuery(name= "Tenderer.beginBy", query = "SELECT t.login from Tenderer t WHERE t.login LIKE ?1")    
})

public class Tenderer implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String ENCRYPTION_ALGORITHM = "SHA-256";

    @Column(unique=true)
    private String login;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String email, password, firstname, lastname, avatar, phone;
    private String registrationDate, updateDate;
  
    @OneToMany(mappedBy = "tenderer", cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private List<Review> reviews = new ArrayList<>();
    
    @OneToMany
    private List<Notification> notifications = new ArrayList<>();
    
    public static final String userCategory = "TENDERER";
    
    public Tenderer() {
        
    }
    
    public Tenderer(String login, String email, String password, String firstname, String lastname, String avatar, String phone, String registrationDate) {
        this.login = login;
        this.email = email;
        this.password = password;
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
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Notification> getNotifications() {
        return notifications;
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
    
    public void addReview(Review r){
        this.reviews.add(r);
        r.setTenderer(this);
    }
    
    public void addNotification(Notification n){
        this.notifications.add(n);
        n.setTenderer(this);
    }
    
    public String encryptPassword(String password){
        ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
        passwordEncryptor.setAlgorithm( ENCRYPTION_ALGORITHM );
        passwordEncryptor.setPlainDigest( true );
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
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        return true;
    }
    
}

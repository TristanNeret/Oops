/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.persistence;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Address
 * @author aziz
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Address.findAll", query = "SELECT adr FROM Address adr"),
    @NamedQuery(name = "Address.findById", query = "SELECT adr FROM Address adr WHERE adr.id=?1"),
    @NamedQuery(name = "Address.findByZip", query = "SELECT adr FROM Address adr WHERE adr.zipCode=?1")
})
public class Address implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int streetNumber, zipCode;
    
    @Size( min = 3, message = "La rue doit contenir au moins 3 caractères" )
    @NotNull( message = "Veuillez saisir une rue" )
    private String street;
    @Size( min = 3, message = "La ville doit contenir au moins 3 caractères" )
    @NotNull( message = "Veuillez saisir une ville" )
    private String town;
    @Size( min = 3, message = "Le pays doit contenir au moins 3 caractères" )
    @NotNull( message = "Veuillez saisir un pays" )
    private String country;
    private String region;

    public Address() {
    }
    
    public Address(int streetNumber, String street, int zipCode, String town, String country) {
        this.streetNumber = streetNumber;
        this.zipCode = zipCode;
        this.street = street;
        this.town = town;
        this.country = country;
    }

    public Address(int streetNumber,String street, int zipCode,  String town, String country, String region) {
        this.streetNumber = streetNumber;
        this.zipCode = zipCode;
        this.street = street;
        this.town = town;
        this.country = country;
        this.region = region;
    }
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
    
   
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.streetNumber;
        hash = 97 * hash + this.zipCode;
        hash = 97 * hash + Objects.hashCode(this.street);
        hash = 97 * hash + Objects.hashCode(this.town);
        hash = 97 * hash + Objects.hashCode(this.country);
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
        final Address other = (Address) obj;
        if (this.streetNumber != other.streetNumber) {
            return false;
        }
        if (this.zipCode != other.zipCode) {
            return false;
        }
        if (!Objects.equals(this.street, other.street)) {
            return false;
        }
        if (!Objects.equals(this.town, other.town)) {
            return false;
        }
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return streetNumber+" "+street+" "+zipCode+" "+town+" "+country;
    } 
    
}

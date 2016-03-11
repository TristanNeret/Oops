/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.persistence;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * PortfolioImage
 * @author Tristan
 */
@Entity
public class PortfolioImage implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Long contractorID;
    
    @Lob
    @Basic(fetch=FetchType.EAGER) 
    @Column(length=10000000)
    private byte[] image;
    
    private String name;
    
    private String description;
    
    private String addDate;

    /**
     * Default constructor
     */
    public PortfolioImage() { 
    
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        this.addDate = dateFormat.format(cal.getTime());
        
    }

    /**
     * PortfolioImage constructor
     * @param contractorID id of the Contractor
     * @param image byte[] representation of the image
     * @param name image name
     * @param description description of the image
     */
    public PortfolioImage(Long contractorID, byte[] image, String name, String description) {
        
        this.contractorID = contractorID;
        this.image = image;
        this.name = name;
        this.description = description;
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        this.addDate = dateFormat.format(cal.getTime());
        
    }
    
    // GETTER/SETTER

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContractorID() {
        return contractorID;
    }

    public void setContractorID(Long contractorID) {
        this.contractorID = contractorID;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }
    
}
    
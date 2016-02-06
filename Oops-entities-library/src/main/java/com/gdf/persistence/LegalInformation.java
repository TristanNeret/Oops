/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.persistence;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * LegalInformation
 * @author aziz
 */
@Entity
public class LegalInformation implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull( message = "Veuillez saisir un numéro de SIRET" )
    private String siret;
    
    @NotNull( message = "Veuillez saisir un numéro de SIREN" )
    private String siren;
    
    @NotNull( message = "Veuillez saisir un numéro de RCS" )
    private String rcs;
    
    @NotNull( message = "Veuillez saisir une assurance" )
    private String insurrance;

    public LegalInformation() {
    }

    public LegalInformation(String siret, String siren, String rcs, String insurrance) {
        this.siret = siret;
        this.siren = siren;
        this.rcs = rcs;
        this.insurrance = insurrance;
    }
    
    public Long getId() {
        return id;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public String getSiren() {
        return siren;
    }

    public void setSiren(String siren) {
        this.siren = siren;
    }

    public String getRcs() {
        return rcs;
    }

    public void setRcs(String rcs) {
        this.rcs = rcs;
    }

    public String getInsurrance() {
        return insurrance;
    }

    public void setInsurrance(String insurrance) {
        this.insurrance = insurrance;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

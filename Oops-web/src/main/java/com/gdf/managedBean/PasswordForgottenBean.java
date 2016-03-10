/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import com.gdf.ejb.ContractorManagerBean;
import com.gdf.ejb.SearchBean;
import com.gdf.ejb.TendererManagerBean;
import com.gdf.persistence.Contractor;
import com.gdf.persistence.PasswordRequest;
import com.gdf.persistence.Tenderer;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author aziz
 */
@Named
@ViewScoped
public class PasswordForgottenBean implements Serializable {

    @EJB
    com.gdf.ejb.AuthentificationBean ab;

    @EJB
    SearchBean sb;

    @EJB
    TendererManagerBean tmb;

    @EJB
    ContractorManagerBean cmb;

    @NotNull(message = "Veuillez saisir un email !")
    private String email;

    private String requestID;

    private Tenderer tenderer;
    private Contractor contractor;

    @NotNull(message = "Veuillez saisir un de mot de passe !")
    @Size(min = 5, message = "Le mot de passe doit contenir au moins 6 caractères !")
    private String password;

    @NotNull(message = "Veuillez saisir une confirmation de mot de passe !")
    private String passwordConfirm;

    private String message = "";

    @PostConstruct
    public void init() {
        if (requestID != null) {
            requestID = requestID.replaceAll(" ", "+");
            PasswordRequest pr = ab.getPasswordRequest(requestID);
            if (pr != null) {

                tenderer = sb.searchTendererByEmail(pr.getUserEmail());
                if (tenderer == null) {
                    contractor = sb.searchContractorByEmail(pr.getUserEmail());
                }

            } else {

                // Wrong password request id
                message = "Le lien de réinitialisation est invalide ou expiré !";
            }
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void passwordForgotten() {

        if (requestID == null) {

            // send request
            ab.passwordForgotten(email);
            message = "Un lien pour réinitialiser votre mot de passe vient de vous être envoyé !";
        }
    }

    public void setNewPassword() {

        if (tenderer != null) {

            tenderer.setPassword(password);
            tmb.update(tenderer);

        } else if (contractor != null) {

            contractor.setPassword(password);
            cmb.update(contractor);
        }

        message = "Votre mot de passe a été mis a jour avec succès !";
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    public Tenderer getTenderer() {
        return tenderer;
    }

    public void setTenderer(Tenderer tenderer) {
        this.tenderer = tenderer;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

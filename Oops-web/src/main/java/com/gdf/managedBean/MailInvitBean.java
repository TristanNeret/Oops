/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import com.gdf.ejb.MailServiceBean;
import com.gdf.ejb.SearchBean;
import com.gdf.persistence.Contractor;
import com.gdf.persistence.Tenderer;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author chris
 */
@Named
@ViewScoped
public class MailInvitBean implements Serializable {

    @EJB
    private MailServiceBean msb;
    @EJB
    private SearchBean search;

    private String message;
    private String to;
    private String subject;

    private Tenderer currentTenderer;
    private Contractor currentContractor;

    /**
     * Creates a new instance of MailInvitBean
     */
    public MailInvitBean() {
    }

    @PostConstruct
    private void init() {
        // Temporary used to connect an user and kwow what user it is
        FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("userID", "1");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userCategory", Tenderer.userCategory);
        String stringId = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userID");

        // WTF ? la fonction search ne trouve pas le prestataire alors que le soumissionnaire oui
        currentContractor = search.searchContractorById(new Long(stringId));
        currentTenderer = search.searchTendererById(new Long(stringId));
        this.fillMessage();
    }

    private void fillMessage() {

        String userCategory = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userCategory");
        String from = "";
        String body = "";
        if (currentTenderer != null && userCategory.equals("TENDERER")) {
            from = currentTenderer.getFirstname() + " " + currentTenderer.getLastname();
            body = " souhaite que vous deveniez soumissionnaire sur Oops afin que vous poussiez donner votre avis sur notre annuaire de prestations."
                    + "\nhttp://www.oopsgdf.fr\n\n";
        }
        if (currentContractor != null && userCategory.equals("CONTRACTOR")) {
            from = currentContractor.getRepresentatorFirstname() + " "
                    + currentContractor.getRepresentatorLastname() + " de "
                    + currentContractor.getSocialReason();
            body = " souhaite que vous deveniez soumissionnaire sur Oops afin que vous poussiez donner votre avis sur l'une de ses prestations."
                    + "\nhttp://www.oopsgdf.fr\n\n";
        }
        subject = from + " vous invite sur Oops";
        message = "Bonjour,\n\n"
                + from
                + body
                + "Cordialement,\nL'équipe Oops";
    }

    public void sendMail() {
        msb.sendEmail(to, subject, message);
        // reset fields
        to = null;
        this.fillMessage();
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Message envoyé!",  "") );
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}

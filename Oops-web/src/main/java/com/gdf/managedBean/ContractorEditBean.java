/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import com.gdf.ejb.AddressBean;
import com.gdf.ejb.ContractorManagerBean;
import com.gdf.ejb.LegalInformationBean;
import com.gdf.ejb.RegistrationBean;
import com.gdf.ejb.SearchBean;
import com.gdf.persistence.Address;
import com.gdf.persistence.Contractor;
import com.gdf.persistence.LegalInformation;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

/**
 *
 * @author bibo
 */
@Named(value = "contractorEditBean")
@RequestScoped
public class ContractorEditBean{
    
    @EJB
    private SearchBean searchBean;
    
    
    @EJB
    private RegistrationBean rb;
    
    
    @EJB
    private LegalInformationBean legalinformationBean;
    
    @EJB
    private AddressBean addressBean;
    
    private Contractor contractor;
    private LegalInformation legalinformation;
    private Address address; 
    
    
    private String login;
    private String password;
    private String cpassword;
    private String lastname;
    private String firstname;
    private String email;
    private String phone;
    private String socialReason;
    private String legalForm;
    private int turnover;
    private int nbEmployees;
    private String siren;
    private String siret;
    private String rcs;
    private String insurrance;
    private boolean editable;
    
    /**
     * Creates a new instance of ContractorEditBean
     */
    
    
    
    public ContractorEditBean() {
    }
    
   
   
    @PostConstruct
    public void init() {
        
        setContractor(searchBean.searchContractorById(1));
        setLegalinformation(legalinformationBean.findById(2));
    }
    
    
    
    public String update_compte() {

        contractor.setPassword(getPassword());
        contractor.setRepresentatorFirstname(getFirstname());
        contractor.setRepresentatorLastname(getLastname());
        contractor.setEmail(getEmail());
        contractor.setPhone(getPhone());
        this.rb.update(contractor);
        return "modified";
    }
    
    public String update_info(){
        contractor.setSocialReason(getSocialReason());
        contractor.setLegalForm(getLegalForm());
        contractor.setTurnover(getTurnover());
        contractor.setNbEmployees(getNbEmployees());
        legalinformation.setInsurrance(getInsurrance());
        legalinformation.setSiren(getSiren());
        legalinformation.setSiret(getSiret());
        contractor.setLegalInformation(legalinformation);
        System.out.println("saved");
        this.rb.update(contractor);
        this.legalinformationBean.update(legalinformation);
        System.out.println("saved");
        return "modified";
    }
    
    public String update_address(){
        this.addressBean.update(address);
        return "";
    }
    
    
    
    
    
    /*
        Contractor setters and getters
    */
    
    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    public String getLogin() {
        return contractor.getLogin();
    }

    /*public void setLogin(String login) {
        this.login = login;
    }*/

    public String getPassword() {
        return contractor.getPassword();
    }

    public void setPassword(String password) {
        this.password=password;
    }

    public String getCpassword() {
        return cpassword;
    }

    public void setCpassword(String cpassword) {
        this.cpassword = cpassword;
    }

    public String getLastname() {
        return contractor.getRepresentatorLastname();
    }

    public void setLastname(String lastname) {
        this.contractor.setRepresentatorLastname(lastname);
    }

    public String getFirstname() {
        return contractor.getRepresentatorLastname();
    }

    public void setFirstname(String firstname) {
        this.contractor.setRepresentatorFirstname(firstname);
    }

    public String getEmail() {
        return contractor.getEmail();
    }

    public void setEmail(String email) {
        this.contractor.setEmail(email);
    }

    public String getPhone() {
        return contractor.getPhone();
    }

    public void setPhone(String phone) {
        this.contractor.setPhone(phone);
    }

    public String getSocialReason() {
        return socialReason;
    }

    public void setSocialReason(String socialReason) {
        this.contractor.setSocialReason(socialReason);
    }

    public String getLegalForm() {
        return legalForm;
    }

    public void setLegalForm(String legalForm) {
        this.contractor.setLegalForm(legalForm);
    }

    public int getTurnover() {
        return contractor.getTurnover();
    }

    public void setTurnover(int turnover) {
        this.contractor.setTurnover(turnover);
    }

    public int getNbEmployees() {
        return contractor.getNbEmployees();
    }

    public void setNbEmployees(int nbEmployees) {
        this.contractor.setNbEmployees(nbEmployees);
    }

    /*
        Legal information setters and getters
    */
      
    public LegalInformation getLegalinformation() {
        return legalinformation;
    }

    public void setLegalinformation(LegalInformation legalinformation) {
        this.legalinformation = legalinformation;
    }
    
    public String getSiret() {
        return legalinformation.getSiret();
    }

    public void setSiret(String siret) {
        this.legalinformation.setSiret(siret);
    }

    public String getSiren() {
        return legalinformation.getSiren();
    }

    public void setSiren(String siren) {
        this.legalinformation.setSiren(siren);
    }

    public String getRcs() {
        return legalinformation.getRcs();
    }

    public void setRcs(String rcs) {
        this.legalinformation.setRcs(rcs);
    }

    public String getInsurrance() {
        return legalinformation.getInsurrance();
    }

    public void setInsurrance(String insurrance) {
        this.legalinformation.setInsurrance(insurrance);
    }

    public String getAdr() {
        return address.getStreetNumber()+" "+address.getStreet() + " " 
                +address.getZipCode() + ""  +address.getTown();
    }
    
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

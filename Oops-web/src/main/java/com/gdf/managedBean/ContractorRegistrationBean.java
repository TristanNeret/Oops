/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import com.gdf.ejb.RegistrationBean;
import com.gdf.ejb.SearchBean;
import com.gdf.persistence.Address;
import com.gdf.persistence.Category;
import com.gdf.persistence.Contractor;
import com.gdf.persistence.LegalInformation;
import com.gdf.persistence.Service;
import com.gdf.singleton.PopulateDB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.faces.view.ViewScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Manage Contrator registration
 *
 * @author borui
 */
@Named(value = "contractorRegistrationBean")
@ViewScoped
public class ContractorRegistrationBean implements Serializable {

    @EJB
    private RegistrationBean rb;
    @EJB
    private SearchBean sb;
    @EJB
    private PopulateDB pdb;

    private Contractor contractor;
    private boolean code;
    
    private int step = 1;

    // STEP 1 -----------------------------------------------------------------------------------
    @NotNull(message = "Veuillez saisir un login")
    @Size(min = 5, message = "Le login doit contenir au moins 5 caractères")
    private String login;
    @NotNull(message = "Veuillez saisir un mot de passe")
    @Size(min = 6, message = "Le mot de passe doit contenir au moins 6 caractères")
    private String password;
    @NotNull(message = "Veuillez saisir une confirmation de mot de passe")
    private String passwordConfirm;
    @NotNull(message = "Veuillez saisir un prénom")
    @Size(min = 3, message = "Le prénom doit contenir au moins 3 caractères")
    private String firstname;
    @NotNull(message = "Veuillez saisir un nom")
    @Size(min = 3, message = "Le nom doit contenir au moins 3 caractères")
    private String lastname;
    @NotNull(message = "Veuillez saisir un email")
    private String email;
    @NotNull(message = "Veuillez saisir un numéro de téléphone")
    private String phone;
    private String region;

    // STEP 2 -----------------------------------------------------------------------------------
    private String socialReason;
    private String legalForm;
    private int turnover;
    private int nbEmployees;
    @Pattern(regexp = "[0-9]{9}", message = "Le n° SIREN doit contenir 9 chiffres")
    @NotNull(message = "Veuillez saisir un numéro de SIREN")
    private String siren;
    @Pattern(regexp = "[0-9]{14}", message = "Le n° SIRET doit contenir 14 chiffres")
    @NotNull(message = "Veuillez saisir un numéro de SIRET")
    private String siret;
    @Size(min = 9, message = "Le n° RCS doit contenir au moins 9 caractères")
    @NotNull(message = "Veuillez saisir un numéro de RCS")
    private String rcs;
    @Size(min = 5, message = "L'assurance doit contenir au moins 5 caractères")
    @NotNull(message = "Veuillez saisir une assurance")
    private String insurrance;

    private int streetNumber, zipCode;
    @Size(min = 5, message = "La rue doit contenir au moins 5 caractères")
    @NotNull(message = "Veuillez saisir une rue")
    private String street;
    @Size(min = 4, message = "La ville doit contenir au moins 4 caractères")
    @NotNull(message = "Veuillez saisir une ville")
    private String town;
    @Size(min = 5, message = "Le pays doit contenir au moins 5 caractères")
    @NotNull(message = "Veuillez saisir un pays")
    private String country;

    private List<SelectItem> legalForms;

    private final SelectItem[] nonTeamCompanies = new SelectItem[]{
        new SelectItem("Auto-entrepreneur", "Auto-entrepreneur"),
        new SelectItem("Entrepreneur individuel", "Entrepreneur individuel"),
        new SelectItem("EIRL", "EIRL"),
        new SelectItem("EURL", "EURL"),
        new SelectItem("SASU", "SASU")
    };

    private final SelectItem[] teamCompanies = new SelectItem[]{
        new SelectItem("SNC", "SNC"),
        new SelectItem("SARL", "SARL"),
        new SelectItem("SA", "SA"),
        new SelectItem("SAS", "SAS"),
        new SelectItem("SCA", "SCA")
    };

    // STEP 3 -----------------------------------------------------------------------------------
    private String logo;
    @NotNull(message = "Veuillez saisir une description")
    @Size(min = 30, message = "La description doit contenir au moins 30 caractères")
    private String description;

    // STEP 4 -----------------------------------------------------------------------------------
    private String titleService;
    private String descriptionService;
    private long idCategoryService;
    private double priceService = 0.0;
    private List<Category> categories;
    private Service editService;
    
    /**
     * All countries available for the registration
     */
    private  List<SelectItem> allCountry;
    private  List<SelectItem> allTown;

    @PostConstruct
    public void init() {

        SelectItemGroup g1 = new SelectItemGroup("Entreprise individuelle");
        g1.setSelectItems(nonTeamCompanies);

        SelectItemGroup g2 = new SelectItemGroup("Entreprise non-individuelle");
        g2.setSelectItems(teamCompanies);
        
        code = false;

        legalForms = new ArrayList<>();
        legalForms.add(g1);
        legalForms.add(g2);

        this.setCategories(sb.getCategories());
    }

    /**
     * Creates a new instance of ContractorRegistrationBean
     */
    public ContractorRegistrationBean() {

    }

    public void step1() {
        Contractor c = new Contractor();
        c.setLogin(this.login);
        c.setPassword(this.password);
        c.setRepresentatorFirstname(this.firstname);
        c.setRepresentatorLastname(this.lastname);
        c.setEmail(this.email);
        c.setPhone(this.phone);

        Long id = this.rb.register(c);
        // Connect the contractor
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userID", id);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userCategory", Contractor.userCategory);

        this.contractor = sb.searchContractorById(id); // the contractor with the id setted

        this.step = 2;
    }

    public void step2() {

        
        contractor.setLegalForm(legalForm);
        contractor.setLogo(logo);
        contractor.setSocialReason(socialReason);
        contractor.setTurnover(turnover);
        contractor.setNbEmployees(nbEmployees);
        contractor.setLegalInformation(new LegalInformation(siret, siren, rcs, insurrance));

        Address contractorAdress = new Address(streetNumber, street, zipCode, town, country);
        
        if(region != null)
            contractorAdress.setRegion(region);
        
                
        contractor.setAddress(contractorAdress);

        this.rb.update(contractor);

        this.step = 3;
    }

    public void step3() {

        contractor.setLogo(logo);
        contractor.setDescription(description);
        this.rb.update(contractor);

        this.step = 4;
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
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getSiren() {
        return siren;
    }

    public void setSiren(String siren) {
        this.siren = siren;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public boolean isATeamCompanySelected() {
        for (SelectItem si : teamCompanies) {
            if (si.getLabel().equals(legalForm)) {
                return true;
            }
        }
        return false;
    }

    public boolean isCode() {
        return code;
    }

    public void setCode(boolean code) {
        this.code = code;
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<SelectItem> getLegalForms() {
        return legalForms;
    }

    public void setLegalForms(List<SelectItem> legalForms) {
        this.legalForms = legalForms;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        this.code = true;
        this.zipCode = zipCode;
        this.region = pdb.getRegion(Integer.toString(zipCode));
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
    
    
    public List<SelectItem> getAllCountry() {
        List<String> lcountries = pdb.getAllCountries();
        
        List<SelectItem> li = new ArrayList<>();
        
        for(String country : lcountries){
            if(country != null)
                li.add(new SelectItem(country));
        }        
        
        return li;
    }
    
    
     public List<SelectItem> getAllTown() {
        List<String> ltowns = pdb.getAllTown(Integer.toString(this.zipCode));
        
        List<SelectItem> li = new ArrayList<>();
        
        for(String town : ltowns)
                li.add(new SelectItem(town)); 
        
        return li;
    }

    public void setAllCountry(List<SelectItem> allCountry) {
        this.allCountry = allCountry;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }
    
    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    /**
     * Test if the Contractor has Services
     *
     * @return True if the Contractor has Service, or False
     */
    public boolean areServices() {

        return !this.contractor.getServices().isEmpty();

    }

    /**
     * Add a new Service
     */
    public void addService() { // step 4 add services
       

        Service service = new Service();
        service.setTitle(getTitleService());
        service.setDescription(getDescriptionService());
        if (this.getPriceService() != 0.0) {
            service.setPrice(getPriceService());
        }
        service.setCategory(this.sb.searchCategoryById(this.idCategoryService));
        contractor.addService(service);
        rb.update(contractor);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Votre prestation a été ajoutée avec succès !", ""));

         if(areServices()) this.step = 5;
    }

    /**
     * Delete a Contractor's Service
     *
     * @param service Service to Remove
     */
    public void deleteService(Service service) {

        contractor.removeService(service);
        rb.update(contractor);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Votre prestation a été supprimé avec succès !", ""));

        if(!areServices()) this.step = 4;
    }

    /**
     * Uppdate a Contractor's Service
     */
    public void updateService() {

        rb.update(this.editService);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Votre prestation a été modifiée avec succès !", ""));

    }

    public String getTitleService() {
        return titleService;
    }

    public void setTitleService(String titleService) {
        this.titleService = titleService;
    }

    public String getDescriptionService() {
        return descriptionService;
    }

    public void setDescriptionService(String descriptionService) {
        this.descriptionService = descriptionService;
    }

    public long getIdCategoryService() {
        return idCategoryService;
    }

    public void setIdCategoryService(long idCategoryService) {
        this.idCategoryService = idCategoryService;
    }

    public double getPriceService() {
        return priceService;
    }

    public void setPriceService(double priceService) {
        this.priceService = priceService;
    }

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Service getEditService() {
        return editService;
    }

    public void setEditService(Service editService) {
        this.editService = editService;
    }
}

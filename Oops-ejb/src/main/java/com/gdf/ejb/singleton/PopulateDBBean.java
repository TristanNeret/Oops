/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.ejb.singleton;

import com.gdf.persistence.Address;
import com.gdf.persistence.Category;
import com.gdf.persistence.Contractor;
import com.gdf.persistence.LegalInformation;
import com.gdf.persistence.Moderator;
import com.gdf.persistence.Review;
import com.gdf.persistence.ReviewState;
import com.gdf.persistence.Service;
import com.gdf.persistence.Tenderer;
import com.gdf.singleton.PopulateDB;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.UriBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Class used to populate the database for selenium tests
 * @author aziz
 */
@Singleton
@Startup
public class PopulateDBBean implements PopulateDB {

    @PersistenceContext(unitName = "OopsPU")
    private EntityManager em;  
  
    private final static String[] ignoredCountry = {"","Samoa américaines","Géorgie du Sud-et-les Îles Sandwich du Sud", "Åland","Samoa américaines","Territoire britannique de l'océan Indien","Îles mineures éloignées des États-Unis", "Terres australes et antarctiques françaises","Géorgie du Sud-et-les Îles Sandwich du Sud"};
    private List<String> allCountries;
    private ClientConfig config;
    private Client client; 
    private WebResource service; 
    
    
    /**
     * Populate the database for the selenium tests
     */
    public PopulateDBBean() {
        this.allCountries = new ArrayList();
    }
    
     
    @PostConstruct
    private void populateDatabase(){
        
        // REVIEWS
        
        /*
            Retrieve all countries of the world for the registration of a contractor (with API rest com.mashape.unirest) 
        */
        this.retrieveAllWorldCoutries();
        
        config = new DefaultClientConfig();
        client = Client.create(config);
                
        Review review = new Review();
        review.setAppreciation("Très professionel");
        review.setContent("Travail réalisé dans les temps et de qualité !");
        review.setRating(4);
        review.setContractorAnswer("Merci et à bientôt !");
        review.setReviewState(ReviewState.ACCEPTED);
        
        Review review1 = new Review();
        review1.setAppreciation("Nul");
        review1.setContent("Vraiment pas intéressant, passez votre chemin...");
        review1.setRating(1);
        review1.setReviewState(ReviewState.DELIVERED);
        
        Review review2 = new Review();
        review2.setAppreciation("Génial !!");
        review2.setContent("Un travail merveilleux dans une super équipe ! !");
        review2.setRating(5);
        review2.setContractorAnswer(null);
        review2.setReviewState(ReviewState.DELIVERED);
        
        Review review3 = new Review();
        review3.setAppreciation("Pas mal..");
        review3.setContent("Résultat satisfaisant qui mériterait plus d'attention.");
        review3.setRating(3);
        review3.setReviewState(ReviewState.ACCEPTED);
        
        Review review4 = new Review();
        review4.setAppreciation("Parfait !");
        review4.setContent("Excellent travail ! Mon application est top !");
        review4.setRating(5);
        review4.setReviewState(ReviewState.ACCEPTED);
        
        
        Review review5 = new Review();
        review5.setAppreciation("Magnifique !!");
        review5.setContent("Les plats sont succulents !!");
        review5.setRating(5);
        review5.setContractorAnswer("Merci et à bientôt !");
        review5.setReviewState(ReviewState.ACCEPTED);
        
        
        Review review6 = new Review();
        review6.setAppreciation("Bravo !");
        review6.setContent("Malgrè les locaux qui ressemblent à un garage, la prestation fut à la hauteur de mon espérance !!");
        review6.setRating(4);
        review6.setContractorAnswer("Merci et à bientôt ! PS : On peut aussi s'occuper de nos pneues !");
        review6.setReviewState(ReviewState.ACCEPTED);
 
        String format = "dd/MM/yy H:mm:ss";
        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(format);
        java.util.Date date = new java.util.Date();
        review.setDate(formater.format(date));
        review1.setDate(formater.format(date));
        review2.setDate(formater.format(date));
        review3.setDate(formater.format(date));
        review4.setDate(formater.format(date));
        review5.setDate(formater.format(date));
        review6.setDate(formater.format(date));
        
        
        em.persist(review);
        em.persist(review1);
        em.persist(review2);
        em.persist(review3);
        em.persist(review4);
        em.persist(review5);
        em.persist(review6);
        
        // TENDERERS
        
        Tenderer tenderer = new Tenderer("Michmich", "michou.dupond@gmail.com", 
                "password", "Michel", "Dupond", "http://goodfilmguide.co.uk/wp-content/uploads/2010/04/avatar12.jpg", "1234567890", "01/01/2016");
        tenderer.setId(1);
        tenderer.addReview(review);
        tenderer.addReview(review1);
        tenderer.addReview(review2);
        tenderer.updateNbReviews();
        em.persist(tenderer);
        
        tenderer = new Tenderer("Dede", "dede.legrand@gmail.com", "password", "Didier", "Legrand", 
                "https://cdn3.iconfinder.com/data/icons/rcons-user-action/32/boy-512.png", "1234567890", "01/01/2016");
        tenderer.addReview(review3);
        tenderer.addReview(review4);
        em.persist(tenderer);
        
        
        tenderer = new Tenderer("Niko", "niko@gmail.com", "password", "Nicolas", "Jukic", 
                "https://cdn3.iconfinder.com/data/icons/rcons-user-action/32/boy-512.png", "1234567890", "01/01/2016");
        tenderer.addReview(review5);
        em.persist(tenderer);
        
        
        tenderer = new Tenderer("Chafinou", "chafi@gmail.com", "password", "Aziz", "Chafi", 
        "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcSO-MG2D5VbdaQAkN60xGcSYKf-O28tfaxlDTyKhiZk1o62SIDB-w", "1234567890", "01/01/2016");
        tenderer.addReview(review6);
        em.persist(tenderer);
        
        // CONTRACTOR
        
        
        //Computer science compagnies
        Category category = new Category("Informatique", "image"); 
        Contractor contractor = new Contractor("ITContractor", "contact@itcontractor.com", "password", "IT Contractor Inc.",
        "SA", "Nous sommes ITContractor nous vous offront different types de services IT,"
                + " n'hesitez pas a nous contacter pour plus d'informations.", 
                "3937937820", "http://www.ut-capitole.fr/medias/photo/it-128_1413288947855-png", "ITContractor", "Representant", 100000, 100, 4,
        new Address(3, "beans street", 2648, "New York", "Etats-Unis"), new LegalInformation("12345678900987", "123456789", "RCSITC123456789", "AssureTout"));
        Service service = new Service("Developpement Informatique", "Nous developpons vos projets informatique, sous forme d'applications "
                + "web et mobiles.", 500.0, contractor, category);
        contractor.setId(50);
        contractor.addService(service);
        contractor.addReview(review);
        contractor.addReview(review1);
        contractor.addReview(review2);
        contractor.addReview(review4);

        em.persist(category);
        em.persist(contractor);

        
        contractor = new Contractor("Sfeir", "contact@sfeir.com", "password", "Sfeir-Est",
        "SA", "SFEIR est renommée dans la communauté technologique pour construire les meilleures plateformes numériques.", 
                "3937937820", "http://lemag.sfeir.com/wp-content/uploads/2015/08/logoSFEIR.jpg", "Sfeir", "Representant", 100000, 100, 0,
        new Address(1, "avenue de l’Europe", 67300, "Schiltigheim", "France","Alsace"), new LegalInformation("12345678900987", "123456789", "RCSITC123456789", "AssureTout"));
        service = new Service("Transformation digitale et développement", "Assistance à maitrise d’ouvrage, audit, gestion de projet, expertise technique, conception, développement "
                + "web et mobiles.", 500.0, contractor, category);
        contractor.addService(service);
        contractor.setId(51);
        em.persist(contractor);
        

        contractor = new Contractor("Acelys", "contact@acelys.com", "password", "Acelys",
        "SA", "Acelys est une société de services et d’ingénierie en informatique montpelliéraine. Depuis plus de 15ans, nous intervenons auprès des entreprises de la région Languedoc Roussillon dans la transformation de leurs métiers et de leurs systèmes d'information.", 
                "3937937820", "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcQtQ3DD5Qkr9YRvw16QqTeYf9FjxltF0AT92DAsaHqychDyjjqzig", "Acelys", "Representant", 100000, 100, 0,
        new Address(159, "Rue de Thorg", 34000 , "Montpellier", "France","Languedoc-Roussillon"), new LegalInformation("12345678900987", "123456789", "RCSITC123456789", "AssureTout"));
        service = new Service("Développement Web", "Qu'il s'agisse d'une application métier spécifique, d’un Portail, d'un site web institutionnel ou commercial, nous pouvons imaginer la solution parfaite répondant à vos attentes."
                , 500.0, contractor, category);
        contractor.addService(service);
        service = new Service("Application mobile : Android, iOS, Windows Phone", "Vous souhaitez développer au sein de votre entreprise les usages mobiles ou proposer à vos utilisateurs et vos clients la possibilité de consulter votre contenu depuis leur smartphone ou tablette."
              , 500.0, contractor, category);
        contractor.addService(service);
        service = new Service("Expertise technique : Big Data, cloud computing", "De nombreux concepts émergent chaque jour en informatique et représentent de réelles opportunités pour vous et vos métiers."
              , 500.0, contractor, category);
        contractor.addService(service);
        contractor.setId(53);
        em.persist(contractor);
        
        
        contractor = new Contractor("ITs4U", "contact@its4u.com", "password", "ITs4U",
        "SA", "ITs4U est une SSII indépendante fondée en 2009 sur des valeurs d’excellence et d’engagement. En croissance constante depuis notre création, nous réalisons plus de 50 % de notre chiffre d’affaires sur engagement de résultats.", 
                "3937937820", "http://www.its4u.lu/wp-content/uploads/2015/01/ITs4U-SSII-Luxembourg-logo-FB-h154.png", "ITs4U", "Representant", 100000, 100, 4,
        new Address(136, "Route de Bettembourg", 5811, "Schiltigheim", "Luxembourg"), new LegalInformation("12345678900987", "123456789", "RCSITC123456789", "AssureTout"));
        service = new Service("Conseil", "Assistance à la définition des solutions qui vous permettent d’atteindre vos objectifs: CRM, ECM, SOA, BPM"
        , 500.0, contractor, category);
        contractor.addService(service);
        service = new Service("Expertise Technique", "Architecture (SOA), expertise et assistance technique"
        , 500.0, contractor, category);
        contractor.addService(service);
        contractor.addReview(review6);
        contractor.setId(52);
        em.persist(contractor);
        
        category = new Category("Batiment", "image"); 
        contractor = new Contractor("FranceBTP", "contact@francebtp.com", "password", "FranceBTP Sarl",
        "SARL", "Nous sommes FranceBTP nous vous offront different services dans le domaine de la construction et du batiment,"
        + " n'hesitez pas a nous contacter pour plus d'informations.", "9379378201", "http://www.cibtp.fr/fileadmin/templates/portail/img/logo_charte_qualite.png", "FranceBTP", "Representant", 500000, 500, 3,
        new Address(3, "rue des haricots", 75001, "Paris", "France","ile-de-france"), new LegalInformation("12345678903987", "126456789", "RCSITC113456789", "Assurancetoutrix"));
        service = new Service("Démolition", "Nous réalisons vos travaux de démolition", 100.0, contractor, category);
        contractor.addService(service); 
        contractor.addReview(review3);
        
        em.persist(category);
        em.persist(contractor);
        
        category = new Category("Divers", "image"); 
        em.persist(category);
        
        
        //Restauration
        category = new Category("Restauration", "image"); 
        em.persist(category);
        
        contractor = new Contractor("CercleRouge", "contact@lecerclerouge.com", "password", "Le Cercle Rouge",
        "SA", "Au Cercle Rouge, la cuisine maison, revisite la tradition corse. Un de ses produits phare : le veau \"tigre\", venu tout droit de l'exploitation \"Agriculture Biologique\" de Jacques Abbatucci.", 
        "3937937820", "http://static.wixstatic.com/media/ac44c4_265208be2f1d45ff8dabd4b3aafcc022.jpg_srz_290_221_85_22_0.50_1.20_0.00_jpg_srz", "Le cercle Rouge", "Representant", 100000, 100, 5,
        new Address(41, "Rue A.THIERS", 54000, "Marseille", "France","Provence-Alpes-Côte d'Azur"), new LegalInformation("12345678900987", "123456789", "RCSITC123456789", "AssureTout"));
        service = new Service("Restaurant Corse", "Une carte de tapas originaux et de desserts maison, à déguster dans le patio chauffé toute l'année, comme à l'intérieur, près de la cheminée, l'endroit idéal pour découvrir aussi l'un de nos cocktails exclusifs et notre carte de spiritueux..."
        , 100.0, contractor, category);
        contractor.addService(service);
        contractor.setId(2);
        contractor.addReview(review5);
        em.persist(contractor);
        
        
        contractor = new Contractor("RelaisSaveurs", "contact@aurelaisdessaveurs.com", "password", "Au Relais des Saveurs",
        "SA", "Dimitri AUDIN, Chef et propriétaire du restaurant, vous propose une cuisine exclusivement réalisée à partir de produits frais, de saison et originaires de producteurs locaux et nationaux.", 
        "3937937820", "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcQwwzwsVyAmhtauanZQj5KhKdDpxHmt084VIAT3LYgkm9Yl0uct", "Au Relais des Saveurs", "Representant", 100000, 100, 0,
        new Address(28, "Rue du commerce", 63800, "Cournon-d'Auvergne", "France","Auvergne"), new LegalInformation("12345678900987", "123456789", "RCSITC123456789", "AssureTout"));
        service = new Service("Le restaurant","Situé au Coeur du vieux bourg, ce lieu est historiquement ancré dans la vie de la Ville de Cournon d’ Auvergne"
        , 100.0, contractor, category);
        contractor.addService(service);
        contractor.setId(3);
        em.persist(contractor);
        
  
        //Animation
        category = new Category("Animation", "image"); 
        em.persist(category);
        
        contractor = new Contractor("SoireeVip", "contact@soireevip.com", "password", "SoireeVip",
        "SA", "Depuis plus de 12 ans, Soirée VIP vous accompagne tout au long de votre évènement.\n" +
        "Nous nous chargeons de l'animation, de la sonorisation et de la mise en lumière de votre soirée. Nous pouvons également nous occuper de la partie vidéo et effets spéciaux", 
        "3937937820", "http://www.mariageservice.com/logo/6104.jpg", "SoireeVip", "Representant", 100000, 100, 0,
        new Address(1, "Avenue Fosh", 54000, "Nancy", "France","Lorraine"), new LegalInformation("12345678900987", "123456789", "RCSITC123456789", "AssureTout"));
        service = new Service("Formule PRESTIGE", "Éclairages piste de danse avec 4 projecteurs robotisés, 2 projecteurs de couleurs, stroboscope, machine à brouillard"
        , 500.0, contractor, category);
        contractor.addService(service);
        contractor.setId(9);
        em.persist(contractor);
        
        contractor = new Contractor("JonnyAnnimation", "contact@jonnyannimation.com", "password", "JonnyAnnimation",
        "SA", "​Vous préparez votre mariage sur le secteur de Brest , de Quimper, la fête de votre association dans le Finistère 29, la Soirée dansante de votre comité d'entrepri​se, un départ en retraite, le bal de votre comité des fêtes, ou bien une Soirée Karaoké ?", 
        "3937937820", "https://upload.wikimedia.org/wikipedia/fr/thumb/a/aa/Jt-logo-current.jpg/220px-Jt-logo-current.jpg", "JonnyAnnimation", "Representant", 100000, 100, 0,
        new Address(1, "Rue de Vern", 35000 , "Renne", "France","Bretagne"), new LegalInformation("12345678900987", "123456789", "RCSITC123456789", "AssureTout"));
        service = new Service("Sculture sur ballons", "La sculpture sur ballons est l´animation idéale pour les enfants durant le vin d'honneur d´un mariage"
        , 500.0, contractor, category);
        contractor.addService(service);
        contractor.setId(8);
        em.persist(contractor);
        
        
        Moderator moderator = new Moderator();
        moderator.setLogin("SuperModerator");
        moderator.setPassword("admin");
        moderator.setId((long)1);
        
        em.persist(moderator);
        
    }
    
    
    /*
     for retrieve all countries with unirest API
    */
    private void retrieveAllWorldCoutries(){
        
        try {
        
            // These code snippets use an open-source library. http://unirest.io/java
            HttpResponse<JsonNode> response = Unirest.get("https://restcountries-v1.p.mashape.com/all")
                    .header("X-Mashape-Key", "IYf0SsPkx6mshsdvXDW6JC2Pt65up1NAEDejsnyT9Ot96YT0tC")
                    .header("Accept", "application/json")
                    .asJson();
            
            String rep = response.getBody().toString();
            JSONParser parser=new JSONParser();
            Object obj;
            
            try {
                
                obj = parser.parse(rep);
                JSONArray jsonArray = (JSONArray) obj;
            
                for (Object jo : jsonArray){
                    JSONObject infoCoutryJSON =  (JSONObject)jo;
                    JSONObject  allTranslationNameCountry = (JSONObject) infoCoutryJSON.get("translations");
                    String nameCountryFrench = (String) allTranslationNameCountry.get("fr");
                    if( nameCountryFrench != null && !this.belongToIgnoredCountry(nameCountryFrench))
                        allCountries.add(nameCountryFrench);
                }
            
            }catch (ParseException ex) {
                Logger.getLogger(PopulateDBBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        } catch (UnirestException ex) {
            Logger.getLogger(PopulateDBBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    /*
    For delete some countries (the small countries for exemple)
    */
    private boolean belongToIgnoredCountry(String name){
        
        for(String country : ignoredCountry){
            if(country.equals(name))
                return true; 
        }                   
        return false;
    }

    @Override
    public List<String> getAllCountries() {
        return this.allCountries;
    }

    
    /*
    For retrieve all towns which correspond to a zipcode 
    */
    @Override
    public List<String> getAllTown(String code) {

   
        service = client.resource(
        UriBuilder.fromUri("http://api.zippopotam.us/fr/").build());
        String response;
        
        try{
            response = service.path(code).get(String.class);
        }catch(UniformInterfaceException ue){
            return null; // if the postal code is null, it doesn't exist dans erro will be display to the customer
        }
        
        List<String> lt = new ArrayList();
        JSONParser parser=new JSONParser();
        Object obj;
        
        try {

            obj = parser.parse(response);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray list =  (JSONArray) jsonObject.get("places");

            for (Object jo : list){
                JSONObject placesObject =  (JSONObject)jo;
                String nameTown = (String) placesObject.get("place name");
                lt.add(nameTown);
            }  

        }catch (ParseException ex) {
            return null;
        }
        
        return lt;
    }
    
    @Override
    public String getRegion(String code) {

        service = client.resource(
        UriBuilder.fromUri("http://api.zippopotam.us/fr/").build());
        
        String response;
        
        try{
            response = service.path(code).get(String.class);
        }catch(UniformInterfaceException ue){
            return null; // if the postal code is null, it doesn't exist dans erro will be display to the customer
        }
 
        String region = "";
        JSONParser parser=new JSONParser();
        Object obj;
        
        try {
 
            obj = parser.parse(response);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray list =  (JSONArray) jsonObject.get("places");
            JSONObject regionJSON =  (JSONObject) list.get(0);
            region =  (String) regionJSON.get("state");

        }catch (ParseException ex) {
            return null;
        }

        return region;
    }
    
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
    
    @Override
    public List<SelectItem> getLegalForms(){
        List<SelectItem> legalForms = new ArrayList<>();
        legalForms.addAll(Arrays.asList(nonTeamCompanies));
        legalForms.addAll(Arrays.asList(teamCompanies));
        return legalForms;
    }

    @Override
    public boolean isATeamCompany(String teamCompany) {
        for (SelectItem si : teamCompanies) {
            if (si.getLabel().equals(teamCompany)) {
                return true;
            }
        }
        return false;
    }
}

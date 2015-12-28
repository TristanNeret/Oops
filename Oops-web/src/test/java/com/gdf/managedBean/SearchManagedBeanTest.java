/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author aziz
 */
public class SearchManagedBeanTest {
    
    private final By searchButtonXpathExp = By.xpath("//input[contains(@id, 'searchButton')]"), 
               typeListXpathExp = By.xpath("//select[contains(@id, 'listType')]"),
               queryInputTextXpathExp = By.xpath("//input[contains(@id, 'queryInputText')]"),
            
               ratingListXpathExp = By.xpath("//select[contains(@id, 'listRating')]"),
               countriesListXpathExp = By.xpath("//select[contains(@id, 'listCountries')]"),
               categoriesListXpathExp = By.xpath("//select[contains(@id, 'listCategories')]");
    
    private WebDriver driver;
    private String baseUrl;

    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        baseUrl = "http://localhost:8080/Oops-web/index.xhtml";
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }
    
    @After
    public void tearDown() {
        driver.close();
    }

    /*
    * TESTS FOR TENDERER SEARCH ---------------------------------------------------------------------------------------------
    */
    
    /*
    * Search without parameters 
    */
   @Test
   public void testSearchTendererWithoutQuery() {
        driver.get(baseUrl);
        
        driver.findElement(queryInputTextXpathExp).clear();
        Select select = new Select(driver.findElement(typeListXpathExp));
        select.selectByVisibleText("Soumissionnaire");    
        driver.findElement(searchButtonXpathExp).click();
        
        driver.findElement(By.id("linkResult"));      
    }
    
     /**
     * Search with a random parameter, no result expected
     */
    @Test
    public void testSearchTendererWithQueryWithoutResult(){
       driver.get(baseUrl);
       
       driver.findElement(queryInputTextXpathExp).clear();
       driver.findElement(queryInputTextXpathExp).sendKeys(RandomStringUtils.randomAscii(10));
       Select select = new Select(driver.findElement(typeListXpathExp));
       select.selectByVisibleText("Soumissionnaire"); 
       driver.findElement(searchButtonXpathExp).click();
       
       driver.findElement(By.id("noResultOutputText"));      
    }
   
    /**
     * Search with parameters, at least one result expected
     */
    @Test
    public void testSearchTendererWithQueryWithResult(){
       driver.get(baseUrl);
       
       driver.findElement(queryInputTextXpathExp).clear();
       driver.findElement(queryInputTextXpathExp).sendKeys("julie");
       Select select = new Select(driver.findElement(typeListXpathExp));
       select.selectByVisibleText("Soumissionnaire");
       driver.findElement(searchButtonXpathExp).click();
       
       driver.findElement(By.id("linkResult"));      
    }
    
    /*
    * TESTS FOR CONTRACTOR SEARCH ---------------------------------------------------------------------------------------------
    */
    
    /*
    * Search without parameters 
    */
   @Test
   public void testContractorTendererWithoutQuery() {
        driver.get(baseUrl);
        
        driver.findElement(queryInputTextXpathExp).clear();
        Select select = new Select(driver.findElement(typeListXpathExp));
        select.selectByVisibleText("Prestataire");
        driver.findElement(searchButtonXpathExp).click();
     
        driver.findElement(By.id("linkResult"));      
    }
    
     /**
     * Search with a random parameter, no result expected
     */
   @Test
    public void testSearchContractorWithQueryWithoutResult(){
       driver.get(baseUrl);
       
       driver.findElement(queryInputTextXpathExp).clear();
       driver.findElement(queryInputTextXpathExp).sendKeys(RandomStringUtils.randomAscii(10));
       Select select = new Select(driver.findElement(typeListXpathExp));
       select.selectByVisibleText("Prestataire");
       driver.findElement(searchButtonXpathExp).click();
       
       driver.findElement(By.id("noResultOutputText"));      
    }
   
    /**
     * Search with parameters, at least one result found
     */
    @Test
    public void testSearchContractorWithQueryWithResult(){
       driver.get(baseUrl);
       
       driver.findElement(queryInputTextXpathExp).clear();
       driver.findElement(queryInputTextXpathExp).sendKeys("apple");
       Select select = new Select(driver.findElement(typeListXpathExp));
       select.selectByVisibleText("Prestataire");
       driver.findElement(searchButtonXpathExp).click();
       
       driver.findElement(By.id("linkResult"));      
    }
}

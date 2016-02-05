/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author nicolas
 */
public class ContractorInformationTest {
    
    private static WebDriver driver;
    private static String baseUrl;
    
    public ContractorInformationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        driver = new FirefoxDriver();
        baseUrl = "http://localhost:8080/Oops-web/views/contractorInformation.xhtml";
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }
    
    @AfterClass
    public static void tearDownClass() {
        driver.close();
    }
    
    @Before
    public void setUp() { 
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Search without parameters in the url
     */
    @Test
    public void testNoId() {
        driver.get(baseUrl);        
        driver.findElement(By.xpath("//h2[contains(text(), 'Prestataire introuvable !')]"));      
    }
    
    @Test
    public void testBadid(){
        driver.get(baseUrl+"?id=500");        
        driver.findElement(By.xpath("//h2[contains(text(), 'Prestataire introuvable !')]"));      
    }
    
    @Test
    public void testGoodid(){
        driver.get(baseUrl+"?id=50");        
        driver.findElement(By.xpath("//h2[1][contains(text(), 'Description')]"));      

    }
    
}
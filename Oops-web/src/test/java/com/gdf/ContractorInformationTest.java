/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf;

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
    
    private WebDriver driver;
    private String baseUrl;
    
    public ContractorInformationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        baseUrl = "http://localhost:8080/Oops-web/views/contractorInformation.xhtml";
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }
    
    @After
    public void tearDown() {
        driver.close();
    }

    /*
     Search without parameters in the url
    */
//    @Test
//    public void testNoId() {
//        driver.get(baseUrl);        
//        driver.findElement(By.xpath("//h2[contains(text(), 'Prestataire introuvable !')]"));      
//    }
//    
//    @Test
//    public void testBadid(){
//        driver.get(baseUrl+"?id=50");        
//        driver.findElement(By.xpath("//h2[contains(text(), 'Prestataire introuvable !')]"));      
//    }
//    
//    @Test
//    public void testGoodid(){
//        driver.get(baseUrl+"?id=1");        
//        driver.findElement(By.xpath("//h2[1][contains(text(), 'Description')]"));      
//
//    }
    
}

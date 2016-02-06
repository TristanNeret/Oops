/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author bettaieb
 */
public class TendererInformationTest {
    
    private static WebDriver driver;
    private static String baseUrl;
    
    public TendererInformationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        driver = new FirefoxDriver();
        baseUrl = "http://localhost:8080/Oops-web/TendererManagement.xhtml";
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
     * Test that error message appeared if the Moderator manage a Review
     * without take decision
     */
    @Test
    public void testErrorMessageManageReview() {
        
        // Preparation
        driver.get(baseUrl); 
        WebElement saveButton = driver.findElement(By.className("commandButtonSaveupdateTenderer"));
        saveButton.click();
        WebElement closeButton = driver.findElement(By.className("commandButtoncloseupdateTenderer"));
        saveButton.click();

        // Test
        WebElement growlTitle = driver.findElement(By.className("ui-growl-title"));

     
        
    }
    
  
    @Test
    public void testSuccessMessageUpdateTenderer() {
        
        // Preparation
        driver.get(baseUrl);    
        WebElement choiceButton = driver.findElement(By.className("commandButtonChoice"));
        choiceButton.click();
        WebElement saveButton = driver.findElement(By.className("commandButtonSaveAdmin"));
        saveButton.click();

        // Test
        WebElement growlTitle = driver.findElement(By.className("ui-growl-title"));

        // Verification
        assertEquals(growlTitle.getText(), "Tenderer Update avec succès !");
        
    }

}

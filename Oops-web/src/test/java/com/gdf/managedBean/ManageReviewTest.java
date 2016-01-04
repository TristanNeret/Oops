/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf;

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
 * @author Tristan
 */
public class ManageReviewTest {
    
    private WebDriver driver;
    private String baseUrl;
    
    public ManageReviewTest() {
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
        baseUrl = "http://localhost:8080/Oops-web/views/adminManager.xhtml";
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }
    
    @After
    public void tearDown() {
        driver.close();
    }
    
    /**
     * Test that error message appeared if the Moderator manage a Review
     * without take decision
     */
    @Test
    public void testErrorMessageManageReview() {
        
        // Preparation
        driver.get(baseUrl); 
        WebElement testTitle = driver.findElement(By.className("titlePanelReviewAdmin"));
        if(testTitle.getText().equals("Avis en attente de traitement :")) {

            // Test only if waiting reviews exist
            WebElement saveButton = driver.findElement(By.className("commandButtonSaveAdmin"));
            saveButton.click();

            // Test
            WebElement growlTitle = driver.findElement(By.className("ui-growl-title"));

            // Verification
            assertEquals(growlTitle.getText(), "Vous devez prendre une décision !");
        
        }
        
    }
    
    /**
     * Test that success message appeared if the Moderator manage a Review
     * after have take decision
     */
    @Test
    public void testSuccessMessageManageReview() {
        
        // Preparation
        driver.get(baseUrl);    
        WebElement testTitle = driver.findElement(By.className("titlePanelReviewAdmin"));
        if(testTitle.getText().equals("Avis en attente de traitement :")) {
        
            // Test only if waiting reviews exist
            WebElement choiceButton = driver.findElement(By.className("commandButtonChoice"));
            choiceButton.click();
            WebElement saveButton = driver.findElement(By.className("commandButtonSaveAdmin"));
            saveButton.click();

            // Test
            WebElement growlTitle = driver.findElement(By.className("ui-growl-title"));

            // Verification
            assertEquals(growlTitle.getText(), "Avis traité avec succès !");
        
        }
        
    }

}

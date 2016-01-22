/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import java.util.concurrent.TimeUnit;
import javax.validation.constraints.AssertTrue;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author aziz
 */
public class ListTendererBeanTest {
    /**
    private static WebDriver driver;
    private static String baseUrl;
    
    public ListTendererBeanTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        driver = new FirefoxDriver();
        baseUrl = "http://localhost:8080/Oops-web/index.xhtml";
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
    
    @Test
    public void testAskForReview(){
        // Preparation
        driver.get(baseUrl); 
        WebElement askButton = driver.findElement(By.className("commandButtonAskReview"));
        askButton.click();

        // Test
        WebElement growlTitle = driver.findElement(By.className("ui-growl-title"));

        // Verification
        assertEquals(growlTitle.getText(), "Avis envoyé avec succès !");
    }
    
    @Test
    public void testReviewAsked(){
         // Preparation
        driver.get(baseUrl);
        boolean notFind = false;
        
        // Test
        try {
            driver.findElement(By.className("commandButtonAskReview"));
        }catch(NoSuchElementException e){
            notFind = true;
        }
        
        // Verification
        assertTrue(notFind);
    }**/
}

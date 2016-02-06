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
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * TendererReviewBeanTest
 * @author Tristan
 */
public class TendererReviewBeanTest {
    
    private static WebDriver driver;
    private static String baseUrl;
    
    public TendererReviewBeanTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        driver = new FirefoxDriver();
        baseUrl = "http://localhost:8080/Oops-web/views/tendererManagement.xhtml";
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        
    }
    
    @AfterClass
    public static void tearDownClass() {
        
        driver.close();
        
    }
    
    @Before
    public void setUp() {
        
        driver.get(baseUrl); 
        
        // Go to write Tab
        boolean test = true;
        int i = 1;
        while (test) {
            WebElement linkElement = driver.findElement(By.xpath("//ul/li[" + i + "]/a"));
            if (linkElement.getText().equals("Mes Avis")) {
                linkElement.click();
                test = false;
            }
            i++;
        }
        
    }
    
    @After
    public void tearDown() {
        
    }

    /**
     * Test of editReview method, of class TendererReviewBean.
     * Test that a Review is well edited
     */
    @Test
    public void testEditReview() {
        
        // Preparation
        String testTitle = "Test edition title";
 
        // Click on edit button of the first Review
        WebElement editButton = driver.findElement(By.xpath("//button[contains(@id, 'reviews:1:editButton')]"));
        editButton.click();
        
        WebElement inputTitle = driver.findElement(By.xpath("//input[contains(@id, 'reviews:1:reviewAppreciation')]"));
        inputTitle.clear();
        inputTitle.sendKeys(testTitle);
        
        // Validate edition of the first Review
        WebElement validateRemoveButton = driver.findElement(By.xpath("//button[contains(@id, 'reviews:1:editButtonDialogValidate')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", validateRemoveButton);
        
        // Test
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-growl-title")));
        WebElement label = driver.findElements(By.className("divReviewAppreciationTenderer")).get(1);
        
        // Verification
        assertEquals(testTitle, label.getText()); 
        
    }

    /**
     * Test of removeReview method, of class TendererReviewBean.
     * Test that a Review is well removed
     */
    @Test
    public void testRemoveReview() {
        
        // Preparation
        int testSize = driver.findElements(By.className("divReviewAppreciationTenderer")).size()-1;
        
        // Click on remove button of the first Review
        WebElement removeButton = driver.findElement(By.xpath("//button[contains(@id, 'reviews:1:deleteButton')]"));
        removeButton.click();
        // Validate remove button of the first Review
        WebElement validateRemoveButton = driver.findElement(By.xpath("//button[contains(@id, 'reviews:1:confirmButtonDialogValidate')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", validateRemoveButton);
        
        // Test
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-growl-title")));
        int newSize = driver.findElements(By.className("divReviewAppreciationTenderer")).size();

        // Verification
        assertEquals(testSize, newSize);
                        
    }

}

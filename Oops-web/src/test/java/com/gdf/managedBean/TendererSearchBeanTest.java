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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * TendererSearchBeanTest
 * @author aziz
 */
public class TendererSearchBeanTest {
//    
//    private final By searchButtonXpathExp = By.xpath("//input[contains(@id, 'searchButton')]"),
//             typeListXpathExp = By.xpath("//select[contains(@id, 'listType')]"),
//             askReviewBtnXpathExp = By.xpath("//button[contains(@id, 'askReviewForm:askReviewBtn')]"),
//             sendReviewBtnXpathExp = By.xpath("//button[contains(@id, 'askReviewDialogForm:sendAskReviewBtn')]");
//
//    private static WebDriver driver;
//    private static String baseUrl;
//    
    public TendererSearchBeanTest() {
        
    }
//    
    @BeforeClass
    public static void setUpClass() {
        
//        driver = new FirefoxDriver();
//        baseUrl = "http://localhost:8080/Oops-web/index.xhtml";
//        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
//    
    }
//    
    @AfterClass
    public static void tearDownClass() {
        
//        driver.close();
        
    }
    
    @Before
    public void setUp() { 
        
    }
    
    @After
    public void tearDown() {
        
    }
//    
    @Test
    public void testAskForReview(){
//       
//        // Preparation
//        driver.get(baseUrl); 
//        
//        Select select = new Select(driver.findElement(typeListXpathExp));
//        select.selectByVisibleText("Soumissionnaire");
//        
//        WebElement searchButton = driver.findElement(searchButtonXpathExp);
//        searchButton.click();
//        
//        WebElement askButton = driver.findElement(askReviewBtnXpathExp);
//        askButton.click();
//        
//        WebElement sendButton = driver.findElement(sendReviewBtnXpathExp);
//        sendButton.click();
//        
//        // Test
//        WebElement growlTitle = (new WebDriverWait(driver, 10))
//        .until(ExpectedConditions.presenceOfElementLocated(By.className("ui-growl-title")));
//        
//        // Verification
//        assertEquals(growlTitle.getText(), "Demande d'avis envoyée !");
//        
    }
}

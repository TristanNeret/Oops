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
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author aziz
 */
public class ContractorEditCompanyTest {
   
    
     private final By tabBtnXpathExp = By.xpath("//a[contains(@href, 'tabCompany')]"),
             saveBtnXpathExp = By.xpath("//button[contains(@id, 'editForm:saveButton')]");
    
    private static WebDriver driver;
    private static String baseUrl;
    
    public ContractorEditCompanyTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        driver = new FirefoxDriver();
        baseUrl = "http://localhost:8080/Oops-web/views/contractorManagement.xhtml";
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
        WebElement tabButton = driver.findElement(tabBtnXpathExp);
        tabButton.click();
        
        WebElement saveButton = driver.findElement(saveBtnXpathExp); 
        saveButton.click(); 
        
        // Test
        WebElement growlTitle = (new WebDriverWait(driver, 10))
        .until(ExpectedConditions.presenceOfElementLocated(By.className("ui-growl-title")));
        
        // Verification
        assertEquals(growlTitle.getText(), "Modification effectuée !");
    }
}

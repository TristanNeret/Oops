/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import java.util.List;
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
 * ServiceAddBeanTest
 * @author Tristan
 */
public class ServiceAddBeanTest {
    
    private static WebDriver driver;
    private static String baseUrl;
    
    public ServiceAddBeanTest() {
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
        
        driver.get(baseUrl); 
        
        // Go to write Tab
        List<WebElement> listElements = driver.findElements(By.xpath("//ul/li"));
        boolean test = true;
        int i = 1;
        while (test) {
            WebElement linkElement = driver.findElement(By.xpath("//ul/li[" + i + "]/a"));
            if (linkElement.getText().equals("Mes Préstations")) {
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
     * Test of addService method, of class ServiceAddBean.
     * Test that a Service is well added
     */
    @Test
    public void testAddService() {
        
        // Preparation
        String testTitle = "Test edition title";
        String testDescription = "Test edition description";

        // Get number of Service
        int testSize = driver.findElements(By.className("divLeftContractorService")).size()+1;
        
        // Edit the new Service
        WebElement inputTitle = driver.findElement(By.xpath("//input[contains(@id, 'tabViewContractor:formNewService:titleService')]"));
        inputTitle.clear();
        inputTitle.sendKeys(testTitle);
        WebElement inputDescription = driver.findElement(By.xpath("//textarea[contains(@id, 'tabViewContractor:formNewService:ServiceContent')]"));
        inputDescription.clear();
        inputDescription.sendKeys(testDescription);
        
        // Add the new Service
        WebElement addButton = driver.findElement(By.xpath("//button[contains(@id, 'tabViewContractor:formNewService:addButton')]"));
        addButton.click();
        
        // Test
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-growl-title")));
        int newSize = driver.findElements(By.className("divLeftContractorService")).size();

        // Verification
        assertEquals(testSize, newSize);
        
    }

    /**
     * Test of updateService method, of class ServiceAddBean.
     * Test that a Service is well edited
     */
    @Test
    public void testUpdateService() {
        
        // Preparation
        String testTitle = "Test edition title";

        // Click on edit button of the first Service
        WebElement editButton = driver.findElement(By.xpath("//button[contains(@id, 'tabViewContractor:formService:updateCommandButton')]"));
        editButton.click();
        
        WebElement inputTitle = driver.findElement(By.xpath("//input[contains(@id, 'tabViewContractor:formService:serviceTitle')]"));
        inputTitle.clear();
        inputTitle.sendKeys(testTitle);
        
        // Validate edition of the first Service
        WebElement validateRemoveButton = driver.findElement(By.xpath("//button[contains(@id, 'tabViewContractor:formService:editButtonDialogValidate')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", validateRemoveButton);
        
        // Test
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-growl-title")));
        WebElement label = driver.findElement(By.className("divServiceTitle"));
        
        // Verification
        assertEquals(testTitle, label.getText()); 
        
    }

    /**
     * Test of deleteService method, of class ServiceAddBean.
     * Test that a Service is well removed
     */
    @Test
    public void testDeleteService() {
        
        // Preparation
        int testSize = driver.findElements(By.className("divLeftContractorService")).size()-1;
        
        // Click on remove button of the first Service
        WebElement removeButton = driver.findElement(By.xpath("//button[contains(@id, 'tabViewContractor:formService:deleteCommandButton')]"));
        removeButton.click();
        // Validate remove button of the first Service
        WebElement validateRemoveButton = driver.findElement(By.xpath("//button[contains(@class, 'ui-confirmdialog-yes')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", validateRemoveButton);
        
        // Test
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-growl-title")));
        int newSize = driver.findElements(By.className("divLeftContractorService")).size();

        // Verification
        assertEquals(testSize, newSize);
                        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;
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
 *
 * @author nicolas
 */
public class AdminSearchBeanTest {
    
    
    private static WebDriver driver;
    private static String baseUrl;
    private final By tabMsgXpathExp = By.xpath("//a[contains(@href, 'tabMessages')]"),
                     searchButtonXpathExp = By.xpath("//button[contains(@id, 'tabViewAdmin:formResearch:searchButton')]"),
                     sendMessageToOneXpathExp = By.xpath("//button[contains(@id, 'tabViewAdmin:formSendMsgSoum:firstButtonSoum')]"),
                     inputMessageXpathExp =  By.xpath("//textarea[contains(@id, 'tabViewAdmin:sendMsgDialogForm:messageOut')]"),
                     sendMessageToOneFromDialogXpathExp = By.xpath("//button[contains(@id, 'tabViewAdmin:sendMsgDialogForm:sendButtonDialogValidate')]");

    
   
 

    @BeforeClass
    public static void setUpClass() {
        
        driver = new FirefoxDriver();
        baseUrl = "http://localhost:8080/Oops-web/views/adminManager.xhtml";
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
    /*
    @Test
    public void testSendMessageToTendererSucces(){
       
        // Preparation
        driver.get(baseUrl); 
        
        driver.get(baseUrl); 
        WebElement tabButton = driver.findElement(tabMsgXpathExp);
        tabButton.click();
          
        WebElement searchButton = driver.findElement(searchButtonXpathExp);
        searchButton.click();
   
        WebElement sendButton = driver.findElement(sendMessageToOneXpathExp);
        sendButton.click();
        
        WebElement inputTitle = driver.findElement(inputMessageXpathExp);
        inputTitle.clear();
        inputTitle.sendKeys("Bonjour monsieur");
        
        WebElement sendButtonDialog = driver.findElement(sendMessageToOneFromDialogXpathExp);
        sendButtonDialog.click();
        
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-growl-title")));
        
        // Test
        WebElement growlTitle = (new WebDriverWait(driver, 10))
        .until(ExpectedConditions.presenceOfElementLocated(By.className("ui-growl-title")));
        
        // Verification
        assertEquals(growlTitle.getText(), "Succès!");
   
    }
    */
       
    @Test
    public void testSendMessageToTendererEchec() throws InterruptedException{
       
        // Preparation
        driver.get(baseUrl); 
        
        driver.get(baseUrl); 
        WebElement tabButton = driver.findElement(tabMsgXpathExp);
        tabButton.click();
          
        WebElement searchButton = driver.findElement(searchButtonXpathExp);
        searchButton.click();
   
        WebElement sendButton = driver.findElement(sendMessageToOneXpathExp);
        sendButton.click();
        
        WebElement inputTitle = driver.findElement(inputMessageXpathExp);
        inputTitle.clear();
        inputTitle.sendKeys("");
        
        WebElement sendButtonDialog = driver.findElement(sendMessageToOneFromDialogXpathExp);
        sendButtonDialog.click();
    
        // Test
        WebElement growlTitle = (new WebDriverWait(driver, 10))
        .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@id, 'tabViewAdmin:sendMsgDialogForm:msgError')]")));
         
        WebElement errorMessageElement = driver.findElement(By.xpath("//*[contains(@id, 'tabViewAdmin:sendMsgDialogForm:msgError')]"));
       
    
         WebElement test = (new WebDriverWait(driver, 10))
        .until(ExpectedConditions. presenceOfElementLocated(By.xpath("//*[contains(@id, 'tabViewAdmin:sendMsgDialogForm:msgError')]")));
           // Verification
        
         driver.wait((long)10);
         
         assertTrue(errorMessageElement.isDisplayed());
             
    }
    
}

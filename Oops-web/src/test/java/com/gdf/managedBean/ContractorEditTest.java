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
 * @author bibo
 */

public class ContractorEditTest {
    
    private static WebDriver driver;
    private static String baseUrl;
    
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

   /*
    @Test
    public void testUpdateMdpSuccess() {
        driver.get(baseUrl);
        
    
        WebElement editButton = driver.findElement(By.xpath("//button[contains(@id, 'j_idt55:j_idt74:buttonModifyMdp')]"));
        editButton.click();
          
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@id, 'j_idt55:formModifyMdp:password')]")));
        
        WebElement inputPwd1 = driver.findElement(By.xpath("//input[contains(@id, 'j_idt55:formModifyMdp:password')]"));
        inputPwd1.clear();
        inputPwd1.sendKeys("Nico54840");
        
        WebElement inputPwd2 = driver.findElement(By.xpath("//input[contains(@id, 'j_idt55:formModifyMdp:confirmationPassword')]"));
        inputPwd2.clear();
        inputPwd2.sendKeys("Nico54840");
      
        
        
            // Validate edition of the first Review
        WebElement validateButton = driver.findElement(By.xpath("//button[contains(@id, 'j_idt55:formModifyMdp:j_idt106')]"));
        validateButton.click();
        
        
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-growl-title")));
        WebElement growlTitle = driver.findElement(By.className("ui-growl-title"));

        // Verification
        assertEquals(growlTitle.getText(), "Succès!");
              
    }
    */
    @Test
    public void testUpdateInformationSuccess() {
        driver.get(baseUrl);
        
    
        WebElement editButton = driver.findElement(By.xpath("//button[contains(@id, 'buttonModify')]"));
        editButton.click();
          
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@id, 'j_idt55:formModify:modifiedLogin')]")));
        
        WebElement inputTitle = driver.findElement(By.xpath("//input[contains(@id, 'j_idt55:formModify:modifiedLogin')]"));
        inputTitle.clear();
        inputTitle.sendKeys("Nico54840");
      
            // Validate edition of the first Review
        WebElement validateButton = driver.findElement(By.xpath("//button[contains(@id, 'j_idt55:formModify:j_idt94')]"));
        validateButton.click();
        
        
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-growl-title")));
        WebElement growlTitle = driver.findElement(By.className("ui-growl-title"));

        // Verification
        assertEquals(growlTitle.getText(), "Succès!");
              
    }
    
    
}

    
    
    
    


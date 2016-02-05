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
 * @author chris
 */
public class TendererInfoMngBeanTest {
    
    private static WebDriver driver;
    private static String baseUrl;
    
    public TendererInfoMngBeanTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        driver = new FirefoxDriver();
        baseUrl = "http://localhost:8080/Oops-web/views/tendererManagement.xhtml";
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }
    
    @AfterClass
    public static void tearDownClass() {
        //driver.close();
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of update method, of class TendererInfoMngBean.
     */
    @Test
    public void testUpdateSuccess() {
        driver.get(baseUrl);
        
        // Go to first tab
        List<WebElement> liElements = driver.findElements(By.xpath("//ul/li"));
        for (int i = 1; i < liElements.size()+1; i++) {
            WebElement linkElement = driver.findElement(By.xpath("//ul/li[" + i + "]/a"));
            if (linkElement.getText().equals("Mon compte")) {
                linkElement.click();
            }
        }
        
        // Click on 'modifier' button
        WebElement editButton = driver.findElement(By.xpath("//button[contains(@id, ':infoForm:modifier')]"));
        editButton.click();
        
        WebElement inputTitle = driver.findElement(By.xpath("//input[contains(@id, 'infoForm:firstname')]"));
        inputTitle.clear();
        inputTitle.sendKeys("testfirstname");
        
         // Validate edition of the tenderer
        WebElement validateButton = driver.findElement(By.xpath("//button[contains(@id, 'infoForm:enregistrer')]"));
        validateButton.click();
        
        // Test
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-growl-title")));
        WebElement name = driver.findElement(By.xpath("//*[contains(text(), 'testfirstname')]"));

        // Verification
        assertEquals("testfirstname", name.getText()); 
    }
    
}

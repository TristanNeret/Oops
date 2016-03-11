/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import java.util.ArrayList;
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
 * @author bibo
 */
public class TendererInvitationBeanTest {
    private static WebDriver driver;
    private static String baseUrl;
    
    private WebElement email, sendButton,messageErrMail;
    
    public TendererInvitationBeanTest(){
        
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
        List list =new ArrayList<WebElement>();
        int i = 1;
        while (test) {
            WebElement linkElement = driver.findElement(By.xpath("//ul/li[" + i + "]/a"));
             
            while (i<list.size()){
                System.out.print(list.get(i));
                i++;
            }
            if (linkElement.getText().equals("Invitations")) {
                linkElement.click();
                test = false;
            }
            i++;
        }
    }

    
    @After
    public void tearDown() {
        
    }
    /*
    // Send invitation with correct mail ex: guendouzbachir@hotmail.com
    */
    @Test
    public void send() {
        
        
        
      email = driver.findElement(By.xpath("//input[contains(@id, 'mailForm:email')]"));
      sendButton = driver.findElement(By.xpath("//button[contains(@id, 'mailForm:send')]"));
      
      
      email.clear();
      email.sendKeys("guendouzbachir@hotmail.com");
      
      
      sendButton.click();
      
      // Test
      WebElement growlTitle = (new WebDriverWait(driver, 10))
        .until(ExpectedConditions.presenceOfElementLocated(By.className("ui-growl-title")));
        
      // Verification
      assertEquals(growlTitle.getText(), "Message envoyé!");
      
    }
    
    @Test
    public void sendWrongMailFormat(){
        
      email = driver.findElement(By.xpath("//input[contains(@id, 'mailForm:email')]"));
      sendButton = driver.findElement(By.xpath("//button[contains(@id, 'mailForm:send')]"));
      messageErrMail =driver.findElement(By.xpath("//button[contains(@id, 'mailForm:messageErrMail')]"));
      
      email.clear();
      email.sendKeys("test@test.c");
      
      
      sendButton.click();
      
     
      // Verification
      assertEquals(messageErrMail.getText(), "Le format de l'addresse mail est incorrect !");
        
        
    }
    
    @Test
    public void sendEmptyMail(){
        
      email = driver.findElement(By.xpath("//input[contains(@id, 'mailForm:email')]"));
      sendButton = driver.findElement(By.xpath("//button[contains(@id, 'mailForm:send')]"));
      
      messageErrMail =driver.findElement(By.xpath("//button[contains(@id, 'mailForm:messageErrMail')]"));
      email.clear();
      
      
      sendButton.click();
      
      
      // Verification
      assertEquals(messageErrMail.getText(), "E-mail: Validation Error: Value is required.");
        
        
   }
    
    
}

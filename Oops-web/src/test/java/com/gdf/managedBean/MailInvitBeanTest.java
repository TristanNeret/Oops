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

/**
 *
 * @author chris
 */
public class MailInvitBeanTest {
    
//    private static WebDriver driver;
//    private static String baseUrl;
//    
    public MailInvitBeanTest() {
    }
//    
    @BeforeClass
    public static void setUpClass() {
//        driver = new FirefoxDriver();
//        baseUrl = "http://localhost:8080/Oops-web/views/tendererManagement.xhtml";
//        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
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
//    /**
//     * Test of sendMail method, of class MailInvitBean.
//     */
//    @Test
//    public void testSendMailSuccess() {
//        driver.get(baseUrl); 
//        
//        // Go to write Tab
//        boolean test = true;
//        int i = 1;
//        while (test) {
//            WebElement linkElement = driver.findElement(By.xpath("//ul/li[" + i + "]/a"));
//            if (linkElement.getText().equals("Invitations")) {
//                linkElement.click();
//                test = false;
//            }
//            i++;
//        }
//        
//        WebElement object = driver.findElement(By.xpath("//input[contains(@id, 'object')]"));
//        object.clear();
//        object.sendKeys("object");
//        
//        WebElement mail = driver.findElement(By.xpath("//input[contains(@id, 'email')]"));
//        mail.clear();
//        mail.sendKeys("a@a.com");
//        
//        WebElement text = driver.findElement(By.xpath("//textarea[contains(@id, 'message')]"));
//        text.clear();
//        text.sendKeys("text");
//        
//        WebElement send = driver.findElement(By.xpath("//button[contains(@id, 'send')]"));
//        send.click();
//        
//        // Test
//        WebElement growlTitle = driver.findElement(By.className("ui-growl-title"));
//
//        // Verification
//        assertEquals(growlTitle.getText(), "Message envoyé!");
//    }
//    
//    /**
//     * Test of sendMail method, of class MailInvitBean.
//     */
    @Test
    public void testSendMailEmpty() {
//        driver.get(baseUrl); 
//        
//        // Go to write Tab
//        boolean test = true;
//        int i = 1;
//        while (test) {
//            WebElement linkElement = driver.findElement(By.xpath("//ul/li[" + i + "]/a"));
//            if (linkElement.getText().equals("Invitations")) {
//                linkElement.click();
//                test = false;
//            }
//            i++;
//        }
//        
//        WebElement object = driver.findElement(By.xpath("//input[contains(@id, 'object')]"));
//        object.clear();
//        object.sendKeys("object");
//        
//        WebElement mail = driver.findElement(By.xpath("//input[contains(@id, 'email')]"));
//        mail.clear();
//
//        
//        WebElement text = driver.findElement(By.xpath("//textarea[contains(@id, 'message')]"));
//        text.clear();
//        text.sendKeys("message");
//        
//        WebElement send = driver.findElement(By.xpath("//button[contains(@id, 'send')]"));
//        send.click();
//        
//        // Test
//        WebElement growlTitle = driver.findElement(By.className("ui-growl-title"));
//
//        // Verification
//        assertEquals(growlTitle.getText(), "E-mail : erreur de validation. Vous devez indiquer une valeur.");
    }
//    
//    /**
//     * Test of sendMail method, of class MailInvitBean.
//     */
//    @Test
//    public void testSendMailInvalide() {
//        driver.get(baseUrl); 
//        
//        // Go to write Tab
//        boolean test = true;
//        int i = 1;
//        while (test) {
//            WebElement linkElement = driver.findElement(By.xpath("//ul/li[" + i + "]/a"));
//            if (linkElement.getText().equals("Invitations")) {
//                linkElement.click();
//                test = false;
//            }
//            i++;
//        }
//        
//        WebElement object = driver.findElement(By.xpath("//input[contains(@id, 'object')]"));
//        object.clear();
//        object.sendKeys("object");
//        
//        WebElement mail = driver.findElement(By.xpath("//input[contains(@id, 'email')]"));
//        mail.clear();
//        mail.sendKeys("dqsdqsdqsdqsdqsd");
//        
//        WebElement text = driver.findElement(By.xpath("//textarea[contains(@id, 'message')]"));
//        text.clear();
//        text.sendKeys("text");
//        
//        WebElement send = driver.findElement(By.xpath("//button[contains(@id, 'send')]"));
//        send.click();
//        
//        // Test
//        WebElement growlTitle = driver.findElement(By.className("ui-growl-title"));
//
//        // Verification
//        assertEquals(growlTitle.getText(), "Le format de l'addresse mail est incorrect.");
//    }
//    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
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
public class TendererRegistrationBeanTest {

    private static WebDriver driver;
    private static String baseUrl;

    private WebElement inputLogin, inputPassword, inputConfirmPassword, 
            inputMail, inputFirstname, inputLastName, inputPhone, inputAvatar, registerButton;

    @BeforeClass
    public static void setUpClass() {
        driver = new FirefoxDriver();
        baseUrl = "http://localhost:8080/Oops-web/views/tendererRegistration.xhtml";
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
    public void registration() {
        // Preparation
        driver.get(baseUrl); 
        
        inputLogin = driver.findElement(By.id("registerTendererForm:registerLogin"));
        inputPassword = driver.findElement(By.id("registerTendererForm:registerPassword"));
        inputConfirmPassword = driver.findElement(By.id("registerTendererForm:registerConfirmPassword"));
        inputMail = driver.findElement(By.id("registerTendererForm:registerMail"));
        inputFirstname = driver.findElement(By.id("registerTendererForm:registerFirstname"));
        inputLastName = driver.findElement(By.id("registerTendererForm:registerLastname"));
        registerButton = driver.findElement(By.id("registerTendererForm:tenderRegisterButton"));
        inputPhone = driver.findElement(By.id("registerTendererForm:registerPhoneNumber"));
        inputAvatar = driver.findElement(By.id("registerTendererForm:registerAvatar"));
        
        inputLogin.clear();
        inputLogin.sendKeys("SuperTenderer"+new Random().nextInt(1000));
        inputPassword.clear();
        inputPassword.sendKeys("password1234");
        inputConfirmPassword.clear();
        inputConfirmPassword.sendKeys("password1234");
        inputMail.clear();
        inputMail.sendKeys("super.tenderer@mail.com");
        inputFirstname.clear();
        inputFirstname.sendKeys("I'm the");
        inputLastName.clear();
        inputLastName.sendKeys("SuperTenderer");
        inputPhone.clear();
        inputPhone.sendKeys("2820092809");
        inputAvatar.clear();
        inputAvatar.sendKeys("http://www.batterytender.com/assets/img/logo_BTmainNav.png");
        
        registerButton.click();
        
         // Test
        WebElement growlTitle = (new WebDriverWait(driver, 10))
        .until(ExpectedConditions.presenceOfElementLocated(By.className("ui-growl-title")));
        
        // Verification
        assertEquals(growlTitle.getText(), "Inscription réussie !");
    }

}

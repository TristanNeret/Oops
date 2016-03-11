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
public class ContractorRegistrationBeanTest {

//    private static WebDriver driver;
//    private static String baseUrl;
//
//    private WebElement inputLogin, inputMdp, inputConfirmMdp,
//            inputMail, inputFirstname, inputLastName, inputPhone, next1Button;
//
//    private WebElement socialReasonInput, sirenInput, siretInput, rcsInput, insurranceInput,
//            streetInput, zipCodeInput, townInput, countryInput, next2Button;
//
//    private WebElement logoInput, descriptionInput, next3Button;
//
//    private WebElement titleServiceInput, serviceContentInput, priceServiceInput,
//            addServiceButton, contractorFinishButton;
//
    @BeforeClass
    public static void setUpClass() {
//        driver = new FirefoxDriver();
//        baseUrl = "http://localhost:8080/Oops-web/views/contractorRegistration.xhtml";
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
    @Test
    public void registration() {
//        // Preparation
//        driver.get(baseUrl);
//
//        // STEP 1
//        inputLogin = driver.findElement(By.id("registerContractorForm1:contractorLogin"));
//        inputMdp = driver.findElement(By.id("registerContractorForm1:contractorPassword"));
//        inputConfirmMdp = driver.findElement(By.id("registerContractorForm1:contractorConfirmPassword"));
//        inputMail = driver.findElement(By.id("registerContractorForm1:contractorMail"));
//        inputFirstname = driver.findElement(By.id("registerContractorForm1:contractorFirstname"));
//        inputLastName = driver.findElement(By.id("registerContractorForm1:contractorLastname"));
//        inputPhone = driver.findElement(By.id("registerContractorForm1:contractorPhoneNumber"));
//
//        inputLogin.clear();
//        inputLogin.sendKeys("SuperContractor" + new Random().nextInt(1000));
//        inputMdp.clear();
//        inputMdp.sendKeys("password1234");
//        inputConfirmMdp.clear();
//        inputConfirmMdp.sendKeys("password1234");
//        inputMail.clear();
//        inputMail.sendKeys("super.contractor@mail.com");
//        inputFirstname.clear();
//        inputFirstname.sendKeys("I'm the");
//        inputLastName.clear();
//        inputLastName.sendKeys("SuperContractor");
//        inputPhone.clear();
//        inputPhone.sendKeys("1234567890");
//
//        next1Button = driver.findElement(By.id("registerContractorForm1:contractorNext1Button"));
//        next1Button.click();
//
//        // STEP 2
//        socialReasonInput = driver.findElement(By.id("registerContractorForm2:contractorSocialReason"));
//        sirenInput = driver.findElement(By.id("registerContractorForm2:contractorSiren"));
//        siretInput = driver.findElement(By.id("registerContractorForm2:contractorSiret"));
//        rcsInput = driver.findElement(By.id("registerContractorForm2:contractorRcs"));
//        insurranceInput = driver.findElement(By.id("registerContractorForm2:contractorInsurrance"));
//        streetInput = driver.findElement(By.id("registerContractorForm2:contractorStreet"));
//        zipCodeInput = driver.findElement(By.id("registerContractorForm2:contractorZipCode"));
//        townInput = driver.findElement(By.id("registerContractorForm2:contractorTown"));
//        countryInput = driver.findElement(By.id("registerContractorForm2:contractorCountry"));
//
//        socialReasonInput.clear();
//        socialReasonInput.sendKeys("SuperContractor");
//        sirenInput.clear();
//        sirenInput.sendKeys("123456789");
//        siretInput.clear();
//        siretInput.sendKeys("12345678911234");
//        rcsInput.clear();
//        rcsInput.sendKeys("9837937937COIJDEIOJJ");
//        insurranceInput.clear();
//        insurranceInput.sendKeys("Assurtourix");
//        streetInput.clear();
//        streetInput.sendKeys("Rue des gens géniaux");
//        zipCodeInput.clear();
//        zipCodeInput.sendKeys("7500");
//        townInput.clear();
//        townInput.sendKeys("Paris");
//        countryInput.clear();
//        countryInput.sendKeys("France");
//
//        next2Button = driver.findElement(By.id("registerContractorForm2:contractorNext2Button"));
//        next2Button.click();
//
//        // STEP 3
//        logoInput = driver.findElement(By.id("registerContractorForm3:contractorLogo"));
//        descriptionInput = driver.findElement(By.id("registerContractorForm3:contractorDescription"));
//
//        logoInput.clear();
//        logoInput.sendKeys("https://s-media-cache-ak0.pinimg.com/originals/5f/03/4d/5f034d56cbf94ee436f9033bdace59f6.jpg");
//        descriptionInput.clear();
//        descriptionInput.sendKeys("En littérature, la description constitue une pause dans le récit, où elle peut former un ensemble autonome, bien que le plus souvent elle prenne place dans la ...");
//
//        next3Button = driver.findElement(By.id("registerContractorForm3:contractorNext3Button"));
//        next3Button.click();
//
//        // STEP 4
//        titleServiceInput = driver.findElement(By.id("registerContractorForm4:titleService"));
//        serviceContentInput = driver.findElement(By.id("registerContractorForm4:serviceContent"));
//        priceServiceInput = driver.findElement(By.id("registerContractorForm4:priceService"));    
//
//        titleServiceInput.clear();
//        titleServiceInput.sendKeys("Mon premier service");
//        serviceContentInput.clear();
//        serviceContentInput.sendKeys("C'est mon premier service et je suis tellement heureux de vous le rendre, mais c'est pas gratuit!");
//        priceServiceInput.clear();
//        priceServiceInput.sendKeys("1000");
//        
//        addServiceButton = driver.findElement(By.id("registerContractorForm4:addServiceButton"));
//        addServiceButton.click();
//        
//        contractorFinishButton = (new WebDriverWait(driver, 10))
//                .until(ExpectedConditions.presenceOfElementLocated(By.id("registerContractorForm4:contractorFinishButton")));
//        contractorFinishButton.click();
//        
//        
//        // Test
//        WebElement growlTitle = (new WebDriverWait(driver, 10))
//                .until(ExpectedConditions.presenceOfElementLocated(By.className("ui-growl-title")));
//
//        // Verification
//        assertEquals(growlTitle.getText(), "Votre prestation a été ajoutée avec succès !");
//        //assertEquals(growlTitle.getText(), "Inscription finalisée !");
    }

}

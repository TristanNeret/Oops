/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdf.managedBean;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * SearchManagedTest
 * @author aziz
 */
public class SearchManagedTest {

    private final By searchButtonXpathExp = By.xpath("//input[contains(@id, 'searchButton')]"),
            typeListXpathExp = By.xpath("//select[contains(@id, 'listType')]"),
            queryInputTextXpathExp = By.xpath("//input[contains(@id, 'queryInputText')]"),
            ratingListXpathExp = By.xpath("//select[contains(@id, 'listRating')]"),
            countryListXpathExp = By.xpath("//select[contains(@id, 'listCountries')]"),
            categoryListXpathExp = By.xpath("//select[contains(@id, 'listCategories')]"),
            sortListXpathExp = By.xpath("//select[contains(@id, 'listOrder')]");
           

    private static WebDriver driver;
    private static String baseUrl;

    @BeforeClass
    public static void setUpClass() {
        driver = new FirefoxDriver();
        baseUrl = "http://localhost:8080/Oops-web/index.xhtml";
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
     * TESTS FOR TENDERER SEARCH ---------------------------------------------------------------------------------------------
     */
    private static final String TENDERER_QUERY = "mich", TENDERER_EXPECTED = "Michmich", TENDERER_EXPECTED_2 = "Dede";

    @Test
    public void testTendererSearchWithoutQuery() {
        driver.get(baseUrl);

        driver.findElement(queryInputTextXpathExp).clear();
        Select select = new Select(driver.findElement(typeListXpathExp));
        select.selectByVisibleText("Soumissionnaire");
        driver.findElement(searchButtonXpathExp).click();

        driver.findElement(By.xpath("//span[text() = \"" + TENDERER_EXPECTED + "\"]"));
        driver.findElement(By.xpath("//span[text() = \"" + TENDERER_EXPECTED_2 + "\"]"));
    }

    @Test
    public void testTendererSearchWithQueryWithoutResult() {
        driver.get(baseUrl);

        driver.findElement(queryInputTextXpathExp).clear();
        driver.findElement(queryInputTextXpathExp).sendKeys(RandomStringUtils.randomAscii(10));
        Select select = new Select(driver.findElement(typeListXpathExp));
        select.selectByVisibleText("Soumissionnaire");
        driver.findElement(searchButtonXpathExp).click();

        driver.findElement(By.id("noResultOutputText"));
    }

    @Test
    public void testTendererSearchWithQueryWithResult() {
        driver.get(baseUrl);

        driver.findElement(queryInputTextXpathExp).clear();
        driver.findElement(queryInputTextXpathExp).sendKeys(TENDERER_QUERY);
        Select select = new Select(driver.findElement(typeListXpathExp));
        select.selectByVisibleText("Soumissionnaire");
        driver.findElement(searchButtonXpathExp).click();

        driver.findElement(By.xpath("//span[text() = \"" + TENDERER_EXPECTED + "\"]"));
    }

    /*
     * TESTS FOR CONTRACTOR SEARCH ---------------------------------------------------------------------------------------------
     */
    private static final String CONTRACTOR_QUERY = "IT Contractor", CONTRACTOR_EXPECTED = "IT Contractor Inc.",
            CONTRACTOR_EXPECTED_2 = "FranceBTP Sarl",
            RATING_0 = ">=4", RATING_1 = ">=3", RATING_2 = ">=2", // find 0, 1, and 2 contractor(s)
            COUNTRY_1 = "USA", COUNTRY_2 = "France",
            CATEGORY_1 = "Informatique", CATEGORY_2 = "Batiment";

    //@Test
    public void testContractorSearchWithoutQuery() {
        driver.get(baseUrl);

        driver.findElement(queryInputTextXpathExp).clear();
        new Select(driver.findElement(typeListXpathExp)).selectByVisibleText("Prestataire");
        new Select(driver.findElement(ratingListXpathExp)).selectByIndex(0);
        new Select(driver.findElement(countryListXpathExp)).selectByIndex(0);
        new Select(driver.findElement(categoryListXpathExp)).selectByIndex(0);
        driver.findElement(searchButtonXpathExp).click();

        driver.findElement(By.xpath("//span[text() = \"" + CONTRACTOR_EXPECTED + "\"]"));
        driver.findElement(By.xpath("//span[text() = \"" + CONTRACTOR_EXPECTED_2 + "\"]"));
    }

    @Test
    public void testContractorSearchWithQueryWithoutResult() {
        driver.get(baseUrl);

        driver.findElement(queryInputTextXpathExp).clear();
        driver.findElement(queryInputTextXpathExp).sendKeys(RandomStringUtils.randomAscii(10));
        new Select(driver.findElement(typeListXpathExp)).selectByVisibleText("Prestataire");
        new Select(driver.findElement(ratingListXpathExp)).selectByIndex(0);
        new Select(driver.findElement(countryListXpathExp)).selectByIndex(0);
        new Select(driver.findElement(categoryListXpathExp)).selectByIndex(0);
        driver.findElement(searchButtonXpathExp).click();

        driver.findElement(By.id("noResultOutputText"));
    }

    @Test
    public void testContractorSearchWithQueryWithResult() {
        driver.get(baseUrl);

        driver.findElement(queryInputTextXpathExp).clear();
        driver.findElement(queryInputTextXpathExp).sendKeys(CONTRACTOR_QUERY);
        new Select(driver.findElement(typeListXpathExp)).selectByVisibleText("Prestataire");
        new Select(driver.findElement(ratingListXpathExp)).selectByIndex(0);
        new Select(driver.findElement(countryListXpathExp)).selectByIndex(0);
        new Select(driver.findElement(categoryListXpathExp)).selectByIndex(0);
        driver.findElement(searchButtonXpathExp).click();

        driver.findElement(By.xpath("//span[text() = \"" + CONTRACTOR_EXPECTED + "\"]"));
    }

    @Test
    public void testContractorSearchWithRatingSelected2Results() {
        driver.get(baseUrl);

        driver.findElement(queryInputTextXpathExp).clear();
        new Select(driver.findElement(typeListXpathExp)).selectByVisibleText("Prestataire");
        new Select(driver.findElement(ratingListXpathExp)).selectByVisibleText(RATING_2);
        new Select(driver.findElement(countryListXpathExp)).selectByIndex(0);
        new Select(driver.findElement(categoryListXpathExp)).selectByIndex(0);
        driver.findElement(searchButtonXpathExp).click();

        driver.findElement(By.xpath("//span[text() = \"" + CONTRACTOR_EXPECTED + "\"]"));
        driver.findElement(By.xpath("//span[text() = \"" + CONTRACTOR_EXPECTED_2 + "\"]"));
    }

    @Test
    public void testContractorSearchWithRatingSelected0Result() {
        driver.get(baseUrl);

        driver.findElement(queryInputTextXpathExp).clear();
        new Select(driver.findElement(typeListXpathExp)).selectByVisibleText("Prestataire");
        new Select(driver.findElement(ratingListXpathExp)).selectByVisibleText(RATING_0);
        new Select(driver.findElement(countryListXpathExp)).selectByIndex(0);
        new Select(driver.findElement(categoryListXpathExp)).selectByIndex(0);
        driver.findElement(searchButtonXpathExp).click();

        driver.findElement(By.id("noResultOutputText"));
    }

    @Test
    public void testContractorSearchWithRatingSelected1Result() {
        driver.get(baseUrl);

        driver.findElement(queryInputTextXpathExp).clear();
        new Select(driver.findElement(typeListXpathExp)).selectByVisibleText("Prestataire");
        new Select(driver.findElement(ratingListXpathExp)).selectByVisibleText(RATING_1);
        new Select(driver.findElement(countryListXpathExp)).selectByIndex(0);
        new Select(driver.findElement(categoryListXpathExp)).selectByIndex(0);
        driver.findElement(searchButtonXpathExp).click();

        driver.findElement(By.xpath("//span[text() = \"" + CONTRACTOR_EXPECTED + "\"]"));
    }

    @Test
    public void testContractorSearchWithCountrySelected1() {
        driver.get(baseUrl);

        driver.findElement(queryInputTextXpathExp).clear();
        new Select(driver.findElement(typeListXpathExp)).selectByVisibleText("Prestataire");
        new Select(driver.findElement(ratingListXpathExp)).selectByIndex(0);
        new Select(driver.findElement(countryListXpathExp)).selectByVisibleText(COUNTRY_1);
        new Select(driver.findElement(categoryListXpathExp)).selectByIndex(0);
        driver.findElement(searchButtonXpathExp).click();

        driver.findElement(By.xpath("//span[text() = \"" + CONTRACTOR_EXPECTED + "\"]"));
    }

    @Test
    public void testContractorSearchWithCountrySelected2() {
        driver.get(baseUrl);

        driver.findElement(queryInputTextXpathExp).clear();
        new Select(driver.findElement(typeListXpathExp)).selectByVisibleText("Prestataire");
        new Select(driver.findElement(ratingListXpathExp)).selectByIndex(0);
        new Select(driver.findElement(countryListXpathExp)).selectByVisibleText(COUNTRY_2);
        new Select(driver.findElement(categoryListXpathExp)).selectByIndex(0);
        driver.findElement(searchButtonXpathExp).click();

        driver.findElement(By.xpath("//span[text() = \"" + CONTRACTOR_EXPECTED_2 + "\"]"));
    }

    @Test
    public void testContractorSearchWithCategorySelected1() {
        driver.get(baseUrl);

        driver.findElement(queryInputTextXpathExp).clear();
        new Select(driver.findElement(typeListXpathExp)).selectByVisibleText("Prestataire");
        new Select(driver.findElement(ratingListXpathExp)).selectByIndex(0);
        new Select(driver.findElement(countryListXpathExp)).selectByIndex(0);
        new Select(driver.findElement(categoryListXpathExp)).selectByVisibleText(CATEGORY_1);
        driver.findElement(searchButtonXpathExp).click();

        driver.findElement(By.xpath("//span[text() = \"" + CONTRACTOR_EXPECTED + "\"]"));
    }

    @Test
    public void testContractorSearchWithCategorySelected2() {
        driver.get(baseUrl);

        driver.findElement(queryInputTextXpathExp).clear();
        new Select(driver.findElement(typeListXpathExp)).selectByVisibleText("Prestataire");
        new Select(driver.findElement(ratingListXpathExp)).selectByIndex(0);
        new Select(driver.findElement(countryListXpathExp)).selectByIndex(0);
        new Select(driver.findElement(categoryListXpathExp)).selectByVisibleText(CATEGORY_2);
        driver.findElement(searchButtonXpathExp).click();

        driver.findElement(By.xpath("//span[text() = \"" + CONTRACTOR_EXPECTED_2 + "\"]"));;
    }

    @Test
    public void testContractorSearchWithQueryTypedAndRatingSelected1() {
        driver.get(baseUrl);

        driver.findElement(queryInputTextXpathExp).clear();
        driver.findElement(queryInputTextXpathExp).sendKeys(CONTRACTOR_QUERY);
        new Select(driver.findElement(typeListXpathExp)).selectByVisibleText("Prestataire");
        new Select(driver.findElement(ratingListXpathExp)).selectByVisibleText(RATING_2);
        new Select(driver.findElement(countryListXpathExp)).selectByIndex(0);
        new Select(driver.findElement(categoryListXpathExp)).selectByIndex(0);
        driver.findElement(searchButtonXpathExp).click();

        driver.findElement(By.xpath("//span[text() = \"" + CONTRACTOR_EXPECTED + "\"]"));
    }

    @Test
    public void testContractorSearchWithQueryTypedAndRatingSelected2() {
        driver.get(baseUrl);

        driver.findElement(queryInputTextXpathExp).clear();
        driver.findElement(queryInputTextXpathExp).sendKeys(CONTRACTOR_QUERY);
        new Select(driver.findElement(typeListXpathExp)).selectByVisibleText("Prestataire");
        new Select(driver.findElement(ratingListXpathExp)).selectByVisibleText(RATING_0);
        new Select(driver.findElement(countryListXpathExp)).selectByIndex(0);
        new Select(driver.findElement(categoryListXpathExp)).selectByIndex(0);
        driver.findElement(searchButtonXpathExp).click();

        driver.findElement(By.id("noResultOutputText"));
    }

    @Test
    public void testContractorSearchWithQueryTypedAndCountrySelected1() {
        driver.get(baseUrl);

        driver.findElement(queryInputTextXpathExp).clear();
        driver.findElement(queryInputTextXpathExp).sendKeys(CONTRACTOR_QUERY);
        new Select(driver.findElement(typeListXpathExp)).selectByVisibleText("Prestataire");
        new Select(driver.findElement(ratingListXpathExp)).selectByIndex(0);
        new Select(driver.findElement(countryListXpathExp)).selectByVisibleText(COUNTRY_1);
        new Select(driver.findElement(categoryListXpathExp)).selectByIndex(0);
        driver.findElement(searchButtonXpathExp).click();

        driver.findElement(By.xpath("//span[text() = \"" + CONTRACTOR_EXPECTED + "\"]"));
    }

    @Test
    public void testContractorSearchWithQueryTypedAndCountrySelected2() {
        driver.get(baseUrl);

        driver.findElement(queryInputTextXpathExp).clear();
        driver.findElement(queryInputTextXpathExp).sendKeys(CONTRACTOR_QUERY);
        new Select(driver.findElement(typeListXpathExp)).selectByVisibleText("Prestataire");
        new Select(driver.findElement(ratingListXpathExp)).selectByIndex(0);
        new Select(driver.findElement(countryListXpathExp)).selectByVisibleText(COUNTRY_2);
        new Select(driver.findElement(categoryListXpathExp)).selectByIndex(0);
        driver.findElement(searchButtonXpathExp).click();

        driver.findElement(By.id("noResultOutputText"));
    }

    @Test
    public void testContractorSearchWithQueryTypedAndCategorySelected1() {
        driver.get(baseUrl);

        driver.findElement(queryInputTextXpathExp).clear();
        driver.findElement(queryInputTextXpathExp).sendKeys(CONTRACTOR_QUERY);
        new Select(driver.findElement(typeListXpathExp)).selectByVisibleText("Prestataire");
        new Select(driver.findElement(ratingListXpathExp)).selectByIndex(0);
        new Select(driver.findElement(countryListXpathExp)).selectByIndex(0);
        new Select(driver.findElement(categoryListXpathExp)).selectByVisibleText(CATEGORY_1);
        driver.findElement(searchButtonXpathExp).click();

        driver.findElement(By.xpath("//span[text() = \"" + CONTRACTOR_EXPECTED + "\"]"));
    }

    @Test
    public void testContractorSearchWithQueryTypedAndCategorySelected2() {
        driver.get(baseUrl);

        driver.findElement(queryInputTextXpathExp).clear();
        driver.findElement(queryInputTextXpathExp).sendKeys(CONTRACTOR_QUERY);
        new Select(driver.findElement(typeListXpathExp)).selectByVisibleText("Prestataire");
        new Select(driver.findElement(ratingListXpathExp)).selectByIndex(0);
        new Select(driver.findElement(countryListXpathExp)).selectByIndex(0);
        new Select(driver.findElement(categoryListXpathExp)).selectByVisibleText(CATEGORY_2);
        driver.findElement(searchButtonXpathExp).click();

        driver.findElement(By.id("noResultOutputText"));
    }

    @Test
    public void testContractorSearchWithRatingAndCountrySelected1() {
        driver.get(baseUrl);

        driver.findElement(queryInputTextXpathExp).clear();
        new Select(driver.findElement(typeListXpathExp)).selectByVisibleText("Prestataire");
        new Select(driver.findElement(ratingListXpathExp)).selectByVisibleText(RATING_2);
        new Select(driver.findElement(countryListXpathExp)).selectByVisibleText(COUNTRY_1);
        new Select(driver.findElement(categoryListXpathExp)).selectByIndex(0);
        driver.findElement(searchButtonXpathExp).click();

        driver.findElement(By.xpath("//span[text() = \"" + CONTRACTOR_EXPECTED + "\"]"));
    }

    @Test
    public void testContractorSearchWithRatingAndCountrySelected2() {
        driver.get(baseUrl);

        driver.findElement(queryInputTextXpathExp).clear();
        new Select(driver.findElement(typeListXpathExp)).selectByVisibleText("Prestataire");
        new Select(driver.findElement(ratingListXpathExp)).selectByVisibleText(RATING_2);
        new Select(driver.findElement(countryListXpathExp)).selectByVisibleText(COUNTRY_2);
        new Select(driver.findElement(categoryListXpathExp)).selectByIndex(0);
        driver.findElement(searchButtonXpathExp).click();

        driver.findElement(By.xpath("//span[text() = \"" + CONTRACTOR_EXPECTED_2 + "\"]"));
    }

    @Test
    public void testContractorSearchWithRatingAndCategorySelected1() {
        driver.get(baseUrl);

        driver.findElement(queryInputTextXpathExp).clear();
        new Select(driver.findElement(typeListXpathExp)).selectByVisibleText("Prestataire");
        new Select(driver.findElement(ratingListXpathExp)).selectByVisibleText(RATING_2);
        new Select(driver.findElement(countryListXpathExp)).selectByIndex(0);
        new Select(driver.findElement(categoryListXpathExp)).selectByVisibleText(CATEGORY_1);
        driver.findElement(searchButtonXpathExp).click();

        driver.findElement(By.xpath("//span[text() = \"" + CONTRACTOR_EXPECTED + "\"]"));
    }

    @Test
    public void testContractorSearchWithRatingAndCategorySelected2() {
        driver.get(baseUrl);

        driver.findElement(queryInputTextXpathExp).clear();
        new Select(driver.findElement(typeListXpathExp)).selectByVisibleText("Prestataire");
        new Select(driver.findElement(ratingListXpathExp)).selectByVisibleText(RATING_2);
        new Select(driver.findElement(countryListXpathExp)).selectByIndex(0);
        new Select(driver.findElement(categoryListXpathExp)).selectByVisibleText(CATEGORY_2);
        driver.findElement(searchButtonXpathExp).click();

        driver.findElement(By.xpath("//span[text() = \"" + CONTRACTOR_EXPECTED_2 + "\"]"));
    }

    @Test
    public void testContractorSearchWithCountryAndCategorySelected1() {
        driver.get(baseUrl);

        driver.findElement(queryInputTextXpathExp).clear();
        new Select(driver.findElement(typeListXpathExp)).selectByVisibleText("Prestataire");
        new Select(driver.findElement(ratingListXpathExp)).selectByIndex(0);
        new Select(driver.findElement(countryListXpathExp)).selectByVisibleText(COUNTRY_1);
        new Select(driver.findElement(categoryListXpathExp)).selectByVisibleText(CATEGORY_1);
        driver.findElement(searchButtonXpathExp).click();

        driver.findElement(By.xpath("//span[text() = \"" + CONTRACTOR_EXPECTED + "\"]"));
    }

    @Test
    public void testContractorSearchWithCountryAndCategorySelected2() {
        driver.get(baseUrl);

        driver.findElement(queryInputTextXpathExp).clear();
        new Select(driver.findElement(typeListXpathExp)).selectByVisibleText("Prestataire");
        new Select(driver.findElement(ratingListXpathExp)).selectByIndex(0);
        new Select(driver.findElement(countryListXpathExp)).selectByVisibleText(COUNTRY_2);
        new Select(driver.findElement(categoryListXpathExp)).selectByVisibleText(CATEGORY_2);
        driver.findElement(searchButtonXpathExp).click();

        driver.findElement(By.xpath("//span[text() = \"" + CONTRACTOR_EXPECTED_2 + "\"]"));
    }

    @Test
    public void testContractorSearchWithQueryTypedRatingAndCountrySelected1() {
        driver.get(baseUrl);

        driver.findElement(queryInputTextXpathExp).clear();
        driver.findElement(queryInputTextXpathExp).sendKeys(CONTRACTOR_QUERY);
        new Select(driver.findElement(typeListXpathExp)).selectByVisibleText("Prestataire");
        new Select(driver.findElement(ratingListXpathExp)).selectByVisibleText(RATING_2);
        new Select(driver.findElement(countryListXpathExp)).selectByVisibleText(COUNTRY_1);
        new Select(driver.findElement(categoryListXpathExp)).selectByIndex(0);
        driver.findElement(searchButtonXpathExp).click();

        driver.findElement(By.xpath("//span[text() = \"" + CONTRACTOR_EXPECTED + "\"]"));
    }

    @Test
    public void testContractorSearchWithQueryTypedRatingAndCountrySelected2() {
        driver.get(baseUrl);

        driver.findElement(queryInputTextXpathExp).clear();
        driver.findElement(queryInputTextXpathExp).sendKeys(CONTRACTOR_QUERY);
        new Select(driver.findElement(typeListXpathExp)).selectByVisibleText("Prestataire");
        new Select(driver.findElement(ratingListXpathExp)).selectByVisibleText(RATING_2);
        new Select(driver.findElement(countryListXpathExp)).selectByVisibleText(COUNTRY_2);
        new Select(driver.findElement(categoryListXpathExp)).selectByIndex(0);
        driver.findElement(searchButtonXpathExp).click();

        driver.findElement(By.id("noResultOutputText"));
    }

    @Test
    public void testContractorSearchWithQueryTypedRatingAndCategorySelected1() {
        driver.get(baseUrl);

        driver.findElement(queryInputTextXpathExp).clear();
        driver.findElement(queryInputTextXpathExp).sendKeys(CONTRACTOR_QUERY);
        new Select(driver.findElement(typeListXpathExp)).selectByVisibleText("Prestataire");
        new Select(driver.findElement(ratingListXpathExp)).selectByVisibleText(RATING_2);
        new Select(driver.findElement(countryListXpathExp)).selectByIndex(0);
        new Select(driver.findElement(categoryListXpathExp)).selectByVisibleText(CATEGORY_1);
        driver.findElement(searchButtonXpathExp).click();

        driver.findElement(By.xpath("//span[text() = \"" + CONTRACTOR_EXPECTED + "\"]"));
    }

    @Test
    public void testContractorSearchWithQueryTypedRatingAndCategorySelected2() {
        driver.get(baseUrl);

        driver.findElement(queryInputTextXpathExp).clear();
        driver.findElement(queryInputTextXpathExp).sendKeys(CONTRACTOR_QUERY);
        new Select(driver.findElement(typeListXpathExp)).selectByVisibleText("Prestataire");
        new Select(driver.findElement(ratingListXpathExp)).selectByVisibleText(RATING_2);
        new Select(driver.findElement(countryListXpathExp)).selectByIndex(0);
        new Select(driver.findElement(categoryListXpathExp)).selectByVisibleText(CATEGORY_2);
        driver.findElement(searchButtonXpathExp).click();

        driver.findElement(By.id("noResultOutputText"));
    }

    @Test
    public void testContractorSearchWithQueryTypedCountryAndCategorySelected1() {
        driver.get(baseUrl);

        driver.findElement(queryInputTextXpathExp).clear();
        driver.findElement(queryInputTextXpathExp).sendKeys(CONTRACTOR_QUERY);
        new Select(driver.findElement(typeListXpathExp)).selectByVisibleText("Prestataire");
        new Select(driver.findElement(ratingListXpathExp)).selectByIndex(0);
        new Select(driver.findElement(countryListXpathExp)).selectByVisibleText(COUNTRY_1);
        new Select(driver.findElement(categoryListXpathExp)).selectByVisibleText(CATEGORY_1);
        driver.findElement(searchButtonXpathExp).click();

        driver.findElement(By.xpath("//span[text() = \"" + CONTRACTOR_EXPECTED + "\"]"));
    }

    @Test
    public void testContractorSearchWithQueryTypedCountryAndCategorySelected2() {
        driver.get(baseUrl);

        driver.findElement(queryInputTextXpathExp).clear();
        driver.findElement(queryInputTextXpathExp).sendKeys(CONTRACTOR_QUERY);
        new Select(driver.findElement(typeListXpathExp)).selectByVisibleText("Prestataire");
        new Select(driver.findElement(ratingListXpathExp)).selectByIndex(0);
        new Select(driver.findElement(countryListXpathExp)).selectByVisibleText(COUNTRY_2);
        new Select(driver.findElement(categoryListXpathExp)).selectByVisibleText(CATEGORY_2);
        driver.findElement(searchButtonXpathExp).click();

        driver.findElement(By.id("noResultOutputText"));
    }
    
    
    @Test 
    public void testContractorSortByInAlphabeticalOrder(){
        driver.get(baseUrl); 
        driver.findElement(queryInputTextXpathExp).clear();
        new Select(driver.findElement(typeListXpathExp)).selectByVisibleText("Prestataire");
        new Select(driver.findElement(ratingListXpathExp)).selectByIndex(0);
        new Select(driver.findElement(countryListXpathExp)).selectByIndex(0);
        new Select(driver.findElement(categoryListXpathExp)).selectByIndex(0);
        driver.findElement(searchButtonXpathExp).click();     
        new Select(driver.findElement(sortListXpathExp)).selectByVisibleText("Nom");
        driver.findElement(By.xpath("//fieldset[contains(@id,'listContractor')][1]/span[contains(text(), 'FranceBTP Sarl')]"));   
    }
    
    @Test 
    public void testContractorSortByRating(){
        driver.get(baseUrl); 
        driver.findElement(queryInputTextXpathExp).clear();
        new Select(driver.findElement(typeListXpathExp)).selectByVisibleText("Prestataire");
        new Select(driver.findElement(ratingListXpathExp)).selectByIndex(0);
        new Select(driver.findElement(countryListXpathExp)).selectByIndex(0);
        new Select(driver.findElement(categoryListXpathExp)).selectByIndex(0);
        driver.findElement(searchButtonXpathExp).click();     
        new Select(driver.findElement(sortListXpathExp)).selectByVisibleText("Note");
        driver.findElement(By.xpath("//fieldset[contains(@id,'listContractor')][1]/span[contains(text(), 'IT Contractor Inc')]"));   
    }
 
}

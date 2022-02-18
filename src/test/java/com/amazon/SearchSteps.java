package com.amazon;

import com.amazon.fw.AbstractTest;
import com.amazon.fw.Util;
import com.amazon.pages.HomePage;
import com.amazon.pages.ItemPage;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SearchSteps extends AbstractTest {

    //Pages needed for this test scenario
    HomePage homePage;
    ItemPage itemPage;
    byte[] screenshot;

    // context needed for various steps
    List<WebElement> resultsList;
    List<String> resultItemsUrl = new ArrayList<>();
    StringBuffer stream = new StringBuffer();

    public SearchSteps() {
        homePage = new HomePage(browser, properties.getProperty("base.url"));
        stream.append("prodducts&desc,seller,price,review").append("\n");
    }

    @Before
    public void setUp(Scenario scenario){
        this.scenario = scenario;
        logger.info("========== Scenario: "
                + scenario.getName() + ": Line: " + scenario.getLine() + " started ==========");
    }

    @Given("Application is launched")
    public void applicationIsLaunched() {
        logger.info("Launched");
    }

    @And("Sort price high to low option is clicked")
    public void sortPriceHighToLowOptionIsClicked() throws InterruptedException {
        homePage.sortResults("blah blah blah");
    }

//    @AfterStep
    public void addScreenShot(Scenario scenario) {
        screenShot("");
    }

    @After
    public void closeBrower() {
        browser.close();
    }

    @When("{string} is searched and sorted with {string}")
    public void itemIsSearchedAndSortedWithSort(String item, String sortOption) {
        logger.info("Item searched: " + item);
//        homePage.searchItem(item);
        homePage.searchViaUrl(item, sortOption);
        screenShot("Launched");
    }

    @Then("Search results should be displayed")
    public void searchResultsShouldBeDisplayed() {
        resultsList = homePage.getSearchResults();
        Assert.assertTrue("Result count is zero", resultsList.size() > 0);
        screenShot("Results");
    }

    @And("Fetch urls into context for {int} individual items")
    public void fetchUrlsIntoContextForCountIndividualItems(int count) {
        int size = resultsList.size();
        logger.info("Size of results: " + size);
        if (count > size) {
            logger.warn("Requested count is higher than actual results. Only maximum result will be considered");
            count = size;
        }

        for (int i=0; i < count; i++) {
            resultItemsUrl.add(resultsList.get(i).findElement(By.xpath("./..")).getAttribute("href"));
//            logger.info(resultItemsUrl.get(i));
        }
    }

    @Then("Product should be displayed with seller, price and review details")
    public void productShouldBeDisplayedWithSellerPriceAndReviewDetails() {

    }

    @When("Open each item and fetch seller, price and review details")
    public void openEachItemAndFetchSellerPriceAndReviewDetails() {
        itemPage = new ItemPage(browser);
        for (String url : resultItemsUrl) {
            itemPage.navigateTo(url);
            String temp = itemPage.getProductTitle() + "," + itemPage.getSellerName() + "," + itemPage.getPrice() + ","
                    + itemPage.getReview();
            logger.info("Product Name &  Desc: " + itemPage.getProductTitle());
            logger.info("Seller Name: " + itemPage.getSellerName());
            logger.info("Price: " + itemPage.getPrice());
            logger.info("Review: " + itemPage.getReview());
            stream.append(temp).append("\n");
        }
    }

    private void write2File(String fileName) throws IOException {
        FileWriter fWriter = new FileWriter(fileName);
        fWriter.write(String.valueOf(stream));
        fWriter.close();
    }

    @And("Write gathered information for item: {string} into csv file")
    public void writeGatheredInformationForItemItemIntoCsvFile(String item) throws IOException {
//        logger.info(stream);
        String[] classpathEntries = System.getProperty("java.class.path").split(File.pathSeparator);
        String path = classpathEntries[1].replaceAll("target/classes", "results");
        String fileName = path + "/" + item.replaceAll(" ", "_") + "_" +
                Util.Date.currentEpoch2String() + ".csv";
        logger.info("CSV file path: " + fileName);
        write2File(fileName);
    }
}

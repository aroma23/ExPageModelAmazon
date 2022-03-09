package com.qa.caesars;

import com.qa.caesars.pages.LoginPage;
import com.qa.fw.AbstractTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;

public class CaesarsSteps extends AbstractTest {
    private LoginPage loginPage;

    public CaesarsSteps() {
        loginPage = new LoginPage(browser, properties.getProperty("caesars.login.url"));
    }

    @Before("@Login")
    public void setUp(Scenario scenario){
        this.scenario = scenario;
        logger.info("========== Scenario: "
                + scenario.getName() + ": Line: " + scenario.getLine() + " started ==========");
    }

    @Given("Login page is launched")
    public void loginPageIsLaunched() {
        logger.info("Launched");
        screenShot("Launched");
    }

    @When("Credentials: {string} and {string} passed to application")
    public void credentialsEmailAndPasswordPassedToApplication(String email, String pwd) {
        loginPage.login(email, pwd);
        screenShot("Credentials entered");
    }

    @Then("Error: {string} message popup")
    public void errorWrongEmailAddressOrPasswordMessagePopup(String errMsg) {
        loginPage.waitTillLoad(By.cssSelector("#login > ul > li"), 10);
        screenShot("Error popped up");
        String innerText = loginPage.getElement(By.cssSelector("#login > ul > li")).getAttribute("innerText");
        logger.info("Error message from login: " + innerText);
        Assert.assertEquals("Error not matched", errMsg, innerText);
    }

    @After("@Login")
    public void closeBrower() {
        browser.close();
    }

}

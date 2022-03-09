package com.qa;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class CommonSteps {
    private static final Logger logger = LogManager.getLogger(CommonSteps.class);
    public Scenario scenario;
    public WebDriver browser;

    @Before
    public void setUp(Scenario scenario) {
        logger.info("========== Scenario: "
                + scenario.getName() + ": Line: " + scenario.getLine() + " started ==========");
        this.scenario = scenario;
    }

    @After
    public void closeBrower() {
        browser.close();
    }

}

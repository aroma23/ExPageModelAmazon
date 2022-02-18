package com.amazon.fw;

import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.*;

/**
 * This abstract class takes care of providing the things needed for a test suite
 *
 * @author Muthukumar Ramaiyah
 *
 */
public abstract class AbstractTest {
    protected static Logger logger;
    protected static Properties properties;
    protected Scenario scenario;
    private static volatile boolean areClientsInitialized = false;
    protected WebDriver browser;

    protected AbstractTest() {
        logger = LogManager.getLogger(this.getClass());
        initializeProperties();
        System.setProperty("webdriver.chrome.driver", properties.getProperty("path.to.chromedriver"));
        browser = new ChromeDriver();
    }
    private static synchronized void initializeProperties() {
        if (!areClientsInitialized) {
            properties = new Properties();
            try {
                properties.load(Objects.requireNonNull(AbstractTest.class.getClassLoader().getResourceAsStream(
                        System.getProperty("test.env") + ".properties")));
                properties.putAll(System.getProperties());
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
            areClientsInitialized = true;
        }
    }

    public void screenShot(String name) {
        if (name.isEmpty() || name.isBlank())
            name = "Screenshot";
        scenario.attach(((TakesScreenshot)browser).getScreenshotAs(OutputType.BYTES), "image/png",
                name);
    }
}

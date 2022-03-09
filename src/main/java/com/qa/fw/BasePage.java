package com.qa.fw;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    //implement partial behaviors that are needed for this framework

    protected WebDriver browser;

    protected BasePage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }

    public void waitTillLoad(By locator, int secs) {
        new WebDriverWait(browser, Duration.ofSeconds(secs)).until(ExpectedConditions.visibilityOfElementLocated(
                locator));
    }

    public WebElement getElement(By locator) {
        return browser.findElement(locator);
    }

    public boolean isExists(By locator) {
        try {
            browser.findElement(locator);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }
}

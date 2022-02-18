package com.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class Page {
    //implement partial behaviors that are needed for this framework

    protected WebDriver browser;

    protected Page(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }

    public void waitTillLoad(By locator, int secs) {
        new WebDriverWait(browser, Duration.ofSeconds(secs)).until(ExpectedConditions.visibilityOfElementLocated(
                locator));
    }
}

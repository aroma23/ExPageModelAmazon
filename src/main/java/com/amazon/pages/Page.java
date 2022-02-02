package com.amazon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class Page {
    protected WebDriver browser;

    public Page(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }
}

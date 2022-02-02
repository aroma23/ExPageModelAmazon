package com.amazon.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ItemPage extends Page{
    private static final String NA = "not available";

    @FindBy(id="productTitle")
    private WebElement productTitle;

    @FindBy(id="sellerProfileTriggerId")
    private WebElement sellerName;

    @FindBy(id="corePriceDisplay_desktop_feature_div")
    private WebElement price;

    @FindBy(id="averageCustomerReviews")
    private WebElement review;

    @FindBy(id="askDPSearchPromptLabel")
    private WebElement askQuestions;

    public ItemPage(WebDriver browser, String url) {
        super(browser);
        browser.get(url);
    }

    public ItemPage(WebDriver browser) {
        super(browser);
    }

    public void navigateTo(String url) {
        browser.get(url);
    }

    public String getSellerName() {
        try {
            return sellerName.getAttribute("innerText");
        } catch (NoSuchElementException ignored) {
            return NA;
        }
    }

    public String getPrice() {
        try {
            return price.getAttribute("innerText").split(System.lineSeparator())[0];
        } catch (NoSuchElementException ignored) {
            return NA;
        }
    }

    public String getReview() {
        try {
            return review.getAttribute("innerText").replace("\n   ", " - ");
        } catch (NoSuchElementException ignored) {
            return NA;
        }
    }

    public String getProductTitle() {
        return productTitle.getAttribute("innerText");
    }

}

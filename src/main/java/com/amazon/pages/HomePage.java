package com.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class HomePage extends Page{

    @FindBy(id="twotabsearchtextbox")
    private WebElement searchBox;

    @FindBy(id="nav-search-submit-button")
    private WebElement submitButton;

    @FindBy(id="a-autoid-0-announce")
    private WebElement sortList;

    @FindBy(id="s-result-sort-select_2")
    private WebElement priceHigh2LowOption;

    @FindBy(xpath="//span[text()='Related searches']")
    private WebElement relatedSearchesLabel;

    public HomePage(WebDriver browser, String url) {
        super(browser);
        browser.get(url);
    }

    public void searchViaUrl(String item) {
        browser.get(browser.getCurrentUrl() +  "/s?k=" + item);
        //https://www.amazon.com/s?k=Bluetooth+headset&s=price-desc-rank
    }

    public void searchViaUrl(String item, String sortOption) {
        browser.get(String.format("%s/s?k=%s&s=%s", browser.getCurrentUrl(), item, sortOption));
    }

    public void searchItem(String item) {
        searchBox.sendKeys(item);
        submitButton.click();
    }

    public void sortResults(String  option) {
        //option param  can be implemented for various options
        sortList.click();
        priceHigh2LowOption.click();
    }

    public List<WebElement> getSearchResults() {
        waitTillLoad(By.xpath(".//span[@class='a-size-medium a-color-base a-text-normal']"), 6);
        return browser.findElements(By.xpath(".//span[@class='a-size-medium a-color-base a-text-normal']"));
    }

}

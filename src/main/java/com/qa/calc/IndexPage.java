package com.qa.calc;

import com.qa.fw.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;

public class IndexPage extends BasePage {
    @FindBy(id="display")
    public WebElement display;

    public IndexPage(WebDriver browser, String url) {
        super(browser);
        browser.get(url);
    }

    public WebElement getButton(String operandOrOperator) {
        return browser.findElement(By.id(Arrays.stream(Buttons.values())
                .filter(b -> b.getButtonState().equalsIgnoreCase(operandOrOperator)).findFirst().orElseThrow()
                .toString()));
    }
}

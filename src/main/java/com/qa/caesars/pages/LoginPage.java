package com.qa.caesars.pages;

import com.qa.fw.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id="id_email")
    private WebElement emailTextBox;

    @FindBy(id="id_password")
    private WebElement passwordTextBox;

    @FindBy(id="id_login_submit")
    private WebElement submitButton;

    @FindBy(css = "#login > ul > li")
    private WebElement errorLabel;

    public LoginPage(WebDriver browser, String url) {
        super(browser);
        browser.get(url);
    }

    public void login(String email, String pwd) {
        emailTextBox.sendKeys(email);
        passwordTextBox.sendKeys(pwd);
        submitButton.click();
    }
}

package com.qa.calc;

import com.qa.CommonSteps;
import com.qa.fw.AbstractTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class CalcSteps extends AbstractTest {
    private IndexPage indexPage;
    public CalcSteps(CommonSteps commonSteps) {
        commonSteps.browser = browser;
        scenario = commonSteps.scenario;
    }
    @Given("Calc is launched")
    public void calcIsLaunched() {
        indexPage = new IndexPage(browser, properties.getProperty("calc.url"));
    }

    @When("OperandA: {string}, Operator {string}, and  OperandB: {string} are clicked")
    public void operator(String a, String operator, String b) {
        indexPage.getButton(a).click();
        screenShot("button clicked");
        indexPage.getButton(operator).click();
        screenShot("button clicked");
        indexPage.getButton(b).click();
        screenShot("button clicked");
        indexPage.getButton("=").click();
        screenShot("button clicked");
    }

    @Then("Validate result: {string} matches in display")
    public void validateResultResultMatchesInDisplay(String result) {
        String resultFromDisplay = indexPage.display.getAttribute("innerText");
        System.out.println(resultFromDisplay);
        Assert.assertEquals(result, resultFromDisplay);
    }
}

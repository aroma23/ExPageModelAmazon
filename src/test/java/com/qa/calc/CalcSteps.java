package com.qa.calc;

import com.qa.CommonSteps;
import com.qa.calc.IndexPage;
import com.qa.fw.AbstractTest;
import io.cucumber.java.en.Given;

public class CalcSteps extends AbstractTest {
    private IndexPage indexPage;
    public CalcSteps(CommonSteps commonSteps) {
        indexPage = new IndexPage(browser, properties.getProperty("calc.url"));
        commonSteps.browser = browser;
        scenario = commonSteps.scenario;
    }
    @Given("Calc is launched")
    public void calcIsLaunched() {
    }
}

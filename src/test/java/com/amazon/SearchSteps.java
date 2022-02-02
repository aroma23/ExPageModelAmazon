package com.amazon;

import com.amazon.fw.AbstractTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class SearchSteps extends AbstractTest {

    public SearchSteps() {
    }

    @Given("Application is launched")
    public void applicationIsLaunched() {
        logger.info("Launched");
    }

    @When("{string} is searched")
    public void itemIsSearched(String item) {
        logger.info(item + " Item searched");
    }
}

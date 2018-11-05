package com.w2a.cucumber.zoho.runners;


import cucumber.api.CucumberOptions;
import cucumber.api.testng.TestNGCucumberRunner;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/resources/features/login.feature",
        glue = "com.w2a.cucumber.zoho.steps"
)
public class LoginFeatureRunner {

    @Test
    public void runCukes(){

        new TestNGCucumberRunner(getClass()).runCukes();
    }
}

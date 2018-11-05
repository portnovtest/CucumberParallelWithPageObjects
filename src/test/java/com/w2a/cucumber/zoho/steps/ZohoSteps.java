package com.w2a.cucumber.zoho.steps;

import com.aventstack.extentreports.Status;
import com.w2a.zoho.ExtentListeners.ExtentManager;
import com.w2a.zoho.ExtentListeners.ExtentTestManager;
import com.w2a.zoho.PageObjects.ZohoHomePage;
import com.w2a.zoho.utilities.DriverManager;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ZohoSteps extends BaseSteps {

    public ZohoHomePage home;

    protected Scenario scenario;
    static String scenarioName;
    static int x = 0;

    @Before
    public void before(Scenario scenario){

        x +=1;
        this.scenario = scenario;
        scenarioName = scenario.getName();
        ExtentTestManager.startTest("Scenario No : " + x + " : " +scenario.getName());
        ExtentTestManager.getTest().log(Status.INFO, "Scenario started: - " + scenario.getName());
        setUpFramework();
    }

    @After
    public void after(Scenario scenario){

        if (scenario.isFailed()){
            ExtentTestManager.logFail("Scenario Failed");
            ExtentTestManager.addScreenShotsOnFailure();
        } else {
            ExtentTestManager.logPass("Scenario Passed");
        }
        ExtentManager.getReporter().flush();
        quit();
    }

    @Given("^launch browser '(.*?)'$")
    public void launch_browser(String browserName) throws Throwable {
        openBrowser(browserName);
        ExtentTestManager.logInfo("Browser Opened : " + browserName);
    }

    @When("^user navigates to the URL '(.*?)'$")
    public void user_navigates_to_the_URL(String URL) throws Throwable {
       home = new ZohoHomePage().open(URL);
    }

    @Then("^user click on login$")
    public void user_click_on_login() throws Throwable {
       home.gotoLogin();
    }

}

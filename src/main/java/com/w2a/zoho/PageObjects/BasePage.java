package com.w2a.zoho.PageObjects;


import com.w2a.zoho.ExtentListeners.ExtentTestManager;
import com.w2a.zoho.utilities.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage<T> {
    protected WebDriver driver;

    private long LOAD_TIMEOUT = 30;
    private int AJAX_ELEMENT_TIMEOUT = 10;

    public BasePage() {
        this.driver = DriverManager.getDriver();
    }

    public  T openPage(Class<T> clazz){
        T page = null;
        driver = DriverManager.getDriver();
        AjaxElementLocatorFactory ajaxElementFactory = new AjaxElementLocatorFactory(driver,10);
        page = PageFactory.initElements(driver, clazz);
        PageFactory.initElements(ajaxElementFactory,page);
        ExpectedCondition pageLoadCondition = ((BasePage)page).getPageLoadCondition();
        waitForPageToLoad(pageLoadCondition);
        return page;
        /*
         *  1st Init Page Factory elements
         *  2nd Page Load Condition
         */
    }

    private void waitForPageToLoad(ExpectedCondition pageLoadCondition){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(pageLoadCondition);
    }

    protected abstract ExpectedCondition getPageLoadCondition();

    public void click(WebElement element, String elementName){
        element.click();
        ExtentTestManager.testReport.get().info("Clicking on : " + elementName);
    }

    public void type(WebElement element, String value, String elementName){
        element.sendKeys(value);
        ExtentTestManager.testReport.get().info("Typing in : " + elementName + " entered the value as : " + value);
    }
}

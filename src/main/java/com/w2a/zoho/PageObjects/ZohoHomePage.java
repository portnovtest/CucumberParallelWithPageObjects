package com.w2a.zoho.PageObjects;

import com.w2a.zoho.utilities.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ZohoHomePage extends BasePage {

    @FindBy(css = "a.zh-login")
    public WebElement logIn;

    public ZohoHomePage open(String url){
        DriverManager.getDriver().navigate().to(url);
        return (ZohoHomePage) openPage(ZohoHomePage.class);
    }

    public ZohoLoginPage gotoLogin(){
        click(logIn, "Login Link");
        return (ZohoLoginPage) openPage(ZohoLoginPage.class);
    }

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(logIn);
    }
}

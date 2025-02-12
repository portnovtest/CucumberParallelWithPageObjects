package com.w2a.zoho.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ZohoLoginPage extends BasePage {

    @FindBy(xpath = "//*[@id='lid']")
    public WebElement email;

    @FindBy(xpath = "//*[@id='pwd']")
    public WebElement pass;

    @FindBy(xpath = "//*[@id='signin_submit']")
    public WebElement signin;

    public ZohoLoginPage doLoginAsInvalidUser(String username, String password){
        type(email, username, "Username textbox");
        type(pass, password, "Password textbox");
        click(signin, "Sign in button");
        return this;
    }

    public ZohoAppPage doLoginAsValidUser(String username, String password){
        type(email, username, "Username textbox");
        type(pass, password, "Password textbox");
        click(signin, "Sign in button");
        return (ZohoAppPage) openPage(ZohoAppPage.class);
    }

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(email);
    }
}

package com.w2a.zoho.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ZohoAppPage extends BasePage{

    @FindBy(xpath = "//*[contains(text(), 'Cliq')]")
    public WebElement cliq;

    @FindBy(xpath = "//*[contains(text(), 'Meeting')]")
    public WebElement meeting;

    @FindBy(xpath = "//*[contains(text(), 'CRM')]")
    public WebElement crm;

    @FindBy(xpath = "//*[contains(text(), 'SalesIQ')]")
    public WebElement salesIQ;

    @FindBy(xpath = "//*[contains(text(), 'People')]")
    public WebElement people;


    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(crm);
    }

    public ZohoCRMPage gotoCRM(){
        click(crm,"CRM Link");
        return (ZohoCRMPage) openPage(ZohoCRMPage.class);
    }

    public ZohoCliqPage gotoCliq(){
        click(cliq,"Cliq Link");
        return (ZohoCliqPage) openPage(ZohoCliqPage.class);
    }

    public ZohoMeetingPage gotoMeeting(){
        click(meeting,"Meeting Link");
        return (ZohoMeetingPage) openPage(ZohoMeetingPage.class);
    }

    public ZohoSalesIQPage gotoSalesIQ(){
        click(salesIQ,"SalesIQ Link");
        return (ZohoSalesIQPage) openPage(ZohoSalesIQPage.class);
    }

    public ZohoPeoplePage gotoPeople(){
        click(people,"People Link");
        return (ZohoPeoplePage) openPage(ZohoPeoplePage.class);
    }
}

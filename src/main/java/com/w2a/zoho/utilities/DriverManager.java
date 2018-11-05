package com.w2a.zoho.utilities;

import org.openqa.selenium.WebDriver;

public class DriverManager {
    public static ThreadLocal<WebDriver> dr = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return dr.get();
    }

    public static void setWebDriver(WebDriver driver) {
        dr.set(driver);
    }
}

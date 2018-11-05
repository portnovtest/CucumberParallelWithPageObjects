package com.w2a.cucumber.zoho.steps;

import com.w2a.zoho.ExtentListeners.ExtentTestManager;
import com.w2a.zoho.utilities.DriverFactory;
import com.w2a.zoho.utilities.DriverManager;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.log4testng.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseSteps {
    private WebDriver driver;
    private Properties config = new Properties();
    private FileInputStream fis;
    public Logger log = Logger.getLogger(BaseSteps.class);
    public boolean grid = true;
    private String defaultUserName;
    private String defaultPassword;

    public String getDefaultUserName() {
        return defaultUserName;
    }

    public void setDefaultUserName(String defaultUserName) {
        this.defaultUserName = defaultUserName;
    }

    public String getDefaultPassword() {
        return defaultPassword;
    }

    public void setDefaultPassword(String defaultPassword) {
        this.defaultPassword = defaultPassword;
    }


    public void setUpFramework(){

        configureLogging();
        DriverFactory.setGridPath("http://localhost:4444/wd/hub");
        DriverFactory.setConfigPropertyFile(System.getProperty("user.dir") + "/src/test/resources/properties/Config.properties");

        if (System.getProperty("os.name").contains("Mac")){
            DriverFactory.setChromeDriverExePath(System.getProperty("user.dir") + "/src/test/resources/executables/chromedriver");
            DriverFactory.setGeckoDriverExePath(System.getProperty("user.dir") + "/src/test/resources/executables/geckodriver");
        } else {
            DriverFactory.setChromeDriverExePath(System.getProperty("user.dir") + "/src/test/resources/executables/chromedriver.exe");
            DriverFactory.setGeckoDriverExePath(System.getProperty("user.dir") + "/src/test/resources/executables/geckodriver.exe");
        }

        try {
            fis = new FileInputStream(DriverFactory.getConfigPropertyFile());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            config.load(fis);
            log.info("Configuration file loaded !!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void logInfo(String message){
        ExtentTestManager.testReport.get().info(message);
    }

    public void configureLogging(){

        String log4jConfigFile = System.getProperty("user.dir") + File.separator + "/src/test/resources/properties/log4j.properties";
        PropertyConfigurator.configure(log4jConfigFile);
    }

    public void openBrowser(String browser){

        if (System.getenv("ExecutionType") != null && System.getenv("ExecutionType").equals("Grid")){
            grid = true;
        }
        DriverFactory.setRemote(grid);
        if (DriverFactory.isRemote()){
            DesiredCapabilities cap = null;

            if (browser.equals("firefox")){
                cap = DesiredCapabilities.firefox();
                cap.setBrowserName("firefox");
                cap.setPlatform(Platform.ANY);
            } else if (browser.equals("chrome")){
                cap = DesiredCapabilities.chrome();
                cap.setBrowserName("chrome");
                cap.setPlatform(Platform.ANY);
            } else if (browser.equals("ie")){
                cap = DesiredCapabilities.internetExplorer();
                cap.setBrowserName("iexplorer");
                cap.setPlatform(Platform.WIN10);
            }

            try {
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
                log.info("Starting a session on Grid !!!");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        } else {
            if (browser.equals("chrome")) {
                System.out.println("Launching : " + browser);
                System.setProperty("webdriver.chrome.driver", DriverFactory.getChromeDriverExePath());
                driver = new ChromeDriver();
                log.info("Chrome browser launched !!!");
            } else if (browser.equals("firefox")){
                System.out.println("Launching : " + browser);
                System.setProperty("webdriver.gecko.driver", DriverFactory.getGeckoDriverExePath());
                driver = new FirefoxDriver();
                log.info("Firefox browser launched !!!");
            }
        }


        DriverManager.setWebDriver(driver);
        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        setDefaultUserName(config.getProperty("defaultUserName"));
        setDefaultPassword(config.getProperty("defaultPassword"));

    }

    public void quit(){
        DriverManager.getDriver().quit();
    }
}

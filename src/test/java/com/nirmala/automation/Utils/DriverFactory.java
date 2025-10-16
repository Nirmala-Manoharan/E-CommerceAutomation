package com.nirmala.automation.Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    private static WebDriver webDriver;

    public static WebDriver getDriver(String driverName){
        WebDriver driver = null;
        if ((driverName.equals("firefox")) && (webDriver == null)){
            webDriver = new FirefoxDriver();
        }
        else if ((driverName.equals("firefox")) && (webDriver == null)){
            webDriver = new ChromeDriver();
        }
        webDriver.manage().window().maximize();
        return webDriver;
    }

    public static void quitDriver(){
        if (webDriver != null){
            webDriver.quit();
        }
    }
}

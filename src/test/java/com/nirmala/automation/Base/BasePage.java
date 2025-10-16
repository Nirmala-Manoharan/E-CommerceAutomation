package com.nirmala.automation.Base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    public static final String BASE_URL = "https://www.automationexercise.com/";
    public static final String LOGIN_URL = BASE_URL + "login";
    public static final String SIGN_UP_URL = BASE_URL + "signup";
    public static final String ACCOUNT_CREATED_URL = BASE_URL + "account_created";


    protected WebDriver webDriver;

    public BasePage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public void type(WebElement element, String text){
        element.clear();
        element.sendKeys(text);
    }

    public void click(WebElement element){
        element.click();
    }

    public String getText(WebElement element){
        return element.getText();
    }

    public String getTitle(){
        return this.webDriver.getTitle();
    }

    public void get(String url){
        webDriver.get(url);
    }

    public boolean isSelected(WebElement element){
        return element.isSelected();
    }

    public void selectByValue(WebElement element, String value){
        Select select = new Select(element);
        select.selectByValue(value);
    }

    public String getAttribute(WebElement element, String attributeName){
        return element.getAttribute(attributeName);
    }

    public List<WebElement> getAllOptions(WebElement element){
        Select select = new Select(element);
        return select.getOptions();
    }

    public void waitForPageToLoad(){
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).
                executeScript("return document.readyState").equals("complete"));
    }
}

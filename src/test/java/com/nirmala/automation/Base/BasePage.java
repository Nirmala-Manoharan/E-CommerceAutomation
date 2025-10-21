package com.nirmala.automation.Base;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.time.Duration;
import java.util.List;

public class BasePage {
    public static final String BASE_URL = "https://www.automationexercise.com/";
    public static final String LOGIN_URL = BASE_URL + "login";
    public static final String SIGN_UP_URL = BASE_URL + "signup";
    public static final String ACCOUNT_CREATED_URL = BASE_URL + "account_created";
    public static final String ACCOUNT_DELETED_URL = BASE_URL + "delete_account";

    protected WebDriver webDriver;
    private Actions actions;

    public BasePage(WebDriver webDriver){
        this.webDriver = webDriver;
        this.actions = new Actions(this.webDriver);
    }

    public void type(WebElement element, String text){
        element.clear();
        element.sendKeys(text);
    }

    // Move to element and click
    public void moveToAndClick(WebElement element) {
        actions.moveToElement(element).click().perform();
    }

    // Send keyboard keys (e.g., Keys.ENTER, Keys.TAB)
    public void sendKey(CharSequence key) {
        actions.sendKeys(key).perform();
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

    public void getCurrentUrl(){
        webDriver.getCurrentUrl();
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
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).
                executeScript("return document.readyState").equals("complete"));
    }

    public void openNewWindowOrTab(String option){
        if (option.equals("TAB")){
            webDriver.switchTo().newWindow(WindowType.TAB);
        }
        else {
            webDriver.switchTo().newWindow(WindowType.WINDOW);
        }
    }

    public void pageRefresh(){
        webDriver.navigate().refresh();
    }

    public void moveBackward(){
        webDriver.navigate().back();
    }

    public boolean isElementInvisible(By locator){
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void copyValue(String value){
        StringSelection selection = new StringSelection(value);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
    }

    public void paste(WebElement element) {
        actions.click(element)
                .keyDown(Keys.CONTROL)
                .sendKeys("v")
                .keyUp(Keys.CONTROL)
                .perform();
    }
}

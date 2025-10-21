package com.nirmala.automation.Page;

import com.nirmala.automation.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Home extends BasePage {
    @FindBy(linkText = "Signup / Login")
    private WebElement loginSignUpLink;

    @FindBy(xpath = "//a[contains(text(), 'Logged in as')]//b")
    private WebElement userName;

    @FindBy(xpath = "//a[contains(text(), 'Logout')]")
    private WebElement logOut;

    @FindBy(xpath = "//a[contains(text(), 'Delete Account')]")
    private WebElement deleteAccount;

    public Home(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getUserName(){
        return getText(userName);
    }

    public void logOut(){
        click(logOut);
    }

    public void deleteAccount(){
        click(deleteAccount);
    }

    public void clickSignUpLogin(){
        click(loginSignUpLink);
    }

    public void waitForHomePageToLoad(){
        waitForPageToLoad();
    }

    public void openNewTab(){
        openNewWindowOrTab("TAB");
    }

    public void openNewWindow(){
        openNewWindowOrTab("WINDOW");
    }

    public boolean isUserLoggedOut(){
        return isElementInvisible(By.xpath("//a[contains(text(), 'Logged in as')]"));
    }
}

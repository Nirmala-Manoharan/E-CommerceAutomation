package com.nirmala.automation.Page;

import com.nirmala.automation.Base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginSignUp extends BasePage {
    @FindBy(xpath = "//input[@data-qa = 'login-email']")
    private WebElement loginEmailInput;

    @FindBy(xpath = "//input[@data-qa = 'login-password']")
    private WebElement loginPasswordInput;

    @FindBy(xpath = "//button[@data-qa = 'login-button']")
    private WebElement loginButton;

    @FindBy(xpath = "//input[@data-qa = 'signup-name']")
    private WebElement signUpName;

    @FindBy(xpath = "//input[@data-qa = 'signup-email']")
    private WebElement signUpEmail;

    @FindBy(xpath = "//button[@data-qa = 'signup-button']")
    private WebElement signUpButton;

    @FindBy(xpath = "//h2[contains(text(), 'New User Signup!')]")
    private WebElement signUpText;

    @FindBy(xpath = "//p[contains(text(), 'Email Address already exist!')]")
    private WebElement userExistErrMessage;

    @FindBy(xpath = "//h2[@data-qa='account-created']")
    private WebElement accountCreated;

    @FindBy(xpath = "//a[@data-qa='continue-button']")
    private WebElement continueButton;

    public LoginSignUp(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String newUserSignUpText(){
        return getText(signUpText);
    }

    public void signUp(String name, String email){
        type(signUpName, name);
        type(signUpEmail, email);
        click(signUpButton);
    }

    public String getUserExistErrMessage(){
        return getText(userExistErrMessage);
    }

    public String getAccountCreatedText(){
        return getText(accountCreated);
    }

    public void clickContinue(){
        click(continueButton);
    }

    public void waitForLoginSignUpPageToLoad(){
        waitForPageToLoad();
    }
}

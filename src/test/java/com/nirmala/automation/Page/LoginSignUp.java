package com.nirmala.automation.Page;

import com.nirmala.automation.Base.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

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

    @FindBy(xpath = "//h2[@data-qa='account-deleted']")
    private WebElement accountDeleted;

    @FindBy(xpath = "//a[@data-qa='continue-button']")
    private WebElement continueButton;

    @FindBy(xpath = "//h2[contains(text(), 'Login to your account')]")
    private WebElement loginText;

    @FindBy(xpath = "//p[contains(text(), 'Your email or password is incorrect!')]")
    private WebElement loginErrMessage;

    public LoginSignUp(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getLoginUserText(){
        return getText(loginText);
    }

    public String newUserSignUpText(){
        return getText(signUpText);
    }

    public void login(String email, String password){
        type(loginEmailInput, email);
        type(loginPasswordInput, password);
        click(loginButton);
    }

    public String getLoginErrText(){
        return getText(loginErrMessage);
    }

    public void signUp(String name, String email){
        type(signUpName, name);
        type(signUpEmail, email);
        click(signUpButton);
    }

    public void loginUsingAction(String email, String password){
        moveToAndClick(loginEmailInput);
        type(loginEmailInput, email);
        sendKey(Keys.TAB);
        type(loginPasswordInput, password);
        moveToAndClick(loginButton);
    }

    public String getUserExistErrMessage(){
        return getText(userExistErrMessage);
    }

    public String getAccountCreatedText(){
        return getText(accountCreated);
    }

    public String getAccountDeletedText(){
        return getText(accountDeleted);
    }
    public void clickContinue(){
        click(continueButton);
    }

    public void waitForLoginSignUpPageToLoad(){
        waitForPageToLoad();
    }

    public String getPasswordFieldTypeAttribute(){
        return getAttribute(loginPasswordInput, "type");
    }

    public String getEmailPlaceHolder(){
        return getAttribute(loginEmailInput, "placeholder");
    }

    public String getPasswordPlaceHolder(){
        return getAttribute(loginPasswordInput, "placeholder");
    }

    public void copyPasteEmailId(String emailId) throws IOException, UnsupportedFlavorException {
        copyValue(emailId);
        paste(loginEmailInput);
    }

    public void copyPastePassword(String password) throws IOException, UnsupportedFlavorException {
        copyValue(password);
        /*Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        String data = (String) clipboard.getData(DataFlavor.stringFlavor);
        System.out.println("Clipboard contains: " + data);*/
        paste(loginPasswordInput);
    }

    public void clickLoginButton(){
        click(loginButton);
    }
}

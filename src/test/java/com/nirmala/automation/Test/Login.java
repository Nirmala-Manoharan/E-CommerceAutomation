package com.nirmala.automation.Test;

import com.nirmala.automation.Page.AccountInformation;
import com.nirmala.automation.Page.Home;
import com.nirmala.automation.Page.LoginSignUp;
import com.nirmala.automation.Utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import static com.nirmala.automation.Base.BasePage.*;

public class Login {
    private LoginSignUp login;
    public WebDriver driver;
    private Home home;
    private AccountInformation accountInfo;
    boolean isSiteBug = true;

    @BeforeClass
    @Parameters({"Browser"})
    public void setUp(String name){
        driver = DriverFactory.getDriver(name);
        home = new Home(driver);
        driver.get(BASE_URL);
        home.waitForHomePageToLoad();
        home = new Home(driver);
        home.clickSignUpLogin();
        home.waitForHomePageToLoad();
        Assert.assertEquals(driver.getCurrentUrl(), LOGIN_URL);
        login = new LoginSignUp(driver);
        accountInfo = new AccountInformation(driver);
    }

    @Test(priority = 1)
    public void validateLoginText(){
        Assert.assertEquals(login.getLoginUserText(), "Login to your account");
    }

    @Test(priority = 2)
    public void validateEmailIdEmptyValue(){
        login.login("", "");
        Assert.assertEquals(driver.getCurrentUrl(), LOGIN_URL);
    }

    @Test(priority = 3)
    public void validatePasswordEmptyValue(){
        login.login("temp@mail.com", "");
        Assert.assertEquals(driver.getCurrentUrl(), LOGIN_URL);
    }

    @Test(priority = 4)
    public void validateEmailIdInValidValue(){
        login.login("temp1@mail.com", "");
        Assert.assertEquals(driver.getCurrentUrl(), LOGIN_URL);
    }

    @Test(priority = 5)
    public void validateLoginWithUnRegisteredEmailId(){
        login.login("temp1@mail.com", "pwd123");
        Assert.assertEquals(login.getLoginErrText(), "Your email or password is incorrect!");
    }

    @Test(priority = 6)
    public void validateLoginWithWrongPassword(){
        login.login("temp@mail.com", "pwd122");
        Assert.assertEquals(login.getLoginErrText(), "Your email or password is incorrect!");
    }

    @Test(priority = 7)
    public void validateLoginWithValidCredentials(){
        login.login("temp@mail.com", "pwd123");
        home.waitForHomePageToLoad();
        Assert.assertEquals(home.getUserName(), "temp");
    }

    @Test(priority = 8)
    public void validateSuccessfulLogout(){
        home.logOut();
        Assert.assertEquals(driver.getCurrentUrl(), LOGIN_URL);
    }


    @Test(priority = 9)
    public void validateLoginWithValidCredentialsExtraSpace(){
        if(isSiteBug){
            throw new SkipException("Skipping this test due to known site bug");
        }
        login.login(" temp@mail.com ", "pwd123");
        home.waitForHomePageToLoad();
        Assert.assertEquals(home.getUserName(), "temp");
        home.logOut();
        Assert.assertEquals(driver.getCurrentUrl(), LOGIN_URL);
    }

    @Test(priority = 10)
    public void validatePasswordFieldMasked(){
        Assert.assertEquals(login.getPasswordFieldTypeAttribute(), "password");
    }

    @Test(priority = 11)
    public void validateLoginUsingAction() throws InterruptedException {
        login.loginUsingAction("temp@mail.com", "pwd123");
        home.waitForHomePageToLoad();
        Thread.sleep(20000);
        Assert.assertEquals(driver.getCurrentUrl(), BASE_URL);
        Assert.assertEquals(home.getUserName(), "temp");
        home.logOut();
    }

    @Test(priority = 12)
    public void verifyCopyPasteInLoginFields(){
        try {
            login.copyPasteEmailId("temp@mail.com");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedFlavorException e) {
            throw new RuntimeException(e);
        }

        try {
            login.copyPastePassword("pwd123");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedFlavorException e) {
            throw new RuntimeException(e);
        }
        login.clickLoginButton();
        home.waitForHomePageToLoad();
        Assert.assertEquals(home.getUserName(), "temp");
        home.logOut();
    }

    @Test(priority = 13)
    public void validatePageTitle(){
        Assert.assertEquals(login.getTitle(), "Automation Exercise - Signup / Login");
    }

    @Test(priority = 14)
    public void validateSessionCookieDeleted(){
        if (isSiteBug) {
            throw new SkipException("Skipping this test due to known site bug");
        }
        login.login("temp@mail.com", "pwd123");
        home.waitForHomePageToLoad();
        Assert.assertNotNull(driver.manage().getCookieNamed("sessionid"));
        Assert.assertEquals(home.getUserName(), "temp");
        home.logOut();
        Assert.assertNull(driver.manage().getCookieNamed("sessionid"));
    }

    @Test(priority = 15)
    public void validateCredentialsNotVisible(){
        login.login("temp@mail.com", "pwd123");
        Assert.assertTrue(driver.getCurrentUrl().contains("https"));
        Assert.assertFalse(driver.getCurrentUrl().contains("temp@mail.com"));
        Assert.assertFalse(driver.getCurrentUrl().contains("pwd123"));
        Assert.assertFalse(driver.getPageSource().contains("temp@mail.com"));
        Assert.assertFalse(driver.getPageSource().contains("pwd123"));
        home.logOut();
    }

    @Test(priority = 16)
    public void validateUserLoggedInAfterPageRefresh(){
        login.login("temp@mail.com", "pwd123");
        home.waitForHomePageToLoad();
        Assert.assertEquals(home.getUserName(), "temp");
        login.pageRefresh();
        home.waitForHomePageToLoad();
        Assert.assertEquals(home.getUserName(), "temp");
        home.logOut();
        Assert.assertEquals(driver.getCurrentUrl(), LOGIN_URL);
    }

    @Test(priority = 17)
    public void validateEmailAndPasswordPlaceHolder(){
        Assert.assertEquals(login.getEmailPlaceHolder(), "Email Address");
        Assert.assertEquals(login.getPasswordPlaceHolder(), "Password");
    }

    @Test(priority = 18)
    public void validateDeleteAccount(){
        login.login("temp@mail.com", "pwd123");
        home.waitForHomePageToLoad();
        home.deleteAccount();
        Assert.assertEquals(driver.getCurrentUrl(), ACCOUNT_DELETED_URL);
        Assert.assertEquals(login.getAccountDeletedText(), "ACCOUNT DELETED!");
        login.clickContinue();
        Assert.assertEquals(driver.getCurrentUrl(), BASE_URL);
    }

    @Test(priority = 19)
    public void validateBrowserBackwardAfterSessionDeletion() throws InterruptedException {
        if(isSiteBug){
            throw new SkipException("Skipping this test due to known site bug");
        }
        home.moveBackward();
        Assert.assertEquals(driver.getCurrentUrl(), ACCOUNT_DELETED_URL);
        Thread.sleep(5000);
        home.moveBackward();
        Thread.sleep(5000);
        Assert.assertEquals(driver.getCurrentUrl(), BASE_URL);
        //Assert.assertTrue(home.isUserLoggedOut());
        Assert.assertNotEquals(home.getUserName(), "temp");
    }

    @Test(priority = 20)
    public void validateLoginWithDeletedUserCredentials(){
        home.clickSignUpLogin();
        home.waitForHomePageToLoad();
        Assert.assertEquals(driver.getCurrentUrl(), LOGIN_URL);
        login.login("temp@mail.com", "pwd123");
        Assert.assertEquals(login.getLoginErrText(), "Your email or password is incorrect!");
    }

    @Test(priority = 21, dataProvider = "signUpDetails", dataProviderClass = com.nirmala.automation.Utils.DataProviderInfo.class)
    public void validateSignUpAfterAccountDeletion(String userName, String email){
        login.signUp(userName, email);
        Assert.assertEquals(driver.getCurrentUrl(), SIGN_UP_URL);
        accountInfo.selectGender(1);
        accountInfo.enterPassword("pwd123");
        accountInfo.selectDOB("30","12", "1977");
        accountInfo.selectNewsLetter();
        accountInfo.selectOffers();
        accountInfo.enterFirstName("temp");
        accountInfo.enterLastName("temp1");
        accountInfo.enterAddress1("5, fancy street");
        accountInfo.selectCountry("Canada");
        accountInfo.enterState("Alberta");
        accountInfo.enterCity("Calfary");
        accountInfo.enterZipCode("M5V 3L9");
        accountInfo.enterNumber("587-555-0199");
        accountInfo.createAccount();
        Assert.assertEquals(driver.getCurrentUrl(), ACCOUNT_CREATED_URL);
        Assert.assertEquals(login.getAccountCreatedText(), "ACCOUNT CREATED!");
        login.clickContinue();
        Assert.assertEquals(driver.getCurrentUrl(), BASE_URL);
        Assert.assertEquals(home.getUserName(), "temp");
        home.logOut();
    }

    @Test(priority = 22)
    public void validateConcurrentLoginInNewTab(){
        login.login("temp@mail.com", "pwd123");
        home.waitForHomePageToLoad();
        Assert.assertEquals(home.getUserName(), "temp");
        home.openNewTab();
        driver.get(BASE_URL);
        home.waitForHomePageToLoad();
        Assert.assertEquals(home.getUserName(), "temp");
        home.logOut();
    }
}
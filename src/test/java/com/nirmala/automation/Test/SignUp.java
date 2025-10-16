package com.nirmala.automation.Test;

import com.nirmala.automation.Page.AccountInformation;
import com.nirmala.automation.Page.Home;
import com.nirmala.automation.Page.LoginSignUp;
import com.nirmala.automation.Utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import static com.nirmala.automation.Base.BasePage.*;

public class SignUp {
    private LoginSignUp signUp;
    public WebDriver driver;
    private Home home;
    private AccountInformation accountInfo;
    boolean isSiteBug = true;

    @BeforeClass
    @Parameters({"Browser"})
    public void setUp(String name){
        driver = DriverFactory.getDriver(name);
        driver.get(BASE_URL);
        home = new Home(driver);
        home.waitForHomePageToLoad();
        home.clickSignUpLogin();
        signUp = new LoginSignUp(driver);
        accountInfo = new AccountInformation(driver);
        signUp.waitForLoginSignUpPageToLoad();
        Assert.assertEquals(driver.getCurrentUrl(), LOGIN_URL);
    }

    @Test(priority = 1)
    public void checkSignUpText(){
        Assert.assertTrue(signUp.newUserSignUpText().equals("New User Signup!"));
    }

    @Test(priority = 2)
    public void validateUserNameField(){
        signUp.signUp("", "temp@mail.com");
        Assert.assertEquals(driver.getCurrentUrl(), LOGIN_URL);
    }

    @Test(priority = 3)
    public void validateEmailIdFieldEmptyValue(){
        signUp.signUp("temp", "");
        Assert.assertEquals(driver.getCurrentUrl(), LOGIN_URL);
    }

    @Test(priority = 4)
    public void validateEmailIdFieldInvalidValue(){
        signUp.signUp("temp", "temp");
        Assert.assertEquals(driver.getCurrentUrl(), LOGIN_URL);
    }

    @Test(priority = 5, dataProvider = "signUpDetails", dataProviderClass = com.nirmala.automation.Utils.DataProviderInfo.class)
    public void signUpValidDetails(String userName, String email){
        signUp.signUp(userName, email);
        Assert.assertEquals(driver.getCurrentUrl(), SIGN_UP_URL);
    }

    @Test(priority = 6)
    public void validateTitleButtons(){
        Assert.assertFalse(accountInfo.checkGender1IsSelected());
        Assert.assertFalse(accountInfo.checkGender2IsSelected());
    }

    @Test(priority = 7)
    public void validateNameAndEmailValue(){
        Assert.assertEquals(accountInfo.getNameFieldValue(), "temp");
        Assert.assertEquals(accountInfo.getEmailFieldValue(), "temp@mail.com");
    }

    @Test(priority = 8)
    public void validatePasswordField(){
        accountInfo.enterPassword("");
        accountInfo.createAccount();
        Assert.assertEquals(driver.getCurrentUrl(), SIGN_UP_URL);
    }

    @Test(priority = 9)
    public void validateDateOfBirth(){
        List<String> days = accountInfo.getAvailableDays();
        List<String> months = accountInfo.getAvailableMonths();
        List<String> year = accountInfo.getAvailableYear();

        Assert.assertFalse(days.contains("40"), "Day 40 should not exist");
        Assert.assertFalse(months.contains("april1"), "april1 should not exist");
        Assert.assertFalse(year.contains("1899"), "1899 should not exist");
    }

    @Test(priority = 10)
    public void validateNewsLetterAndOffersOptions(){
        Assert.assertFalse(accountInfo.isNewsLetterSelected());
        Assert.assertFalse(accountInfo.isOffersSelected());
        accountInfo.selectNewsLetter();
        accountInfo.selectOffers();
        Assert.assertTrue(accountInfo.isNewsLetterSelected());
        Assert.assertTrue(accountInfo.isOffersSelected());
    }

    @Test(priority = 11)
    public void validateFirstNameFieldEmptyValue(){
        accountInfo.enterPassword("pwd123");
        accountInfo.enterFirstName("");
        accountInfo.createAccount();
    }

    @Test(priority = 12)
    public void validateFirstNameInvalidValue(){
        if (isSiteBug) {
            throw new SkipException("Skipping this test due to known site bug");
        }
        accountInfo.enterPassword("pwd123");
        accountInfo.enterFirstName("-");
        accountInfo.enterLastName("temp");
        accountInfo.enterAddress1("5, fancy street");
        accountInfo.selectCountry("Canada");
        accountInfo.enterState("Alberta");
        accountInfo.enterCity("Calgary");
        accountInfo.enterZipCode("M5V 3L9");
        accountInfo.enterNumber("587-555-0199");
        accountInfo.createAccount();
        Assert.assertEquals(driver.getCurrentUrl(), SIGN_UP_URL);
    }

    @Test(priority = 13)
    public void validateLastNameFieldEmptyValue(){
        accountInfo.enterPassword("pwd123");
        accountInfo.enterFirstName("temp1");
        accountInfo.enterLastName("");
        accountInfo.createAccount();
    }

    @Test(priority = 14)
    public void validateLastNameInvalidValue(){
        if (isSiteBug) {
            throw new SkipException("Skipping this test due to known site bug");
        }
        accountInfo.enterPassword("pwd123");
        accountInfo.enterFirstName("temp");
        accountInfo.enterLastName("-");
        accountInfo.enterAddress1("5, fancy street");
        accountInfo.selectCountry("Canada");
        accountInfo.enterState("Alberta");
        accountInfo.enterCity("Calgary");
        accountInfo.enterZipCode("M5V 3L9");
        accountInfo.enterNumber("587-555-0199");
        accountInfo.createAccount();
        Assert.assertEquals(driver.getCurrentUrl(), SIGN_UP_URL);
    }

    @Test(priority = 15)
    public void validateAddressFieldEmpty() {
        accountInfo.enterPassword("pwd123");
        accountInfo.enterFirstName("temp");
        accountInfo.enterLastName("temp1");
        accountInfo.enterAddress1("");
        accountInfo.createAccount();
        Assert.assertEquals(driver.getCurrentUrl(), SIGN_UP_URL);
    }

    @Test(priority = 16)
    public void validateCountryList(){
        List<String> countryList = new ArrayList<>();
        countryList.add("India");
        countryList.add("Canada");
        countryList.add("United States");
        countryList.add("Australia");
        countryList.add("Israel");
        countryList.add("New Zealand");
        countryList.add("Singapore");

        List<WebElement> countries = accountInfo.getAllCountries();
        for (WebElement country : countries){
            Assert.assertTrue(countryList.contains(accountInfo.get(country)));
        }
    }

    @Test(priority = 17)
    public void validateStateFieldEmptyValue(){
        accountInfo.enterPassword("pwd123");
        accountInfo.enterFirstName("temp");
        accountInfo.enterLastName("temp1");
        accountInfo.enterAddress1("5, fancy street");
        accountInfo.selectCountry("Canada");
        accountInfo.enterState("");
        accountInfo.createAccount();
    }

    @Test(priority = 18)
    public void validateStateFieldInvalidValue(){
        if (isSiteBug) {
            throw new SkipException("Skipping this test due to known site bug");
        }
        accountInfo.enterPassword("pwd123");
        accountInfo.enterFirstName("temp");
        accountInfo.enterLastName("temp1");
        accountInfo.enterAddress1("5, fancy street");
        accountInfo.selectCountry("Canada");
        accountInfo.enterState("----");
        accountInfo.enterCity("Calgary");
        accountInfo.enterZipCode("M5V 3L9");
        accountInfo.enterNumber("587-555-0199");
        accountInfo.createAccount();
        Assert.assertEquals(driver.getCurrentUrl(), SIGN_UP_URL);
    }

    @Test(priority = 19)
    public void validateCityFieldEmpty(){
        accountInfo.enterPassword("pwd123");
        accountInfo.enterFirstName("temp");
        accountInfo.enterLastName("temp1");
        accountInfo.enterAddress1("5, fancy street");
        accountInfo.selectCountry("Canada");
        accountInfo.enterState("Alberta");
        accountInfo.enterCity("");
        accountInfo.createAccount();
        Assert.assertEquals(driver.getCurrentUrl(), SIGN_UP_URL);
    }

    @Test(priority = 20)
    public void validateCityFieldInvalidValue(){
        if (isSiteBug) {
            throw new SkipException("Skipping this test due to known site bug");
        }
        accountInfo.enterPassword("pwd123");
        accountInfo.enterFirstName("temp");
        accountInfo.enterLastName("temp1");
        accountInfo.enterAddress1("5, fancy street");
        accountInfo.selectCountry("Canada");
        accountInfo.enterState("Alberta");
        accountInfo.enterCity("----");
        accountInfo.enterZipCode("M5V 3L9");
        accountInfo.enterNumber("587-555-0199");
        accountInfo.createAccount();
        Assert.assertEquals(driver.getCurrentUrl(), SIGN_UP_URL);
    }

    @Test(priority = 21)
    public void validateZipCodeEmpty(){
        accountInfo.enterPassword("pwd123");
        accountInfo.enterFirstName("temp");
        accountInfo.enterLastName("temp1");
        accountInfo.enterAddress1("5, fancy street");
        accountInfo.selectCountry("Canada");
        accountInfo.enterState("Alberta");
        accountInfo.enterCity("Calgary");
        accountInfo.enterZipCode("");
        accountInfo.createAccount();
        Assert.assertEquals(driver.getCurrentUrl(), SIGN_UP_URL);
    }

    @Test(priority = 22)
    public void validateZipCodeInvalidValue(){
        if (isSiteBug) {
            throw new SkipException("Skipping this test due to known site bug");
        }
        accountInfo.enterPassword("pwd123");
        accountInfo.enterFirstName("temp");
        accountInfo.enterLastName("temp1");
        accountInfo.enterAddress1("5, fancy street");
        accountInfo.selectCountry("Canada");
        accountInfo.enterState("Alberta");
        accountInfo.enterCity("Calgary");
        accountInfo.enterZipCode("----");
        accountInfo.enterNumber("587-555-0199");
        accountInfo.createAccount();
        Assert.assertEquals(driver.getCurrentUrl(), SIGN_UP_URL);
    }

    @Test(priority = 23)
    public void validateMobileNumberEmptyValue(){
        accountInfo.enterPassword("pwd123");
        accountInfo.enterFirstName("temp");
        accountInfo.enterLastName("temp1");
        accountInfo.enterAddress1("5, fancy street");
        accountInfo.selectCountry("Canada");
        accountInfo.enterState("Alberta");
        accountInfo.enterCity("Calfary");
        accountInfo.enterZipCode("M5V 3L9");
        accountInfo.enterNumber("");
        accountInfo.createAccount();
        Assert.assertEquals(driver.getCurrentUrl(), SIGN_UP_URL);
    }

    @Test(priority = 23)
    public void validateMobileNumberInvalidValue(){
        if (isSiteBug) {
            throw new SkipException("Skipping this test due to known site bug");
        }
        accountInfo.enterPassword("pwd123");
        accountInfo.enterFirstName("temp");
        accountInfo.enterLastName("temp1");
        accountInfo.enterAddress1("5, fancy street");
        accountInfo.selectCountry("Canada");
        accountInfo.enterState("Alberta");
        accountInfo.enterCity("Calfary");
        accountInfo.enterZipCode("M5V 3L9");
        accountInfo.enterNumber("587-555-0199");
        accountInfo.createAccount();
        Assert.assertEquals(driver.getCurrentUrl(), SIGN_UP_URL);
    }

    @Test(priority = 24)
    public void validateSignUpValidValues(){
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
        Assert.assertEquals(signUp.getAccountCreatedText(), "ACCOUNT CREATED!");
        signUp.clickContinue();
        Assert.assertEquals(driver.getCurrentUrl(), BASE_URL);
        Assert.assertEquals(home.getUserName(), "temp");
    }

    @Test(priority = 25)
    public void validateLogout(){
        home.logOut();
        Assert.assertEquals(driver.getCurrentUrl(), LOGIN_URL);
    }

    @Test(priority = 26)
    public void validateSignUpExistingUser(){
        signUp.signUp("temp", "temp@mail.com");
        Assert.assertEquals(signUp.getUserExistErrMessage(), "Email Address already exist!");
    }

    @AfterClass
    public void tearDown(){
      //  quitDriver();
    }
}

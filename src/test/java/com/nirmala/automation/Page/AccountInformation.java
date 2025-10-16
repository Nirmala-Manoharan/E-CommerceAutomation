package com.nirmala.automation.Page;

import com.nirmala.automation.Base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class AccountInformation extends BasePage {

    @FindBy(id = "id_gender1")
    private WebElement gender1;

    @FindBy(id = "id_gender2")
    private WebElement gender2;

    @FindBy(id = "name")
    private WebElement name;

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "days")
    private WebElement day;

    @FindBy(id = "months")
    private WebElement month;

    @FindBy(id = "years")
    private WebElement year;

    @FindBy(id = "newsletter")
    private WebElement newsletter;

    @FindBy(id = "optin")
    private WebElement optin;

    @FindBy(id = "first_name")
    private WebElement first_name;

    @FindBy(id = "last_name")
    private WebElement last_name;

    @FindBy(id = "company")
    private WebElement company;

    @FindBy(id = "address1")
    private WebElement address1;

    @FindBy(id = "address2")
    private WebElement address2;

    @FindBy(id = "country")
    private WebElement country;

    @FindBy(id = "state")
    private WebElement state;

    @FindBy(id = "city")
    private WebElement city;

    @FindBy(id = "zipcode")
    private WebElement zipcode;

    @FindBy(id = "mobile_number")
    private WebElement mobile_number;

    @FindBy(xpath = "//button[@type = 'submit']")
    private WebElement createAccount;

    public AccountInformation(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean checkGender1IsSelected(){
        return isSelected(gender1);
    }

    public boolean checkGender2IsSelected(){
        return isSelected(gender2);
    }

    public void selectGender(int value){
        if (value == 1){
            click(gender1);
        }
        else {
            click(gender2);
        }
    }

    public String getNameFieldValue(){
        return getAttribute(name, "value");
    }

    public String getEmailFieldValue(){
        return getAttribute(email, "value");
    }

    public void enterPassword(String pwd){
        type(password, pwd);
    }

    public void selectDOB(String dayInput, String monthInput, String yearInput){
        selectByValue(day, dayInput);
        selectByValue(month, monthInput);
        selectByValue(year, yearInput);
    }

    public void enterFirstName(String firstName){
        type(first_name, firstName);
    }

    public void enterLastName(String lastName){
        type(last_name, lastName);
    }

    public void enterAddress1(String addr){
        type(address1, addr);
    }

    public void enterAddress2(String addr){
        type(address1, addr);
    }

    public void selectCountry(String name){
        selectByValue(country, name);
    }
    public List<WebElement> getAllCountries(){
        return getAllOptions(country);
    }

    public void enterState(String name){
        type(state, name);
    }

    public void enterCity(String name){
        type(city, name);
    }

    public void enterZipCode(String code){
        type(zipcode, code);
    }

    public void enterNumber(String number){
        type(mobile_number, number);
    }

    public void createAccount(){
        click(createAccount);
    }

    public void selectNewsLetter(){
        newsletter.click();
    }

    public void selectOffers(){
        optin.click();
    }

    public boolean isNewsLetterSelected(){
        return newsletter.isSelected();
    }

    public boolean isOffersSelected(){
        return optin.isSelected();
    }

    public String get(WebElement element){
        return getText(element);
    }

    public List<String> getAvailableDays(){
        return getAllOptions(day).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<String> getAvailableMonths(){
        return getAllOptions(month).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<String> getAvailableYear(){
        return getAllOptions(year).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void waitForSignUpPageToLoad(){
        waitForPageToLoad();
    }
}

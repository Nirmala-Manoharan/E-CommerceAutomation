package com.nirmala.automation.Utils;

import org.testng.annotations.DataProvider;

public class DataProviderInfo {
    @DataProvider(name = "signUpDetails")
    public static Object[][] driverDetails(){
        return new Object[][]{
                {"temp", "temp@mail.com"}
        };
    }
}

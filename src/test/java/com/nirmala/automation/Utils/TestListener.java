package com.nirmala.automation.Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private static ExtentReports extent = ExtentManager.createInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public void onTestStart(ITestResult result) {
        test.set(extent.createTest(result.getMethod().getMethodName()));
    }

    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result){
        test.get().fail("Test failed: " + result.getThrowable());
        Object currentClass = result.getInstance();
        try {
            WebDriver driver = (WebDriver)currentClass.getClass().getDeclaredField("driver").get(currentClass);
            ScreenshotUtils.takeScreenShot(driver, result.getMethod().getMethodName());
        } catch (NoSuchFieldException e) {
            System.out.println(e.getMessage());
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
    }

    public void onFinish(ITestContext context) {
        extent.flush();
    }
}

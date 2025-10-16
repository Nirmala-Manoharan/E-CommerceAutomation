package com.nirmala.automation.Utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.io.FileHandler;

public class ScreenshotUtils {
    public static void takeScreenShot(WebDriver driver, String testName){
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = "C:\\Users\\manoh\\Desktop\\Java\\Testing\\screenshots\\"+testName+timeStamp+".jpg";

        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File file = screenshot.getScreenshotAs(OutputType.FILE);
        try{
            FileHandler.copy(file, new File(fileName));
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}

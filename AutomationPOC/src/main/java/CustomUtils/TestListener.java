package CustomUtils;

import BaseClass.BaseTestWeb;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.sql.SQLOutput;

public  class TestListener extends BaseTestWeb implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult){
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }
    //Test attachment for allute
    @Attachment (value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG (WebDriver driver) {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }


    //Test attachments for allure
    @Attachment (value = "(0)", type = "text/plain")
    public static String saveTextLog (String message){
        return message;
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult){
        System.out.println("I am in onTestFailure method" + getTestMethodName(iTestResult) + " failed");


        //Get driver from BaseTestWeb and assign to local webdriver variable.
Object testClass = iTestResult.getInstance();
WebDriver driver = ((BaseTestWeb)testClass).getDriver;

//Allure ScreenshotRobot and SaveTestLog
        if(driver instanceof WebDriver)
        {
            System.out.println("Screenshot captured from test case: " + getTestMethodName(iTestResult));
            saveScreenshotPNG(driver);
        }

//Saves a log on allure
        saveTextLog(getTestMethodName(iTestResult) + "failed and screenshot taken");

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }


}

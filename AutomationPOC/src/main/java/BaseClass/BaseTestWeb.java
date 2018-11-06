package BaseClass;

import Config.DriverFactory;
import Config.GlobalVariables;
import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;


import java.io.IOException;
import java.lang.reflect.Method;

public class BaseTestWeb {

    public WebDriver driver;
    public WebDriverWait wait;

    public BaseTestWeb()   {
    }

    public WebDriver getDriver() {
        return driver;
    }

    ATUTestRecorder recorder;

    {
        try {
            recorder = new ATUTestRecorder("C:\\Users\\Gareth Dean\\Videos\\Testing Videos","Video 1 test",false);
        } catch (ATUTestRecorderException e) {
            e.printStackTrace();
        }
    }

    @BeforeSuite
    public void suiteSetup() throws IOException  {

        try {
            recorder.start();
        } catch (ATUTestRecorderException e) {
            e.printStackTrace();
        }
        String url = GlobalVariables.RootURL;
        DriverFactory myDriverFactory = new DriverFactory();
        driver = myDriverFactory.getWebDriver(GlobalVariables.browser);
        driver.manage().window().maximize();
        driver.get(url);
    }

    @BeforeMethod
    public void getMethodName(Method method) {
        // Get the name of the test being run
        GlobalVariables.currentTestName = method.getName();

    }

    @AfterMethod
    public void teardown(ITestResult result){
        if (result.getStatus() == ITestResult.FAILURE || result.getStatus() == ITestResult.SKIP){
            driver.get(GlobalVariables.RootURL);
        }
    }



    @AfterClass
    public void ensClass(){
        // Close the browser
        //End everything
        driver.close();
        driver.quit();
    }
    @AfterSuite
    public void endSuite() {
        try {
            recorder.stop();
        } catch (ATUTestRecorderException e) {
            e.printStackTrace();
        }
    }

}

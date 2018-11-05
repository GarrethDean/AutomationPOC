package BaseClass;

import Config.DriverFactory;
import Config.Hetzner;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.ITestListener;

import java.io.IOException;
import java.lang.reflect.Method;

public class BaseTestWeb {

    public static WebDriver driver;
    public WebDriver getDriver;

    @BeforeSuite
    public void suiteSetup() throws IOException{

        String url = Hetzner.RootURL;
        DriverFactory myDriverFactory = new DriverFactory();
        driver = myDriverFactory.getWebDriver(Hetzner.browser);
        driver.manage().window().maximize();
        driver.get(url);
    }

    @BeforeMethod
    public void getMethodName(Method method) {
        // Get the name of the test being run
        Hetzner.currentTestName = method.getName();
    }

    @AfterMethod
    public void teardown(ITestResult result){
        if (result.getStatus() == ITestResult.FAILURE || result.getStatus() == ITestResult.SKIP){
            driver.get(Hetzner.RootURL);
        }
    }

    @AfterSuite
    public void endSuite()throws IOException{
        // Close the browser
       //End everything

    }

    @AfterClass
    public void ensClass()throws IOException{

        driver.close();
        driver.quit();
    }

}

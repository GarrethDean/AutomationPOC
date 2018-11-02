package BaseClass;


import Config.DriverFactory;
import Config.Hetzner;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseTestAndroid {

    public AndroidDriver driver;
    public WebDriver webDriver;
    public DriverFactory myDriverFactory;
    public String url = Hetzner.RootURL;


    @BeforeSuite
    public void setupSuite() throws IOException {
        myDriverFactory = new DriverFactory();
        driver = myDriverFactory.getAndroidDriver();
    }

    @BeforeMethod
    public void setup(Method method) {
        // Get the name of the test being run
        Hetzner.currentTestName = method.getName();
    }

    @AfterMethod
    public void teardown(ITestResult result) throws IOException {
        // Re-open the app and log in again if the test fails
        if (result.getStatus() == ITestResult.FAILURE || result.getStatus() == ITestResult.SKIP) {
            setupSuite();
        }
    }

    @AfterClass
    public void endClass () throws IOException {
       // driver.closeApp();
        driver.close();
        driver.quit();
        setupSuite();
    }

    @AfterSuite
    public void endtest () {
        driver.closeApp();
        driver.close();
        driver.quit();
    }
}





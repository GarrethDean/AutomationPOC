package Config;

import CustomUtils.ErrorLog;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import java.io.IOException;

import java.net.URL;


public class DriverFactory {
    private ErrorLog myErrorLog;

    public DriverFactory(){

        myErrorLog = new ErrorLog();
    }

    public AndroidDriver getAndroidDriver() throws  IOException {

        // Start the appium server

//        AppiumDriverLocalService service = AppiumDriverLocalService.buildDefaultService();
//        service.start();

        // Create object of  DesiredCapabilities class and specify android platform
        DesiredCapabilities capabilities = DesiredCapabilities.android();

        // set the capability to execute test in android app
        capabilities.setCapability(MobileCapabilityType.PLATFORM, Platform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Emulator_1");
        capabilities.setCapability(MobileCapabilityType.VERSION, "8.0");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
        //capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"espresso");
        capabilities.setCapability("avd","Emulator_1");
        capabilities.setCapability("appPackage", "com.example.android.uamp");
        capabilities.setCapability("appActivity", "com.example.android.uamp.ui.MusicPlayerActivity");
        capabilities.setCapability("newCommandTimeout",999999);

        // Hide the soft keyboard
        capabilities.setCapability("unicodeKeyboard", true);
        capabilities.setCapability("resetKeyboard", true);
        return new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    public WebDriver getWebDriver(String browser) throws IOException {

        // Return the relevant driver
        switch (browser.toUpperCase()){
            case "CHROME":
                ChromeDriverManager.getInstance().setup();
                return new ChromeDriver();
            case "FIREFOX":
                FirefoxDriverManager.getInstance().setup();
                return new FirefoxDriver();
            case "IE":
                InternetExplorerDriverManager.getInstance().setup();
                return new InternetExplorerDriver();
            default:
                myErrorLog.captureAPIErrorDetails("Invalid identifier type: Must be: Xpath, ID or CSS.");
                Assert.fail();
                return null;

        }
    }
}
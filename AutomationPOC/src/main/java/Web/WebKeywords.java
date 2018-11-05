package Web;

import PageFunctions.WebPageFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import BaseClass.BaseTestWeb;
import java.io.IOException;
import io.qameta.allure.Step;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.model.Label;
import io.qameta.allure.model.Link;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StatusDetails;




public class WebKeywords extends BaseTestWeb {
    private WebPageFunctions myPage;
    private String homePage = "HomePage";
    public String message;


    public WebKeywords(WebDriver driver) throws IOException {
        myPage = new WebPageFunctions(driver, "Hetzner_Web.xlsx", "PageObjectMaster.xlsx");
    }
    @Step("Navigate to different heading on main page")
    public void NavigateMainMenu(String catagoryName) throws IOException, InterruptedException {

        myPage.buttonClick(homePage, catagoryName);
        Thread.sleep(200);
    }
@Step("Scroll Terms and conditions button into view : (0), click on the the terms and conditons button ")
    public void ScrollToAndClickElement(String elementToScrollTo) throws IOException {
        myPage.scrollItemIntoView(homePage, elementToScrollTo);
        myPage.buttonClick(homePage, elementToScrollTo);
        myPage.switchToTab();
    }
    @Step("Extract date on terms and conditons page if page returns error.")
    public void extractAndStoreData() throws IOException {

        try {
            WebElement error = driver.findElement(By.xpath("//body[1]"));
            message = error.getText();
            if (!message.contains("resource you are")) {
                message = "I have read the terms and conditions";
            }
        } catch (Exception e) {
            driver.quit();
        }

        myPage.switchToDefaultContent();
    }
    @Step("Enter text into textbox : (0)")
    public void enterTextIntoMultipleFields(String valueTextbox, String textToType) throws IOException {
        myPage.textBoxType(homePage, valueTextbox, textToType);

    }
    @Step ("Click the submit buttone to send form")
    public void clickSubmit(String valueName) throws IOException {
        myPage.buttonClick(homePage,valueName);
    }
}












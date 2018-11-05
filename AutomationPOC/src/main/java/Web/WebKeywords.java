package Web;

import PageFunctions.WebPageFunctions;
import BaseClass.BaseTestWeb;
import Web.WebKeywords;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import BaseClass.BaseTestWeb;
import javax.xml.ws.Action;
import java.io.IOException;
import io.qameta.allure.Step;




public class WebKeywords extends BaseTestWeb {
    private WebPageFunctions myPage;
    private String homePage = "HomePage";
    public String message;


    public WebKeywords(WebDriver driver) throws IOException {
        myPage = new WebPageFunctions(driver, "GlobalKinetic_Web.xlsx", "PageObjectMaster.xlsx");
    }
@Step
    public void NavigateMainMenu(String catagoryName) throws IOException, InterruptedException {

        myPage.buttonClick(homePage, catagoryName);
        Thread.sleep(200);
    }
@Step
    public void ScrollToAndClickElement(String elementToScrollTo) throws IOException {
        myPage.scrollItemIntoView(homePage, elementToScrollTo);
        myPage.buttonClick(homePage, elementToScrollTo);
        myPage.switchToTab();
    }
    @Step
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
    @Step
    public void enterTextIntoMultipleFields(String valueTextbox, String textToType) throws IOException {
        myPage.textBoxType(homePage, valueTextbox, textToType);

    }
    @Step
    public void clickSubmit(String valueName) throws IOException {
        myPage.buttonClick(homePage,valueName);
    }
}












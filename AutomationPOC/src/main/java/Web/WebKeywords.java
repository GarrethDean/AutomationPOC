package Web;

import PageFunctions.WebPageFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import BaseClass.BaseTestWeb;

import java.io.IOException;




public class WebKeywords extends BaseTestWeb {
    private WebPageFunctions myPage;
    private String homePage = "HomePage";
    public String message;


    public WebKeywords(WebDriver driver) throws IOException {
        myPage = new WebPageFunctions(driver, "GlobalKinetic_Web.xlsx", "PageObjectMaster.xlsx");
    }

    public void NavigateMainMenu(String catagoryName) throws IOException, InterruptedException {

        myPage.buttonClick(homePage, catagoryName);
        Thread.sleep(200);
    }

    public void ScrollToAndClickElement(String elementToScrollTo) throws IOException {
        myPage.scrollItemIntoView(homePage, elementToScrollTo);
        myPage.buttonClick(homePage, elementToScrollTo);
        myPage.switchToTab();
    }

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

    public void enterTextIntoMultipleFields(String valueTextbox, String textToType) throws IOException {
        myPage.textBoxType(homePage, valueTextbox, textToType);

    }

    public void clickSubmit(String valueName) throws IOException {
        myPage.buttonClick(homePage,valueName);
    }
}












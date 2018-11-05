package WebTests;

import BaseClass.BaseTestWeb;
import Web.WebKeywords;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Category;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class Hetzner_Web extends BaseTestWeb {

	private WebKeywords myKeyword;

	@BeforeClass
    public void classSetup() throws IOException{
        myKeyword = new WebKeywords(driver);
    }
    @Test (priority = 1,testName = "Navigating through Global Kinetic",alwaysRun = true)
    public void testWebMainCategories() throws Exception {
        //TODO 'Using Todo to make the text stand out'
        //Navigate to all header tabs
        myKeyword.NavigateMainMenu("Team");
        myKeyword.NavigateMainMenu("Services");
        myKeyword.NavigateMainMenu("Work");
        myKeyword.NavigateMainMenu("Products");
        myKeyword.NavigateMainMenu("Careers");
        myKeyword.NavigateMainMenu("Contact");

        //Just using thread to show you what is happening before it continues
        Thread.sleep(2000);
    }

    @Test (priority = 1,testName = "Navigate to global kinetic terms and conditons page",alwaysRun = true)
    public void testTermsAndCondtions() throws Exception {
        //Scroll to terms and contions page
        myKeyword.ScrollToAndClickElement("Terms and Conditions");
        //extracts the terms and condtions page body
        myKeyword.extractAndStoreData();
    }
    
    @Test (priority = 1,testName = "Navigating through Global Kinetic",alwaysRun = true)
    public void sendContactUsFrom() throws Exception {
        //Enters user details either specified in the excel sheet or in the test manually
        myKeyword.enterTextIntoMultipleFields("Message Title","QA Automation assessment");
        myKeyword.enterTextIntoMultipleFields("Name","Garreth");
        myKeyword.enterTextIntoMultipleFields("Email Address","garrethdean210@gmail.com");
        myKeyword.enterTextIntoMultipleFields("Message","Terms and conditions page : "+myKeyword.message);

        //Just using thread to show you what is happening before it continues
        Thread.sleep(2000);

        //Comemented out so that we won't spam the contact us email address.
       // myKeyword.clickSubmit("send");
    }

}

package WebTests;

import BaseClass.BaseTestWeb;
import Web.WebKeywords;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.IOException;

public class Hetzner_Web extends BaseTestWeb {

	private WebKeywords myKeyword;

	@BeforeClass
    public void classSetup() throws IOException{
        myKeyword = new WebKeywords(driver);
    }
    @Test (priority = 1,description = "Navigating to Kinetic",alwaysRun = true)
    @Description("Test Description: Navigate to the Global Kinetic website and navigate through the main headers.")
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
        //Scroll to terms and contions page
        myKeyword.ScrollToAndClickElement("Terms and Conditions");
        //extracts the terms and condtions page body
        myKeyword.extractAndStoreData();

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

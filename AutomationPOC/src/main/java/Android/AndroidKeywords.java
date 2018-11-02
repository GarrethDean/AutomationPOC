package Android;


import PageFunctions.AndroidPageFunctions;
import PageFunctions.WebPageFunctions;
import io.appium.java_client.android.AndroidDriver;

import java.io.IOException;

public class AndroidKeywords {

    private WebPageFunctions myPage;
    private AndroidPageFunctions myPageAndroid;
    private String homePage = "HomePage";


    public AndroidKeywords(AndroidDriver driver) throws IOException {
        myPage = new WebPageFunctions(driver, "Hetzner_Android.xlsx", "PageObjectMasterAndroid.xlsx");
        myPageAndroid = new AndroidPageFunctions(driver, "Hetzner_Android.xlsx", "PageObjectMasterAndroid.xlsx");


    }

    public void UniversalMediaPlayerNavigate(String valueName) throws IOException {

       myPage.buttonClick(homePage,valueName);

    }
    public void validateMusicIsPlayingByicon(String valueName) throws IOException {

        myPage.isButtonVisible(homePage,valueName,5);

    }

    public void scrollToElement() {

        myPageAndroid.scrollDownMobile(1);

    }


}


package AndroidTests;

import BaseClass.BaseTestAndroid;
import Android.AndroidKeywords;
import org.testng.annotations.*;

import java.io.IOException;

public class Hetzner_Android extends BaseTestAndroid {
    private AndroidKeywords myKeyword;

    @BeforeClass
    public void classSetup() throws IOException {
        myKeyword = new AndroidKeywords(driver);

}

    @Test(priority = 1)
    public void testUniversalMediaPlayer() throws Exception {
        //Navigate the app
        myKeyword.UniversalMediaPlayerNavigate("Genres");
        myKeyword.UniversalMediaPlayerNavigate("Cinematic");
        myKeyword.UniversalMediaPlayerNavigate("Item 1");
        //Validate that the song is playing (visually check)
         myKeyword.validateMusicIsPlayingByicon("Music Playing Icon");
         //Pause the song
         myKeyword.UniversalMediaPlayerNavigate("Play/Pause");
        myKeyword.UniversalMediaPlayerNavigate("Navigate Up");
        myKeyword.UniversalMediaPlayerNavigate("Rock");
        myKeyword.scrollToElement();
        myKeyword.UniversalMediaPlayerNavigate("Item 7");

    }

}

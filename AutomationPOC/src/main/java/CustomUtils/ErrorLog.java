package CustomUtils;

import Config.Hetzner;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ErrorLog {

    public void captureErrorDetails(WebDriver driver, String errorMessage) throws IOException{
        // Get the current date
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        String dateStr = sdf.format(date);

        // Build the name of the file
        String fileName = "Error_Logs/" + Hetzner.currentTestName +"_" + dateStr + ".txt";

        File file = new File(fileName);

        //Write the error message to the file
        FileWriter writer = new FileWriter(file, true);
        writer.append("\r\n");
        writer.append(errorMessage);
        writer.close();

        // Build the name of the screenshot file
        String screenShotFileName = "Error_Screenshots/" + Hetzner.currentTestName +"_" + dateStr + ".png";

        //Take the screenshot and convert to a file
        TakesScreenshot scrShot =((TakesScreenshot)driver);
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

        //Move image file to the Error Screenshots folder
        File DestFile=new File(screenShotFileName);
        FileUtils.copyFile(SrcFile, DestFile);

    }

    public void captureAPIErrorDetails(String errorMessage) throws IOException{
        // Get the current date
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        String dateStr = sdf.format(date);

        // Build the name of the file
        String fileName = "Error_Logs/" + Hetzner.currentTestName +"_" + dateStr + ".txt";

        File file = new File(fileName);

        //Write the error message to the file
        FileWriter writer = new FileWriter(file, true);
        writer.append("\r\n");
        writer.append(errorMessage);
        writer.close();

    }

}

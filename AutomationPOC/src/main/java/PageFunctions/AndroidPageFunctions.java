package PageFunctions;

import CustomUtils.ErrorLog;
import ExcelFunctions.ExcelFunctions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.util.ArrayList;

public class AndroidPageFunctions {
	private AndroidDriver driver;
	private WebDriverWait wait;
	private WebElement myButton;
	private WebElement myTextBox;
	private ExcelFunctions myExcel;
	private ArrayList<String> parameterList;
	private WebPageFunctions myPage;
	private ErrorLog myErrorLog;
	private PointOption myPoint;

	public AndroidPageFunctions(AndroidDriver driver, String testFilePath, String masterFilePath) throws IOException {
		this.driver = driver;
		wait  = new WebDriverWait(this.driver, 10);
		myExcel = new ExcelFunctions(testFilePath, masterFilePath);
		parameterList = new ArrayList<String>();
		myPage = new WebPageFunctions(driver, testFilePath, masterFilePath);
		myErrorLog = new ErrorLog();
		myPoint = PointOption.point(80, 800);

	}

	public void scrollDownMobile(int moveDown){
        TouchAction swipe = new TouchAction(driver).press(myPoint)
				.waitAction()
				.moveTo(PointOption.point(80, moveDown))
				.release();
		swipe.perform();
	}
	public void scrollupMobile(int moveUp){
		TouchAction swipe = new TouchAction(driver).press(PointOption.point(350, 1120))
				.waitAction()
				.moveTo(PointOption.point(10, moveUp))
				.release();
		swipe.perform();
	}

	public void tapByCoordindates(int xCoordinates, int yCoordinates){
		PointOption tapPoint = PointOption.point(xCoordinates, yCoordinates);
		new TouchAction(driver).tap(tapPoint).perform();
	}

	public void tapbyCoordinatesFromBottom(int XCoordinates, int distanceFromBottom){

		// Read in the length of the screen
		Dimension dimensions = driver.manage().window().getSize();
		int height = dimensions.getHeight();

		// Get Y coordinates relative to the bottom of the screen
		int YCoordinates = height - distanceFromBottom;

		// Tap on the coordinates
		tapByCoordindates(XCoordinates, YCoordinates);
	}

	public void tapbyCoordinatesFromRight(int distanceFromRight, int YCoordinates){

		// Read in the width of the screen
		Dimension dimensions = driver.manage().window().getSize();
		int width = dimensions.getWidth();

		// Get X coordinates relative to the right hand side of the screen
		int XCoordinates = width - distanceFromRight;

		// Tap on the coordinates
		tapByCoordindates(XCoordinates, YCoordinates);
	}

}

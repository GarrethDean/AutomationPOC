package PageFunctions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import CustomUtils.ErrorLog;
import ExcelFunctions.ExcelFunctions;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class WebPageFunctions {
	private WebDriver driver;
	private WebDriverWait wait;
	private WebElement myButton;
	private ExcelFunctions myExcel;
	private ArrayList<String> parameterList;
	private ErrorLog myErrorLog;
	private int longWait;
	private ExtentReports extentReport;
	private ExtentTest test;


	public WebPageFunctions(WebDriver driver, String testFilePath, String masterFilePath) throws IOException {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 20);
		myExcel = new ExcelFunctions(testFilePath, masterFilePath);
		parameterList = new ArrayList<String>();
		myErrorLog = new ErrorLog();
		longWait = 60;
		WebElement sLoader;
	}


	public WebElement getElement(String sheetName, String valueName, int waitTime) throws IOException {
		WebElement webElement = null;

		// Read in the values from the spreadsheet
		parameterList = myExcel.buildExcelList(sheetName, valueName);
		String identifierName = parameterList.get(0);
		String identifierType = parameterList.get(1);
		String masterSheetName = parameterList.get(2);

		// Read the identifier from the master sheet
		String identifier = myExcel.readFromMasterFile(masterSheetName, identifierName);

		// wait for the button to be visible
		pauseUntilObjectVisible(sheetName, valueName, waitTime);

		// Set the identifier according to type
		switch (identifierType.toUpperCase()) {
			case "XPATH":
				webElement = driver.findElement(By.xpath(identifier));
				break;
			case "ID":
				webElement = driver.findElement(By.id(identifier));
				break;
			case "CSS":
				webElement = driver.findElement(By.cssSelector(identifier));
				break;
			case "NAME":
				webElement = driver.findElement(By.name(identifier));
				break;
			case "IOSPRED":
				webElement = driver.findElement(MobileBy.iOSNsPredicateString(identifier));
				break;
			default:
				myErrorLog.captureErrorDetails(driver, "Invalid identifier type: Must be: Xpath, ID or CSS.");
				Assert.fail();
		}
		return webElement;
	}
	public void pauseUntilObjectVisible(String sheetName, String valueName, int waitTime) throws IOException {
		// Read in the identifier type and master sheet name from the spreadsheet
		parameterList = myExcel.buildExcelList(sheetName, valueName);
		String identifierName = parameterList.get(0);
		String identifierType = parameterList.get(1);
		String masterSheetName = parameterList.get(2);

		WebDriverWait wait = new WebDriverWait(driver, waitTime);

		// Read the identifier from the master sheet
		String identifier = myExcel.readFromMasterFile(masterSheetName, identifierName);

		//waiting for the loading icon to finnish.

		switch (identifierType.toUpperCase()) {
			case "XPATH":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(identifier)));
				break;
			case "ID":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(identifier)));
				break;
			case "CSS":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(identifier)));
				break;
			case "NAME":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(identifier)));
				break;
			case "IOSPRED":
				wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.iOSNsPredicateString(identifier)));
				break;
			default:
				myErrorLog.captureErrorDetails(driver, "Invalid identifier type: Must be: Xpath, ID or CSS.");
				Assert.fail();
		}
	}
	public void buttonClick(String sheetName, String valueName) throws IOException {
		try {
			// Click on the button
			myButton = getElement(sheetName, valueName, longWait);
			myButton.click();
		} catch (Exception e) {
			myErrorLog.captureErrorDetails(driver, "Could not click on the button: " + valueName + ". " +
					"Check that the button is displayed.");
			Reporter.log("Failed to  clicked on " + valueName);
			Assert.fail();

		}
	}
	public void textBoxType(String sheetName, String valueName, String textToType) throws IOException {
		try {
			// Read in the values from the spreadsheet
			parameterList = myExcel.buildExcelList(sheetName, valueName);

			// Clear the text box if it's not empty
			WebElement myTextBox = getElement(sheetName, valueName, longWait);
			if (!(myTextBox.getText()).equals("")) {
				myTextBox.clear();
			}
			myTextBox.sendKeys(textToType);
		} catch (Exception e) {
			myErrorLog.captureErrorDetails(driver, "Could not type in the text box: " + valueName +
					" . Check that the text box is displayed.");
			Assert.fail();
		}
	}
	public void scrollItemIntoView(String sheetName, String valueName) throws IOException {
		// Locate the element to scroll to
		WebElement scrollItem = getElement(sheetName, valueName, 5);

		try {
			// Scroll to the item
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", scrollItem);
			jse.executeScript("window.scrollBy(0,-80)");
		} catch (Exception e) {
			myErrorLog.captureErrorDetails(driver, "Could not scroll to " + valueName +
					". Check that the item is displayed.");
			Assert.fail();
		}
	}
	public boolean isButtonVisible(String sheetName, String valueName, int waitTime) throws IOException {
		Boolean isVisible;

		// Check if the button is displayed
		try {
			// wait for the button to be visible
			pauseUntilObjectVisible(sheetName, valueName, waitTime);

			// Button is visible
			isVisible = true;
		} catch (Exception e) {
			isVisible = false;
		}
		return isVisible;
	}
	public void switchToTab() {
		//Switch to new window
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
	}
	public void switchToDefaultContent() {
		//Switch to new window
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));
	}






}

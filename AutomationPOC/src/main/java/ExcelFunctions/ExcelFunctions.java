package ExcelFunctions;

import CustomUtils.ErrorLog;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.testng.*;

public class ExcelFunctions {
	private XSSFWorkbook  wb;
	private XSSFWorkbook pageObjectMaster;
	private XSSFSheet sheet;
	private String excelMasterFilePath;
	private String excelFilePath;
	private ArrayList<String> myList = new ArrayList<>();
	private Map<String,String> myMap = new HashMap<>();
	private ErrorLog myErrorLog;

	public ExcelFunctions(String excelFilePath, String excelMasterFilePath) throws IOException {
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream ExcelFileToRead = classloader.getResourceAsStream(excelFilePath);
		this.excelMasterFilePath = excelMasterFilePath;
		this.excelFilePath = excelFilePath;
		wb = new XSSFWorkbook(ExcelFileToRead);
		InputStream masterFile = classloader.getResourceAsStream(excelMasterFilePath);
		pageObjectMaster = new XSSFWorkbook (masterFile);
		myErrorLog = new ErrorLog();
	}

	// Overload the constructor for APIFunctions class
	public ExcelFunctions(String excelFilePath) throws IOException {
		this.excelFilePath = excelFilePath;
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream ExcelFileToRead = classloader.getResourceAsStream(excelFilePath);
		wb = new XSSFWorkbook(ExcelFileToRead);
		myErrorLog = new ErrorLog();
	}

	// Returns the parameters from a row in an excel sheet
	public ArrayList<String> buildExcelList(String sheetName, String valueName) throws IOException{
		// Open the sheet and count the number of rows
		sheet = wb.getSheet(sheetName);
		int MaxRow = sheet.getLastRowNum() + 1;
		myList.clear();

		// Find the row to be used
		for (int i = 0; i < MaxRow; i++) {
			if (sheet.getRow(i) != null) {
				String cellValue = (sheet.getRow(i).getCell(0)).toString();
				int MaxColumn = sheet.getRow(i).getLastCellNum();
				if (cellValue.equals(valueName)) {

					// Build the list of parameters in the row
					for (int j = 1; j < MaxColumn; j++) {
						String myValue = (sheet.getRow(i).getCell(j)).toString();
						myList.add(myValue);
					}
					break;
				}
			}
		}
		// Fail the test if the value isn't in the spreadsheet
		if (myList.size() == 0){
			myErrorLog.captureAPIErrorDetails("Could not find: " + valueName +
					" in the sheet. Check that the name is correct.");
			Assert.fail();
		}
		return myList;
	}

	public Map<String, String> buildformParamsMap(String sheetName, String valueName) throws IOException{

		// Open the sheet and count the number of rows
		int MaxRow = 0;
		try{
			sheet = wb.getSheet(sheetName);
			MaxRow = sheet.getLastRowNum() + 1;
		}
		catch (Exception e){
			myErrorLog.captureAPIErrorDetails("Could not open the sheet: " + sheetName +
					" . Check that the name is correct.");
			Assert.fail();
		}
		myMap.clear();

		// Find the rows to be used
		try {
			for (int i = 0; i < MaxRow; i++) {
				if (sheet.getRow(i) != null) {
					// Build the map based on required rows
					String cellValue = (sheet.getRow(i).getCell(2)).toString();
					if (cellValue.equals(valueName)) {
						String myValue1 = (sheet.getRow(i).getCell(0)).toString();
						String myValue2 = (sheet.getRow(i).getCell(1)).toString();
						myMap.put(myValue1, myValue2);
					}
				}
			}
		}
		catch (Exception e){
			myErrorLog.captureAPIErrorDetails("Could not find " + valueName +
					" in the sheet. Check that the name is correct.");
			Assert.fail();
		}
		return myMap;
	}

	public String readFromMasterFile(String sheetName, String valueName) throws IOException{

		// Open the sheet and count the number of rows
		int MaxRow = 0;
		String identifierValue = null;
		try {
			sheet = pageObjectMaster.getSheet(sheetName);
			MaxRow = sheet.getLastRowNum() + 1;
		}
		catch (Exception e){
			myErrorLog.captureAPIErrorDetails("Could not open the sheet: " + sheetName +
					" . Check that the name is correct.");
			Assert.fail();
		}
		try{
			// Find the row to be used
			for (int i = 0; i < MaxRow; i++) {
				if (sheet.getRow(i) != null) {
					String cellValue = (sheet.getRow(i).getCell(0)).toString();
					if (cellValue.equals(valueName)) {
						identifierValue = (sheet.getRow(i).getCell(1)).toString();
						break;
					}
				}
			}
		}
		catch (Exception e){
			myErrorLog.captureAPIErrorDetails("Could not find the object: " + valueName +
					" in the master file.");
			Assert.fail();
		}
		if (identifierValue == null) {
			myErrorLog.captureAPIErrorDetails("No value returned for the object: " + valueName +
					". Check that the identifier name and master sheet name are correct.");
			Assert.fail();
		}
		return identifierValue;
	}

	public void writeToCell(String sheetName, String valueName, String columnName, String cellTextValue) throws IOException{

		// Open the sheet and count the number of rows and columns
		sheet = wb.getSheet(sheetName);
		int MaxColumn = sheet.getRow(0).getLastCellNum();
		int MaxRow = sheet.getLastRowNum() + 1;

		for (int i = 0; i < MaxRow; i++) {
			if (sheet.getRow(i) != null) {
				// Find the row to be written to
				String cellValue = (sheet.getRow(i).getCell(0)).toString();
				if (cellValue.equals(valueName)) {
					for (int j = 1; j < MaxColumn; j++) {

						// Find the cell to be written to
						String columnValue = (sheet.getRow(0).getCell(j)).toString();
						if (columnValue.equals(columnName)) {
							Cell cell = sheet.getRow(i).getCell(j);

							// Create a cell if it's null
							if (cell == null){
								cell = sheet.getRow(i).createCell(j);
							}

							cell.setCellValue(cellTextValue);
							FileOutputStream fos = new FileOutputStream(new File(this.excelFilePath));
							wb.write(fos);
							fos.close();
							break;
						}
					}
				}
			}
		}
	}
}


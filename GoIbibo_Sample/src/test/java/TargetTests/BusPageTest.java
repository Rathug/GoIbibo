package TargetTests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.inject.internal.BytecodeGen.Visibility;
import com.mysql.cj.protocol.Resultset;

import BaseResourcesPackage.Base;
import POMTargetScreens.LoginPage;
import POMTargetScreens.MainHomePage;
import POMTargetScreens.SearchResultPage;
import jdk.internal.org.jline.utils.Log;

public class BusPageTest extends Base {

	public static XSSFWorkbook workbook;
	public static XSSFSheet worksheet;
	public static DataFormatter formatter = new DataFormatter();
	public WebDriver driver;
	LoginPage lh;

	/*
	 * @BeforeTest public void InitialCall() throws IOException { // driver =
	 * InitializeWebDriver(); driver.manage().timeouts( ).implicitlyWait(15,
	 * TimeUnit.SECONDS); driver.manage().window().maximize(); lh = new
	 * LoginPage(driver);
	 * 
	 * }
	 */

	@Test(dataProvider = "FromDatabase")
	public void excelcheck(String Username, String Password) throws IOException {

		driver = InitializeWebDriver();
		driver.get("https://www.cleartrip.com/signin");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		lh = new LoginPage(driver);
		lh.usernamefldmthd().sendKeys(Username);
		lh.pwdfldmthd().sendKeys(Password);
		System.out.println("Excel done");
	}

	@DataProvider
	public static Object[][] LoginData() throws IOException {
		Object[][] obj = new Object[2][2];
		obj[0][0] = "guru";
		obj[0][1] = "lolo";
		obj[1][0] = "sabu";
		obj[1][1] = "ooyo";
		System.out.println(obj);
		return obj;

	}

	/*
	 * @DataProvider public static Object[][] LoginData1() throws IOException {
	 * 
	 * File srcFile = new File("F://DataSheet.xlsx"); FileInputStream fis = new
	 * FileInputStream(srcFile); XSSFWorkbook Wrkbook =new XSSFWorkbook(fis);
	 * XSSFSheet SheetIndex = Wrkbook.getSheetAt(0); int RowsCount =
	 * SheetIndex.getPhysicalNumberOfRows();
	 * System.out.println("Rows kooo"+RowsCount); XSSFRow RowData
	 * =SheetIndex.getRow(0); int CellsCountorcolumnscount =
	 * RowData.getPhysicalNumberOfCells();
	 * System.out.println("Col kooo"+CellsCountorcolumnscount); //do it size -1 coz
	 * array starts with 0 so Object[][] TargetData = new
	 * Object[RowsCount-1][CellsCountorcolumnscount]; for(int i=0;i<RowsCount-1;i++)
	 * { SheetIndex.getRow(i+1);
	 * 
	 * for(int j=0;j<CellsCountorcolumnscount;j++) { TargetData[i][j]=
	 * RowData.getCell(j).getStringCellValue();
	 * //System.out.print("||"+RowData.getCell(j).getStringCellValue()); }
	 * //System.out.println(); } return TargetData; } }
	 */
	@DataProvider
	public static Object[][] LoginData1() throws IOException {

		FileInputStream fileInputStream = new FileInputStream("F://DataSheet.xlsx"); // Excel sheet file location get
																						// mentioned here
		workbook = new XSSFWorkbook(fileInputStream); // get my workbook
		worksheet = workbook.getSheetAt(0);// get my sheet from workbook
		XSSFRow Row = worksheet.getRow(0);

		int RowNum = worksheet.getPhysicalNumberOfRows();// count my number of Rows
		int ColNum = Row.getLastCellNum();
		System.out.println("cellscolumn:" + Row.getLastCellNum());// get laet last ColNum

		Object Data[][] = new Object[RowNum - 1][ColNum]; // pass my count data in array

		for (int i = 0; i < RowNum - 1; i++) // Loop work for Rows
		{
			XSSFRow row = worksheet.getRow(i + 1);

			for (int j = 0; j < ColNum; j++) // Loop work for colNum
			{
				if (row == null)
					Data[i][j] = "";
				else {
					XSSFCell cell = row.getCell(j);
					if (cell == null)
						Data[i][j] = ""; // if it get Null value it pass no data
					else {
						String value = formatter.formatCellValue(cell);
						Data[i][j] = value; // This formatter get my all values as string i.e integer, float all type
											// data value
					}
				}
			}
		}

		return Data;
	}

	// Checking Database read
	@DataProvider
	public Object[][] FromDatabase() throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/LOGINDB", "root", "root");
		// for url
		// "jdbc:mysql://"+host+":"+port+"/databasename";
		// "jdbc:mysql://127.0.0.1:3306/LOGINDB","root","root");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM USERDATA");
		//rs.last();
		int rowscnt = 3;
		ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
		int colscnt = rsmd.getColumnCount();
		Object DBData[][] = new Object[rowscnt][colscnt];
		//rs.beforeFirst();
		int i=0;
		//Iterating the data in the Table
		while (rs.next())
		{
			for(int j=0;j<colscnt;j++)
			{
				if(j==0)
				{
					DBData[i][j]=rs.getString("Username");	
				}
				else
				{
					DBData[i][j]=rs.getString("Password");	
				}
			}
			i++;	
			}
		return DBData;
			
		}

}

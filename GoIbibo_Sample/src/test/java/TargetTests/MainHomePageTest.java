package TargetTests;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import BaseResourcesPackage.Base;
import POMTargetScreens.MainHomePage;
import POMTargetScreens.SearchResultPage;
import jdk.internal.org.jline.utils.Log;

public class MainHomePageTest extends Base {
    
	
	public WebDriver driver;
	MainHomePage mh;
	SearchResultPage sp;
	public int count = 0;

	@BeforeTest
	public void InitialCall() throws IOException {
		driver = InitializeWebDriver();
		driver.get("https://www.goibibo.com/");
		driver.manage().timeouts( ).implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		mh = new MainHomePage(driver);
		sp = new SearchResultPage(driver);
	}

	@Test(dataProvider = "FromAndTOSuggestData")
	public void AnputFromSuggestion(String From, String To) throws IOException, InterruptedException {
		// Actions a= new Actions(driver);
		// a.click(mh.FromSuggestMethod()).build().perform();
		// a.sendKeys(mh.FromSuggestMethod(),From).build().perform();
		// a.sendKeys(Keys.ARROW_DOWN).build().perform();
		// a.sendKeys(mh.FromSuggestMethod(),Keys.ARROW_DOWN).build().perform();
		mh.FromSuggestMethod().clear();
		mh.FromSuggestMethod().sendKeys(From);
		Thread.sleep(3000);
		mh.FromSuggestMethod().sendKeys(Keys.ARROW_DOWN);
		mh.FromSuggestMethod().sendKeys(Keys.ENTER);
		mh.ToSuggestMethod().sendKeys(To);
		Thread.sleep(3000);
		mh.ToSuggestMethod().sendKeys(Keys.ARROW_DOWN);
		mh.ToSuggestMethod().sendKeys(Keys.ENTER);
		
	}

	@Test(dataProvider = "DatesSelection")
	public void DatesSel(String strtmnth, String strdate, String Destmnth, String Destdate) throws IOException {
		mh.DepCalMethod().click();
		String v = mh.MonthPickerMethod().getText();
		System.out.println(v);
		while (!mh.MonthPickerMethod().getText().contains(strtmnth)) {
			mh.MonthNxtBtnMethod().click();
		}
		System.out.println(mh.MonthPickerMethod().getText());
		
		
		
		for (int i = 0; i < mh.DaysPickerMethod().size(); i++) {
			String[] Text = mh.DaysPickerMethod().get(i).getText().split("\n");
			if (Text[0].equalsIgnoreCase(strdate)) {
				mh.DaysPickerMethod().get(Integer.parseInt(Text[0]) - 1).click();
				break;

			}
		}
		mh.RetCalMethod().click();
		while (!mh.MonthPickerMethod().getText().contains(Destmnth)) {

			mh.MonthNxtBtnMethod().click();
		}
		for (int j = 0; j < mh.DaysPickerMethod().size(); j++) {
			String[] Text1 = mh.DaysPickerMethod().get(j).getText().split("\n");
			if (Text1[0].equalsIgnoreCase(Destdate))
			{
				mh.DaysPickerMethod().get(Integer.parseInt(Text1[0]) - 1).click();
				break;
			}
		}
	}

	@Test(dataProvider = "TravelData")
	public void TravelBlock(Integer Ad, Integer Ch, Integer Inf, String opt) throws IOException, InterruptedException {

		mh.TravelblockMethod().click();
		System.out.println(Ad);
		while (count < Ad) {
			mh.AdultsBtnMethod().click();
			count++;
		}
		System.out.println(count);
		count = 0;
		while (count < Ch) {
			mh.ChildbtnMethod().click();
			count++;
		}
		count = 0;
		while (count < Inf) {
			mh.InfnbtnMethod().click();
			count++;
		}

		// mh.TravelClassDropDwnMethod().click();
		Select S = new Select(mh.TravelClassDropDwnMethod());
		S.selectByVisibleText(opt);
		mh.SubmitBtn().click(); 
		Thread.sleep(2000);
				WebDriverWait  w = new WebDriverWait(driver,30);
				w.until(ExpectedConditions.visibilityOf(sp.BookBtnMethod()));
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("window.scrollBy(0,1750)");
				Thread.sleep(5000);
				sp.Vatsan1Method();
				
				
				
				//System.out.println(sp.PriceListMethod().size());
			//	JavascriptExecutor jse = (JavascriptExecutor)driver;
				//jse.executeScript("window.scrollBy(0,1750)");
				//Thread.sleep(9000);
				//System.out.println(sp.PriceListMethod().size());
				//for (int l=0;l<sp.PriceListMethod().size();l++) 
			//	{
			//	if(checkin min and max)
			//	{
			//		min = 
			///	    max =
			//	}
				// System.out.println(sp.PriceListMethod().get(l).getText());
				
				//}
			//	List<String> Pv; 
			//	Pv = sp.PriceListMethod().stream().map(s->s.getText()).collect(Collectors.toList());
			//	Pv.stream().forEach(s->System.out.println(s));	
			//	log.error("Full Data not reached");
			//sp.SourcePriceBtnMethod().click();
	}
	
	
	
	
	
	
	// -------DATA PROVIDERS--------

	@DataProvider
	public Object FromAndTOSuggestData() {

		Object[][] obj = new Object[1][2];
		obj[0][0] = "CJB";
		obj[0][1] = "HYD";
		return obj;

	}

	@DataProvider
	public Object DatesSelection() {
		Object[][] Dbj = new Object[1][4];
		Dbj[0][0] = "January";
		Dbj[0][1] = "1";
		Dbj[0][2] = "January";
		Dbj[0][3] = "3";
		return Dbj;
	}

	@DataProvider
	public Object TravelData() {

		Object[][] Tvj = new Object[1][4];
		Tvj[0][0] = 3;
		Tvj[0][1] = 2;
		Tvj[0][2] = 1;
		Tvj[0][3] = "Economy";
		return Tvj;

	}
}

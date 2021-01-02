package POMTargetScreens;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class MainHomePage{
	
	WebDriver driver;
	
	By FromSuggest = By.id("gosuggest_inputSrc");
	By ToSuggest = By.id("gosuggest_inputDest");
	By Depcal = By.id("departureCalendar");
	By Retcal = By.id("returnCalendar");
	By MonthPicker = By.cssSelector("div[class='DayPicker-Caption']");
    By MonthNextBtn = By.cssSelector("span.DayPicker-NavButton.DayPicker-NavButton--next");
    By MonthPrevBtn = By.cssSelector("span.DayPicker-NavButton.DayPicker-NavButton--prev");
    By DaysPicker = By.cssSelector("div.calDate");
	By Travelblock = By.cssSelector("div[id='pax_link_common']");
    By AdultsBtn = By.id("adultPaxPlus");
    By Childbtn = By.id("childPaxPlus");
    By Infnbtn = By.id("infantPaxPlus");
    By TravelClassDrop = By.id("gi_class");
    By Subbtn = By.id("gi_search_btn");
    By Busicon =By.xpath("//i[@class='icon-bus db blue ico24 lh1-2 padT5 padB5']");
  
   
	public MainHomePage(WebDriver driver2) {
		this.driver = driver2;
	}

	public WebElement FromSuggestMethod()
	{
		return driver.findElement(FromSuggest);
		
	}
	
	public WebElement ToSuggestMethod()
	{
		return driver.findElement(ToSuggest);
		
	}

	public WebElement DepCalMethod()
	{
		return driver.findElement(Depcal);
		
	}
	
	public WebElement RetCalMethod()
	{
		return driver.findElement(Retcal);
		
	}

   public WebElement MonthPickerMethod()
	{
		return driver.findElement(MonthPicker);
		
		
	}
	public WebElement MonthPrevBtnMethod()
	{
		return driver.findElement(MonthPrevBtn);
		
	}
	public WebElement MonthNxtBtnMethod()
	{
		return driver.findElement(MonthNextBtn);
		
	}
	
	public List<WebElement> DaysPickerMethod()
	{
		return driver.findElements(DaysPicker);
		
	}
	public WebElement TravelblockMethod()
	{
		
		return driver.findElement(Travelblock);
		
	}
	public WebElement AdultsBtnMethod()
	{
		
		return driver.findElement(AdultsBtn) ;
		
	}
	public WebElement ChildbtnMethod()
	{
		return driver.findElement(Childbtn);
		
	}
	public WebElement InfnbtnMethod()
	{
		return driver.findElement(Infnbtn);
		
	}
	public WebElement TravelClassDropDwnMethod()
	{
	
		return driver.findElement(TravelClassDrop);
		
		
	}

	public WebElement SubmitBtn() {
		// TODO Auto-generated method stub
		return driver.findElement(Subbtn);
	}
	public WebElement BusIconMethod()
	{
		return driver.findElement(Busicon);
		
	}


}

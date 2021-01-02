package POMTargetScreens;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SearchResultPage{

	WebDriver driver;
	
	By BookButton = By.xpath("//div[@class='dF justifyBetween']/div[2]/span[2]/span/input");
	By SourcePriceBtn= By.xpath("//div[@class='fltHpyOnwrdWrp']/div/ul/li[4]");
	By PriceList=By.xpath("//div[@class='fltHpyOnwrdWrp']//span[@class='ico20']");
	//driver.findElement(By.class("fltHpyOnwrdWrp")).findElements(By.xpath("//span[@class='ico20']")).get(i).getText();
	By Vatsan= By.className("fltHpyOnwrdWrp");
	By Vatsan1 =By.xpath("//span[@class='ico20']");
			
	
	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement BookBtnMethod()
	{
		
		return driver.findElement(BookButton);
		
	}
	public List<WebElement> PriceListMethod()
	{	
		return driver.findElements(PriceList);	
	}
	public WebElement SourcePriceBtnMethod()
	{
		
		return driver.findElement(SourcePriceBtn);	
	}
	public WebElement VatsanMethod()
	{
		
		return driver.findElement(Vatsan);
		
	}
	public List<WebElement> Vatsan1Method()
	{
		List<WebElement> VAL = driver.findElement(By.className("fltHpyOnwrdWrp")).findElements(By.xpath("//span[@class='ico20']"));
		for(int i=0;i<VAL.size();i++)
		{
			System.out.println(VAL.get(i).getText());
		}
		
		
		//List<String>Smpleval = new ArrayList<String>();
		
		return driver.findElements(Vatsan1);
		
	}
	



}

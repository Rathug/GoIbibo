package POMTargetScreens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	 WebDriver driver;

	 By usernamefld = By.id("email");
	 By pwdfld = By.id("password");
	   
	   
	 
	public LoginPage(WebDriver driver2)
	{
		this.driver = driver2;
	}
	
	public WebElement usernamefldmthd()
	{
		return driver.findElement(usernamefld);
		
		
	}
	public WebElement pwdfldmthd()
	{
		return driver.findElement(pwdfld);
		
	}
	}



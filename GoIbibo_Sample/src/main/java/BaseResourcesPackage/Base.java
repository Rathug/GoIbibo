package BaseResourcesPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Driver;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class Base {
	
	public WebDriver driver;
	
	public WebDriver InitializeWebDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream Fp = new FileInputStream("G:\\Eclipse for selenium\\GoIbibo_Sample\\src\\main\\java\\BaseResourcesPackage\\Resources.properties");
		prop.load(Fp);
		String BrowserName = prop.getProperty("browser");
		if(BrowserName.equalsIgnoreCase("chrome"))
		{
			
			System.setProperty("webdriver.chrome.driver","G:\\chromedriver.exe");
			driver = new ChromeDriver();
					}
		else if(BrowserName.equalsIgnoreCase("firefox"))
		{
			 driver = new FirefoxDriver();
			System.out.println("FirefoxDriver not present or Mapped properly");
			System.setProperty("webdriver.chrome.driver","F:\\chromedriver.exe");
			driver.get(prop.getProperty("url"));
		}
		else {
			System.out.println("No Drivers Found");
		}
		return driver;
	}
	
	public String GetScreenshotMethod(String FNAME, WebDriver driver2)throws IOException
	{
		String Descrnpath =System.getProperty("user.dir")+"\\Error Screenshots\\"+FNAME+".png";
	File Src = ((TakesScreenshot)driver2).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(Src, new File(Descrnpath));
	return Descrnpath;
	}
	
	
	
	

}

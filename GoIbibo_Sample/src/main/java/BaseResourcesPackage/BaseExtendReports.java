package BaseResourcesPackage;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseExtendReports {
static ExtentReports extent;
	
	public static ExtentReports RepConfig()
	{
		
		String path =System.getProperty("user.dir")+"\\reports\\indvex.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		extent =new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Mass Tester", "Rathish Ganesh");
		return extent;
		
	} 
}



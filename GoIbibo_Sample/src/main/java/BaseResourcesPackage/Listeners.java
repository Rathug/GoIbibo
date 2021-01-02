package BaseResourcesPackage;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Log;

import BaseResourcesPackage.Base;


public class Listeners extends Base implements ITestListener {
	ExtentTest test;
	ExtentReports extent =BaseExtendReports.RepConfig();;
	ThreadLocal<ExtentTest> extentTest =new ThreadLocal<ExtentTest>();
	
	public void onTestStart(ITestResult result) {
		
		test= extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("test overuu");
		test.log(Status.PASS,"Test Successeh");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		//Getting failed method testcase name;
		test.fail(result.getThrowable());	
	String FailedTCName =result.getMethod().getMethodName();
	//Create a dummy object
	WebDriver driver = null;
	//paste it for getting driver life
	try {
		driver =(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
	}catch(Exception E)
	{
		E.printStackTrace();
	}
	try {
		test.addScreenCaptureFromPath(GetScreenshotMethod(FailedTCName,driver),result.getMethod().getMethodName());
		GetScreenshotMethod(FailedTCName,driver);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
		
	}

}

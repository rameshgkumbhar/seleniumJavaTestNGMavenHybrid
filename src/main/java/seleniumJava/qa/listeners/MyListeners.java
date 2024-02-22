package seleniumJava.qa.listeners;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import seleniumJava.qa.utils.ExtentReporter;
import seleniumJava.qa.utils.Utilities;

public class MyListeners implements ITestListener {
	
	ExtentReports extentReport;
	ExtentTest extentTest;
	
	@Override
	public void onStart(ITestContext context) {
		extentReport = ExtentReporter.generateExtentReport();
	}

	@Override
	public void onTestStart(ITestResult result) {
		//String testName = result.getName();
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.INFO, result.getName()+ "execution started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		//String testName = result.getName();
		extentTest.log(Status.PASS,result.getName()+ "executed successfully");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// String testName = result.getName();
		
		WebDriver driver = null;
		
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException e) {
			e.printStackTrace();
		}

//		File srcScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//		String destnationScreenshotPath = System.getProperty("user.dir")+"\\Screenshots\\"+result.getName()+".png";
//		
//		try {
//			FileHandler.copy(srcScreenshot, new File(destnationScreenshotPath));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		String destnationScreenshotPath = Utilities.captureScreenshot(driver, result.getName());
		
		extentTest.addScreenCaptureFromPath(destnationScreenshotPath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, result.getName()+ "got Failed");			
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// String testName = result.getName();
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, result.getName() + " got skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
		String extentReportsPath = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReport = new File(extentReportsPath);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

package MyAtomation.SeleniumAutomation.TestComponents;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class TestListeners implements ITestListener {
	
	ExtentReports extent;
	 ExtentTest test;
	
	 
	 WebDriver driver;
	 
 ThreadLocal<ExtentTest> threadExtentTest = new ThreadLocal<ExtentTest>();

	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
		extent.flush();

	}

	@Override
	public void onStart(ITestContext arg0) {
		
		// TODO Auto-generated method stub
	
		

		ExtentSparkReporter htmlReporter  = new ExtentSparkReporter(System.getProperty("user.dir") + "//reports//extent-report.html");
	        
		extent = new ExtentReports();
		 
		 
	    extent.attachReporter(htmlReporter);


	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		
		threadExtentTest.get().log(Status.FAIL, "Test fail");

		threadExtentTest.get().fail(arg0.getThrowable());
		
		 try {
			driver = (WebDriver) arg0.getTestClass().getRealClass().getField("driver").get(arg0.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 System.out.println(arg0.getTestClass());
		 System.out.println(arg0.getTestClass().getRealClass());



		
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			String customPath = System.getProperty("user.dir") + "//reports//screenshot1.png";

			try {
				Files.copy(screenshot.toPath(), Path.of(customPath), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			threadExtentTest.get().addScreenCaptureFromPath(customPath, arg0.getMethod().getMethodName());
		
			System.out.println(arg0.getName());
			System.out.println("Screenshot taken: " + new File(customPath).getAbsolutePath());
		
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
		threadExtentTest.get().log(Status.SKIP, "Test skipped");

	}

	@Override
	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub

		
		
		
		
	        
	  
	        
	        test = extent.createTest(arg0.getName());
	        
	        threadExtentTest.set(test);
		
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		
		threadExtentTest.get().log(Status.PASS, "Test pass");

	}

}

package MyAtomation.SeleniumAutomation.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.*;

import MyAtomation.SeleniumAutomation.PageClaases.LandingPage;

public class TestUtilities {

	private WebDriver driver;

	protected LandingPage objectLandingPage;

	public WebDriver driverMethod() throws IOException {

		Properties prop = new Properties();

		FileInputStream fiso = new FileInputStream(System.getProperty("user.dir")
				+ "//src//main//java//MyAtomation//Selenium//resources//Global.properties");

		prop.load(fiso);
		
		String broswer =  System.getProperty("browser") !=null ? System.getProperty("browser"): prop.getProperty("browser");
		
		//mvn -Dbrowser=chrome test "maven command to be used to run test in terminal", if mvn test is used then 
		// System.getProperty("browser") becomes null so browser gets value from prop.getProperty("browser")
		

		if (broswer.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			
			if (broswer.contains("chromeoptions"))
			{
			options.addArguments("headless");
			}

			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));

		}
		
		
		else if (broswer.equalsIgnoreCase("firefox")) {
			
			
			driver = new FirefoxDriver();
			driver.manage().window().setSize(new Dimension(1440,900));
		}

		return driver;

	}

	@BeforeMethod
	public void launchAppPage() throws IOException {
		
		driver = driverMethod() ;

		objectLandingPage = new LandingPage(driver);

		objectLandingPage.getPage("https://rahulshettyacademy.com/client");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		driver.manage().window().maximize();

	}

	@AfterMethod
	public void closeBrowser() throws IOException {

		if (driver != null) {

			driver.quit();
		}
	}

	public List<HashMap<String, String>> jsontoHash(String path) throws IOException {

		@SuppressWarnings("deprecation")
		String jsonString = FileUtils.readFileToString(new File(path));

		List<HashMap<String, String>> resultMap = convertJsonArrayToListOfHashMaps(jsonString);

		return resultMap;
	}

	@SuppressWarnings("unchecked")
	private List<HashMap<String, String>> convertJsonArrayToListOfHashMaps(String jsonArrayString) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();

		// Use TypeReference to specify the desired type (List<HashMap>)
		return objectMapper.readValue(jsonArrayString, new TypeReference<List<HashMap<String, String>>>() {
		});
	}

}

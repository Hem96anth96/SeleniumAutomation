package MyAtomation.SeleniumAutomation.Tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import MyAtomation.SeleniumAutomation.PageClaases.CartPage;
import MyAtomation.SeleniumAutomation.PageClaases.DashboardPage;
import MyAtomation.SeleniumAutomation.PageClaases.LandingPage;
import MyAtomation.SeleniumAutomation.PageClaases.PaymentPage;
import MyAtomation.SeleniumAutomation.TestComponents.Retry;
import MyAtomation.SeleniumAutomation.TestComponents.TestUtilities;

public class TestngTests2 extends TestUtilities{
	
    
	
	@Test
	public void test3()  {
		
		
		
		Assert.assertTrue(true);
		
	}



	}
	
	
	
	

	
	
	
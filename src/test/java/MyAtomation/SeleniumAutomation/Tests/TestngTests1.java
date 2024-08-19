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

public class TestngTests1 extends TestUtilities {

	@Test(dataProvider = "myDataProvider")
	// public void test1(String email, String password) throws InterruptedException,
	// IOException {

	public void test1(HashMap<String, String> data) throws InterruptedException, IOException {

		DashboardPage objectDashboardPage = objectLandingPage.login(data.get(("email")), data.get("password"));

		objectDashboardPage.itemsToCart();

		List<String> dashItemsList = objectDashboardPage.returnItemsAddedFromDash();

		String dashItems = dashItemsList.toString() + "dash";

		CartPage objectCartPage = objectDashboardPage.goToCart();

		objectCartPage.listComparision(dashItems, dashItemsList);

		PaymentPage objectPaymentPage = objectCartPage.clickCheckOutButtton();

		objectPaymentPage.paymentValues("123", "c");

		objectPaymentPage.clickOnPlaceOrder();

	}

	@Test(retryAnalyzer = Retry.class)
	public void test2() {
		//hello there

		objectLandingPage.login("sainikil96@gmail.com", "wrongpassword");

		String toast = objectLandingPage.getToast();

		Assert.assertEquals(toast, "Incorrect email or password.");

	}

//	@DataProvider(name = "myDataProvider")
//	public Object[][] myDataProvider() {
//	    return new Object[][]{
//	        {"sainikil96@gmail.com", "*r#NHQH59V6WL@Q"},
//
//	        // Additional sets of parameters can be added here if needed
//	    };
//	}	

	@DataProvider(name = "myDataProvider")
	public Object[][] myDataProvider() throws IOException {

//		HashMap<String, String> dataMap = new HashMap<>();
//		
//		dataMap.put("email", "sainikil96@gmail.com");
//		dataMap.put("password","*r#NHQH59V6WL@Q");

		List<HashMap<String, String>> resultMap = jsontoHash(
				System.getProperty("user.dir") + "//src//test//java//MyAtomation//SeleniumAutomation//data//data.json");

		return new Object[][] { { resultMap.get(0) }, { resultMap.get(1) } };

		// Additional sets of parameters can be added here if needed
	}
}

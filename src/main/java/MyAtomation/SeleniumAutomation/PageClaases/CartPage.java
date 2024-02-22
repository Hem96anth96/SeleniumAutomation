package MyAtomation.SeleniumAutomation.PageClaases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import MyAtomation.SeleniumAutomation.UtilityClass.TheUtilityClass;

public class CartPage extends TheUtilityClass {

	WebDriver driver;

	public CartPage(WebDriver driver) {

		super(driver);

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[contains(text(),'Checkout')]")
	WebElement checkOut;

	public void listComparision(String dashItems, List<String> dashItemsList) {
		// TODO Auto-generated method stub

		List<String> itemNamesCart = driver.findElements(By.xpath("//*[@class='cartSection']/h3")).stream()
				.map(i -> i.getText()).toList();

		System.out.println(dashItems);
		System.out.println(itemNamesCart.toString() + "cart");
		Assert.assertEquals(dashItemsList.equals(itemNamesCart), true);

	}

	public PaymentPage clickCheckOutButtton() {
		actionClick(checkOut);
		return new PaymentPage(driver);
	}

	public List<String> returnItemsAddedFromDash() {

		List<String> itemNamesDashboard = driver
				.findElements(By.xpath("//*[@class='row']/div/div[@class='card']/div/h5")).stream()
				.map(i -> i.getText()).toList();

		return itemNamesDashboard;
	}

}

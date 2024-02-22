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

public class PaymentPage extends TheUtilityClass {

	WebDriver driver;

	public PaymentPage(WebDriver driver) {

		super(driver);

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[contains(text(),'CVV Code')]/following-sibling::input")
	WebElement cvvCode;

	@FindBy(xpath = "//*[@class='form-group']/*[@class='input txt text-validated']")
	WebElement country;

	@FindBy(xpath = "//*[contains(text(),'Place Order ')]")
	WebElement placeOrder;

	@FindBy(xpath = "//*[contains(text(),' Thankyou for the order')]")
	WebElement orderConfirmationText;

	@FindBy(xpath = "//*[@class='ta-results list-group ng-star-inserted']/button")
	List<WebElement> countryElements;

	public void paymentValues(String cvvtext, String countrytext) {

		cvvCode.sendKeys(cvvtext);
		country.sendKeys(countrytext);

		for (int i = 0; i < countryElements.size(); i++) {

			if (countryElements.get(i).getText().contentEquals("Canada")) {

				countryElements.get(i).click();
				break;
			}
		}

	}

	public void clickOnPlaceOrder() {

		actionClick(placeOrder);

		Assert.assertEquals((orderConfirmationText).getText(), "THANKYOU FOR THE ORDER.");
		System.out.println(orderConfirmationText.getText());

	}

}

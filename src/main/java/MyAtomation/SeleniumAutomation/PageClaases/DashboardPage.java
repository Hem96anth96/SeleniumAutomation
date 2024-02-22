package MyAtomation.SeleniumAutomation.PageClaases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import MyAtomation.SeleniumAutomation.UtilityClass.TheUtilityClass;

public class DashboardPage extends TheUtilityClass {

	WebDriver driver;

	public DashboardPage(WebDriver driver) {

		super(driver);

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@id='toast-container']")
	WebElement toast;

	@FindBy(xpath = "//*[@class='row']/div/div[@class='card']/div/h5")
	WebElement productText;

	@FindBy(xpath = "//*[@routerlink='/dashboard/cart']")
	WebElement cartButton;

	public void itemsToCart() throws InterruptedException {

		waitForElementInVis(toast);

		// driver.findElements(By.xpath("//*[@class='row']/div/div[@class='card']/div/h5")).stream()
		// .filter(i->i.getText().equalsIgnoreCase("ZARA COAT
		// 3")).forEach(i->i.findElement(By.xpath("following-sibling::button[2]")).click());

		List<String> itemNamesDashboard = driver
				.findElements(By.xpath("//*[@class='row']/div/div[@class='card']/div/h5")).stream()
				.map(i -> i.getText()).toList();

		List<WebElement> items = driver.findElements(By.xpath("//*[@class='row']/div/div[@class='card']/div/h5"))
				.stream().map(i -> i.findElement(By.xpath("following-sibling::button[2]"))).toList();

		for (int i = 0; i < items.size(); i++) {

			items.get(i).click();

			waitForElementVis(toast);

			// wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='ng-animating']"))));

			Thread.sleep(3000);

		}

	}

	public List<String> returnItemsAddedFromDash() {

		List<String> itemNamesDashboard = driver
				.findElements(By.xpath("//*[@class='row']/div/div[@class='card']/div/h5")).stream()
				.map(i -> i.getText()).toList();

		return itemNamesDashboard;
	}

	public CartPage goToCart() {

		cartButton.click();

		return new CartPage(driver);

	}

}

package MyAtomation.SeleniumAutomation.Tests;

import java.time.Duration;
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

public class EndtoEnd {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

		driver.get("https://rahulshettyacademy.com/client");

		driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("sainikil96@gmail.com");
		driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys("*r#NHQH59V6WL@Q");

		driver.findElement(By.xpath("//input[@id='login']")).click();

		// driver.findElements(By.xpath("//*[@class='row']/div/div[@class='card']/div/h5")).stream()
		// .filter(i->i.getText().equalsIgnoreCase("ZARA COAT
		// 3")).forEach(i->i.findElement(By.xpath("following-sibling::button[2]")).click());

		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@id='toast-container']"))));

		List<String> itemNamesDashboard = driver
				.findElements(By.xpath("//*[@class='row']/div/div[@class='card']/div/h5")).stream()
				.map(i -> i.getText()).toList();

		List<WebElement> items = driver.findElements(By.xpath("//*[@class='row']/div/div[@class='card']/div/h5"))
				.stream().map(i -> i.findElement(By.xpath("following-sibling::button[2]"))).toList();

		for (int i = 0; i < items.size(); i++) {

			items.get(i).click();

			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@id='toast-container']"))));

			System.out.println(driver.findElement(By.xpath("//div[@id='toast-container']")).getText());
			Thread.sleep(3000);

		}

		// wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='ng-animating']"))));
		// Thread.sleep(3000);

		driver.findElement(By.xpath("//*[@routerlink='/dashboard/cart']")).click();

		List<String> itemNamesCart = driver.findElements(By.xpath("//*[@class='cartSection']/h3")).stream()
				.map(i -> i.getText()).toList();

		System.out.println(itemNamesDashboard.toString() + "dash");
		System.out.println(itemNamesCart.toString() + "cart");
		Assert.assertEquals(itemNamesDashboard.equals(itemNamesCart), true);

		Actions actionObject = new Actions(driver);

		actionObject.moveToElement(driver.findElement(By.xpath("//button[contains(text(),'Checkout')]"))).click()
				.build().perform();

		Thread.sleep(4000);

		driver.findElement(By.xpath("//*[contains(text(),'CVV Code')]/following-sibling::input")).sendKeys("123");

		driver.findElement(By.xpath("//*[@class='form-group']/*[@class='input txt text-validated']")).sendKeys("c");

		List<WebElement> countryElements = driver
				.findElements(By.xpath("//*[@class='ta-results list-group ng-star-inserted']/button")).stream()
				.toList();

		for (int i = 0; i < countryElements.size(); i++) {

			if (countryElements.get(i).getText().contentEquals("Canada")) {

				countryElements.get(i).click();
				break;
			}
		}

		// countryElements.stream().forEach(i->actionObject.moveToElement(i).click().build().perform());

		actionObject.moveToElement(driver.findElement(By.xpath("//*[contains(text(),'Place Order ')]"))).click().build()
				.perform();

		Assert.assertEquals(driver.findElement(By.xpath("//*[contains(text(),' Thankyou for the order')]")).getText(),
				"THANKYOU FOR THE ORDER.");
		System.out.println(driver.findElement(By.xpath("//*[contains(text(),' Thankyou for the order')]")).getText());

		Thread.sleep(4000);

		driver.quit();

	}

}

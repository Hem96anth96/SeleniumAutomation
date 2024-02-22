package MyAtomation.SeleniumAutomation.UtilityClass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TheUtilityClass {

	WebDriver driver;

	public TheUtilityClass(WebDriver driver) {
		this.driver = driver;
	}

	public void waitForElementVis(WebElement toast) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

		wait.until(ExpectedConditions.visibilityOf(toast));

		System.out.println(toast.getText());
	}

	public void waitForElementInVis(WebElement toast) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

		wait.until(ExpectedConditions.invisibilityOf(toast));

	}

	public void actionClick(WebElement element) {

		Actions actionObject = new Actions(driver);

		actionObject.moveToElement(element).click().build().perform();
	}

}

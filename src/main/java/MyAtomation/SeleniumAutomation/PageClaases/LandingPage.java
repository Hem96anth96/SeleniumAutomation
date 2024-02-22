package MyAtomation.SeleniumAutomation.PageClaases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import MyAtomation.SeleniumAutomation.UtilityClass.TheUtilityClass;

public class LandingPage extends TheUtilityClass  {

	public WebDriver driver;

	public LandingPage(WebDriver driver) {
		
		super(driver);

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='userEmail']")
	WebElement userEmail;

	@FindBy(xpath = "//input[@id='userPassword']")
	WebElement userPassword;

	// @FindBy(xpath = "//input[@id='login']")
	// WebElement loginButton;
	
	@FindBy(xpath = "//*[@class='toast-bottom-right toast-container']")
	WebElement toast;

	public DashboardPage login(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		driver.findElement(By.xpath("//input[@id='login']")).click();

		return new DashboardPage(driver);

	}

	public void getPage(String url) {
		driver.get(url);

	}
	
	public String getToast() {
		
		waitForElementVis(toast);
		
		return toast.getText();
		
		
	}

}

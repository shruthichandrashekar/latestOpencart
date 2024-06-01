package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HomePage extends BasePage{
	public  WebDriver driver;
	public HomePage(WebDriver driver) {
		super(driver);
	}

	//locators
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	@FindBy(xpath="//a[normalize-space()='Log in']")
	WebElement lnkLogin;


	public AccountRegistration clickRegister() {
		lnkRegister.click();
		return new AccountRegistration(driver);
	}
	
	public void clickLogin() {
		lnkLogin.click();
	}
}


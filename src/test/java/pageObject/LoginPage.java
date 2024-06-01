package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	public static WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	//locators
	
	@FindBy(xpath="//input[@name='Email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@name='Password']")
	WebElement  txtPassword;
	
	@FindBy(xpath="//button[normalize-space()='Log in']")
	WebElement btnLogin;
	
	@FindBy(xpath="//div[@class='topic-block-title']//h2[text()='Welcome to our store']")
	WebElement msg; 
	
	@FindBy(xpath="//a[contains(text(), 'Log out')]")
	WebElement lnkLogOut;
	
	//methods
	public void setEmali(String email) {
		txtEmail.sendKeys(email);	
	}
	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}
	public void login() {
		btnLogin.click();
	}
	
	public boolean isMyAccountPageExists() {
		try 
		{
			return msg.isDisplayed();
		}catch(Exception e) {
			return (false);
		}
	} 
	
	public void clickLogOut() {
		lnkLogOut.click();
	}

}

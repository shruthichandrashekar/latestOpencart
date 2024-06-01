package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistration extends BasePage{
	
	public static WebDriver driver;
	
	public AccountRegistration(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='gender-female']")
	WebElement radioFemale; 
	
	@FindBy(xpath="//input[@id='FirstName']")
	WebElement txtFirstName;
	
	@FindBy(xpath="//input[@id='LastName']")
	WebElement txtLastName;
	
	@FindBy(xpath="//input[@id='Email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='Password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='ConfirmPassword']")
	WebElement txtConfirmPassword;
	
	@FindBy(xpath="//button[@id='register-button']")
	WebElement btnRegister;
	
	@FindBy(xpath="//div[@class='result']")
	WebElement msgConfirmation;
	
	@FindBy(xpath="//a[normalize-space()='Continue']")
	WebElement lnkContinue;
	
	
	
	public void selectGender() {
		radioFemale.click();
	}
	public void setFirstName(String fname) {
		txtFirstName.sendKeys(fname);
	}
	public void setLastName(String lname) {
		txtLastName.sendKeys(lname);
	}
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}
	public void setConfirmPassword(String cnpwd) {
		txtConfirmPassword.sendKeys(cnpwd);
	}
	public void clickRegister() {
		btnRegister.click();
	}
	public void clickContinue() {
		lnkContinue.click();
	}
	
	public String getConfirmationMsg() {
		try 
		{
			return msgConfirmation.getText();
		}catch(Exception e){
			return e.getMessage();
		}
	}
	
}

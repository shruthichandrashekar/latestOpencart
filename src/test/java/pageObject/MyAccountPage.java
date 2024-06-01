package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {
	
	public static WebDriver driver;
	
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
//	@FindBy(xpath="//div[@class='block block-account-navigation']//strong[contains(text(), 'My account')]")
//	WebElement txtMyAccount;
	
//	@FindBy(xpath="//a[contains(text(), 'Log out')]")
//	WebElement lnkLogOut;
	
//	public void clickLogOut() {
//		lnkLogOut.click();
//	}
	
//	public boolean isMyAccountPageExists() {
//		try 
//		{
//			return txtMyAccount.isDisplayed();
//		}catch(Exception e) {
//			return (false);
//		}
//	} 
}
	
	

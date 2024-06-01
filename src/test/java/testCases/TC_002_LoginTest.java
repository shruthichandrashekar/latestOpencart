package testCases;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass {
	
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@BeforeClass(groups= {"sanity","regression","master"})
	@Parameters({ "browser"})
	public void setUp(String br) throws IOException {
		
		//loading properties file
		 FileReader file=new FileReader(".//src//test//resources//config.properties");
		 p=new Properties();
		 p.load(file);
		
		
		//load logger file
		logger = LogManager.getLogger(getClass());
		
		//For cross broswer testing to load browser based on condition
				if(br.equalsIgnoreCase("chrome")) {
					driver = new ChromeDriver();
				}else if(br.equalsIgnoreCase("Edge")) {
					driver = new EdgeDriver();
				}else {
					System.out.println("Browser not matching");
					return;
				}
				
				
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				
				driver.get(p.getProperty("appURL"));
				driver.manage().window().maximize();
	}
	
	@AfterClass(groups= {"sanity","regression","master"})
	public void tearDown() {
		//driver.close();
	}
	
	@Test(groups= {"sanity","master"})
	public void verify_login() {
		
		logger.info("*** Starting Login  *** ");
	    logger.debug("Capturing application level logs");
	    
	    try
	    {
	    //home page
	    HomePage hp = new HomePage(driver);
	    hp.clickLogin();
	    logger.info("*** Clicked login from home page ***");
	    
	    //Login page
	    LoginPage lp = new LoginPage(driver);
	    lp.setEmali(p.getProperty("email"));
	    lp.setPassword(p.getProperty("password"));
	    
	    logger.info("Providing login details");
	    lp.login();
	    
	    //My account page
	    //MyAccountPage accPage = new MyAccountPage(driver);
	    
	   boolean targetedPage = lp.isMyAccountPageExists();
	   
	   
	   if(targetedPage==true) 
	   {
		   logger.info("...login test passed....");
		   Assert.assertTrue(true);
		   
	   }else 
	   {
		   logger.error("... Login test failed ....");
		   Assert.fail();
	   }
	   
	   //lp.clickLogOut();
	 }catch(Exception e)
	    {
		 Assert.fail();
	    }
	    
		logger.info("... TC_002_Login test finished ...");
	}

}

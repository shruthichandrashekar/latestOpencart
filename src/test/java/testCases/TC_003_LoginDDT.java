package testCases;

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
import utilities.DataProviders;

public class TC_003_LoginDDT {
	
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@BeforeClass
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
	
	@AfterClass
	public void tearDown() {
		//driver.close();
	}

	/*Data is valid  - login success - test pass  - logout
	  Data is valid -- login failed - test fail

	  Data is invalid - login success - test fail  - logout
	  Data is invalid -- login failed - test pass
	*/
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class)
	public void Verify_Login_DDT(String username, String password, String res) {
		
		try {
			logger.info("*** Starting LoginDDT test ***");
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
	    boolean targetedPage = lp.isMyAccountPageExists();
	    
	    if(res.equalsIgnoreCase("Valid")) {
	    	
	    	if(targetedPage==true) {
	    		lp.clickLogOut();
	    		Assert.assertTrue(true);
	    	}else {
	    		Assert.assertTrue(false);
	    	}
	    }
	    if(res.equalsIgnoreCase("Invalid")) {
	    	if(targetedPage==true) {
	    		System.out.println("inside if for invaild");
	    		lp.clickLogOut();
	    		Assert.assertTrue(false);
	    	}else {
	    		Assert.assertTrue(true);
	    	}
	    	
	    }
		}catch(Exception e) {
			Assert.fail("An exception occurred: " + e.getMessage());
		}
		logger.info("*** Finished LoginDDT test ***");
	}

}

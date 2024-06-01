package testCases;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObject.AccountRegistration;
import pageObject.HomePage;
import testBase.BaseClass;

public class TC_001_AccontRegistrationTest extends BaseClass {
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
		
//		switch(br.toLowerCase()) {
//		case "chrome": driver = new ChromeDriver(); break;
//		case "edge": driver = new EdgeDriver(); break;
//		default: System.out.println("Browser not matching");
//				return;
//		}
		
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
	
	@Test(groups= {"regression","master"})
	public void Verify_Accout_Registration() {
		
		logger.info("*** Starting Account Registration *** ");
		
		try
		{
		HomePage hp = new HomePage(driver);
		hp.clickRegister();
		logger.info("*** Clicking Register Link *** ");
		
		logger.info("*** Providing customer info for registration *** ");
		
		AccountRegistration accReg = new AccountRegistration(driver);
		accReg.selectGender();
		accReg.setFirstName(randomString().toUpperCase());
		accReg.setLastName(randomString().toUpperCase());
		accReg.setEmail(randomAlphaNumber()+"gmail.com");
		
		String password=randomString();
		
		accReg.setPassword(password);
		accReg.setConfirmPassword(password);
		accReg.clickRegister();
		logger.info("*** Click Register button to register *** ");
		
//		accReg.setFirstName("Shruthi");
//		accReg.setLastName("C");
//		accReg.setEmail("shruthi.c@gmail.com");
//		accReg.setPassword("Flower");
//		accReg.setConfirmPassword("Flower");
		
		
		String msg = accReg.getConfirmationMsg();
		Assert.assertEquals(msg, "Your registration completed");
		logger.info("*** Validate the confirmation msg *** ");
		
		accReg.clickContinue();
		logger.info("*** Click Continue *** ");
		}
		catch(Exception e)
		{
			logger.error("Test failed");
			Assert.fail();
		}

		logger.info("*** Ending of the Account Registration *** ");
	}

	public String randomString() {
		String generatedRandomString = RandomStringUtils.randomAlphabetic(8);
		return generatedRandomString;
	}
	public String randomAlphaNumber() {
		String str = RandomStringUtils.randomNumeric(3);
		String num = RandomStringUtils.randomAlphabetic(5);
		
		return (str+"@"+num);
	}
}

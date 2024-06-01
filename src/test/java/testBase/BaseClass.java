package testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class BaseClass {
	
	public Logger logger;
	
	public static WebDriver driver;
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}
//	
//	@BeforeClass
//	public void setUp() {
//		WebDriverManager.chromedriver().setup();
//		driver = new ChromeDriver();
//		driver.manage().deleteAllCookies();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		
//		driver.get("https://demo.nopcommerce.com/");
//		driver.manage().window().maximize();
//	}
//	
//	@AfterClass
//	public void tearDown() {
//		driver.close();
//	}
	
//	public String randomString() {
//		String generatedRandomString = RandomStringUtils.randomAlphabetic(5);
//		return generatedRandomString;
//	}
//	public String randomAlphaNumber() {
//		String str = RandomStringUtils.randomNumeric(3);
//		String num = RandomStringUtils.randomAlphabetic(5);
//		
//		return (str+"@"+num);
//	}
	


}

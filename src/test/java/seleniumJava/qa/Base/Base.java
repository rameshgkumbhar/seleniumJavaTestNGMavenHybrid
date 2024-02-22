package seleniumJava.qa.Base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import seleniumJava.qa.utils.Utilities;

public class Base extends Utilities{
	
	WebDriver driver;
	public Properties prop;
	public Properties testDataProp;
	
	//Constructor of base class, so that properties file can be accessed directly in other class
	public Base() {
		
		// code block to load config.properties file
		testDataProp = new Properties();
		File testDataPropFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\seleniumJava\\qa\\testData\\testData.properties");
		
		try {
			FileInputStream testDataFis = new FileInputStream(testDataPropFile);
			testDataProp.load(testDataFis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		
		// code block to load config.properties file
		prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\seleniumJava\\qa\\config\\config.properties");
		
		try {
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);
		}catch(Throwable e) {
		
			e.printStackTrace();
		}
		
	}
	// Method to initialize browser and open application and return driver 
	public WebDriver initializeBrowserAndOpenApplication(String browserName) throws InterruptedException {
		
		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		
		driver.get(prop.getProperty("application_url"));
		Thread.sleep(5000);
		
		return driver;
		
	}

}

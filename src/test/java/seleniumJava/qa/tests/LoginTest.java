package seleniumJava.qa.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import seleniumJava.qa.Base.Base;
import seleniumJava.qa.pages.LoginPage;
import seleniumJava.qa.utils.Utilities;

public class LoginTest extends Base {
	
	// constructor - used to call property file and other methods directly from base class
	public LoginTest() {
		super();
	}
	
	public WebDriver driver;
	
	@BeforeMethod
	public void setup() throws InterruptedException {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
	}
	
	@AfterMethod
	public void tearDown( ) {
		driver.quit();
	}
	
	@Test(priority=1,dataProvider="validCredentialsSupplier")
	public void verifyLoginWithValidCredentials(String username, String password) throws InterruptedException {
		
		LoginPage loginPage = new LoginPage(driver);
		System.out.println("Page Title: "+ driver.getTitle());
		System.out.println("Page URL: " + driver.getCurrentUrl());
		loginPage.login(username, password);
		Thread.sleep(2000);
		String productsHeading = loginPage.getHomePageHeading();
		System.out.println("Home Page Heading: " + productsHeading);
		Assert.assertEquals(productsHeading, testDataProp.getProperty("expectedHomePageHeading"), testDataProp.getProperty("HomePageHeadingError"));
		System.out.println("=========================================================================="  );
		System.out.println();
				
	}
	
	@DataProvider(name="validCredentialsSupplier")
	public Object [][] supplyTestData() {
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	}
	
	@Test(priority=2)
	public void verifyLoginWithInvalidCredentials() throws InterruptedException {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(testDataProp.getProperty("invalid_userName"), testDataProp.getProperty("invalid_password"));
		Thread.sleep(2000);
		String actualError = loginPage.getInvalidLoginError();
		System.out.println("Invalid login error: "+ actualError);
		Assert.assertEquals(actualError, testDataProp.getProperty("expectedInvalidLoginError"), testDataProp.getProperty("invalidLoginMissmatchError"));
		System.out.println();
		System.out.println("=========================================================================="  );
	}
	
	@Test(priority=3,dependsOnMethods={"verifyLoginWithInvalidCredentials"})
	public void verifyLoginWithoutCredentials() throws InterruptedException {
		LoginPage loginPage = new LoginPage(driver);
		System.out.println("Click Login button without entering credentials");
		loginPage.clickLoginButton();
		Thread.sleep(2000);
		String actualError = loginPage.getLoginWithoutCredentialError();
		System.out.println("Login without credentials error: "+ actualError);
		Assert.assertEquals(actualError, testDataProp.getProperty("expectedBlankLoginError"), testDataProp.getProperty("BlankLoginMissmatchError"));
		System.out.println();
		System.out.println("=========================================================================="  );
	}

}

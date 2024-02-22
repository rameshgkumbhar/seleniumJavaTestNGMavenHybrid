package seleniumJava.qa.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import seleniumJava.qa.Base.Base;
import seleniumJava.qa.pages.InventoryPage;
import seleniumJava.qa.pages.LoginPage;

public class InventoryTest extends Base{
	
	public WebDriver driver;
	
	// constructor - used to call property file and other methods directly from base class
	public InventoryTest() {
	super();
	}
	
	@BeforeMethod
	public void setup() throws InterruptedException {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
	}
	
	@AfterMethod
	public void tearDown( ) {
		driver.quit();
	}
	
	@Test
	public void verifyInventoryDetails() throws InterruptedException {
		LoginPage loginPage = new LoginPage(driver);
		InventoryPage inventoryPage = new InventoryPage(driver);
		loginPage.login(prop.getProperty("valid_userName"), prop.getProperty("valid_password"));
		Thread.sleep(2000);
		String productName = inventoryPage.getProductName();
		System.out.println("Product Name: " + productName);
		Boolean isAddToCartButtonDisplayed = inventoryPage.isAddToCartButtonDisplayed();
		System.out.println("IS Add to Cart button displayed: " + isAddToCartButtonDisplayed);
		assert(isAddToCartButtonDisplayed);
		String priceOnInventory = inventoryPage.getProductPrice();
		System.out.println("Product price on inventory: " + priceOnInventory);
		System.out.println("=========================================================================="  );
		System.out.println();
	}

}

package seleniumJava.qa.tests;

import static org.testng.Assert.assertNotEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import seleniumJava.qa.Base.Base;
import seleniumJava.qa.pages.InventoryPage;
import seleniumJava.qa.pages.LoginPage;
import seleniumJava.qa.pages.ProductDetailsPage;

public class ProductDetailsTest extends Base{
	
	public WebDriver driver;
	
	// constructor - used to call property file and other methods directly from base class
	public ProductDetailsTest() {
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
	public void verifyProductDetails() throws InterruptedException {
		LoginPage loginPage = new LoginPage(driver);
		InventoryPage inventoryPage = new InventoryPage(driver);
		ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);
		System.out.println("Page Title: "+ driver.getTitle());
		System.out.println("Page URL: " + driver.getCurrentUrl());
		System.out.println("Enter valid user-name");
		loginPage.login(prop.getProperty("valid_userName"), prop.getProperty("valid_password"));
		Thread.sleep(2000);
		String nameOnInventory = inventoryPage.getProductName();
		System.out.println("Product name on inventory: " + nameOnInventory);
		String priceOnInventory = inventoryPage.getProductPrice();
		System.out.println("Product price on inventory: " + priceOnInventory);
		inventoryPage.clickProductName();
		Thread.sleep(2000);
		String productNameOnProductDetails = productDetailsPage.getProductName();
		System.out.println("Product name on Product Details : " + productNameOnProductDetails);
		Assert.assertEquals(nameOnInventory, productNameOnProductDetails, testDataProp.getProperty("productNameMissmatchError"));
		String priceOnProductDetails = productDetailsPage.getProductPrice();
		System.out.println("Product price on inventory: " + priceOnProductDetails);
		Assert.assertEquals(priceOnInventory, priceOnProductDetails, testDataProp.getProperty("priceMissmatchError"));
		String addToCartText =productDetailsPage.getAddToCartButtonText();
		System.out.println("Add To Cart Button Text: " + addToCartText);
		productDetailsPage.clickAddToCartButton();
		Thread.sleep(2000);
		String addToCartButtonTextAfterClick = productDetailsPage.getRemoveButtonText();
		System.out.println("Add To Cart Button Text after click: " + addToCartButtonTextAfterClick);
		assertNotEquals(addToCartText, addToCartButtonTextAfterClick, testDataProp.getProperty("addToCartButtonTextError"));
		System.out.println("=========================================================================="  );
		System.out.println();
		
	}

}

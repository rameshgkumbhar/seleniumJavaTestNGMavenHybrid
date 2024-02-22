package seleniumJava.qa.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import seleniumJava.qa.Base.Base;
import seleniumJava.qa.pages.InventoryPage;
import seleniumJava.qa.pages.LoginPage;
import seleniumJava.qa.pages.ProductDetailsPage;
import seleniumJava.qa.pages.ShoppingCartPage;

public class ShoppingCartTest extends Base{
	
	public WebDriver driver;
	
	public ShoppingCartTest() {
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
	public void verifyCartDetails() throws InterruptedException {
		LoginPage loginPage = new LoginPage(driver);
		InventoryPage inventoryPage = new InventoryPage(driver);
		ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);
		ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
		loginPage.login(prop.getProperty("valid_userName"), prop.getProperty("valid_password"));
		Thread.sleep(2000);
		
		inventoryPage.clickProductName();
		
		String productNameOnProductDetails = productDetailsPage.getProductName();
		System.out.println("Product name on Product Details : " + productNameOnProductDetails);
		String priceOnProductDetails = productDetailsPage.getProductPrice();
		System.out.println("Product price on Product Details: " + priceOnProductDetails);
		productDetailsPage.clickAddToCartButton();
		productDetailsPage.clickShoppingCartLink();
		
		String productNameOnCart = shoppingCartPage.getProductName();
		System.out.println("Product Name on Cart: " + productNameOnCart);
		Assert.assertEquals(productNameOnProductDetails, productNameOnCart, testDataProp.getProperty("productNameMissmatchError"));
		String ProductPriceOnCart = shoppingCartPage.getProductPrice();
		System.out.println("Product Price on Cart: " + ProductPriceOnCart);
		Assert.assertEquals(priceOnProductDetails, ProductPriceOnCart, testDataProp.getProperty("priceMissmatchError"));
		System.out.println("=========================================================================="  );
		System.out.println();
	}

}

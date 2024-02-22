package seleniumJava.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage {
	WebDriver driver;
	
	@FindBy(css = "#item_4_title_link>div")
	private WebElement productName;
	
	@FindBy(id = "add-to-cart-sauce-labs-backpack")
	private WebElement addToCartButton;
	
	@FindBy(xpath = "//*[@id='inventory_container']/div/div[1]/div[2]/div[2]/div")
	private WebElement productPrice;
	
	// constructor to initialize webElement
	public InventoryPage(WebDriver driver) {
			
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getProductName() {
		return productName.getText();
	}
	
	public Boolean isAddToCartButtonDisplayed() {
		return addToCartButton.isDisplayed();
	}
	
	public String getProductPrice() {
		return productPrice.getText();
	}
	
	public void clickProductName() {
		productName.click();
	}
	

}

package seleniumJava.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage {
	
	WebDriver driver;
	
	@FindBy(css = "div.inventory_details_name.large_size")
	private WebElement productName;
	
	@FindBy(xpath = "//*[@id='inventory_item_container']/div/div/div[2]/div[3]")
	private WebElement productPrice;
	
	@FindBy(xpath = "//div[@class='inventory_details_desc_container']/button")
	private WebElement addToCartButton;
	
	@FindBy(xpath = "//button[@data-test = 'remove-sauce-labs-backpack']")
	private WebElement removeButton;
	
	@FindBy(xpath = "//a[@class='shopping_cart_link']")
	private WebElement shoppingCartLink;
	
	
	// constructor to initialize webElement
	public ProductDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getProductName() {
		return productName.getText();
	}
	
	public String getProductPrice() {
		return productPrice.getText();
	}
	
	public String getAddToCartButtonText() {
		return addToCartButton.getText();
	}
	
	public void clickAddToCartButton() {
		addToCartButton.click();
	}
	
	public String getRemoveButtonText() {
		return removeButton.getText();
	}
	
	public void clickShoppingCartLink() {
		shoppingCartLink.click();
	}

}

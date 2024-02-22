package seleniumJava.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartPage {
	
	WebDriver driver;
	
	@FindBy(xpath = "//*[@id='item_4_title_link']/div")
	private WebElement productName;
	
	@FindBy(xpath = "//*[@id='cart_contents_container']/div/div[1]/div[3]/div[2]/div[2]/div")
	private WebElement productPrice;
	
	
	// constructor to initialize webElement
	public ShoppingCartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getProductName() {
		return productName.getText();
	}
	
	public String getProductPrice() {
		return productPrice.getText();
	}
	

}

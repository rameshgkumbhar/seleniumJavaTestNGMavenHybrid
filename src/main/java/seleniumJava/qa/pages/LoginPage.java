package seleniumJava.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	
	@FindBy(id = "user-name")
	private WebElement userNameInputBox;
	
	@FindBy(id = "password")
	private WebElement passwordInputBox;
	
	@FindBy(id = "login-button")
	private WebElement loginButton;
	
	@FindBy(xpath = "//h3[@data-test = 'error']")
	private WebElement invalidLoginError;
	
	@FindBy(xpath = "//h3[@data-test = 'error']")
	private WebElement loginWithoutCredentialError;
	
	@FindBy(xpath = "//*[@id= 'header_container']/div[2]/span")
	private WebElement homePageHeading;
	
	// constructor to initialize webElement
	public LoginPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterUserName(String userName) {
		userNameInputBox.sendKeys(userName);
	}
	
	public void enterPassword(String password) {
		passwordInputBox.sendKeys(password);
	}
	
	public void login(String userName, String password) {
		System.out.println("Enter valid user name");
		this.enterUserName(userName);
		System.out.println("Enter valid password");
		this.enterPassword(password);
		this.clickLoginButton();
	}
	
	public void clickLoginButton() {
		loginButton.click();
	}
	
	public String getInvalidLoginError() {
		return invalidLoginError.getText();
	}
	
	public String getLoginWithoutCredentialError() {
		return loginWithoutCredentialError.getText();
	}
	
	public String getHomePageHeading() {
		return homePageHeading.getText();
	}
	
	

}

package pageobjects;

//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	//WebDriver driver;
	
	@FindBy(xpath="//span[normalize-space()='My Account']")
	 WebElement myAccount;
	
	@FindBy(css="a[href=\"https://demo.opencart.com/en-gb?route=account/register\"]")
	 WebElement registerBtn;
	
	@FindBy(xpath="//li/a[text()='Login']")
	WebElement LoginBtn;
	
	@FindBy(xpath="//input[@name='search']")
	WebElement searchBox;
	
	@FindBy(xpath="//div[@id='search']/button")
	WebElement searchBtn;
	
	//By Login= By.xpath ("//li/a[text()='Login']");
	
	
	
		public HomePage(WebDriver driver){
			
			super(driver);
		}
		
	
	public void clickMyAccount() {
		
		myAccount.click();
	}
	
	public void clickRegisterBtn() {
		
		registerBtn.click();
	}
	
	public void clickOnLoginBtn() {
		
		LoginBtn.click();
	}
	
	public void SetValueinSearchBox(String item) {
		
		searchBox.sendKeys(item);
	}
	
	public void clickOnSearchBtn() {
		searchBtn.click();
	}
		
}

package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

//Inheritence we used in our framework by extending BasePage class

public class LoginPage extends BasePage{

	@FindBy(css="input#input-email")
	WebElement EmailInput;
	
	@FindBy(name="password")
	WebElement PasswordInput;
	
	@FindBy(css="button[type='submit']")
	WebElement LoginBtn;
	
	public LoginPage(WebDriver driver){
		
		super(driver);
	}
	
	
	public void EnterEmailID(String email) {
		
		EmailInput.sendKeys(email);
	}
	
	public void EnterPassword(String password) {
		
		PasswordInput.sendKeys(password);
	}
	
	public void clickOnLoginBtn() {
		
		LoginBtn.click();
	}
}

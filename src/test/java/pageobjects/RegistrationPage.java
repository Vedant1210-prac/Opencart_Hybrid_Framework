package pageobjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage extends BasePage {
	//WebDriver driver;
	
	WebDriverWait wait;
	
	@FindBy(xpath="//input[@name='firstname']")
	 WebElement firstName;
	
	@FindBy(xpath="//input[contains(@id, 'lastname')]")
	 WebElement lastName;
	
	@FindBy(css="input[name=email]")
	 WebElement Email;
	
	@FindBy(name="password")
	 WebElement password;
	
	@FindBy(xpath="//div/input[@type='checkbox' and @name='agree']")
	 WebElement privacy;
	
	@FindBy(xpath="//div/button[@type='submit']")
	 WebElement ContinueBtn;
	
	@FindBy(tagName="h1")
	 WebElement MsgOnRegistration;
	
	public RegistrationPage(WebDriver driver){
		
		super(driver);
	}
	
	
	public void setFirstName(String fname) {
		
		firstName.sendKeys(fname);
	}
	
	public void setLastName(String lname) {
		
		lastName.sendKeys(lname);
	}

	public void setEmail(String email) {
		
		Email.sendKeys(email);
	}
	
	public void setpassword(String pass) {
		
		password.sendKeys(pass);
	}
	
	public void clickprivacyBtn() {
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		js.executeScript("arguments[0].scrollIntoView(true)", privacy);
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		wait.until(ExpectedConditions.elementToBeClickable(privacy)).click();
		
	}
	
	public void clickContinueBtn() {
		
		ContinueBtn.click();
	}
	
	public String Message() {
		try {
			
			return (MsgOnRegistration.getText());
		}catch(Exception e) {
			return (e.getMessage());
		}
		
	}
	
}

package pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{

	@FindBy (xpath="//h2[text()='My Account']")
	WebElement MyAccountHeader;		
	
	@FindBy(xpath="//li/a[text()='Logout']")
	//(xpath="//div[@class='list-group mb-3']//a[text()='Logout']")
	WebElement LogoutBtn;
		
	JavascriptExecutor js = (JavascriptExecutor) driver;
	public MyAccountPage(WebDriver driver) {

		super(driver);
	
	}
	
	public boolean MyAccountisDisplayed() {
		
		try {
			return MyAccountHeader.isDisplayed();
		}catch(Exception e) {
			return false;
		}
	}
	
	public void clickLogout() {
		
		js.executeScript("arguments[0].scrollIntoView(true)", LogoutBtn);
		LogoutBtn.click();
	}
	


}

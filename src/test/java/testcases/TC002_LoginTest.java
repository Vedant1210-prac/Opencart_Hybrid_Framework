package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	
	public HomePage hp;
	public LoginPage lp;
	public MyAccountPage mp;
	
	@Test(groups = "Sanity")
	public void LoginTest() throws InterruptedException {
		try {
		logger.info("****TC002_LoginTestStarted****");
	
		 hp = new HomePage(driver);
		 hp.clickMyAccount();
		 hp.clickOnLoginBtn();
		 
		logger.info("****LoginPage*****");
		 
		 lp= new LoginPage(driver);
		 lp.EnterEmailID(p.getProperty("Email"));
		 lp.EnterPassword(p.getProperty("Password"));
		 lp.clickOnLoginBtn();
		 
		logger.info("Validating We are not correct Page");
		 mp = new MyAccountPage(driver);
		 
		 boolean targetPage = mp.MyAccountisDisplayed();
		 
		 Assert.assertTrue(targetPage);		
		}catch(Exception e) {
			
			logger.error("Test Failed");
			logger.debug("Debug error");
		}
		Thread.sleep(3000);
		
		hp.clickMyAccount();
		mp.clickLogout();
		logger.info("Test completed");
		
	}

}

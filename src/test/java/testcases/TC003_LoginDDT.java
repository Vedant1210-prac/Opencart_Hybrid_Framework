package testcases;

import org.apache.logging.log4j.core.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass{
	
	public HomePage hp;
	public LoginPage lp;
	public MyAccountPage macc;
	

	
	@Test(dataProvider ="LoginData", dataProviderClass = DataProviders.class, groups = "dataprovider")
	public void verify_loginDDT(String email, String pass, String exp) throws InterruptedException {
		
		logger.info("****TC003_LoginDDT started****");
		try {
		//homePage
		hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickOnLoginBtn();
		
		//LoginPage
		lp = new LoginPage(driver);
		lp.EnterEmailID(email);
		lp.EnterPassword(pass);
		lp.clickOnLoginBtn();
		
		//MyAccountPage
		macc = new MyAccountPage(driver);
		boolean targetPage = macc.MyAccountisDisplayed();
		
		
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(targetPage==true){
			
					hp.clickMyAccount();
					macc.clickLogout();
					Assert.assertEquals(targetPage, true);;
				}
				else
				{
					Assert.assertTrue(false);
				}
			}
			
			if(exp.equalsIgnoreCase("Invalid"))
			{
				if(targetPage==true)
				{
					macc.clickLogout();
					Assert.assertTrue(false);
					logger.error("Test failed with invalid data");
				}
				else
				{
					Assert.assertTrue(true);
					logger.info("Test passed user should not be able to login");
				}
			}
		
		}
		catch(Exception e) {
			Assert.fail();
		}
		finally {
			logger.info("***TC003_LoginData finished***");
		}
		
	}
/*logger.info("**** Starting TC_003_LoginDDT *****");
		
		try {
	
		//Home page
			HomePage hp=new HomePage(driver);
			hp.clickMyAccount();
			hp.clickOnLoginBtn(); //Login link under MyAccount
				
			//Login page
			LoginPage lp=new LoginPage(driver);
			lp.EnterEmailID(email);
			lp.EnterPassword(pass);
			lp.clickOnLoginBtn(); //Login button
				
			//My Account Page
			MyAccountPage macc=new MyAccountPage(driver);
			
			Thread.sleep(3000);
			boolean targetPage = macc.MyAccountisDisplayed();
			
			if(exp.equalsIgnoreCase("Valid"))
			{
				if(targetPage==true)
				{
					hp.clickMyAccount();
					macc.clickLogout();
					Assert.assertEquals(targetPage, true);;
				}
				else
				{
					Assert.assertTrue(false);
				}
			}
			
			if(exp.equalsIgnoreCase("Invalid"))
			{
				if(targetPage==true)
				{
					macc.clickLogout();
					Assert.assertTrue(false);
					logger.error("Test failed with invalid data");
				}
				else
				{
					Assert.assertTrue(true);
					logger.info("Test passed user should not be able to login");
				}
			}
		}
		catch(Exception e)
		{
			Assert.fail("An exception occurred: " + e.getMessage());
		}
			
		logger.info("**** Finished TC_003_LoginDDT *****");
	}
*/

}

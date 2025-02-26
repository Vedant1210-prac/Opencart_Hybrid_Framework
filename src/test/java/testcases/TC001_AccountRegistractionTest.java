package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.RegistrationPage;
import testBase.BaseClass;

public class TC001_AccountRegistractionTest extends BaseClass {

	
	/*@Test(priority=1, groups = {"Regression", "Master"})
	public void Navigation_to_RegistrationPage() {
		
		
	}*/
	
	@Test(priority=2, groups={"Regression", "Master"})
	public void AccountRegistration() throws InterruptedException {
		try {
			
			logger.info("*****Starting TC001_AccountRegistractionTest***** ");
			
			HomePage hp = new HomePage(driver);
			
			hp.clickMyAccount();
			hp.clickRegisterBtn();
			
			logger.info("Clicked on Registeration Button");
		RegistrationPage Reg = new RegistrationPage(driver);
		
		Reg.setFirstName(randomString().toUpperCase());
		Reg.setLastName(randomString().toUpperCase());
		Reg.setEmail(randomString()+"@gmail.com");
		Reg.setpassword(randomPassword());
		Reg.clickprivacyBtn();
		Reg.clickContinueBtn();
		
		
		logger.info("Validating Message");
		Thread.sleep(5000);
		String confrmMsg = Reg.Message();
		
		if(confrmMsg.equals("Your Account Has Been Created!")) {
			
			Assert.assertTrue(true);
		}else {
			logger.error("Test Failed");
			logger.debug("Debug logs");
			Assert.assertTrue(false);
		}
		
		//Assert.assertEquals(confrmMsg, "Your Account Has Been Created1!");
		
		}catch(Exception e){
			
			
			Assert.fail();
		}
		
		logger.info("******Finished TC001_AccountRegistractionTest****");
	}
	
	
}

package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.SigninPage;

public class TC002_InValidLoginTest extends BaseClass {
	SigninPage signinPage;
	@Test
	public void verify_login_Error() throws InterruptedException {
		 logger.info("*** Starting TC002_InValidLoginTest ***");
		 try { 
		 signinPage = new SigninPage(driver);

	        signinPage.enterEmailTxt(p.getProperty("invalidusername"));
	        logger.info("Entered username");

	        signinPage.enterPasswordTxt(p.getProperty("invalidpassword"));
	        logger.info("Entered password");
	        
	        signinPage.clickSignInBtn();
	        logger.info("Clicked on Sign-In button");
	        
	        boolean isErrorVisible = signinPage.isErrorMessageVisible();
	        Assert.assertTrue(isErrorVisible, "Error message is not visible for invalid login credentials.");
            logger.info("Error message is displayed as expected for invalid credentials.");
            Thread.sleep(3000);
		 } catch (Exception e) {
	            logger.error("An error occurred during TC002_InValidLoginTest: ", e);
	            Assert.fail("Test failed due to an exception: " + e.getMessage());
	        }
		 Thread.sleep(3000);
		 logger.info("*** Starting TC002_InValidLoginTest ***");
	}
}

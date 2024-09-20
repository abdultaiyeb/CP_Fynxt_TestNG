package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.SigninPage;
import pageObjects.CP.Dashboard;

public class TC001_ValidLoginTest extends BaseClass {
	SigninPage signinPage;
    @Test
    public void verify_login() {
        logger.info("*** Starting TC001_ValidLoginTest ***");
       
        try {
         signinPage = new SigninPage(driver);

        signinPage.enterEmailTxt(p.getProperty("username"));
        logger.info("Entered username");

        signinPage.enterPasswordTxt(p.getProperty("password"));
        logger.info("Entered password");
        
        signinPage.clickSignInBtn();
        logger.info("Clicked on Sign-In button");
      
        Dashboard dashboard = new Dashboard(driver);
        dashboard.isDashboardHeadingCorrect();
        logger.info("Dashboard heading text is correct.");

        } catch (Exception e) {
            logger.error("An error occurred during login test: ", e);
            Assert.fail("Test failed due to an exception.");
        }
        logger.info("*** Finished TC001_ValidLoginTest ***");

    }
    
    
}

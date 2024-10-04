package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.Dashboard;
import pageObjects.SigninPage;
import testBase.BaseClass;

public class TC001_LoginTests extends BaseClass {
    SigninPage signinPage;

    // DataProvider inside the same class
    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][] {
            { p.getProperty("username"), p.getProperty("password"), true },  // Valid login
            { p.getProperty("invalidusername"), p.getProperty("invalidpassword"), false }  // Invalid login
        };
    }

    @Test(dataProvider = "loginData", priority = 1)
    public void verifyLogin(String username, String password, boolean isValid) {
        logger.info("*** Starting Login Test for user: {} ***", username);
        try {
            signinPage = new SigninPage(driver);
            signinPage.enterEmail(username);
            logger.info("Entered username: {}", username);

            signinPage.enterPassword(password);
            logger.info("Entered password");

            signinPage.clickSignIn();
            logger.info("Clicked on Sign-In button");

            if (isValid) {
                Dashboard dashboard = new Dashboard(driver);
                Assert.assertTrue(dashboard.verifyDashboardHeading(), "Dashboard heading is incorrect.");
                logger.info("Dashboard heading text is correct for valid login.");
            } else {
                boolean isErrorVisible = signinPage.isErrorVisible();
                Assert.assertTrue(isErrorVisible, "Error message is not visible for invalid login credentials.");
                logger.info("Error message is displayed for invalid login.");
            }
        } catch (Exception e) {
            logger.error("An error occurred during the login test: ", e);
            Assert.fail("Test failed due to an exception.");
        } finally {
            logger.info("*** Finished Login Test for user: {} ***", username);
        }
    }
}

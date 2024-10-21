package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.ManagedAccounts_Leaderboard;
import pageObjects.Navigation;
import testBase.BaseClass;

public class TC006_LeaderboardStrategyTest extends BaseClass {

    // Constants for test data
    private static final String DESIRED_STRATEGY_NAME = "test_strategyt9poq4sR";  
    private static final String EXPECTED_STRATEGY_NAME = "test_strategyt9poq4sR";  

    @Test
    public void leaderboardList() {
        logger.info("*** Starting TC006_LeaderboardStrategyTest ***");

        try {
           //Call the reusable login method from BaseClass
            login();
            logger.info("Login successful.");

            //Navigate to Managed Accounts > Leaderboard
            Navigation navigation = new Navigation(driver);
            navigation.clickOnManagedAccountsMenu();
            logger.info("Clicked on Managed Accounts menu.");

            navigation.clickOnLeaderboardSubMenu();
            logger.info("Clicked on Leaderboard submenu.");

            //Create an object of ManagedAccounts_Leaderboard and call the action method
            ManagedAccounts_Leaderboard leaderboardPage = new ManagedAccounts_Leaderboard(driver);
            
            // Select the leaderboard and subscribe
            leaderboardPage.selectLeaderboardAndSubscribe(DESIRED_STRATEGY_NAME);
            logger.info("Selected leaderboard and clicked subscribe for: " + DESIRED_STRATEGY_NAME);

            // Verify the strategy name in the new tab
            String actualStrategyName = DESIRED_STRATEGY_NAME;  
            Assert.assertEquals(actualStrategyName, EXPECTED_STRATEGY_NAME, "Strategy name does not match the expected value.");
            logger.info("Strategy name verification passed. Expected: " + EXPECTED_STRATEGY_NAME + ", Found: " + actualStrategyName);

        } catch (Exception e) {
            logger.error("An error occurred during the leaderboard selection or strategy verification process: ", e);
            Assert.fail("Test failed due to an exception.");
        }

        logger.info("*** Finished TC006_LeaderboardStrategyTest ***");
    }
}

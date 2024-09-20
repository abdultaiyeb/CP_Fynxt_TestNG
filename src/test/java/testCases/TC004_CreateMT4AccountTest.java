package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CP.MyAccounts_CreateAccountMT4;
import pageObjects.CP.Navigation;

public class TC004_CreateMT4AccountTest extends BaseClass {

    @Test
    public void createMT4_Account() throws InterruptedException {
        logger.info("*** Starting TC004_CreateMT4AccountTest ***");

        try {
            // Call the reusable login method from BaseClass
            login();
            logger.info("Login successful.");

            Navigation navigation = new Navigation(driver);
            navigation.clickOnMyAccounts();
            logger.info("Clicked on My Accounts.");

            navigation.clickOnCreateAccount();
            logger.info("Clicked on Create Account.");

            MyAccounts_CreateAccountMT4 createAccountPage = new MyAccounts_CreateAccountMT4(driver);

            // Select trading platform
            createAccountPage.selectTradingPlatformOption("MT4");
            logger.info("Selected MT4 trading platform.");

            // Use Faker to generate random account name
            String accountName = faker.letterify("?????");  // 5 random letters
            createAccountPage.setAccountName(accountName);
            logger.info("Entered account name: " + accountName);

            // Select wallet option
            createAccountPage.selectWalletOption("USD");
            logger.info("Selected USD wallet option.");

            // Set account balance
            String amount = "1000";
            createAccountPage.setAmount(amount);
            logger.info("Set account balance to: " + amount);

            // Confirm account creation
            createAccountPage.clickIConfirm();
            logger.info("Clicked 'I Confirm' checkbox.");

            // Click Create Account button
            createAccountPage.clickCreateAccountbtn();
            logger.info("Clicked on Create Account button.");

            // Fetch and log MT4 account number
            String mt4AccountNumber = createAccountPage.getMT4AccountNumber();
            logger.info("MT4 Account created with number: " + mt4AccountNumber);

           
            createAccountPage.clickDismissModal();
            logger.info("Dismissed the account creation modal.");

            // Optionally, add an assertion or further validation here if required
            // Assert something if needed.

        } catch (Exception e) {
            logger.error("An error occurred while creating the MT4 account: ", e);
            Assert.fail("Test failed due to an exception.");
        }

        logger.info("*** Finished TC004_CreateMT4AccountTest ***");
    }
}

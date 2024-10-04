package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import pageObjects.MyAccounts_CreateAccountMT4;
import pageObjects.Navigation;
import testBase.BaseClass;

public class TC003_CreateMT4AccountTest extends BaseClass {
    private Faker faker;

    // Constants for test data
    private static final String DEPOSIT_AMOUNT = "1000";
    private static final String TRADING_PLATFORM = "MT4";
    private static final String WALLET_OPTION = "USD";

    @BeforeClass
    public void setUp() {
        // Initialize the Faker instance
        faker = new Faker();
    }

    @Test
    public void createMT4_Account() throws InterruptedException {
        logger.info("*** Starting TC003_CreateMT4AccountTest ***");

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
            createAccountPage.selectTradingPlatformOption(TRADING_PLATFORM);
            logger.info("Selected trading platform: " + TRADING_PLATFORM);

            // Use Faker to generate random account name
            String accountName = faker.letterify("?????");  // 5 random letters
            createAccountPage.setAccountName(accountName);
            logger.info("Entered account name: " + accountName);

            // Select wallet option
            createAccountPage.selectWalletOption(WALLET_OPTION);
            logger.info("Selected wallet option: " + WALLET_OPTION);

            // Set account balance
            createAccountPage.setAmount(DEPOSIT_AMOUNT);
            logger.info("Set account balance to: " + DEPOSIT_AMOUNT);

            // Confirm account creation
            createAccountPage.clickIConfirm();
            logger.info("Clicked 'I Confirm' checkbox.");

            // Click Create Account button
            createAccountPage.clickCreateAccountBtn();
            logger.info("Clicked on Create Account button.");

            // Fetch and log MT4 account number
            String mt4AccountNumber = createAccountPage.getMT4AccountNumber();
            logger.info("MT4 Account created with number: " + mt4AccountNumber);

            // Dismiss the account creation modal
            createAccountPage.clickDismissModal();
            logger.info("Dismissed the account creation modal.");

            // Optionally, add an assertion or further validation here if required
            // Assert something if needed.

        } catch (Exception e) {
            logger.error("An error occurred while creating the MT4 account: ", e);
            Assert.fail("Test failed due to an exception.");
        }

        logger.info("*** Finished TC003_CreateMT4AccountTest ***");
    }
}

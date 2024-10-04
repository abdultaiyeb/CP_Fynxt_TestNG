package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.MyAccounts_TransferFunds;
import pageObjects.Navigation;
import testBase.BaseClass;

public class TC004_TransferFundsTest extends BaseClass {
    
    // Constants for test data
    private static final String FROM_WALLET = "Wallet (USD)";
    private static final String TO_WALLET = "1245983689 (dvdfv)";
    private static final String TRANSFER_AMOUNT = "1000"; // Example transfer amount

    @Test
    public void transferFunds() {
        logger.info("*** Starting TC004_TransferFundsTest ***");

        try {
            // Call the reusable login method from BaseClass
            login();
            logger.info("Login successful.");

            // Navigate to Transfer Funds page
            Navigation navigation = new Navigation(driver);
            navigation.clickOnMyWallet();
            logger.info("Clicked on My Wallet.");
            
            navigation.clickOnTransferFunds();
            logger.info("Clicked on Transfer Funds.");

            // Create an instance of MyAccounts_TransferFunds page object
            MyAccounts_TransferFunds transferFunds = new MyAccounts_TransferFunds(driver);
            
            // Select wallet for transfer
            transferFunds.selectFromWallet(FROM_WALLET);
            logger.info("Selected from wallet: " + FROM_WALLET);
            
            // Select destination wallet
            transferFunds.selectToWallet(TO_WALLET);
            logger.info("Selected to wallet: " + TO_WALLET);
            
            // Enter the transfer amount
            transferFunds.enterAmount(TRANSFER_AMOUNT);
            logger.info("Transfer amount entered: " + TRANSFER_AMOUNT);
            
            // Confirm the transfer
            transferFunds.clickConfirmTransfer();
            logger.info("Clicked on confirm transfer button.");

            // Wait for success message and validate it
            String actualMessage = transferFunds.getSuccessMessage();
            String expectedMessage = "Fund transfer successful.";
            Assert.assertEquals(actualMessage, expectedMessage, "Success message does not match the expected value.");
            logger.info("Fund transfer successful. Success message verified.");

        } catch (Exception e) {
            logger.error("An error occurred during the transfer process: ", e);
            Assert.fail("Test failed due to an exception.");
        }

        logger.info("*** Finished TC004_TransferFundsTest ***");
    }
}

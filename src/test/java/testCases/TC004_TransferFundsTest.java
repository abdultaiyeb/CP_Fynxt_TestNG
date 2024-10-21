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
            login();
            logger.info("Login successful.");

            Navigation navigation = new Navigation(driver);
            navigation.clickOnMyWallet();
            logger.info("Clicked on My Wallet.");
            
            navigation.clickOnTransferFunds();
            logger.info("Clicked on Transfer Funds.");

            MyAccounts_TransferFunds transferFunds = new MyAccounts_TransferFunds(driver);
            
            transferFunds.selectFromWallet(FROM_WALLET);
            logger.info("Selected from wallet: " + FROM_WALLET);
            
            transferFunds.selectToWallet(TO_WALLET);
            logger.info("Selected to wallet: " + TO_WALLET);
            
            transferFunds.enterAmount(TRANSFER_AMOUNT);
            logger.info("Transfer amount entered: " + TRANSFER_AMOUNT);
            
            transferFunds.clickConfirmTransfer();
            logger.info("Clicked on confirm transfer button.");

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
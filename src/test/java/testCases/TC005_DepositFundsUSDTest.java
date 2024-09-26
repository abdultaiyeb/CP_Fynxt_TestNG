package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CP.Navigation;
import pageObjects.CP.MyWallet.Deposit_Funds;

public class TC005_DepositFundsUSDTest extends BaseClass {

    @Test(priority = 1)
    public void depositFundsUSD() {
        try {
            // Call the reusable login method from BaseClass
            login();
            logger.info("Login successful.");

            // Navigate to Deposit Funds page
            Navigation navigation = new Navigation(driver);
            navigation.clickOnMyWallet();
            navigation.clickOnDepositFunds();
            logger.info("Navigated to Deposit Funds page.");

            // Create an instance of Deposit_Funds page object
            Deposit_Funds depositFunds = new Deposit_Funds(driver);

            // Perform deposit actions
            String depositAmount = "1000"; // Example deposit amount
            depositFunds.insertDepositAmount(depositAmount);
            logger.info("Deposit amount entered: " + depositAmount);

            // Select payment method
            depositFunds.clickPaymentMethod();
            logger.info("Selected payment method: Wire Transfer");

            // Select bank account
            depositFunds.selectBankAccountFromDropdown();
            logger.info("Selected bank account: Abdul(12345)");

            // Proceed and confirm deposit
            depositFunds.clickProceed();
            depositFunds.clickConfirm();
            logger.info("Proceeded and confirmed the deposit.");

            // Wait for success message and validate it
            String actualMessage = depositFunds.getSuccessMessage();
            String expectedMessage = "Fund transfer successful.";

            Assert.assertEquals(actualMessage, expectedMessage, "Success message does not match the expected value.");
            logger.info("Fund transfer successful. Success message verified.");

        } catch (Exception e) {
            logger.error("An error occurred during the deposit process: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }

    @Test(priority = 2)
    public void approvedepositFundsUSD() {
        try {
            // Call the reusable login method from BaseClass
            login();
            logger.info("Login successful.");

            // Navigate to Deposit Funds page
            Navigation navigation = new Navigation(driver);
            navigation.clickOnMyWallet();
            navigation.clickOnDepositFunds();
            logger.info("Navigated to Deposit Funds page.");

            // Close popup using Actions class
            Deposit_Funds depositFunds = new Deposit_Funds(driver);
            depositFunds.closePopupWithAction();
            logger.info("Closed the deposit confirmation popup using action class.");

        } catch (Exception e) {
            logger.error("An error occurred during the approval process: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }
}

package pageObjects.CP.MyWallet;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pageObjects.BasePage;

public class Deposit_Funds extends BasePage {

    public Deposit_Funds(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='Amount']")
    private WebElement amountField;

    @FindBy(xpath = "//div[@class='wallet-container']//li[2]//a[1]")
    private WebElement paymentMethod; 

    @FindBy(xpath = "//button[@data-id='BankAccountId']//span[@class='filter-option pull-left'][normalize-space()='Select']")
    private WebElement clickSelect;

    @FindBy(xpath="//span[@class='text'][normalize-space()='Abdul(12345)']") 
    private WebElement bankAccountOptions;

    @FindBy(xpath="//button[@id='btnProceed']") 
    private WebElement proceedBtn;

    @FindBy(xpath="//div[@class='jconfirm-buttons']//button[text()='Confirm']")
    private WebElement confirmBtn;

    @FindBy(xpath="//div[@class='toast-message']")
    private WebElement successMsg;

    // Method to insert deposit amount
    public void insertDepositAmount(String amount) {
        amountField.clear();
        waitAndSendKeys(amountField, amount);
    }

    // Method to select payment method
    public void selectPaymentMethod() {
        waitAndClick(paymentMethod);
    }

    

    // Method to select a bank account from the dropdown
    public void selectBankAccount() {
        clickBankAccountDropdownWithJS(); // Ensure dropdown is clicked using JS
        waitAndClick(bankAccountOptions);
    }

    // Method to click on the 'Proceed' button
    public void clickProceed() {
        waitAndClick(proceedBtn);
    }

    // Method to click on the 'Confirm' button in the confirmation dialog
    public void clickConfirm() {
        waitAndClick(confirmBtn);
    }

    public String getSuccessMessage() {
        waitForVisibility(successMsg);  
        return successMsg.getText();     
    }

    public void clickBankAccountDropdownWithJS() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", clickSelect);  // clickSelect is your dropdown WebElement
    }
}

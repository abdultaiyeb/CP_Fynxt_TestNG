package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

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

    @FindBy(xpath = "//span[@class='text'][normalize-space()='Abdul(12345)']")
    private WebElement bankAccountOptions;

    @FindBy(xpath = "//button[@id='btnProceed']")
    private WebElement proceedBtn;

    @FindBy(xpath = "//div[@class='jconfirm-buttons']//button[text()='Confirm']")
    private WebElement confirmBtn;

    @FindBy(xpath = "//div[@class='toast-message']")
    private WebElement successMsg;

    @FindBy(xpath = "//i[@data-dismiss='modal']")
    private WebElement closeIcon;

    // Method to insert deposit amount
    public void insertDepositAmount(String amount) {
        waitAndClearAndSendKeys(amountField, amount);
    }

    // Method to click on payment method
    public void clickPaymentMethod() {
        waitAndClick(paymentMethod);
    }

    // Method to select a bank account from the dropdown
    public void selectBankAccountFromDropdown() {
        clickBankAccountDropdownWithJS();  
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

    // Method to get the success message
    public String getSuccessMessage() {
        return waitAndGetText(successMsg); // Using retry logic for getting text
    }

    // Click bank account dropdown using JavaScript Executor
    private void clickBankAccountDropdownWithJS() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", clickSelect);
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }
    
    // Method to close popup using Actions class
    public void closePopupWithAction() {
        Actions actions = new Actions(driver);
        waitForVisibility(closeIcon);
        actions.moveToElement(closeIcon).click().perform();
    }
}

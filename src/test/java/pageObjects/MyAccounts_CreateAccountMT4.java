package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MyAccounts_CreateAccountMT4 extends BasePage {

    // Constructor
    public MyAccounts_CreateAccountMT4(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);  // Initialize WebElements
    }

    // WebElements
    @FindBy(xpath = "//button[@data-id='TradingPlatformId']") 
    private WebElement tradingPlatformDropdown;    

    @FindBy(xpath = "(//div[@class='form-group pt-sm-15'])[1]//ul//a") 
    private List<WebElement> tradingPlatformOptions;

    @FindBy(xpath = "//input[@id='AccountName']") 
    private WebElement accountNameField;

    @FindBy(xpath = "//span[@class='filter-option pull-left' and text()='AUD']") 
    private WebElement walletDropdown;

    @FindBy(xpath = "//div[@class='col-xl-6 col-md-12 col-sm-6']//ul[@class='dropdown-menu inner']//li//a") 
    private List<WebElement> walletOptions;

    @FindBy(xpath = "//input[@id='Balance']") 
    private WebElement setAmount;

    @FindBy(xpath = "//div[input[@id='chkConfirmAccount']]//label[@for='chkConfirmAccount']") 
    private WebElement clickIConfirm;

    @FindBy(xpath = "//button[@id='btnMT45CreateAccount']") 
    private WebElement createAccountBtn;

    @FindBy(xpath = "(//li[@data-resourcekey='TXT_MetaTrader_Login']//span[@class='login'])[1]") 
    private WebElement mt4AccountNo;

    @FindBy(xpath = "(//i[@class='fas fa-times' and @data-dismiss='modal'])[1]") 
    private WebElement clickCross;

    // Method to select an option from the Trading Platform dropdown
    public void selectTradingPlatformOption(String platformName) {
        waitAndClick(tradingPlatformDropdown);
        waitForVisibility(tradingPlatformOptions.get(0)); 

        for (WebElement option : tradingPlatformOptions) {
            if (option.getText().contains(platformName)) {
                waitAndClick(option);  
                break;
            }
        }
    }

    // Method to enter Account Name
    public void setAccountName(String accountName) {
        waitAndClick(accountNameField); 
        waitAndClearAndSendKeys(accountNameField, accountName);
    }

    // Method to select a currency from the wallet dropdown
    public void selectWalletOption(String currency) {
        waitAndClick(walletDropdown);
        waitForVisibility(walletOptions.get(0));  

        for (WebElement option : walletOptions) {
            if (option.getText().contains(currency)) {
                waitAndClick(option);
                break;
            }
        }
    }

    // Method to set the amount
    public void setAmount(String amount) {
        waitAndClick(setAmount); 
        waitAndClearAndSendKeys(setAmount, amount);
    }

    // Method to click 'I Confirm'
    public void clickIConfirm() {
        waitAndClick(clickIConfirm);
    }

    // Method to click 'Create Account' button
    public void clickCreateAccountBtn() {
        waitAndClick(createAccountBtn);
    }

    // Method to get MT4 Account Number
    public String getMT4AccountNumber() {
        waitForVisibility(mt4AccountNo);
        return mt4AccountNo.getText();  
    }

    // Method to click dismiss modal (cross button)
    public void clickDismissModal() {
        waitAndClick(clickCross);  
    }
}

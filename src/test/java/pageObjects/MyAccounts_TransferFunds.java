package pageObjects;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MyAccounts_TransferFunds extends BasePage {

	public MyAccounts_TransferFunds(WebDriver driver) {
		super(driver);
		
	}

@FindBy(xpath="//button[@data-id='FromAccountId']//span[@class='filter-option pull-left'][normalize-space()='Select']") 
private WebElement clickSelect;

@FindBy(xpath="(//div[@class='col-md-12 col-lg-6 col-md-12 col-sm-6'])[1]//ul[@class='dropdown-menu inner']//li//a//span[@class='text']")
private List<WebElement> walletOptions;

@FindBy(xpath="//button[contains(@data-id,'ToAccountId')]//span[contains(@class,'filter-option pull-left')][normalize-space()='Select']") 
private WebElement clickTo;

@FindBy(xpath="(//div[@class='col-md-12 col-lg-6 col-md-12 col-sm-6'])[2]//ul[@class='dropdown-menu inner']//li//a//span[@class='text']")
private List<WebElement> walletOptions2;

@FindBy(xpath="//input[@id='TransferAmount']") 
private WebElement enterAmount;

@FindBy(xpath="//button[@id='btnconfirmTransfer']") 
private WebElement clickconfirmTransferbtn;

@FindBy(xpath="//div[@id='toast-container']//div[contains(@class, 'toast-message')]") 
private WebElement fundTransferMessage;


public void selectFromWallet(String walletFromName) {
    waitAndClick(clickSelect); 

    waitForVisibility(walletOptions.get(0)); 
    waitForSeconds(3);
    wait.until(ExpectedConditions.visibilityOfAllElements(walletOptions));

    for (WebElement wallet : walletOptions) {
        if (wallet.getText().equalsIgnoreCase(walletFromName)) {
            waitAndClick(wallet); 
            break; 
        }
    }
}

public void selectToWallet(String walletToName) {
	 waitForSeconds(3);
    waitAndClick(clickTo); 
    waitForVisibility(walletOptions2.get(0)); 
    waitForSeconds(3);
    wait.until(ExpectedConditions.visibilityOfAllElements(walletOptions2));

    for (WebElement wallet : walletOptions2) {
        if (wallet.getText().equalsIgnoreCase(walletToName)) {
            waitAndClick(wallet); 
            break; 
        }
    }
}

public void enterAmount(String amount) {
    waitAndClearAndSendKeys(enterAmount, amount);
}

public void clickConfirmTransfer() {
    waitAndClick(clickconfirmTransferbtn);
}


public void waitForSeconds(int seconds) {
    try {
        TimeUnit.SECONDS.sleep(seconds);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); 
    }
}
public String getSuccessMessage() {
	 waitForVisibility(fundTransferMessage);
    return waitAndGetText(fundTransferMessage); 
}


}



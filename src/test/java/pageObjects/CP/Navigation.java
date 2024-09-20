package pageObjects.CP;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.BasePage;

public class Navigation extends BasePage {

    public Navigation(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[normalize-space()='My Accounts']")
    private WebElement myAccountsMenu;

    @FindBy(xpath = "//body/aside[@id='sidebar']/div[@id='menu-accordion1']/ul[contains(@class,'navbar-nav')]/li[contains(@class,'nav-item dropdown-true dropdown-true-active')]/div[@id='MyAccounts_menu_type_1']/ul[contains(@class,'nav')]/li[contains(@class,'collapsedIconView-submenus')]/a[1]")
    private WebElement createAccountSubMenu;

    @FindBy(xpath = "//span[normalize-space()='My Wallet']")
    private WebElement myWalletMenu;

    @FindBy(xpath = "//body/aside[@id='sidebar']/div[@id='menu-accordion1']/ul[contains(@class,'navbar-nav')]/li[contains(@class,'nav-item dropdown-true dropdown-true-active')]/div[@id='MyWallet_menu_type_1']/ul[contains(@class,'nav')]/li[1]/a[1]")
    private WebElement depositFundsSubMenu;

    @FindBy(xpath = "//span[normalize-space(text()) = 'Managed Accounts']")
    private WebElement managedAccountsMenu;

    @FindBy(xpath = "//body/aside[@id='sidebar']/div[@id='menu-accordion1']/ul[contains(@class,'navbar-nav')]/li[contains(@class,'nav-item dropdown-true dropdown-true-active')]/div[@id='ManagedAccounts_menu_type_1']/ul[contains(@class,'nav')]/li[3]/a[1]")
    private WebElement publishedStrategiesSubMenu;

    public void clickOnMyAccounts() {
        waitAndClick(myAccountsMenu);
    }

    public void clickOnCreateAccount() {
        waitAndClick(createAccountSubMenu);
    }

    public void clickOnMyWallet() {
        waitAndClick(myWalletMenu);
    }

    public void clickOnDepositFunds() {
        waitAndClick(depositFundsSubMenu);
    }

    public void clickOnManagedAccountsMenu() {
        waitAndClick(managedAccountsMenu);
    }

    public void clickOnPublishedStrategiesSubMenu() {
        waitAndClick(publishedStrategiesSubMenu);
    }
}

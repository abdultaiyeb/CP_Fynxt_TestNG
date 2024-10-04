package pageObjects;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ManagedAccounts_PublishedStrategies extends BasePage {

    public ManagedAccounts_PublishedStrategies(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@class='addMAMStrategy btn btn-theme-priary download']")
    private WebElement addbtn;

    @FindBy(xpath = "//input[@id='StrategyName']")
    private WebElement strategyNameTxt;

    @FindBy(xpath = "//input[@id='PublisherName']")
    private WebElement publisherNameTxt;

    @FindBy(xpath = "//input[@id='MasterRawPF']")
    private WebElement setPerformanceFee;

    @FindBy(id = "InvestmentSettlementMethod")
    private WebElement performanceFeeDropdown;

    @FindBy(xpath = "//textarea[@id='StrategyDescription']")
    private WebElement strategyDescription;

    @FindBy(xpath = "//button[@id='btnCreateMAMStrategy']")
    private WebElement sendRequest;

    @FindBy(xpath = "//tbody//h6[@class='m-0 ellipsis']//span[@class='ellipsis']")
    private List<WebElement> strategiesList;

    // Method to click the Add button
    public void clickAddButton() {
        waitAndClick(addbtn);
    }

    // Method to enter the strategy name
    public void enterStrategyName(String strategyName) {
        waitAndClearAndSendKeys(strategyNameTxt, strategyName);
    }

    // Method to enter the publisher name
    public void enterPublisherName(String publisherName) {
        waitAndClearAndSendKeys(publisherNameTxt, publisherName);
    }

    // Method to enter the strategy description
    public void enterStrategyDescription(String description) {
        waitAndClearAndSendKeys(strategyDescription, description);
    }

    // Method to set the performance fee and select the period option
    public void setPerformanceFee(String fee, String periodOption) {
        waitAndClearAndSendKeys(setPerformanceFee, fee); // Clear and send keys for performance fee
        selectPerformanceFeePeriod(periodOption);
    }

    // Helper method to select the performance fee period from dropdown
    private void selectPerformanceFeePeriod(String periodOption) {
        Select performanceFeeSelect = new Select(performanceFeeDropdown);
        performanceFeeSelect.selectByVisibleText(periodOption);
    }

    // Method to get the names of all strategies
    public List<String> getStrategyNames() {
        return strategiesList.stream()
                             .map(WebElement::getText)
                             .collect(Collectors.toList());
    }

    // Method to click the Send Request button
    public void clickSendRequest() {
        waitAndClick(sendRequest);
    }
}

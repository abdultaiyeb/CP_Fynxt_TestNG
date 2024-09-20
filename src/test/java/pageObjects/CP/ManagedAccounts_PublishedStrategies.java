package pageObjects.CP;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pageObjects.BasePage;

public class ManagedAccounts_PublishedStrategies extends BasePage {

    public ManagedAccounts_PublishedStrategies(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath="//button[@class='addMAMStrategy btn btn-theme-priary download']") 
    private WebElement addbtn;

    @FindBy(xpath="//input[@id='StrategyName']") 
    private WebElement strategyNameTxt;

    @FindBy(xpath="//input[@id='PublisherName']") 
    private WebElement publisherNameTxt;

    @FindBy(xpath="//input[@id='MasterRawPF']") 
    private WebElement setPerformanceFee;

    @FindBy(id="InvestmentSettlementMethod") 
    private WebElement performanceFeeDropdown; 

    @FindBy(xpath="//textarea[@id='StrategyDescription']") 
    private WebElement strategyDescription;
    
    @FindBy(xpath="//button[@id='btnCreateMAMStrategy']") 
    private WebElement sendRequest;

    @FindBy(xpath="//tbody//h6[@class='m-0 ellipsis']//span[@class='ellipsis']")
    private List<WebElement> strategiesList;
    
    public void clickAddButton() {
        waitAndClick(addbtn);
    }

    public void enterStrategyName(String strategyName) {
        waitAndSendKeys(strategyNameTxt, strategyName);
    }

    public void enterPublisherName(String publisherName) {
        waitAndSendKeys(publisherNameTxt, publisherName);
    }

    public void enterStrategyDescription(String description) {
        waitAndSendKeys(strategyDescription, description);
    }

    public void setPerformanceFee(String fee, String periodOption) {
        setPerformanceFee.clear();
        waitAndSendKeys(setPerformanceFee, fee);
        Select performanceFeeSelect = new Select(performanceFeeDropdown);
        performanceFeeSelect.selectByVisibleText(periodOption);
    }

    public List<String> getStrategyNames() {
        return strategiesList.stream()
                             .map(WebElement::getText)
                             .collect(Collectors.toList());
    }
    
    
    public void clickSendRequest() {
        waitAndClick(sendRequest);
    }

	
}

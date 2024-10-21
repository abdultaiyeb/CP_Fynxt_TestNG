package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Set;

public class ManagedAccounts_Leaderboard extends BasePage {

    public ManagedAccounts_Leaderboard(WebDriver driver) {
        super(driver);
    }

    // Find all elements of the leaderboard
    @FindBy(xpath = "//div[@id=\"Active-slider\"]//h6")
    private List<WebElement> LeaderboardList;

    // Subscribe button for each leaderboard item
    @FindBy(xpath = "//div[@id=\"Active-slider\"]//div[@class=\"overlay-line mt-3\"]//a")
    private WebElement Subscribebtn;
    
    // Subscribe button in the new tab
    @FindBy(xpath="//div[@class='col-auto pl-0']//span[@class='d-inline-flex'][normalize-space()='Subscribe']") 
    private WebElement clickSubscribe;

    // Strategy name in the new tab to verify
    @FindBy(xpath="//p[@class='text-nocase mb-0 mt-1']") 
    private WebElement testStrategytName;

    // Method to select a specific leaderboard item, click Subscribe, switch to the new tab,
    // click on Subscribe in the new tab, and verify the strategy name
    public void selectLeaderboardAndSubscribe(String leaderboardName) {
        boolean found = false;

        // Iterate over the leaderboard list to find the matching element
        for (WebElement leaderboardItem : LeaderboardList) {
            if (leaderboardItem.getText().equalsIgnoreCase(leaderboardName)) {
                // Wait for visibility of the leaderboard item
                waitForVisibility(leaderboardItem);
                System.out.println("Found the leaderboard item: " + leaderboardName);

                // Click on the Subscribe button
                waitAndClick(Subscribebtn);
                
                // Switch to the new tab
                switchToNewTab();

                // Perform actions in the new tab
                subscribeInNewTabAndVerifyStrategy(leaderboardName);

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Leaderboard item not found: " + leaderboardName);
        }
    }

    // Method to switch to the newly opened tab
    private void switchToNewTab() {
        // Get the current window handle before clicking
        String originalWindow = driver.getWindowHandle();

        // Get all window handles
        Set<String> allWindows = driver.getWindowHandles();

        // Loop through window handles
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(originalWindow)) {
                // Switch to the new tab
                driver.switchTo().window(windowHandle);
                System.out.println("Switched to new tab.");
                break;
            }
        }
    }

    // Method to click on Subscribe in the new tab and verify the strategy name
    private void subscribeInNewTabAndVerifyStrategy(String expectedStrategyName) {
        // Wait for the Subscribe button and click it in the new tab
        waitAndClick(clickSubscribe);
        System.out.println("Clicked Subscribe in the new tab.");

        // Wait for the strategy name to be visible and get its text
        waitForVisibility(testStrategytName);
        String actualStrategyName = testStrategytName.getText().trim();

        // Verify that the actual strategy name matches the expected strategy name
        if (actualStrategyName.equalsIgnoreCase(expectedStrategyName)) {
            System.out.println("Strategy name verified successfully: " + actualStrategyName);
        } else {
            System.out.println("Strategy name verification failed. Expected: " + expectedStrategyName + ", but found: " + actualStrategyName);
        }
    }
}

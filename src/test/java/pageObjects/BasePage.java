package pageObjects;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Supplier;

import java.util.NoSuchElementException;
import java.util.logging.Logger;

public class BasePage {

    protected WebDriver driver;
    protected Wait<WebDriver> wait;
    private static final int RETRY_LIMIT = 3;
    private static final Logger LOGGER = Logger.getLogger(BasePage.class.getName());

    // Constructor with default timeout and polling interval
    public BasePage(WebDriver driver) {
        this(driver, 20, 500); // Default values
    }

    // Overloaded constructor to allow custom timeout and polling intervals
    public BasePage(WebDriver driver, long timeoutInSeconds, long pollingInMillis) {
        this.driver = driver;
        this.wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofMillis(pollingInMillis))
                .ignoring(NoSuchElementException.class);
        PageFactory.initElements(driver, this);
    }

    // Wait for an element to be visible
    public void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Generic method to handle retries for actions
    private void retryAction(Runnable action) {
        for (int attempt = 1; attempt <= RETRY_LIMIT; attempt++) {
            try {
                action.run();
                return; // Exit if action is successful
            } catch (Exception e) {
                if (attempt == RETRY_LIMIT) {
                    throw new RuntimeException("Action failed after " + RETRY_LIMIT + " attempts: " + e.getMessage());
                }
                LOGGER.warning("Attempt " + attempt + " failed: " + e.getMessage() + ". Retrying...");
            }
        }
    }

    // Method to click an element
    public void waitAndClick(WebElement element) {
        retryAction(() -> {
            waitForVisibility(element);
            element.click();
        });
    }

    // Method to clear an element and send keys to it
    public void waitAndClearAndSendKeys(WebElement element, String text) {
        retryAction(() -> {
            waitForVisibility(element);
            element.clear();
            element.sendKeys(text);
        });
    }

    // Method to get text from an element with retry logic
    public String waitAndGetText(WebElement element) {
        return retryActionWithResult(() -> {
            waitForVisibility(element);
            return element.getText().trim();
        });
    }

    // Generic method to handle retries with return values
    private <T> T retryActionWithResult(Supplier<T> action) {
        for (int attempt = 1; attempt <= RETRY_LIMIT; attempt++) {
            try {
                return action.get(); // Execute the action and return the result
            } catch (Exception e) {
                if (attempt == RETRY_LIMIT) {
                    throw new RuntimeException("Action failed after " + RETRY_LIMIT + " attempts: " + e.getMessage());
                }
                LOGGER.warning("Attempt " + attempt + " failed: " + e.getMessage() + ". Retrying...");
            }
        }
        return null; // This line will never be reached, but it's here to satisfy the return type
    }
}

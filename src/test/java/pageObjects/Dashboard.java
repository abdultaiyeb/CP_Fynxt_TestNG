package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.logging.Logger;

public class Dashboard extends BasePage {

    private static final Logger LOGGER = Logger.getLogger(Dashboard.class.getName());

    public Dashboard(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//body/form[@novalidate='novalidate']/div/h5[1]")
    private WebElement dashboardHeading;

    // Method to verify if the dashboard heading text is correct
    public boolean verifyDashboardHeading() {
        // Wait for the element to be visible
        waitForVisibility(dashboardHeading);
        
        // Retrieve the text from the element
        String actualText = dashboardHeading.getText().trim();
        String expectedText = "Dashboard"; // Define the expected text
        
        // Log actual and expected text for debugging
        LOGGER.info("Verifying dashboard heading. Expected: " + expectedText + ", Actual: " + actualText);

        // Compare the actual text with the expected text
        return actualText.equals(expectedText);
    }
}

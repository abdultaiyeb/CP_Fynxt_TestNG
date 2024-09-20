package pageObjects.CP;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageObjects.BasePage;

public class Dashboard extends BasePage{

	public Dashboard(WebDriver driver) {
		super(driver);
		
	}

	@FindBy(xpath="//body/form[@novalidate='novalidate']/div/h5[1]")
    private WebElement dashboardHeading;
	
	
	public boolean isDashboardHeadingCorrect() {
        // Wait for the element to be visible
        waitForVisibility(dashboardHeading);
        // Retrieve the text from the element
        String actualText = dashboardHeading.getText();
        // Define the expected text
        String expectedText = "Dashboard ";
        // Compare the actual text with the expected text
        return actualText.equals(expectedText);
    }
}

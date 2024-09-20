package testCases;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CP.ManagedAccounts_PublishedStrategies;
import pageObjects.CP.Navigation;

public class TC015_CreatePublicStrategy extends BaseClass {

    @Test
    public void create_Public_strategy() {
        logger.info("*** Starting TC005_CreatePublicStrategy ***");

        try {
            // Call the reusable login method from BaseClass
            login();
            logger.info("Login successful.");

            Navigation navigation = new Navigation(driver);
            navigation.clickOnManagedAccountsMenu();
            logger.info("Clicked on Managed Accounts menu.");

            navigation.clickOnPublishedStrategiesSubMenu();
            logger.info("Clicked on Published Strategies sub-menu.");
            
            ManagedAccounts_PublishedStrategies publishedStrategies = new ManagedAccounts_PublishedStrategies(driver);

            publishedStrategies.clickAddButton();
            logger.info("Clicked on Add button.");

            // Use Faker to generate random data
            String strategyName = faker.lorem().characters(7)+ " Strategy";
            String publisherName = faker.name().fullName();
            String strategyDescription = faker.lorem().sentence();

            publishedStrategies.enterStrategyName(strategyName);
            logger.info("Entered strategy name: " + strategyName);
            System.out.println("Strategy Name to be verified: " + strategyName);  // Print to console

            publishedStrategies.enterPublisherName(publisherName);
            logger.info("Entered publisher name: " + publisherName);

            publishedStrategies.enterStrategyDescription(strategyDescription);
            logger.info("Entered strategy description: " + strategyDescription);

            publishedStrategies.setPerformanceFee("10", "MonthEnd");
            logger.info("Set performance fee to 10 for MonthEnd.");

            publishedStrategies.clickSendRequest();
            logger.info("Clicked on Send Request button.");
            
            Thread.sleep(5000);
            

            List<String> strategyNames = publishedStrategies.getStrategyNames();
            logger.info("Fetched list of strategy names.");

            System.out.println("List of strategy names: " + strategyNames);

            Assert.assertTrue(strategyNames.contains(strategyName), 
                "The created strategy name was not found in the list.");
            logger.info("Strategy name found in the list, test passed.");

        } catch (Exception e) {
            logger.error("An error occurred while creating the public strategy: ", e);
            Assert.fail("Test failed due to an exception.");
        }

        logger.info("*** Finished TC005_CreatePublicStrategy ***");
    }
}

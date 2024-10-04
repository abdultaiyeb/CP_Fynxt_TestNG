package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.github.javafaker.Faker;

import pageObjects.SigninPage; // Import SigninPage for login functionality

public class BaseClass {
    public static WebDriver driver;
    public Logger logger;
    public Properties p;    
    protected Faker faker;
    SigninPage signinPage; // Declare SigninPage object

    @BeforeClass(groups = {"Sanity", "Regression", "Master"})
    @Parameters({"os", "browser"})
    public void setUpClass(String os, String br) throws IOException {
        // Load the properties file
        try (FileReader file = new FileReader("./src/test/resources/config.properties")) {
            p = new Properties();
            p.load(file);
        }

        // Initialize the logger
        logger = LogManager.getLogger(this.getClass());
        logger.info("Logger initialized for " + this.getClass().getName());

        // Initialize the Faker object
        faker = new Faker();
        logger.info("Faker instance initialized");
    }

    @BeforeMethod(groups = {"Sanity", "Regression", "Master"})
    @Parameters({"browser"})
    public void setUp(String br) {
        // Browser setup
        switch (br.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                logger.warn("Invalid browser name: " + br + ". Launching Chrome as default.");
                driver = new ChromeDriver();
        }

        // WebDriver setup
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(p.getProperty("appurl"));
        driver.manage().window().maximize();
        logger.info("WebDriver initialized and application URL opened");
    }

    @AfterMethod(groups = {"Sanity", "Regression", "Master"})
    public void tearDownMethod() {
        if (driver != null) {
            driver.quit();
            logger.info("WebDriver session ended after method execution");
        }
    }

    @AfterClass(groups = {"Sanity", "Regression", "Master"})
    public void tearDownClass() {
        logger.info("Test class teardown completed");
    }

    // Reusable login method
    public void login() {
        signinPage = new SigninPage(driver);  // Initialize SigninPage object

        signinPage.enterEmail(p.getProperty("username"));
        logger.info("Entered username");

        signinPage.enterPassword(p.getProperty("password"));
        logger.info("Entered password");

        signinPage.clickSignIn();
        logger.info("Clicked on Sign-In button");
    }

    // Faker utility methods
    public String DOB() {
        Date birthday = faker.date().birthday();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(birthday);
    }
    
    public String randomString() {
        return faker.letterify("????????");  // Generates a random string of 8 letters
    }

    public String generatePhoneNumber() {
        String prefix = "98";
        String randomDigits = faker.number().digits(8);  // Generates the remaining 8 digits
        return prefix + randomDigits;
    }

    public String generateNumber() {
        return faker.number().digits(10);  // Generates a 10-digit number as a string
    }

    public String generatePassword() {
        String upperCase = faker.letterify("?").toUpperCase();  // Generates one random uppercase letter
        String lowerCase = faker.letterify("???").toLowerCase();  // Generates three random lowercase letters
        String numbers = faker.number().digits(6);  // Generates a string of six random digits
        return upperCase + lowerCase + "@" + numbers;
    }

    // Screenshot capture method
    public String captureScreen(String tname) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath = System.getProperty("user.dir") + "/screenshots/" + tname + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);

        // Copy the file to the target location
        FileUtils.copyFile(sourceFile, targetFile);
        
        return targetFilePath;
    }
}
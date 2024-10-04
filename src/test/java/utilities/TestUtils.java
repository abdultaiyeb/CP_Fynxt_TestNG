package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.github.javafaker.Faker;

public class TestUtils {
	protected static  Faker faker = new Faker();

    // Faker utility methods
    public static String generateDOB() {
        Date birthday = faker.date().birthday();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(birthday);
    }	

    public static String randomString(int length) {
        return faker.letterify("?".repeat(length));
    }

    public static String generatePhoneNumber() {
        String prefix = "98";
        String randomDigits = faker.number().digits(8);
        return prefix + randomDigits;
    }

    public static String generateNumber(int digits) {
        return faker.number().digits(digits);
    }

    public static String generatePassword() {
        String upperCase = faker.letterify("?").toUpperCase();
        String lowerCase = faker.letterify("???").toLowerCase();
        String numbers = faker.number().digits(6);
        return upperCase + lowerCase + "@" + numbers;
    }

    // Screenshot capture method
    public static String captureScreen(WebDriver driver, String tname) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String targetFilePath = System.getProperty("user.dir") + "/screenshots/" + tname + "_" + timeStamp + ".png";
        FileUtils.copyFile(sourceFile, new File(targetFilePath));
        return targetFilePath;
    }
}

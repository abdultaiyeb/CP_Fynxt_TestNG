package utilities;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigReader {
    private static Properties properties;
    private static final Logger logger = LogManager.getLogger(ConfigReader.class);

    // Static block to load properties file when the class is loaded
    static {
        try (FileReader reader = new FileReader("./src/test/resources/config.properties")) {
            properties = new Properties();
            properties.load(reader);
            logger.info("Config properties loaded successfully.");
        } catch (IOException e) {
            logger.error("Failed to load config.properties file.", e);
            throw new RuntimeException("Could not load configuration file", e);
        }
    }

    // Method to get property values
    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value != null) {
            return value;
        } else {
            logger.warn("Property key not found: " + key);
            return "";
        }
    }
}
package org.log4j1x;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4jPropertiesFileDemo {
    private static final Logger logger = Logger.getLogger(Log4jPropertiesFileDemo.class);
    public static void main(String[] args) {
        // Initialize log4j configuration
        //if log4j.properties is in src/main/resources directory
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        // if root log4j.properties is in directory
        //  PropertyConfigurator.configure("log4j.properties");
        logger.trace("This is a TRACE message");
        logger.debug("This is a DEBUG message");
        logger.info("This is an INFO message");
        logger.warn("This is a WARN message");
        logger.error("This is an ERROR message");
        logger.fatal("This is a FATAL message");

        // Demonstrate logging with parameters
        String username = "John";
        int userId = 12345;
        logger.info("User logged in: " + username + " (ID: " + userId + ")");

        // Demonstrate exception logging
        try {
            int result = 10 / 0;
        } catch (Exception e) {
            logger.error("Division by zero error occurred", e);
        }

        logger.info("Log4j 1.x demonstration completed successfully!");
    }
}

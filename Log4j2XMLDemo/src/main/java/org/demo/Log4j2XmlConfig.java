package main.java.org.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4j2XmlConfig {
    private static Logger logger = LogManager.getLogger(Log4j2XmlConfig.class);
    public static void main(String[] args) {
        System.out.println("=== Log4j2XmlConfig ===");
        // Demonstrate logging to all appenders
        demonstrateAllAppenders();
    }

    private static void demonstrateAllAppenders() {
        logger.trace("This is a TRACE message - will appear in all configured appenders");
        logger.debug("This is a DEBUG message - will appear in all configured appenders");
        logger.info("This is an INFO message - will appear in all configured appenders");
        logger.warn("This is a WARN message - will appear in all configured appenders");
        logger.error("This is an ERROR message - will appear in all configured appenders");
        logger.fatal("This is a FATAL message - will appear in all configured appenders");

        // Demonstrate logging with parameters
        String user = "John Doe";
        int count = 42;
        logger.info("User {} has performed {} operations", user, count);

        // Demonstrate exception logging
        try {
            throw new RuntimeException("Sample exception for demonstration");
        } catch (Exception e) {
            logger.error("An exception occurred", e);
        }

    }

}

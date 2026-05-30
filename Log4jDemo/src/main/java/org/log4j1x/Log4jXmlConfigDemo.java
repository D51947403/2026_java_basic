package org.log4j1x;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class Log4jXmlConfigDemo {
    // Get logger instance
    private static final Logger logger = Logger.getLogger(Log4jXmlConfigDemo.class);

    public static void main(String[] args) {
        // Initialize log4j configuration

       DOMConfigurator.configure("src/main/resources/log4j.xml");

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

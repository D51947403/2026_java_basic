package org.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackDemo {
    // Best Practice: Define the logger as static final based on the class name
    private static final Logger logger = LoggerFactory.getLogger(LogbackDemo.class);
    public static void main(String[] args) {
        logger.info("Application is starting...");
        String username = "Devendra Singraul";
        // Best Practice: Use parameterized logging, not string concatenation!
        logger.debug("User {} is attempting to log in.", username);

        try {
            // Simulate an error
            throw new RuntimeException("Database connection failed");
        } catch (Exception e) {
            // Best Practice: Pass the exception as the last argument
            logger.error("An unexpected error occurred", e);
        }

        logger.trace("priting trace log");
        logger.debug("priting debug log");
        logger.info("priting info log");
        logger.warn("priting warn log");
        logger.error("priting error log");

    }
}

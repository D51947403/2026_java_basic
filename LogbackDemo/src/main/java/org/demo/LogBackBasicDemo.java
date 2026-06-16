package org.demo;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogBackBasicDemo {
    // Best Practice: Define the logger as static final based on the class name
    private static final Logger logger = LoggerFactory.getLogger(LogBackBasicDemo.class);
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
        logger.info("============Printing Internal State============");
        // print internal state
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(loggerContext);
    }

}

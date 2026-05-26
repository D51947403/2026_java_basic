package com.practice.logdemo.jul;

import java.util.logging.*;
import java.io.IOException;

public class JulLoggingExample {
    private static final Logger logger = Logger.getLogger(JulLoggingExample.class.getName());
    
    static {
        try {
            // Configure file handler
            FileHandler fileHandler = new FileHandler("jul_logging_20May2026.log", false);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);

            // Configure console handler with custom formatter
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new CustomFormatter());
            logger.addHandler(consoleHandler);

            // Set logging level
            logger.setLevel(Level.ALL);

        } catch (IOException e) {
            logger.severe("Failed to initialize file handler: " + e.getMessage());
        }
    }

    public static void demonstrateBasicLogging() {
        logger.info("=== JUL Basic Logging Demo ===");
        
        // Different log levels
        logger.severe("SEVERE: Critical error occurred");
        logger.warning("WARNING: Potential issue detected");
        logger.info("INFO: General information message");
        logger.config("CONFIG: Configuration details");
        logger.fine("FINE: Detailed debugging information");
        logger.finer("FINER: More detailed debugging");
        logger.finest("FINEST: Most detailed debugging");
    }
    
    public static void demonstrateParameterLogging() {
        logger.info("=== JUL Parameter Logging Demo ===");
        
        String user = "Alice";
        int loginAttempts = 5;
        boolean success = false;
        
        // JUL doesn't support parameterized logging like SLF4J
        logger.info("User " + user + " attempted login " + loginAttempts + " times");
        logger.info(String.format("Login status for %s: %s", user, success ? "Success" : "Failed"));
    }
    
    public static void demonstrateExceptionLogging() {
        logger.info("=== JUL Exception Logging Demo ===");
        
        try {
            // Simulate an exception
            String nullString = null;
            int length = nullString.length();
            
        } catch (NullPointerException e) {
            logger.log(Level.SEVERE, "NullPointerException caught", e);
            logger.log(Level.WARNING, "Failed to process string: " + e.getMessage());
        }
        
        try {
            // Simulate another exception
            int result = 10 / 0;
            
        } catch (ArithmeticException e) {
            logger.log(Level.SEVERE, "ArithmeticException occurred", e);
        }
    }
    
    public static void demonstrateMethodLogging() {
        logger.entering(JulLoggingExample.class.getName(), "demonstrateMethodLogging");
        
        logger.info("Processing method execution : demonstrateMethodLogging");
        
        try {
            // Simulate some work
            Thread.sleep(100);
            logger.info("Method processing completed successfully");
            
        } catch (InterruptedException e) {
            logger.log(Level.WARNING, "Method execution interrupted", e);
            Thread.currentThread().interrupt();
        }
        logger.info("exiting method execution : demonstrateMethodLogging");
        logger.exiting(JulLoggingExample.class.getName(), "demonstrateMethodLogging");
    }
    
    public static void demonstrateLoggerHierarchy() {
        logger.info("=== JUL Logger Hierarchy Demo ===");
        
        // Get parent logger
        Logger parentLogger = logger.getParent();
        if (parentLogger != null) {
            logger.info("Parent logger: " + parentLogger.getName());
            logger.info("Parent level: " + parentLogger.getLevel());
        }
        
        // Create child logger
        Logger childLogger = Logger.getLogger(JulLoggingExample.class.getName() + ".child");
        childLogger.info("Message from child logger");
        
        // Show logger handlers
        Handler[] handlers = logger.getHandlers();
        logger.info("Number of handlers: " + handlers.length);
        for (Handler handler : handlers) {
            logger.info("Handler: " + handler.getClass().getSimpleName());
        }
    }
    
    // Custom formatter for better output
    private static class CustomFormatter extends Formatter {
        @Override
        public String format(LogRecord record) {
            return String.format("[%1$tF %1$tT] [%2$-7s] %3$-20s: %4$s %n",
                new java.util.Date(record.getMillis()),
                record.getLevel().getLocalizedName(),
                record.getLoggerName(),
                record.getMessage());
        }
    }
    
    public static void main(String[] args) {
      //  demonstrateBasicLogging();
    //    demonstrateParameterLogging();
      //  demonstrateExceptionLogging();
     //  demonstrateMethodLogging();
       demonstrateLoggerHierarchy();
    }
}

package com.practice.logdemo.jul;

import java.util.logging.*;
import java.io.IOException;
import java.io.InputStream;

public class JulLoggingPropertiesConfig {
    private static final Logger logger = Logger.getLogger(JulLoggingPropertiesConfig.class.getName());
    
    static {
        try {
            // Load logging configuration from properties file
            loadLoggingConfiguration();
            logger.info("Logging configuration loaded successfully from logging.properties");
        } catch (IOException e) {
            logger.warning("Failed to load logging configuration, using fallback setup: " + e.getMessage());
        }
    }
    
    private static void loadLoggingConfiguration() throws IOException {
        String configFileName = "com/practice/logdemo/jul/logging.properties";
        InputStream configStream = JulLoggingPropertiesConfig.class.getClassLoader().getResourceAsStream(configFileName);
        
        if (configStream != null) {
            LogManager.getLogManager().readConfiguration(configStream);
            configStream.close();
        } else {
            throw new IOException("Configuration file not found: " + configFileName);
        }
    }
    
    public static void demonstrateBasicLogging() {
        logger.info("=== JUL Configuration-Based Logging Demo ===");
        
        // Different log levels
        logger.severe("SEVERE: Critical error occurred");
        logger.warning("WARNING: Potential issue detected");
        logger.info("INFO: General information message");
        logger.config("CONFIG: Configuration details");
        logger.fine("FINE: Detailed debugging information");
        logger.finer("FINER: More detailed debugging");
        logger.finest("FINEST: Most detailed debugging");
    }
    
    public static void demonstrateConfigurationFeatures() {
        logger.info("=== Configuration Features Demo ===");
        
        // Show current logger configuration
        logger.info("Logger name: " + logger.getName());
        logger.info("Logger level: " + logger.getLevel());
        logger.info("Logger using parent handlers: " + logger.getUseParentHandlers());
        
        // Show handlers
        Handler[] handlers = logger.getHandlers();
        logger.info("Number of handlers: " + handlers.length);
        for (Handler handler : handlers) {
            logger.info("Handler: " + handler.getClass().getSimpleName() + 
                       ", Level: " + handler.getLevel() + 
                       ", Formatter: " + handler.getFormatter().getClass().getSimpleName());
        }
        
        // Test file rotation (if configured)
        for (int i = 0; i < 10; i++) {
            logger.info("Log message " + (i + 1) + " for testing file rotation");
        }
    }
    
    public static void demonstrateParameterLogging() {
        logger.info("=== Parameter Logging Demo ===");
        
        String user = "Bob";
        int loginAttempts = 3;
        boolean success = true;
        
        logger.info("User " + user + " attempted login " + loginAttempts + " times");
        logger.info(String.format("Login status for %s: %s", user, success ? "Success" : "Failed"));
    }
    
    public static void demonstrateExceptionLogging() {
        logger.info("=== Exception Logging Demo ===");
        
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
    
    public static void main(String[] args) {
        demonstrateBasicLogging();
        demonstrateConfigurationFeatures();
        demonstrateParameterLogging();
        demonstrateExceptionLogging();
        
        logger.info("Configuration-based logging demonstration completed!");
    }
}

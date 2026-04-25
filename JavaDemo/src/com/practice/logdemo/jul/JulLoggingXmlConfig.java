package com.practice.logdemo.jul;

import java.util.logging.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * Java Util Logging example using XML configuration file
 * Demonstrates loading logging configuration from XML format
 */
public class JulLoggingXmlConfig {
    private static final Logger logger = Logger.getLogger(JulLoggingXmlConfig.class.getName());
    
    static {
        try {
            // Load logging configuration from XML file
            loadXmlConfiguration();
            logger.info("XML logging configuration loaded successfully");
        } catch (IOException e) {
            // Fallback to basic configuration if XML loading fails
            setupFallbackConfiguration();
            logger.warning("Failed to load XML configuration, using fallback setup: " + e.getMessage());
        }
    }
    
    private static void loadXmlConfiguration() throws IOException {
        String xmlConfigFileName = "logging.xml";
        InputStream configStream = JulLoggingXmlConfig.class.getClassLoader().getResourceAsStream(xmlConfigFileName);
        
        if (configStream != null) {
            LogManager.getLogManager().readConfiguration(configStream);
            configStream.close();
        } else {
            throw new IOException("XML configuration file not found: " + xmlConfigFileName);
        }
    }
    
    private static void setupFallbackConfiguration() {
        try {
            // Basic fallback configuration using standalone CustomFormatter
            GlobalCustomFormatter formatter = new GlobalCustomFormatter();
            
            // Console handler
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(formatter);
            logger.addHandler(consoleHandler);
            
            // File handler
            FileHandler fileHandler = new FileHandler("jul_xml_fallback.log", true);
            fileHandler.setFormatter(formatter);
            logger.addHandler(fileHandler);
            
            logger.setLevel(Level.INFO);
            
        } catch (IOException e) {
            System.err.println("Failed to setup fallback logging configuration: " + e.getMessage());
        }
    }
    
    public static void demonstrateXmlConfigLogging() {
        logger.info("=== JUL XML Configuration Logging Demo ===");
        
        // Different log levels
        logger.severe("SEVERE: Critical error occurred");
        logger.warning("WARNING: Potential issue detected");
        logger.info("INFO: General information message");
        logger.config("CONFIG: Configuration details");
        logger.fine("FINE: Detailed debugging information");
        logger.finer("FINER: More detailed debugging");
        logger.finest("FINEST: Most detailed debugging");
    }
    
    public static void demonstrateXmlConfigFeatures() {
        logger.info("=== XML Configuration Features Demo ===");
        
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
        
        // Test XML-specific features
        logger.info("Testing XML configuration with custom formatter");
        for (int i = 0; i < 5; i++) {
            logger.info("XML config test message " + (i + 1));
        }
    }
    
    public static void demonstrateExceptionLogging() {
        logger.info("=== Exception Logging with XML Config ===");
        
        try {
            // Simulate an exception
            String nullString = null;
            int length = nullString.length();
            
        } catch (NullPointerException e) {
            logger.log(Level.SEVERE, "NullPointerException caught", e);
            logger.log(Level.WARNING, "Failed to process string: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        demonstrateXmlConfigLogging();
        demonstrateXmlConfigFeatures();
        demonstrateExceptionLogging();
        
        logger.info("XML configuration-based logging demonstration completed!");
    }
}

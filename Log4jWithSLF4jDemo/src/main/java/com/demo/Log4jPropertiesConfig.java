package main.java.com.demo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//SLF4J Logging with Properties File Configuration
//Direct jar links:
// https://repo1.maven.org/maven2/log4j/log4j/1.2.17/log4j-1.2.17.jar
//https://repo1.maven.org/maven2/org/slf4j/slf4j-log4j12/1.7.33/
//https://repo1.maven.org/maven2/org/slf4j/slf4j-api/1.7.33/slf4j-api-1.7.33.jar
public class Log4jPropertiesConfig {
    private static final Logger logger = LoggerFactory.getLogger(Log4jPropertiesConfig.class);

    public static void demonstrateBasicLogging() {
        logger.info("=== SLF4J Basic Logging with Properties Config Demo ===");
        
        // Different log levels - controlled by properties file
        logger.error("ERROR: Critical error occurred");
        logger.warn("WARN: Potential issue detected");
        logger.info("INFO: General information message");
        logger.debug("DEBUG: Detailed debugging information");
        logger.trace("TRACE: Most detailed debugging");
    }
    
    public static void demonstrateParameterizedLogging() {
        logger.info("=== SLF4J Parameterized Logging with Properties Config Demo ===");
        
        String user = "Bob";
        int loginAttempts = 3;
        boolean success = true;
        double processingTime = 125.5;
        
        // Parameterized logging - more efficient than string concatenation
        logger.info("User {} attempted login {} times", user, loginAttempts);
        logger.info("Login status for {}: {}", user, success ? "Success" : "Failed");
        logger.info("Processing completed in {} ms", processingTime);
        
        // Complex parameterized logging
        logger.info("User {} (ID: {}) from {} logged in at {} after {} attempts", 
                   user, "12345", "192.168.1.100", "10:30 AM", loginAttempts);
    }
    
    public static void demonstrateExceptionLogging() {
        logger.info("=== SLF4J Exception Logging with Properties Config Demo ===");
        
        try {
            // Simulate an exception
            String nullString = null;
            int length = nullString.length();
            
        } catch (NullPointerException e) {
            logger.error("NullPointerException caught while processing string", e);
            logger.warn("Failed to process string - message: {}", e.getMessage());
        }
        
        try {
            // Simulate another exception
            int result = 10 / 0;
            
        } catch (ArithmeticException e) {
            logger.error("ArithmeticException occurred during calculation", e);
        }
        
        // Exception with custom message
        try {
            throw new IllegalArgumentException("Invalid parameter value");
        } catch (IllegalArgumentException e) {
            logger.error("Validation failed for input parameter", e);
        }
    }
    
    public static void demonstrateConditionalLogging() {
        logger.info("=== SLF4J Conditional Logging with Properties Config Demo ===");
        
        // Check if log level is enabled before expensive operations
        // These checks work with properties file configuration
        if (logger.isDebugEnabled()) {
            // Expensive operation only performed if debug logging is enabled
            String debugInfo = generateExpensiveDebugInfo();
            logger.debug("Detailed debug information: {}", debugInfo);
        }
        
        // Lambda-based conditional logging (Java 8+)
        logger.debug("Expensive debug info: {}");
        
        // Check for specific levels
        if (logger.isTraceEnabled()) {
            logger.trace("Trace level is enabled - performing detailed analysis");
        }
    }

    
    public static void demonstrateMarkerLogging() {
        logger.info("=== SLF4J Marker Logging with Properties Config Demo ===");
        
        // Create markers for different types of events
        org.slf4j.Marker securityMarker = org.slf4j.MarkerFactory.getMarker("SECURITY");
        org.slf4j.Marker performanceMarker = org.slf4j.MarkerFactory.getMarker("PERFORMANCE");
        org.slf4j.Marker businessMarker = org.slf4j.MarkerFactory.getMarker("BUSINESS");
        
        // Log with markers - can be filtered in properties file
        logger.info(securityMarker, "User authentication successful");
        logger.warn(securityMarker, "Multiple failed login attempts detected");
        
        logger.info(performanceMarker, "Database query executed in 50ms");
        logger.warn(performanceMarker, "Slow database query detected - execution time: 2000ms");
        
        logger.info(businessMarker, "Order #12345 processed successfully");
        logger.error(businessMarker, "Payment processing failed for order #12346");
    }
    
    // Helper method to simulate expensive operation
    private static String generateExpensiveDebugInfo() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append("Debug data ").append(i).append(", ");
        }
        return sb.toString();
    }
    
    public static void demonstrateLoggingBestPractices() {
        logger.info("=== SLF4J Best Practices with Properties Config Demo ===");
        
        // 1. Use appropriate log levels - controlled by properties
        logger.error("Critical system failure - immediate attention required");
        logger.warn("Deprecated API usage - should be updated");
        logger.info("Application startup completed");
        logger.debug("Method entry with parameters: {}", "paramValue");
        logger.trace("Variable state: x={}, y={}", 10, 20);
        
        // 2. Use parameterized logging for performance
        String user = "John";
        int attempts = 5;
        logger.info("User {} made {} login attempts", user, attempts);
        
        // 3. Log exceptions properly
        try {
            throw new RuntimeException("Simulated error");
        } catch (Exception e) {
            logger.error("Error occurred while processing user request", e);
        }
    }
    
    public static void demonstratePropertiesConfiguration() {
        logger.info("=== Properties Configuration Features Demo ===");
        
        // These logging levels are controlled by the properties file
        // Example: logback.properties or log4j.properties
        
        logger.info("This log level is controlled by properties file");
        logger.debug("Debug logging can be enabled/disabled via properties");
        logger.trace("Trace logging can be controlled via properties configuration");
        
        // Pattern and output format controlled by properties
        logger.info("Log pattern and format are configured in properties file");
        
        // Appender configuration controlled by properties
        logger.info("Console and file appenders configured via properties");

    }
    
    public static void main(String[] args) {
        demonstrateBasicLogging();
//        demonstrateParameterizedLogging();
//        demonstrateExceptionLogging();
//        demonstrateConditionalLogging();
//        demonstrateMarkerLogging();
//        demonstrateLoggingBestPractices();
//        demonstratePropertiesConfiguration();
    }
}

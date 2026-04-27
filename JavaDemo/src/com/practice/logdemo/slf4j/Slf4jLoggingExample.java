package com.practice.logdemo.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
//SLF4J (Simple Logging Facade for Java)
public class Slf4jLoggingExample {
    private static final Logger logger = LoggerFactory.getLogger(Slf4jLoggingExample.class);

    public static void demonstrateBasicLogging() {
        logger.info("=== SLF4J Basic Logging Demo ===");
        
        // Different log levels
        logger.error("ERROR: Critical error occurred");
        logger.warn("WARN: Potential issue detected");
        logger.info("INFO: General information message");
        logger.debug("DEBUG: Detailed debugging information");
        logger.trace("TRACE: Most detailed debugging");
    }
    
    public static void demonstrateParameterizedLogging() {
        logger.info("=== SLF4J Parameterized Logging Demo ===");
        
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
        logger.info("=== SLF4J Exception Logging Demo ===");
        
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
        logger.info("=== SLF4J Conditional Logging Demo ===");
        
        // Check if log level is enabled before expensive operations
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
    
    public static void demonstrateMDCLogging() {
        logger.info("=== SLF4J MDC (Mapped Diagnostic Context) Demo ===");
        
        // Clear any existing MDC
        MDC.clear();
        
        // Add context information
        MDC.put("userId", "user123");
        MDC.put("sessionId", "session456");
        MDC.put("requestId", "req789");
        MDC.put("clientIP", "192.168.1.100");
        
        logger.info("User action performed");
        logger.warn("Suspicious activity detected");
        logger.error("Critical error occurred");
        
        // Update MDC values
        MDC.put("userId", "admin");
        MDC.put("operation", "system_maintenance");
        
        logger.info("System maintenance started");
        
        // Clear MDC
        MDC.clear();
        logger.info("MDC cleared - logging without context");
    }
    
    public static void demonstrateMarkerLogging() {
        logger.info("=== SLF4J Marker Logging Demo ===");
        
        // Create markers for different types of events
        org.slf4j.Marker securityMarker = org.slf4j.MarkerFactory.getMarker("SECURITY");
        org.slf4j.Marker performanceMarker = org.slf4j.MarkerFactory.getMarker("PERFORMANCE");
        org.slf4j.Marker businessMarker = org.slf4j.MarkerFactory.getMarker("BUSINESS");
        
        // Log with markers
        logger.info(securityMarker, "User authentication successful");
        logger.warn(securityMarker, "Multiple failed login attempts detected");
        
        logger.info(performanceMarker, "Database query executed in 50ms");
        logger.warn(performanceMarker, "Slow database query detected - execution time: 2000ms");
        
        logger.info(businessMarker, "Order #12345 processed successfully");
        logger.error(businessMarker, "Payment processing failed for order #12346");
    }
    
    public static void demonstrateFluentLogging() {
        logger.info("=== SLF4J Fluent Logging Demo ===");
        
        // Note: Fluent API requires additional dependencies (logback-classic)
        // This is a conceptual example
        
        String user = "Alice";
        String action = "login";
        boolean success = true;
        
        // Standard SLF4J logging (always available)
        logger.info("User {} performed action: {} - Success: {}", user, action, success);
        
        // Demonstrate different logging patterns
        logger.atInfo().log("Simple info message");
        logger.atWarn().addKeyValue("user", user).addKeyValue("action", action).log("User action completed");
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
        logger.info("=== SLF4J Best Practices Demo ===");
        
        // 1. Use appropriate log levels
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
        
        // 4. Include context in log messages
        MDC.put("operation", "user_registration");
        MDC.put("userId", "new_user_123");
        logger.info("User registration completed successfully");
        MDC.clear();
    }
    
    public static void main(String[] args) {
        demonstrateBasicLogging();
        demonstrateParameterizedLogging();
        demonstrateExceptionLogging();
        demonstrateConditionalLogging();
        demonstrateMDCLogging();
        demonstrateMarkerLogging();
        demonstrateFluentLogging();
        demonstrateLoggingBestPractices();
    }
}

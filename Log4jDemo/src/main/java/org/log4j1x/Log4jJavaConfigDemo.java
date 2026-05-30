package org.log4j1x;

import org.apache.log4j.*;
import org.apache.log4j.varia.LevelRangeFilter;

public class Log4jJavaConfigDemo {
    private static final Logger logger = Logger.getLogger(Log4jJavaConfigDemo.class);

    public static void main(String[] args) {
        // Reset configuration to start fresh
        LogManager.resetConfiguration();

        // Configure appenders programmatically
        configureConsoleAppender();
        configureFileAppender();
        configureRollingFileAppender();
        configureDailyRollingFileAppender();

        // Set root logger level
        Logger rootLogger = Logger.getRootLogger();
        rootLogger.setLevel(Level.DEBUG);

        // Demonstrate logging
        demonstrateLogging();
    }

    private static void configureConsoleAppender() {
        // Create ConsoleAppender
        ConsoleAppender consoleAppender = new ConsoleAppender();

        // Create PatternLayout
        PatternLayout layout = new PatternLayout();
        layout.setConversionPattern("%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n");

        // Set layout
        consoleAppender.setLayout(layout);
        consoleAppender.setTarget("System.out");
        consoleAppender.activateOptions();

        // Add to root logger
        Logger.getRootLogger().addAppender(consoleAppender);

        logger.info("ConsoleAppender configured successfully");
    }

    private static void configureFileAppender() {
        // Create FileAppender
        FileAppender fileAppender = null;
        fileAppender = new FileAppender();

        // Create PatternLayout for file
        PatternLayout layout = new PatternLayout();
        layout.setConversionPattern("%d{ISO8601} [%t] %-5p %c{2}:%L - %m%n");

        fileAppender.setLayout(layout);
        fileAppender.setFile("logs/java-config-file.log");
        fileAppender.setAppend(true);
        fileAppender.activateOptions();

        // Add filter to only log INFO and above
        LevelRangeFilter filter = new LevelRangeFilter();
        filter.setLevelMin(Level.INFO);
        filter.activateOptions();
        fileAppender.addFilter(filter);

        Logger.getRootLogger().addAppender(fileAppender);

        logger.info("FileAppender configured successfully");
    }

    private static void configureRollingFileAppender() {
        // Create RollingFileAppender
        RollingFileAppender rollingAppender = new RollingFileAppender();
        
        // Create PatternLayout
        PatternLayout layout = new PatternLayout();
        layout.setConversionPattern("%d{yyyy-MM-dd HH:mm:ss.SSS} %-5p [%t] %c{1}:%L - %m%n");
        
        rollingAppender.setLayout(layout);
        rollingAppender.setFile("logs/java-config-rolling.log");
        rollingAppender.setAppend(true);
        rollingAppender.setMaxFileSize("1MB");
        rollingAppender.setMaxBackupIndex(5);
        rollingAppender.activateOptions();
        
        Logger.getRootLogger().addAppender(rollingAppender);
        
        logger.info("RollingFileAppender configured successfully");
    }

    private static void configureDailyRollingFileAppender() {
        // Create DailyRollingFileAppender
        DailyRollingFileAppender dailyRollingAppender = new DailyRollingFileAppender();
        
        // Create PatternLayout
        PatternLayout layout = new PatternLayout();
        layout.setConversionPattern("%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1} - %m%n");
        
        dailyRollingAppender.setLayout(layout);
        dailyRollingAppender.setFile("logs/java-config-daily.log");
        dailyRollingAppender.setAppend(true);
        dailyRollingAppender.setDatePattern("'.'yyyy-MM-dd");
        dailyRollingAppender.activateOptions();
        
        Logger.getRootLogger().addAppender(dailyRollingAppender);
        
        logger.info("DailyRollingFileAppender configured successfully");
    }

    private static void demonstrateLogging() {
        logger.trace("This is a TRACE message");
        logger.debug("This is a DEBUG message");
        logger.info("This is an INFO message");
        logger.warn("This is a WARN message");
        logger.error("This is an ERROR message");
        logger.fatal("This is a FATAL message");

        // Demonstrate logging with parameters
        String username = "JohnDoe";
        int userId = 12345;
        logger.info("User logged in: " + username + " (ID: " + userId + ")");

        // Demonstrate exception logging
        try {
            int result = 10 / 0;
        } catch (Exception e) {
            logger.error("Division by zero error occurred", e);
        }

        // Demonstrate performance logging
        long startTime = System.currentTimeMillis();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            logger.error("Sleep interrupted", e);
        }
        long endTime = System.currentTimeMillis();
        logger.debug("Operation took " + (endTime - startTime) + "ms");

        logger.info("Log4j 1.x Java-based configuration demonstration completed!");
    }
}

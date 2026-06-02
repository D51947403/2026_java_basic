package org.demo;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.builder.api.*;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;

import java.io.File;

// link to download jar : https://repo.maven.apache.org/maven2/org/apache/logging/log4j/log4j-core/
//API JAR: https://repo.maven.apache.org/maven2/org/apache/logging/log4j/log4j-api/2.26.0/log4j-api-2.26.0.jar
//Core JAR: https://repo.maven.apache.org/maven2/org/apache/logging/log4j/log4j-core/2.26.0/log4j-core-2.26.0.jar
public class Log4j2JavaConfig {
    private static Logger logger;

    public static void main(String[] args) {
        System.out.println("=== Log4j2JavaConfig ===");
        // Initialize Log4j2 with Java-based configuration
        Configuration config = createConfiguration();
        LoggerContext ctx = Configurator.initialize(Log4j2JavaConfig.class.getClassLoader(), config);
        
        // Get logger AFTER configuration is initialized
        logger = LogManager.getLogger(Log4j2JavaConfig.class);

        // Demonstrate logging to all appenders
        demonstrateAllAppenders();

        // Shutdown Log4j2
        Configurator.shutdown(ctx);
    }

    private static Configuration createConfiguration() {
        ConfigurationBuilder<BuiltConfiguration> builder = ConfigurationBuilderFactory.newConfigurationBuilder();

        builder.setStatusLevel(Level.ERROR)
                .setConfigurationName("Log4j2AllAppendersConfig")
                .add(builder.newFilter("ThresholdFilter", Filter.Result.ACCEPT, Filter.Result.NEUTRAL)
                        .addAttribute("level", Level.DEBUG));

        // Console Appender
        AppenderComponentBuilder consoleAppender = builder.newAppender("Console", "CONSOLE")
                .addAttribute("target", ConsoleAppender.Target.SYSTEM_OUT)
                .add(builder.newLayout("PatternLayout")
                        .addAttribute("pattern", "%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"));
        builder.add(consoleAppender);

        // File Appender
        AppenderComponentBuilder fileAppender = builder.newAppender("File", "File")
                .addAttribute("fileName", "logs/log4j2-file.log")
                .addAttribute("append", "false")
                .add(builder.newLayout("PatternLayout")
                        .addAttribute("pattern", "%d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"));
        builder.add(fileAppender);

        // Rolling File Appender
        AppenderComponentBuilder rollingFileAppender = builder.newAppender("RollingFile", "RollingFile")
                .addAttribute("fileName", "logs/log4j2-rolling.log")
                .addAttribute("filePattern", "logs/log4j2-rolling-%d{yyyy-MM-dd}-%i.log")
                .add(builder.newLayout("PatternLayout")
                        .addAttribute("pattern", "%d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"))
                .addComponent(builder.newComponent("Policies")
                        .addComponent(builder.newComponent("TimeBasedTriggeringPolicy")
                                .addAttribute("interval", "1"))
                        .addComponent(builder.newComponent("SizeBasedTriggeringPolicy")
                                .addAttribute("size", "1 KB")))
                .addComponent(builder.newComponent("DefaultRolloverStrategy")
                        .addAttribute("max", "10"));
        builder.add(rollingFileAppender);

        // Daily Rolling File Appender
        AppenderComponentBuilder dailyRollingAppender = builder.newAppender("DailyRollingFile", "RollingFile")
                .addAttribute("fileName", "logs/log4j2-daily.log")
                .addAttribute("filePattern", "logs/log4j2-daily-%d{yyyy-MM-dd}.log")
                .add(builder.newLayout("PatternLayout")
                        .addAttribute("pattern", "%d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"))
                .addComponent(builder.newComponent("Policies")
                        .addComponent(builder.newComponent("TimeBasedTriggeringPolicy")
                                .addAttribute("interval", "1")
                                .addAttribute("modulate", "true")));
        builder.add(dailyRollingAppender);

        // Random Access File Appender
        AppenderComponentBuilder randomAccessFileAppender = builder.newAppender("RandomAccessFile", "RandomAccessFile")
                .addAttribute("fileName", "logs/log4j2-randomaccess.log")
                .addAttribute("append", "true")
                .addAttribute("immediateFlush", "true")
                .add(builder.newLayout("PatternLayout")
                        .addAttribute("pattern", "%d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"));
        builder.add(randomAccessFileAppender);

        // Memory Mapped File Appender
        AppenderComponentBuilder memoryMappedAppender = builder.newAppender("MemoryMappedFile", "MemoryMappedFile")
                .addAttribute("fileName", "logs/log4j2-memorymapped.log")
                .addAttribute("append", "true")
                .addAttribute("immediateFlush", "true")
                .addAttribute("regionLength", 33554432) // 32MB
                .add(builder.newLayout("PatternLayout")
                        .addAttribute("pattern", "%d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"));
        builder.add(memoryMappedAppender);

        // Root Logger with all appenders
        builder.add(builder.newRootLogger(Level.DEBUG)
                .add(builder.newAppenderRef("Console"))
                .add(builder.newAppenderRef("File"))
                .add(builder.newAppenderRef("RollingFile"))
                .add(builder.newAppenderRef("DailyRollingFile"))
                .add(builder.newAppenderRef("RandomAccessFile"))
                .add(builder.newAppenderRef("MemoryMappedFile")));

        return builder.build();
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

        System.out.println("\n=== Log files created in 'logs' directory ===");
        System.out.println("1. Console output (visible above)");
        System.out.println("2. logs/log4j2-file.log");
        System.out.println("3. logs/log4j2-rolling.log");
        System.out.println("4. logs/log4j2-daily.log");
        System.out.println("5. logs/log4j2-randomaccess.log");
        System.out.println("6. logs/log4j2-memorymapped.log");
    }
}

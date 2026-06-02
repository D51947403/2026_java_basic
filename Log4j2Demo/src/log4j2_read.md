# Apache Log4j2 - Brief Overview

## What is Log4j2?

Apache Log4j 2 is an upgrade to Log4j that provides significant improvements over its predecessor such as performance improvements, automatic reloading of configuration files, support for custom log levels, and a plugin architecture.

## Key Features

- **Performance**: Significantly faster than Log4j 1.x and other logging frameworks
- **API Separation**: Clean separation between API and implementation
- **Automatic Configuration**: Supports XML, JSON, YAML, properties, and Java-based configuration
- **Plugins**: Extensible architecture with plugin support
- **Async Logging**: High-performance asynchronous logging
- **Filters**: Fine-grained control over log messages
- **Garbage-free**: Low GC overhead in steady-state logging

## Log Levels (Priority Order)

1. **TRACE** - Most detailed information (lowest priority)
2. **DEBUG** - Detailed debugging information
3. **INFO** - General informational messages
4. **WARN** - Warning messages for potential issues
5. **ERROR** - Error events that might still allow the application to continue
6. **FATAL** - Very severe error events that may cause application termination (highest priority)

## Core Components

### Logger
The main interface for logging operations. Obtained via `LogManager.getLogger()`.

### Appender
Destinations where log events are written. Common appenders include:

- **ConsoleAppender** - Writes to System.out or System.err
- **FileAppender** - Writes to a file
- **RollingFileAppender** - Writes to a file with rollover based on time/size
- **DailyRollingFileAppender** - Rolls file daily
- **RandomAccessFileAppender** - High-performance file writing
- **MemoryMappedFileAppender** - Fastest file I/O using memory mapping
- **SMTPAppender** - Sends logs via email
- **JDBCAppender** - Writes logs to database
- **SyslogAppender** - Sends logs to syslog server
- **KafkaAppender** - Sends logs to Kafka

### Layout
Formats log output. Common layouts:

- **PatternLayout** - Customizable pattern-based formatting
- **JSONLayout** - JSON format output
- **XMLLayout** - XML format output
- **HTMLLayout** - HTML table format

### Filter
Controls which log events are processed based on criteria like level, marker, or custom conditions.

## Configuration Methods

1. **XML** - `log4j2.xml`
2. **JSON** - `log4j2.json`
3. **YAML** - `log4j2.yaml`
4. **Properties** - `log4j2.properties`
5. **Java** - Programmatic configuration using ConfigurationBuilder API

## Basic Usage

```java
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyClass {
    private static final Logger logger = LogManager.getLogger(MyClass.class);
    
    public void doSomething() {
        logger.debug("Debug message");
        logger.info("Info message");
        logger.error("Error occurred", exception);
    }
}
```

## Required Dependencies

- **log4j-api-2.26.0.jar** - API interfaces
- **log4j-core-2.26.0.jar** - Core implementation

## Advantages

- Thread-safe and high-performance
- Supports multiple appenders per logger
- Configurable log levels per logger
- Supports parameterized logging
- Exception stack trace logging
- Context-based logging (MDC, NDC)

## Common Use Cases

- Application logging and debugging
- Error tracking and monitoring
- Audit logging
- Performance monitoring
- Security event logging

## Official Documentation:

https://logging.apache.org/log4j/2.x/

https://logging.apache.org/log4j/2.x/manual/customconfig.html

### Source Code
https://github.com/apache/logging-log4j2/

Latest Log4j 2 version: 2.26.0

Direct download link for binary distribution (contains all JARs):
- https://downloads.apache.org/logging/log4j/2.26.0/apache-log4j-2.26.0-bin.zip

For individual JAR files, use Maven Central:

- Core JAR: https://repo.maven.apache.org/maven2/org/apache/logging/log4j/log4j-core/2.26.0/log4j-core-2.26.0.jar
- API JAR: https://repo.maven.apache.org/maven2/org/apache/logging/log4j/log4j-api/2.26.0/log4j-api-2.26.0.jar

#### Other Log4j2 tutorials:

- https://www.geeksforgeeks.org/advance-java/what-is-log4j2-and-how-to-configure-log4j2-with-java-in-maven-project/

- https://sematext.com/blog/log4j2-tutorial/#log4j-2-appenders

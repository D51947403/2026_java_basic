# Log4j with SLF4J

This project demonstrates the integration of Log4j with SLF4J (Simple Logging Facade for Java).

## Overview

- **SLF4J**: A facade or abstraction layer for various logging frameworks including Log4j, Logback, and java.util.logging
- **Log4j**: A popular logging framework that provides flexible and powerful logging capabilities

## Benefits of Using SLF4J

- **Abstraction**: Write code against the SLF4J API, switch logging implementations without changing code
- **Performance**: Parameterized logging reduces string concatenation overhead
- **Flexibility**: Easy to switch between different logging frameworks

## Configuration

The logging configuration is defined in `log4j.properties` located in `src/main/resources/`.

## Usage Example

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Example {
    private static final Logger logger = LoggerFactory.getLogger(Example.class);
    
    public void doSomething() {
        logger.info("Processing started");
        logger.debug("Debug information: {}", someVariable);
        logger.error("Error occurred", exception);
    }
}
```

## Log Files

- `application.log` - General application logs
- `error.log` - Error-level logs
- `security.log` - Security-related logs
- `performance.log` - Performance metrics

# JUL (Java Util Logging) Examples

This package contains examples demonstrating the Java Util Logging (JUL) framework.

## Files

- `GlobalCustomFormatter.java` - Custom formatter for consistent log output formatting across different logging configurations
- `JulLoggingExample.java` - Basic JUL logging demonstration with custom formatter, file/console handlers, and comprehensive logging examples
- `JulLoggingPropertiesConfig.java` - Demonstrates JUL configuration using properties files with fallback mechanisms and comprehensive logging examples
- `JulLoggingXmlConfig.java` - Shows JUL configuration using XML files with custom formatter integration and exception handling examples

## Overview

Java Util Logging (JUL) is the built-in logging framework in Java. It provides:

- Multiple logging levels (SEVERE, WARNING, INFO, CONFIG, FINE, FINER, FINEST)
- Console and file handlers
- Formatters for customizing log output
- Hierarchical logger configuration

## Official Documentation

For comprehensive documentation, refer to:
- [Oracle Java Logging Overview](https://docs.oracle.com/javase/8/docs/technotes/guides/logging/overview.html)
- [Oracle Java Logging Guide](https://docs.oracle.com/javase/8/docs/technotes/guides/logging/)

## Class Summaries

### GlobalCustomFormatter
A standalone custom formatter that extends `java.util.logging.Formatter`. Provides consistent log formatting with the pattern `[timestamp] [level] logger-name: message`. Used across different logging configurations to maintain uniform output format.

### JulLoggingExample
Basic JUL logging demonstration class that showcases fundamental JUL features. Includes:
- Custom formatter implementation for enhanced log output
- File and console handler configuration
- Comprehensive logging examples (basic logging, parameter logging, exception handling, method logging, logger hierarchy)
- Demonstrates JUL's built-in method entry/exit logging capabilities

### JulLoggingPropertiesConfig
Demonstrates JUL configuration using properties files (`logging.properties`). Features include:
- Automatic configuration loading from classpath
- Fallback configuration with console and file handlers
- Comprehensive logging demonstrations (basic logging, configuration features, parameter logging, exception handling)
- File rotation testing and logger configuration inspection

### JulLoggingXmlConfig
Shows JUL configuration using XML files (`logging.xml`). Features include:
- XML-based configuration loading with fallback mechanisms
- Integration with `GlobalCustomFormatter` for consistent output
- Exception handling demonstrations
- XML-specific configuration testing and handler inspection

## Quick Start

```java
import java.util.logging.Logger;
import java.util.logging.Level;

// Get a logger
Logger logger = Logger.getLogger(MyClass.class.getName());

// Log messages at different levels
logger.info("Information message");
logger.warning("Warning message");
logger.severe("Error message");
```

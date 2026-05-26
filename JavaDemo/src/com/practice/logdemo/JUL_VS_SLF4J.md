# Java Logging Examples

This package contains comprehensive examples of Java logging frameworks and best practices.

## Files Overview

### `JulLoggingExample.java`
Complete examples of Java Util Logging (JUL) - the built-in Java logging framework.

### `Slf4jLoggingExample.java`
Comprehensive SLF4J examples - the industry-standard logging facade.

### `README.md`
This documentation file.

## Logging Frameworks Comparison

| Feature | JUL (Java Util Logging) | SLF4J |
|---------|-------------------------|-------|
| **Dependencies** | Built into Java SE | External (slf4j-api.jar) |
| **Performance** | Good | Excellent (parameterized logging) |
| **Configuration** | Programmatic & properties | Multiple formats (XML, JSON, Groovy) |
| **Features** | Basic | Rich (MDC, Markers, Fluent API) |
| **Implementations** | Single | Multiple (Logback, Log4j2, etc.) |
| **Industry Adoption** | Limited | Widely adopted |

## Log Levels Hierarchy

### JUL
```
SEVERE > WARNING > INFO > CONFIG > FINE > FINER > FINEST
```

### SLF4J
```
ERROR > WARN > INFO > DEBUG > TRACE
```

## Key Concepts Demonstrated

### Java Util Logging (JUL)
- **Basic Logging**: Different log levels and hierarchy
- **File & Console Handlers**: Output to multiple destinations
- **Custom Formatting**: Timestamps, logger names, custom patterns
- **Exception Logging**: Stack trace capture and formatting
- **Method Logging**: `entering()` and `exiting()` for method tracking
- **Logger Hierarchy**: Parent-child logger relationships

### SLF4J
- **Parameterized Logging**: `{}` placeholders for better performance
- **MDC (Mapped Diagnostic Context)**: Request tracking and context
- **Markers**: Categorizing log events (SECURITY, PERFORMANCE, BUSINESS)
- **Conditional Logging**: Avoiding expensive operations
- **Fluent API**: Structured logging with key-value pairs
- **Best Practices**: Appropriate level usage and exception handling

## Usage Examples

### Running the Examples
```bash
# Run the main demonstration
java com.practice.logdemo.LoggingDemo

# Run individual framework examples
java com.practice.logdemo.JulLoggingExample
java com.practice.logdemo.Slf4jLoggingExample
```

### Basic JUL Usage
```java
import java.util.logging.Logger;

private static final Logger logger = Logger.getLogger(MyClass.class.getName());

logger.info("Information message");
logger.warning("Warning message");
logger.severe("Error message");
```

### Basic SLF4J Usage
```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

private static final Logger logger = LoggerFactory.getLogger(MyClass.class);

logger.info("Information message");
logger.warn("Warning message");
logger.error("Error message");

// Parameterized logging
logger.info("User {} logged in at {}", username, timestamp);
```

## Configuration

### JUL Configuration
Can be configured via:
- Programmatic configuration (shown in examples)
- `logging.properties` file
- JVM system properties

### SLF4J Configuration
Requires implementation (e.g., Logback):
- `logback.xml` configuration file
- Programmatic configuration
- Environment variables

## Best Practices

1. **Use Appropriate Log Levels**
   - `ERROR`: Critical failures requiring immediate attention
   - `WARN`: Potential issues that don't stop execution
   - `INFO`: Important application events
   - `DEBUG`: Detailed information for troubleshooting
   - `TRACE`: Most detailed information (development only)

2. **Parameterized Logging**
   - Use `{}` placeholders instead of string concatenation
   - Avoids string creation when logging is disabled

3. **Exception Logging**
   - Always include the exception object as the last parameter
   - Provide meaningful context in the log message

4. **Context Information**
   - Use MDC for request tracking
   - Include relevant identifiers (user ID, session ID, etc.)

5. **Performance Considerations**
   - Check log level before expensive operations
   - Use conditional logging for complex computations

## Dependencies

### For JUL
No external dependencies required (built into Java SE).

### For SLF4J
Add to your project:
```xml
<!-- Maven -->
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>2.0.7</version>
</dependency>

<!-- Implementation (e.g., Logback) -->
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.4.8</version>
</dependency>
```

## Output Files

When running `JulLoggingExample`, a log file `jul_logging.log` will be created in the current directory containing all log messages.

## Further Reading

- [SLF4J Manual](http://www.slf4j.org/manual.html)
- [Java Util Logging Guide](https://docs.oracle.com/javase/8/docs/technotes/guides/logging/)
- [Logback Documentation](http://logback.qos.ch/documentation.html)

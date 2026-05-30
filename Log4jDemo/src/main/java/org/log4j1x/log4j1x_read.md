# Apache Log4j 1.x Documentation

## Overview
Log4j is a popular Java-based logging utility from Apache. 
It's used for logging application events and messages to various outputs like consoles, 
files, databases, and more.

## Key Features

- Thread-safe logging
- Multiple logging levels (TRACE, DEBUG, INFO, WARN, ERROR, FATAL)
- Configurable output formats and destinations
- Runtime configuration without code changes
- Support for multiple appenders (console, file, rolling files, etc.)

## Common Use Cases

- Debugging applications
- Tracking application behavior
- Error monitoring and reporting
- Audit logging

## Configuration Methods

Log4j 1.x supports three main configuration approaches:

### 1. Properties File Configuration
- Simple key-value format
- Use `PropertyConfigurator.configure("log4j.properties")`
- Best for basic configurations
- Example: `Log4jPropertiesFileDemo.java`

### 2. XML Configuration
- Hierarchical XML structure
- Use `DOMConfigurator.configure("log4j.xml")`
- Supports complex configurations with filters and advanced features
- Example: `Log4jXmlConfigDemo.java`

### 3. Java Programmatic Configuration
- Configure using Java code
- Use `LogManager.resetConfiguration()` to start fresh
- Best for dynamic/runtime configuration
- Example: `Log4jJavaConfigDemo.java`

**Note**: You can use multiple configuration methods in the same project. Each class can load a different configuration file depending on its specific requirements.

## Logging Levels

Log4j provides the following logging levels (in order of priority):

- **TRACE**: Most detailed information, typically for debugging
- **DEBUG**: Fine-grained informational events for debugging
- **INFO**: General informational messages
- **WARN**: Potentially harmful situations
- **ERROR**: Error events that might still allow the application to continue
- **FATAL**: Very severe error events that may cause the application to abort

## Appenders

Appenders define where log messages are sent:

- **ConsoleAppender**: Outputs to the console (System.out or System.err)
- **FileAppender**: Writes to a file
- **RollingFileAppender**: Rolls over files based on size
- **DailyRollingFileAppender**: Rolls over files based on date
- **SMTPAppender**: Sends logs via email
- **JDBCAppender**: Writes to a database

## Layouts

Layouts define the format of log messages:

- **PatternLayout**: Customizable pattern format
- **SimpleLayout**: Simple text format
- **HTMLLayout**: HTML table format
- **XMLLayout**: XML format

Common pattern symbols:
- `%d`: Date/time
- `%p`: Priority/level
- `%c`: Category/logger name
- `%m`: Message
- `%n`: Line separator
- `%L`: Line number
- `%t`: Thread name

## Project Structure

This project demonstrates all three configuration methods:

- `Log4jPropertiesFileDemo.java` - Properties file configuration
- `Log4jXmlConfigDemo.java` - XML file configuration
- `Log4jJavaConfigDemo.java` - Java programmatic configuration

Each demo class can be run independently to test different configuration approaches.

## References

- [Apache Log4j 1.x Documentation](https://logging.apache.org/log4j/1.x/index.html)
- [GeeksforGeeks - Apache Log4j](https://www.geeksforgeeks.org/java/apache-log4j/)
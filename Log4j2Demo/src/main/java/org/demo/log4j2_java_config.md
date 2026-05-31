## Log4j2 Java-Based Configuration

Log4j2 can be configured programmatically using Java code as well as XML, JSON, YAML, or properties files. This approach provides type safety and compile-time checking.

### Required Dependencies

- **log4j-api-2.26.0.jar** - API interfaces and classes
-  https://repo.maven.apache.org/maven2/org/apache/logging/log4j/log4j-core/2.26.0/log4j-core-2.26.0.jar
- **log4j-core-2.26.0.jar** - Core implementation and appenders
-  https://repo.maven.apache.org/maven2/org/apache/logging/log4j/log4j-api/2.26.0/log4j-api-2.26.0.jar

### ConfigurationBuilder API

The `ConfigurationBuilder` API allows you to build Log4j2 configurations programmatically:

```java
import org.apache.logging.log4j.core.config.builder.api.*;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;

ConfigurationBuilder<BuiltConfiguration> builder = ConfigurationBuilderFactory.newConfigurationBuilder();
```

### Key Components

#### 1. Configuration Setup
```java
builder.setStatusLevel(Level.ERROR)
        .setConfigurationName("MyConfig")
        .add(builder.newFilter("ThresholdFilter", Filter.Result.ACCEPT, Filter.Result.NEUTRAL)
                .addAttribute("level", Level.DEBUG));
```

#### 2. Appenders

**Console Appender** - Outputs to console:
```java
AppenderComponentBuilder consoleAppender = builder.newAppender("Console", "CONSOLE")
        .addAttribute("target", ConsoleAppender.Target.SYSTEM_OUT)
        .add(builder.newLayout("PatternLayout")
                .addAttribute("pattern", "%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"));
builder.add(consoleAppender);
```

**File Appender** - Writes to a single file:
```java
AppenderComponentBuilder fileAppender = builder.newAppender("File", "File")
        .addAttribute("fileName", "logs/application.log")
        .addAttribute("append", "false")
        .add(builder.newLayout("PatternLayout")
                .addAttribute("pattern", "%d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"));
builder.add(fileAppender);
```

**Rolling File Appender** - Rolls based on time and size:
```java
AppenderComponentBuilder rollingFileAppender = builder.newAppender("RollingFile", "RollingFile")
        .addAttribute("fileName", "logs/rolling.log")
        .addAttribute("filePattern", "logs/rolling-%d{yyyy-MM-dd}-%i.log")
        .add(builder.newLayout("PatternLayout")
                .addAttribute("pattern", "%d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"))
        .addComponent(builder.newComponent("Policies")
                .addComponent(builder.newComponent("TimeBasedTriggeringPolicy")
                        .addAttribute("interval", "1"))
                .addComponent(builder.newComponent("SizeBasedTriggeringPolicy")
                        .addAttribute("size", "10 MB")))
        .addComponent(builder.newComponent("DefaultRolloverStrategy")
                .addAttribute("max", "10"));
builder.add(rollingFileAppender);
```

**Daily Rolling File Appender** - Rolls daily:
```java
AppenderComponentBuilder dailyRollingAppender = builder.newAppender("DailyRollingFile", "RollingFile")
        .addAttribute("fileName", "logs/daily.log")
        .addAttribute("filePattern", "logs/daily-%d{yyyy-MM-dd}.log")
        .add(builder.newLayout("PatternLayout")
                .addAttribute("pattern", "%d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"))
        .addComponent(builder.newComponent("Policies")
                .addComponent(builder.newComponent("TimeBasedTriggeringPolicy")
                        .addAttribute("interval", "1")
                        .addAttribute("modulate", "true")));
builder.add(dailyRollingAppender);
```

**Random Access File Appender** - High-performance file writing:
```java
AppenderComponentBuilder randomAccessFileAppender = builder.newAppender("RandomAccessFile", "RandomAccessFile")
        .addAttribute("fileName", "logs/randomaccess.log")
        .addAttribute("append", "true")
        .addAttribute("immediateFlush", "false")
        .add(builder.newLayout("PatternLayout")
                .addAttribute("pattern", "%d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"));
builder.add(randomAccessFileAppender);
```

**Memory Mapped File Appender** - Fastest file I/O using memory mapping:
```java
AppenderComponentBuilder memoryMappedAppender = builder.newAppender("MemoryMappedFile", "MemoryMappedFile")
        .addAttribute("fileName", "logs/memorymapped.log")
        .addAttribute("append", "true")
        .addAttribute("immediateFlush", "false")
        .addAttribute("regionLength", 33554432) // 32MB
        .add(builder.newLayout("PatternLayout")
                .addAttribute("pattern", "%d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"));
builder.add(memoryMappedAppender);
```

#### 3. Logger Configuration

```java
// Root Logger
builder.add(builder.newRootLogger(Level.DEBUG)
        .add(builder.newAppenderRef("Console"))
        .add(builder.newAppenderRef("File"))
        .add(builder.newAppenderRef("RollingFile")));

// Named Logger
builder.add(builder.newLogger("com.example", Level.DEBUG)
        .add(builder.newAppenderRef("Console"))
        .add(builder.newAppenderRef("File"))
        .addAttribute("additivity", false));
```

#### 4. Initialize Configuration

```java
Configuration config = builder.build();
Configurator.initialize(config);
LoggerContext ctx = Configurator.initialize(YourClass.class.getClassLoader(), config);
```

#### 5. Shutdown

```java
Configurator.shutdown(ctx);
```

### Complete Example

See `Log4j2JavaConfig.java` for a complete working example demonstrating all appenders with Java-based configuration.

### Advantages of Java Configuration

- **Type Safety**: Compile-time checking of configuration
- **IDE Support**: Auto-completion and refactoring
- **Dynamic Configuration**: Can change configuration at runtime
- **No External Files**: Configuration is part of the codebase
- **Easier Testing**: Can create different configurations for tests

### Pattern Layout Patterns

- `%d{HH:mm:ss.SSS}` - Date and time
- `[%t]` - Thread name
- `%-5level` - Log level (5 characters, left-aligned)
- `%logger{36}` - Logger name (abbreviated to 36 characters)
- `%msg` - Log message
- `%n` - Line separator
- `%ex` - Exception stack trace
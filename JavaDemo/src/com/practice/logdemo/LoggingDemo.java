package com.practice.logdemo;

public class LoggingDemo {
    
    public static void main(String[] args) {
        System.out.println("=== Java Logging Framework Comparison ===");
        
        System.out.println("\n1. Java Util Logging (JUL) Demonstration:");
        System.out.println("=========================================");
        JulLoggingExample.main(new String[]{});
        
        System.out.println("\n\n2. SLF4J Logging Demonstration:");
        System.out.println("===============================");
        Slf4jLoggingExample.main(new String[]{});
        
        System.out.println("\n\n=== Key Differences ===");
        System.out.println("JUL (Java Util Logging):");
        System.out.println("- Built into Java SE");
        System.out.println("- No external dependencies");
        System.out.println("- Less feature-rich");
        System.out.println("- String concatenation for parameters");
        
        System.out.println("\nSLF4J (Simple Logging Facade for Java):");
        System.out.println("- Industry standard");
        System.out.println("- Requires external dependencies");
        System.out.println("- Rich feature set (MDC, Markers, etc.)");
        System.out.println("- Parameterized logging for better performance");
        System.out.println("- Works with multiple implementations (Logback, Log4j2)");
    }
}

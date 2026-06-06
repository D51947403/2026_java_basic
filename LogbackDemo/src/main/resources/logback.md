# Logback Official Documentation
https://logback.qos.ch/
https://logback.qos.ch/manual/architecture.html
https://logback.qos.ch/access.html

### What is Logback?

Logback is a logging framework for Java applications created by Ceki Gülcü, the original founder of the popular Log4j project. 
Logback was designed from the ground up to be a successor to Log4j 1.x, addressing many of its architectural shortcomings 
while improving performance.
The default logging framework used in Spring Boot is **Logback**.

### Logback's architecture
Logback is divided into three modules:

- logback-core: The groundwork module that other modules build upon.
- logback-classic: The module that extends core. Significantly, it implements the SLF4J (Simple Logging Facade for Java) API natively.
- logback-access: A module that integrates with Servlet containers (like Tomcat or Jetty) to provide HTTP-access log functionality.

### Default configuration files: 
- logback-test.xml
- logback.xml

### Required dependencies
- logback-core
- logback-classic
- slf4j-api

### Optional dependencies
- logback-access

## Logback Tutorial
https://medium.com/@kaustubh.saha/a-deep-dive-into-the-logback-framework-90163e3ce3a6
https://howtodoinjava.com/logback/logback-tutorial/
https://www.baeldung.com/logback

# To download any jar 
https://central.sonatype.com/
- Search for jar 
- Go version section
- Click on browser icon

For Logback specific jar
  https://repo1.maven.org/maven2/ch/qos/logback/
For SLF4j specific jar
  https://repo1.maven.org/maven2/org/slf4j/

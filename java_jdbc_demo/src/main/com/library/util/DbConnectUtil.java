package main.com.library.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnectUtil {

    private static Properties properties = new Properties();
    private static final String PROPERTIES_FILE = "D:\\2026_java_notes\\database_secret\\application.properties";
    static {
        loadProperties();
    }
    
    private static void loadProperties() {
        try (FileInputStream fis = new FileInputStream(PROPERTIES_FILE)) {
            properties.load(fis);
            System.out.println("Properties file loaded successfully from: " + PROPERTIES_FILE);
        } catch (IOException e) {
            System.err.println("Error loading properties file: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        // Load and register the driver
        Class.forName(properties.getProperty("db.driver"));
        
        // Get connection details from properties
        String url = properties.getProperty("db.url");
        String username = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");
        
        // Establish connection
        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println("Connection Established successfully to: " + url);
        
        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection Closed....");
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
    
    // Utility method to get property value
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    // Utility method to reload properties
    public static void reloadProperties() {
        loadProperties();
        System.out.println("Properties reloaded successfully");
    }
}

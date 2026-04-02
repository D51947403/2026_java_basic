package main.com.library.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/library_db"; // Database details
    private static final  String USER_NAME = "*****"; // MySQL credentials
    private static final String PASSWORD = "*****";//MySQL credentials
    private static final String MYSQL_DRIVER="com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        // Load and register the driver
        Class.forName(MYSQL_DRIVER);
        
        // Establish connection
        Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        System.out.println("Connection Established successfully");
        
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
}

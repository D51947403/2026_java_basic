package main.com.library.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectUtil {

    private static final String url = "jdbc:mysql://localhost:3306/library_db"; // Database details
    private static final  String username = "********"; // MySQL credentials
    private static final String password = "**********";//MySQL credentials
    private static final String mySqlDriver="com.mysql.cj.jdbc.Driver";
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        // Load and register the driver
        Class.forName(mySqlDriver);
        
        // Establish connection
        Connection connection = DriverManager.getConnection(url, username, password);
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

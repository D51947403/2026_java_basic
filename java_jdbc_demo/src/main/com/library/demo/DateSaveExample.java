package main.com.library.demo;

import main.com.library.util.DateUtility;
import main.com.library.util.DbConnectUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class DateSaveExample {
    
    public static void main(String[] args) {
        DateSaveExample example = new DateSaveExample();
        
        // Example 1: Save current date
        Date currentDate = new Date();
        example.saveStudentWithDate("John Doe", "john@example.com", currentDate);
        
        // Example 2: Save custom date using DateUtility
        Date customDate = DateUtility.getDateFromCustomString("2023-12-25");
        example.saveStudentWithDate("Jane Smith", "jane@example.com", customDate);
    }
    
    public void saveStudentWithDate(String studentName, String studentEmail, Date updated_date) {
        Connection connection = null;
        Statement statement = null;
        
        try {
            connection = DbConnectUtil.getConnection();
            statement = connection.createStatement();
            
            // Method 1: Using java.sql.Date (Recommended for Statement)
            java.sql.Date sqlDate = new java.sql.Date(updated_date.getTime());
            
            // Method 2: Using formatted string (alternative approach)
            String formattedDate = DateUtility.getCustomStringFromDate(updated_date);
            
            // SQL query with date using java.sql.Date
            String insertQuery = "INSERT INTO student_detail (student_name, student_email, updated_date) " +
                               "VALUES ('" + studentName + "', '" + studentEmail + "', '" + sqlDate + "')";
            
            // Alternative: Using formatted string
            // String insertQuery = "INSERT INTO student_detail (student_name, student_email, updated_date) " +
            //                    "VALUES ('" + studentName + "', '" + studentEmail + "', '" + formattedDate + "')";
            
            int rowsAffected = statement.executeUpdate(insertQuery, Statement.RETURN_GENERATED_KEYS);
            
            if (rowsAffected > 0) {
                System.out.println("Student saved successfully with updated_date date: " + sqlDate);
                
                // Get generated key
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    System.out.println("Generated student ID: " + generatedKeys.getInt(1));
                }
            } else {
                System.out.println("Failed to save student");
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error saving student: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Clean up resources
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                System.err.println("Error closing statement: " + e.getMessage());
            }
            DbConnectUtil.closeConnection(connection);
        }
    }
    
    /**
     * Alternative method using PreparedStatement (Better approach for dates)
     */
    public void saveStudentWithDatePreparedStatement(String studentName, String studentEmail, Date updated_date) {
        Connection connection = null;
        java.sql.PreparedStatement preparedStatement = null;
        
        try {
            connection = DbConnectUtil.getConnection();
            
            String insertQuery = "INSERT INTO student_detail (student_name, student_email, updated_date) " +
                               "VALUES (?, ?, ?)";
            
            preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            
            // Set parameters
            preparedStatement.setString(1, studentName);
            preparedStatement.setString(2, studentEmail);
            preparedStatement.setDate(3, new java.sql.Date(updated_date.getTime()));
            
            int rowsAffected = preparedStatement.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Student saved successfully using PreparedStatement");
                
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    System.out.println("Generated student ID: " + generatedKeys.getInt(1));
                }
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error saving student: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) {
                System.err.println("Error closing PreparedStatement: " + e.getMessage());
            }
            DbConnectUtil.closeConnection(connection);
        }
    }
}

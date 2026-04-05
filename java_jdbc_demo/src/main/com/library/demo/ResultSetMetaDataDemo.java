package main.com.library.demo;

import main.com.library.util.DbConnectUtil;

import java.sql.*;

public class ResultSetMetaDataDemo {

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
            // Get database connection
            connection = DbConnectUtil.getConnection();
            
            // Create statement
            statement = connection.createStatement();
            
            // Execute query
            String sql = "SELECT * FROM admin_detail";
            resultSet = statement.executeQuery(sql);
            
            // Get ResultSetMetaData
            ResultSetMetaData metaData = resultSet.getMetaData();
            
            // Display metadata information
            System.out.println("=== ResultSet Metadata for admin_detail table ===");
            System.out.println();
            
            // Table information
            System.out.println("Table Information:");
            System.out.println("Table Name: " + metaData.getTableName(1));
            System.out.println("Schema Name: " + metaData.getSchemaName(1));
            System.out.println("Catalog Name: " + metaData.getCatalogName(1));
            System.out.println();
            
            // Column count
            int columnCount = metaData.getColumnCount();
            System.out.println("Total Columns: " + columnCount);
            System.out.println();
            
            // Column details
            System.out.println("Column Details:");
            System.out.println("--------------------------------------------------------------------------------------------------");
            System.out.printf("%-5s | %-20s | %-15s | %-15s | %-10s | %-15s | %-10s%n", 
                "Pos", "Column Name", "Data Type", "Type Name", "Size", "Nullable", "Auto Inc");
            System.out.println("--------------------------------------------------------------------------------------------------");
            
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                int dataType = metaData.getColumnType(i);
                String typeName = metaData.getColumnTypeName(i);
                int columnSize = metaData.getColumnDisplaySize(i);
                boolean nullable = metaData.isNullable(i) == ResultSetMetaData.columnNullable;
                boolean autoIncrement = metaData.isAutoIncrement(i);
                
                System.out.printf("%-5d | %-20s | %-15d | %-15s | %-10d | %-15s | %-10s%n",
                    i,
                    columnName,
                    dataType,
                    typeName,
                    columnSize,
                    nullable ? "YES" : "NO",
                    autoIncrement ? "YES" : "NO"
                );
            }
            System.out.println("--------------------------------------------------------------------------------------------------");
            System.out.println();
            
            // Display actual data
            System.out.println("Actual Data from admin_detail table:");
            System.out.println("----------------------------------------");
            
            // Print column headers
            for (int i = 1; i <= columnCount; i++) {
                System.out.printf("%-20s", metaData.getColumnName(i));
            }
            System.out.println();
            
            // Print separator line
            for (int i = 1; i <= columnCount; i++) {
                System.out.printf("%-20s", "--------------------");
            }
            System.out.println();
            
            // Print data rows
            int rowCount = 0;
            while (resultSet.next()) {
                rowCount++;
                for (int i = 1; i <= columnCount; i++) {
                    Object value = resultSet.getObject(i);
                    System.out.printf("%-20s", value != null ? value.toString() : "NULL");
                }
                System.out.println();
            }
            System.out.println("----------------------------------------");
            System.out.println("Total Rows: " + rowCount);
            System.out.println();
            
            // Additional metadata information
            System.out.println("Additional Metadata Information:");
            System.out.println("---------------------------------");
            
            for (int i = 1; i <= columnCount; i++) {
                System.out.println("Column " + i + ": " + metaData.getColumnName(i));
                System.out.println("  - Precision: " + metaData.getPrecision(i));
                System.out.println("  - Scale: " + metaData.getScale(i));
                System.out.println("  - Case Sensitive: " + metaData.isCaseSensitive(i));
                System.out.println("  - Searchable: " + metaData.isSearchable(i));
                System.out.println("  - Currency: " + metaData.isCurrency(i));
                System.out.println("  - Signed: " + metaData.isSigned(i));
                System.out.println("  - Read Only: " + metaData.isReadOnly(i));
                System.out.println("  - Writable: " + metaData.isWritable(i));
                System.out.println("  - Definitely Writable: " + metaData.isDefinitelyWritable(i));
                System.out.println();
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) DbConnectUtil.closeConnection(connection);
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
    }
}

package main.com.library.demo;

import main.com.library.util.DbConnectUtil;

import java.sql.*;

public class DatabaseMetaDataDemo {
    public static void main(String[] args) {
        Connection connection = null;
        
        try {
            // Get database connection
            connection = DbConnectUtil.getConnection();
            
            // Get DatabaseMetaData
            DatabaseMetaData dbMetaData = connection.getMetaData();
            
            // Display database information
            System.out.println("=== Database Metadata Information ===");
            System.out.println();
            
            // Basic database information
            System.out.println("Basic Database Information:");
            System.out.println("--------------------------");
            System.out.println("Database Product Name: " + dbMetaData.getDatabaseProductName());
            System.out.println("Database Product Version: " + dbMetaData.getDatabaseProductVersion());
            System.out.println("Database Major Version: " + dbMetaData.getDatabaseMajorVersion());
            System.out.println("Database Minor Version: " + dbMetaData.getDatabaseMinorVersion());
            System.out.println();
            
            // Driver information
            System.out.println("Driver Information:");
            System.out.println("------------------");
            System.out.println("Driver Name: " + dbMetaData.getDriverName());
            System.out.println("Driver Version: " + dbMetaData.getDriverVersion());
            System.out.println("Driver Major Version: " + dbMetaData.getDriverMajorVersion());
            System.out.println("Driver Minor Version: " + dbMetaData.getDriverMinorVersion());
            System.out.println();
            
            // Connection information
            System.out.println("Connection Information:");
            System.out.println("----------------------");
            System.out.println("URL: " + dbMetaData.getURL());
            System.out.println("Username: " + dbMetaData.getUserName());
            System.out.println("Transaction Isolation: " + connection.getTransactionIsolation());
            System.out.println("Auto Commit: " + connection.getAutoCommit());
            System.out.println("ReadOnly: " + connection.isReadOnly());
            System.out.println();
            
            // Database capabilities
            System.out.println("Database Capabilities:");
            System.out.println("---------------------");
            System.out.println("Supports Transactions: " + dbMetaData.supportsTransactions());
            System.out.println("Supports Savepoints: " + dbMetaData.supportsSavepoints());
            System.out.println("Supports Batch Updates: " + dbMetaData.supportsBatchUpdates());
            System.out.println("Supports Named Parameters: " + dbMetaData.supportsNamedParameters());
            System.out.println("Supports Multiple Result Sets: " + dbMetaData.supportsMultipleResultSets());
            System.out.println("Supports GetGeneratedKeys: " + dbMetaData.supportsGetGeneratedKeys());
            System.out.println();
            
            // SQL capabilities
            System.out.println("SQL Capabilities:");
            System.out.println("-----------------");
            System.out.println("ANSI SQL Level: " + getSQLLevel(dbMetaData));
            System.out.println("Supports Outer Joins: " + dbMetaData.supportsOuterJoins());
            System.out.println("Supports Full Outer Joins: " + dbMetaData.supportsFullOuterJoins());
            System.out.println("Supports Limited Outer Joins: " + dbMetaData.supportsLimitedOuterJoins());
            System.out.println("Supports Subqueries: " + dbMetaData.supportsSubqueriesInComparisons());
            System.out.println("Supports Union: " + dbMetaData.supportsUnion());
            System.out.println("Supports Union All: " + dbMetaData.supportsUnionAll());
            System.out.println("Supports Group By: " + dbMetaData.supportsGroupBy());
            System.out.println("Supports Group By Beyond Select: " + dbMetaData.supportsGroupByBeyondSelect());
            System.out.println("Supports Like Escape Clause: " + dbMetaData.supportsLikeEscapeClause());
            System.out.println();
            
            // Limits and constraints
            System.out.println("Database Limits:");
            System.out.println("---------------");
            System.out.println("Max Connections: " + dbMetaData.getMaxConnections());
            System.out.println("Max Statements: " + dbMetaData.getMaxStatements());
            System.out.println("Max Table Name Length: " + dbMetaData.getMaxTableNameLength());
            System.out.println("Max Column Name Length: " + dbMetaData.getMaxColumnNameLength());
            System.out.println("Max Columns in Table: " + dbMetaData.getMaxColumnsInTable());
            System.out.println("Max Row Size: " + dbMetaData.getMaxRowSize());
            System.out.println("Max Statement Length: " + dbMetaData.getMaxStatementLength());
            System.out.println();
            
            // List all tables
            System.out.println("Database Tables:");
            System.out.println("---------------");
            ResultSet tables = dbMetaData.getTables(null, null, "%", new String[]{"TABLE"});
            while (tables.next()) {
                System.out.println("Catalog: " + tables.getString("TABLE_CAT") +
                    ", Schema: " + tables.getString("TABLE_SCHEM") +
                    ", Table: " + tables.getString("TABLE_NAME") +
                    ", Type: " + tables.getString("TABLE_TYPE"));
            }
            tables.close();
            System.out.println();
            
            // List all schemas
            System.out.println("Database Schemas:");
            System.out.println("-----------------");
            ResultSet schemas = dbMetaData.getSchemas();
            while (schemas.next()) {
                System.out.println("Schema: " + schemas.getString("TABLE_SCHEM"));
            }
            schemas.close();
            System.out.println();
            
            // Column information for a specific table
            System.out.println("Column Information for admin_detail:");
            System.out.println("------------------------------------");
            ResultSet columns = dbMetaData.getColumns(null, null, "admin_detail", "%");
            System.out.printf("%-20s | %-15s | %-15s | %-10s | %-10s | %-10s%n", 
                "Column Name", "Data Type", "Type Name", "Size", "Nullable", "Default");
            System.out.println("--------------------------------------------------------------------------------");
            
            while (columns.next()) {
                System.out.printf("%-20s | %-15d | %-15s | %-10d | %-10s | %-10s%n",
                    columns.getString("COLUMN_NAME"),
                    columns.getInt("DATA_TYPE"),
                    columns.getString("TYPE_NAME"),
                    columns.getInt("COLUMN_SIZE"),
                    columns.getString("IS_NULLABLE"),
                    columns.getString("COLUMN_DEF")
                );
            }
            columns.close();
            System.out.println();
            
            // Primary key information
            System.out.println("Primary Key Information:");
            System.out.println("-------------------------");
            ResultSet primaryKeys = dbMetaData.getPrimaryKeys(null, null, "admin_detail");
            while (primaryKeys.next()) {
                System.out.println("Column: " + primaryKeys.getString("COLUMN_NAME") +
                    ", Key Seq: " + primaryKeys.getString("KEY_SEQ") +
                    ", PK Name: " + primaryKeys.getString("PK_NAME"));
            }
            primaryKeys.close();
            System.out.println();
            
            // Foreign key information
            System.out.println("Foreign Key Information:");
            System.out.println("-------------------------");
            ResultSet foreignKeys = dbMetaData.getImportedKeys(null, null, "admin_detail");
            while (foreignKeys.next()) {
                System.out.println("FK Column: " + foreignKeys.getString("FKCOLUMN_NAME") +
                    ", PK Table: " + foreignKeys.getString("PKTABLE_NAME") +
                    ", PK Column: " + foreignKeys.getString("PKCOLUMN_NAME") +
                    ", FK Name: " + foreignKeys.getString("FK_NAME"));
            }
            foreignKeys.close();
            
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            // Close connection
            if (connection != null) DbConnectUtil.closeConnection(connection);
        }
    }
    
    // Helper method to determine SQL conformance level
    private static String getSQLLevel(DatabaseMetaData dbMetaData) throws SQLException {
        if (dbMetaData.supportsCoreSQLGrammar()) {
            if (dbMetaData.supportsExtendedSQLGrammar()) {
                if (dbMetaData.supportsANSI92FullSQL()) {
                    return "ANSI 92 Full";
                } else if (dbMetaData.supportsANSI92IntermediateSQL()) {
                    return "ANSI 92 Intermediate";
                } else {
                    return "ANSI 92 Entry";
                }
            } else {
                return "Core SQL";
            }
        } else {
            return "Unknown";
        }
    }
}

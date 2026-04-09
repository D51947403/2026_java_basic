package main.com.library.dao;

import main.com.library.dto.AdminDTO;
import main.com.library.util.Constants;
import main.com.library.util.DbConnectUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

public class ExportExcelDataDemo {

    /**
     * Get all admins (READ_ALL operation)
     */
    public List<Object> getAdminDataFromDb() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        List<Object> adminDataList = new ArrayList<>();
        try {
            connection = DbConnectUtil.getConnection();

            String callQuery = "{call admin_crud(NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, ?, ?)}";
            callableStatement = connection.prepareCall(callQuery);

            // Set parameters
            callableStatement.setString(1, Constants.READ_ALL);

            // Register OUT parameters
            callableStatement.registerOutParameter(2, Types.INTEGER);
            callableStatement.registerOutParameter(3, Types.VARCHAR);

            // Execute and get result set
            resultSet = callableStatement.executeQuery();

            List<AdminDTO> adminList = new ArrayList<>();
            while (resultSet.next()) {
                AdminDTO admin = new AdminDTO(
                        resultSet.getInt("admin_id"),
                        resultSet.getString("admin_name"),
                        resultSet.getString("admin_code"),
                        resultSet.getString("admin_address"),
                        resultSet.getString("admin_email"),
                        resultSet.getString("admin_phone"),
                        resultSet.getInt("admin_salary"),
                        Constants.NOT_DELETED // is_deleted is always 'N' for READ_ALL operation
                );
                adminList.add(admin);
                // adding values
                adminDataList.add(adminList);

                List<String> headerList=new ArrayList<>();
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    headerList.add(metaData.getColumnName(i));
                }
                adminDataList.add(headerList);
            }

            // Get output parameters
            String message = callableStatement.getString(3);
            System.out.println("Procedure message: " + message);

            return adminDataList;

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error getting all admins: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (callableStatement != null) callableStatement.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
            DbConnectUtil.closeConnection(connection);
        }
    }

    public void exportDataIntoExcelFile() {
        List<Object> adminDataList = getAdminDataFromDb();
        
        if (adminDataList.isEmpty()) {
            System.out.println("No data to export");
            return;
        }
        
        FileWriter fileWriter = null;
        
        try {
            // Get data from adminDataList
            List<AdminDTO> adminList = (List<AdminDTO>) adminDataList.get(0);
            List<String> headerList = (List<String>) adminDataList.get(1);
            
            // Create CSV file (can be opened in Excel)
            String fileName = "admin_data_export.csv";
            fileWriter = new FileWriter(fileName);
            
            // Write headers
            for (int i = 0; i < headerList.size(); i++) {
                fileWriter.append(headerList.get(i));
                if (i < headerList.size() - 1) {
                    fileWriter.append(",");
                }
            }
            fileWriter.append("\n");
            
            // Write data rows
            for (AdminDTO admin : adminList) {
                fileWriter.append(String.valueOf(admin.getAdminId())).append(",");
                fileWriter.append(escapeCsv(admin.getAdminName())).append(",");
                fileWriter.append(escapeCsv(admin.getAdminCode())).append(",");
                fileWriter.append(escapeCsv(admin.getAdminAddress())).append(",");
                fileWriter.append(escapeCsv(admin.getAdminEmail())).append(",");
                fileWriter.append(escapeCsv(admin.getAdminPhone())).append(",");
                fileWriter.append(String.valueOf(admin.getAdminSalary()));
                fileWriter.append("\n");
            }
            
            System.out.println("CSV file exported successfully: " + fileName);
            System.out.println("You can open this file in Excel");
            
        } catch (IOException e) {
            System.err.println("Error exporting to CSV: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                System.err.println("Error closing file writer: " + e.getMessage());
            }
        }
    }
    
    // Helper method to escape CSV values
    private String escapeCsv(String value) {
        if (value == null) {
            return "";
        }
        // If value contains comma, quote, or newline, wrap in quotes and escape internal quotes
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }
    
    // Main method for testing
    public static void main(String[] args) {
        ExportExcelDataDemo exportDemo = new ExportExcelDataDemo();
        exportDemo.exportDataIntoExcelFile();
    }
}

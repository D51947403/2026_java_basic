package main.com.library.dao.proc;

import main.com.library.dto.AdminDTO;
import main.com.library.util.Constants;
import main.com.library.util.DbConnectUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {
    private Connection connection = null;
    private CallableStatement callableStatement = null;
    private ResultSet resultSet = null;
    // Result class to hold procedure output
    public static class ProcedureResult {
        private int count;
        private String message;
        
        public ProcedureResult(int count, String message) {
            this.count = count;
            this.message = message;
        }
        
        public int getCount() { return count; }
        public String getMessage() { return message; }
    }
    
    /**
     * Call admin_crud stored procedure
     */
    private ProcedureResult callAdminCrudProcedure(
            Integer adminId, String adminName, String adminCode, String adminAddress,
            String adminEmail, String adminPhone, Integer adminSalary, 
            String isDeleted, String operationType) {
        
         connection = null;
         callableStatement = null;
        
        try {
            connection = DbConnectUtil.getConnection();
            
            // Prepare call to stored procedure
            String callQuery = "{call admin_crud(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
            callableStatement = connection.prepareCall(callQuery);

            // Set IN parameters
            if (adminId != null) {
                callableStatement.setInt(1, adminId);
            } else {
                callableStatement.setNull(1, Constants.MINUS_ONE);
            }
            
            callableStatement.setString(2, adminName);
            callableStatement.setString(3, adminCode);
            callableStatement.setString(4, adminAddress);
            callableStatement.setString(5, adminEmail);
            callableStatement.setString(6, adminPhone);
            
            if (adminSalary != null) {
                callableStatement.setInt(7, adminSalary);
            } else {
                callableStatement.setNull(7, Constants.MINUS_ONE);
            }
            
            callableStatement.setString(8, isDeleted);
            callableStatement.setString(9, operationType);

            // Register OUT parameters
            callableStatement.registerOutParameter(10, Types.INTEGER);  // o_count
            callableStatement.registerOutParameter(11, Types.VARCHAR); // o_message

            // Execute the procedure
            callableStatement.execute();
            
            // Get OUT parameters
            int count = callableStatement.getInt(10);
            String message = callableStatement.getString(11);
            
            return new ProcedureResult(count, message);
            
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error calling admin_crud procedure: " + e.getMessage());
            e.printStackTrace();
            return new ProcedureResult(-1, "Error: " + e.getMessage());
        } finally {
            try {
                if (callableStatement != null) callableStatement.close();
            } catch (SQLException e) {
                System.err.println("Error closing CallableStatement: " + e.getMessage());
            }
            DbConnectUtil.closeConnection(connection);
        }
    }
    
    /**
     * Get admin by ID (READ operation)
     */
    public AdminDTO getAdminById(int adminId) {
        
        try {
            connection = DbConnectUtil.getConnection();
            
            String callQuery = "{call admin_crud(?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, ?, ?)}";
            callableStatement = connection.prepareCall(callQuery);
            
            // Set parameters
            callableStatement.setInt(1, adminId);
            callableStatement.setString(2, Constants.READ);
            
            // Register OUT parameters
            callableStatement.registerOutParameter(3, Types.INTEGER);
            callableStatement.registerOutParameter(4, Types.VARCHAR);
            
            // Execute and get result set
            resultSet = callableStatement.executeQuery();
            
            AdminDTO admin = null;
            if (resultSet.next()) {
                admin = new AdminDTO(
                    resultSet.getInt("admin_id"),
                    resultSet.getString("admin_name"),
                    resultSet.getString("admin_code"),
                    resultSet.getString("admin_address"),
                    resultSet.getString("admin_email"),
                    resultSet.getString("admin_phone"),
                    resultSet.getInt("admin_salary"),
                    Constants.NOT_DELETED // is_deleted is always 'N' for READ operation
                );
            }
            
            // Get output parameters
            String message = callableStatement.getString(4);
            System.out.println("Procedure message: " + message);
            
            return admin;
            
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error getting admin by ID: " + e.getMessage());
            e.printStackTrace();
            return null;
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
    
    /**
     * Get all admins (READ_ALL operation)
     */
    public List<AdminDTO> getAllAdmins() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        
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
            
            List<AdminDTO> admins = new ArrayList<>();
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
                admins.add(admin);
            }
            
            // Get output parameters
            String message = callableStatement.getString(3);
            System.out.println("Procedure message: " + message);
            
            return admins;
            
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
    
    /**
     * Create new admin (CREATE operation)
     */
    public ProcedureResult createAdmin(AdminDTO admin) {
        return callAdminCrudProcedure(
            null, admin.getAdminName(), admin.getAdminCode(), admin.getAdminAddress(),
            admin.getAdminEmail(), admin.getAdminPhone(), admin.getAdminSalary(),
            Constants.NOT_DELETED, Constants.CREATE
        );
    }
    
    /**
     * Update existing admin (UPDATE operation)
     */
    public ProcedureResult updateAdmin(AdminDTO admin) {
        return callAdminCrudProcedure(
            admin.getAdminId(), admin.getAdminName(), admin.getAdminCode(), admin.getAdminAddress(),
            admin.getAdminEmail(), admin.getAdminPhone(), admin.getAdminSalary(),
            Constants.NOT_DELETED, Constants.UPDATE
        );
    }
    
    /**
     * Soft delete admin (DELETE operation)
     */
    public ProcedureResult deleteAdmin(int adminId) {
        return callAdminCrudProcedure(
            adminId, null, null, null, null, null, null, Constants.SOFT_DELETED, Constants.DELETE
        );
    }
    
    /**
     * Search admins by name or code (SEARCH operation)
     */
    public List<AdminDTO> searchAdmins(String adminName, String adminCode) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        
        try {
            connection = DbConnectUtil.getConnection();
            
            String callQuery = "{call admin_crud(NULL, ?, ?, NULL, NULL, NULL, NULL, NULL, ?, ?, ?)}";
            callableStatement = connection.prepareCall(callQuery);
            
            // Set parameters
            callableStatement.setString(1, adminName);
            callableStatement.setString(2, adminCode);
            callableStatement.setString(3, Constants.SEARCH);
            
            // Register OUT parameters
            callableStatement.registerOutParameter(4, Types.INTEGER);
            callableStatement.registerOutParameter(5, Types.VARCHAR);
            
            // Execute and get result set
            resultSet = callableStatement.executeQuery();
            
            List<AdminDTO> admins = new ArrayList<>();
            while (resultSet.next()) {
                AdminDTO admin = new AdminDTO(
                    resultSet.getInt("admin_id"),
                    resultSet.getString("admin_name"),
                    resultSet.getString("admin_code"),
                    resultSet.getString("admin_address"),
                    resultSet.getString("admin_email"),
                    resultSet.getString("admin_phone"),
                    resultSet.getInt("admin_salary"),
                    Constants.NOT_DELETED // is_deleted is always 'N' for SEARCH operation
                );
                admins.add(admin);
            }
            
            // Get output parameters
            String message = callableStatement.getString(5);
            System.out.println("Procedure message: " + message);
            
            return admins;
            
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error searching admins: " + e.getMessage());
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
    
    /**
     * Test method to demonstrate all operations
     */
    public static void main(String[] args) {
        AdminDao adminDao = new AdminDao();
        
        // Test CREATE operation
        AdminDTO newAdmin = new AdminDTO();
        newAdmin.setAdminName("Rivank Lodhi");
        newAdmin.setAdminCode("ADM014");
        newAdmin.setAdminAddress("123 Main St");
        newAdmin.setAdminEmail("john@example.com");
        newAdmin.setAdminPhone("555-1234");
        newAdmin.setAdminSalary(60000);

        // Create new admin
        ProcedureResult createResult = adminDao.createAdmin(newAdmin);
        System.out.println("Create Result: " + createResult.getMessage());
        
        // Test READ_ALL operation
        List<AdminDTO> allAdmins = adminDao.getAllAdmins();
        System.out.println("All Admins: " + allAdmins.size());
        allAdmins.forEach(System.out::println);

        // Test SEARCH operation
        List<AdminDTO> searchResults = adminDao.searchAdmins("Rivank", "009");
        System.out.println("Search Results: " + searchResults.size());
        searchResults.forEach(System.out::println);

        // Test READ operation (assuming admin with ID 4005 exists)
        AdminDTO admin = adminDao.getAdminById(4005);
        if (admin != null) {
            System.out.println("Found Admin: " + admin);

            // Test UPDATE operation
            admin.setAdminSalary(65000);
            admin.setAdminPhone("99999999");
            ProcedureResult updateResult = adminDao.updateAdmin(admin);
            System.out.println("Update Result: " + updateResult.getMessage());
        }

        // Test DELETE operation
        ProcedureResult deleteResult = adminDao.deleteAdmin(4000);
        System.out.println("Delete Result: " + deleteResult.getMessage());
    }
}

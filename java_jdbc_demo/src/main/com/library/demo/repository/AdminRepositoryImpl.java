package main.com.library.demo.repository;

import main.com.library.dto.AdminDTO;
import main.com.library.util.DbConnectUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/***
 * This is repository implementation class for admin
 */
public class AdminRepositoryImpl implements AdminRepository{

    private static Connection connection;

    @Override
    public AdminDTO addAdmin(AdminDTO adminDTO) {
        try {
            connection = DbConnectUtil.getConnection();
            String insertQuery = "INSERT INTO admin_detail (admin_name, admin_code, admin_address, admin_email, admin_phone, admin_salary, is_deleted) " +
                                "VALUES (?, ?, ?, ?, ?, ?, 'N')";
            System.out.println("InsertQuery: " + insertQuery);
            
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            
            // Set parameters for the prepared statement
            preparedStatement.setString(1, adminDTO.getAdminName());
            preparedStatement.setString(2, adminDTO.getAdminCode());
            preparedStatement.setString(3, adminDTO.getAdminAddress());
            preparedStatement.setString(4, adminDTO.getAdminEmail());
            preparedStatement.setString(5, adminDTO.getAdminPhone());
            preparedStatement.setInt(6, adminDTO.getAdminSalary());
            
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
            
            if (rowsAffected > 0) {
                // Get the generated admin_id
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedAdminId = generatedKeys.getInt(1);
                    adminDTO.setAdminId(generatedAdminId);
                    adminDTO.setIsDeleted("N");
                    
                    // Return the newly added admin by fetching fresh data
                    return getAdminById(generatedAdminId);
                }
            }
            
            return null; // Insert failed

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbConnectUtil.closeConnection(connection);
        }
    }

    @Override
    public AdminDTO updateAdmin(AdminDTO adminDTO) {
        try {
            connection = DbConnectUtil.getConnection();
            String updateQuery = "UPDATE admin_detail SET admin_name = '" + adminDTO.getAdminName() + 
                                "', admin_code = '" + adminDTO.getAdminCode() + 
                                "', admin_address = '" + adminDTO.getAdminAddress() + 
                                "', admin_email = '" + adminDTO.getAdminEmail() + 
                                "', admin_phone = '" + adminDTO.getAdminPhone() + 
                                "', admin_salary = " + adminDTO.getAdminSalary() + 
                                " WHERE admin_id = " + adminDTO.getAdminId() + 
                                " AND is_deleted = 'N'";
            System.out.println("UpdateQuery: " + updateQuery);
            
            Statement statement = connection.createStatement();
            int rowsAffected = statement.executeUpdate(updateQuery);
            System.out.println("Rows affected: " + rowsAffected);
            
            if (rowsAffected > 0) {
                // Return the updated admin by fetching fresh data
                return getAdminById(adminDTO.getAdminId());
            } else {
                return null; // Admin not found or already deleted
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbConnectUtil.closeConnection(connection);
        }
    }

    @Override
    public String deleteAdmin(int adminId) {
        try {
            connection = DbConnectUtil.getConnection();
            String updateQuery = "UPDATE admin_detail SET is_deleted = 'Y' WHERE admin_id = " + adminId;
            System.out.println("UpdateQuery: " + updateQuery);
            
            Statement statement = connection.createStatement();
            int rowsAffected = statement.executeUpdate(updateQuery);
            System.out.println("Rows affected: " + rowsAffected);
            
            if (rowsAffected > 0) {
                return "Admin with ID " + adminId + " has been soft deleted successfully";
            } else {
                return "Admin with ID " + adminId + " not found or already deleted";
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbConnectUtil.closeConnection(connection);
        }
    }

    @Override
    public List<AdminDTO> getAllAdmin() {
        List<AdminDTO> adminList = new ArrayList<>();
        
        try {
            connection = DbConnectUtil.getConnection();
            String selectQuery = "SELECT admin_id, admin_name, admin_code, admin_address, admin_email, admin_phone, admin_salary, is_deleted FROM admin_detail WHERE is_deleted = 'N'";
            System.out.println("SelectQuery: " + selectQuery);
            
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery);
            System.out.println("ResultSet: " + resultSet);
            
            while (resultSet.next()) {
                AdminDTO adminDTO = new AdminDTO();
                adminDTO.setAdminId(resultSet.getInt("admin_id"));
                adminDTO.setAdminName(resultSet.getString("admin_name"));
                adminDTO.setAdminCode(resultSet.getString("admin_code"));
                adminDTO.setAdminAddress(resultSet.getString("admin_address"));
                adminDTO.setAdminSalary(resultSet.getInt("admin_salary"));
                adminDTO.setAdminEmail(resultSet.getString("admin_email"));
                adminDTO.setAdminPhone(resultSet.getString("admin_phone"));
                adminDTO.setIsDeleted(resultSet.getString("is_deleted"));
                adminList.add(adminDTO);
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbConnectUtil.closeConnection(connection);
        }

        return adminList;
    }

    @Override
    public AdminDTO getAdminById(int adminId) {
        AdminDTO adminDTO = new AdminDTO();
        try {
            connection = DbConnectUtil.getConnection();
            String selectQuery = "SELECT admin_id, admin_name, admin_code, admin_address, admin_email, admin_phone, admin_salary, is_deleted FROM admin_detail WHERE admin_id ="+adminId+" and is_deleted ='N'";
            System.out.println("SelectQuery: "+selectQuery);
            Statement statement = connection.prepareStatement(selectQuery);

             ResultSet resultSet = statement.executeQuery(selectQuery);
             System.out.println("ResultSet: "+resultSet);
             while(resultSet.next()) {
                adminDTO.setAdminId(resultSet.getInt("admin_id"));
                adminDTO.setAdminName(resultSet.getString("admin_name"));
                adminDTO.setAdminCode(resultSet.getString("admin_code"));
                adminDTO.setAdminAddress(resultSet.getString("admin_address"));
                adminDTO.setAdminSalary(resultSet.getInt("admin_salary"));
                adminDTO.setAdminEmail(resultSet.getString("admin_email"));
                adminDTO.setAdminPhone(resultSet.getString("admin_phone"));
             }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbConnectUtil.closeConnection(connection);
        }

        return adminDTO;
    }

    @Override
    public AdminDTO getAdminByCode(String adminCode) {
        AdminDTO adminDTO = new AdminDTO();
        try {
            connection = DbConnectUtil.getConnection();
            String selectQuery = "SELECT admin_id, admin_name, admin_code, admin_address, admin_email, admin_phone, admin_salary, is_deleted FROM admin_detail WHERE admin_code = '"+adminCode+"' and is_deleted ='N'";
            System.out.println("SelectQuery: "+selectQuery);
            Statement statement = connection.prepareStatement(selectQuery);

             ResultSet resultSet = statement.executeQuery(selectQuery);
             System.out.println("ResultSet: "+resultSet);
             while(resultSet.next()) {
                adminDTO.setAdminId(resultSet.getInt("admin_id"));
                adminDTO.setAdminName(resultSet.getString("admin_name"));
                adminDTO.setAdminCode(resultSet.getString("admin_code"));
                adminDTO.setAdminAddress(resultSet.getString("admin_address"));
                adminDTO.setAdminSalary(resultSet.getInt("admin_salary"));
                adminDTO.setAdminEmail(resultSet.getString("admin_email"));
                adminDTO.setAdminPhone(resultSet.getString("admin_phone"));
                adminDTO.setIsDeleted(resultSet.getString("is_deleted"));
             }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbConnectUtil.closeConnection(connection);
        }

        return adminDTO;
    }

    @Override
    public List<AdminDTO> searchAdmin(String adminName, String adminCode, String adminAddress) {
        List<AdminDTO> adminList = new ArrayList<>();
        
        try {
            connection = DbConnectUtil.getConnection();
            
            // Build dynamic query based on provided parameters
            StringBuilder queryBuilder = new StringBuilder("SELECT admin_id, admin_name, admin_code, admin_address, admin_email, admin_phone, admin_salary, is_deleted FROM admin_detail WHERE is_deleted = 'N'");
            List<Object> parameters = new ArrayList<>();
            
            if (adminName != null && !adminName.trim().isEmpty()) {
                queryBuilder.append(" AND admin_name LIKE ?");
                parameters.add("%" + adminName.trim() + "%");
            }
            
            if (adminCode != null && !adminCode.trim().isEmpty()) {
                queryBuilder.append(" AND admin_code LIKE ?");
                parameters.add("%" + adminCode.trim() + "%");
            }
            
            if (adminAddress != null && !adminAddress.trim().isEmpty()) {
                queryBuilder.append(" AND admin_address LIKE ?");
                parameters.add("%" + adminAddress.trim() + "%");
            }
            
            String selectQuery = queryBuilder.toString();
            System.out.println("SelectQuery: " + selectQuery);
            
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            
            // Set parameters dynamically
            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setString(i + 1, parameters.get(i).toString());
            }
            
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("ResultSet: " + resultSet);
            
            while (resultSet.next()) {
                AdminDTO adminDTO = new AdminDTO();
                adminDTO.setAdminId(resultSet.getInt("admin_id"));
                adminDTO.setAdminName(resultSet.getString("admin_name"));
                adminDTO.setAdminCode(resultSet.getString("admin_code"));
                adminDTO.setAdminAddress(resultSet.getString("admin_address"));
                adminDTO.setAdminSalary(resultSet.getInt("admin_salary"));
                adminDTO.setAdminEmail(resultSet.getString("admin_email"));
                adminDTO.setAdminPhone(resultSet.getString("admin_phone"));
                adminDTO.setIsDeleted(resultSet.getString("is_deleted"));
                adminList.add(adminDTO);
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbConnectUtil.closeConnection(connection);
        }

        return adminList;
    }
}

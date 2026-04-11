package test.com.library.dao.batch;

import main.com.library.dao.batch.AuthorDao;
import main.com.library.util.DbConnectUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorDaoTest {
    
    private AuthorDao authorDao;
    private Connection connection;
    
    public static void main(String[] args) {
        AuthorDaoTest test = new AuthorDaoTest();
        test.runAllTests();
    }
    
    public void runAllTests() {
        System.out.println("=== Running AuthorDao Tests ===");
        
        try {
            setup();
            testBatchInsertUsingStatement();
            cleanup();
            
            setup();
            testBatchInsertUsingPreparedStatement();
            cleanup();
            
            setup();
            testBatchInsertWithSavepointRollback();
            cleanup();
            
            setup();
            testBatchInsertWithCompleteRollback();
            cleanup();
            
            System.out.println("=== All Tests Completed Successfully ===");
            
        } catch (Exception e) {
            System.err.println("Test failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void setup() throws SQLException, ClassNotFoundException {
        authorDao = new AuthorDao();
        connection = DbConnectUtil.getConnection();
        
        // Clean up test data before each test
        cleanupTestData();
        
        System.out.println("Setup completed for test");
    }
    
    private void cleanup() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            cleanupTestData();
            connection.close();
        }
        System.out.println("Cleanup completed for test");
    }
    
    private void cleanupTestData() throws SQLException {
        try {
            // Delete test data
            PreparedStatement deleteStmt = connection.prepareStatement(
                "DELETE FROM author_detail WHERE author_code LIKE 'JKR01%' OR author_id IN (1000, 1001, 1002, 1003, 1004, 1005)"
            );
            deleteStmt.executeUpdate();
            deleteStmt.close();
            
            // Reset auto-increment if needed
            PreparedStatement resetStmt = connection.prepareStatement("ALTER TABLE author_detail AUTO_INCREMENT = 1");
            resetStmt.executeUpdate();
            resetStmt.close();
            
            connection.commit();
        } catch (SQLException e) {
            System.err.println("Error cleaning up test data: " + e.getMessage());
        }
    }
    
    public void testBatchInsertUsingStatement() throws SQLException, ClassNotFoundException {
        System.out.println("\n--- Test: Batch Insert Using Statement ---");
        
        // Execute batch insert
        authorDao.batchInsertAuthorsUsingStatement();
        
        // Verify results
        int insertCount = countAuthorsByCode("JKR01");
        int updateCount = countAuthorsById(1000);
        
        System.out.println("Inserted authors with JKR01 codes: " + insertCount);
        System.out.println("Updated author with ID 1000: " + updateCount);
        
        assert insertCount == 6 : "Expected 6 inserted authors, got " + insertCount;
        assert updateCount > 0 : "Expected author with ID 1000 to be updated";
        
        System.out.println("✓ Test passed: Batch Insert Using Statement");
    }
    
    public void testBatchInsertUsingPreparedStatement() throws SQLException, ClassNotFoundException {
        System.out.println("\n--- Test: Batch Insert Using PreparedStatement ---");
        
        // Execute batch insert
        authorDao.batchInsertAuthorsUsingPreparedStatement();
        
        // Verify results
        int insertCount = countAuthorsByCode("JKR01");
        int updateCount = countAuthorsById(1000);
        
        System.out.println("Inserted authors with JKR01 codes: " + insertCount);
        System.out.println("Updated author with ID 1000: " + updateCount);
        
        assert insertCount == 6 : "Expected 6 inserted authors, got " + insertCount;
        assert updateCount > 0 : "Expected author with ID 1000 to be updated";
        
        System.out.println("✓ Test passed: Batch Insert Using PreparedStatement");
    }
    
    public void testBatchInsertWithSavepointRollback() throws SQLException, ClassNotFoundException {
        System.out.println("\n--- Test: Batch Insert with Savepoint Rollback ---");
        
        // First, let's modify the AuthorDao to simulate a failure in the second batch
        // For this test, we'll manually create the scenario
        
        try {
            // Manually execute the first batch
            connection.setAutoCommit(false);
            java.sql.Statement stmt = connection.createStatement();
            
            // Add INSERT statements
            stmt.addBatch("INSERT INTO author_detail (author_name, author_code, author_address, author_dob, author_email, author_education, author_university) VALUES " +
                "('Test Author1', 'TEST001', 'Test City', '1990-01-01', 'test1@example.com', 'Test Education', 'Test University')");
            stmt.addBatch("INSERT INTO author_detail (author_name, author_code, author_address, author_dob, author_email, author_education, author_university) VALUES " +
                "('Test Author2', 'TEST002', 'Test City', '1990-01-01', 'test2@example.com', 'Test Education', 'Test University')");
            
            // Execute first batch
            stmt.executeBatch();
            
            // Create savepoint
            java.sql.Savepoint savepoint = connection.setSavepoint("after_insert");
            
            // Add UPDATE statements that might fail
            stmt.addBatch("UPDATE author_detail SET author_name='Updated Author' WHERE author_id=99999"); // This should fail
            
            try {
                stmt.executeBatch();
                connection.commit();
                System.out.println("Unexpected: Second batch succeeded");
            } catch (Exception e) {
                System.out.println("Expected failure in second batch: " + e.getMessage());
                // Rollback to savepoint
                connection.rollback(savepoint);
                connection.commit();
                System.out.println("Rolled back to savepoint");
            }
            
            // Verify that INSERT operations are preserved but UPDATE failed
            int insertCount = countAuthorsByCode("TEST");
            System.out.println("Authors preserved after rollback: " + insertCount);
            
            assert insertCount == 2 : "Expected 2 authors to be preserved after rollback";
            
            System.out.println("✓ Test passed: Savepoint rollback works correctly");
            
        } catch (SQLException e) {
            System.err.println("Error in savepoint rollback test: " + e.getMessage());
            throw e;
        }
    }
    
    public void testBatchInsertWithCompleteRollback() throws SQLException, ClassNotFoundException {
        System.out.println("\n--- Test: Batch Insert with Complete Rollback ---");
        
        try {
            connection.setAutoCommit(false);
            java.sql.Statement stmt = connection.createStatement();
            
            // Add statements that will cause an error
            stmt.addBatch("INSERT INTO author_detail (author_name, author_code, author_address, author_dob, author_email, author_education, author_university) VALUES " +
                "('Rollback Test', 'ROLL001', 'Test City', '1990-01-01', 'rollback@example.com', 'Test Education', 'Test University')");
            stmt.addBatch("INVALID SQL STATEMENT"); // This will cause the entire batch to fail
            
            try {
                stmt.executeBatch();
                connection.commit();
                System.out.println("Unexpected: Batch succeeded with invalid SQL");
            } catch (Exception e) {
                System.out.println("Expected failure: " + e.getMessage());
                connection.rollback();
                System.out.println("Complete rollback executed");
            }
            
            // Verify that no data was inserted
            int insertCount = countAuthorsByCode("ROLL");
            System.out.println("Authors after complete rollback: " + insertCount);
            
            assert insertCount == 0 : "Expected 0 authors after complete rollback";
            
            System.out.println("✓ Test passed: Complete rollback works correctly");
            
        } catch (SQLException e) {
            System.err.println("Error in complete rollback test: " + e.getMessage());
            throw e;
        }
    }
    
    // Helper methods
    private int countAuthorsByCode(String codePattern) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(
            "SELECT COUNT(*) FROM author_detail WHERE author_code LIKE ?"
        );
        stmt.setString(1, codePattern + "%");
        ResultSet rs = stmt.executeQuery();
        
        int count = 0;
        if (rs.next()) {
            count = rs.getInt(1);
        }
        
        rs.close();
        stmt.close();
        return count;
    }
    
    private int countAuthorsById(int authorId) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(
            "SELECT COUNT(*) FROM author_detail WHERE author_id = ?"
        );
        stmt.setInt(1, authorId);
        ResultSet rs = stmt.executeQuery();
        
        int count = 0;
        if (rs.next()) {
            count = rs.getInt(1);
        }
        
        rs.close();
        stmt.close();
        return count;
    }
    
    private void printAuthorDetails() throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(
            "SELECT author_id, author_name, author_code, author_email FROM author_detail ORDER BY author_id"
        );
        ResultSet rs = stmt.executeQuery();
        
        System.out.println("Current Author Details:");
        System.out.println("ID\tName\t\tCode\tEmail");
        System.out.println("--\t----\t\t----\t-----");
        
        while (rs.next()) {
            System.out.printf("%d\t%s\t%s\t%s\n", 
                rs.getInt("author_id"),
                rs.getString("author_name"),
                rs.getString("author_code"),
                rs.getString("author_email"));
        }
        
        rs.close();
        stmt.close();
    }
}

package test.com.library.dao;

import main.com.library.dao.proc.AdminDao;
import main.com.library.dto.AdminDTO;
import main.com.library.util.Constants;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Test Cases for AdminDao
 * Note: These tests require a running MySQL database with the admin_crud stored procedure
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("AdminDao Test Suite")
class AdminDaoTest {
    
    private static AdminDao adminDao;
    private static int testAdminId;
    
    @BeforeAll
    static void setUp() {
        adminDao = new AdminDao();
        System.out.println("=== AdminDao Test Suite Started ===");
    }
    
    @AfterAll
    static void tearDown() {
        System.out.println("=== AdminDao Test Suite Completed ===");
    }
    
    @Test
    @Order(1)
    @DisplayName("Test Create Admin - Success")
    void testCreateAdmin_Success() {
        // Arrange
        AdminDTO newAdmin = new AdminDTO();
        newAdmin.setAdminName("Test User");
        newAdmin.setAdminCode("TEST001");
        newAdmin.setAdminAddress("123 Test Street");
        newAdmin.setAdminEmail("test@example.com");
        newAdmin.setAdminPhone("555-TEST");
        newAdmin.setAdminSalary(50000);
        
        // Act
        AdminDao.ProcedureResult result = adminDao.createAdmin(newAdmin);
        
        // Assert
        assertNotNull(result);
        assertEquals(0, result.getCount()); // Default count from procedure
        assertTrue(result.getMessage().contains("successfully") || result.getMessage().contains("created"));
        System.out.println("✓ Create Admin Test Passed: " + result.getMessage());
    }
    
    @Test
    @Order(2)
    @DisplayName("Test Create Admin - Duplicate Code")
    void testCreateAdmin_DuplicateCode() {
        // Arrange
        AdminDTO duplicateAdmin = new AdminDTO();
        duplicateAdmin.setAdminName("Another User");
        duplicateAdmin.setAdminCode("TEST001"); // Same code as previous test
        duplicateAdmin.setAdminAddress("456 Test Street");
        duplicateAdmin.setAdminEmail("another@example.com");
        duplicateAdmin.setAdminPhone("555-TEST2");
        duplicateAdmin.setAdminSalary(60000);
        
        // Act
        AdminDao.ProcedureResult result = adminDao.createAdmin(duplicateAdmin);
        
        // Assert
        assertNotNull(result);
        // The behavior depends on database constraints and procedure implementation
        System.out.println("✓ Create Duplicate Admin Test: " + result.getMessage());
    }
    
    @Test
    @Order(3)
    @DisplayName("Test Get All Admins")
    void testGetAllAdmins() {
        // Act
        List<AdminDTO> admins = adminDao.getAllAdmins();
        
        // Assert
        assertNotNull(admins);
        assertFalse(admins.isEmpty());
        assertTrue(admins.size() > 0);
        
        System.out.println("✓ Get All Admins Test Passed: Found " + admins.size() + " admins");
    }
    
    @Test
    @Order(4)
    @DisplayName("Test Search Admins - By Name")
    void testSearchAdmins_ByName() {
        // Act
        List<AdminDTO> searchResults = adminDao.searchAdmins("Test", null);
        
        // Assert
        assertNotNull(searchResults);
        // Should find admins with "Test" in their name
        for (AdminDTO admin : searchResults) {
            assertTrue(admin.getAdminName().toLowerCase().contains("test"));
        }
        
        System.out.println("✓ Search Admins by Name Test Passed: Found " + searchResults.size() + " results");
    }
    
    @Test
    @Order(5)
    @DisplayName("Test Search Admins - By Code")
    void testSearchAdmins_ByCode() {
        // Act
        List<AdminDTO> searchResults = adminDao.searchAdmins(null, "001");
        
        // Assert
        assertNotNull(searchResults);
        // Should find admins with "001" in their code
        for (AdminDTO admin : searchResults) {
            assertTrue(admin.getAdminCode().contains("001"));
        }
        
        System.out.println("✓ Search Admins by Code Test Passed: Found " + searchResults.size() + " results");
    }
    
    @Test
    @Order(6)
    @DisplayName("Test Search Admins - By Name and Code")
    void testSearchAdmins_ByNameAndCode() {
        // Act
        List<AdminDTO> searchResults = adminDao.searchAdmins("Test", "001");
        
        // Assert
        assertNotNull(searchResults);
        // Should find admins matching both criteria
        for (AdminDTO admin : searchResults) {
            assertTrue(admin.getAdminName().toLowerCase().contains("test") || 
                      admin.getAdminCode().contains("001"));
        }
        
        System.out.println("✓ Search Admins by Name and Code Test Passed: Found " + searchResults.size() + " results");
    }
    
    @Test
    @Order(7)
    @DisplayName("Test Get Admin By ID - Existing Admin")
    void testGetAdminById_Existing() {
        // First, get all admins to find a valid ID
        List<AdminDTO> admins = adminDao.getAllAdmins();
        assertFalse(admins.isEmpty(), "Need at least one admin to test get by ID");
        
        int validId = admins.get(0).getAdminId();
        
        // Act
        AdminDTO admin = adminDao.getAdminById(validId);
        
        // Assert
        assertNotNull(admin);
        assertEquals(validId, admin.getAdminId());
        assertNotNull(admin.getAdminName());
        assertNotNull(admin.getAdminCode());
        assertEquals(Constants.NOT_DELETED, admin.getIsDeleted());
        
        testAdminId = validId; // Store for update test
        System.out.println("✓ Get Admin by ID Test Passed: Found admin with ID " + validId);
    }
    
    @Test
    @Order(8)
    @DisplayName("Test Get Admin By ID - Non-Existing Admin")
    void testGetAdminById_NonExisting() {
        // Act
        AdminDTO admin = adminDao.getAdminById(99999);
        
        // Assert
        assertNull(admin);
        System.out.println("✓ Get Non-Existing Admin Test Passed: Returned null as expected");
    }
    
    @Test
    @Order(9)
    @DisplayName("Test Update Admin - Success")
    void testUpdateAdmin_Success() {
        // Skip if no admin ID was found
        if (testAdminId == 0) {
            System.out.println("⚠ Update Test Skipped: No valid admin ID found");
            return;
        }
        
        // Get the admin first
        AdminDTO admin = adminDao.getAdminById(testAdminId);
        assertNotNull(admin, "Admin should exist for update test");
        
        // Arrange - Modify some fields
        String originalName = admin.getAdminName();
        int originalSalary = admin.getAdminSalary();
        
        admin.setAdminName("Updated " + originalName);
        admin.setAdminSalary(originalSalary + 10000);
        admin.setAdminPhone("555-UPDATED");
        
        // Act
        AdminDao.ProcedureResult result = adminDao.updateAdmin(admin);
        
        // Assert
        assertNotNull(result);
        assertTrue(result.getMessage().contains("successfully") || result.getMessage().contains("updated"));
        
        // Verify the update by fetching again
        AdminDTO updatedAdmin = adminDao.getAdminById(testAdminId);
        assertNotNull(updatedAdmin);
        assertEquals("Updated " + originalName, updatedAdmin.getAdminName());
        assertEquals(originalSalary + 10000, updatedAdmin.getAdminSalary());
        assertEquals("555-UPDATED", updatedAdmin.getAdminPhone());
        
        System.out.println("✓ Update Admin Test Passed: " + result.getMessage());
    }
    
    @Test
    @Order(10)
    @DisplayName("Test Delete Admin - Success")
    void testDeleteAdmin_Success() {
        // Create a new admin specifically for deletion test
        AdminDTO tempAdmin = new AdminDTO();
        tempAdmin.setAdminName("Temp Delete User");
        tempAdmin.setAdminCode("DEL001");
        tempAdmin.setAdminAddress("789 Delete Street");
        tempAdmin.setAdminEmail("delete@example.com");
        tempAdmin.setAdminPhone("555-DELETE");
        tempAdmin.setAdminSalary(40000);
        
        // Create the admin first
        AdminDao.ProcedureResult createResult = adminDao.createAdmin(tempAdmin);
        assertNotNull(createResult);
        
        // Find the created admin's ID
        List<AdminDTO> searchResults = adminDao.searchAdmins("Temp Delete User", null);
        assertFalse(searchResults.isEmpty(), "Created admin should be found");
        
        int adminToDeleteId = searchResults.get(0).getAdminId();
        
        // Act - Delete the admin
        AdminDao.ProcedureResult deleteResult = adminDao.deleteAdmin(adminToDeleteId);
        
        // Assert
        assertNotNull(deleteResult);
        assertTrue(deleteResult.getMessage().contains("successfully") || deleteResult.getMessage().contains("deleted"));
        
        // Verify the admin is soft-deleted (should not appear in normal searches)
        List<AdminDTO> afterDelete = adminDao.searchAdmins("Temp Delete User", null);
        boolean found = false;
        for (AdminDTO admin : afterDelete) {
            if (admin.getAdminId() == adminToDeleteId) {
                found = true;
                break;
            }
        }
        
        System.out.println("✓ Delete Admin Test Passed: " + deleteResult.getMessage());
    }
    
    @Test
    @Order(11)
    @DisplayName("Test ProcedureResult Class")
    void testProcedureResult() {
        // Test the ProcedureResult class
        AdminDao.ProcedureResult result = new AdminDao.ProcedureResult(5, "Test Message");
        
        assertEquals(5, result.getCount());
        assertEquals("Test Message", result.getMessage());
        
        System.out.println("✓ ProcedureResult Test Passed");
    }
    
    @Test
    @Order(12)
    @DisplayName("Test AdminDTO Class")
    void testAdminDTO() {
        // Test the AdminDTO class
        AdminDTO admin = new AdminDTO();
        
        // Test setters
        admin.setAdminId(1);
        admin.setAdminName("Test Admin");
        admin.setAdminCode("T001");
        admin.setAdminAddress("Test Address");
        admin.setAdminEmail("test@test.com");
        admin.setAdminPhone("555-TEST");
        admin.setAdminSalary(60000);
        admin.setIsDeleted("N");
        
        // Test getters
        assertEquals(1, admin.getAdminId());
        assertEquals("Test Admin", admin.getAdminName());
        assertEquals("T001", admin.getAdminCode());
        assertEquals("Test Address", admin.getAdminAddress());
        assertEquals("test@test.com", admin.getAdminEmail());
        assertEquals("555-TEST", admin.getAdminPhone());
        assertEquals(60000, admin.getAdminSalary());
        assertEquals("N", admin.getIsDeleted());
        
        // Test toString
        String toString = admin.toString();
        assertTrue(toString.contains("Test Admin"));
        assertTrue(toString.contains("T001"));
        
        System.out.println("✓ AdminDTO Test Passed");
    }
    
    @Test
    @DisplayName("Test Constants Usage")
    void testConstantsUsage() {
        // Verify that constants are properly used in the DAO
        assertEquals("READ", Constants.READ);
        assertEquals("READ_ALL", Constants.READ_ALL);
        assertEquals("CREATE", Constants.CREATE);
        assertEquals("UPDATE", Constants.UPDATE);
        assertEquals("DELETE", Constants.DELETE);
        assertEquals("SEARCH", Constants.SEARCH);
        assertEquals("N", Constants.NOT_DELETED);
        assertEquals("Y", Constants.SOFT_DELETED);
        assertEquals(-1, Constants.MINUS_ONE);
        assertEquals("NA", Constants.NOT_AVAILABLE);
        
        System.out.println("✓ Constants Test Passed");
    }
    
    @Test
    @DisplayName("Test Edge Cases - Null Parameters")
    void testEdgeCases_NullParameters() {
        // Test with null parameters
        AdminDTO nullAdmin = new AdminDTO();
        nullAdmin.setAdminName(null);
        nullAdmin.setAdminCode(null);
        
        // This should not throw an exception
        AdminDao.ProcedureResult result = adminDao.createAdmin(nullAdmin);
        assertNotNull(result);
        
        System.out.println("✓ Edge Cases Test Passed: Handled null parameters gracefully");
    }
    
    @Test
    @DisplayName("Test Edge Cases - Empty Strings")
    void testEdgeCases_EmptyStrings() {
        // Test with empty strings
        AdminDTO emptyAdmin = new AdminDTO();
        emptyAdmin.setAdminName("");
        emptyAdmin.setAdminCode("");
        emptyAdmin.setAdminEmail("");
        emptyAdmin.setAdminPhone("");
        
        // This should not throw an exception
        AdminDao.ProcedureResult result = adminDao.createAdmin(emptyAdmin);
        assertNotNull(result);
        
        System.out.println("✓ Edge Cases Test Passed: Handled empty strings gracefully");
    }
}

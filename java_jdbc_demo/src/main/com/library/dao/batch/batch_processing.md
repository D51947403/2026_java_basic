# JDBC Batch Processing and Savepoints

## Overview

Batch processing in JDBC allows you to group multiple SQL statements together and execute them as a single unit, significantly improving performance by reducing network round-trips between your application and the database.

## Benefits of Batch Processing

- **Performance**: Reduces database round-trips from N statements to 1 execution
- **Transaction Management**: All statements in a batch succeed or fail together
- **Resource Efficiency**: Less memory and CPU overhead compared to individual executions
- **Network Optimization**: Minimizes network communication overhead

## Two Main Approaches

### 1. Using Statement

```java
// Create statement and add SQL commands to batch
Statement stmt = connection.createStatement();
stmt.addBatch("INSERT INTO author_detail (author_name, author_code) VALUES ('John Doe', 'JD001')");
stmt.addBatch("INSERT INTO author_detail (author_name, author_code) VALUES ('Jane Smith', 'JS002')");
stmt.addBatch("UPDATE author_detail SET author_address='New Address' WHERE author_id=1001");

// Execute all statements in the batch
int[] results = stmt.executeBatch();

// results array contains the number of rows affected for each statement
for (int result : results) {
    System.out.println("Rows affected: " + result);
}
```

### 2. Using PreparedStatement (Recommended)

```java
// Prepare statement with parameters
String sql = "INSERT INTO author_detail (author_name, author_code, author_address, author_dob, author_email, author_education, author_university) VALUES (?, ?, ?, ?, ?, ?, ?)";
PreparedStatement pstmt = connection.prepareStatement(sql);

// Set parameters and add to batch multiple times
String[][] authors = {
    {"J.K. Rowling", "JKR001", "Edinburgh, Scotland", "1965-07-31", "jk.rowling@example.com", "BA in French and Classics", "University of Exeter"},
    {"Stephen King", "SK001", "Bangor, Maine", "1947-09-21", "stephen.king@example.com", "BA in English", "University of Maine"},
    {"Agatha Christie", "AC001", "Torquay, England", "1890-09-15", "agatha.christie@example.com", "Home Schooled", "N/A"}
};

for (String[] author : authors) {
    pstmt.setString(1, author[0]);  // author_name
    pstmt.setString(2, author[1]);  // author_code
    pstmt.setString(3, author[2]);  // author_address
    pstmt.setString(4, author[3]);  // author_dob
    pstmt.setString(5, author[4]);  // author_email
    pstmt.setString(6, author[5]);  // author_education
    pstmt.setString(7, author[6]);  // author_university
    pstmt.addBatch();
}

// Execute the batch
int[] results = pstmt.executeBatch();
```

## Savepoints in JDBC

### What is a Savepoint?

A **savepoint** is a marker within a transaction that allows you to rollback to a specific point instead of rolling back the entire transaction. This provides granular control over transaction management.

### Purpose in Batch Processing

Savepoints are particularly useful in batch processing when you want to:
- **Partial Recovery**: Rollback only failed portions while preserving successful operations
- **Complex Transactions**: Handle multiple logical units within one transaction
- **Error Isolation**: Contain errors to specific batch segments

### Savepoint Methods

- **`connection.setSavepoint()`**: Creates an unnamed savepoint
- **`connection.setSavepoint(String name)`**: Creates a named savepoint
- **`connection.rollback(Savepoint savepoint)`**: Rolls back to specific savepoint
- **`connection.releaseSavepoint(Savepoint savepoint)`**: Releases a savepoint

### Complete Example with Savepoints

```java
public void batchInsertAuthorsWithSavepoints() {
    Connection connection = null;
    Statement batchStatement = null;
    
    try {
        connection = DbConnectUtil.getConnection();
        // Start transaction
        connection.setAutoCommit(false);
        batchStatement = connection.createStatement();
        
        try {
            // First batch: INSERT operations
            batchStatement.addBatch("INSERT INTO author_detail (author_name, author_code, author_address) VALUES ('Author 1', 'A001', 'Address 1')");
            batchStatement.addBatch("INSERT INTO author_detail (author_name, author_code, author_address) VALUES ('Author 2', 'A002', 'Address 2')");
            batchStatement.addBatch("INSERT INTO author_detail (author_name, author_code, author_address) VALUES ('Author 3', 'A003', 'Address 3')");

            // Execute the first batch
            batchStatement.executeBatch();
            
            // Create savepoint after first batch
            Savepoint savepointInsert = connection.setSavepoint("after_insert");
            System.out.println("Savepoint created after INSERT operations");
            
            // Second batch: UPDATE operations
            batchStatement.addBatch("UPDATE author_detail SET author_name='Updated Author 1' WHERE author_id=1001");
            batchStatement.addBatch("UPDATE author_detail SET author_name='Updated Author 2' WHERE author_id=1002");
            batchStatement.addBatch("UPDATE author_detail SET author_name='Updated Author 3' WHERE author_id=1003");

            try {
                // Execute the second batch
                batchStatement.executeBatch();
                
                // Third batch: More complex operations
                batchStatement.addBatch("UPDATE author_detail SET author_address='New Address 1' WHERE author_id=1001");
                batchStatement.addBatch("UPDATE author_detail SET author_address='New Address 2' WHERE author_id=1002");
                
                // Execute the third batch
                batchStatement.executeBatch();
                
                // If all successful, commit the transaction
                connection.commit();
                System.out.println("All batch operations completed successfully");
                
            } catch (BatchUpdateException e) {
                // Rollback to savepoint if second/third batch fails
                System.out.println("Second/Third batch failed, rolling back to savepoint: " + savepointInsert);
                connection.rollback(savepointInsert);
                connection.commit();
                System.out.println("Rolled back to savepoint, first batch operations preserved");
            }
            
        } catch (BatchUpdateException e) {
            // Rollback everything if first batch fails
            System.out.println("First batch failed, rolling back entire transaction");
            connection.rollback();
            throw e;
        }
        
    } catch (ClassNotFoundException | SQLException e) {
        throw new RuntimeException(e);
    } finally {
        try {
            if (connection != null) {
                connection.setAutoCommit(true);
                DbConnectUtil.closeConnection(connection);
            }
            if (batchStatement != null) {
                batchStatement.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
```

## Transaction Flow with Savepoints

```
Start Transaction (setAutoCommit(false))
├── Batch 1: INSERT operations
│   └── Execute → Success → Create Savepoint 1
├── Batch 2: UPDATE operations  
│   └── Execute → Failure → Rollback to Savepoint 1
├── Batch 3: More operations
│   └── (Skipped due to rollback)
└── Commit (only Batch 1 preserved)
```

## Best Practices

### Batch Processing
1. **Use PreparedStatement**: Better performance and prevents SQL injection
2. **Batch Size**: Keep batches reasonable (typically 100-1000 statements)
3. **Memory Management**: Clear batches periodically for large datasets
4. **Error Handling**: Handle `BatchUpdateException` properly

### Savepoint Management
1. **Strategic Placement**: Create savepoints at logical transaction boundaries
2. **Named Savepoints**: Use descriptive names for better debugging
3. **Resource Cleanup**: Release savepoints when no longer needed
4. **Performance Impact**: Be aware that savepoints add some overhead

### Transaction Management
1. **Always Commit/Rollback**: Ensure transactions are properly completed
2. **Exception Handling**: Handle exceptions at appropriate levels
3. **Connection Management**: Properly close connections and statements
4. **AutoCommit Management**: Reset autoCommit to original state

## Error Handling

```java
try {
    int[] results = pstmt.executeBatch();
    // Check results for any failures
    for (int i = 0; i < results.length; i++) {
        if (results[i] == Statement.EXECUTE_FAILED) {
            System.out.println("Statement " + i + " failed");
        }
    }
} catch (BatchUpdateException e) {
    int[] updateCounts = e.getUpdateCounts();
    System.out.println("Batch failed after " + updateCounts.length + " successful statements");
    // Handle partial success scenario
}
```

## Performance Considerations

- **Batch Size**: Larger batches reduce network overhead but increase memory usage
- **Connection Pooling**: Reuse connections for better performance
- **Prepared Statements**: Cache prepared statements for reuse
- **Database Configuration**: Ensure database is optimized for batch operations

## Common Use Cases

1. **Bulk Data Loading**: Importing large datasets
2. **Data Migration**: Moving data between systems
3. **Periodic Updates**: Scheduled batch jobs
4. **ETL Operations**: Extract, Transform, Load processes
5. **Data Synchronization**: Syncing between databases

## Conclusion

JDBC batch processing with savepoints provides a powerful mechanism for handling large-scale database operations efficiently while maintaining data integrity and error recovery capabilities. Proper implementation can significantly improve application performance and reliability.

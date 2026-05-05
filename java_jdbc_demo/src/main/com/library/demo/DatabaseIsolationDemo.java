package main.com.library.demo;

import main.com.library.util.DbConnectUtil;

import java.sql.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class DatabaseIsolationDemo {

    public static void main(String[] args) {
        try {
            setupTestData();
            
            System.out.println("=== Database Isolation Levels Demo ===\n");
            
            demonstrateReadUncommitted();
            Thread.sleep(1000);
            
            demonstrateReadCommitted();
            Thread.sleep(1000);
            
            demonstrateRepeatableRead();
            Thread.sleep(1000);
            
            demonstrateSerializable();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setupTestData() throws SQLException {
        try (Connection conn =DbConnectUtil.getConnection()) {
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("CREATE TABLE IF NOT EXISTS accounts (id INT PRIMARY KEY, name VARCHAR(50), balance DECIMAL(10,2))");
                stmt.execute("DELETE FROM accounts");
                stmt.execute("INSERT INTO accounts VALUES (1, 'Alice', 1000.00), (2, 'Bob', 500.00)");
                //conn.commit();
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void demonstrateReadUncommitted() {
        System.out.println("1. READ_UNCOMMITTED Isolation Level");
        System.out.println("   - Can read uncommitted changes from other transactions");
        System.out.println("   - Allows dirty reads, non-repeatable reads, and phantom reads\n");
        
        CountDownLatch latch = new CountDownLatch(2);
        
        Thread writer = new Thread(() -> {
            try (Connection conn =DbConnectUtil.getConnection()) {
                conn.setAutoCommit(false);
                conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
                
                try (Statement stmt = conn.createStatement()) {
                    stmt.executeUpdate("UPDATE accounts SET balance = 800.00 WHERE id = 1");
                    System.out.println("   Writer: Updated Alice's balance to 800.00 (uncommitted)");
                    latch.countDown();
                    
                    Thread.sleep(2000);
                    conn.rollback();
                    System.out.println("   Writer: Rolled back transaction");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        Thread reader = new Thread(() -> {
            try {
                latch.await();
                Thread.sleep(500);
                
                try (Connection conn = DbConnectUtil.getConnection()) {
                    conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
                    
                    try (Statement stmt = conn.createStatement();
                         ResultSet rs = stmt.executeQuery("SELECT balance FROM accounts WHERE id = 1")) {
                        
                        if (rs.next()) {
                            System.out.println("   Reader: Read Alice's balance = " + rs.getDouble("balance") + " (DIRTY READ!)");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        writer.start();
        reader.start();
        
        try {
            writer.join();
            reader.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println();
    }

    private static void demonstrateReadCommitted() {
        System.out.println("2. READ_COMMITTED Isolation Level");
        System.out.println("   - Can only read committed changes from other transactions");
        System.out.println("   - Prevents dirty reads, but allows non-repeatable reads and phantom reads\n");
        
        CountDownLatch latch = new CountDownLatch(2);
        
        Thread writer = new Thread(() -> {
            try (Connection conn = DbConnectUtil.getConnection()) {
                conn.setAutoCommit(false);
                conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
                
                try (Statement stmt = conn.createStatement()) {
                    stmt.executeUpdate("UPDATE accounts SET balance = 900.00 WHERE id = 1");
                    System.out.println("   Writer: Updated Alice's balance to 900.00 (uncommitted)");
                    latch.countDown();
                    
                    Thread.sleep(2000);
                    conn.commit();
                    System.out.println("   Writer: Committed transaction");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        Thread reader = new Thread(() -> {
            try {
                latch.await();
                Thread.sleep(500);
                
                try (Connection conn = DbConnectUtil.getConnection()) {
                    conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
                    
                    try (Statement stmt = conn.createStatement();
                         ResultSet rs = stmt.executeQuery("SELECT balance FROM accounts WHERE id = 1")) {
                        
                        if (rs.next()) {
                            System.out.println("   Reader: First read - Alice's balance = " + rs.getDouble("balance"));
                        }
                    }
                    
                    Thread.sleep(1500);
                    
                    try (Statement stmt = conn.createStatement();
                         ResultSet rs = stmt.executeQuery("SELECT balance FROM accounts WHERE id = 1")) {
                        
                        if (rs.next()) {
                            System.out.println("   Reader: Second read - Alice's balance = " + rs.getDouble("balance") + " (NON-REPEATABLE READ!)");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        writer.start();
        reader.start();
        
        try {
            writer.join();
            reader.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println();
    }

    private static void demonstrateRepeatableRead() {
        System.out.println("3. REPEATABLE_READ Isolation Level");
        System.out.println("   - Guarantees that if a row is read once, it will have the same values if read again");
        System.out.println("   - Prevents dirty reads and non-repeatable reads, but allows phantom reads\n");
        
        CountDownLatch latch = new CountDownLatch(2);
        
        Thread writer = new Thread(() -> {
            try (Connection conn = DbConnectUtil.getConnection()) {
                conn.setAutoCommit(false);
                conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
                
                try (Statement stmt = conn.createStatement()) {
                    stmt.executeUpdate("UPDATE accounts SET balance = 1100.00 WHERE id = 1");
                    System.out.println("   Writer: Updated Alice's balance to 1100.00 (uncommitted)");
                    latch.countDown();
                    
                    Thread.sleep(2000);
                    conn.commit();
                    System.out.println("   Writer: Committed transaction");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        Thread reader = new Thread(() -> {
            try {
                latch.await();
                Thread.sleep(500);
                
                try (Connection conn = DbConnectUtil.getConnection()) {
                    conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
                    
                    try (Statement stmt = conn.createStatement();
                         ResultSet rs = stmt.executeQuery("SELECT balance FROM accounts WHERE id = 1")) {
                        
                        if (rs.next()) {
                            System.out.println("   Reader: First read - Alice's balance = " + rs.getDouble("balance"));
                        }
                    }
                    
                    Thread.sleep(1500);
                    
                    try (Statement stmt = conn.createStatement();
                         ResultSet rs = stmt.executeQuery("SELECT balance FROM accounts WHERE id = 1")) {
                        
                        if (rs.next()) {
                            System.out.println("   Reader: Second read - Alice's balance = " + rs.getDouble("balance") + " (REPEATABLE READ!)");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        writer.start();
        reader.start();
        
        try {
            writer.join();
            reader.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println();
    }

    private static void demonstrateSerializable() {
        System.out.println("4. SERIALIZABLE Isolation Level");
        System.out.println("   - Highest level of isolation");
        System.out.println("   - Prevents dirty reads, non-repeatable reads, and phantom reads");
        System.out.println("   - Transactions are executed as if they were serial\n");
        
        CountDownLatch latch = new CountDownLatch(2);
        
        Thread writer = new Thread(() -> {
            try (Connection conn = DbConnectUtil.getConnection()) {
                conn.setAutoCommit(false);
                conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
                
                try (Statement stmt = conn.createStatement()) {
                    stmt.executeUpdate("INSERT INTO accounts VALUES (3, 'Charlie', 750.00)");
                    System.out.println("   Writer: Inserted Charlie account (uncommitted)");
                    latch.countDown();
                    
                    Thread.sleep(2000);
                    conn.commit();
                    System.out.println("   Writer: Committed transaction");
                }
            } catch (Exception e) {
                System.out.println("   Writer: " + e.getMessage());
            }
        });
        
        Thread reader = new Thread(() -> {
            try {
                latch.await();
                Thread.sleep(500);
                
                try (Connection conn = DbConnectUtil.getConnection()) {
                    conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
                    
                    try (Statement stmt = conn.createStatement();
                         ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as count FROM accounts")) {
                        
                        if (rs.next()) {
                            System.out.println("   Reader: First count = " + rs.getInt("count"));
                        }
                    }
                    
                    Thread.sleep(1500);
                    
                    try (Statement stmt = conn.createStatement();
                         ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as count FROM accounts")) {
                        
                        if (rs.next()) {
                            System.out.println("   Reader: Second count = " + rs.getInt("count") + " (NO PHANTOM READS!)");
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("   Reader: " + e.getMessage());
            }
        });
        
        writer.start();
        reader.start();
        
        try {
            writer.join();
            reader.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println();
    }

    public static void printIsolationLevelInfo() {
        System.out.println("=== Database Isolation Levels ===");
        System.out.println("1. READ_UNCOMMITTED:");
        System.out.println("   - Lowest isolation level");
        System.out.println("   - Allows dirty reads, non-repeatable reads, phantom reads");
        System.out.println("   - Best performance, least data consistency");
        System.out.println();
        
        System.out.println("2. READ_COMMITTED:");
        System.out.println("   - Prevents dirty reads");
        System.out.println("   - Allows non-repeatable reads, phantom reads");
        System.out.println("   - Good balance between performance and consistency");
        System.out.println();
        
        System.out.println("3. REPEATABLE_READ:");
        System.out.println("   - Prevents dirty reads, non-repeatable reads");
        System.out.println("   - Allows phantom reads");
        System.out.println("   - Better consistency, moderate performance impact");
        System.out.println();
        
        System.out.println("4. SERIALIZABLE:");
        System.out.println("   - Highest isolation level");
        System.out.println("   - Prevents all concurrency issues");
        System.out.println("   - Worst performance, best data consistency");
        System.out.println();
        
        System.out.println("Concurrency Issues:");
        System.out.println("- Dirty Read: Reading uncommitted data from another transaction");
        System.out.println("- Non-repeatable Read: Same row returns different values when read multiple times");
        System.out.println("- Phantom Read: New rows appear/disappear in subsequent reads of the same query");
    }
}

package main.com.library.dao.batch;

import main.com.library.util.DbConnectUtil;
import test.com.library.dao.batch.AuthorDaoTest;

import java.sql.*;

public class AuthorDao {
    private Connection connection = null;
    private Statement batchStatement=null;
    private PreparedStatement batchPreparedStatement=null;

    public void batchInsertAuthorsUsingStatement() {
        try {
            connection = DbConnectUtil.getConnection();
            // Start transaction
            connection.setAutoCommit(false);
            batchStatement = connection.createStatement();
            
            try {
                // Prepare batch insert
                batchStatement.addBatch("INSERT INTO author_detail (author_name, author_code, author_address, author_dob, author_email, author_education, author_university) VALUE " +
                        " ('J.K. Rowling11', 'JKR011', 'Edinburgh, Scotland', '1965-07-31', 'jk.rowling@example.com', 'BA in French and Classics', 'University of Exeter')");
                batchStatement.addBatch("INSERT INTO author_detail (author_name, author_code, author_address, author_dob, author_email, author_education, author_university) VALUE " +
                        " ('J.K. Rowling12', 'JKR012', 'Edinburgh, Scotland', '1965-07-31', 'jk.rowling@example.com', 'BA in French and Classics', 'University of Exeter')");
                batchStatement.addBatch("INSERT INTO author_detail (author_name, author_code, author_address, author_dob, author_email, author_education, author_university) VALUE " +
                        " ('J.K. Rowling13', 'JKR013', 'Edinburgh, Scotland', '1965-07-31', 'jk.rowling@example.com', 'BA in French and Classics', 'University of Exeter')");
                batchStatement.addBatch("INSERT INTO author_detail (author_name, author_code, author_address, author_dob, author_email, author_education, author_university) VALUE " +
                        " ('J.K. Rowling14', 'JKR014', 'Edinburgh, Scotland', '1965-07-31', 'jk.rowling@example.com', 'BA in French and Classics', 'University of Exeter')");
                batchStatement.addBatch("INSERT INTO author_detail (author_name, author_code, author_address, author_dob, author_email, author_education, author_university) VALUE " +
                        " ('J.K. Rowling15', 'JKR015', 'Edinburgh, Scotland', '1965-07-31', 'jk.rowling@example.com', 'BA in French and Classics', 'University of Exeter')");
                batchStatement.addBatch("INSERT INTO author_detail (author_name, author_code, author_address, author_dob, author_email, author_education, author_university) VALUE " +
                        " ('J.K. Rowling16', 'JKR016', 'Edinburgh, Scotland', '1965-07-31', 'jk.rowling@example.com', 'BA in French and Classics', 'University of Exeter')");

                // Execute the first batch
                batchStatement.executeBatch();
                
                // Create savepoint after first batch
                Savepoint savepointInsert = connection.setSavepoint();
                
                // Add more statements to batch
                batchStatement.addBatch("update author_detail set author_name='Mukund', author_email='mukund@gmail.com',author_education='PHD' where author_id=1000");
                batchStatement.addBatch("update author_detail set author_name='John Smith', author_email='john.smith@example.com', author_education='Masters' where author_id=1001");
                batchStatement.addBatch("update author_detail set author_name='Sarah Johnson', author_email='sarah.j@example.com', author_education='PhD' where author_id=1002");
                batchStatement.addBatch("update author_detail set author_name='Michael Brown', author_email='michael.b@example.com', author_education='Masters in Literature' where author_id=1003");
                batchStatement.addBatch("update author_detail set author_name='Emily Davis', author_email='emily.d@example.com', author_education='BA in English' where author_id=1004");
                batchStatement.addBatch("update author_detail set author_address='New York, USA', author_university='Harvard University' where author_id=1005");
                batchStatement.addBatch("update author_detail set author_address='Los Angeles, USA', author_university='Stanford University' where author_id=1001");
                batchStatement.addBatch("update author_detail set author_address='London, UK', author_university='Oxford University' where author_id=1002");
                batchStatement.addBatch("update author_detail set author_address='Paris, France', author_university='Sorbonne University' where author_id=1003");
                batchStatement.addBatch("update author_detail set author_address='Tokyo, Japan', author_university='Tokyo University' where author_id=1004");

                try {
                    // Execute the second batch
                    batchStatement.executeBatch();
                    // Create final savepoint
                    Savepoint savepointUpdate = connection.setSavepoint();
                    connection.commit();
                    System.out.println("All batch operations completed successfully");
                } catch (BatchUpdateException e) {
                    // Rollback to savepoint if second batch fails
                    System.out.println("Second batch failed, rolling back to savepointInsert");
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
            
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                    DbConnectUtil.closeConnection(connection);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void batchInsertAuthorsUsingPreparedStatement() {
        try {
            connection = DbConnectUtil.getConnection();
            // Start transaction
            connection.setAutoCommit(false);
            
            try {
                // Prepare INSERT statement
                String insertSql = "INSERT INTO author_detail (author_name, author_code, author_address, author_dob, author_email, author_education, author_university) VALUES (?, ?, ?, ?, ?, ?, ?)";
                batchPreparedStatement = connection.prepareStatement(insertSql);
                
                // Add INSERT statements to batch
                batchPreparedStatement.setString(1, "J.K. Rowling11");
                batchPreparedStatement.setString(2, "JKR011");
                batchPreparedStatement.setString(3, "Edinburgh, Scotland");
                batchPreparedStatement.setString(4, "1965-07-31");
                batchPreparedStatement.setString(5, "jk.rowling@example.com");
                batchPreparedStatement.setString(6, "BA in French and Classics");
                batchPreparedStatement.setString(7, "University of Exeter");
                batchPreparedStatement.addBatch();
                
                batchPreparedStatement.setString(1, "J.K. Rowling12");
                batchPreparedStatement.setString(2, "JKR012");
                batchPreparedStatement.setString(3, "Edinburgh, Scotland");
                batchPreparedStatement.setString(4, "1965-07-31");
                batchPreparedStatement.setString(5, "jk.rowling@example.com");
                batchPreparedStatement.setString(6, "BA in French and Classics");
                batchPreparedStatement.setString(7, "University of Exeter");
                batchPreparedStatement.addBatch();
                
                batchPreparedStatement.setString(1, "J.K. Rowling13");
                batchPreparedStatement.setString(2, "JKR013");
                batchPreparedStatement.setString(3, "Edinburgh, Scotland");
                batchPreparedStatement.setString(4, "1965-07-31");
                batchPreparedStatement.setString(5, "jk.rowling@example.com");
                batchPreparedStatement.setString(6, "BA in French and Classics");
                batchPreparedStatement.setString(7, "University of Exeter");
                batchPreparedStatement.addBatch();
                
                batchPreparedStatement.setString(1, "J.K. Rowling14");
                batchPreparedStatement.setString(2, "JKR014");
                batchPreparedStatement.setString(3, "Edinburgh, Scotland");
                batchPreparedStatement.setString(4, "1965-07-31");
                batchPreparedStatement.setString(5, "jk.rowling@example.com");
                batchPreparedStatement.setString(6, "BA in French and Classics");
                batchPreparedStatement.setString(7, "University of Exeter");
                batchPreparedStatement.addBatch();
                
                batchPreparedStatement.setString(1, "J.K. Rowling15");
                batchPreparedStatement.setString(2, "JKR015");
                batchPreparedStatement.setString(3, "Edinburgh, Scotland");
                batchPreparedStatement.setString(4, "1965-07-31");
                batchPreparedStatement.setString(5, "jk.rowling@example.com");
                batchPreparedStatement.setString(6, "BA in French and Classics");
                batchPreparedStatement.setString(7, "University of Exeter");
                batchPreparedStatement.addBatch();
                
                batchPreparedStatement.setString(1, "J.K. Rowling16");
                batchPreparedStatement.setString(2, "JKR016");
                batchPreparedStatement.setString(3, "Edinburgh, Scotland");
                batchPreparedStatement.setString(4, "1965-07-31");
                batchPreparedStatement.setString(5, "jk.rowling@example.com");
                batchPreparedStatement.setString(6, "BA in French and Classics");
                batchPreparedStatement.setString(7, "University of Exeter");
                batchPreparedStatement.addBatch();

                // Execute the first batch
                batchPreparedStatement.executeBatch();
                
                // Create savepoint after first batch
                Savepoint savepointInsert = connection.setSavepoint();
                
                // Prepare UPDATE statement
                String updateSql = "update author_detail set author_name=?, author_email=?, author_education=? where author_id=?";
                batchPreparedStatement = connection.prepareStatement(updateSql);
                
                // Add UPDATE statements to batch
                batchPreparedStatement.setString(1, "Mukund");
                batchPreparedStatement.setString(2, "mukund@gmail.com");
                batchPreparedStatement.setString(3, "PHD");
                batchPreparedStatement.setInt(4, 1000);
                batchPreparedStatement.addBatch();
                
                batchPreparedStatement.setString(1, "John Smith");
                batchPreparedStatement.setString(2, "john.smith@example.com");
                batchPreparedStatement.setString(3, "Masters");
                batchPreparedStatement.setInt(4, 1001);
                batchPreparedStatement.addBatch();
                
                batchPreparedStatement.setString(1, "Sarah Johnson");
                batchPreparedStatement.setString(2, "sarah.j@example.com");
                batchPreparedStatement.setString(3, "PhD");
                batchPreparedStatement.setInt(4, 1002);
                batchPreparedStatement.addBatch();
                
                batchPreparedStatement.setString(1, "Michael Brown");
                batchPreparedStatement.setString(2, "michael.b@example.com");
                batchPreparedStatement.setString(3, "Masters in Literature");
                batchPreparedStatement.setInt(4, 1003);
                batchPreparedStatement.addBatch();
                
                batchPreparedStatement.setString(1, "Emily Davis");
                batchPreparedStatement.setString(2, "emily.d@example.com");
                batchPreparedStatement.setString(3, "BA in English");
                batchPreparedStatement.setInt(4, 1004);
                batchPreparedStatement.addBatch();

                try {
                    // Execute the second batch
                    batchPreparedStatement.executeBatch();
                    
                    // Prepare address UPDATE statement
                    String addressUpdateSql = "update author_detail set author_address=?, author_university=? where author_id=?";
                    batchPreparedStatement = connection.prepareStatement(addressUpdateSql);
                    
                    // Add address UPDATE statements to batch
                    batchPreparedStatement.setString(1, "New York, USA");
                    batchPreparedStatement.setString(2, "Harvard University");
                    batchPreparedStatement.setInt(3, 1005);
                    batchPreparedStatement.addBatch();
                    
                    batchPreparedStatement.setString(1, "Los Angeles, USA");
                    batchPreparedStatement.setString(2, "Stanford University");
                    batchPreparedStatement.setInt(3, 1001);
                    batchPreparedStatement.addBatch();
                    
                    batchPreparedStatement.setString(1, "London, UK");
                    batchPreparedStatement.setString(2, "Oxford University");
                    batchPreparedStatement.setInt(3, 1002);
                    batchPreparedStatement.addBatch();
                    
                    batchPreparedStatement.setString(1, "Paris, France");
                    batchPreparedStatement.setString(2, "Sorbonne University");
                    batchPreparedStatement.setInt(3, 1003);
                    batchPreparedStatement.addBatch();
                    
                    batchPreparedStatement.setString(1, "Tokyo, Japan");
                    batchPreparedStatement.setString(2, "Tokyo University");
                    batchPreparedStatement.setInt(3, 1004);
                    batchPreparedStatement.addBatch();
                    
                    // Execute the third batch
                    batchPreparedStatement.executeBatch();
                    
                    // Create final savepoint
                    Savepoint savepointUpdate = connection.setSavepoint();
                    connection.commit();
                    System.out.println("All batch operations completed successfully using PreparedStatement");
                } catch (BatchUpdateException e) {
                    // Rollback to savepoint if second/third batch fails
                    System.out.println("Second/Third batch failed, rolling back to savepointInsert");
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
            
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                    DbConnectUtil.closeConnection(connection);
                }
                if (batchPreparedStatement != null) {
                    batchPreparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        AuthorDao authorDao = new AuthorDao();
    //    authorDao.batchInsertAuthorsUsingStatement();
      //  System.out.println("Batch Insert Using Statement Completed");
        authorDao.batchInsertAuthorsUsingPreparedStatement();
        System.out.println("Batch Insert Using PreparedStatement Completed");
    }
}

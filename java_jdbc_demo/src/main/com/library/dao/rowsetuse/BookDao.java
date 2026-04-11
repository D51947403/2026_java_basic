package main.com.library.dao.rowsetuse;

import main.com.library.dto.BookDTO;
import main.com.library.util.DbConnectUtil;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    public void addBook() {

        String insertBookQuery = "insert into book_detail (book_title, isbn, author_id, publisher_id, publication_date, price, genre, pages, language) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Get connection details from properties
        String driver = DbConnectUtil.getProperty("db.driver");
        String url = DbConnectUtil.getProperty("db.url");
        String username = DbConnectUtil.getProperty("db.username");
        String password = DbConnectUtil.getProperty("db.password");
        try {
            Class.forName(driver);

            CachedRowSet cachedRowSet = RowSetProvider.newFactory().createCachedRowSet();

            // Set connection properties
            cachedRowSet.setUrl(url);
            cachedRowSet.setUsername(username);
            cachedRowSet.setPassword(password);

            // Set the INSERT command
            cachedRowSet.setCommand(insertBookQuery);

            // Set parameter values
            cachedRowSet.setString(1, "The Great Gatsby");
            cachedRowSet.setString(2, "974-0-7432-7356-5");
            cachedRowSet.setInt(3, 1004); // author_id (Jane Austen)
            cachedRowSet.setInt(4, 2004); // publisher_id (Penguin Random House)
            cachedRowSet.setDate(5, java.sql.Date.valueOf("1925-04-10"));
            cachedRowSet.setBigDecimal(6, new java.math.BigDecimal("12.99"));
            cachedRowSet.setString(7, "Classic Fiction");
            cachedRowSet.setInt(8, 180);
            cachedRowSet.setString(9, "English");

            cachedRowSet.execute();
            cachedRowSet.close();
            System.out.println("Book added successfully!");
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    public void addBook(BookDTO book) {
        String insertBookQuery = "INSERT INTO book_detail (book_title, isbn, author_id, publisher_id, publication_date, price, genre, pages, language) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        // Get connection details from properties
        String driver = DbConnectUtil.getProperty("db.driver");
        String url = DbConnectUtil.getProperty("db.url");
        String username = DbConnectUtil.getProperty("db.username");
        String password = DbConnectUtil.getProperty("db.password");
        try {
            Class.forName(driver);
            CachedRowSet cachedRowSet = RowSetProvider.newFactory().createCachedRowSet();

            // Set connection properties
            cachedRowSet.setUrl(url);
            cachedRowSet.setUsername(username);
            cachedRowSet.setPassword(password);

            // Set the INSERT command
            cachedRowSet.setCommand(insertBookQuery);

            // Set parameter values from BookDTO
            cachedRowSet.setString(1, book.getBookTitle());
            cachedRowSet.setString(2, book.getIsbn());
            cachedRowSet.setInt(3, book.getAuthorId());
            cachedRowSet.setInt(4, book.getPublisherId());

            // Handle date conversion
            if (book.getPublicationDate() != null) {
                cachedRowSet.setDate(5, java.sql.Date.valueOf(book.getPublicationDate()));
            } else {
                cachedRowSet.setNull(5, java.sql.Types.DATE);
            }

            cachedRowSet.setBigDecimal(6, book.getPrice());
            cachedRowSet.setString(7, book.getGenre());
            cachedRowSet.setInt(8, book.getPages());
            cachedRowSet.setString(9, book.getLanguage());

            // Execute the INSERT
            cachedRowSet.execute();

            System.out.println("Book '" + book.getBookTitle() + "' inserted successfully!");
            cachedRowSet.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public List<BookDTO> getAllBooks() {
        List<BookDTO> books = new ArrayList<>();
        String selectQuery = "SELECT * FROM book_detail WHERE is_deleted = 'N'";
        
        // Get connection details from properties
        String driver = DbConnectUtil.getProperty("db.driver");
        String url = DbConnectUtil.getProperty("db.url");
        String username = DbConnectUtil.getProperty("db.username");
        String password = DbConnectUtil.getProperty("db.password");
        
        try {
            Class.forName(driver);
            JdbcRowSet jdbcRowSet = RowSetProvider.newFactory().createJdbcRowSet();
            
            // Set connection properties
            jdbcRowSet.setUrl(url);
            jdbcRowSet.setUsername(username);
            jdbcRowSet.setPassword(password);
            
            // Set SELECT command
            jdbcRowSet.setCommand(selectQuery);
            
            // Execute query
            jdbcRowSet.execute();
            
            // Iterate through results
            while (jdbcRowSet.next()) {
                BookDTO book = new BookDTO();
                
                // Set book details
                book.setBookId(jdbcRowSet.getInt("book_id"));
                book.setBookTitle(jdbcRowSet.getString("book_title"));
                book.setIsbn(jdbcRowSet.getString("isbn"));
                
                // Handle nullable foreign keys
                int authorId = jdbcRowSet.getInt("author_id");
                book.setAuthorId(jdbcRowSet.wasNull() ? null : authorId);
                
                int publisherId = jdbcRowSet.getInt("publisher_id");
                book.setPublisherId(jdbcRowSet.wasNull() ? null : publisherId);
                
                // Handle nullable date
                java.sql.Date pubDate = jdbcRowSet.getDate("publication_date");
                book.setPublicationDate(pubDate != null ? pubDate.toLocalDate() : null);
                
                book.setPrice(jdbcRowSet.getBigDecimal("price"));
                book.setGenre(jdbcRowSet.getString("genre"));
                book.setPages(jdbcRowSet.getInt("pages"));
                book.setLanguage(jdbcRowSet.getString("language"));
                
                // Set audit fields
                int createdBy = jdbcRowSet.getInt("created_by_admin");
                book.setCreatedByAdmin(jdbcRowSet.wasNull() ? null : createdBy);
                
                int updatedBy = jdbcRowSet.getInt("updated_by_admin");
                book.setUpdatedByAdmin(jdbcRowSet.wasNull() ? null : updatedBy);
                
                java.sql.Date createdDate = jdbcRowSet.getDate("created_date");
                book.setCreatedDate(createdDate != null ? createdDate.toLocalDate() : null);
                
                java.sql.Date updatedDate = jdbcRowSet.getDate("updated_date");
                book.setUpdatedDate(updatedDate != null ? updatedDate.toLocalDate() : null);
                
                book.setIsDeleted(jdbcRowSet.getString("is_deleted"));
                
                books.add(book);
            }
            
            System.out.println("Total books retrieved: " + books.size());
            jdbcRowSet.close();
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        
        return books;
    }
    public static void main(String[] args) {
        BookDao bookDao = new BookDao();

        // Method 1: Direct values
      //  bookDao.addBook();

        // Method 2: Using BookDTO
        BookDTO book = new BookDTO();
        book.setBookTitle("To Kill a Mockingbird");
        book.setIsbn("978-0-06-112008-5");
        book.setAuthorId(1009); // Toni Morrison
        book.setPublisherId(2002); // HarperCollins
        book.setPublicationDate(LocalDate.of(1960, 7, 11));
        book.setPrice(new BigDecimal("14.99"));
        book.setGenre("Classic Fiction");
        book.setPages(324);
        book.setLanguage("English");

        // bookDao.addBook(book);

      List<BookDTO> books =  bookDao.getAllBooks();
      System.out.println(books);
    }
}

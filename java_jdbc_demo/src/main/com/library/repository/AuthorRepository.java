package main.com.library.repository;

import main.com.library.dto.AuthorDTO;
import main.com.library.util.DbConnectUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AuthorRepository {
    public static void main(String[] args) throws SQLException {
     AuthorDTO  authorDTO = new AuthorDTO();
     authorDTO.setAuthorName("J.K. Rowling");
     authorDTO.setAuthorEmail("jkr@library.com");

     AuthorRepository authorRepository = new AuthorRepository();
     AuthorDTO savedAuthorDTO = authorRepository.addAuthor(authorDTO);
     System.out.println("Saved Author: " + savedAuthorDTO);

    }

    private static Connection connection;

    public AuthorDTO addAuthor(AuthorDTO authorDTO) throws SQLException {
          AuthorDTO savedAuthorDTO = null;
          Statement statement=null;
        try {
            connection = DbConnectUtil.getConnection();
             statement = connection.createStatement();
            String insertQuery = "INSERT INTO author_detail (author_name, author_email) VALUES ('" + authorDTO.getAuthorName() + "', '" + authorDTO.getAuthorEmail() + "')";
            int rowsAffected =  statement.executeUpdate(insertQuery, Statement.RETURN_GENERATED_KEYS);

            if (rowsAffected > 0) {
             ResultSet resultSet = statement.getGeneratedKeys();
             if (resultSet.next()) {
                authorDTO.setAuthorId(resultSet.getInt(1));
                 savedAuthorDTO=getAuthorById(authorDTO.getAuthorId());
             }
            }
        } catch (SQLException|ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            if (statement != null) {
                statement.close();
            }
            DbConnectUtil.closeConnection(connection);
        }

        return savedAuthorDTO;
    }

    public AuthorDTO getAuthorById(int authorId) throws SQLException {
        Statement statement=null;
        AuthorDTO authorDTO = new AuthorDTO();
        try{
            connection = DbConnectUtil.getConnection();
             statement = connection.createStatement();
            String fetchQuery ="select author_id,author_name,author_email from author_detail where author_id="+authorId+" and is_deleted='N'";
          ResultSet resultSet = statement.executeQuery(fetchQuery);
          while (resultSet.next()) {
              authorDTO.setAuthorId(resultSet.getInt("author_id"));
              authorDTO.setAuthorName(resultSet.getString("author_name"));
              authorDTO.setAuthorEmail(resultSet.getString("author_email"));

          }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            if (statement != null) {
                statement.close();
            }
            DbConnectUtil.closeConnection(connection);
        }
        return authorDTO;
    }
}

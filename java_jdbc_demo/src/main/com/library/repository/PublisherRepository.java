package main.com.library.repository;

import main.com.library.dto.PublisherDTO;
import main.com.library.util.DbConnectUtil;

import java.sql.*;


public class PublisherRepository {
     private static Connection connection;
     private static PreparedStatement preparedStatement;
     private static ResultSet resultSet;

    public PublisherDTO addPublisher(PublisherDTO publisherDTO) {
        PublisherDTO savedDTO = new PublisherDTO();
        String driverClassName = "com.mysql.cj.jdbc.Driver";
        String url ="jdbc:mysql://localhost:3306/library_db";
        String user = "****";
        String password = "****";
        String insertSql = "insert into publisher_detail (publisher_name,publisher_code,publisher_address,publisher_email,publisher_phone)\n" +
                "value (?,?,?,?,?);";
        try {
            Class.forName(driverClassName);
            connection = DriverManager.getConnection( url, user, password);

            preparedStatement = connection.prepareStatement(insertSql,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, publisherDTO.getPublisherName());
            preparedStatement.setString(2, publisherDTO.getPublisherCode());
            preparedStatement.setString(3, publisherDTO.getPublisherAddress());
            preparedStatement.setString(4, publisherDTO.getPublisherEmail());
            preparedStatement.setString(5, publisherDTO.getPublisherPhone());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet  resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                   int newPublisherId =resultSet.getInt(1);
                    savedDTO =getPublisherById(newPublisherId);
                }

            }
        } catch (ClassNotFoundException |SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
       System.out.println("Record inserted successfully"+savedDTO);

        return savedDTO;
    }

    public PublisherDTO getPublisherById(int publisherId) {
         PublisherDTO publisherDTO = new PublisherDTO();

         String selectSql = "select publisher_id,publisher_name,publisher_code,publisher_address,publisher_email,publisher_phone from publisher_detail where publisher_id =? " +
                 " and is_deleted ='N';";
        try {
            preparedStatement= DbConnectUtil.getConnection().prepareStatement(selectSql);
            preparedStatement.setInt(1,publisherId);

         resultSet =  preparedStatement.executeQuery();

            while (resultSet.next()){

                System.out.println(resultSet.getInt("publisher_id"));
                System.out.println(resultSet.getString("publisher_name"));
                System.out.println(resultSet.getString("publisher_code"));
                System.out.println(resultSet.getString("publisher_address"));
                System.out.println(resultSet.getString("publisher_email"));
                System.out.println(resultSet.getString("publisher_phone"));

                publisherDTO.setPublisherId(resultSet.getInt("publisher_id"));
                publisherDTO.setPublisherName(resultSet.getString("publisher_name"));
                publisherDTO.setPublisherCode(resultSet.getString("publisher_code"));
                publisherDTO.setPublisherAddress(resultSet.getString("publisher_address"));
                publisherDTO.setPublisherEmail(resultSet.getString("publisher_email"));
                publisherDTO.setPublisherPhone(resultSet.getString("publisher_phone"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {

            try {
                preparedStatement.close();
                if(resultSet != null)
                resultSet.close();
               DbConnectUtil.closeConnection(connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

        return publisherDTO;
    }

    public static void main(String args[]){
        PublisherDTO newPublisher=new PublisherDTO();
        newPublisher.setPublisherName("New Publisher");
        newPublisher.setPublisherCode("PUB009");
        newPublisher.setPublisherAddress("Patna");
        newPublisher.setPublisherEmail("newpublisher@gmail.com");
        newPublisher.setPublisherPhone("9876543210");

        PublisherRepository publisherRepository = new PublisherRepository();
        newPublisher = publisherRepository.addPublisher(newPublisher);
        System.out.println("Publisher added successfully");
        System.out.println(newPublisher);

        PublisherDTO publisherDTO = publisherRepository.getPublisherById(2011);
        System.out.println("Publisher details");
        System.out.println(publisherDTO);
    }
}

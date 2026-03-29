package main.com.library.demo.repository;

import main.com.library.dto.StudentDTO;

import java.sql.*;
import java.util.List;


public class StudentRepository {
    public static void main(String[] args) {
      StudentDTO studentDTO = new StudentDTO();
      studentDTO.setStudentName("Devendra Singraul");
      studentDTO.setStudentEmail("singraul.devendra@yahoo.com");

      StudentRepository studentRepository = new StudentRepository();
      StudentDTO savedStudentDTO = studentRepository.addStudent(studentDTO);
      System.out.println("Saved Student: " + savedStudentDTO);

    //  StudentDTO fetchedStudentDTO = studentRepository.getStudentById(savedStudentDTO.getStudentId());
    //  System.out.println("Fetched Student: " + fetchedStudentDTO);
    }

    public StudentDTO addStudent(StudentDTO studentDTO){
        Connection connection=null;
        Statement statement=null;
        StudentDTO savedStudentDTO = new StudentDTO();
        try {
             Class.forName("com.mysql.cj.jdbc.Driver");
             connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_db", "******", "*********");
             statement = connection.createStatement();
            String insertQuery = "insert into student_detail (student_name, student_email) values ('" + studentDTO.getStudentName() + "', '" + studentDTO.getStudentEmail() + "')";
            
            int rowInserted = statement.executeUpdate(insertQuery, Statement.RETURN_GENERATED_KEYS);

            if(rowInserted > 0){
                // Get the generated student_id
                ResultSet generatedKeys = statement.getGeneratedKeys();
                System.out.println("Record inserted successfully");
                if (generatedKeys.next()) {
                    studentDTO.setStudentId(generatedKeys.getInt(1));
                    savedStudentDTO = getStudentById(studentDTO.getStudentId());
                }
            }
            else{
                System.out.println("Failed to insert record");
            }


        } catch (ClassNotFoundException|SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return savedStudentDTO;
    }

    public  StudentDTO getStudentById(int studentId) {
        StudentDTO studentDTO = new StudentDTO();
        Connection connection=null;
        Statement statement=null;
        StudentDTO savedStudentDTO = new StudentDTO();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_db", "******", "*********");
            statement = connection.createStatement();
            String fetchQuery = "select student_id, student_name,student_email from student_detail where student_id ="+studentId+" and is_deleted='N'";
            ResultSet resultSet =statement.executeQuery(fetchQuery);

            while(resultSet.next()) {
                studentDTO.setStudentId(resultSet.getInt("student_id"));
                studentDTO.setStudentName(resultSet.getString("student_name"));
                studentDTO.setStudentEmail(resultSet.getString("student_email"));
            }

        } catch (ClassNotFoundException|SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return  studentDTO;
    }

}

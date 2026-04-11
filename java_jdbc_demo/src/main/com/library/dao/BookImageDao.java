package main.com.library.dao;

import main.com.library.util.DbConnectUtil;

import java.io.*;
import java.sql.*;

public class BookImageDao {

    public void addBookImage(int bookId, String frontImagePath, String backImagePath) {
        String insertQuery = "INSERT INTO book_image (book_id, front_image, back_image) VALUES (?, ?, ?)";
        
        try {
            Connection conn = DbConnectUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(insertQuery);
            
            // Set book_id
            pstmt.setInt(1, bookId);
            
            // Handle front image BLOB
            if (frontImagePath != null && !frontImagePath.trim().isEmpty()) {
                File frontImageFile = new File(frontImagePath);
                if (frontImageFile.exists()) {
                    FileInputStream frontFis = new FileInputStream(frontImageFile);
                    pstmt.setBinaryStream(2, frontFis, (int) frontImageFile.length());
                } else {
                    pstmt.setNull(2, Types.BLOB);
                    System.out.println("Front image file not found: " + frontImagePath);
                }
            } else {
                pstmt.setNull(2, Types.BLOB);
            }
            
            // Handle back image BLOB
            if (backImagePath != null && !backImagePath.trim().isEmpty()) {
                File backImageFile = new File(backImagePath);
                if (backImageFile.exists()) {
                    FileInputStream backFis = new FileInputStream(backImageFile);
                    pstmt.setBinaryStream(3, backFis, (int) backImageFile.length());
                } else {
                    pstmt.setNull(3, Types.BLOB);
                    System.out.println("Back image file not found: " + backImagePath);
                }
            } else {
                pstmt.setNull(3, Types.BLOB);
            }
            
            // Execute the insert
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Book image inserted successfully! Rows affected: " + rowsAffected);
            
            // Close resources
            pstmt.close();
            conn.close();
            
        } catch (ClassNotFoundException | SQLException | IOException e) {
            System.err.println("Error inserting book image: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void addBookImageWithByteArray(int bookId, byte[] frontImageData, byte[] backImageData) {
        String insertQuery = "INSERT INTO book_image (book_id, front_image, back_image) VALUES (?, ?, ?)";
        
        try {
            Connection conn = DbConnectUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(insertQuery);
            
            // Set book_id
            pstmt.setInt(1, bookId);
            
            // Set front image BLOB from byte array
            if (frontImageData != null && frontImageData.length > 0) {
                ByteArrayInputStream frontBais = new ByteArrayInputStream(frontImageData);
                pstmt.setBinaryStream(2, frontBais, frontImageData.length);
            } else {
                pstmt.setNull(2, Types.BLOB);
            }
            
            // Set back image BLOB from byte array
            if (backImageData != null && backImageData.length > 0) {
                ByteArrayInputStream backBais = new ByteArrayInputStream(backImageData);
                pstmt.setBinaryStream(3, backBais, backImageData.length);
            } else {
                pstmt.setNull(3, Types.BLOB);
            }
            
            // Execute the insert
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Book image inserted successfully using byte arrays! Rows affected: " + rowsAffected);
            
            // Close resources
            pstmt.close();
            conn.close();
            
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error inserting book image: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void getBookImage(int imageId) {
        String selectQuery = "SELECT image_id, book_id, front_image, back_image FROM book_image WHERE image_id = ?";
        
        try {
            Connection conn = DbConnectUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(selectQuery);
            pstmt.setInt(1, imageId);
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                int retrievedImageId = rs.getInt("image_id");
                int retrievedBookId = rs.getInt("book_id");
                
                System.out.println("Image ID: " + retrievedImageId);
                System.out.println("Book ID: " + retrievedBookId);
                
                // Get front image
                Blob frontBlob = rs.getBlob("front_image");
                if (frontBlob != null) {
                    int frontImageSize = (int) frontBlob.length();
                    System.out.println("Front image size: " + frontImageSize + " bytes");
                    
                    // Save front image to file
                    InputStream frontIs = frontBlob.getBinaryStream();
                    saveImageToFile(frontIs, "front_image_" + imageId + ".jpg");
                } else {
                    System.out.println("No front image found");
                }
                
                // Get back image
                Blob backBlob = rs.getBlob("back_image");
                if (backBlob != null) {
                    int backImageSize = (int) backBlob.length();
                    System.out.println("Back image size: " + backImageSize + " bytes");
                    
                    // Save back image to file
                    InputStream backIs = backBlob.getBinaryStream();
                    saveImageToFile(backIs, "back_image_" + imageId + ".jpg");
                } else {
                    System.out.println("No back image found");
                }
            } else {
                System.out.println("No book image found with ID: " + imageId);
            }
            
            // Close resources
            rs.close();
            pstmt.close();
            conn.close();
            
        } catch (ClassNotFoundException | SQLException | IOException e) {
            System.err.println("Error retrieving book image: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void saveImageToFile(InputStream inputStream, String fileName) throws IOException {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fileName);
            byte[] buffer = new byte[1024];
            int bytesRead;
            
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
            
            System.out.println("Image saved as: " + fileName);
        } finally {
            if (fos != null) {
                fos.close();
            }
            inputStream.close();
        }
    }
    
    public void updateBookImage(int imageId, String newFrontImagePath, String newBackImagePath) {
        String updateQuery = "UPDATE book_image SET front_image = ?, back_image = ? WHERE image_id = ?";
        
        try {
            Connection conn = DbConnectUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(updateQuery);
            
            // Handle front image BLOB
            if (newFrontImagePath != null && !newFrontImagePath.trim().isEmpty()) {
                File frontImageFile = new File(newFrontImagePath);
                if (frontImageFile.exists()) {
                    FileInputStream frontFis = new FileInputStream(frontImageFile);
                    pstmt.setBinaryStream(1, frontFis, (int) frontImageFile.length());
                } else {
                    pstmt.setNull(1, Types.BLOB);
                }
            } else {
                pstmt.setNull(1, Types.BLOB);
            }
            
            // Handle back image BLOB
            if (newBackImagePath != null && !newBackImagePath.trim().isEmpty()) {
                File backImageFile = new File(newBackImagePath);
                if (backImageFile.exists()) {
                    FileInputStream backFis = new FileInputStream(backImageFile);
                    pstmt.setBinaryStream(2, backFis, (int) backImageFile.length());
                } else {
                    pstmt.setNull(2, Types.BLOB);
                }
            } else {
                pstmt.setNull(2, Types.BLOB);
            }
            
            // Set image_id for WHERE clause
            pstmt.setInt(3, imageId);
            
            // Execute the update
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Book image updated successfully! Rows affected: " + rowsAffected);
            
            // Close resources
            pstmt.close();
            conn.close();
            
        } catch (ClassNotFoundException | SQLException | IOException e) {
            System.err.println("Error updating book image: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        BookImageDao bookImageDao = new BookImageDao();
        
        // Example 1: Add book image using file paths
       //  bookImageDao.addBookImage(3001, "D:\\2026_java_notes\\app_images\\front_image.jpg", "D:\\2026_java_notes\\app_images\\back_image.jpg");
        
        // Example 2: Add book image using byte arrays
        byte[] frontImageData = new byte[1024];
        byte[] backImageData = new byte[1024];
        try {
            frontImageData=bookImageDao.convertFileToByteArray("D:\\2026_java_notes\\app_images\\front_image.jpg");
            backImageData=bookImageDao.convertFileToByteArray("D:\\2026_java_notes\\app_images\\back_image.jpg");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
       // bookImageDao.addBookImageWithByteArray(3002, frontImageData, backImageData);
        
        // Example 3: Retrieve book image
        // bookImageDao.getBookImage(7001);
        
        // Example 4: Update book image
         bookImageDao.updateBookImage(7000, "D:\\2026_java_notes\\app_images\\front_image.jpg", "D:\\2026_java_notes\\app_images\\back_image.jpg");

    }

    private byte[] convertImageToByteArray(InputStream inputStream, String fileName) throws IOException {
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }

            byte[] imageBytes = baos.toByteArray();
            System.out.println("Image '" + fileName + "' converted to byte array. Size: " + imageBytes.length + " bytes");

            return imageBytes;
        } finally {
            // Close input stream
            if (inputStream != null) {
                inputStream.close();
            }
            // Close output stream
            if (baos != null) {
                baos.close();
            }
        }
    }
    
    // Additional utility method to convert file to byte array
    public byte[] convertFileToByteArray(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException("File not found: " + filePath);
        }
        
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            return convertImageToByteArray(fis, file.getName());
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
    }
}

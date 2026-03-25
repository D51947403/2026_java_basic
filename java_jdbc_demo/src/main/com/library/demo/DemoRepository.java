package main.com.library.demo;

import java.sql.*;

class DemoRepository {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/library_db"; // Database details
        String username = "******"; // MySQL credentials
        String password = "******";//MySQL credentials
        String mySqlDriver="com.mysql.cj.jdbc.Driver";
        String query = "select * from admin_detail"; // Query to be run

        // Load and register the driver
        Class.forName(mySqlDriver);

        // Establish connection
        Connection con = DriverManager.getConnection(url, username, password);
        System.out.println("Connection Established successfully");

        // Create a statement
        Statement st = con.createStatement();

        // Execute the query
        ResultSet rs = st.executeQuery(query);

        // Process the results
        int i=1;
        while (rs.next()) {
            String value = rs.getString(i);
            System.out.print(value+" "); // Print result on console
        }
        System.out.println();
        // Close the statement and connection
        st.close();
        con.close();
        System.out.println("Connection Closed....");
    }
}
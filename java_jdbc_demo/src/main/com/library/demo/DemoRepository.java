package main.com.library.demo;

import java.sql.*;

class DemoRepository {
    public static void main(String[] args) throws Exception {
        //MySQL
        String url = "jdbc:mysql://localhost:3306/library_db"; // Database details
        String username = "********"; // MySQL credentials
        String password = "**********";//MySQL credentials
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
        while (rs.next()) {
            String value1 = rs.getString(1);
            String value2 = rs.getString(2);
            String adminCode =rs.getString("admin_code");
            int admin_salary =rs.getInt("admin_salary");
            System.out.print(value1+" "+value2+" "+adminCode+" "+admin_salary);
            System.out.println();
        }
       // System.out.println();
        // Close the statement and connection
        st.close();
        con.close();
        System.out.println("Connection Closed....");
    }
}
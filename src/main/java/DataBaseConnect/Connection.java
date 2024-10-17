package DataBaseConnect;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
    private java.sql.Connection con = null; // Variable to hold the database connection

    // Method to establish a connection to the database
    public void connect(String location, int delay) {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // Handle the case where the driver is not found
            System.out.println("Could not load SQL driver");
            System.exit(-1); // Exit the program if the driver cannot be loaded
        }

        int retries = 10; // Number of retries for connection
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait for a specified delay before attempting to connect
                Thread.sleep(delay);
                // Attempt to connect to the database
                con = DriverManager.getConnection("jdbc:mysql://" + location
                                + "/world?allowPublicKeyRetrieval=true&useSSL=false",
                        "root", "example");
                System.out.println("Successfully connected"); // Connection successful
                break; // Exit the loop if the connection is successful
            } catch (SQLException sqle) {
                // Handle SQL exceptions during connection attempts
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage()); // Print error message
            } catch (InterruptedException ie) {
                // Handle interruptions while waiting
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    // Method to retrieve the current database connection
    public java.sql.Connection getConnection() {
        return con;
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect() {
        if (con != null) {
            try {
                // Close the database connection
                con.close();
                con = null; // Set the connection variable to null after closing
            } catch (Exception e) {
                // Handle exceptions that may occur during disconnection
                System.out.println("Error closing connection to database");
            }
        }
    }
}
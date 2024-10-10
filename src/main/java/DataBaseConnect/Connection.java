package DataBaseConnect;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
    private java.sql.Connection con = null;
    public void connect()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Connection logic here
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            throw new RuntimeException("Could not load SQL driver", e); // or any custom exception
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
                System.out.println("Successfully Connected the World Database");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    // to retrieve the Connection
    public java.sql.Connection getConnection() {
        return con;
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
                con = null;
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }
}
package UnitTest;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import DataBaseConnect.Connection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnectTest {

    private Connection connection;
    private java.sql.Connection mockSqlConnection;

    @BeforeEach
    public void setUp() {
        // Initialize the connection object before each test
        connection = new Connection();
        // Mock the SQL connection
        mockSqlConnection = mock(java.sql.Connection.class);
    }

    @Test
    public void testSuccessfulConnection() throws Exception {
        // Mock DriverManager to return the mock connection
        mockStatic(DriverManager.class);
        when(DriverManager.getConnection(anyString(), anyString(), anyString()))
                .thenReturn(mockSqlConnection);

        // Call the connect method
        connection.connect();

        // Verify that the connection was successful by checking if getConnection() returns the mock connection
        assertNotNull(connection.getConnection());
        assertEquals(mockSqlConnection, connection.getConnection());

        // Verify the success message printed out
        // Here, you can capture console output to check for specific messages, if needed.
    }

    @Test
    public void testFailedConnection() throws Exception {
        // Mock DriverManager to throw an SQLException
        mockStatic(DriverManager.class);
        when(DriverManager.getConnection(anyString(), anyString(), anyString()))
                .thenThrow(new SQLException("Connection failed"));

        // Call the connect method and expect retries
        connection.connect();

        // Verify that after all retries, connection is still null
        assertNull(connection.getConnection());
    }

    @Test
    public void testDisconnect() throws SQLException {
        // Mock the SQL connection's close method
        doNothing().when(mockSqlConnection).close();

        // Set the connection to the mock connection
        connection.connect();
        connection.disconnect();

        // Verify that the close method was called
        verify(mockSqlConnection, times(1)).close();
    }

    @Test
    public void testDisconnectWithNullConnection() {
        // Ensure no exceptions occur if connection is null
        connection.disconnect();
        // Since there's no connection, nothing should happen, and the test should pass
    }
}


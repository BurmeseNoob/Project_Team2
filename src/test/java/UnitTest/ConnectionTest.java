package UnitTest;

import static org.mockito.Mockito.*; // Import mockito methods for creating mock objects
import static org.junit.jupiter.api.Assertions.*; // Import assertions for unit testing
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class ConnectionTest {

    // Instance of the class we are testing
    private DataBaseConnect.Connection connection;

    // Static mocking of DriverManager (which is used to establish the database connection)
    private MockedStatic<DriverManager> mockedDriverManager;

    /**
     * This method runs before each test. It sets up the required objects for the test.
     */
    @BeforeEach
    void setUp() {
        // Initialize the Connection class and mock the DriverManager
        connection = new DataBaseConnect.Connection();
        mockedDriverManager = mockStatic(DriverManager.class);
    }

    /**
     * This method runs after each test to clean up resources.
     */
    @AfterEach
    void tearDown() {
        // Close the static mock and disconnect the connection to clean up
        mockedDriverManager.close();
        connection.disconnect();
    }

    /**
     * Test to check if the connection is established successfully.
     * It mocks the database connection process to simulate a successful connection.
     */
    @Test
    void connectSuccess() throws SQLException {
        // Arrange: Set up a mock Connection object
        Connection mockConnection = mock(Connection.class);

        // Simulate DriverManager returning the mock connection when called
        mockedDriverManager.when(() -> DriverManager.getConnection(anyString(), anyString(), anyString()))
                .thenReturn(mockConnection);

        // Act: Attempt to connect using the connection object
        connection.connect();

        // Assert: Ensure the connection object is not null (indicating success)
        assertNotNull(connection.getConnection(), "Connection should not be null after successful connection.");
    }

    /**
     * Test to check if the connection is properly disconnected.
     * It first establishes a connection, then disconnects, and checks if the connection is null afterward.
     */
    @Test
    void disconnectTest() throws SQLException {
        // Arrange: Set up a mock Connection object
        Connection mockConnection = mock(Connection.class);
        mockedDriverManager.when(() -> DriverManager.getConnection(anyString(), anyString(), anyString()))
                .thenReturn(mockConnection);

        // Establish the connection first
        connection.connect();

        // Act: Disconnect the connection
        connection.disconnect();

        // Assert: Ensure the connection is null after disconnecting
        assertNull(connection.getConnection(), "Connection should be null after disconnection.");
    }

    /**
     * Additional test to verify that the connection is null after calling disconnect.
     */
    @Test
    public void testGetConnectionAfterDisconnect() {
        // Act: Establish the connection and then disconnect
        connection.connect();
        connection.disconnect();

        // Assert: After disconnect, the connection should be null
        assertNull(connection.getConnection());
    }
}

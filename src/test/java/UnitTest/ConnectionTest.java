package UnitTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
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

    private DataBaseConnect.Connection connection;
    private MockedStatic<DriverManager> mockedDriverManager;

    @BeforeEach
    void setUp() {
        connection = new DataBaseConnect.Connection();
        mockedDriverManager = mockStatic(DriverManager.class);
    }

    @AfterEach
    void tearDown() {
        mockedDriverManager.close();
        connection.disconnect();
    }

    @Test
    void connectSuccess() throws SQLException {
        // Arrange
        Connection mockConnection = mock(Connection.class);
        mockedDriverManager.when(() -> DriverManager.getConnection(anyString(), anyString(), anyString()))
                .thenReturn(mockConnection);

        // Act
        connection.connect("localhost:33060", 30000);

        // Assert
        assertNotNull(connection.getConnection(), "Connection should not be null after successful connection.");
    }

    @Test
    void disconnectTest() throws SQLException {
        // Arrange
        Connection mockConnection = mock(Connection.class);
        mockedDriverManager.when(() -> DriverManager.getConnection(anyString(), anyString(), anyString()))
                .thenReturn(mockConnection);
        connection.connect("localhost:33060", 30000);  // Establish connection first

        // Act
        connection.disconnect();

        // Assert
        assertNull(connection.getConnection(), "Connection should be null after disconnection.");
    }
    @Test
    public void testGetConnectionAfterDisconnect() {
        connection.connect("localhost:33060", 30000); // Establish connection
        connection.disconnect(); // Call disconnect method
        assertNull(connection.getConnection()); // Ensure connection is null after disconnect
    }
}

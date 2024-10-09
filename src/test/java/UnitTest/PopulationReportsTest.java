package UnitTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import Reports.PopulationReports;
import ProjectTeam2.PopulationLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class PopulationReportsTest {
    private PopulationReports populationReports;
    private Connection mockConnection;
    private Statement mockStatement;
    private ResultSet mockResultSet;

    @BeforeEach
    public void setUp() throws SQLException {
        // Mock the necessary SQL objects
        mockConnection = mock(Connection.class);
        mockStatement = mock(Statement.class);
        mockResultSet = mock(ResultSet.class);

        // Assign the mocked connection into PopulationReports
        populationReports = new PopulationReports(mockConnection);

        // Mock the behavior of creating a statement and executing a query
        when(mockConnection.createStatement()).thenReturn(mockStatement);
    }

    //Test the display report method with empty value
    @Test
    void displayReportTestEmpty()
    {
        ArrayList<PopulationLevel> data = new ArrayList<PopulationLevel>();
        populationReports.displayFormatforPopulationReports(data);
    }

    //Test the display report method with NULL value
    @Test
    void displayReportTestNull()
    {
        ArrayList<PopulationLevel> data = new ArrayList<PopulationLevel>();
        data.add(null);
        populationReports.displayFormatforPopulationReports(data);
    }

    /**
     * Test about Population level in CONTINENT
     */
    @Test
    public void testGetPopulationLevelInContinent() throws SQLException {
        // Mock the SQL query result set
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        // Mock the behavior of resultSet.next() and resultSet getters
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);

        when(mockResultSet.getString("Name")).thenReturn("Asia");
        when(mockResultSet.getLong("TotalPopulation")).thenReturn(4500000000L);
        when(mockResultSet.getLong("PopulationInCities")).thenReturn(2000000000L);
        when(mockResultSet.getDouble("PercentPopulationInCities")).thenReturn(44.44);
        when(mockResultSet.getLong("PopulationNotInCities")).thenReturn(2500000000L);
        when(mockResultSet.getDouble("PercentPopulationNotInCities")).thenReturn(55.56);

        // Call the method to test
        ArrayList<PopulationLevel> result = populationReports.getPopulationLevelInContinent();

        // Verify with the size
        assertEquals(1, result.size());

        // Verify the result With the mock data
        PopulationLevel popLevel = result.get(0);
        assertEquals("Asia", popLevel.getName());
        assertEquals(4500000000L, popLevel.getTotalPopulation());
        assertEquals(2000000000L, popLevel.getPopulationInCities());
        assertEquals(44.44, popLevel.getPercentagesInCities(), 0.01);
        assertEquals(2500000000L, popLevel.getPopulationNotInCities());
        assertEquals(55.56, popLevel.getPercentagesNotInCities(), 0.01);
    }


    @Test
    public void testDisplayFormatforPopulationReports() {
        // Create a list of PopulationLevel objects
        ArrayList<PopulationLevel> popList = new ArrayList<>();
        popList.add(new PopulationLevel("Europe", 741000000, 400000000, 54.0, 341000000, 46.0));

        // Call the display method
        populationReports.displayFormatforPopulationReports(popList);
    }

    @Test
    public void testDisplayPopulationLevelInContinent() throws SQLException {
        // Set up the mock SQL query to return the mocked ResultSet when executed
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        // Simulate the ResultSet returning one row of data and then stopping (returning false)
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);

        // Set up the mock data for the columns in the ResultSet
        when(mockResultSet.getString("Name")).thenReturn("Africa");
        when(mockResultSet.getLong("TotalPopulation")).thenReturn(1300000000L); // Total population of Africa
        when(mockResultSet.getLong("PopulationInCities")).thenReturn(500000000L); // Population in cities
        when(mockResultSet.getDouble("PercentPopulationInCities")).thenReturn(38.46); // Percentage of population in cities
        when(mockResultSet.getLong("PopulationNotInCities")).thenReturn(800000000L); // Population not in cities
        when(mockResultSet.getDouble("PercentPopulationNotInCities")).thenReturn(61.54); // Percentage of population not in cities

        // Call the method to display the population report for continents, which will print to the console
        populationReports.displayPopulationLevelInContinent();
    }

    /**
     * Test about Population level in REGION
     */
    @Test
    public void testGetPopulationLevelInRegion() throws SQLException {
        // Set up mock data for the ResultSet
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        // Simulate ResultSet having one row and then no more data
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);

        // Set up the expected values for the ResultSet columns
        when(mockResultSet.getString("Name")).thenReturn("Southern Europe");
        when(mockResultSet.getLong("TotalPopulation")).thenReturn(500000000L);
        when(mockResultSet.getLong("PopulationInCities")).thenReturn(300000000L);
        when(mockResultSet.getDouble("PercentPopulationInCities")).thenReturn(60.0);
        when(mockResultSet.getLong("PopulationNotInCities")).thenReturn(200000000L);
        when(mockResultSet.getDouble("PercentPopulationNotInCities")).thenReturn(40.0);

        // Call the method to test
        ArrayList<PopulationLevel> result = populationReports.getPopulationLevelInRegion();

        // Check if the result is not null and contains one entry
        assertNotNull(result);
        assertEquals(1, result.size());

        // Verify the result With the mock data
        PopulationLevel popLevel = result.get(0);
        assertEquals("Southern Europe", popLevel.getName());
        assertEquals(500000000L, popLevel.getTotalPopulation());
        assertEquals(300000000L, popLevel.getPopulationInCities());
        assertEquals(60.0, popLevel.getPercentagesInCities());
        assertEquals(200000000L, popLevel.getPopulationNotInCities());
        assertEquals(40.0, popLevel.getPercentagesNotInCities());
    }

    @Test
    public void testDisplayPopulationLevelInRegion() throws SQLException {
        // Set up the mock SQL query to return the mocked ResultSet when executed
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        // Simulate the ResultSet returning one row of data and then stopping (returning false)
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);

        // Set up the expected values for the ResultSet columns
        when(mockResultSet.getString("Name")).thenReturn("Southern Europe");
        when(mockResultSet.getLong("TotalPopulation")).thenReturn(500000000L);
        when(mockResultSet.getLong("PopulationInCities")).thenReturn(300000000L);
        when(mockResultSet.getDouble("PercentPopulationInCities")).thenReturn(60.0);
        when(mockResultSet.getLong("PopulationNotInCities")).thenReturn(200000000L);
        when(mockResultSet.getDouble("PercentPopulationNotInCities")).thenReturn(40.0);

        // Call the method to display the population report for continents, which will print to the console
        populationReports.displayPopulationLevelInRegion();
    }

    /**
     * Test about Population level in COUNTRY
     */
    @Test
    public void testGetPopulationLevelInCountry() throws SQLException {
    // Mock the ResultSet to return expected data for the SQL query
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        // Simulate the ResultSet returning one row, then no more rows
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);

        // Provide mock data
        when(mockResultSet.getString("Name")).thenReturn("USA");
        when(mockResultSet.getLong("TotalPopulation")).thenReturn(331000000L);
        when(mockResultSet.getLong("PopulationInCities")).thenReturn(250000000L);
        when(mockResultSet.getDouble("PercentPopulationInCities")).thenReturn(75.53);
        when(mockResultSet.getLong("PopulationNotInCities")).thenReturn(81000000L);
        when(mockResultSet.getDouble("PercentPopulationNotInCities")).thenReturn(24.47);

        // Call the method being tested
        ArrayList<PopulationLevel> result = populationReports.getPopulationLevelInCountry();

        // Validate the result is not null and contains one entry
        assertNotNull(result);
        assertEquals(1, result.size());

        // Check if the returned PopulationLevel matches the mock data
        PopulationLevel popLevel = result.get(0);
        assertEquals("USA", popLevel.getName());
        assertEquals(331000000L, popLevel.getTotalPopulation());
        assertEquals(250000000L, popLevel.getPopulationInCities());
        assertEquals(75.53, popLevel.getPercentagesInCities());
        assertEquals(81000000L, popLevel.getPopulationNotInCities());
        assertEquals(24.47, popLevel.getPercentagesNotInCities());
    }

    @Test
    public void testDisplayPopulationLevelInCountry() throws SQLException
    {
        // Set up the mock SQL query to return the mocked ResultSet when executed
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        // Simulate the ResultSet returning one row of data and then stopping (returning false)
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);

        // Provide mock data
        when(mockResultSet.getString("Name")).thenReturn("USA");
        when(mockResultSet.getLong("TotalPopulation")).thenReturn(331000000L);
        when(mockResultSet.getLong("PopulationInCities")).thenReturn(250000000L);
        when(mockResultSet.getDouble("PercentPopulationInCities")).thenReturn(75.53);
        when(mockResultSet.getLong("PopulationNotInCities")).thenReturn(81000000L);
        when(mockResultSet.getDouble("PercentPopulationNotInCities")).thenReturn(24.47);

        // Call the method to display the population report for continents, which will print to the console
        populationReports.displayPopulationLevelInCountry();
    }





}

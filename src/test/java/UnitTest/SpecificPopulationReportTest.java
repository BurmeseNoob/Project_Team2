package UnitTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import ProjectTeam2.SpecificPopulation;
import Reports.PopulationReports;
import Reports.SpecificPopulationReports;
import ProjectTeam2.PopulationLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SpecificPopulationReportTest {
    private SpecificPopulationReports spr;
    private Connection mockConnection;
    private Statement mockStatement;
    private ResultSet mockResultSet;

    //Initiate  the necessary object
    @BeforeEach
    public void setUp() throws SQLException {
        // Mock the necessary SQL objects
        mockConnection = mock(Connection.class);
        mockStatement = mock(Statement.class);
        mockResultSet = mock(ResultSet.class);

        // Inject the mocked connection into SpecificPopulationReports
        spr = new SpecificPopulationReports(mockConnection);

        // Mock the behavior of creating a statement and executing a query
        when(mockConnection.createStatement()).thenReturn(mockStatement);
    }


    // test the display format method with null and empty list
    @Test
    public void displayTestEmpty() throws SQLException {
        ArrayList<SpecificPopulation> data = new ArrayList<SpecificPopulation>();
        spr.DisplayReportFormat(data);
    }

    @Test
    public void displayTestNull() throws SQLException {
        ArrayList<SpecificPopulation> data = new ArrayList<SpecificPopulation>();
        data.add(null);
        spr.DisplayReportFormat(data);
    }

    /**
     * Test about World Population Get Methods and Display Format Methods
     */
    @Test
    public void testGetWorldPopulation() throws SQLException {
        // Mock the SQL query result set
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        // Mock the behavior of resultSet.next() and resultSet getters
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);

        // test data
        // Two colum of ("Name" & "TotalPopulation")
        when(mockResultSet.getString("name")).thenReturn("World");
        when(mockResultSet.getLong("WorldPopulation")).thenReturn(7800000000L);

        // Call the method to test
        ArrayList<SpecificPopulation> result = spr.getWorldPopulation();

        // Verify with the size
        assertEquals(1, result.size());

        // Verify the result With the mock data
        SpecificPopulation sp = result.get(0);
        assertEquals("World", sp.getName());
        assertEquals(7800000000L, sp.getPopulation());
    }


    @Test
    public void testDisplayWorldPopulation() throws SQLException
    {

        //creating the specific population arraylist to test
        ArrayList<SpecificPopulation> spList = new ArrayList<>();

        //Test wil null list
        spList.add(null);
        spr.displayContinentPopulation(spList);

        //Test with Data
        spList.add(new SpecificPopulation("World", 7800000000L));
        spr.displayContinentPopulation(spList);
    }


    /**
     * Test about Region Population Get Methods and Display Format Methods
     */

    @Test
    public void testGetContinentPopulation() throws SQLException
    {
        // Mock the SQL query result set
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        // Mock the behavior of resultSet.next() and resultSet getters
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);

        // test data
        // Two colum of ("Name" & "TotalPopulation")
        when(mockResultSet.getString("Continent")).thenReturn("Asia");
        when(mockResultSet.getLong("ContinentPopulation")).thenReturn(4800000000L);

        // Call the method to test
        ArrayList<SpecificPopulation> result = spr.getContinentPopulation();

        // Verify with the size
        assertEquals(1, result.size());

        // Verify the result With the mock data
        SpecificPopulation sp = result.get(0);
        assertEquals("Asia", sp.getName());
        assertEquals(4800000000L, sp.getPopulation());
    }

    @Test
    public void testDisplayContinentPopulation() throws SQLException
    {
        //creating the specific population arraylist to test
        ArrayList<SpecificPopulation> spList = new ArrayList<>();

        //Test wil null list
        spList.add(null);
        spr.displayContinentPopulation(spList);

        //Test with Data
        spList.add(new SpecificPopulation("Asia", 4800000000L));
        spr.displayContinentPopulation(spList);

    }


    /**
     * Test about Region Population Get Methods and Display Format Methods
     */
    @Test
    public void testGetRegionPopulation() throws SQLException
    {
        // Mock the SQL query result set
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        // Mock the behavior of resultSet.next() and resultSet getters
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);

        // test data
        // Two colum of ("Name" & "TotalPopulation")
        when(mockResultSet.getString("Region")).thenReturn("South Asia");
        when(mockResultSet.getLong("RegionPopulation")).thenReturn(1800000000L);

        // Call the method to test
        ArrayList<SpecificPopulation> result = spr.getRegionPopulation();

        // Verify with the size
        assertEquals(1, result.size());

        // Verify the result With the mock data
        SpecificPopulation sp = result.get(0);
        assertEquals("South Asia", sp.getName());
        assertEquals(1800000000L, sp.getPopulation());
    }

    @Test
    public void testDisplayRegionPopulation()throws SQLException
    {
        //creating the specific population arraylist to test
        ArrayList<SpecificPopulation> spList = new ArrayList<>();

        //Test wil null list
        spList.add(null);
        spr.displayRegionPopulation(spList);

        //Test with Data
        spList.add(new SpecificPopulation("South Asia", 4800000000L));
        spr.displayRegionPopulation(spList);
    }

    /**
     * Test about Country Population Get Methods and Display Format Methods
     */
    @Test
    public void testGetCountryPopulation() throws SQLException
    {
        // Mock the SQL query result set
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        // Mock the behavior of resultSet.next() and resultSet getters
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);

        // test data
        // Two colum of ("Name" & "TotalPopulation")
        when(mockResultSet.getString("Name")).thenReturn("USA");
        when(mockResultSet.getLong("CountryPopulation")).thenReturn(4800000000L);

        // Call the method to test
        ArrayList<SpecificPopulation> result = spr.getCountryPopulation();

        // Verify with the size
        assertEquals(1, result.size());

        // Verify the result With the mock data
        SpecificPopulation sp = result.get(0);
        assertEquals("USA", sp.getName());
        assertEquals(4800000000L, sp.getPopulation());
    }

    @Test
    public void testDisplayCountryPopulation()throws SQLException
    {
        //creating the specific population arraylist to test
        ArrayList<SpecificPopulation> spList = new ArrayList<>();

        //Test wil null list
        spList.add(null);
        spr.displayCountryPopulation(spList);

        //Test with Data
        spList.add(new SpecificPopulation("USA", 4800000000L));
        spr.displayCountryPopulation(spList);
    }

    /**
     * Test about District Population Get Methods and Display Format Methods
     */

    @Test
    public void testGetDistrictPopulation() throws SQLException
    {
        // Mock the SQL query result set
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        // Mock the behavior of resultSet.next() and resultSet getters
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);

        // test data
        // Two colum of ("Name" & "TotalPopulation")
        when(mockResultSet.getString("District")).thenReturn("LA");
        when(mockResultSet.getLong("DistrictPopulation")).thenReturn(10300000L);

        // Call the method to test
        ArrayList<SpecificPopulation> result = spr.getDistrictPopulation();

        // Verify with the size
        assertEquals(1, result.size());

        // Verify the result With the mock data
        SpecificPopulation sp = result.get(0);
        assertEquals("LA", sp.getName());
        assertEquals(10300000L, sp.getPopulation());
    }

    @Test
    public void testDisplayDistrictPopulation()throws SQLException
    {
        //creating the specific population arraylist to test
        ArrayList<SpecificPopulation> spList = new ArrayList<>();

        //Test wil null list
        spList.add(null);
        spr.displayDistrictPopulation(spList);

        //Test with Data
        spList.add(new SpecificPopulation("LA", 10300000L));
        spr.displayDistrictPopulation(spList);
    }


    /**
     * Test about City Population Get Methods and Display Format Methods
     */
    @Test
    public void testGetCityPopulation() throws SQLException
    {
        // Mock the SQL query result set
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        // Mock the behavior of resultSet.next() and resultSet getters
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);

        // test data
        // Two colum of ("Name" & "TotalPopulation")
        when(mockResultSet.getString("Name")).thenReturn("New York");
        when(mockResultSet.getLong("CityPopulation")).thenReturn(8336817L);

        // Call the method to test
        ArrayList<SpecificPopulation> result = spr.getCityPopulation();

        // Verify with the size
        assertEquals(1, result.size());

        // Verify the result With the mock data
        SpecificPopulation sp = result.get(0);
        assertEquals("New York", sp.getName());
        assertEquals(8336817L, sp.getPopulation());
    }


    @Test
    public void testDisplayCityPopulation()throws SQLException
    {
        //creating the specific population arraylist to test
        ArrayList<SpecificPopulation> spList = new ArrayList<>();

        //Test wil null list
        spList.add(null);
        spr.displayCityPopulation(spList);

        //Test with Data
        spList.add(new SpecificPopulation("New York", 8336817L));
        spr.displayCityPopulation(spList);
    }















}

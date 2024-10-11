package UnitTest;

import ProjectTeam2.Country;
import Reports.CountryReports;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.PreparedStatement;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CountryReportsTest {

    private Connection mockConnection;
    private Statement mockStatement;
    private ResultSet mockResultSet;
    private CountryReports countryReports;

    @BeforeEach
    public void setUp() throws Exception {
        // Create mock objects
        mockConnection = mock(Connection.class);
        mockStatement = mock(Statement.class);
        mockResultSet = mock(ResultSet.class);

        // Initialize the class with the mock connection
        countryReports = new CountryReports(mockConnection);

        // Mock the behavior of creating a statement and executing a query
        when(mockConnection.createStatement()).thenReturn(mockStatement);
    }

    // test the display table format method with null and empty list
    @Test
    public void displayTestEmpty() throws SQLException {
        ArrayList<Country> data = new ArrayList<>();
        countryReports.displayTableFormat(data);
    }

    @Test
    public void displayTestNull() throws SQLException {
        ArrayList<Country> data = new ArrayList<>();
        data.add(null);
        countryReports.displayTableFormat(data);
    }

    // Test for getDescendingPopulationOfCountry method
    @Test
    public void testGetDescendingPopulationOfCountry() throws Exception {
        // Set up mock behavior for the SQL query
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        // Set up mock result data
        when(mockResultSet.next()).thenReturn(true, true, false); // Simulate two rows
        when(mockResultSet.getString("Code")).thenReturn("USA", "CAN");
        when(mockResultSet.getString("Name")).thenReturn("United States", "Canada");
        when(mockResultSet.getString("Continent")).thenReturn("North America", "North America");
        when(mockResultSet.getString("Region")).thenReturn("Americas", "Americas");
        when(mockResultSet.getInt("Population")).thenReturn(331002651, 37742154);
        when(mockResultSet.getInt("Capital")).thenReturn(1, 2);

        // Call the method
        ArrayList<Country> result = countryReports.getDescendingPopulationOfCountry();

        // Verify the result
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("USA", result.get(0).getCode());
        assertEquals("United States", result.get(0).getName());
        assertEquals(331002651, result.get(0).getPopulation());
    }

    @Test
    public void testDisplayingAboutDescendingPopulation() throws Exception {

        ArrayList<Country> countryList = new ArrayList<>();

        //Test wil null list
        countryList.add(null);
        countryReports.displayingAboutDescendingPopulation(countryList);

        //Test with Data
        countryList.add(new Country("2","USA","North America","Americas",331002651,1));
        countryReports.displayingAboutDescendingPopulation(countryList);
    }


    // Test for getDistinctContinent method
    @Test
    public void testGetDistinctContinent() throws Exception {
        // Set up mock behavior for the SQL query
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        // Set up mock result data
        when(mockResultSet.next()).thenReturn(true, true, false); // Simulate two rows
        when(mockResultSet.getString("Continent")).thenReturn("North America", "Asia");

        // Call the method
        ArrayList<String> result = countryReports.getDistinctContinent();

        // Verify the result
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains("North America"));
        assertTrue(result.contains("Asia"));
    }

    @Test
    public void testdisplayingAboutDescendingPopulationByContinent() throws Exception {
        ArrayList<String> continentList = null;

        // Test with null list (simulating no data)
        countryReports.displayingAboutDescendingPopulationByContinent(continentList);

        // Test with empty list
        countryReports.displayingAboutDescendingPopulationByContinent(new ArrayList<>());
    }

    // Test for Countries in a region Population
    // Testing about getDistinctRegion
    @Test
    public void testGetDistinctRegion() throws Exception {
        // Set up mock behavior for the SQL query
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        // Set up mock result data
        when(mockResultSet.next()).thenReturn(true, true, false); // Simulate two rows
        when(mockResultSet.getString("Region")).thenReturn("South Asia", "South Asia");

        // Call the method
        ArrayList<String> result = countryReports.getDistinctRegion();

        // Verify the result
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains("South Asia"));
        assertTrue(result.contains("South Asia"));
    }

    @Test
    public void testdisplayingAboutDescendingPopulationByRegion() throws Exception {
        ArrayList<String> regionList = null;

        // Test with null list (simulating no data)
        countryReports.displayingAboutDescendingPopulationByRegion(regionList);

        // Test with empty list
        countryReports.displayingAboutDescendingPopulationByRegion(new ArrayList<>());
    }


    //test with invalid value To getting top populated country
    @Test
    public void testGetTopPopulatedCountryWithNegativeN() {
        int N = -1;

        // Expect an IllegalArgumentException to be thrown for negative N
        assertThrows(IllegalArgumentException.class, () -> {
            countryReports.getTopPopulatedCountry(N);
        });
    }


    @Test
    public void testDisplayTopPopulatedCountryNegativeN() {
        int N = -1;

        // Expect an IllegalArgumentException to be thrown for negative N
        assertThrows(IllegalArgumentException.class, () -> {
            countryReports.displayTopPouplateCountrybyUser(N);
        });
    }

    @Test
    public void testGetDescendingPopulationOfCountryByContinent() throws Exception {
        // Define the continent to test
        String testContinent = "Asia";

        // Create mock objects for PreparedStatement and ResultSet
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        // Mock the behavior of preparing the SQL statement
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        // Mock setting the parameter for the continent
        doNothing().when(mockPreparedStatement).setString(1, testContinent);

        // Mock the behavior of executing the query
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        // Simulate two rows returned by the query
        when(mockResultSet.next()).thenReturn(true, true, false); // Simulate two rows of data

        // Mock the data returned by the ResultSet for each row
        when(mockResultSet.getString("Code")).thenReturn("CHN", "IND");
        when(mockResultSet.getString("Name")).thenReturn("China", "India");
        when(mockResultSet.getString("Continent")).thenReturn("Asia", "Asia");
        when(mockResultSet.getString("Region")).thenReturn("East Asia", "South Asia");
        when(mockResultSet.getInt("Population")).thenReturn(1439323776, 1380004385);
        when(mockResultSet.getInt("Capital")).thenReturn(1, 2);

        // Call the method to be tested
        ArrayList<Country> result = countryReports.getDescendingPopulatinOfCountryByContinent(testContinent);

        // Assert the results
        assertNotNull(result);  // Ensure the result is not null
        assertEquals(2, result.size());  // Ensure there are 2 countries in the result

        // Verify the details of the first country
        Country china = result.get(0);
        assertEquals("CHN", china.getCode());
        assertEquals("China", china.getName());
        assertEquals(1439323776, china.getPopulation());

        // Verify the details of the second country
        Country india = result.get(1);
        assertEquals("IND", india.getCode());
        assertEquals("India", india.getName());
        assertEquals(1380004385, india.getPopulation());

        // Verify the PreparedStatement was called with the correct SQL query
        verify(mockConnection, times(1)).prepareStatement(
                "SELECT Code, Name, Continent, Region, Population, Capital " +
                        "FROM country WHERE Continent = ? ORDER BY Population DESC"
        );

        // Verify the parameter was set correctly
        verify(mockPreparedStatement, times(1)).setString(1, testContinent);

        // Verify that the ResultSet was iterated correctly
        verify(mockResultSet, times(3)).next(); // Two rows of data + one false return to end the loop
    }



    @Test
    public void testGetDescendingPopulationOfCountryByRegion() throws Exception {
        // Define the region to test
        String testRegion = "Western Europe";

        // Create mock objects for PreparedStatement and ResultSet
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        // Mock the behavior of preparing the SQL statement
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        // Mock setting the parameter for the region
        doNothing().when(mockPreparedStatement).setString(1, testRegion);

        // Mock the behavior of executing the query
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        // Simulate two rows returned by the query
        when(mockResultSet.next()).thenReturn(true, true, false); // Simulate two rows of data

        // Mock the data returned by the ResultSet for each row
        when(mockResultSet.getString("Code")).thenReturn("DEU", "FRA");
        when(mockResultSet.getString("Name")).thenReturn("Germany", "France");
        when(mockResultSet.getString("Continent")).thenReturn("Europe", "Europe");
        when(mockResultSet.getString("Region")).thenReturn("Western Europe", "Western Europe");
        when(mockResultSet.getInt("Population")).thenReturn(83783942, 65273511);
        when(mockResultSet.getInt("Capital")).thenReturn(1, 2);

        // Call the method to be tested
        ArrayList<Country> result = countryReports.getDescendingPopulatinOfCountryByRegion(testRegion);

        // Assert the results
        assertNotNull(result);  // Ensure the result is not null
        assertEquals(2, result.size());  // Ensure there are 2 countries in the result

        // Verify the details of the first country
        Country germany = result.get(0);
        assertEquals("DEU", germany.getCode());
        assertEquals("Germany", germany.getName());
        assertEquals(83783942, germany.getPopulation());

        // Verify the details of the second country
        Country france = result.get(1);
        assertEquals("FRA", france.getCode());
        assertEquals("France", france.getName());
        assertEquals(65273511, france.getPopulation());

        // Verify the PreparedStatement was called with the correct SQL query
        verify(mockConnection, times(1)).prepareStatement(
                "SELECT Code, Name, Continent, Region, Population, Capital " +
                        "FROM country WHERE Region = ? ORDER BY Population DESC"
        );

        // Verify the parameter was set correctly
        verify(mockPreparedStatement, times(1)).setString(1, testRegion);

        // Verify that the ResultSet was iterated correctly
        verify(mockResultSet, times(3)).next(); // Two rows of data + one false return to end the loop
    }


    @Test
    public void testGetTopPopulatedCountry() throws Exception {
        // Create a mock PreparedStatement
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);

        // Mock the behavior of the connection's prepareStatement method
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        // Mock the behavior of the PreparedStatement's executeQuery method
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        // Set up mock result data
        when(mockResultSet.next()).thenReturn(true, true, false); // Simulate two rows
        when(mockResultSet.getString("Code")).thenReturn("USA", "CAN");
        when(mockResultSet.getString("Name")).thenReturn("United States", "Canada");
        when(mockResultSet.getString("Continent")).thenReturn("North America", "North America");
        when(mockResultSet.getString("Region")).thenReturn("Americas", "Americas");
        when(mockResultSet.getInt("Population")).thenReturn(331002651, 37742154);
        when(mockResultSet.getInt("Capital")).thenReturn(1, 2);

        // Call the method
        ArrayList<Country> result = countryReports.getTopPopulatedCountry(2);

        // Verify the result
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("USA", result.get(0).getCode());
        assertEquals("United States", result.get(0).getName());
        assertEquals(331002651, result.get(0).getPopulation());

        assertEquals("CAN", result.get(1).getCode());
        assertEquals("Canada", result.get(1).getName());
        assertEquals(37742154, result.get(1).getPopulation());

        // Verify that the correct query was executed
        verify(mockPreparedStatement, times(1)).setInt(1, 2);
        verify(mockPreparedStatement, times(1)).executeQuery();
    }

    @Test
    public void testDisplayTopPopulatedCountryAccordingToContinentByUserInput() throws Exception {
        // Mock the distinct continents
        ArrayList<String> mockContinents = new ArrayList<>();
        mockContinents.add("North America");
        mockContinents.add("Asia");

        // Mock countries for each continent
        ArrayList<Country> northAmericaCountries = new ArrayList<>();
        northAmericaCountries.add(new Country("USA", "United States", "North America", "Americas", 331002651, 1));
        northAmericaCountries.add(new Country("CAN", "Canada", "North America", "Americas", 37742154, 2));

        ArrayList<Country> asiaCountries = new ArrayList<>();
        asiaCountries.add(new Country("CHN", "China", "Asia", "East Asia", 1402112000, 3));
        asiaCountries.add(new Country("IND", "India", "Asia", "South Asia", 1380004385, 4));

        // Spy on the CountryReports class to track method interactions
        CountryReports spyCountryReports = Mockito.spy(countryReports);

        // Mock the behavior of getDistinctContinent to return the mock continents
        doReturn(mockContinents).when(spyCountryReports).getDistinctContinent();

        // Mock the behavior of getDescendingPopulatinOfCountryByContinent for each continent
        doReturn(northAmericaCountries).when(spyCountryReports).getDescendingPopulatinOfCountryByContinent("North America");
        doReturn(asiaCountries).when(spyCountryReports).getDescendingPopulatinOfCountryByContinent("Asia");

        // Spy on displayTableFormat to verify it is called correctly
        doNothing().when(spyCountryReports).displayTableFormat(any());

        // Call the method with N = 1 (expecting only 1 country per continent)
        spyCountryReports.displayTopPopulatedCountryAccordingtoContinentByUserInput(1);

        // Verify that getDistinctContinent was called once
        verify(spyCountryReports, times(1)).getDistinctContinent();

        // Verify that getDescendingPopulatinOfCountryByContinent was called for each continent
        verify(spyCountryReports, times(1)).getDescendingPopulatinOfCountryByContinent("North America");
        verify(spyCountryReports, times(1)).getDescendingPopulatinOfCountryByContinent("Asia");

    }


    @Test
    public void testDisplayTopPopulatedCountryAccordingToRegionByUserInput() throws Exception {
        // Mock the distinct regions
        ArrayList<String> mockRegions = new ArrayList<>();
        mockRegions.add("Americas");
        mockRegions.add("Europe");

        // Mock countries for each region
        ArrayList<Country> americasCountries = new ArrayList<>();
        americasCountries.add(new Country("USA", "United States", "North America", "Americas", 331002651, 1));
        americasCountries.add(new Country("CAN", "Canada", "North America", "Americas", 37742154, 2));

        ArrayList<Country> europeCountries = new ArrayList<>();
        europeCountries.add(new Country("DEU", "Germany", "Europe", "Western Europe", 83783942, 3));
        europeCountries.add(new Country("FRA", "France", "Europe", "Western Europe", 65273511, 4));

        // Spy on the CountryReports class to track method interactions
        CountryReports spyCountryReports = Mockito.spy(countryReports);

        // Mock the behavior of getDistinctRegion to return the mock regions
        doReturn(mockRegions).when(spyCountryReports).getDistinctRegion();

        // Mock the behavior of getDescendingPopulatinOfCountryByRegion for each region
        doReturn(americasCountries).when(spyCountryReports).getDescendingPopulatinOfCountryByRegion("Americas");
        doReturn(europeCountries).when(spyCountryReports).getDescendingPopulatinOfCountryByRegion("Europe");

        // Spy on displayTableFormat to verify it is called correctly
        doNothing().when(spyCountryReports).displayTableFormat(any());

        // Call the method with N = 1 (expecting only 1 country per region)
        spyCountryReports.displayTopPopulatedCountryAccordingtoRegionByUserInput(1);

        // Verify that getDistinctRegion was called once
        verify(spyCountryReports, times(1)).getDistinctRegion();

        // Verify that getDescendingPopulatinOfCountryByRegion was called for each region
        verify(spyCountryReports, times(1)).getDescendingPopulatinOfCountryByRegion("Americas");
        verify(spyCountryReports, times(1)).getDescendingPopulatinOfCountryByRegion("Europe");
    }













}

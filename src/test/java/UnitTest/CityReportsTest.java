package UnitTest;

import ProjectTeam2.City;
import ProjectTeam2.Country;
import Reports.CityReports;
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

import static org.mockito.Mockito.*;

public class CityReportsTest {

    private Connection mockConnection;
    private Statement mockStatement;
    private ResultSet mockResultSet;
    private CityReports cityReports;

    @BeforeEach
    public void setUp() throws SQLException {
        // Mocking the database connection and statement
        mockConnection = Mockito.mock(Connection.class);
        mockStatement = Mockito.mock(Statement.class);
        mockResultSet = Mockito.mock(ResultSet.class);


        // Setting up the mocks
        when(mockConnection.createStatement()).thenReturn(mockStatement);

        // Initialize CityReports
        cityReports = new CityReports(mockConnection);
    }

    @Test
    public void testCityReportFormat_NoCities() throws SQLException {
        ArrayList<City> cities = new ArrayList<>();
        cityReports.cityReportFormat(cities);
        // Here, you can verify that the output was as expected, or just assert that no exceptions were thrown.
    }

    @Test
    public void testCityReportFormat_WithCities() throws SQLException {
        ArrayList<City> cities = new ArrayList<>();
        City city = new City("New York", "USA", "New York", 8419600);
        cities.add(city);

        // Mocking ResultSet to return the country name
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getString("Name")).thenReturn("United States");

        // Execute the method
        cityReports.cityReportFormat(cities);

        // Verify if the query was executed with the correct SQL
        verify(mockStatement, times(1)).executeQuery(contains("country.Name"));
    }

    @Test
    public void testCapitalCityReportFormat_NoCities() throws SQLException {
        ArrayList<City> cities = new ArrayList<>();
        cityReports.capitalCityReportFormat(cities);
        // Verify that no exceptions are thrown
    }

    @Test
    public void testCapitalCityReportFormat_WithCities() throws SQLException {
        ArrayList<City> cities = new ArrayList<>();
        City city = new City("Washington D.C.", "USA", "District of Columbia", 705749);
        cities.add(city);

        // Mocking ResultSet to return the country name
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getString("Name")).thenReturn("United States");

        // Execute the method
        cityReports.capitalCityReportFormat(cities);

        // Verify if the query was executed with the correct SQL
        verify(mockStatement, times(1)).executeQuery(contains("country.Name"));
    }

    /***
     * Test For //#7 (Cities in the world organised by largest population to smallest.)
     * */

    @Test
    public void testGetPopulationOftheCitybyDescendingOrder() throws SQLException {
        // Mocking ResultSet for multiple cities
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next())
                .thenReturn(true) // First call returns true
                .thenReturn(true) // Second call returns true
                .thenReturn(false); // Third call returns false

        when(mockResultSet.getString("Name")).thenReturn("New York").thenReturn("Los Angeles");
        when(mockResultSet.getString("CountryCode")).thenReturn("USA").thenReturn("USA");
        when(mockResultSet.getString("District")).thenReturn("New York").thenReturn("California");
        when(mockResultSet.getInt("Population")).thenReturn(8419600).thenReturn(3980400);

        ArrayList<City> result = cityReports.getPopulationOftheCitybyDescendingOrder();

        assert(result.size() == 2);
        assert(result.get(0).getName().equals("New York"));
        assert(result.get(1).getName().equals("Los Angeles"));
    }


    /***
     * Test For //#8 (the cities in a continent organised by largest population to smallest)
     * */


    @Test
    public void testGetDistinctContinent() throws SQLException {
        // Mocking ResultSet for distinct continents
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, true, false); // Two continents, then end
        when(mockResultSet.getString("Continent")).thenReturn("Asia", "Europe");

        ArrayList<String> continents = cityReports.getDistinctContinent();

        // Verify the contents of the returned list
        assert(continents.size() == 2);
        assert(continents.get(0).equals("Asia"));
        assert(continents.get(1).equals("Europe"));

        // Verify that the SQL statement was executed
        verify(mockStatement, times(1)).executeQuery(contains("SELECT DISTINCT Continent"));
    }

    @Test
    public void testGetPopulationOftheCitybyContinent_NoContinents() throws SQLException {
        ArrayList<String> continents = new ArrayList<>();
        cityReports.getPopulationOftheCitybyContinent(continents);
    }

    @Test
    public void testGetPopulationOftheCitybyContinent_WithContinents() throws SQLException {
        ArrayList<String> continents = new ArrayList<>();
        continents.add("Asia");

        // Mocking ResultSet for population query
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false); // One city, then end
        when(mockResultSet.getString("CityName")).thenReturn("Tokyo");
        when(mockResultSet.getString("CountryCode")).thenReturn("JP");
        when(mockResultSet.getString("District")).thenReturn("Kanto");
        when(mockResultSet.getInt("Population")).thenReturn(13929286);

        // Execute the method
        cityReports.getPopulationOftheCitybyContinent(continents);

        // Verify if the city report format was called correctly
        verify(mockStatement, times(1)).executeQuery(contains("country.Continent = 'Asia'"));
    }

    @Test
    public void testGetPopulationOftheCitybyContinent_HandlesNull() throws SQLException {
        cityReports.getPopulationOftheCitybyContinent(null);
        // Verify that no exceptions are thrown and appropriate message is printed
    }

    @Test
    public void testGetPopulationOftheCitybyContinent_HandlesNullInContinents() throws SQLException {
        ArrayList<String> continents = new ArrayList<>();
        continents.add(null);
        cityReports.getPopulationOftheCitybyContinent(continents);
    }


    /***
     * Test For //#9 (the cities in a Region organised by largest population to smallest)
     * */

    @Test
    public void testGetDistinctRegion() throws SQLException {
        // Mocking ResultSet for distinct regions
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, true, false); // Two regions, then end
        when(mockResultSet.getString("Region")).thenReturn("East Asia", "Western Europe");

        ArrayList<String> regions = cityReports.getDistinctRegion();

        // Verify the contents of the returned list
        assert(regions.size() == 2);
        assert(regions.get(0).equals("East Asia"));
        assert(regions.get(1).equals("Western Europe"));

        // Verify that the SQL statement was executed
        verify(mockStatement, times(1)).executeQuery(contains("SELECT DISTINCT Region"));
    }

    @Test
    public void testGetPopulationOftheCitybyRegion_NoRegions() throws SQLException {
        ArrayList<String> regions = new ArrayList<>();
        cityReports.getPopulationOftheCitybyRegion(regions);
        // Verify that no exceptions are thrown and appropriate message is printed
    }

    @Test
    public void testGetPopulationOftheCitybyRegion_WithRegions() throws SQLException {
        ArrayList<String> regions = new ArrayList<>();
        regions.add("East Asia");

        // Mocking ResultSet for population query
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false); // One city, then end
        when(mockResultSet.getString("CityName")).thenReturn("Tokyo");
        when(mockResultSet.getString("CountryCode")).thenReturn("JP");
        when(mockResultSet.getString("District")).thenReturn("Kanto");
        when(mockResultSet.getInt("Population")).thenReturn(13929286);

        // Execute the method
        cityReports.getPopulationOftheCitybyRegion(regions);

        // Verify if the city report format was called correctly
        verify(mockStatement, times(1)).executeQuery(contains("country.Region = 'East Asia'"));
    }

    @Test
    public void testGetPopulationOftheCitybyRegion_HandlesNull() throws SQLException {
        cityReports.getPopulationOftheCitybyRegion(null);
    }

    @Test
    public void testGetPopulationOftheCitybyRegion_HandlesNullInRegions() throws SQLException {
        ArrayList<String> regions = new ArrayList<>();
        regions.add(null);
        cityReports.getPopulationOftheCitybyRegion(regions);
    }

    /***
     * Test For //#10 (the cities in a Country organised by largest population to smallest)
     * */
    @Test
    public void testGetDistinctCountry() throws SQLException {
        // Mocking ResultSet for distinct country names
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, true, false); // Two countries, then end
        when(mockResultSet.getString("Name")).thenReturn("Japan", "Germany");

        ArrayList<String> countries = cityReports.getDistinctCountry();

        // Verify the contents of the returned list
        assert(countries.size() == 2);
        assert(countries.get(0).equals("Japan"));
        assert(countries.get(1).equals("Germany"));

        // Verify that the SQL statement was executed
        verify(mockStatement, times(1)).executeQuery(contains("SELECT DISTINCT Name"));
    }

    @Test
    public void testGetPopulationOftheCitybyCountry_NoCountries() throws SQLException {
        // Test how the method behaves when given an empty list of countries
        ArrayList<String> countries = new ArrayList<>();
        cityReports.getPopulationOftheCitybyCountry(countries);
        // Verify that no exceptions are thrown and appropriate message is printed
    }

    @Test
    public void testGetPopulationOftheCitybyCountry_WithCountries() throws SQLException {
        // Test the method with a valid country name
        ArrayList<String> countries = new ArrayList<>();
        countries.add("Japan");

        // Mocking ResultSet for population query
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false); // One city, then end
        when(mockResultSet.getString("CityName")).thenReturn("Tokyo");
        when(mockResultSet.getString("CountryCode")).thenReturn("JP");
        when(mockResultSet.getString("District")).thenReturn("Kanto");
        when(mockResultSet.getInt("Population")).thenReturn(13929286);

        // Execute the method
        cityReports.getPopulationOftheCitybyCountry(countries);

        // Verify if the city report format was called correctly
        verify(mockStatement, times(1)).executeQuery(contains("country.Name = 'Japan'"));
    }

    @Test
    public void testGetPopulationOftheCitybyCountry_HandlesNull() throws SQLException {
        // Test how the method handles a null input for the list of countries
        cityReports.getPopulationOftheCitybyCountry(null);

    }

    @Test
    public void testGetPopulationOftheCitybyCountry_HandlesNullInCountries() throws SQLException {
        // Test how the method behaves when the list contains a null value
        ArrayList<String> countries = new ArrayList<>();
        countries.add(null);
        cityReports.getPopulationOftheCitybyCountry(countries);

    }


    /***
     * Test For //#11 (the cities in a District organised by largest population to smallest)
     * */

    @Test
    public void testGetDistinctDistrict() throws SQLException {
        // Mocking ResultSet for distinct districts
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, true, false); // Two districts, then end
        when(mockResultSet.getString("District")).thenReturn("Kanto", "Kansai");

        ArrayList<String> districts = cityReports.getDistinctDistrict();

        // Verify the contents of the returned list
        assert(districts.size() == 2);
        assert(districts.get(0).equals("Kanto"));
        assert(districts.get(1).equals("Kansai"));

        // Verify that the SQL statement was executed
        verify(mockStatement, times(1)).executeQuery(contains("SELECT DISTINCT District"));
    }

    @Test
    public void testGetPopulationOfthecitybyDistrict_NoDistricts() throws SQLException {
        // Test how the method behaves when given an empty list of districts
        ArrayList<String> districts = new ArrayList<>();
        cityReports.getPopulationOfthecitybyDistrict(districts);

    }

    @Test
    public void testGetPopulationOfthecitybyDistrict_WithDistricts() throws SQLException {
        // Test the method with a valid district name
        ArrayList<String> districts = new ArrayList<>();
        districts.add("Kanto");

        // Mocking ResultSet for population query
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false); // One city, then end
        when(mockResultSet.getString("CityName")).thenReturn("Tokyo");
        when(mockResultSet.getString("CountryCode")).thenReturn("JP");
        when(mockResultSet.getString("District")).thenReturn("Kanto");
        when(mockResultSet.getInt("Population")).thenReturn(13929286);

        // Execute the method
        cityReports.getPopulationOfthecitybyDistrict(districts);

        // Verify if the city report format was called correctly
        verify(mockStatement, times(1)).executeQuery(contains("city.District = 'Kanto'"));
    }

    @Test
    public void testGetPopulationOfthecitybyDistrict_HandlesNull() throws SQLException {
        // Test how the method handles a null input for the list of districts
        cityReports.getPopulationOfthecitybyDistrict(null);

    }

    @Test
    public void testGetPopulationOfthecitybyDistrict_HandlesNullInDistricts() throws SQLException {
        // Test how the method behaves when the list contains a null value
        ArrayList<String> districts = new ArrayList<>();
        districts.add(null);
        cityReports.getPopulationOfthecitybyDistrict(districts);
    }

    /***
     * Test For //#12 (top N populated cities in the world where N is provided by the user)
     * */

    @Test
    public void testGetPopulationOfthecity_NLessThanOrEqualToZero() throws SQLException {
        // Test the method when N is less than or equal to 0
        ArrayList<City> cities = cityReports.getPopulationOfthecity(0);
        // Assert that the returned value is null and "No Query Available" is printed
        assert(cities == null); // or verify the output if you have a way to capture System.out
    }

    @Test
    public void testgetPopulationOftheCity() throws SQLException {
        // Create a mock PreparedStatement
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);

        // Mock the behavior of the connection's prepareStatement method
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        // Mock the behavior of the PreparedStatement's executeQuery method
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        //mock data
        when(mockResultSet.next()).thenReturn(true, true, false); // Two cities, then end
        when(mockResultSet.getString("Name")).thenReturn("Tokyo", "Delhi");
        when(mockResultSet.getString("CountryCode")).thenReturn("JP", "IN");
        when(mockResultSet.getString("District")).thenReturn("Kanto", "Delhi");
        when(mockResultSet.getInt("Population")).thenReturn(13929286, 16787941);

        ArrayList<City> cities = cityReports.getPopulationOfthecity(2);

        // Verify the result
        assertNotNull(cities);

        //Check One row
        assertEquals(2, cities.size());
        assertEquals("Tokyo", cities.get(0).getName());
        assertEquals("JP", cities.get(0).getCountryCode());
        assertEquals(13929286, cities.get(0).getPopulation());

        // Verify that the correct query was executed
        verify(mockPreparedStatement, times(1)).setInt(1, 2);
        verify(mockPreparedStatement, times(1)).executeQuery();
    }

    @Test
    public void testDisplayingOutputOfTheCityPopulationTopValueByN_NoCities() throws SQLException {
        // Test how the method behaves when given an empty list of cities
        ArrayList<City> cities = new ArrayList<>();
        cityReports.displayingOutputOfTheCityPopulationTopValueByN(cities, 3);
        // Verify that no exceptions are thrown and appropriate message is printed
    }

    @Test
    public void testDisplayingOutputOfTheCityPopulationTopValueByN_ValidInput() throws SQLException {
        // Create mock cities
        ArrayList<City> cities = new ArrayList<>();
        cities.add(new City("City1", "USA", "District1", 100000));
        cities.add(new City("City2", "USA", "District2", 200000));

        // Spy on the CityReports class to track method interactions
        CityReports spyCityReports = Mockito.spy(cityReports);

        // Mock the behavior of the database call to return country names
        when(mockStatement.executeQuery(any(String.class))).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true).thenReturn(false); // Simulate one result
        when(mockResultSet.getString("Name")).thenReturn("CountryName");

        // Call the method with valid input
        spyCityReports.displayingOutputOfTheCityPopulationTopValueByN(cities, 2);

        // Verify that the output method was called
        verify(spyCityReports, times(1)).cityReportFormat(cities);
    }


    @Test
    public void testDisplayingOutputOfTheCityPopulationTopValueByN_HandlesNull() throws SQLException {
        // Test how the method handles a null input for the list of cities
        cityReports.displayingOutputOfTheCityPopulationTopValueByN(null, 3);

    }


    /***
     * Test For //#13 N populated cities in a continent where N is provided by the user
     * */

    @Test
    public void testGetPopulationOftheCityByContinentTopN() throws SQLException {
        // Create a mock PreparedStatement
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);

        // Mock the behavior of the connection's prepareStatement method
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        // Mock the behavior of the PreparedStatement's executeQuery method
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        // Prepare mock data for distinct continents
        when(mockStatement.executeQuery(any(String.class))).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false); // Simulate one continent
        when(mockResultSet.getString("Continent")).thenReturn("Asia");

        // Prepare mock for the prepared statement to get city population
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false); // Simulate one city
        when(mockResultSet.getString("CityName")).thenReturn("Tokyo");
        when(mockResultSet.getString("CountryCode")).thenReturn("JPN");
        when(mockResultSet.getString("District")).thenReturn("Kanto");
        when(mockResultSet.getInt("Population")).thenReturn(13929286);

        // Spy on the CityReports class to track method interactions
        CityReports spyCityReports = Mockito.spy(cityReports);

        // Call the method with N = 1
        spyCityReports.getPopulationOftheCityByContinentTopN(1);

        // Verify that getDistinctContinent was called once
        verify(spyCityReports, times(1)).getDistinctContinent();

        // Verify that the prepared statement was called with the expected continent and limit
        verify(mockPreparedStatement, times(1)).setString(1, "Asia");
        verify(mockPreparedStatement, times(1)).setInt(2, 1);
    }

    //Test with N (-) value
    @Test
    public void testGetPopulationOftheCityByContinentTopN_NegativeN() throws SQLException {
        // Spy on the CityReports class
        CityReports spyCityReports = Mockito.spy(cityReports);

        // Call the method with a negative N
        spyCityReports.getPopulationOftheCityByContinentTopN(-1);

        // Verify that getDistinctContinent was not called
        verify(spyCityReports, times(0)).getDistinctContinent();
    }

    //Test with N (0) Value
    @Test
    public void testGetPopulationOftheCityByContinentTopN_ZeroN() throws SQLException {
        // Spy on the CityReports class
        CityReports spyCityReports = Mockito.spy(cityReports);

        // Call the method with N = 0
        spyCityReports.getPopulationOftheCityByContinentTopN(0);

        // Verify that getDistinctContinent was not called
        verify(spyCityReports, times(0)).getDistinctContinent();
    }

    /***
     * Test For //#14 N populated cities in a region where N is provided by the user.
     *
     * */

    //test with valid mock data and N value
    @Test
    public void testGetPopulationOftheCityByRegionTopN() throws SQLException {
        // Create a mock PreparedStatement
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);

        // Mock the behavior of the connection's prepareStatement method
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        // Mock the behavior of the PreparedStatement's executeQuery method
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        // Prepare mock data for distinct regions
        when(mockStatement.executeQuery(any(String.class))).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false); // Simulate one region
        when(mockResultSet.getString("Region")).thenReturn("South Asia");

        // Prepare mock for the prepared statement to get city population
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false); // Simulate one city
        when(mockResultSet.getString("CityName")).thenReturn("Mumbai");
        when(mockResultSet.getString("CountryCode")).thenReturn("IND");
        when(mockResultSet.getString("District")).thenReturn("Maharashtra");
        when(mockResultSet.getInt("Population")).thenReturn(20411000);

        // Spy on the CityReports class to track method interactions
        CityReports spyCityReports = Mockito.spy(cityReports);

        // Call the method with N = 1
        spyCityReports.getPopulationOftheCityByRegionTopN(1);

        // Verify that getDistinctRegion was called once
        verify(spyCityReports, times(1)).getDistinctRegion();

        // Verify that the prepared statement was called with the expected region and limit
        verify(mockPreparedStatement, times(1)).setString(1, "South Asia");
        verify(mockPreparedStatement, times(1)).setInt(2, 1);
    }

    //Test with ZeroValue N
    @Test
    public void testGetPopulationOftheCityByRegionTopN_ZeroN() throws SQLException {
        // Spy on the CityReports class
        CityReports spyCityReports = Mockito.spy(cityReports);

        // Call the method with N = 0
        spyCityReports.getPopulationOftheCityByRegionTopN(0);

        // Verify that getDistinctRegion was not called, since the method should exit early
        verify(spyCityReports, times(0)).getDistinctRegion();
    }

    //Negative N

    @Test
    public void testGetPopulationOftheCityByRegionTopN_NegativeN() throws SQLException {
        // Spy on the CityReports class
        CityReports spyCityReports = Mockito.spy(cityReports);

        // Call the method with a negative N value
        spyCityReports.getPopulationOftheCityByRegionTopN(-1);

        // Verify that getDistinctRegion was not called, since the method should exit early
        verify(spyCityReports, times(0)).getDistinctRegion();
    }


    /***
     * Test For //#15 N (top N populated cities by Country Where N is provided by the  user.)
     *
     * */

    @Test
    public void testGetPopulationOftheCityByCountryTopN() throws SQLException {

        // Create a mock PreparedStatement
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);

        // Mock the behavior of the connection's prepareStatement method
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        // Mock the behavior of the PreparedStatement's executeQuery method
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        // Mock the distinct countries and city data
        when(mockStatement.executeQuery(any(String.class))).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false); // Simulate one country
        when(mockResultSet.getString("Name")).thenReturn("India");

        // Mock the city data for the country
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false); // Simulate one city
        when(mockResultSet.getString("CityName")).thenReturn("Mumbai");
        when(mockResultSet.getString("CountryCode")).thenReturn("IND");
        when(mockResultSet.getString("District")).thenReturn("Maharashtra");
        when(mockResultSet.getInt("Population")).thenReturn(20411000);

        // Spy on the CityReports class to track method interactions
        CityReports spyCityReports = Mockito.spy(cityReports);

        // Call the method with N = 1
        spyCityReports.getPopulationOftheCityByCountryTopN(1);

        // Verify that getDistinctCountry was called once
        verify(spyCityReports, times(1)).getDistinctCountry();

        // Verify that the prepared statement was called with the expected country and limit
        verify(mockPreparedStatement, times(1)).setString(1, "India");
        verify(mockPreparedStatement, times(1)).setInt(2, 1);
    }

    //test with negative N and Zero N
    @Test
    public void testGetPopulationOftheCityByCountryTopN_NegativeN() throws SQLException {
        // Spy on the CityReports class
        CityReports spyCityReports = Mockito.spy(cityReports);

        // Call the method with a negative N value
        spyCityReports.getPopulationOftheCityByCountryTopN(-1);

        // Verify that getDistinctCountry was not called, since the method should exit early
        verify(spyCityReports, times(0)).getDistinctCountry();
    }

    @Test
    public void testGetPopulationOftheCityByCountryTopN_ZeroN() throws SQLException {
        // Spy on the CityReports class
        CityReports spyCityReports = Mockito.spy(cityReports);

        // Call the method with N = 0
        spyCityReports.getPopulationOftheCityByCountryTopN(0);

        // Verify that getDistinctCountry was not called, since the method should exit early
        verify(spyCityReports, times(0)).getDistinctCountry();
    }

    /***
     * Test For //#16 top N populated cities in a district where N is provided by the user)
     *
     * */

    @Test
    public void testGetPopulationOftheCityByDistrictTopN() throws SQLException {
        // Create a mock PreparedStatement
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);

        // Mock the behavior of the connection's prepareStatement method
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        // Mock the behavior of the PreparedStatement's executeQuery method
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        // Mock the distinct districts and city data
        when(mockStatement.executeQuery(any(String.class))).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false); // Simulate one district
        when(mockResultSet.getString("District")).thenReturn("District1");

        // Mock the city data for the district
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false); // Simulate one city
        when(mockResultSet.getString("CityName")).thenReturn("CityA");
        when(mockResultSet.getString("CountryCode")).thenReturn("CTY");
        when(mockResultSet.getString("District")).thenReturn("District1");
        when(mockResultSet.getInt("Population")).thenReturn(1000000);

        // Spy on the CityReports class to track method interactions
        CityReports spyCityReports = Mockito.spy(cityReports);

        // Call the method with N = 1
        spyCityReports.getPopulationOftheCityByDistrictTopN(1);

        // Verify that getDistinctDistrict was called once
        verify(spyCityReports, times(1)).getDistinctDistrict();

        // Verify that the prepared statement was called with the expected district and limit
        verify(mockPreparedStatement, times(1)).setString(1, "District1");
        verify(mockPreparedStatement, times(1)).setInt(2, 1);
    }

    //test with negative N and Zero N

    @Test
    public void testGetPopulationOftheCityByDistrictTopN_NegativeN() throws SQLException {
        // Spy on the CityReports class
        CityReports spyCityReports = Mockito.spy(cityReports);

        // Call the method with a negative N value
        spyCityReports.getPopulationOftheCityByDistrictTopN(-1);

        // Verify that getDistinctDistrict was not called, since the method should exit early
        verify(spyCityReports, times(0)).getDistinctDistrict();
    }

    @Test
    public void testGetPopulationOftheCityByDistrictTopN_ZeroN() throws SQLException {
        // Spy on the CityReports class
        CityReports spyCityReports = Mockito.spy(cityReports);

        // Call the method with N = 0
        spyCityReports.getPopulationOftheCityByDistrictTopN(0);

        // Verify that getDistinctDistrict was not called, since the method should exit early
        verify(spyCityReports, times(0)).getDistinctDistrict();
    }




















}
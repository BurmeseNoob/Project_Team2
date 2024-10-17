package IntegrationTest;

import DataBaseConnect.Connection;
import ProjectTeam2.*;
import Reports.CityReports;
import Reports.CountryReports;
import Reports.LanguageReports;
import Reports.PopulationReports;
import Reports.SpecificPopulationReports;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Allow the test instance to be reused across all tests in the class
public class AppIntegrationTest {

    static App app; // Static instance of the App class for testing
    static Connection connection; // Static instance of the database connection

    @BeforeAll
    public void setUp() throws SQLException {
        // Initialize the app and establish a database connection before all tests
        app = new App();
        connection = new Connection();

        // Connect to the database using specified host and timeout
        connection.connect("localhost:33061", 30000);
    }

    @AfterAll
    public void tearDown() throws SQLException {
        // Disconnect from the database after all tests have completed
        connection.disconnect();
    }

    /**
     * Tests the functionality of running country reports.
     * This test verifies that country reports can be generated and that the
     * returned data is valid (i.e., not null and not empty).
     *
     * @throws SQLException if there is an error accessing the database
     */
    @Test
    public void testRunCountryReports() throws SQLException {
        CountryReports cr = new CountryReports(connection.getConnection()); // Create an instance of CountryReports
        int N = 10; // Define a test parameter for the number of results to retrieve

        // Execute the method to run country reports
        app.runCountryReports(cr, N);

        // Validate that the country report data is not null and not empty
        ArrayList<Country> countries = cr.getDescendingPopulationOfCountry();
        assertNotNull(countries); // Assert that countries list is not null
        assertFalse(countries.isEmpty()); // Assert that countries list is not empty

        // Validate that distinct continents data is retrieved successfully
        ArrayList<String> continents = cr.getDistinctContinent();
        assertNotNull(continents); // Assert that continents list is not null
        assertFalse(continents.isEmpty()); // Assert that continents list is not empty
    }

    /**
     * Tests the functionality of running city reports.
     * This test verifies that city reports can be generated and that the
     * returned data is valid (i.e., not null and not empty).
     *
     * @throws SQLException if there is an error accessing the database
     */
    @Test
    public void testRunCityReports() throws SQLException {
        CityReports cy = new CityReports(connection.getConnection()); // Create an instance of CityReports
        int N = 10; // Define a test parameter for the number of results to retrieve

        // Execute the method to run city reports
        app.runCityReports(cy, N);

        // Validate that the city report data is not null and not empty
        ArrayList<City> cities = cy.getPopulationOftheCitybyDescendingOrder();
        assertNotNull(cities); // Assert that cities list is not null
        assertFalse(cities.isEmpty()); // Assert that cities list is not empty

        // Validate that distinct continents data is retrieved successfully
        ArrayList<String> continents = cy.getDistinctContinent();
        assertNotNull(continents); // Assert that continents list is not null
        assertFalse(continents.isEmpty()); // Assert that continents list is not empty
    }

    /**
     * Tests the functionality of running population reports.
     * This test verifies that population reports can be generated and that the
     * returned data is valid (i.e., not null and not empty).
     *
     * @throws SQLException if there is an error accessing the database
     */
    @Test
    public void testRunPopulationReports() throws SQLException {
        PopulationReports pr = new PopulationReports(connection.getConnection()); // Create an instance of PopulationReports
        SpecificPopulationReports cpr = new SpecificPopulationReports(connection.getConnection()); // Create an instance of SpecificPopulationReports
        int N = 10; // Define a test parameter for the number of results to retrieve

        // Execute the method to run population reports
        app.runPopulationReports(pr, cpr, N);

        // Validate that world population data is retrieved successfully
        ArrayList<SpecificPopulation> worldPopulation = cpr.getWorldPopulation();
        assertNotNull(worldPopulation); // Assert that world population list is not null
        assertFalse(worldPopulation.isEmpty()); // Assert that world population list is not empty

        // Validate that continent population data is retrieved successfully
        ArrayList<SpecificPopulation> continentPopulation = cpr.getContinentPopulation();
        assertNotNull(continentPopulation); // Assert that continent population list is not null
        assertFalse(continentPopulation.isEmpty()); // Assert that continent population list is not empty
    }

    /**
     * Tests the functionality of running language reports.
     * This test verifies that language reports can be generated and that the
     * returned data is valid (i.e., not null and not empty).
     *
     * @throws SQLException if there is an error accessing the database
     */
    @Test
    public void testRunLanguageReports() throws SQLException {
        LanguageReports lr = new LanguageReports(connection.getConnection()); // Create an instance of LanguageReports

        // Execute the method to run language reports
        app.runLanguageReports(lr);

        // Validate that language data is retrieved successfully
        ArrayList<LanguageData> languages = lr.getWorldLanguagesSpeak();
        assertNotNull(languages); // Assert that languages list is not null
        assertFalse(languages.isEmpty()); // Assert that languages list is not empty
    }
}

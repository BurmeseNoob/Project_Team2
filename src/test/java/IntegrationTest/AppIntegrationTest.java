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

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AppIntegrationTest {

    static App app;
    static Connection connection;

    @BeforeAll
    public void setUp() throws SQLException {
        // Initialize the app and set up the database connection
        app = new App();
        connection = new Connection();

        connection.connect("localhost:33061", 30000);
    }

    @AfterAll
    public void tearDown() throws SQLException {
        // Disconnect after all tests are completed
        connection.disconnect();
    }

    @Test
    public void testRunCountryReports() throws SQLException {
        // Test running country reports
        CountryReports cr = new CountryReports(connection.getConnection());
        int N = 10;

        app.runCountryReports(cr, N);

        // Verify some basic behavior, e.g., non-empty result or no exceptions thrown
        ArrayList<Country> countries = cr.getDescendingPopulationOfCountry();
        assertNotNull(countries);
        assertFalse(countries.isEmpty());

        ArrayList<String> continents = cr.getDistinctContinent();
        assertNotNull(continents);
        assertFalse(continents.isEmpty());
    }

    @Test
    public void testRunCityReports() throws SQLException {
        // Test running city reports
        CityReports cy = new CityReports(connection.getConnection());
        int N = 10;

        app.runCityReports(cy, N);

        // Verify that city reports generate valid data
        ArrayList<City> cities = cy.getPopulationOftheCitybyDescendingOrder();
        assertNotNull(cities);
        assertFalse(cities.isEmpty());

        ArrayList<String> continents = cy.getDistinctContinent();
        assertNotNull(continents);
        assertFalse(continents.isEmpty());
    }

    @Test
    public void testRunPopulationReports() throws SQLException {
        // Test running population reports
        PopulationReports pr = new PopulationReports(connection.getConnection());
        SpecificPopulationReports cpr = new SpecificPopulationReports(connection.getConnection());
        int N = 10;

        app.runPopulationReports(pr, cpr, N);

        // Verify that population reports generate data
        ArrayList<SpecificPopulation> worldPopulation = cpr.getWorldPopulation();
        assertNotNull(worldPopulation);
        assertFalse(worldPopulation.isEmpty());

        ArrayList<SpecificPopulation> continentPopulation = cpr.getContinentPopulation();
        assertNotNull(continentPopulation);
        assertFalse(continentPopulation.isEmpty());
    }

    @Test
    public void testRunLanguageReports() throws SQLException {
        // Test running language reports
        LanguageReports lr = new LanguageReports(connection.getConnection());

        app.runLanguageReports(lr);

        // Verify that language reports generate valid data
        ArrayList<LanguageData> languages = lr.getWorldLanguagesSpeak();
        assertNotNull(languages);
        assertFalse(languages.isEmpty());
    }
}

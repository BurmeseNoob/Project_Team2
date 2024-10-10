package IntegrationTest;
import DataBaseConnect.Connection;
import ProjectTeam2.App;
import ProjectTeam2.City;
import ProjectTeam2.Country;
import Reports.CityReports;
import Reports.CountryReports;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{
    static App app;

    @BeforeAll
    static void init() throws SQLException {
        app = new App();
        app.runApp(); // Running the app's logic to ensure everything initializes
    }

    @Test
    void testCountryReports() throws SQLException {
        // Check that country reports are generated and displayed properly
        CountryReports countryReports = new CountryReports(new Connection().getConnection());
        ArrayList<Country> countries = countryReports.getDescendingPopulationOfCountry();

        assertNotNull(countries, "The country list should not be null.");
        assertTrue(countries.size() > 0, "The country list should contain data.");
    }

    @Test
    void testCityReports() throws SQLException {
        // Check that city reports are generated correctly
        CityReports cityReports = new CityReports(new Connection().getConnection());
        ArrayList<City> cities = cityReports.getPopulationOftheCitybyDescendingOrder();

        assertNotNull(cities, "The city list should not be null.");
        assertTrue(cities.size() > 0, "The city list should contain data.");
    }
}
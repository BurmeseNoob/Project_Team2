package IntegrationTest;

import DataBaseConnect.Connection;
import ProjectTeam2.*;
import Reports.CityReports;
import Reports.CountryReports;
import Reports.LanguageReports;
import Reports.PopulationReports;
import Reports.PopulationReports;
import Reports.SpecificPopulationReports;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AppIntegrationTest {

    static App app;

    @BeforeAll
    static void init() throws SQLException {
        app = new App();
        app.runApp(); // Running the app's logic to ensure everything initializes
    }@Test
    public void testGetLanguages() throws SQLException, ClassNotFoundException {
        // Step 1: Establish a connection and create the LanguageReports object
        Connection connection = new Connection();
        connection.connect("localhost:33060", 30000);
        LanguageReports lr = new LanguageReports(connection.getConnection());

        // Step 2: Retrieve the list of world languages and check it is not null or empty
        ArrayList<LanguageData> languagesSpeakList = lr.getWorldLanguagesSpeak();
        assertNotNull(languagesSpeakList, "Languages list should not be null.");
        assertFalse(languagesSpeakList.isEmpty(), "Languages list should not be empty.");

        // Step 3: Check for expected content in languagesSpeakList (assuming you know the expected output structure)
        for (LanguageData languageData : languagesSpeakList) {
            assertNotNull(languageData.getLanguage(), "Language name should not be null.");
        }

        // Step 4: Test the display function (if applicable)
        lr.displayWorldLanguagesSpeak(languagesSpeakList); // Check if this displays output as expected

        // Step 5: Close the connection after testing
        connection.disconnect();
    }
    @Test
    public void testRunCountryReports() throws SQLException, ClassNotFoundException {
        // Step 1: Establish a connection and create the CountryReports object
        Connection connection = new Connection();
        connection.connect("localhost:33060", 30000);
        CountryReports cr = new CountryReports(connection.getConnection());

        // Step 2: Test the descending population of countries
        ArrayList<Country> countries = cr.getDescendingPopulationOfCountry();
        assertNotNull(countries, "Countries list should not be null.");
        assertFalse(countries.isEmpty(), "Countries list should not be empty.");

        // Check if the countries list is in descending order by population
        for (int i = 1; i < countries.size(); i++) {
            assertTrue(countries.get(i - 1).getPopulation() >= countries.get(i).getPopulation(),
                    "Countries list should be in descending order of population.");
        }

        // Step 3: Test distinct continents
        ArrayList<String> continents = cr.getDistinctContinent();
        assertNotNull(continents, "Continents list should not be null.");
        assertFalse(continents.isEmpty(), "Continents list should not be empty.");

        // Step 4: Test distinct regions
        ArrayList<String> regions = cr.getDistinctRegion();
        assertNotNull(regions, "Regions list should not be null.");
        assertFalse(regions.isEmpty(), "Regions list should not be empty.");

        // Step 5: Test user input for top N populated countries
        int N = 10; // Example value for testing
        cr.displayTopPouplateCountrybyUser(N);
        cr.displayTopPopulatedCountryAccordingtoContinentByUserInput(N);
        cr.displayTopPopulatedCountryAccordingtoRegionByUserInput(N);

        // Step 6: Close the connection after testing
        connection.disconnect();
    }
    @Test
    public void testRunCityReports() throws SQLException, ClassNotFoundException {
        // Step 1: Establish a connection and create the CityReports object
        Connection connection = new Connection();
        connection.connect("localhost:33060", 30000);
        CityReports cy = new CityReports(connection.getConnection());

        // Step 2: Test retrieving and displaying cities by descending population
        ArrayList<City> allCities = cy.getPopulationOftheCitybyDescendingOrder();
        assertNotNull(allCities, "City list should not be null.");
        assertFalse(allCities.isEmpty(), "City list should not be empty.");

        // Check if the cities list is in descending order by population
        for (int i = 1; i < allCities.size(); i++) {
            assertTrue(allCities.get(i - 1).getPopulation() >= allCities.get(i).getPopulation(),
                    "City list should be in descending order of population.");
        }
        cy.cityReportFormat(allCities); // Assumes this displays the city report in a proper format

        // Step 3: Test retrieving distinct continents
        ArrayList<String> distinctContinents = cy.getDistinctContinent();
        assertNotNull(distinctContinents, "Continents list should not be null.");
        assertFalse(distinctContinents.isEmpty(), "Continents list should not be empty.");
        cy.getPopulationOftheCitybyContinent(distinctContinents);

        // Step 4: Test retrieving distinct regions
        ArrayList<String> distinctRegions = cy.getDistinctRegion();
        assertNotNull(distinctRegions, "Regions list should not be null.");
        assertFalse(distinctRegions.isEmpty(), "Regions list should not be empty.");
        cy.getPopulationOftheCitybyRegion(distinctRegions);

        // Step 5: Test retrieving distinct countries
        ArrayList<String> distinctCountries = cy.getDistinctCountry();
        assertNotNull(distinctCountries, "Countries list should not be null.");
        assertFalse(distinctCountries.isEmpty(), "Countries list should not be empty.");
        cy.getPopulationOftheCitybyCountry(distinctCountries);

        // Step 6: Test retrieving distinct districts
        ArrayList<String> distinctDistricts = cy.getDistinctDistrict();
        assertNotNull(distinctDistricts, "Districts list should not be null.");
        assertFalse(distinctDistricts.isEmpty(), "Districts list should not be empty.");
        cy.getPopulationOfthecitybyDistrict(distinctDistricts);

        // Step 7: Test top N cities by population
        int N = 10; // Example value for testing
        ArrayList<City> topCities = cy.getPopulationOfthecity(N);
        assertNotNull(topCities, "Top cities list should not be null.");
        assertFalse(topCities.isEmpty(), "Top cities list should not be empty.");
        cy.displayingOutputOfTheCityPopulationTopValueByN(topCities, N);

        // Step 8: Test top N cities by population within a continent, region, country, and district
        cy.getPopulationOftheCityByContinentTopN(N);
        cy.getPopulationOftheCityByRegionTopN(N);
        cy.getPopulationOftheCityByCountryTopN(N);
        cy.getPopulationOftheCityByDistrictTopN(N);

        // Step 9: Close the connection after testing
        connection.disconnect();
    }

}
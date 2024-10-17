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
    static Connection connection;  // Reusable connection instance

    @BeforeAll
    public void setUp() throws SQLException {
        // Initialize the app and set up the database connection once for all tests
        app = new App();
        connection = new Connection();
        connection.connect("localhost:33061", 60000);  // Connect once for reuse
        app.runApp(connection);
    }

    @AfterAll
    public void tearDown() throws SQLException {
        // Disconnect after all tests are completed
        connection.disconnect();
    }

    @Test
    public void testGetLanguages() throws SQLException {
        // Reuse the established connection for LanguageReports
        LanguageReports lr = new LanguageReports(connection.getConnection());

        // Step 2: Retrieve the list of world languages and check it is not null or empty
        ArrayList<LanguageData> languagesSpeakList = lr.getWorldLanguagesSpeak();
        assertNotNull(languagesSpeakList, "Languages list should not be null.");
        assertFalse(languagesSpeakList.isEmpty(), "Languages list should not be empty.");

        // Check for expected content in languagesSpeakList
        for (LanguageData languageData : languagesSpeakList) {
            assertNotNull(languageData.getLanguage(), "Language name should not be null.");
        }

        // Step 4: Test the display function (if applicable)
        lr.displayWorldLanguagesSpeak(languagesSpeakList);
    }

    @Test
    public void testRunCountryReports() throws SQLException {
        // Reuse the established connection for CountryReports
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
        int N = 10;  // Example value for testing
        cr.displayTopPouplateCountrybyUser(N);
        cr.displayTopPopulatedCountryAccordingtoContinentByUserInput(N);
        cr.displayTopPopulatedCountryAccordingtoRegionByUserInput(N);
    }

    @Test
    public void testRunCityReports() throws SQLException {
        // Reuse the established connection for CityReports
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
        cy.cityReportFormat(allCities);

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
        int N = 10;
        ArrayList<City> topCities = cy.getPopulationOfthecity(N);
        assertNotNull(topCities, "Top cities list should not be null.");
        assertFalse(topCities.isEmpty(), "Top cities list should not be empty.");
        cy.displayingOutputOfTheCityPopulationTopValueByN(topCities, N);

        // Step 8: Test top N cities by population within a continent, region, country, and district
        cy.getPopulationOftheCityByContinentTopN(N);
        cy.getPopulationOftheCityByRegionTopN(N);
        cy.getPopulationOftheCityByCountryTopN(N);
        cy.getPopulationOftheCityByDistrictTopN(N);
    }

    @Test
    public void testRunPopulationReports() throws SQLException {
        // Reuse the established connection for PopulationReports and SpecificPopulationReports
        PopulationReports pr = new PopulationReports(connection.getConnection());
        SpecificPopulationReports cpr = new SpecificPopulationReports(connection.getConnection());

        // Step 2: Test displaying population level in continent, region, and country
        pr.displayPopulationLevelInContinent();
        pr.displayPopulationLevelInRegion();
        pr.displayPopulationLevelInCountry();

        // Step 3: Test world population data
        ArrayList<SpecificPopulation> resultWorld = cpr.getWorldPopulation();
        assertNotNull(resultWorld, "World population result should not be null.");
        assertFalse(resultWorld.isEmpty(), "World population result should not be empty.");
        cpr.displayWorldPopulation(resultWorld);

        // Step 4: Test population data by continent
        ArrayList<SpecificPopulation> resultContinent = cpr.getContinentPopulation();
        assertNotNull(resultContinent, "Continent population result should not be null.");
        assertFalse(resultContinent.isEmpty(), "Continent population result should not be empty.");
        cpr.displayContinentPopulation(resultContinent);

        // Step 5: Test population data by region
        ArrayList<SpecificPopulation> resultRegion = cpr.getRegionPopulation();
        assertNotNull(resultRegion, "Region population result should not be null.");
        assertFalse(resultRegion.isEmpty(), "Region population result should not be empty.");
        cpr.displayRegionPopulation(resultRegion);

        // Step 6: Test population data by country
        ArrayList<SpecificPopulation> resultCountry = cpr.getCountryPopulation();
        assertNotNull(resultCountry, "Country population result should not be null.");
        assertFalse(resultCountry.isEmpty(), "Country population result should not be empty.");
        cpr.displayCountryPopulation(resultCountry);

        // Step 7: Test population data by district
        ArrayList<SpecificPopulation> resultDistrict = cpr.getDistrictPopulation();
        assertNotNull(resultDistrict, "District population result should not be null.");
        assertFalse(resultDistrict.isEmpty(), "District population result should not be empty.");
        cpr.displayDistrictPopulation(resultDistrict);

        // Step 8: Test population data by city
        ArrayList<SpecificPopulation> resultCity = cpr.getCityPopulation();
        assertNotNull(resultCity, "City population result should not be null.");
        assertFalse(resultCity.isEmpty(), "City population result should not be empty.");
        cpr.displayCityPopulation(resultCity);
    }
}

package UnitTest;

import static org.mockito.Mockito.*;

import ProjectTeam2.*;
import Reports.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.util.ArrayList;

public class AppClassTest {


    /*****
     * Mock object for CountryReports class, used to simulate behavior
     * without relying on the actual implementation.
     *****/
    private CountryReports mockCountryReports;
    /*****
     * Mock object for CityReports class, used to simulate behavior
     * without interacting with the actual CityReports implementation.
     *****/
    private CityReports mockCityReports;
    /*****
     * Mock object for PopulationReports class, used to test population-related
     * methods without depending on the actual data source.
     *****/
    private PopulationReports mockPopulationReports;
    /*****
     * Mock object for SpecificPopulationReports class, used to simulate methods
     * that retrieve population data at a specific level (e.g., continent, country).
     *****/
    private SpecificPopulationReports mockSpecificPopulationReports;
    /*****
     * Mock object for LanguageReports class, used to simulate behavior
     * for language report methods without relying on actual data.
     *****/
    private LanguageReports mockLanguageReports;
    /*****
     * App class instance being tested, responsible for running various report methods.
     *****/
    private App app;

    @BeforeEach
    void setUp() {
        mockCountryReports = mock(CountryReports.class); // Mocking CountryReports class
        mockCityReports = mock(CityReports.class); // Mocking CityReports class
        mockPopulationReports = mock(PopulationReports.class); // Mocking PopulationReports class
        mockSpecificPopulationReports = mock(SpecificPopulationReports.class); // Mocking SpecificPopulationReports class
        mockLanguageReports = mock(LanguageReports.class); // Mocking LanguageReports class
        app = new App();
    }


    //Test For the Country Report
    @Test
    void testRunCountryReports() throws SQLException {
        ArrayList<String> mockContinents = new ArrayList<>();
        mockContinents.add("Asia");
        mockContinents.add("Europe");

        ArrayList<String> mockRegions = new ArrayList<>();
        mockRegions.add("Western Europe");
        mockRegions.add("Eastern Asia");

        ArrayList<Country> mockCountries = new ArrayList<>();

        // Mock the behavior of the methods
        when(mockCountryReports.getDistinctContinent()).thenReturn(mockContinents);
        when(mockCountryReports.getDistinctRegion()).thenReturn(mockRegions);
        when(mockCountryReports.getDescendingPopulationOfCountry()).thenReturn(mockCountries);

        // Run the test method
        app.runCountryReports(mockCountryReports, 10);

        // Verify if the methods were called
        verify(mockCountryReports).getDescendingPopulationOfCountry();
        verify(mockCountryReports).displayingAboutDescendingPopulation(mockCountries);
        verify(mockCountryReports).getDistinctContinent();
        verify(mockCountryReports).displayingAboutDescendingPopulationByContinent(mockContinents);
        verify(mockCountryReports).getDistinctRegion();
        verify(mockCountryReports).displayingAboutDescendingPopulationByRegion(mockRegions);
        verify(mockCountryReports).displayTopPouplateCountrybyUser(10);
        verify(mockCountryReports).displayTopPopulatedCountryAccordingtoContinentByUserInput(10);
        verify(mockCountryReports).displayTopPopulatedCountryAccordingtoRegionByUserInput(10);
    }

    //Test for City Reports
    @Test
    void testRunCityReports() throws SQLException {
        ArrayList<String> mockContinents = new ArrayList<>();
        mockContinents.add("Asia");
        ArrayList<String> mockRegions = new ArrayList<>();
        mockRegions.add("Western Europe");
        ArrayList<String> mockCountries = new ArrayList<>();
        mockCountries.add("France");
        ArrayList<String> mockDistricts = new ArrayList<>();
        mockDistricts.add("California");
        ArrayList<City> mockCities = new ArrayList<>();

        // Mock the behavior of the methods
        when(mockCityReports.getDistinctContinent()).thenReturn(mockContinents);
        when(mockCityReports.getDistinctRegion()).thenReturn(mockRegions);
        when(mockCityReports.getDistinctCountry()).thenReturn(mockCountries);
        when(mockCityReports.getDistinctDistrict()).thenReturn(mockDistricts);
        when(mockCityReports.getPopulationOftheCitybyDescendingOrder()).thenReturn(mockCities);
        when(mockCityReports.getPopulationOfthecity(10)).thenReturn(mockCities);

        // Run the test method
        app.runCityReports(mockCityReports, 10);

        // Verify the methods were called
        verify(mockCityReports).getPopulationOftheCitybyDescendingOrder();
        verify(mockCityReports).cityReportFormat(mockCities);
        verify(mockCityReports).getDistinctContinent();
        verify(mockCityReports).getPopulationOftheCitybyContinent(mockContinents);
        verify(mockCityReports).getDistinctRegion();
        verify(mockCityReports).getPopulationOftheCitybyRegion(mockRegions);
        verify(mockCityReports).getDistinctCountry();
        verify(mockCityReports).getPopulationOftheCitybyCountry(mockCountries);
        verify(mockCityReports).getDistinctDistrict();
        verify(mockCityReports).getPopulationOfthecitybyDistrict(mockDistricts);
        verify(mockCityReports).getPopulationOfthecity(10);
        verify(mockCityReports).displayingOutputOfTheCityPopulationTopValueByN(mockCities, 10);
    }

    //TestThePopulationReports and Specific Population Reports
    @Test
    void testRunPopulationReports() throws SQLException {
        ArrayList<SpecificPopulation> mockSpecificPopulation = new ArrayList<>();

        // Mock the behavior of the methods
        when(mockSpecificPopulationReports.getWorldPopulation()).thenReturn(mockSpecificPopulation);
        when(mockSpecificPopulationReports.getContinentPopulation()).thenReturn(mockSpecificPopulation);
        when(mockSpecificPopulationReports.getRegionPopulation()).thenReturn(mockSpecificPopulation);
        when(mockSpecificPopulationReports.getCountryPopulation()).thenReturn(mockSpecificPopulation);
        when(mockSpecificPopulationReports.getDistrictPopulation()).thenReturn(mockSpecificPopulation);
        when(mockSpecificPopulationReports.getCityPopulation()).thenReturn(mockSpecificPopulation);

        // Run the test method
        app.runPopulationReports(mockPopulationReports, mockSpecificPopulationReports, 10);

        // Verify if methods were called
        verify(mockPopulationReports).displayPopulationLevelInContinent();
        verify(mockPopulationReports).displayPopulationLevelInRegion();
        verify(mockPopulationReports).displayPopulationLevelInCountry();

        verify(mockSpecificPopulationReports).getWorldPopulation();
        verify(mockSpecificPopulationReports).displayWorldPopulation(mockSpecificPopulation);
        verify(mockSpecificPopulationReports).getContinentPopulation();
        verify(mockSpecificPopulationReports).displayContinentPopulation(mockSpecificPopulation);
        verify(mockSpecificPopulationReports).getRegionPopulation();
        verify(mockSpecificPopulationReports).displayRegionPopulation(mockSpecificPopulation);
        verify(mockSpecificPopulationReports).getCountryPopulation();
        verify(mockSpecificPopulationReports).displayCountryPopulation(mockSpecificPopulation);
        verify(mockSpecificPopulationReports).getDistrictPopulation();
        verify(mockSpecificPopulationReports).displayDistrictPopulation(mockSpecificPopulation);
        verify(mockSpecificPopulationReports).getCityPopulation();
        verify(mockSpecificPopulationReports).displayCityPopulation(mockSpecificPopulation);
    }

    // Test for LANGUAGE METHODS
    @Test
    void testRunLanguageReports() throws SQLException {
        ArrayList<LanguageData> mockLanguageData = new ArrayList<>();

        // Mock the behavior of the methods
        when(mockLanguageReports.getWorldLanguagesSpeak()).thenReturn(mockLanguageData);

        // Run the test method
        app.runLanguageReports(mockLanguageReports);

        // Verify if methods were called
        verify(mockLanguageReports).getWorldLanguagesSpeak();
        verify(mockLanguageReports).displayWorldLanguagesSpeak(mockLanguageData);
    }


}

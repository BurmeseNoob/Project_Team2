package ProjectTeam2;
import Reports.*;
import DataBaseConnect.Connection;

import java.sql.*;
import java.util.ArrayList;

public class App {


    public static void main(String[] args) throws SQLException {
        //Inistiate the Object
        App app = new App();
        app.runApp();

        Connection ct = new Connection();

        if(args.length < 1){
            ct.connect("localhost:33060", 30000);
        }else{
            ct.connect(args[0], Integer.parseInt(args[1]));
        }
    }

    // Separated method to handle application logic, easier to test
    public void runApp() throws SQLException {
        // Instantiate the SQL database connection object.
        Connection ct = new Connection();
        ct.connect("localhost:33060", 30000);

        /**
         * Include Instantiate the respective reports object (Country, City, Population, Language and Specific Population)
         *
         * @param ct parameter need to insert the sql database connection to make querying
         * object1 : Country Reports
         * object2 : City Reports
         * object3 : Population Reports by specific column
         * object4 : Country Language Reports greatest number to smallest
         * object5 : Specific population by Country,City,District,Continent
         */
        // Instantiate the respective reports objects (Country, City, Population, Language and Specific Population)
        CountryReports cr = new CountryReports(ct.getConnection());
        CityReports cy = new CityReports(ct.getConnection());
        PopulationReports pr = new PopulationReports(ct.getConnection());
        LanguageReports lr = new LanguageReports(ct.getConnection());
        SpecificPopulationReports cpr = new SpecificPopulationReports(ct.getConnection());

        // Default setting: user input N value to test in the docker output
        int N = 10;

        // Separate each reporting task into its own method
//        runCountryReports(cr, N);
//        runCityReports(cy, N);
        runPopulationReports(pr, cpr, N);
        runLanguageReports(lr);

        // Close connection
        ct.disconnect();
    }

    // Separated methods to execute different tasks

    public void runCountryReports(CountryReports cr, int N) throws SQLException {

        /*
         * Method 1 to 6 is about the querying of the population respective to Continent, Region and World descending order
         *
         *  3 methods are respective with user N inputs value, top N rows of the table
         *
         * */

        // 1# Query about retrieving and displaying the data of the population of the Country according to the Descending Order
        ArrayList<Country> countries = cr.getDescendingPopulationOfCountry();
        cr.displayingAboutDescendingPopulation(countries);

        // 2# Report About the data of population of the Country ordered by descending according to Continent
        ArrayList<String> dcont = cr.getDistinctContinent();
        cr.displayingAboutDescendingPopulationByContinent(dcont);

        ArrayList<String> regions = cr.getDistinctRegion();
        cr.displayingAboutDescendingPopulationByRegion(regions);

        cr.displayTopPouplateCountrybyUser(N);
        cr.displayTopPopulatedCountryAccordingtoContinentByUserInput(N);
        cr.displayTopPopulatedCountryAccordingtoRegionByUserInput(N);
    }

    public void runCityReports(CityReports cy, int N) throws SQLException {
        ArrayList<City> allCities = cy.getPopulationOftheCitybyDescendingOrder();
        cy.cityReportFormat(allCities);

        ArrayList<String> distinctContinents = cy.getDistinctContinent();
        cy.getPopulationOftheCitybyContinent(distinctContinents);

        ArrayList<String> distinctRegions = cy.getDistinctRegion();
        cy.getPopulationOftheCitybyRegion(distinctRegions);

        ArrayList<String> distinctCountries = cy.getDistinctCountry();
        cy.getPopulationOftheCitybyCountry(distinctCountries);

        ArrayList<String> distinctDistricts = cy.getDistinctDistrict();
        cy.getPopulationOfthecitybyDistrict(distinctDistricts);

        ArrayList<City> topCities = cy.getPopulationOfthecity(N);
        cy.displayingOutputOfTheCityPopulationTopValueByN(topCities, N);

        cy.getPopulationOftheCityByContinentTopN(N);
        cy.getPopulationOftheCityByRegionTopN(N);
        cy.getPopulationOftheCityByCountryTopN(N);
        cy.getPopulationOftheCityByDistrictTopN(N);


    }

    public void runPopulationReports(PopulationReports pr, SpecificPopulationReports cpr, int N) throws SQLException {
        pr.displayPopulationLevelInContinent();
        pr.displayPopulationLevelInRegion();
        pr.displayPopulationLevelInCountry();

        ArrayList<SpecificPopulation> resultWorld = cpr.getWorldPopulation();
        cpr.displayWorldPopulation(resultWorld);

        ArrayList<SpecificPopulation> resultContinent = cpr.getContinentPopulation();
        cpr.displayContinentPopulation(resultContinent);

        ArrayList<SpecificPopulation> resultRegion = cpr.getRegionPopulation();
        cpr.displayRegionPopulation(resultRegion);

        ArrayList<SpecificPopulation> resultCountry = cpr.getCountryPopulation();
        cpr.displayCountryPopulation(resultCountry);

        ArrayList<SpecificPopulation> resultDistrict = cpr.getDistrictPopulation();
        cpr.displayDistrictPopulation(resultDistrict);

        ArrayList<SpecificPopulation> resultCity = cpr.getCityPopulation();
        cpr.displayCityPopulation(resultCity);
    }

    public void runLanguageReports(LanguageReports lr) throws SQLException {
        ArrayList<LanguageData> ld = lr.getWorldLanguagesSpeak();
        lr.displayWorldLanguagesSpeak(ld);
    }
}

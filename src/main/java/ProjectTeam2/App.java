    package ProjectTeam2;
    import Reports.*;
    import DataBaseConnect.Connection;

    import java.sql.*;
    import java.util.ArrayList;

    public class App {


        public static void main(String[] args) throws SQLException {
            //Inistiate the Object
            App app = new App();

            Connection ct = new Connection();

            if(args.length < 1){
                ct.connect("db:3306", 60000);
            }else{
                ct.connect(args[0], Integer.parseInt(args[1]));
            }
            //Run the application containing querying methods
            app.runApp(ct);
        }

        // Separated method to handle application logic, easier to test
        /*
         * Include Instantiate the respective reports object (Country, City, Population, Language and Specific Population)
         *
         * @param ct parameter need to insert the sql database connection to make querying
         * object1 : Country Reports
         * object2 : City Reports
         * object3 : Population Reports by specific column
         * object4 : Country Language Reports greatest number to smallest
         * object5 : Specific population by Country,City,District,Continent
         */
        public void runApp(Connection con) throws SQLException {
            // Instantiate the SQL database connection object.
            Connection ct = con;


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
    //        runPopulationReports(pr, cpr, N);
            runLanguageReports(lr);

            // Close connection
            ct.disconnect();
        }

        // Separated methods to execute different tasks


        /*
         * Method 1 to 6 is about the querying of the population respective to Continent, Region and World descending order
         *
         *  3 methods are respective with user N inputs value, top N rows of the table
         *
         * */
        public void runCountryReports(CountryReports cr, int N) throws SQLException {


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
        // Method to run city reports based on various criteria
        public void runCityReports(CityReports cy, int N) throws SQLException {
            // Retrieve all cities sorted by population in descending order
            ArrayList<City> allCities = cy.getPopulationOftheCitybyDescendingOrder();
            cy.cityReportFormat(allCities);

            // Get distinct continents and report city populations by continent
            ArrayList<String> distinctContinents = cy.getDistinctContinent();
            cy.getPopulationOftheCitybyContinent(distinctContinents);

            // Get distinct regions and report city populations by region
            ArrayList<String> distinctRegions = cy.getDistinctRegion();
            cy.getPopulationOftheCitybyRegion(distinctRegions);

            // Get distinct countries and report city populations by country
            ArrayList<String> distinctCountries = cy.getDistinctCountry();
            cy.getPopulationOftheCitybyCountry(distinctCountries);

            // Get distinct districts and report city populations by district
            ArrayList<String> distinctDistricts = cy.getDistinctDistrict();
            cy.getPopulationOfthecitybyDistrict(distinctDistricts);

            // Retrieve and display the top N cities by population
            ArrayList<City> topCities = cy.getPopulationOfthecity(N);
            cy.displayingOutputOfTheCityPopulationTopValueByN(topCities, N);

            // Retrieve population of the top N cities by continent, region, country, and district
            cy.getPopulationOftheCityByContinentTopN(N);
            cy.getPopulationOftheCityByRegionTopN(N);
            cy.getPopulationOftheCityByCountryTopN(N);
            cy.getPopulationOftheCityByDistrictTopN(N);


        }
        // Method to run population reports based on various criteria
        public void runPopulationReports(PopulationReports pr, SpecificPopulationReports cpr, int N) throws SQLException {
            // Display population levels for continents, regions, and countries
            pr.displayPopulationLevelInContinent();
            pr.displayPopulationLevelInRegion();
            pr.displayPopulationLevelInCountry();

            // Retrieve and display world population data
            ArrayList<SpecificPopulation> resultWorld = cpr.getWorldPopulation();
            cpr.displayWorldPopulation(resultWorld);

            // Retrieve and display continent population data
            ArrayList<SpecificPopulation> resultContinent = cpr.getContinentPopulation();
            cpr.displayContinentPopulation(resultContinent);

            // Retrieve and display region population data
            ArrayList<SpecificPopulation> resultRegion = cpr.getRegionPopulation();
            cpr.displayRegionPopulation(resultRegion);

            // Retrieve and display country population data
            ArrayList<SpecificPopulation> resultCountry = cpr.getCountryPopulation();
            cpr.displayCountryPopulation(resultCountry);

            // Retrieve and display district population data
            ArrayList<SpecificPopulation> resultDistrict = cpr.getDistrictPopulation();
            cpr.displayDistrictPopulation(resultDistrict);

            // Retrieve and display city population data
            ArrayList<SpecificPopulation> resultCity = cpr.getCityPopulation();
            cpr.displayCityPopulation(resultCity);
        }

        // Method to run language reports
        public void runLanguageReports(LanguageReports lr) throws SQLException {
            // Retrieve data on languages spoken worldwide
            ArrayList<LanguageData> ld = lr.getWorldLanguagesSpeak();
            // Display the retrieved language data
            lr.displayWorldLanguagesSpeak(ld);
        }
    }

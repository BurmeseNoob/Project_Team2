package ProjectTeam2;
import Reports.*;
import DataBaseConnect.Connection;

import java.sql.*;
import java.util.ArrayList;

public class App
{

    public static void main(String[] args) throws SQLException {


        // Instantiate the  SQL database connection object.
        Connection ct = new Connection();
        ct.connect();

        /**
         * Instantiate the respective reports object (Country, City, Population, Language and Specific Population)
         *
         * @param ct parameter need to insert the sql database connection to make querying
         * object1 : Country Reports
         * object2 : City Reports
         * object3 : Population Reports by specific column
         * object4 : Country Language Reports greatest number to smallest
         * object5 : Specific population by Country,City,District,Continent
         */
        CountryReports cr = new CountryReports(ct.getConnection());
        CityReports cy = new CityReports(ct.getConnection());
        PopulationReports pr = new PopulationReports(ct.getConnection());
        LanguageReports lr  = new LanguageReports(ct.getConnection());
        SpecificPopulationReports cpr = new SpecificPopulationReports(ct.getConnection());


        // Default setting the user input N value to test in the docker output
        int N = 10;

        /*
        * Method 1 to 6 is about the querying of the population respective to Continent, Region and World descending order
        *
        *  3 methods are respective with user N inputs value, top N rows of the table
        *
        * */

// 1# Query about retrieving and displaying the data of the population of the Country according to the Descending Order
//        ArrayList<Country> countries = cr.getDescendingPopulationOfCountry();
//        cr.displayingAboutDescendingPopulation(countries);
//        System.out.println("");
//
//// 2# Report About the data of population of the Country ordered by descending according to Continent
//        ArrayList<String> dcont = cr.getDistinctContinent();
//        cr.displayingAboutDescendingPopulationByContinent(dcont); // Fix: Added 'cr.' to call the method correctly
//        System.out.println("");
//
//// 3# Report About the data of population of the Country ordered by descending according to Region
//        ArrayList<String> regions = cr.getDistinctRegion(); // Fix: Get distinct regions
//        cr.displayingAboutDescendingPopulationByRegion(regions); // Fix: Call the method correctly
//        System.out.println("");
//
//// 4# Report about the top populated country limited by user
//        cr.displayTopPouplateCountrybyUser(N); // Fix: Call the method directly
//        System.out.println("");
//
//// 5# Report about the top populated country according to Continent limited by user
//        cr.displayTopPopulatedCountryAccordingtoContinentByUserInput(N); // Fix: Call the method directly
//        System.out.println("");
//
//// 6# Report about the top populated country according to Region limited by user
//        cr.displayTopPopulatedCountryAccordingtoRegionByUserInput(N); // Fix: Call the method directly
//        System.out.println("");
//
//       /**** ----------------------------------------------------------- ****/
//
//        /*
//         * Method 7 to 16 is about the querying of the Cities population respective to Continent, Region, District, Country descending order
//         *
//         *  4 methods are respective with user N inputs value, top N rows of the table
//         *
//         * */
//
/// 7. Cities in the world organised by largest population to smallest
        ArrayList<City> allCities = cy.getPopulationOftheCitybyDescendingOrder(); // get the list
        cy.cityReportFormat(allCities); // display the formatted report

// 8. Cities in a continent organised by largest population to smallest
        ArrayList<String> distinctContinents = cy.getDistinctContinent();
        cy.getPopulationOftheCitybyContinent(distinctContinents);

// 9. Cities in a region organised by largest population to smallest
        ArrayList<String> distinctRegions = cy.getDistinctRegion();
        cy.getPopulationOftheCitybyRegion(distinctRegions);

// 10. Cities in a country organised by largest population to smallest
        ArrayList<String> distinctCountries = cy.getDistinctCountry();
        cy.getPopulationOftheCitybyCountry(distinctCountries);

// 11. Cities in a district organised by largest population to smallest
        ArrayList<String> distinctDistricts = cy.getDistinctDistrict();
        cy.getPopulationOfthecitybyDistrict(distinctDistricts);

// 12. Top N populated cities in the world where N is provided by the user
        ArrayList<City> topCities = cy.getPopulationOfthecity(N);
        cy.displayingOutputOfTheCityPopulationTopValueByN(topCities, N);
//
// 13. Top N populated cities in a continent where N is provided by the user
        int NContinent = 15;
        cy.getPopulationOftheCityByContinentTopN(NContinent);

// 14. Top N populated cities in a region where N is provided by the user
        int NRegion = 5;
        cy.getPopulationOftheCityByRegionTopN(NRegion);

// 15. Top N populated cities by Country where N is provided by the user
        int NCountry = 8;
        cy.getPopulationOftheCityByCountryTopN(NCountry);

// 16. Top N populated cities in a district where N is provided by the user
        int NDistrict = 9;
        cy.getPopulationOftheCityByDistrictTopN(NDistrict);
//
//        /**** ----------------------------------------------------------- ****/
//
//        /*
//         * Method 17 to 22 is about the querying of the CapitalCities population respective to Continent, Region, World descending order
//         *
//         *  3 methods are respective with user N inputs value, top N rows of the table
//         *
//         * */
//
//        //17# Capital cities in the world organised by largest population to smallest.
//        cy.getPopulationOftheCapitalCities();
//
//        //18# Capital cities in a continent organised by largest population to smallest.
//        cy.getPopulationOftheCapitalCitiesInContinent();
//
//        //19# Capital cities in a region organised by largest to smallest.
//        cy.getCapitalCityInRegion();
//
//        //20# Top N populated capital cities in the world where N is provided by the user
//        cy.getTopPopulatedCapitalCities(N);
//
//        //21# Top N populated capital cities in a continent where N is provided by the user
//        cy.getTopPopulatedCapitalCitiesInContinent(N);
//
//        //22# Top N populated capital cities in a region where N is provided by the user
//        cy.getTopPopulatedCapitalCitiesInRegion(N);
//
//        /**** ----------------------------------------------------------- ****/
//
//        /*
//         * Method 23 to 25 is population of the people especially who is living in cities and who are not living
//         * in cities by continents, region and country
//         * */
//
//        //23# Population of people, people living in cities, and people not living in cities in each continent.
//        pr.displayPopulationLevelInContinent();
//
//        //24# Population of people, people living in cities, and people not living in cities in each region.
//        pr.displayPopulationLevelInRegion();
//
//        //25# Population of people, people living in cities, and people not living in cities in each country
//        pr.displayPopulationLevelInCountry();

        /**** ----------------------------------------------------------- ****/

        /*
         * Method 26 to 31 is the querying about the population of the world, specific continent, region, country , district and city.
         * */

//        //26# Population of the world
//        // 1st Store the  array list return from get ContinentPopulation and Use in the Display method
//        ArrayList<SpecificPopulation> resultWorld = cpr.getWorldPopulation();
//        cpr.displayWorldPopulation(resultWorld);
//
//        //27# Population of a continent
//        // 1st Store the  array list return from get ContinentPopulation and Use in the Display method
//        ArrayList<SpecificPopulation> resultContinent = cpr.getContinentPopulation();
//        cpr.displayContinentPopulation(resultContinent);
//
//        //28# Population of a region
//        // 1st Store the  array list return from RegionPopulation and Use in the Display method
//        ArrayList<SpecificPopulation> resultRegion = cpr.getRegionPopulation();
//        cpr.displayRegionPopulation(resultRegion);

        //29# Population of a country
//        // 1st Store the  array list return from CountryPopulation and Use in the Display method
//        ArrayList<SpecificPopulation> resultCountry = cpr.getCountryPopulation();
//        cpr.displayCountryPopulation(resultCountry);

        //30# Population of a district
        // 1st Store the  array list return from DistrictPopulation and Use in the Display method
//        ArrayList<SpecificPopulation> resultDistrict = cpr.getDistrictPopulation();
//        cpr.displayDistrictPopulation(resultDistrict);

        //31# Population of a city
        // 1st Store the  array list return from DistrictPopulation and Use in the Display method
//        ArrayList<SpecificPopulation> resultCity = cpr.getCityPopulation();
//        cpr.displayCityPopulation(resultCity);

        /**** ----------------------------------------------------------- ****/

        /*
         * Method 32 is the querying of language speaker from greatest to smallest including the percentage of world population
         * language are (Chinese,English,Hindi,Spanish,Arabic.)
         * */

        //32#Languages Report
        //Store the languages data into list and pass the parameter to the display method
//        ArrayList<LanguageData> ld = lr.getWorldLanguagesSpeak();
//        lr.displayWorldLanguagesSpeak(ld);



































        ct.disconnect();

    }
}
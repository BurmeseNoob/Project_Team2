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
        LanguagesReports lr  = new LanguagesReports(ct.getConnection());
        SpecificPopulationReports cpr = new SpecificPopulationReports(ct.getConnection());


        // Default setting the user input N value to test in the docker output
        int N = 10;

        /*
        * Method 1 to 6 is about the querying of the population respective to Continent, Region and World descending order
        *
        *  3 methods are respective with user N inputs value, top N rows of the table
        *
        * */

        //1# Query about retrieving  and displaying the data of the population of the Country according to the Descending Order
        cr.displayingAboutDescendingPopulation();
       System.out.println("");

        //2# Report About the data of population of the Country order by descending according to Continent
        cr.displayingAboutDescendingPopulationByContinent();
       System.out.println("");

        //3# Report About the data of population of the Country order by descending according to Region
        cr.displayingAboutDescendingPopulationByRegion();
       System.out.println("");

        //4# Report about top populate country limit by user
        cr.displayTopPouplateCountrybyUser(N);
       System.out.println("");

       //5# Report about top populate country according to Continent limit by user
        cr.displayTopPopulatedCountryAccordingtoContinentByUserInput(N);
       System.out.println("");

       //6# Report about top populate country according to Region limit by user
       cr.displayTopPopulatedCountryAccordingtoRegionByUserInput(N);
       System.out.println("");

       /**** ----------------------------------------------------------- ****/

        /*
         * Method 7 to 16 is about the querying of the Cities population respective to Continent, Region, District, Country descending order
         *
         *  4 methods are respective with user N inputs value, top N rows of the table
         *
         * */

        //7# Cities in the world organised by largest population to smallest
        cy.displayThePopulationOfCity();

        //8# Cities in a continent organised by largest population to smallest.
        cy.getPopulationOftheCitybyContinent();

        //9# Cities in a region organised by largest population to smallest.
        cy.getPopulationOftheCitybyRegion();

        //10# Cities in a country organised by largest population to smallest.
        cy.getPopulationOftheCitybyCountry();

        //11 # Cities in a district organised by largest population to smallest.
        cy.getPopulationOfthecitybyDistrict();

        //12# Top N populated cities in the world where N is provided by the user.
        cy.displayingOutputOfTheCityPopulationTopValueByN(N);

        //13# Top N populated cities in a continent where N is provided by the user
        cy.getPopulationOftheCityByContinentTopN(N);

        //14# Top N populated cities in a region where N is provided by the user.
        cy.getPopulationOftheCityByRegionTopN(N);

        //15# Top N populated cities in a country where N is provided by the user.
        cy.getPopulationOftheCityByCountryTopN(N);

        //16# Top N populated cities in a district where N is provided by the user
        cy.getPopulationOftheCityByDistrictTopN(N);


        /**** ----------------------------------------------------------- ****/

        /*
         * Method 17 to 22 is about the querying of the CapitalCities population respective to Continent, Region, World descending order
         *
         *  3 methods are respective with user N inputs value, top N rows of the table
         *
         * */

        //17# Capital cities in the world organised by largest population to smallest.
        cy.getPopulationOftheCapitalCities();

        //18# Capital cities in a continent organised by largest population to smallest.
        cy.getPopulationOftheCapitalCitiesInContinent();

        //19# Capital cities in a region organised by largest to smallest.
        cy.getCapitalCityInRegion();

        //20# Top N populated capital cities in the world where N is provided by the user
        cy.getTopPopulatedCapitalCities(N);

        //21# Top N populated capital cities in a continent where N is provided by the user
        cy.getTopPopulatedCapitalCitiesInContinent(N);

        //22# Top N populated capital cities in a region where N is provided by the user
        cy.getTopPopulatedCapitalCitiesInRegion(N);

        /**** ----------------------------------------------------------- ****/

        /*
         * Method 23 to 25 is population of the people especially who is living in cities and who are not living
         * in cities by continents, region and country
         * */

        //23# Population of people, people living in cities, and people not living in cities in each continent.
        pr.displayPopulationLevelInContinent();

        //24# Population of people, people living in cities, and people not living in cities in each region.
        pr.displayPopulationLevelInRegion();

        //25# Population of people, people living in cities, and people not living in cities in each country
        pr.displayPopulationLevelInCountry();

        /**** ----------------------------------------------------------- ****/

        /*
         * Method 26 to 31 is the querying about the population of the world, specific continent, region, country , district and city.
         * */

        //26# Population of the world
        cpr.displayWorldPopulation();

        //27# Population of a continent
        cpr.displayContinentPopulation();

        //28# Population of a region
        cpr.displayRegionPopulation();

        //29# Population of a country
        cpr.displayCountryPopulation();

        //30# Population of a district
        cpr.displayDistrictPopulation();

        //31# Population of a city
        cpr.displayCityPopulation();

        /**** ----------------------------------------------------------- ****/

        /*
         * Method 32 is the querying of language speaker from greatest to smallest including the percentage of world population
         * language are (Chinese,English,Hindi,Spanish,Arabic.)
         * */

        //32#Languages Report
       lr.displayWorldLanguagesSpeak();



































        ct.disconnect();

    }
}
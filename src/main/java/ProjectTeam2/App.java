package ProjectTeam2;
import Reports.CityReports;
import Reports.CountryReports;
import DataBaseConnect.Connection;
import Reports.LanguagesReports;
import Reports.PopulationReports;

import java.sql.*;
import java.util.ArrayList;

public class App
{

    public static void main(String[] args) throws SQLException {

        Connection ct = new Connection();
        ct.connect();

        //Creating the CountyReport Object to get the Data for displaying the system.
        CountryReports cr = new CountryReports(ct.getConnection());
        CityReports cy = new CityReports(ct.getConnection());
        PopulationReports pr = new PopulationReports(ct.getConnection());
        LanguagesReports lr  = new LanguagesReports(ct.getConnection());
//
//        //1# Query about retrieving  and displaying the data of the population of the Country according to the Descending Order
//        cr.displayingAboutDescendingPopulation();
//        System.out.println("");
//        //2# Report About the data of population of the Country order by descending according to Continent
//        cr.displayingAboutDescendingPopulationByContinent();
//        System.out.println("");
//        //3# Report About the data of population of the Country order by descending according to Region
//        cr.displayingAboutDescendingPopulationByRegion();
//        System.out.println("");
//        //4# Report about top populate country limit by user
          int N = 10;
//        cr.displayTopPouplateCountrybyUser(N);
//        System.out.println("");
//
//        //5# Report about top populate country according to Continent limit by user
//        cr.displayTopPopulatedCountryAccordingtoContinentByUserInput(N);
//        System.out.println("");
//
//        //6# Report about top populate country according to Region limit by user
//        cr.displayTopPopulatedCountryAccordingtoRegionByUserInput(N);
//        System.out.println("");
        //7# (Cities in the world organised by largest population to smallest.)
//        cy.displayThePopulationOfCity();

        //8#(Cities in a continent organised by largest population to smallest.)
//        cy.getPopulationOftheCitybyContinent();

        //9# (Cities in a region organised by largest population to smallest.)
//        cy.getPopulationOftheCitybyRegion();
        //10# (cities in a country organised by largest population to smallest)
//        cy.getPopulationOftheCitybyCountry();
        //11# (Cities in a district organised by largest population to smallest.)
//        cy.getPopulationOfthecitybyDistrict();

        //12# (top N populated cities in the world where N is provided by the user)
//        cy.displayingOutputOfTheCityPopulationTopValueByN(N);

        //13# (top N populated cities in a continent where N is provided by the user.)
//        cy.getPopulationOftheCityByContinentTopN(N);

        //14# (top N populated cities in a region where N is provided by the user.)
//        cy.getPopulationOftheCityByRegionTopN(N);

        //15# (top N populated cities in a country where N is provided by the user)
//        cy.getPopulationOftheCityByCountryTopN(N);


        //16# (top N populated cities in a district where N is provided by the user)
//        cy.getPopulationOftheCityByDistrictTopN(N);

        //17# (capital cities in the world organised by largest population to smallest)
//            cy.getPopulationOftheCapitalCities();

        //18# (Capital cities in a continent organised by largest population to smallest)
//        cy.getPopulationOftheCapitalCitiesInContinent();

        //19# (Capital cities in a region organised by largest to smallest.)
//        cy.getCapitalCityInRegion();

        //20# (Top N populated capital cities in the world where N is provided by the user)
//        cy.getTopPopulatedCapitalCities(N);


//        //21#
//        cy.getTopPopulatedCapitalCitiesInContinent(N);
//
//        //22#
//        cy.getTopPopulatedCapitalCitiesInRegion(N);


        //23#
//        pr.displayPopulationLevelInContinent();

        //24#
//        pr.displayPopulationLevelInRegion();

        //25#

        pr.displayPopulationLevelInCountry();



        //   Languages Report
//        lr.displayWorldLanguagesSpeak();





        ct.disconnect();

    }
}
package ProjectTeam2;
import Reports.CityReports;
import Reports.CountryReports;
import DataBaseConnect.Connection;

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
//        int N = 10;
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
        cy.getPopulationOftheCitybyCountry();
        //11# (Cities in a district organised by largest population to smallest.)
        cy.getPopulationOfthecitybyDistrict();


        ct.disconnect();

    }
}
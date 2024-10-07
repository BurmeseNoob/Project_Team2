package Reports;
import ProjectTeam2.City;
import ProjectTeam2.Country;
import com.google.protobuf.Message;

import java.sql.*;
import java.util.ArrayList;

public class CountryReports {

    private Connection con;

    // Connection from SQL
    public CountryReports(Connection con) {
        this.con = con;
    }

    //#1 (countries in the world organised by largest population to smallest)
    public ArrayList<Country> getDescendingPopulationOfCountry()
    {
        ArrayList<Country> report = new ArrayList<>();

        try
        {
            // Prepare the SQL statement
            Statement stmt = con.createStatement();
            // SQL Command to retrieve
            String strSelect = "SELECT Code, Name, Continent, Region, Population, Capital " +
                    "FROM country " +
                    "ORDER BY Population DESC;";
            // Execute the Command
            ResultSet rset = stmt.executeQuery(strSelect);

            // Iterating the Data
            while (rset.next())
            {
                // Create a new Country object
                Country ct = new Country(
                        rset.getString("Code"),
                        rset.getString("Name"),
                        rset.getString("Continent"),
                        rset.getString("Region"),
                        rset.getInt("Population"),
                        rset.getInt("Capital")
                );

                //add data to the arraylist
                report.add(ct);
            }

            // Return the report list
            return report;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the Data");
            return null;
        }
    }

    public void displayingAboutDescendingPopulation()
    {
        ArrayList<Country> report = getDescendingPopulationOfCountry();

        System.out.println("+----------------------------------------------------------------+");
        System.out.println("+       Lists of the Country Population Descending Order        +");
        System.out.println("+----------------------------------------------------------------+");


        // Checking about list is not null
        if (report != null && !report.isEmpty())
        {
            System.out.println("+-----------------------------------------------------------------------------------------------------------------------------+");
            System.out.printf("%-10s %-30s %-20s %-20s %-49s %-39s%n", "CountryCode", "Name", "Continent", "Region", "Population", "Capital");
            System.out.println("+-----------------------------------------------------------------------------------------------------------------------------+");

            // Loop through the ArrayList and display each country in table format
            for (Country country : report)
            {
                System.out.printf("%-10s %-30s %-20s %-20s %-49s %-39s%n",
                        country.getCode(),
                        country.getName(),
                        country.getContinent(),
                        country.getRegion(),
                        country.getPopulation(),
                        country.getCapital());
            }
        }
        else
        {
            System.out.println("No countries found.");
        }
    }


    //#########################################################################
    //#2 (Countries in a continent organised by largest population to smallest.)
    public ArrayList<String> getDistinctContinent()
    {
        // get the ditinctContinent to iterate for population
        ArrayList<String> continentList = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            //Iterating about distinct Continct in the country table
            String strSelect = "SELECT DISTINCT Continent FROM country";
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next()) {
                continentList.add(rset.getString("Continent"));
            }

            return continentList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public ArrayList<Country> getDescendingPopulatinOfCountryByContinent(String continent)
    {
        ArrayList<Country> countryList = new ArrayList<>();
        try {
            String strSelect = "SELECT Code, Name, Continent, Region, Population, Capital " +
                    "FROM country WHERE Continent = ? ORDER BY Population DESC";
            PreparedStatement pstmt = con.prepareStatement(strSelect);
            pstmt.setString(1, continent);
            ResultSet rset = pstmt.executeQuery();

            //iterating the result from query
            while (rset.next()) {
                Country ct = new Country(
                        rset.getString("Code"),
                        rset.getString("Name"),
                        rset.getString("Continent"),
                        rset.getString("Region"),
                        rset.getInt("Population"),
                        rset.getInt("Capital")
                );
                countryList.add(ct);
            }

            return countryList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void displayingAboutDescendingPopulationByContinent()
    {
        //Arraylist of getting Distinct Continent
        ArrayList<String> continents = getDistinctContinent();
        //Iterating the Cotinent arraylist and querying specific population
        for(String continent: continents)
        {
            System.out.println("");
            System.out.println("Descend order of Population in Country of : " + "<" + continent + ">");
            System.out.println("_______________________________________________________________________");

            ArrayList<Country> countries= getDescendingPopulatinOfCountryByContinent(continent);

            for (Country country : countries)
            {
                System.out.println("Country Code: " + country.getCode() +
                                    ",  Name: " + country.getName() +
                                    ",  Region: " + country.getRegion() +
                                    ",  Population: " + country.getPopulation());
            }
        }

    }

    //#########################################################################
    //#3 (Countries in a region  organised by largest population to smallest.)
    //all the code flow are same as report2

    public ArrayList<String> getDistinctRegion()
    {
        // get the ditinctRegion to iterate for population
        ArrayList<String> regionList = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            //Iterating about distinct region in the country table
            String strSelect = "SELECT DISTINCT Region FROM country";
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next()) {
                regionList.add(rset.getString("Region"));
            }

            return regionList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<Country> getDescendingPopulatinOfCountryByRegion(String region)
    {
        ArrayList<Country> regionList = new ArrayList<>();
        try {
            String strSelect = "SELECT Code, Name, Continent, Region, Population, Capital " +
                    "FROM country WHERE Region = ? ORDER BY Population DESC";
            PreparedStatement pstmt = con.prepareStatement(strSelect);
            pstmt.setString(1, region);
            ResultSet rset = pstmt.executeQuery();

            //iterating the result from query
            while (rset.next()) {
                Country ct = new Country(
                        rset.getString("Code"),
                        rset.getString("Name"),
                        rset.getString("Continent"),
                        rset.getString("Region"),
                        rset.getInt("Population"),
                        rset.getInt("Capital")
                );
                regionList.add(ct);
            }

            return regionList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void displayingAboutDescendingPopulationByRegion()
    {
        //Arraylist of getting Distinct Continent
        ArrayList<String> regions = getDistinctRegion();
        //Iterating the Cotinent arraylist and querying specific population
        for(String region: regions)
        {
            System.out.println("");
            System.out.println("Descend order of Population in Region of : " + "<" + region + ">");
            System.out.println("_______________________________________________________________________");

            ArrayList<Country> countries= getDescendingPopulatinOfCountryByRegion(region);

            for (Country country : countries)
            {
                System.out.println("Country Code: " + country.getCode() +
                        ",  Name: " + country.getName() +
                        ",  Region: " + country.getRegion() +
                        ",  Population: " + country.getPopulation());
            }
        }

    }

    //#########################################################################
    //#4 (populated countries in the world according to top values give by user)
    public ArrayList<Country> getTopPopulatedCountry(int N)
    {
        ArrayList<Country> countryList = new ArrayList<>();
        try {
            String command = "SELECT Code, Name, Continent, Region, Population,Capital " +
                    "FROM country ORDER BY Population DESC LIMIT ?";

            // preparing the command
            PreparedStatement  pstmt = con.prepareStatement(command);
            pstmt.setInt(1,N);

            //execute
            ResultSet rset = pstmt.executeQuery();

            //iterating and addto arraylist
            while (rset.next()) {
                Country ct = new Country(
                        rset.getString("Code"),
                        rset.getString("Name"),
                        rset.getString("Continent"),
                        rset.getString("Region"),
                        rset.getInt("Population"),
                        rset.getInt("Capital")
                );
                countryList.add(ct);
            }
            return countryList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void displayTopPouplateCountrybyUser(int N)
    {
        // get the country list
        ArrayList<Country> countries = getTopPopulatedCountry(N);

        System.out.println("");
        System.out.println("Top " + N + " populated Countries ! ");
        System.out.println("_______________________________________________________________________");

        for (Country country : countries) {
            System.out.println("CountryCode: " + country.getCode() +
                    ",  Name: " + country.getName() +
                    ",  Region: " + country.getRegion() +
                    ",  Population: " + country.getPopulation());
        }

    }

    //#########################################################################
    //#5 (populated countries in the world according to top values give by user and each Continent)
    public void displayTopPopulatedCountryAccordingtoContinentByUserInput(int N)
    {
        ArrayList<String> Dcontinents = getDistinctContinent();

        for(String continent : Dcontinents)
        {
            ArrayList<Country> Countries = getDescendingPopulatinOfCountryByContinent(continent);
            System.out.println("");
            System.out.println("Top " + N + " Descend order of Population in Country of : " + "<" + continent + ">");
            System.out.println("_______________________________________________________________________");

            // Check if  the users' N exceeds the number of countries in this continent
            int limit = Math.min(N, Countries.size());
            //Slice the Countries arraylist according to user input
            for(int i =0; i < limit; i++ )
            {
                Country ct = Countries.get(i);
                System.out.println("Country Code: " + ct.getCode() +
                        ",  Name: " + ct.getName() +
                        ",  Region: " + ct.getRegion() +
                        ",  Population: " + ct.getPopulation());
            }
        }
    }
    //#########################################################################
    //#6 (populated countries in the world according to top values give by user and each Region)
    //same as report 5 codes
    public void displayTopPopulatedCountryAccordingtoRegionByUserInput(int N)
    {
        ArrayList<String> Dregions = getDistinctRegion();
        for(String region : Dregions)
        {
            ArrayList<Country> Countries = getDescendingPopulatinOfCountryByRegion(region);
            System.out.println("");
            System.out.println("Top " + N + " Descend order of Population in Country of : " + "<" + region + ">");
            System.out.println("_______________________________________________________________________");


            int limit = Math.min(N, Countries.size());
            //Slice the Countries arraylist according to user input
            for(int i =0; i < limit; i++ )
            {
                Country ct = Countries.get(i);
                System.out.println("Country Code: " + ct.getCode() +
                        ",  Name: " + ct.getName() +
                        ",  Region: " + ct.getRegion() +
                        ",  Population: " + ct.getPopulation());
            }
        }
    }










}

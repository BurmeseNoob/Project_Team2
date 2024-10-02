package Reports;
import ProjectTeam2.City;
import ProjectTeam2.Country;
import com.google.protobuf.Message;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CountryReports {

    private Connection con;

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

    //#2



}

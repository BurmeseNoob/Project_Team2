package Reports;
import java.sql.*;
import java.util.ArrayList;
import ProjectTeam2.City;
import ProjectTeam2.PopulationLevel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class  PopulationReports {

    private Connection con;

    public PopulationReports(Connection con) {
        this.con = con;
    }

    // Initiate the PopulationReports Display format
    public void displayFormatforPopulationReports(ArrayList<PopulationLevel> pops)
    {

        //checking the population level record is null
        if (pops == null || pops.size() == 0)
        {
            System.out.println("No population data found");
            return;
        }
        ArrayList<PopulationLevel> poplists = pops;
        //Table header format
        System.out.println("+-------------------------------------------------+----------------------+----------------------+-----------------+-----------------------------+-------------------+");
        System.out.printf("| %-47s | %-20s | %-20s |  %-14s | %-27s |   %-15s  | %n",
                "Name", "Total Population", "Population In Cities", "% in Cities", "Population Not in Cities", "% Not in Cities"
        );
        System.out.println("+-------------------------------------------------+----------------------+----------------------+-----------------+-----------------------------+-------------------+");



        for(PopulationLevel pop : poplists)
        {
            if (pop == null)
                continue;
            System.out.printf("| %-47s | %,20d | %,20d |     %8.2f%%   | %,27d |     %8.2f%%      | %n",
                    pop.getName(),pop.getTotalPopulation(),pop.getPopulationInCities(),pop.getPercentagesInCities(),pop.getPopulationNotInCities(),pop.getPercentagesNotInCities());
        }
        System.out.println("+-------------------------------------------------+----------------------+----------------------+-----------------+-----------------------------+-------------------+");

    }

    //23# (Population of people, people living in cities, and people not living in cities in each continent)
    public ArrayList<PopulationLevel> getPopulationLevelInContinent() throws SQLException {
        try {
            ArrayList<PopulationLevel> resultList= new ArrayList<>();
            //SQL statement creating
            Statement stmt = con.createStatement();
            //Command building
            String command = "SELECT \n" +
                    "    country.Continent AS Name, \n" + // to show in table
                    "    SUM(country.Population) AS TotalPopulation,\n" + //Show about total population
                    "    SUM(city.Population) AS PopulationInCities,\n" +
                    "    (SUM(city.Population) / SUM(country.Population) * 100) AS PercentPopulationInCities,\n" + //calculation about %
                    "    SUM(country.Population) - SUM(city.Population) AS PopulationNotInCities,\n" + //calculation about %
                    "    ((SUM(country.Population) - SUM(city.Population)) / SUM(country.Population) * 100) AS PercentPopulationNotInCities\n" + //calculation about %
                    "FROM \n" +
                    "       country\n" +
                    "LEFT JOIN \n" +
                    "    city ON country.Code = city.CountryCode\n" + // two table joining
                    "GROUP BY \n" +
                    "    country.Continent;\n";
            // Execute the Command
            ResultSet rset = stmt.executeQuery(command);

            while(rset.next())
            {
                resultList.add(new PopulationLevel(
                        rset.getString("Name"),
                        rset.getLong("TotalPopulation"),
                        rset.getLong("PopulationInCities"),
                        rset.getDouble("PercentPopulationInCities"),
                        rset.getLong("PopulationNotInCities"),
                        rset.getDouble("PercentPopulationNotInCities")
                ));
            }

            return resultList;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void displayPopulationLevelInContinent() throws SQLException {
        // Displaying the output for Population level in Continent
        System.out.println("Population Level Report of Continent");
        System.out.println("__________________________________");
        // method for Displaying the output for Population level in Continent
        displayFormatforPopulationReports(getPopulationLevelInContinent());
        System.out.println("");
    }

    //24# (Population of people, people living in cities, and people not living in cities in each Region)
    public ArrayList<PopulationLevel> getPopulationLevelInRegion()throws SQLException
    {
        try {
            ArrayList<PopulationLevel> resultList= new ArrayList<>();
            //SQL statement creating
            Statement stmt = con.createStatement();
            //Command building
            String command = "SELECT \n" +
                    "    country.Region AS Name, \n" + // to show in table
                    "    SUM(country.Population) AS TotalPopulation,\n" + //Show about total population
                    "    SUM(city.Population) AS PopulationInCities,\n" +
                    "    (SUM(city.Population) / SUM(country.Population) * 100) AS PercentPopulationInCities,\n" + //calculation about %
                    "    SUM(country.Population) - SUM(city.Population) AS PopulationNotInCities,\n" + //calculation about %
                    "    ((SUM(country.Population) - SUM(city.Population)) / SUM(country.Population) * 100) AS PercentPopulationNotInCities\n" + //calculation about %
                    "FROM \n" +
                    "       country\n" +
                    "LEFT JOIN \n" +
                    "    city ON country.Code = city.CountryCode\n" + // two table joining
                    "GROUP BY \n" +
                    "    country.Region;\n"; // each region
            // Execute the Command
            ResultSet rset = stmt.executeQuery(command);

            while(rset.next())
            {
                resultList.add(new PopulationLevel(
                        rset.getString("Name"),
                        rset.getLong("TotalPopulation"),
                        rset.getLong("PopulationInCities"),
                        rset.getDouble("PercentPopulationInCities"),
                        rset.getLong("PopulationNotInCities"),
                        rset.getDouble("PercentPopulationNotInCities")
                ));
            }
            return resultList;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void displayPopulationLevelInRegion() throws SQLException {
        // Displaying the output for Population level in Region
        System.out.println("Population Level Report of Region");
        System.out.println("__________________________________");
        // method for Displaying the output for Population level in Region
        displayFormatforPopulationReports(getPopulationLevelInRegion());
        System.out.println("");
    }

    //25# (Population of people, people living in cities, and people not living in cities in each Country)
    public ArrayList<PopulationLevel> getPopulationLevelInCountry() throws SQLException
    {
        try {
            ArrayList<PopulationLevel> resultList= new ArrayList<>();
            //SQL statement creating
            Statement stmt = con.createStatement();
            //Command building
            String command = "SELECT \n" +
                    "    country.Name AS Name, \n" +
                    "    country.Population AS TotalPopulation, \n" +
                    "    SUM(city.Population) AS PopulationInCities, \n" +
                    "    (SUM(city.Population) / country.Population * 100) AS PercentPopulationInCities, \n" +
                    "    country.Population - SUM(city.Population) AS PopulationNotInCities, \n" +
                    "    ((country.Population - SUM(city.Population)) / country.Population * 100) AS PercentPopulationNotInCities \n" +
                    "FROM \n" +
                    "    country \n" +
                    "LEFT JOIN \n" +
                    "    city ON country.Code = city.CountryCode \n" + // two table joining
                    "GROUP BY \n" +
                    "    country.Code;"; // As the country code
            // Execute the Command
            ResultSet rset = stmt.executeQuery(command);

            while(rset.next())
            {
                resultList.add(new PopulationLevel(
                        rset.getString("Name"),
                        rset.getLong("TotalPopulation"),
                        rset.getLong("PopulationInCities"),
                        rset.getDouble("PercentPopulationInCities"),
                        rset.getLong("PopulationNotInCities"),
                        rset.getDouble("PercentPopulationNotInCities")
                ));
            }
            return resultList;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void displayPopulationLevelInCountry() throws SQLException {
        // Displaying the output for Population level in Country
        System.out.println("Population Level Report of Country");
        System.out.println("__________________________________");
        // method for Displaying the output for Population level in Country
        displayFormatforPopulationReports(getPopulationLevelInCountry());
        System.out.println("");
    }


}

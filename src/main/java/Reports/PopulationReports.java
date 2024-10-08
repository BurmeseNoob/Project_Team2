package Reports;
import java.sql.*;
import java.util.ArrayList;
import ProjectTeam2.City;
import ProjectTeam2.PopulationLevel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PopulationReports {

    private Connection con;

    public PopulationReports(Connection con) {
        this.con = con;
    }

    // Initiate the PopulationReports Display format
    public void displayFormatforPopulationReports(ArrayList<PopulationLevel> pops)
    {
        ArrayList<PopulationLevel> poplists = pops;
        //Table header format
        System.out.println("+-------------------+----------------------+----------------------+------------+------------------------------+-----------------+");
        System.out.printf("| %-17s | %-20s | %-20s | %-10s | %-27s | %-10s | %n",
                "Name", "Total Population", "Population In Cities", "% in Cities", "Population Not in Cities", "% Not in Cities"
        );
        System.out.println("+-------------------+----------------------+----------------------+------------+------------------------------+-----------------+");


        for(PopulationLevel pop : poplists)
        {
            System.out.printf("| %-17s | %,20d | %,20d |     %.2f%%   | %,27d |     %.2f%%      | %n",
                    pop.getName(),pop.getTotalPopulation(),pop.getPopulationInCities(),pop.getPercentagesInCities(),pop.getPopulationNotInCities(),pop.getPercentagesNotInCities());
        }
        System.out.println("+-------------------+----------------------+----------------------+------------+------------------------------+------------------+");
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

    //25# (Population of people, people living in cities, and people not living in cities in each Country)

}

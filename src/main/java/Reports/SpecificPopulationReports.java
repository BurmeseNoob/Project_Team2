package Reports;
import ProjectTeam2.SpecificPopulation;

import java.sql.*;
import java.util.ArrayList;

public class SpecificPopulationReports {
    private Connection con;

    // Connection from SQL
    public SpecificPopulationReports(Connection con) {
        this.con = con;
    }

    //Initiate the Report table format
    public void DisplayReportFormat(ArrayList<SpecificPopulation> sp) throws SQLException {
        ArrayList<SpecificPopulation> spList = sp;

        // Table Header Format
        System.out.println("+_____________________________________________+___________________________________+");
        System.out.printf("| %-43s | %-33s | %n", " Name ", " Population ");
        System.out.println("+_____________________________________________+____________________________________");
        for (SpecificPopulation population: spList) {
            System.out.printf("| %-43s | %,33d | %n", population.getName(), population.getPopulation());
        }
        System.out.println("+_____________________________________________+___________________________________+");
    }

    //26 # World Population Report
    public ArrayList<SpecificPopulation> getWorldPopulation()
    {
        try
        {
            ArrayList<SpecificPopulation> resultList= new ArrayList<>();
            //SQL statement creating
            Statement stmt = con.createStatement();
            //Command building
            String command = "SELECT SUM(Population) AS WorldPopulation FROM country";
            // Execute the Command
            ResultSet rset = stmt.executeQuery(command);

            if(rset.next())
            {
                resultList.add(
                        new SpecificPopulation("World",rset.getLong("WorldPopulation"))
                );
            }

            return resultList;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void displayWorldPopulation() throws SQLException {
        //Table heading
        System.out.println("");
        System.out.println("World Population");
        System.out.println("___________________");

        DisplayReportFormat(getWorldPopulation());
    }

    //27 # Continent Population Report
    public ArrayList<SpecificPopulation> getContinentPopulation()
    {
        try
        {
            ArrayList<SpecificPopulation> resultList= new ArrayList<>();
            //SQL statement creating
            Statement stmt = con.createStatement();
            //Command building
            String command = "SELECT Continent, SUM(Population) AS ContinentPopulation FROM country GROUP BY Continent";

            // Execute the Command
            ResultSet rset = stmt.executeQuery(command);

            while(rset.next())
            {
                resultList.add(
                        new SpecificPopulation(rset.getString("Continent"),rset.getLong("ContinentPopulation"))
                );
            }

            return resultList;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void displayContinentPopulation() throws SQLException {
        //Table heading
        System.out.println("");
        System.out.println("Continent Population");
        System.out.println("___________________");

        DisplayReportFormat(getContinentPopulation());
    }

    //28 # Region Population Report
    public ArrayList<SpecificPopulation> getRegionPopulation()
    {
        try
        {
            ArrayList<SpecificPopulation> resultList= new ArrayList<>();
            //SQL statement creating
            Statement stmt = con.createStatement();
            //Command building
            String command = "SELECT Region, SUM(Population) AS RegionPopulation FROM country GROUP BY Region";

            // Execute the Command
            ResultSet rset = stmt.executeQuery(command);

            while(rset.next())
            {
                resultList.add(
                        new SpecificPopulation(rset.getString("Region"),rset.getLong("RegionPopulation"))
                );
            }

            return resultList;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void displayRegionPopulation() throws SQLException {
        //Table heading
        System.out.println("");
        System.out.println("Region Population");
        System.out.println("___________________");

        //display the method of region population result
        DisplayReportFormat(getRegionPopulation());
    }


    //29 # Country Population Report
    public ArrayList<SpecificPopulation> getCountryPopulation()
    {
        try
        {
            ArrayList<SpecificPopulation> resultList= new ArrayList<>();
            //SQL statement creating
            Statement stmt = con.createStatement();
            //Command building
            String command = "SELECT Name, Population AS CountryPopulation FROM country";

            // Execute the Command
            ResultSet rset = stmt.executeQuery(command);

            while(rset.next())
            {
                resultList.add(
                        new SpecificPopulation(rset.getString("Name"),rset.getLong("CountryPopulation"))
                );
            }

            return resultList;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void displayCountryPopulation() throws SQLException {
        //Table heading
        System.out.println("");
        System.out.println("Country Population");
        System.out.println("___________________");

        //display the method of country population result
        DisplayReportFormat(getCountryPopulation());
    }


    //30 # District Population Report
    public ArrayList<SpecificPopulation> getDistrictPopulation()
    {
        try
        {
            ArrayList<SpecificPopulation> resultList= new ArrayList<>();
            //SQL statement creating
            Statement stmt = con.createStatement();
            //Command building
            String command = "SELECT District, SUM(Population) AS DistrictPopulation FROM city GROUP BY District";

            // Execute the Command
            ResultSet rset = stmt.executeQuery(command);

            while(rset.next())
            {
                resultList.add(
                        new SpecificPopulation(rset.getString("District"),rset.getLong("DistrictPopulation"))
                );
            }

            return resultList;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }


    public void displayDistrictPopulation() throws SQLException {
        //Table heading
        System.out.println("");
        System.out.println("District Population");
        System.out.println("___________________");

        //display the method of district population result
        DisplayReportFormat(getDistrictPopulation());
    }

    //31 # City Population Report
    public ArrayList<SpecificPopulation> getCityPopulation()
    {
        try
        {
            ArrayList<SpecificPopulation> resultList= new ArrayList<>();
            //SQL statement creating
            Statement stmt = con.createStatement();
            //Command building
            String command = "SELECT Name, Population AS CityPopulation FROM city";

            // Execute the Command
            ResultSet rset = stmt.executeQuery(command);

            while(rset.next())
            {
                resultList.add(
                        new SpecificPopulation(rset.getString("Name"),rset.getLong("CityPopulation"))
                );
            }

            return resultList;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }


    public void displayCityPopulation() throws SQLException {
        //Table heading
        System.out.println("");
        System.out.println("City Population");
        System.out.println("___________________");

        //display the method of city population result
        DisplayReportFormat(getCityPopulation());
    }






}

package Reports;
import java.sql.*;
import java.util.ArrayList;
import ProjectTeam2.City;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CityReports {

    private Connection con;

    public CityReports(Connection con) {
        this.con = con;
    }

    /// Initiate the format of the CityReport
    public void cityReportFormat(ArrayList<City> cities) throws SQLException {
        ArrayList<City> citiesList = cities;

        for (City city : citiesList) {
            try {
                String getCC = city.getCountryCode();
                Statement stmt = con.createStatement();
                //Query to get the Country NAME
                String query = "SELECT country.Name FROM country " +
                        "JOIN city ON country.Code = city.CountryCode " +
                        "WHERE city.CountryCode = '" + getCC + "'";

                ResultSet rset = stmt.executeQuery(query);
                if (rset.next()) {
                    String countryName = rset.getString("Name");
                    System.out.printf("City Name: %-20s Country: %-35s District: %-25s Population: %-18d%n",
                            city.getName(), countryName, city.getDistrict(), city.getPopulation());

                }
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //#7 (Cities in the world organised by largest population to smallest.)
    public void displayThePopulationOfCity() throws SQLException {
        System.out.println("");
        System.out.println("Population of cities order by descending are: ");
        System.out.println("________________________________________________________________");

        ArrayList<City> resultList = getPopulationOftheCitybyDescendingOrder();
        cityReportFormat(resultList);
    }

    public ArrayList<City> getPopulationOftheCitybyDescendingOrder() {
        ArrayList<City> resultList = new ArrayList<>();
        try {
            // Prepare the SQL statement
            Statement stmt = con.createStatement();
            // SQL Command to retrieve
            String strSelect = "SELECT *" +
                    "FROM city " +
                    "ORDER BY Population DESC;";
            // Execute the Command
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next()) {
                City cy = new City(
                        rset.getString("Name"),
                        rset.getString("CountryCode"),
                        rset.getString("District"),
                        rset.getInt("Population")
                );
                resultList.add(cy);
            }
            return  resultList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //#8 (the cities in a continent organised by largest population to smallest)
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

    public void getPopulationOftheCitybyContinent() throws SQLException {
        //1 country codd 2 join 3 continent

       try
       {
           Statement stmt = con.createStatement();

           ArrayList<String> continents = getDistinctContinent();

           for(String continent : continents)
           {
               ArrayList<City> resultList = new ArrayList<>();
               System.out.println("");
               System.out.println("Population of cities order by descending according to Continent : " + continent);
               System.out.println("________________________________________________________________");

               String cmd = "SELECT \n" +
                       "    city.Name AS CityName, \n" +
                       "    city.District, \n" +
                       "    city.Population, \n" +
                       "    country.Name AS CountryName, \n" +
                       "    country.Code AS CountryCode, \n" +
                       "    country.Continent\n" +
                       "FROM \n" +
                       "    city\n" +
                       "JOIN \n" +
                       "    country ON city.CountryCode = country.Code\n" +
                       "WHERE \n" +
                       "    country.Continent = '" + continent + "' " +
                       "ORDER BY \n" +
                       "    city.Population DESC;\n";


               ResultSet rset = stmt.executeQuery(cmd);

               while(rset.next())
               {
                   City cy = new City(
                       rset.getString("CityName"),
                       rset.getString("CountryCode"),
                       rset.getString("District"),
                       rset.getInt("Population")
                   );

                   resultList.add(cy);
               }

               cityReportFormat(resultList);
                System.out.println("");
           }
       }
       catch (SQLException e) {
           throw new RuntimeException(e);
       }
    }

    //#9 (the cities in a region organised by largest population to smallest)
    public ArrayList<String> getDistinctRegion() throws SQLException
    {
        // get the ditinctRegion to iterate for population
        ArrayList<String> regionList = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            //Iterating about distinct Region in the country table
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

    public void getPopulationOftheCitybyRegion() throws SQLException {
        //1 country codd 2 join 3 region
        try
        {
            Statement stmt = con.createStatement();

            ArrayList<String> regions = getDistinctRegion();

            for(String region: regions)
            {
                ArrayList<City> resultList = new ArrayList<>();
                System.out.println("");
                System.out.println("Population of cities order by descending according to Region : " + region);
                System.out.println("________________________________________________________________");

                String cmd = "SELECT \n" +
                        "    city.Name AS CityName, \n" +
                        "    city.District, \n" +
                        "    city.Population, \n" +
                        "    country.Name AS CountryName, \n" +
                        "    country.Code AS CountryCode, \n" +
                        "    country.Continent\n" +
                        "FROM \n" +
                        "    city\n" +
                        "JOIN \n" +
                        "    country ON city.CountryCode = country.Code\n" +
                        "WHERE \n" +
                        "    country.Region = '" + region + "' " +
                        "ORDER BY \n" +
                        "    city.Population DESC;\n";


                ResultSet rset = stmt.executeQuery(cmd);

                while(rset.next())
                {
                    City cy = new City(
                            rset.getString("CityName"),
                            rset.getString("CountryCode"),
                            rset.getString("District"),
                            rset.getInt("Population")
                    );

                    resultList.add(cy);
                }

                cityReportFormat(resultList);
                System.out.println("");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //#10 (the cities in a Country organised by largest population to smallest)
    public ArrayList<String> getDistinctCountry()
    {
        ArrayList<String> countryList = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            //Iterating about distinct country name in the country table
            String strSelect = "SELECT DISTINCT Name FROM country";
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next()) {
                countryList.add(rset.getString("Name"));
            }

            return countryList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void getPopulationOftheCitybyCountry() throws SQLException {
        try {
            Statement stmt = con.createStatement();
            ArrayList<String> countryList = getDistinctCountry();

            for (String country : countryList) {
                ArrayList<City> resultList = new ArrayList<>();
                System.out.println("");
                System.out.println("Population of cities order by descending according to CountryName : " + country);
                System.out.println("________________________________________________________________");

                String cmd = "SELECT \n" +
                        "    city.Name AS CityName, \n" +
                        "    city.District, \n" +
                        "    city.Population, \n" +
                        "    country.Name AS CountryName, \n" +
                        "    country.Code AS CountryCode, \n" +
                        "    country.Continent\n" +
                        "FROM \n" +
                        "    city\n" +
                        "JOIN \n" +
                        "    country ON city.CountryCode = country.Code\n" +
                        "WHERE \n" +
                        "    country.Name = '" + country + "' " +
                        "ORDER BY \n" +
                        "    city.Population DESC;\n";

                ResultSet rset = stmt.executeQuery(cmd);

                while(rset.next())
                {
                    City cy = new City(
                            rset.getString("CityName"),
                            rset.getString("CountryCode"),
                            rset.getString("District"),
                            rset.getInt("Population")
                    );

                    resultList.add(cy);
                }

                cityReportFormat(resultList);
                System.out.println("");

            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //#11 (cities in a district organised by largest population to smallest.)
    public ArrayList<String> getDistinctDistrict() throws SQLException {
        ArrayList<String> districtList = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            //Iterating about distinct country name in the country table
            String strSelect = "SELECT DISTINCT District FROM city";
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next()) {
                districtList.add(rset.getString("District"));
            }

            return districtList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void getPopulationOfthecitybyDistrict() throws SQLException {
        try {
            Statement stmt = con.createStatement();
            ArrayList<String> districtList = getDistinctDistrict();
            for (String district : districtList) {
                ArrayList<City> resultList = new ArrayList<>();

                System.out.println("");
                System.out.println("Population of cities order by descending according to City's District : " + district);
                System.out.println("________________________________________________________________");

                String cmd = "SELECT \n" +
                        "    city.Name AS CityName, \n" +
                        "    city.District, \n" +
                        "    city.Population, \n" +
                        "    country.Name AS CountryName, \n" +
                        "    country.Code AS CountryCode, \n" +
                        "    country.Continent\n" +
                        "FROM \n" +
                        "    city\n" +
                        "JOIN \n" +
                        "    country ON city.CountryCode = country.Code\n" +
                        "WHERE \n" +
                        "    city.District = '" + district+ "' " +
                        "ORDER BY \n" +
                        "    city.Population DESC;\n";

                ResultSet rset = stmt.executeQuery(cmd);

                while(rset.next())
                {
                    City cy = new City(
                            rset.getString("CityName"),
                            rset.getString("CountryCode"),
                            rset.getString("District"),
                            rset.getInt("Population")
                    );

                    resultList.add(cy);
                }

                cityReportFormat(resultList);
                System.out.println("");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

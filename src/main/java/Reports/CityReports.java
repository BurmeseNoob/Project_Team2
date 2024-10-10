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

    // Initiate the format of the CityReport |
    public void cityReportFormat(ArrayList<City> cities) throws SQLException {
        ArrayList<City> citiesList = cities;

        if(citiesList == null || citiesList.size() == 0) {
            System.out.println("No cities found");
            return;
        }
        // Table Header Format
        System.out.println("+----------------------+-----------------------------------+-------------------------+--------+");
        System.out.printf("| %-25s | %-30s | %-20s | %-18s |%n",
                "City Name", "Country", "District", "Population");
        System.out.println("+----------------------+-----------------------------------+-------------------------+--------+");
        for (City city : citiesList) {
            if(city == null)
            {
                continue;
            }
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
                    System.out.printf("| %-25s | %-30s | %-20s | %,18d |%n",
                            city.getName(), countryName, city.getDistrict(), city.getPopulation());

                }
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("+----------------------+-----------------------------------+-------------------------+--------+");

    }

    // Initiate the format of the CapitalCity Report
    public void capitalCityReportFormat(ArrayList<City> cities) throws SQLException {
        ArrayList<City> citiesList = cities;

        if(citiesList == null || citiesList.size() == 0) {
            System.out.println("No cities found");
            return;
        }

        // Table Header Format
        System.out.println("+----------------------+-----------------------------------+--------+");
        System.out.printf("| %-25s | %-30s | %-18s |%n",
                "City Name", "Country", "Population");
        System.out.println("+----------------------+-----------------------------------+--------+");

        for (City city : citiesList) {
            if(city == null)
            {
                continue;
            }
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
                    System.out.printf("| %-25s | %-30s | %,18d |%n",
                            city.getName(), countryName, city.getPopulation());
                }
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("+----------------------+-----------------------------------+-------------------------+--------+");
    }

    //#7 (Cities in the world organised by largest population to smallest.)
    public void displayThePopulationOfCity(ArrayList<City> cities) throws SQLException {
        System.out.println("");
        System.out.println("Population of cities order by descending are: ");
        System.out.println("________________________________________________________________");

        ArrayList<City> resultList = cities;
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
                //add to the list to the object of city according to result
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

    public void getPopulationOftheCitybyContinent(ArrayList<String> DConts) throws SQLException {
        //1 country codd 2 join 3 continent
       try
       {
           Statement stmt = con.createStatement();

           ArrayList<String> continents = DConts;

           if(continents == null || continents.size() == 0) {
               System.out.println("No distinct found");
               return;
           }

           for(String continent : continents)
           {
               if(continent == null)
                   continue;
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
                   //add to the list to the object of city according to result
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

    public void getPopulationOftheCitybyRegion(ArrayList<String> DRegion) throws SQLException {
        //1 country codd 2 join 3 region
        try
        {
            Statement stmt = con.createStatement();

            ArrayList<String> regions = DRegion;
            if(regions == null || regions.size() == 0) {
                System.out.println("No Region found");
                return;
            }

            for(String region: regions)
            {
                if(region == null)
                    continue;
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
                    //add to the list to the object of city according to result
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

    public void getPopulationOftheCitybyCountry(ArrayList<String> DCountry) throws SQLException {
        try {
            Statement stmt = con.createStatement();
            ArrayList<String> countryList = DCountry;
            if(countryList == null || countryList.size() == 0) {
                System.out.println("No Country found");
                return;
            }

            for (String country : countryList) {
                if(country == null)
                    continue;
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
                    //add to the list to the object of city according to result
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


    public void getPopulationOfthecitybyDistrict(ArrayList<String> DDistrict) throws SQLException {
        try {
            Statement stmt = con.createStatement();
            ArrayList<String> districtList = DDistrict;
            if(districtList == null || districtList.size() == 0) {
                System.out.println("No District found");
                return;
            }
            for (String district : districtList) {
                if(district == null)
                    continue;
                ArrayList<City> resultList = new ArrayList<>();

                System.out.println("");
                System.out.println("Population of cities order by descending according to City's District : " + district);
                System.out.println("__________________________________________________________________________________________");

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
                    //add to the list to the object of city according to result
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


    //#12 (top N populated cities in the world where N is provided by the user)
    // method will accept value as the parameter
    public ArrayList<City> getPopulationOfthecity(int N) throws SQLException {

        // Declaring the city arraylist to return
        ArrayList<City> cityList = new ArrayList<>();
        if(N <= 0)
        {
            System.out.println("No Query Available");
            return null;
        }
        try {
            // command about querying the descending order of the city population and limit by user input (N)
            String command = "SELECT * FROM city ORDER BY Population DESC LIMIT ?";
            PreparedStatement  pstmt = con.prepareStatement(command);
            pstmt.setInt(1,N);

            //Execute the prepared command statement
            ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {
                //add to the list to the object of city according to result
                City cy = new City(
                        rset.getString("Name"),
                        rset.getString("CountryCode"),
                        rset.getString("District"),
                        rset.getInt("Population")
                );

                //add each city object to the arraylist
                cityList.add(cy);

            }
            //Returning the list to display the data
            return cityList;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void displayingOutputOfTheCityPopulationTopValueByN(ArrayList<City> cities,int N) throws SQLException {

        //Displaying Header
        System.out.println("");
        System.out.println("Top " + N + " populated Cities ! ");
        System.out.println("_______________________________________________________________________");

        ArrayList<City> cityList = cities;
        if(cityList == null || cityList.size() == 0) {
            System.out.println("No City found");
            return;
        }
        //Display the Format and get teh data
        cityReportFormat(cityList);
        System.out.println("");
    }

    //13# (top N populated cities in a continent where N is provided by the user.)
    // can use the getting distinct continent method from report #8
    //method to retrieve and displaying the population of the city by a continent
    public void getPopulationOftheCityByContinentTopN(int N) throws SQLException {
        // Validate N
        if (N <= 0) {
            System.out.println("No Query Available");
            return;
        }

        ArrayList<String> continents = getDistinctContinent();

        // Query and display results for each continent
        for (String continent : continents) {
            ArrayList<City> cityList = new ArrayList<>();

            System.out.println("");
            System.out.println("Population of cities according to Continent: " + continent);
            System.out.println("_______________________________________________________________________________");

            String command = "SELECT city.Name AS CityName, city.District, city.Population, " +
                    "country.Name AS CountryName, country.Code AS CountryCode, country.Continent " +
                    "FROM city JOIN country ON city.CountryCode = country.Code " +
                    "WHERE country.Continent = ? " +
                    "ORDER BY city.Population DESC LIMIT ?;";

            try (PreparedStatement pstmt = con.prepareStatement(command)) {
                pstmt.setString(1, continent);
                pstmt.setInt(2, N);

                ResultSet rset = pstmt.executeQuery();

                while (rset.next()) {
                    cityList.add(new City(
                            rset.getString("CityName"),
                            rset.getString("CountryCode"),
                            rset.getString("District"),
                            rset.getInt("Population")
                    ));
                }
            }

            // Display the report for the current continent
            cityReportFormat(cityList);
        }
    }


    //14# (top N populated cities in a region where N is provided by the user.)
    // can use the getting distinct region method from report #9
    // method structure is same like method 13
    //method to retrieve and displaying the population of the city by a region.
    public void getPopulationOftheCityByRegionTopN(int N) throws SQLException {
        // Check for a valid N value
        if (N <= 0) {
            System.out.println("No Query Available");
            return;
        }

        // Get distinct regions from a helper method
        ArrayList<String> regions = getDistinctRegion();

        // Query and display results for each region
        for (String region : regions) {
            ArrayList<City> cityList = new ArrayList<>();

            System.out.println("");
            System.out.println("Population of Top " + N + " cities according to Region: " + region);
            System.out.println("_______________________________________________________________________________");

            String command = "SELECT city.Name AS CityName, city.District, city.Population, " +
                    "country.Name AS CountryName, country.Code AS CountryCode, country.Region " +
                    "FROM city JOIN country ON city.CountryCode = country.Code " +
                    "WHERE country.Region = ? " +
                    "ORDER BY city.Population DESC LIMIT ?;";

            try (PreparedStatement pstmt = con.prepareStatement(command)) {
                pstmt.setString(1, region);
                pstmt.setInt(2, N);

                ResultSet rset = pstmt.executeQuery();

                while (rset.next()) {
                    cityList.add(new City(
                            rset.getString("CityName"),
                            rset.getString("CountryCode"),
                            rset.getString("District"),
                            rset.getInt("Population")
                    ));
                }
            }

            // Display the report for the current region
            cityReportFormat(cityList);
        }
    }


    //15# (top N populated cities by Country Where N is provided by the  user.)
    // can use the getting distinct country method from report #10
    //method to retrieve and displaying the population of the city by a country.
    public void getPopulationOftheCityByCountryTopN(int N) throws SQLException {
        if (N <= 0) {
            System.out.println("No Query Available");
            return;
        }

        // Get distinct countries
        ArrayList<String> countries = getDistinctCountry();

        // Query and display results for each country
        for (String country : countries) {
            ArrayList<City> cityList = new ArrayList<>();

            System.out.println("");
            System.out.println("Population of Top " + N + " cities according to Country: " + country);
            System.out.println("_____________________________________________________________________________________");

            String command = "SELECT city.Name AS CityName, city.District, city.Population, " +
                    "country.Name AS CountryName, country.Code AS CountryCode, country.Continent " +
                    "FROM city JOIN country ON city.CountryCode = country.Code " +
                    "WHERE country.Name = ? " +
                    "ORDER BY city.Population DESC LIMIT ?;";

            try (PreparedStatement pstmt = con.prepareStatement(command)) {
                pstmt.setString(1, country);
                pstmt.setInt(2, N);

                ResultSet rset = pstmt.executeQuery();

                while (rset.next()) {
                    cityList.add(new City(
                            rset.getString("CityName"),
                            rset.getString("CountryCode"),
                            rset.getString("District"),
                            rset.getInt("Population")
                    ));
                }
            }

            // Display the report for the current country
            cityReportFormat(cityList);
        }
    }


    //16# (top N populated cities in a district where N is provided by the user)
    // can use the getting distinct district method from report #11
    //method to retrieve and displaying the population of the city by a district
    public void getPopulationOftheCityByDistrictTopN(int N) throws SQLException {
        if (N <= 0) {
            System.out.println("No Query Available");
            return;
        }

        // Get distinct districts
        ArrayList<String> districts = getDistinctDistrict();

        // Query and display results for each district
        for (String district : districts) {
            ArrayList<City> cityList = new ArrayList<>();

            System.out.println("");
            System.out.println("Population of Top " + N + " cities according to District: " + district);
            System.out.println("_____________________________________________________________________________________");

            String command = "SELECT city.Name AS CityName, city.District, city.Population, " +
                    "country.Name AS CountryName, country.Code AS CountryCode, country.Continent " +
                    "FROM city JOIN country ON city.CountryCode = country.Code " +
                    "WHERE city.District = ? " +
                    "ORDER BY city.Population DESC LIMIT ?;";

            try (PreparedStatement pstmt = con.prepareStatement(command)) {
                pstmt.setString(1, district);
                pstmt.setInt(2, N);

                ResultSet rset = pstmt.executeQuery();

                while (rset.next()) {
                    cityList.add(new City(
                            rset.getString("CityName"),
                            rset.getString("CountryCode"),
                            rset.getString("District"),
                            rset.getInt("Population")
                    ));
                }
            }

            // Display the report for the current district
            cityReportFormat(cityList);
        }
    }




    // Capital city Format

    //17# (capital cities in the world organised by largest population to smallest)
    public ArrayList<City> getPopulationOftheCapitalCities()throws SQLException
    {

        ArrayList<City> cityList = new ArrayList<>();
        // Prepare the SQL statement
        Statement stmt = con.createStatement();
        // SQL Command to retrieve
        String command = "SELECT \n" +
                "    city.Name AS CityName, \n" +
                "    city.District, \n" +
                "    city.Population, \n" +
                "    country.Name AS CountryName, \n" +
                "    country.Code AS CountryCode, \n" +
                "    country.Continent\n" +
                "FROM \n" +
                "    country country \n" +  // Use the 'Country' table here
                "JOIN \n" +
                "    city city ON country.Capital = city.ID \n" +  // Join on the Capital and ID
                "ORDER BY \n" +
                "    city.Population DESC;\n";

        // Execute the Command
        ResultSet rset = stmt.executeQuery(command);

        while (rset.next()) {
            //add to the list to the object of city according to result
            cityList.add(new City(
                    rset.getString("CityName"),
                    rset.getString("CountryCode"),
                    rset.getString("District"),
                    rset.getInt("Population")
            ));
        }

        return cityList;
    }



    public void displayPopulationOftheCapitalCity(ArrayList<City> cityList) throws SQLException {
        ArrayList<City> result = cityList;
        if(result==null || result.size()==0)
        {
            System.out.println("No City Found");
            return;
        }

        //Table header
        System.out.println("");
        System.out.println("Population of Capital Cities in the World");
        System.out.println("________________________________________");


        //Display the result as the table format
        capitalCityReportFormat(result);
    }





    //18# (Capital cities in a continent organised by largest population to smallest)
    public void getPopulationOftheCapitalCitiesInContinent()throws SQLException
    {
        //Get the distinct continent
        ArrayList<String> continents = getDistinctContinent();
        //loop each continet and display the population of the capital cities
        for(String continent : continents)
        {
            //Table header format
            System.out.println("");
            System.out.println("Population of Capital Cities in a Continent : " + continent);
            System.out.println("__________________________________________________");

            ArrayList<City> cityList = new ArrayList<>();
            Statement stmt = con.createStatement();
            // SQL Command to retrieve
            String command = "SELECT \n" +
                    "    city.Name AS CityName, \n" +
                    "    city.District, \n" +
                    "    city.Population, \n" +
                    "    country.Name AS CountryName, \n" +
                    "    country.Code AS CountryCode, \n" +
                    "    country.Continent\n" +
                    "FROM \n" +
                    "    country country \n" +
                    "JOIN \n" +
                    "    city city ON country.Capital = city.ID \n" + // join with capital number and city id
                    "WHERE \n" +
                    "    country.Continent = '" + continent + "' " +  // query with each continent
                    "ORDER BY \n" +
                    "    city.Population DESC;\n";

            // Execute the Command
            ResultSet rset = stmt.executeQuery(command);

            while (rset.next()) {
                //add to the list to the object of city according to result
                cityList.add(new City(
                        rset.getString("CityName"),
                        rset.getString("CountryCode"),
                        rset.getString("District"),
                        rset.getInt("Population")
                ));
            }
            //display the city object
            cityReportFormat(cityList);

        }

    }


    //19# (the capital cities in a region organised by largest to smallest)
    public void getCapitalCityInRegion() throws SQLException
    {
        //Get the distinct region
        ArrayList<String> regions = getDistinctRegion();

        //loop each region and display the population of the capital cities
        for(String region : regions)
        {
            //Table header format
            System.out.println("");
            System.out.println("Population of Capital Cities in a Region : " + region);
            System.out.println("__________________________________________________");

            ArrayList<City> cityList = new ArrayList<>();
            Statement stmt = con.createStatement();
            // SQL Command to retrieve
            String command = "SELECT \n" +
                    "    city.Name AS CityName, \n" +
                    "    city.District, \n" +
                    "    city.Population, \n" +
                    "    country.Name AS CountryName, \n" +
                    "    country.Code AS CountryCode, \n" +
                    "    country.Continent\n" +
                    "FROM \n" +
                    "    country country \n" +
                    "JOIN \n" +
                    "    city city ON country.Capital = city.ID \n" + // join with capital number and city id
                    "WHERE \n" +
                    "    country.Region = '" + region + "' " +  // query with each region
                    "ORDER BY \n" +
                    "    city.Population DESC;\n";

            // Execute the Command
            ResultSet rset = stmt.executeQuery(command);

            while (rset.next()) {
                //add to the list to the object of city according to result
                cityList.add(new City(
                        rset.getString("CityName"),
                        rset.getString("CountryCode"),
                        rset.getString("District"),
                        rset.getInt("Population")
                ));
            }
            //display the city object
            cityReportFormat(cityList);

        }
    }

    //20# (top N populated capital cities in the world where N is provided by the user)
    public void getTopPopulatedCapitalCities(int N) throws SQLException {
        //Table header format
        System.out.println("");
        System.out.println("Population of Capital Cities in the World Top " + N + " values." );
        System.out.println("___________________________________________");

        try{
            ArrayList<City> cityList = new ArrayList<>();
            // Prepare the SQL statement
            Statement stmt = con.createStatement();
            // SQL Command to retrieve
            String command = "SELECT \n" +
                    "    city.Name AS CityName, \n" +
                    "    city.District, \n" +
                    "    city.Population, \n" +
                    "    country.Name AS CountryName, \n" +
                    "    country.Code AS CountryCode, \n" +
                    "    country.Continent\n" +
                    "FROM \n" +
                    "    country country \n" +
                    "JOIN \n" +
                    "    city city ON country.Capital = city.ID \n" +  // Join on the Capital and ID
                    "ORDER BY \n" +
                    "    city.Population DESC LIMIT ?;\n"; // limit as the N value

            //preare the command
            PreparedStatement  pstmt = con.prepareStatement(command);
            //match with parameter
            pstmt.setInt(1,N);

            ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {
                //add to the list to the object of city according to result
                cityList.add(new City(
                        rset.getString("CityName"),
                        rset.getString("CountryCode"),
                        rset.getString("District"),
                        rset.getInt("Population")
                ));
            }

            cityReportFormat(cityList);
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //21# (top N populated capital cities in a continent where N is provided by the user)
    public void getTopPopulatedCapitalCitiesInContinent(int N) throws SQLException
    {
        //Get distinct continents
        ArrayList<String> continents = getDistinctContinent();

        //loop each continent
        for(String continent : continents)
        {
            ArrayList<City> cityList = new ArrayList<>();

            //table header format
            System.out.println("");
            System.out.println("Population of  Top " + N +  "  Capital cities  according to Continent : " + continent);
            System.out.println("_______________________________________________________________________________");



            String command = "SELECT \n" +
                    "    city.Name AS CityName, \n" +
                    "    city.District, \n" +
                    "    city.Population, \n" +
                    "    country.Name AS CountryName, \n" +
                    "    country.Code AS CountryCode, \n" +
                    "    country.Continent\n" +
                    "FROM \n" +
                    "    country\n" +
                    "JOIN \n" +
                    "    city ON country.Capital = city.ID \n" +  // Join on the Capital and ID
                    "WHERE \n" +
                    "    country.Continent = '" + continent + "' " +
                    "ORDER BY \n" +
                    "    city.Population DESC LIMIT ?;\n"; // limit value with N

            PreparedStatement  pstmt = con.prepareStatement(command);
            pstmt.setInt(1,N);

            ResultSet rset = pstmt.executeQuery();

            // Execute the command result
            while(rset.next())
            {
                //add to the list to the object of city according to result
                cityList.add(new City(
                        rset.getString("CityName"),
                        rset.getString("CountryCode"),
                        rset.getString("District"),
                        rset.getInt("Population")
                ));
            }

            // display through city list
            cityReportFormat(cityList);
        }

    }

    //22# (top N populated capital cities in a region where N is provided by the user)
    public void getTopPopulatedCapitalCitiesInRegion(int N) throws SQLException
    {
        //Get distinct regions
        ArrayList<String> regions = getDistinctRegion();

        //loop each continent
        for(String region: regions)
        {
            ArrayList<City> cityList = new ArrayList<>();

            //table header format
            System.out.println("");
            System.out.println("Population of  Top " + N +  "  Capital cities  according to Region : " + region);
            System.out.println("_______________________________________________________________________________");



            String command = "SELECT \n" +
                    "    city.Name AS CityName, \n" +
                    "    city.District, \n" +
                    "    city.Population, \n" +
                    "    country.Name AS CountryName, \n" +
                    "    country.Code AS CountryCode, \n" +
                    "    country.Continent\n" +
                    "FROM \n" +
                    "    country\n" +
                    "JOIN \n" +
                    "    city ON country.Capital = city.ID \n" +  // Join on the Capital and ID
                    "WHERE \n" +
                    "    country.Region = '" + region + "' " + // specify by region
                    "ORDER BY \n" +
                    "    city.Population DESC LIMIT ?;\n"; // limit value with N

            PreparedStatement  pstmt = con.prepareStatement(command);
            pstmt.setInt(1,N);

            ResultSet rset = pstmt.executeQuery();

            // Execute the command result
            while(rset.next())
            {
                //add to the list to the object of city according to result
                cityList.add(new City(
                        rset.getString("CityName"),
                        rset.getString("CountryCode"),
                        rset.getString("District"),
                        rset.getInt("Population")
                ));
            }
            // display through city list
            cityReportFormat(cityList);
        }
    }

}

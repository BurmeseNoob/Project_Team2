package Reports;
import java.sql.*;
import java.util.ArrayList;
import ProjectTeam2.City;
import ProjectTeam2.PopulationLevel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LanguagesReports {

    private Connection con;

    public LanguagesReports(Connection con) {
        this.con = con;
    }

    //method to display about the languages speaking largest numbers to smallest on world population
    public void displayWorldLanguagesSpeak() throws SQLException
    {
        try {
            //SQL statement creating
            Statement stmt = con.createStatement();
            //Command building (two table Country & CountryLanguage)
            String command = "SELECT \n" +
                    "    cl.Language AS Language, \n" +
                    "    SUM(c.Population * (cl.Percentage / 100)) AS NumberOfSpeakers, \n" + // getting about total number of speaker languages
                    "    (SUM(c.Population * (cl.Percentage / 100)) / (SELECT SUM(Population) FROM country) * 100) AS PercentageOfWorldPopulation\n" + // World population percentage
                    "FROM \n" +
                    "    countrylanguage cl\n" +
                    "JOIN \n" +
                    "    country c ON cl.CountryCode = c.Code\n" +
                    "WHERE \n" +
                    "    cl.Language IN ('Chinese','English', 'Hindi', 'Spanish', 'Arabic')\n" +
                    "GROUP BY \n" +
                    "    cl.Language\n" +
                    "ORDER BY \n" +
                    "    NumberOfSpeakers DESC;\n"; // (Largest number to smallest speaker)

            int count = 1;
            ResultSet rs = stmt.executeQuery(command);
            System.out.println("Total Languages Speaking Report");


            //table header
            System.out.println("-------------------------------------------------------------------");
            System.out.printf("| %-2s | %-10s| %-15s| %30s| %n",
                    "No","Language","TotalSpeak","WorldPopulationPercentage");
            System.out.println("-------------------------------------------------------------------");
            while (rs.next()) {
                System.out.printf("| %-2s | %-10s| %,15d|                        %.2f%% | %n",
                                  count++,rs.getString("Language"),rs.getLong("NumberOfSpeakers"),rs.getDouble("PercentageOfWorldPopulation"));

            }
            System.out.println("-------------------------------------------------------------------");



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

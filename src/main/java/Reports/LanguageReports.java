package Reports;
import ProjectTeam2.LanguageData;

import java.sql.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LanguageReports {

    private Connection con;

    public LanguageReports(Connection con) {
        this.con = con;
    }

    //methods  to  get the languages and their speakers and population
    public ArrayList<LanguageData> getWorldLanguagesSpeak() throws SQLException {
        ArrayList<LanguageData> languageList = new ArrayList<>();

        try {
            Statement stmt = con.createStatement();
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

            ResultSet rs = stmt.executeQuery(command);

            while (rs.next()) {
                String language = rs.getString("Language");
                long numberOfSpeakers = rs.getLong("NumberOfSpeakers");
                double percentageOfWorldPopulation = rs.getDouble("PercentageOfWorldPopulation");

                languageList.add(new LanguageData(language, numberOfSpeakers, percentageOfWorldPopulation));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return languageList;
    }

    // 32 #method to display about the languages speaking largest numbers to smallest on world population
    public void displayWorldLanguagesSpeak(ArrayList<LanguageData> ld) throws SQLException
    {
        ArrayList<LanguageData> worldLanguages = ld;

        if(worldLanguages.size() == 0 || worldLanguages==null)
        {
            System.out.println("No world languages found");
            return;
        }
        else {
            int count = 1;
            System.out.println("Total Languages Speaking Report");

            //table header
            System.out.println("-------------------------------------------------------------------");
            System.out.printf("| %-2s | %-10s| %-15s| %30s| %n",
                    "No.", "Language", "TotalSpeak", "WorldPopulationPercentage");
            System.out.println("-------------------------------------------------------------------");
            for (LanguageData lang : worldLanguages) {
                if (lang == null)
                    continue;
                System.out.printf("| %-2s | %-10s | %,15d|                    %8.2f%% | %n",
                        count++, lang.getLanguage(), lang.getNumberOfSpeakers(), lang.getPercentageOfWorldPopulation());
            }

            System.out.println("-------------------------------------------------------------------");
        }
    }

}

package UnitTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ProjectTeam2.LanguageData;
import ProjectTeam2.SpecificPopulation;
import Reports.LanguageReports;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LanguageReportsTest {
    private LanguageReports languagesReports;
    private Connection mockConnection;
    private Statement mockStatement;
    private ResultSet mockResultSet;

    @BeforeEach
    //Inistiating the object (connection, reports object)
    public void setUp() throws SQLException {
        // Mock the necessary SQL objects
        mockConnection = mock(Connection.class);
        mockStatement = mock(Statement.class);
        mockResultSet = mock(ResultSet.class);

        // Connect the mock connection to the Language Reports Constructor.
        languagesReports = new LanguageReports(mockConnection);

        // Mock the behavior of creating a statement
        when(mockConnection.createStatement()).thenReturn(mockStatement);
    }

    @Test
    public void testGetWorldLanguagesSpeak() throws SQLException {
        // Prepare mock data for the ResultSet
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, true, false); // Two rows of data
        // Sample Mock data
        when(mockResultSet.getString("Language")).thenReturn("English", "Chinese");
        when(mockResultSet.getLong("NumberOfSpeakers")).thenReturn(1500000000L, 1200000000L);
        when(mockResultSet.getDouble("PercentageOfWorldPopulation")).thenReturn(19.4, 15.2);

        // Method  to test about get world speak languages
        ArrayList<LanguageData> result = languagesReports.getWorldLanguagesSpeak();

        // test the expected outcome with result
        assertEquals(2, result.size());

        //test the result out come1
        LanguageData englishData = result.get(0);
        assertEquals("English", englishData.getLanguage());
        assertEquals(1500000000L, englishData.getNumberOfSpeakers());
        assertEquals(19.4, englishData.getPercentageOfWorldPopulation(), 0.01);

        //test the result outcome2
        LanguageData chineseData = result.get(1);
        assertEquals("Chinese", chineseData.getLanguage());
        assertEquals(1200000000L, chineseData.getNumberOfSpeakers());
        assertEquals(15.2, chineseData.getPercentageOfWorldPopulation(), 0.01);
    }

    @Test
    public void testDisplayWorldLanguagesSpeak() throws SQLException
    {
        //creating the specific population arraylist to test
        ArrayList<LanguageData> languageList = new ArrayList<>();

        //Test wil null list
        languageList.add(null);
        languagesReports.displayWorldLanguagesSpeak(languageList);

        //Test with Data
        languageList.add(new LanguageData("Chinese",1200000000L, 15.2));
        languagesReports.displayWorldLanguagesSpeak(languageList);
    }

}

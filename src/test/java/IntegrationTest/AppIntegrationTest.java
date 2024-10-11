package IntegrationTest;

import DataBaseConnect.Connection;
import ProjectTeam2.*;
import Reports.CityReports;
import Reports.CountryReports;
import Reports.LanguageReports;
import Reports.PopulationReports;
import Reports.PopulationReports;
import Reports.SpecificPopulationReports;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AppIntegrationTest {

    static App app;

    @BeforeAll
    static void init() throws SQLException {
        app = new App();
        app.runApp(); // Running the app's logic to ensure everything initializes
    }@Test
    public void testGetLanguages() throws SQLException, ClassNotFoundException {
        // Step 1: Establish a connection and create the LanguageReports object
        Connection connection = new Connection();
        connection.connect("localhost:33060", 30000);
        LanguageReports lr = new LanguageReports(connection.getConnection());

        // Step 2: Retrieve the list of world languages and check it is not null or empty
        ArrayList<LanguageData> languagesSpeakList = lr.getWorldLanguagesSpeak();
        assertNotNull(languagesSpeakList, "Languages list should not be null.");
        assertFalse(languagesSpeakList.isEmpty(), "Languages list should not be empty.");

        // Step 3: Check for expected content in languagesSpeakList (assuming you know the expected output structure)
        for (LanguageData languageData : languagesSpeakList) {
            assertNotNull(languageData.getLanguage(), "Language name should not be null.");
        }

        // Step 4: Test the display function (if applicable)
        lr.displayWorldLanguagesSpeak(languagesSpeakList); // Check if this displays output as expected

        // Step 5: Close the connection after testing
        connection.disconnect();
    }


}
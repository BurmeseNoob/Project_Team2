package UnitTest;

import static org.junit.jupiter.api.Assertions.*;

import ProjectTeam2.City;
import ProjectTeam2.LanguageData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LanguageDataClassTest {

    // Declare a LanguageData object for testing
    private LanguageData languageData;

    @BeforeEach
    public void setUp() {
        // Initialize a LanguageData object before each test with sample values
        languageData = new LanguageData("Chinese", 1200000000, 15.2);
    }

    @Test
        // Test getter method for language name
    void testGetLanguage() {
        // Verify if getLanguage method returns the correct language name
        assertEquals("Chinese", languageData.getLanguage()); // test the result with expected outcome
    }

    @Test
        // Test getter method for the number of speakers
    void testGetNumbersOfSpeakers() {
        // Verify if getNumberOfSpeakers method returns the correct number of speakers
        assertEquals(1200000000, languageData.getNumberOfSpeakers()); // test the result with expected outcome
    }

    @Test
        // Test getter method for percentage of world population
    void testGetPercentageOfWorldPopulation() {
        // Verify if getPercentageOfWorldPopulation method returns the correct percentage
        assertEquals(15.2, languageData.getPercentageOfWorldPopulation()); // test the result with expected outcome
    }

}

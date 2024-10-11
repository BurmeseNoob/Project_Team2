package UnitTest;
import static org.junit.jupiter.api.Assertions.*;

import ProjectTeam2.City;
import ProjectTeam2.LanguageData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LanguageDataClassTest {
    private LanguageData languageData;
    @BeforeEach
    public void setUp() {
        // Initialize a city object before each test
        languageData = new LanguageData("Chinese",1200000000, 15.2);
    }

    @Test
    //Testing about getter methods of language
    void testGetLanguage() {
        assertEquals("Chinese", languageData.getLanguage()); // test the result with expected outcome
    }

    @Test
    //Testing about getter methods of number of language speaker
    void testGetNumbersOfSpeakers() {
        assertEquals(1200000000, languageData.getNumberOfSpeakers()); // test the result with expected outcome
    }

    @Test
    //Testing about getter methods of language
    void testGetPercentageOfWorldPopulation() {
        assertEquals(15.2, languageData.getPercentageOfWorldPopulation()); // test the result with expected outcome
    }

}

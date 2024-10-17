package UnitTest;

import static org.junit.jupiter.api.Assertions.*;

import ProjectTeam2.PopulationLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PopulationLevelClassTest {

    // Declare an instance of the PopulationLevel class to be tested
    private PopulationLevel populationLevel;

    @BeforeEach
    public void setUp() {
        // Initialize a PopulationLevel object before each test with predefined values
        populationLevel = new PopulationLevel(
                "USA",          // Name of the country
                331002651,      // Total population
                250000000,      // Population living in cities
                75.5,           // Percentage of population living in cities
                81002651,       // Population not living in cities
                24.5            // Percentage of population not living in cities
        );
    }

    @Test
    // Test if the getName method returns the correct country name
    public void testGetName() {
        assertEquals("USA", populationLevel.getName());
    }

    @Test
    // Test if the getTotalPopulation method returns the correct total population
    public void testGetTotalPopulation() {
        assertEquals(331002651, populationLevel.getTotalPopulation());
    }

    @Test
    // Test if the getPopulationInCities method returns the correct population living in cities
    public void testGetPopulationInCities() {
        assertEquals(250000000, populationLevel.getPopulationInCities());
    }

    @Test
    // Test if the getPercentagesInCities method returns the correct percentage of population in cities
    public void testGetPercentagesInCities() {
        assertEquals(75.5, populationLevel.getPercentagesInCities());
    }

    @Test
    // Test if the getPopulationNotInCities method returns the correct population not living in cities
    public void testGetPopulationNotInCities() {
        assertEquals(81002651, populationLevel.getPopulationNotInCities());
    }

    @Test
    // Test if the getPercentagesNotInCities method returns the correct percentage of population not in cities
    public void testGetPercentagesNotInCities() {
        assertEquals(24.5, populationLevel.getPercentagesNotInCities());
    }
}

package UnitTest;

import static org.junit.jupiter.api.Assertions.*;

import ProjectTeam2.PopulationLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PopulationLevelClassTest {

    private PopulationLevel populationLevel;

    @BeforeEach
    public void setUp() {
        // Initialize a PopulationLevel object before each test
        populationLevel = new PopulationLevel(
                "USA",
                331002651,
                250000000,
                75.5,
                81002651,
                24.5
        );
    }

    @Test
    public void testGetName() {
        assertEquals("USA", populationLevel.getName());
    }

    @Test
    public void testGetTotalPopulation() {
        assertEquals(331002651, populationLevel.getTotalPopulation());
    }

    @Test
    public void testGetPopulationInCities() {
        assertEquals(250000000, populationLevel.getPopulationInCities());
    }

    @Test
    public void testGetPercentagesInCities() {
        assertEquals(75.5, populationLevel.getPercentagesInCities());
    }

    @Test
    public void testGetPopulationNotInCities() {
        assertEquals(81002651, populationLevel.getPopulationNotInCities());
    }

    @Test
    public void testGetPercentagesNotInCities() {
        assertEquals(24.5, populationLevel.getPercentagesNotInCities());
    }
}


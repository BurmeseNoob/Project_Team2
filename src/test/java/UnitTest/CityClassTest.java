package UnitTest;

import static org.junit.jupiter.api.Assertions.*;

import ProjectTeam2.City;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CityClassTest {

    private City city;

    /**
     * Set up method executed before each test to initialize the City object.
     * This ensures each test runs with a fresh instance of the City class.
     */
    @BeforeEach
    public void setUp() {
        // Initialize a City object with test data
        city = new City("New York", "USA", "New York", 8419600);
    }

    /**
     * Test for the getName method.
     * This test checks if the method returns the correct name of the city.
     */
    @Test
    public void testGetName() {
        // Assert that the city's name is "New York"
        assertEquals("New York", city.getName());
    }

    /**
     * Test for the getCountryCode method.
     * This test ensures the method returns the correct country code of the city.
     */
    @Test
    public void testGetCountryCode() {
        // Assert that the country's code is "USA"
        assertEquals("USA", city.getCountryCode());
    }

    /**
     * Test for the getDistrict method.
     * This test checks if the method returns the correct district for the city.
     */
    @Test
    public void testGetDistrict() {
        // Assert that the district of the city is "New York"
        assertEquals("New York", city.getDistrict());
    }

    /**
     * Test for the getPopulation method.
     * This test ensures the method returns the correct population of the city.
     */
    @Test
    public void testGetPopulation() {
        // Assert that the population is 8,419,600
        assertEquals(8419600, city.getPopulation());
    }
}

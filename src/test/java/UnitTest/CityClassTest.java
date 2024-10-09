package UnitTest;

import static org.junit.jupiter.api.Assertions.*;

import ProjectTeam2.City;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CityClassTest {

    private City city;

    @BeforeEach
    public void setUp() {
        // Initialize a city object before each test
        city = new City("New York", "USA", "New York", 8419600);
    }

    @Test
    public void testGetName() {
        // Test if the getName method returns the correct name
        assertEquals("New York", city.getName());
    }

    @Test
    public void testGetCountryCode() {
        // Test if the getCountryCode method returns the correct country code
        assertEquals("USA", city.getCountryCode());
    }

    @Test
    public void testGetDistrict() {
        // Test if the getDistrict method returns the correct district
        assertEquals("New York", city.getDistrict());
    }

    @Test
    public void testGetPopulation() {
        // Test if the getPopulation method returns the correct population
        assertEquals(8419600, city.getPopulation());
    }
}


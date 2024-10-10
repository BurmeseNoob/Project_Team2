package UnitTest;

import static org.junit.jupiter.api.Assertions.*; // Importing assertion methods for testing

import ProjectTeam2.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CountryClassTest {

    // Declare a Country object to be tested
    private Country country;

    /**
     * This method runs before each test to initialize the Country object.
     */
    @BeforeEach
    public void setUp() {
        // Initialize a country object with sample data
        country = new Country("USA", "United States", "North America", "Northern America", 331002651, 1);
    }

    /**
     * Test to check if the getCode method works correctly.
     * It should return the country code "USA".
     */
    @Test
    public void testGetCode() {
        assertEquals("USA", country.getCode());
    }

    /**
     * Test to check if the setCode method works correctly.
     * It changes the country code to "CAN" and verifies the result.
     */
    @Test
    public void testSetCode() {
        country.setCode("CAN");
        assertEquals("CAN", country.getCode());
    }

    /**
     * Test to check if the getName method works correctly.
     * It should return the country name "United States".
     */
    @Test
    public void testGetName() {
        assertEquals("United States", country.getName());
    }

    /**
     * Test to check if the setName method works correctly.
     * It changes the country name to "Canada" and verifies the result.
     */
    @Test
    public void testSetName() {
        country.setName("Canada");
        assertEquals("Canada", country.getName());
    }

    /**
     * Test to check if the getContinent method works correctly.
     * It should return the continent "North America".
     */
    @Test
    public void testGetContinent() {
        assertEquals("North America", country.getContinent());
    }

    /**
     * Test to check if the setContinent method works correctly.
     * It changes the continent to "Europe" and verifies the result.
     */
    @Test
    public void testSetContinent() {
        country.setContinent("Europe");
        assertEquals("Europe", country.getContinent());
    }

    /**
     * Test to check if the getRegion method works correctly.
     * It should return the region "Northern America".
     */
    @Test
    public void testGetRegion() {
        assertEquals("Northern America", country.getRegion());
    }

    /**
     * Test to check if the setRegion method works correctly.
     * It changes the region to "Southern America" and verifies the result.
     */
    @Test
    public void testSetRegion() {
        country.setRegion("Southern America");
        assertEquals("Southern America", country.getRegion());
    }

    /**
     * Test to check if the getCapital method works correctly.
     * It should return the capital ID "1".
     */
    @Test
    public void testGetCapital() {
        assertEquals(1, country.getCapital());
    }

    /**
     * Test to check if the setCapital method works correctly.
     * It changes the capital ID to "2" and verifies the result.
     */
    @Test
    public void testSetCapital() {
        country.setCapital(2);
        assertEquals(2, country.getCapital());
    }

    /**
     * Test to check if the getPopulation method works correctly.
     * It should return the population value "331002651".
     */
    @Test
    public void testGetPopulation() {
        assertEquals(331002651, country.getPopulation());
    }

    /**
     * Test to check if the setPopulation method works correctly.
     * It changes the population to "37742154" and verifies the result.
     */
    @Test
    public void testSetPopulation() {
        country.setPopulation(37742154);
        assertEquals(37742154, country.getPopulation());
    }
}

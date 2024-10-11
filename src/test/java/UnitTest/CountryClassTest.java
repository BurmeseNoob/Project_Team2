package UnitTest;

import static org.junit.jupiter.api.Assertions.*;

import ProjectTeam2.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CountryClassTest {

    private Country country;

    @BeforeEach
    public void setUp() {
        // Initialize a country object before each test
        country = new Country("USA", "United States", "North America", "Northern America", 331002651, 1);
    }

    @Test
    public void testGetCode() {
        assertEquals("USA", country.getCode());
    }

    @Test
    public void testSetCode() {
        country.setCode("CAN");
        assertEquals("CAN", country.getCode());
    }

    @Test
    public void testGetName() {
        assertEquals("United States", country.getName());
    }

    @Test
    public void testSetName() {
        country.setName("Canada");
        assertEquals("Canada", country.getName());
    }

    @Test
    public void testGetContinent() {
        assertEquals("North America", country.getContinent());
    }

    @Test
    public void testSetContinent() {
        country.setContinent("Europe");
        assertEquals("Europe", country.getContinent());
    }

    @Test
    public void testGetRegion() {
        assertEquals("Northern America", country.getRegion());
    }

    @Test
    public void testSetRegion() {
        country.setRegion("Southern America");
        assertEquals("Southern America", country.getRegion());
    }

    @Test
    public void testGetCapital() {
        assertEquals(1, country.getCapital());
    }

    @Test
    public void testSetCapital() {
        country.setCapital(2);
        assertEquals(2, country.getCapital());
    }

    @Test
    public void testGetPopulation() {
        assertEquals(331002651, country.getPopulation());
    }

    @Test
    public void testSetPopulation() {
        country.setPopulation(37742154);
        assertEquals(37742154, country.getPopulation());
    }
}


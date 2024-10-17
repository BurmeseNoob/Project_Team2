package UnitTest;

import static org.junit.jupiter.api.Assertions.*;

import ProjectTeam2.SpecificPopulation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SpecificPopulationClassTest {

    // Declare an instance of SpecificPopulation to test its functionality
    private SpecificPopulation specificPopulation;

    @BeforeEach
    public void setUp() {
        // Initialize a SpecificPopulation object before each test
        specificPopulation = new SpecificPopulation("USA", 331002651L);
    }

    @Test
    public void testGetName() {
        // Test the getName method to ensure it returns the correct country name
        assertEquals("USA", specificPopulation.getName());
    }

    @Test
    public void testGetPopulation() {
        // Test the getPopulation method to ensure it returns the correct population value
        assertEquals(331002651L, specificPopulation.getPopulation());
    }
}

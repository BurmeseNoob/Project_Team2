package UnitTest;

import static org.junit.jupiter.api.Assertions.*;

import ProjectTeam2.SpecificPopulation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SpecificPopulationClassTest {

    private SpecificPopulation specificPopulation;

    @BeforeEach
    public void setUp() {
        // Initialize a SpecificPopulation object before each test
        specificPopulation = new SpecificPopulation("USA", 331002651L);
    }

    @Test
    public void testGetName() {
        assertEquals("USA", specificPopulation.getName());
    }

    @Test
    public void testGetPopulation() {
        assertEquals(331002651L, specificPopulation.getPopulation());
    }
}


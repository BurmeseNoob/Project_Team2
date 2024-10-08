package ProjectTeam2;

public class SpecificPopulation {
    private String name;
    private Long population;

    public SpecificPopulation(String name, long population) {
        this.name = name;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public long getPopulation() {
        return population;
    }
}

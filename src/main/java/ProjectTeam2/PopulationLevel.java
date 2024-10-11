package ProjectTeam2;

public class PopulationLevel {
    // private variables
    private String Name;
    private long TotalPopulation, PopulationInCities, PopulationNotInCities;
    private double PercentagesInCities, PercentagesNotInCities;

    // building the constructor
    public PopulationLevel(
            String Name,
            long TotalPopulation,
            long PopulationInCities,
            double PercentagesInCities,
            long PopulationNotInCities,
            double PercentagesNotInCities
    ) {
        this.Name = Name;
        this.TotalPopulation = TotalPopulation;
        this.PopulationInCities = PopulationInCities;
        this.PercentagesInCities = PercentagesInCities;
        this.PopulationNotInCities = PopulationNotInCities;
        this.PercentagesNotInCities = PercentagesNotInCities;
    }

    public String getName() {
        return Name;
    }

    public long getTotalPopulation() {
        return TotalPopulation;
    }

    public long getPopulationInCities() {
        return PopulationInCities;
    }

    public double getPercentagesInCities() {
        return PercentagesInCities;
    }

    public long getPopulationNotInCities() {
        return PopulationNotInCities;
    }

    public double getPercentagesNotInCities() {
        return PercentagesNotInCities;
    }
}

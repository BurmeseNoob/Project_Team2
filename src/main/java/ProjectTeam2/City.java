package ProjectTeam2;

public class City {
    String Name,CountryCode,District;
    int Population;

    public City(String Name, String CountryCode, String District, int Population) {
        this.Name = Name;
        this.CountryCode = CountryCode;
        this.District = District;
        this.Population = Population;
    }

    public String getName() {
        return Name;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public String getDistrict() {
        return District;
    }

    public int getPopulation() {
        return Population;
    }

}

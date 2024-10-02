package ProjectTeam2;

public class Country {
    private String Code,Name,Continent,Region;
    private int Capital,Population;

    public Country(String Code,
                   String Name,
                   String Continent,
                   String Region,
                   int Population,
                   int Capital) {

        this.Code = Code;
        this.Name = Name;
        this.Continent = Continent;
        this.Region = Region;
        this.Population = Population;
        this.Capital = Capital;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getContinent() {
        return Continent;
    }

    public void setContinent(String Continent) {
        this.Continent = Continent;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String Region) {
        this.Region = Region;
    }


    public int getCapital() {
        return Capital;
    }

    public void setCapital(int Capital) {
        this.Capital = Capital;
    }


    public int getPopulation() {
        return Population;
    }

    public void setPopulation(int Population) {
        this.Population = Population;
    }

}

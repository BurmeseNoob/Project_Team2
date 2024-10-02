package ProjectTeam2;

public class City {
    String CountryCode,Language,IsOfficial;
    Float Percentage;

    public City(String CountryCode, String Language, String IsOfficial, Float Percentage) {
        this.CountryCode = CountryCode;
        this.Language = Language;
        this.IsOfficial = IsOfficial;
        this.Percentage = Percentage;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public String getLanguage() {
        return Language;
    }

    public String getIsOfficial() {
        return IsOfficial;
    }

    public Float getPercentage() {
        return Percentage;
    }
}

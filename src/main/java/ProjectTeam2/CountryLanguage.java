package ProjectTeam2;

public class CountryLanguage {
    private String CountryCode,Language,IsOfficial;
    private float Percentage;

    public CountryLanguage(String CountryCode, String Language, String IsOfficial, float Percentage) {
        this.CountryCode = CountryCode;
        this.Language = Language;
        this.IsOfficial = IsOfficial;
        this.Percentage = Percentage;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String CountryCode) {
        this.CountryCode = CountryCode;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String Language) {
        this.Language = Language;
    }

    public String getIsOfficial() {
        return IsOfficial;
    }

    public void setIsOfficial(String IsOfficial) {
        this.IsOfficial = IsOfficial;
    }

    public float getPercentage() {
        return Percentage;
    }
    public void setPercentage(float Percentage) {
        this.Percentage = Percentage;
    }

}

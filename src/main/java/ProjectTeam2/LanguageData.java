package ProjectTeam2;

public class LanguageData {
    private String language;
    private long numberOfSpeakers;
    private double percentageOfWorldPopulation;

    public LanguageData(String language, long numberOfSpeakers, double percentageOfWorldPopulation) {
        this.language = language;
        this.numberOfSpeakers = numberOfSpeakers;
        this.percentageOfWorldPopulation = percentageOfWorldPopulation;
    }

    public String getLanguage() {
        return language;
    }

    public long getNumberOfSpeakers() {
        return numberOfSpeakers;
    }

    public double getPercentageOfWorldPopulation() {
        return percentageOfWorldPopulation;
    }

}

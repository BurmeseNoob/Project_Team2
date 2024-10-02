package Reports;
import ProjectTeam2.City;
import com.google.protobuf.Message;

public class CityReports {

    public String reCountryCode() {
        City ct1 = new City("USA", "English", "T", 95.0f);
        City ct2 = new City("USB", "English", "T", 95.0f);
        City ct3 = new City("USC", "English", "T", 95.0f);

        String Message = ct1.getCountryCode();
        String Message1 = ct2.getCountryCode();
        String Message2 = ct3.getCountryCode();

        String MasterMessage = Message + " " + Message1 + " " + Message2;
        return MasterMessage;
    }
}

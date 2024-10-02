package ProjectTeam2;
import Reports.CountryReports;
import DataBaseConnect.Connection;

import java.sql.*;
import java.util.ArrayList;

public class App
{

    public static void main(String[] args)
    {

        Connection ct = new Connection();
        ct.connect();

        //Creating the CountyReport Object to get the Data for displaying the system.
        CountryReports cr = new CountryReports(ct.getConnection());

        //1# Query about retrieving  and displaying the data of the population of the Country according to the Descending Order
        cr.displayingAboutDescendingPopulation();

        //2#

        //3#

        //4#

        //5#

        //6#

        //7#

        //8#

        //9#

        //10#

        ct.disconnect();

    }
}
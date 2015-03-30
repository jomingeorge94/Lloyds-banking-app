package ncl.ac.uk.cs.teamone.lloydsstudent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Artemiy on 03/03/2015.
 */
public class Data {
    //Used to retrieve data uid of a specific imei from the db
    private static String uid = "";

    public static Map<String, String> customer = new HashMap<>();

    public static List<Map<String, String>> accounts = new ArrayList<Map<String, String>>();

    public static Map<String, String> transactions = new HashMap<>();

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }
}

package ncl.ac.uk.cs.teamone.lloydsstudent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Artemiy on 03/03/2015.
 */
public class Data {
    private Map<String, String> customer = new HashMap<>();

    public void setCustomer(Map<String, String> customer) {
        this.customer = customer;
    }

    public Map<String, String> getCustomer() {
        return customer;
    }
}

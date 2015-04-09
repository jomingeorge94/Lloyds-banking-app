package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.Activity;

/**
 * Created by Artemiy on 27/03/2015.
 */
public class Retrieve {
    private int table = 0;

    public Retrieve(Activity a, String url, int table) {
        String[] keys = {"uid"};
        String[] values = {new Data().customer.get(keys[0])};

        switch(table) {
            case 1:
            //Accounts
            case 2:
                this.table = 1;
                break;
            //Budget
            case 3:
                this.table = 2;
                break;
        }

        PHPHandler handler = new PHPHandler(a, keys, values, this.table);

        handler.execute(url);
    }
}

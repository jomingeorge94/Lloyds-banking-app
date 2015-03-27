package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.Activity;

/**
 * Created by Artemiy on 27/03/2015.
 */
public class Retrieve {
    public Retrieve(Activity a, String url, int table) {
        String[] keys = {"uid"};
        String[] values = {new Data().customer.get(keys[0])};

        switch(table) {
            //Accounts
            case 2:
                table = 1;
                break;
            //Transactions
            case 3:
                table = 2;
                break;
        }

        PHPHandler handler = new PHPHandler(a, keys, values, table);

        handler.execute(url);

        Data d = new Data();

        switch(table) {
            //Accounts
            case 2:
                d.accounts = handler.getData();
                break;
            //Transactions
            case 3:
                d.transactions = handler.getData();
                break;
        }
    }
}

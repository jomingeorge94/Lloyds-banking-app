package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Jomin on 27/03/2015.
 */
public class HomeScreenSecondAccountOverview extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.main_second_account_overview, container, false);

        Data d = new Data();

        TextView type_of_account = (TextView) v.findViewById(R.id.accountType);
        type_of_account.setText(d.accounts.get(1).get("type_of_account"));

        TextView total_money = (TextView) v.findViewById(R.id.currentMoney);
        total_money.setText(d.accounts.get(1).get("total_money"));

        TextView account_number_and_sortcode = (TextView) v.findViewById(R.id.accountDetails);
        account_number_and_sortcode.setText(d.accounts.get(1).get("sortcode") + " | " + d.accounts.get(1).get("account_number"));

        TextView overdraft = (TextView) v.findViewById(R.id.textView6);
        overdraft.setText(d.accounts.get(1).get("overdraft"));

        return v;
    }

}

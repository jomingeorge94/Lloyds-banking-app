package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Jomin on 27/03/2015.
 */
public class HomeScreenSecondAccount extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.main_second_account, container, false);

        Data d = new Data();

        TextView type_of_account = (TextView) v.findViewById(R.id.main_second_account_name);
        type_of_account.setText(d.accounts.get(1).get("type_of_account"));

        TextView total_money = (TextView) v.findViewById(R.id.main_second_account_balance);
        total_money.setText(d.accounts.get(1).get("total_money"));

        TextView account_number_and_sortcode = (TextView) v.findViewById(R.id.main_second_details);
        account_number_and_sortcode.setText(d.accounts.get(1).get("sortcode") + " | " + d.accounts.get(1).get("account_number"));

        v.findViewById(R.id.main_second_account_balance).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                ((LinearLayout)getActivity().findViewById(R.id.main_tab_switch)).removeAllViews();
                transaction.replace(R.id.main_tab_switch, new OverviewFragment());
                transaction.commit();
            }
        });


        return v;
    }

}

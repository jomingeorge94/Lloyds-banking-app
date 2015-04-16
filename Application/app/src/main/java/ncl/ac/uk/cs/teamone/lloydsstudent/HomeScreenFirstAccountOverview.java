package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Jomin on 27/03/2015.
 */
public class HomeScreenFirstAccountOverview extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.main_first_account_overview, container, false);

        Data d = new Data();

        TextView type_of_account = (TextView) v.findViewById(R.id.accountType);
        type_of_account.setText(d.accounts.get(0).get("type_of_account"));

        TextView total_money = (TextView) v.findViewById(R.id.currentMoney);
        total_money.setText(d.accounts.get(0).get("total_money"));

        TextView account_number_and_sortcode = (TextView) v.findViewById(R.id.accountDetails);
        account_number_and_sortcode.setText(d.accounts.get(0).get("sortcode") + " | " + d.accounts.get(0).get("account_number"));

        TextView overdraft = (TextView) v.findViewById(R.id.textView6);
        overdraft.setText(d.accounts.get(0).get("overdraft"));

        v.findViewById(R.id.lastMonthButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                v.findViewById(R.id.scrollvisibility).setVisibility(View.VISIBLE);
            }
        });

        v.findViewById(R.id.thisMonth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Last Month Button is Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        final ImageButton b = (ImageButton)v.findViewById(R.id.fraudalert1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FraudAlertDialogBox dialog = new FraudAlertDialogBox();
                dialog.show(getFragmentManager(),"Hellooo");
            }
        });


        return v;
    }

}

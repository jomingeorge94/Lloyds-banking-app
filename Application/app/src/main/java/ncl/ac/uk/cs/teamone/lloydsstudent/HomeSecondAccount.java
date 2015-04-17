package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Fragment which gives a brief overview of an account and generates a GUI to display the
 * overview pulled from the database
 *
 * Created by Jomin on 27/03/2015.
 */
public class HomeSecondAccount extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        // Set the view for the fragment
        final View v = inflater.inflate(R.layout.home_second_acct, container, false);

        // Create data variable to get and store the information from the server
        Data d = new Data();

        // Assign local variables to TextView fields from the XML
        TextView type_of_account = (TextView) v.findViewById(R.id.main_second_name);
        TextView total_money = (TextView) v.findViewById(R.id.main_second_balance);
        TextView account_number_and_sortcode = (TextView) v.findViewById(R.id.main_second_details);

        // Change the text of the fields to data pulled from the server
        type_of_account.setText(d.accounts.get(1).get("type_of_account"));
        total_money.setText(d.accounts.get(1).get("total_money"));
        account_number_and_sortcode.setText(d.accounts.get(1).get("sortcode") + " | " + d.accounts.get(1).get("account_number"));

        // Add listener to balance to detect when a user clicks the balance
        v.findViewById(R.id.main_second_balance).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create fragment transaction to deal with swapping the fragmetn views
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                // Removes view from parent fragemnt (Main Tabs)
                ((RelativeLayout)getActivity().findViewById(R.id.main_tab_switch)).removeAllViews();
                // Adds replacing the view with new fragment to the transaction
                transaction.replace(R.id.main_tab_switch, (new AccountSwitchingOverview(getFragmentManager())).getItem(1), "");

                // Applies the transaction, showing the new fragment
                transaction.commit();
            }
        });

        // Returns the view
        return v;

    }

    /**
     * Takes a float and returns a formatted crrency output in the form £xx.xx ready to be outputted
     * to the GUI
     *
     * @param value takes the float to be formatted
     * @return returns the formatted float as a String
     */
    private static String currencyFormatter(float value) { return String.format("£%.2f", value); }

}

package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import ncl.ac.uk.cs.teamone.lloydsstudent.Data;
import ncl.ac.uk.cs.teamone.lloydsstudent.FraudAlertDialogBox;
import ncl.ac.uk.cs.teamone.lloydsstudent.R;

/**
 * Fragment which gives a brief overview of an account and generates a GUI to dispaly the
 * overview pulled from the database
 *
 * Created by Jomin on 27/03/2015.
 */
public class HomeSecondOverview extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        // Generates the view for this fragment from the XML file
        final View v = inflater.inflate(R.layout.home_second_acct_overview, container, false);

        // Creates a local variable and gets the data from the database
        Data d = new Data();

        // Assign local variables to the TextViews from the XML file
        TextView type_of_account = (TextView) v.findViewById(R.id.main_second_account);
        TextView total_money = (TextView) v.findViewById(R.id.main_second_balance_overview);
        TextView account_number_and_sortcode = (TextView) v.findViewById(R.id.main_second_details);
        TextView overdraft = (TextView) v.findViewById(R.id.main_second_overdraft);

        // Assign their values from the database
        type_of_account.setText(d.accounts.get(1).get("type_of_account"));
        total_money.setText(currencyFormatter(Float.parseFloat(d.accounts.get(1).get("total_money"))));
        account_number_and_sortcode.setText(d.accounts.get(1).get("sortcode") + " | " + d.accounts.get(1).get("account_number"));
        overdraft.setText(d.accounts.get(1).get("overdraft"));

        // Listener to detect click of the last month button
        v.findViewById(R.id.lastMonthButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                v.findViewById(R.id.main_second_scroll).setVisibility(View.VISIBLE);
            }
        });

        // Listener to detect click of the this month button
        v.findViewById(R.id.thisMonth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Last Month Button is Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // Create an array to hold all of the fruad buttons
        final ImageButton[] button = new ImageButton[7];
        // Begin loop to add listeners
        for(int i = 0; i < 7; i++) {

            // Temporary variable for button ID
            int j = i + 1;
            // Search term to find buttons from XML file
            String search = "main_second_trans_btn_" + j;

            // Get the resource ID of each button
            int ID = getResources().getIdentifier(search, "id", getActivity().getPackageName());
            // Assign the XML object to the local array variable
            button[i] = ((ImageButton) v.findViewById(ID));

            // Give each button a listener
            button[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Create new Dialog box object
                    FraudAlertDialogBox dialog = new FraudAlertDialogBox();
                    // Show the dialog box
                    dialog.show(getFragmentManager(), "");
                }
            });

        }

        // Return the View
        return v;
    }

    /**
     * Takes a float and returns a formatted crrency output in the form £xx.xx ready to be outputted
     * to the GUI
     *
     * @param value takes the float to be formatted
     * @return returns the formatted float as a String
     */
    private String currencyFormatter(float value) { return String.format("£%.2f", value); }

}
package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Jomin on 19/02/2015.
 */
public class HomeFragment extends Fragment {
    View v;
    ViewPager viewPager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View v =  inflater.inflate(R.layout.home_layout, container, false);

        // Create local variable to get and store the customer data
        Data data = new Data();

        //Set the customer name
        TextView customerName = (TextView) v.findViewById(R.id.main_tag_customer);

        customerName.setText(data.customer.get("firstname") + " " + data.customer.get("lastname"));

        viewPager = (ViewPager)v.findViewById(R.id.main_tag_account_switcher);
        AccountSwitching accountSwitching = new AccountSwitching(getChildFragmentManager());
        viewPager.setAdapter(accountSwitching);

        // Creates a click event to open Transfer fragment
        final Button maketransfer = (Button)v.findViewById(R.id.main_tab_transfer);
        maketransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gets the fragment manager from the current parent and starts the transaction
                android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
                // Clears the previous parent container
                ((FrameLayout)getActivity().findViewById(android.R.id.tabcontent)).removeAllViews();
                // Adds the fragment to be show to the transaction
                transaction.replace(android.R.id.tabcontent, new Transfer(), "");
                // Replaces the fragment
                transaction.commit();
            }
        });

        // Creates a click event to open Pay A Contact Fragment
        final Button paycontact = (Button)v.findViewById(R.id.main_tab_contact);
        paycontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gets the fragment manager from the current parent and starts the transaction
                android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
                // Clears the previous parent container
                ((FrameLayout)getActivity().findViewById(android.R.id.tabcontent)).removeAllViews();
                // Adds the fragment to be show to the transaction
                transaction.replace(android.R.id.tabcontent, new PayContact(), "");
                // Makes it possible to return from the new fragment
                transaction.addToBackStack("");
                // Replaces the fragment
                transaction.commit();
            }
        });

        this.v = v;

        return v;
    }

    /**
     * Fragment which gives a brief overview of an account and generates a GUI to display the
     * overview pulled from the database
     *
     * Created by Jomin on 27/03/2015.
     */
    public static class FirstAccount extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

            // Set the view for the fragment
            final View v = inflater.inflate(R.layout.home_first_acct, container, false);

            // Create data variable to get and store the information from the server
            Data d = new Data();

            // Assign local variables to TextView fields from the XML
            TextView type_of_account = (TextView) v.findViewById(R.id.main_first_name);
            TextView total_money = (TextView) v.findViewById(R.id.main_first_balance);
            TextView account_number_and_sortcode = (TextView) v.findViewById(R.id.main_first_details);

            // Change the text of the fields to data pulled from the server
            type_of_account.setText(d.accounts.get(0).get("type_of_account"));
            total_money.setText(currencyFormatter(Float.parseFloat(d.accounts.get(0).get("total_money"))));
            account_number_and_sortcode.setText(d.accounts.get(0).get("sortcode") + " | " + d.accounts.get(0).get("account_number"));

            // Add listener to balance to detect when a user clicks the balance
            v.findViewById(R.id.main_first_balance).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Create fragment transaction to deal with swapping the fragmetn views
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    // Removes view from parent fragemnt (Main Tabs)
                    ((RelativeLayout)getActivity().findViewById(R.id.main_tab_switch)).removeAllViews();
                    // Adds replacing the view with new fragment to the transaction
                    transaction.replace(R.id.main_tab_switch, new OverviewFragment(), "");
                    // Applies the transaction, showing the new fragment
                    transaction.commit();
                }
            });

            // Returns the view
            return v;

        }

    }

    /**
     * Fragment which gives a brief overview of an account and generates a GUI to dispaly the
     * overview pulled from the database
     *
     * Created by Jomin on 27/03/2015.
     */
    public static class FirstOverview extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            // Generates the view for this fragment from the XML file
            final View v = inflater.inflate(R.layout.home_first_acct_overview, container, false);

            // Creates a local variable and gets the data from the database
            Data d = new Data();

            // Assign local variables to the TextViews from the XML file
            TextView type_of_account = (TextView) v.findViewById(R.id.main_first_account);
            TextView total_money = (TextView) v.findViewById(R.id.main_first_balance_overview);
            TextView account_number_and_sortcode = (TextView) v.findViewById(R.id.main_first_details);
            TextView overdraft = (TextView) v.findViewById(R.id.main_first_overdraft);

            // Assign their values from the database
            type_of_account.setText(d.accounts.get(0).get("type_of_account"));
            total_money.setText(currencyFormatter(Float.parseFloat(d.accounts.get(0).get("total_money"))));
            account_number_and_sortcode.setText(d.accounts.get(0).get("sortcode") + " | " + d.accounts.get(0).get("account_number"));
            overdraft.setText(d.accounts.get(0).get("overdraft"));

            // Listener to detect click of the last month button
            v.findViewById(R.id.lastMonthButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    v.findViewById(R.id.main_first_scroll).setVisibility(View.VISIBLE);
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
            for (int i = 0; i < 7; i++) {

                // Temporary variable for button ID
                int j = i + 1;
                // Search term to find buttons from XML file
                String search = "main_first_trans_btn_" + j;

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
    }

    /**
     * Fragment which gives a brief overview of an account and generates a GUI to display the
     * overview pulled from the database
     *
     * Created by Jomin on 27/03/2015.
     */
    public static class SecondAccount extends Fragment {

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
                    transaction.replace(R.id.main_tab_switch, new OverviewFragment());
                    // Applies the transaction, showing the new fragment
                    transaction.commit();
                }
            });

            // Returns the view
            return v;

        }

    }

    /**
     * Fragment which gives a brief overview of an account and generates a GUI to dispaly the
     * overview pulled from the database
     *
     * Created by Jomin on 27/03/2015.
     */
    public static class SecondOverview extends Fragment {

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


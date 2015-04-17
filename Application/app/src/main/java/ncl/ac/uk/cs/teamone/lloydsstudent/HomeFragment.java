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
 * This is a fragment which will be used to generate the GUI for the Home Tab of the application.
 * As well as displaying information it also initiates other activities such as payment and
 * transfers
 *
 * Created by Jomin on 19/02/2015.
 */
public class HomeFragment extends Fragment {

    // Variables to use across methods and nested classes
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
     * Takes a float and returns a formatted crrency output in the form £xx.xx ready to be outputted
     * to the GUI
     *
     * @param value takes the float to be formatted
     * @return returns the formatted float as a String
     */
    private static String currencyFormatter(float value) { return String.format("£%.2f", value); }

}


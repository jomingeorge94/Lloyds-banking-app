package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * A fragment which creates a GUI that links to a database to preform transactions between the bank
 * accounts of one Lloyds customer
 *
 * Created by Jomin on 07/03/2015.
 */
public class HomeTransfer extends Fragment implements AdapterView.OnItemSelectedListener {

    // Declare variable to be used to store spinner objecs form XML files
    private Spinner from;
    private Spinner to;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.home_transfer, container, false);

        // Initialise variable to store spinner values
        ArrayList<String> list = new ArrayList<String>();

        // Creates a local variable and gets the data from the database
        final Data d = new Data();

        // Adds type of accounts to list from database
        for(int i = 0; i < d.accounts.size(); i++) {
            list.add(d.accounts.get(i).get("type_of_account"));
        }

        // Initialize the spinners from XML
        Spinner from = (Spinner) v.findViewById(R.id.transfer_from_spinner);
        Spinner to = (Spinner) v.findViewById(R.id.transfer_to_spinner);

        // Create an Array Adapter and populate it ready to pass to the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, list);

        // Populate the spinner with the values from the Array Adapter
        from.setAdapter(adapter);
        to.setAdapter(adapter);

        // Set prompt for the spinner
        from.setPrompt("Select an account");
        to.setPrompt("Select an account");

        // Initialise text fields and buttons form XML to local vaiables
        final EditText amount = (EditText)v.findViewById(R.id.transfer_amount);
        final EditText reference = (EditText)v.findViewById(R.id.transfer_reference);
        final Button reviewButton = (Button)v.findViewById(R.id.transfer_confirm);

        // Create a TExt watcher to make sure each input has some value before enabling finish button
        final TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // Checks amount has at least one character and reference at least 5
                if(amount.length() >= 1 && reference.length() >= 5){
                    // Enable Button
                    reviewButton.setEnabled(true);
                    reviewButton.setBackgroundColor(getResources().getColor(R.color.dark_green));
                } else  {
                    // Disable Button
                    reviewButton.setEnabled(false);
                    reviewButton.setBackgroundColor(getResources().getColor(R.color.medium_grey));
                }
            }
        };

        // Add watcher to both text fields
        amount.addTextChangedListener(watcher);
        reference.addTextChangedListener(watcher);

        // Create a new onClickListener for the review button to handle inputted data on a click
        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Total money of the account in the 'From' spinner
                float from = Float.parseFloat(d.accounts.get(HomeTransfer.this.from.getSelectedItemPosition()).get("total_money"));
                float fromOverdraft = Float.parseFloat(d.accounts.get(HomeTransfer.this.from.getSelectedItemPosition()).get("overdraft"));

                // Total money of the account in the 'To' spinner
                float to = Float.parseFloat(d.accounts.get(HomeTransfer.this.to.getSelectedItemPosition()).get("total_money"));

                // The amount to transfer
                float transferAmount = Float.parseFloat(amount.getText().toString());

                // Checks to make sure that the current money + overdraft do not go less than 0 before opening confirmation box
                if((from + fromOverdraft) - transferAmount > 0) {

                    // Creates a new confirm transfer fragment and initialises it to a variable
                    HomeTransferConfirm fragment = new HomeTransferConfirm();

                    // New bundle to store data to be transferred
                    Bundle args = new Bundle();

                    // Add each input value to the bundle
                    args.putString("spinnerAccountFrom", HomeTransfer.this.from.getSelectedItem().toString());
                    fragment.setArguments(args);
                    args.putString("spinnerAccountTo", HomeTransfer.this.to.getSelectedItem().toString());
                    fragment.setArguments(args);
                    args.putString("spinnerAccountAmount", amount.getText().toString());
                    fragment.setArguments(args);
                    args.putString("spinnerAccountReference", reference.getText().toString());
                    fragment.setArguments(args);

                    // Sets the account number 'From'
                    args.putString("from", d.accounts.get(HomeTransfer.this.from.getSelectedItemPosition()).get("account_number"));
                    fragment.setArguments(args);
                    // Sets the money 'From'
                    args.putString("from_money", Float.toString(from));
                    fragment.setArguments(args);
                    // Sets the account number 'To'
                    args.putString("to", d.accounts.get(HomeTransfer.this.to.getSelectedItemPosition()).get("account_number"));
                    fragment.setArguments(args);
                    // Sets the money 'To'
                    args.putString("to_money", Float.toString(to));
                    fragment.setArguments(args);
                    // The transfer amount
                    args.putString("transferAmount", Float.toString(transferAmount));

                    // Sets the argument for the new fragment
                    fragment.setArguments(args);

                    // Starts the new fragment
                    fragment.show(getFragmentManager(), "Make a Transfer");

                }
                else {

                    // Constructs an alert dialog
                    AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                    alertDialog.setTitle("Not Enough Money");

                    // Set action for the dialog button
                    alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });

                    // Show the alert
                    alertDialog.show();

                }
            }
        });

        // Return fragment view
        return v;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

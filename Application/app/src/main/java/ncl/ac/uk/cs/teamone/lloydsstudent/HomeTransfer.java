package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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
        ArrayList<String> list = new ArrayList<>();

        // Creates a local variable and gets the data from the database
        final Data d = new Data();

        // Adds type of accounts to list from database
        for(int i = 0; i < d.accounts.size(); i++) {
            list.add(d.accounts.get(i).get("type_of_account"));
        }

        // Initialize the spinners from XML
        from = (Spinner) v.findViewById(R.id.transfer_from_spinner);
        to = (Spinner) v.findViewById(R.id.transfer_to_spinner);

        // Create an Array Adapter and populate it ready to pass to the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_item, list);

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
        v.findViewById(R.id.transfer_confirm).setOnClickListener(new View.OnClickListener() {
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
                if ((from + fromOverdraft) - transferAmount > 0) {

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

                } else {

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

    /**
     * A Dialog Fragment which is used to create a graphical pop up for the user to show a summary of
     * the transaction they have just inputted and give them the option to either edit the transaction
     * or go ahead and perform it
     *
     * Created by Jomin on 15/03/2015.
     */
    public static class HomeTransferConfirm extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            // Create and inflater to inflate the view and align the inflated view to a local variable
            final LayoutInflater inflater = getActivity().getLayoutInflater();
            final View dialog = inflater.inflate(R.layout.home_transfer_confirm, null);

            // Create strings from arguments passed to dialog
            final String from = getArguments().getString("spinnerAccountFrom");
            final String to = getArguments().getString("spinnerAccountTo");
            final String reference = getArguments().getString("spinnerAccountReference");
            // Call to currency formatter method to convert string into a string in format £xx.xx
            final String amount = currencyFormatter(Float.parseFloat(getArguments().getString("spinnerAccountAmount")));

            // Initialises a dilouge builder to disaply the dialouge
            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            // Assigns the view to the builder
            builder.setView(dialog);
            // To allow the dialouge to be closed
            builder.setCancelable(true);

            // Uses the diaolouge builder to create the dialouge
            final AlertDialog firstdialog = builder.create();

            // Get and set the position of the window and align with parent
            WindowManager.LayoutParams params = firstdialog.getWindow().getAttributes();
            params.gravity = Gravity.TOP | Gravity.RIGHT;
            params.x = 0;
            params.y = 300;

            // Assign received values to label
            TextView fromView = (TextView) dialog.findViewById(R.id.transfer_confirm_from);
            fromView.setText(from);

            TextView toView = (TextView) dialog.findViewById(R.id.transfer_confirm_to);
            toView.setText(to);

            TextView amountView = (TextView) dialog.findViewById(R.id.transfer_confirm_amount);
            amountView.setText(amount);

            TextView referenceView = (TextView) dialog.findViewById(R.id.transfer_confirm_reference);
            referenceView.setText(reference);

            // Assigns listener to dialog positive button to submit request to database handler
            dialog.findViewById(R.id.transfer_confirm_ok).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // Initialize the async task
                    String[] keys = {"uid", "from", "from_money", "to", "to_money", "amount"};
                    String[] values = {new Data().customer.get("uid"), getArguments().getString("from"), getArguments().getString("from_money"), getArguments().getString("to"), getArguments().getString("to_money"), amount};

                    // New PHPHandler object to deal with request
                    PHPHandler handler = new PHPHandler(getActivity(), keys, values, 5);

                    // Execute the script
                    handler.execute("http://www.abunities.co.uk/t2022t1/maketransfer.php");

                }
            });

            // Assigns listener to negative button to return to previous screen
            dialog.findViewById(R.id.transfer_confirm_edit).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // Close dialog
                    firstdialog.dismiss();

                }
            });

            // Return the view
            return firstdialog;
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
}

package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A fragment which creates a GUI that links to a database to perform transactions with users out
 * of the Lloyds banking system
 *
 * Created by Jomin on 12/03/2015.
 */
public class PayContact extends Fragment {

    // Local variable to sotre spinner object from XML file
    private Spinner spinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.pay_contact, container, false);

        // Initialise variable to store spinner values
        ArrayList<String> list = new ArrayList<String>();

        // Creates a local variable and gets the data from the database
        final Data d = new Data();

        // Adds type of accounts to list from database
        for(int i = 0; i < d.accounts.size(); i++) {
            list.add(d.accounts.get(i).get("type_of_account"));
        }

        // Initialize the spinner from XML
        spinner = (Spinner) v.findViewById(R.id.pay_from_spinner);

        // Create an Array Adapter and populate it ready to pass to the spinner
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, list);

        // Populate the spinner with the values from the Array Adapter
        spinner.setAdapter(adapter);
        // Set prompt for the spinner
        spinner.setPrompt("Select an account");

        // Initialise text fields and buttons form XML to local variables
        final EditText number = (EditText)v.findViewById(R.id.pay_to_number);
        final EditText sortcode = (EditText)v.findViewById(R.id.pay_to_sort);
        final EditText amount = (EditText)v.findViewById(R.id.pay_to_amount);
        final EditText reference = (EditText)v.findViewById(R.id.pay_to_reference);
        final Button review = (Button)v.findViewById(R.id.pay_review);

        // Create a Text watcher to make sure each input has some value before enabling finish button
        final TextWatcher watcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Prevents blank spaces
                if (number.getText().toString().matches("[^0]")){
                    number.setText("");
                }
                // Checks lengths of text are reasonable and not null
                if(number.length() == 11 && sortcode.length() == 8 && amount.length() >=1 && reference.length() >= 3) {
                    // Enable button
                    review.setEnabled(true);
                    review.setBackgroundColor(getResources().getColor(R.color.dark_green));
                } else  {
                    // Disable button
                    review.setEnabled(false);
                    review.setBackgroundColor(getResources().getColor(R.color.medium_grey));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        // Add watcher to text fields
        number.addTextChangedListener(watcher);
        sortcode.addTextChangedListener(watcher);
        amount.addTextChangedListener(watcher);
        reference.addTextChangedListener(watcher);

        // Create a new onClickListener for the review button to handle inputted data on a click
        v.findViewById(R.id.pay_review).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Creates a new Confirm fragment and initialises it to a variable
                PayConfirm confirm = new PayConfirm();

                // New bundle to store data to be transferred
                Bundle args = new Bundle();

                args.putString("from", spinner.getSelectedItem().toString());
                confirm.setArguments(args);

                args.putString("number", number.getText().toString());
                confirm.setArguments(args);

                args.putString("sortcode", sortcode.getText().toString());
                confirm.setArguments(args);

                args.putString("amount", amount.getText().toString());
                confirm.setArguments(args);

                args.putString("reference", reference.getText().toString());
                confirm.setArguments(args);

                // Starts the new fragment
                confirm.show(getFragmentManager(), "make a transfer dialog");
            }
        });

        // Returns the view
        return v;
    }

    /**
     * A Dialog Fragment which is used to create a graphical pop up for the user to show a summary of
     * the transaction they have just inputted and give them the option to either edit the transaction
     * or go ahead and perform it
     *
     * Created by Jomin on 17/03/2015.
     */
    public static class PayConfirm extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            // Create and inflater to inflate the view and align the inflated view to a local variable
            final LayoutInflater inf = getActivity().getLayoutInflater();
            final View view = inf.inflate(R.layout.pay_contact_confirm, null);

            // Create strings from arguments passed to dialog
            String account = getArguments().getString("from");
            String number = getArguments().getString("number");
            String sortcode = getArguments().getString("sortcode");
            String amount = getArguments().getString("amount");
            String reference = getArguments().getString("reference");

            // Initialises a dialog builder to display the dialog
            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            // Assigns the view to the builder
            builder.setView(view);
            // To allow the dialog to be closed
            builder.setCancelable(true);

            // Uses the dialog builder to create the dialog
            final AlertDialog dialog = builder.create();

            // Assign received values to label
            TextView accountView = (TextView) view.findViewById(R.id.pay_from);
            accountView.setText(account);

            TextView phoneView = (TextView) view.findViewById(R.id.pay_to);
            phoneView.setText(number);

            TextView sortcodeView = (TextView) view.findViewById(R.id.pay_sortcode);
            sortcodeView.setText(sortcode);

            TextView amountView = (TextView) view.findViewById(R.id.pay_amount);
            amountView.setText(amount);

            TextView referenceView = (TextView) view.findViewById(R.id.pay_reference);
            referenceView.setText(reference);

            // Assigns listener to dialog positive button to submit request to database handler
            view.findViewById(R.id.pay_ok).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // Create dialog to tell user payment is successful
                    final ProgressDialog success = new ProgressDialog(getActivity());
                    success.setTitle("Payment Success");
                    success.setMessage("A payment that you've initiated has been completed successfully. " +
                            "It might take a couple minutes until this line item shows up on the Transaction history.");
                    success.setIcon(R.drawable.transfer_money_icon);
                    success.setIndeterminate(true);
                    success.setCancelable(false);
                    // Show dialog
                    success.show();

                    // Create wait for transaction to process, currently 8 seconds
                    long delay = 8000;
                    // Create new timer to control wait
                    Timer timer = new Timer();
                    // Begin wait
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            // Dismiss popups
                            dialog.dismiss();
                            success.dismiss();
                            // Create fragment manager to reset view
                            android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            transaction.replace(android.R.id.tabcontent, new PayContact(), "PayContact");
                            // Reset view
                            transaction.commit();
                        }
                        // Define delay time
                    }, delay);
                }
            });

            // Create listener to cancel dialouge and allow user to edit details
            view.findViewById(R.id.pay_edit).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Close dialog
                    dialog.dismiss();
                }
            });

            return dialog;
        }
    }
}


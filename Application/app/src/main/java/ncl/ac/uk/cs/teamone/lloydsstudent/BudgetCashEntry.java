package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import static android.widget.AdapterView.OnItemSelectedListener;

/**
 * A class which generates a Dialog fragment which gives the user an option to log a cash
 * transaction in the budgeting part of the application
 *
 * Created by Dan on 19/03/2015.
 */
public class BudgetCashEntry extends DialogFragment {

    // Declare variable to be used in the Dialog
    private EditText name;
    private EditText purchase;
    private Spinner category;
    private Button add;
    private Button close;

    // Declares variables to hold money values
    private float cashBalance;
    private float purchaseBalance;

    // Boolean condition to check if the view has just been created (to prevent early onitemselected call)
    private boolean start = true;

    //method to switch the fragment, this method will switch the fragment to the budget layout xml file
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.budget_cash_entry, container, false);
        // Get an input manager to mange soft keyboard during inputs
        final InputMethodManager keyboard = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        // Prevent the title bar from being shown
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        // Gets the spinner from the XML resource file and assigns it to a local variable
        category = (Spinner) v.findViewById(R.id.cash_category_input);
        // Creates a resource variable for pulling the array of strings
        Resources res = getResources();
        // Creates an array adapter to generate spinner items
        ArrayAdapter adapter = new ArrayAdapter<>(getActivity(), R.layout.cash_entry_dropdown, res.getStringArray(R.array.cash_categories) );
        // Assigns a view to the spinner to style the items
        adapter.setDropDownViewResource(R.layout.cash_entry_item);
        // Assigns the formatted view filled with items to the spinner
        category.setAdapter(adapter);

        // Gets input methods from the XML file and assigns them to local variables
        name = (EditText) v.findViewById(R.id.cash_name_input);
        purchase = (EditText) v.findViewById(R.id.cash_purchase_input);

        // Gets the buttons from the XML file and assigns them to local variables
        add = (Button) v.findViewById(R.id.cash_add_button);
        close = (Button) v.findViewById(R.id.cash_cancel_button);

        // Creates a Text listener to detect if the Add button should be enabled
        final TextWatcher watcher = new TextWatcher() {

            // Ignore
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {}
            // Ignore
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {}
            // When text changes conditions are checked for the Add button
            @Override
            public void afterTextChanged(Editable s) {
                // Checks each input field has at least something in it
                if (name.length() > 0 && purchase.length() > 0 && category.getSelectedItem().toString() != null) {
                    // Enables Add Button
                    add.setEnabled(true);
                    add.setBackgroundColor(getResources().getColor(R.color.dark_green));

                }
                else {
                    // Disables Add Button
                    add.setEnabled(false);
                    add.setBackgroundColor(getResources().getColor(R.color.medium_grey));
                }
            }

        };

        // Assigns text changed listeners
        name.addTextChangedListener(watcher);
        purchase.addTextChangedListener(watcher);

        // Adds listener to add button to confirm the cash entry
        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /******* Debug Assignment *******/
                cashBalance = 10.11f;
                // Retrieves the value inputted
                purchaseBalance = Float.parseFloat(purchase.getText().toString());
                // Performs a check to see if cash entered is over cash balance
                addCheck(v);
            }

        });

        // Adds listener to close button to close fragment when clicked
        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Close fragment
                dismiss();
            }

        });

        // Adds listener to change focus when "next" key is pressed
        name.setOnEditorActionListener(new EditText.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                // Checks input has come from next key
                if(actionId == EditorInfo.IME_ACTION_NEXT) {
                    // Tells program to activate spinner listener
                    start = false;
                    // Removes window from view
                    keyboard.hideSoftInputFromWindow(name.getWindowToken(), 0);
                    // Removes focus from name edit text
                    v.clearFocus();
                    // Requests focus to the spinner
                    category.requestFocus();
                    // Simlates a click on the spinner to bring up options
                    category.performClick();
                }
                // Return success
                return true;
            }

        });

        // Adds a on item select listener to the spinner to change focus to next edit text
        category.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Check the call has not been made to early, so focus will only change if inputs have previously been selcted
                if(!start) {
                    // Clears the focus from the spinner
                    parent.clearFocus();
                    // Requests focus on the next edit text
                    purchase.requestFocus();
                    // Shows the soft keyboard for the next input
                    keyboard.showSoftInput(purchase, InputMethodManager.SHOW_IMPLICIT);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Ignore
            }

        });

        // Adds a key listener to detect when the user presses the done key on the soft keyboard
        purchase.setOnEditorActionListener(new EditText.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                // Checks the input has come from the done button
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // Hides the soft keyboard from the view
                    keyboard.hideSoftInputFromWindow(purchase.getWindowToken(), 0);
                    // Clears the focus from the edit text
                    v.clearFocus();
                }
                // Returns success
                return true;
            }

        });

        // Return the fragment view
        return v;

    }

    /**
     * Performs a simple check which displays a prompt to the user if the purchase looks incorrect.
     * As the application should be tracking how much cash the user has, if a logged purchase is
     * larger there is a chance that the user may have made a mistake. This dialog asks the user
     * to confirm or change before pushing a larger than expected purchase
     *
     * @param v current view of the fragment
     */
    private void addCheck(View v) {

        if(purchaseBalance > cashBalance) {

            // Creates a new dialog builder object to construct a dialog
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
            // Sets the title of the dialog
            alertDialogBuilder.setTitle("Warning");
            // Begin construction the builder
            alertDialogBuilder
                    // Set the message
                    .setMessage("Your purchase is over the amount of cash you have currently withdrawn. If you wish to continue with this purchase your cash balance will be set to £0 and your full cash amount will be added to your transactions")
                    // Remove cancel button
                    .setCancelable(false)
                    // Set positive button to take user back to edit input
                    .setPositiveButton("Edit",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            dialog.dismiss();
                        }
                    })
                    // Set negative button to continue to log the purchase
                    .setNegativeButton("Continue", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Call method to log purchase
                            addCashPurchase();
                        }
                    });

            // Create the dialog from the builder
            AlertDialog alertDialog = alertDialogBuilder.create();
            // Show the dialog
            alertDialog.show();
        }

    }

    /**
     * A method which takes the data entered by the user and commits their changes to the
     * database
     */
    private void addCashPurchase() {

        // Url to connect to
        String url = "http://www.abunities.co.uk/t2022t1/add_entry.php";

        // Values to send to the PHP file
        String[] keys = {"uid", "purchase", "category"};
        String[] values = {new Data().customer.get("uid"), Float.toString(purchaseBalance), category.getSelectedItem().toString()};

        // Create an asynchronous object
        PHPHandler handler = new PHPHandler(getActivity(), keys, values, 6);

        // Execute the object
        handler.execute(url);

        // Close fragment
        dismiss();

    }

}
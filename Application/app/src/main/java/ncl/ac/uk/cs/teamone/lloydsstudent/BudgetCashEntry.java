package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
 * Created by Dan on 19/03/2015.
 */
public class BudgetCashEntry extends DialogFragment {

    // Declare variable to be used in the Dialog
    private EditText name;
    private EditText purchase;
    private Spinner category;
    private Button add;
    private Button close;

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

        name.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_NEXT) {
                    start = false;
                    keyboard.hideSoftInputFromWindow(name.getWindowToken(), 0);
                    v.clearFocus();
                    category.requestFocus();
                    category.performClick();
                }
                return true;
            }
        });

        category.setSelection(0);
        category.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Check the call has not been made to early, so focus will only change if inputs have previously been selcted
                if(!start) {
                    parent.clearFocus();
                    purchase.requestFocus();
                    keyboard.showSoftInput(purchase, InputMethodManager.SHOW_IMPLICIT);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        purchase.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    keyboard.hideSoftInputFromWindow(purchase.getWindowToken(), 0);
                    v.clearFocus();
                }
                return true;
            }
        });

        return v;
    }

    private void addCheck(View v) {
        if(purchaseBalance > cashBalance) {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
            alertDialogBuilder.setTitle("Warning");
            alertDialogBuilder
                    .setMessage("Your purchase is over the amount of cash you have currently withdrawn. If you wish to continue with this purchase your cash balance will be set to £0 and your full cash amount will be added to your transactions")
                    .setCancelable(false)
                    .setPositiveButton("Edit",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("Continue", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            addCashPurchase();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }

    private void addCashPurchase() {
        //url to connect to
        String url = "http://www.abunities.co.uk/t2022t1/add_entry.php";

        //values to send to the PHP file
        String[] keys = {"uid", "purchase", "category"};
        String[] values = {new Data().getUid(), Float.toString(purchaseBalance), category.getSelectedItem().toString()};

        //create an asynchronous object
        PHPHandler handler = new PHPHandler(getActivity(), keys, values, 0);

        //execute the object
        handler.execute(url);
    }

}
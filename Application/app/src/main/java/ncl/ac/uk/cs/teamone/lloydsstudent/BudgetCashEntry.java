package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
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


/**
 * Created by Dan on 19/03/2015.
 */
public class BudgetCashEntry extends DialogFragment {

    private EditText name;
    private EditText purchase;
    private Spinner category;
    private Button add;
    private Button close;

    private float cashBalance;
    private float purchaseBalance;

    //method to switch the fragment, this method will switch the fragment to the budget layout xml file
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.budget_cash_entry, container, false);

        Spinner spinner = (Spinner) v.findViewById(R.id.cash_category_input);
        Resources res = getResources();
        ArrayAdapter adapter = new ArrayAdapter<>(getActivity(), R.layout.cash_entry_dropdown, res.getStringArray(R.array.cash_categories) );
        adapter.setDropDownViewResource(R.layout.cash_entry_item);
        spinner.setAdapter(adapter);
        spinner.setPrompt("Select an account");

        name = (EditText) v.findViewById(R.id.cash_name_input);
        category = (Spinner) v.findViewById(R.id.cash_category_input);
        purchase = (EditText) v.findViewById(R.id.cash_purchase_input);

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

        name.addTextChangedListener(watcher);
        purchase.addTextChangedListener(watcher);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cashBalance = 10.11f;
                purchaseBalance = Float.parseFloat(purchase.getText().toString());
                addCheck(v);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
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
                            addCashPurcahse();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }

    private void addCashPurcahse() {
        // Backend method for adding a cash purchase
    }

}
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
 * Created by Jomin on 07/03/2015.
 */
public class HomeTransfer extends Fragment implements AdapterView.OnItemSelectedListener {

    Spinner makeaTransferSpinnerAccountFrom;
    Spinner MakeaTransferSpinnerAccountTo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.home_transfer, container, false);

        ArrayList<String> list = new ArrayList<String>();
        final Data d = new Data();

        for(int i = 0; i < d.accounts.size(); i++) {
            list.add(d.accounts.get(i).get("type_of_account"));
        }

        //initialize the spinner
        makeaTransferSpinnerAccountFrom = (Spinner) v.findViewById(R.id.transfer_from_spinner);
        //initialize the adapter with values in list
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, list);
        //set the adapter and the prompt for the user
        makeaTransferSpinnerAccountFrom.setAdapter(adapter);
        makeaTransferSpinnerAccountFrom.setPrompt("Select an account");

        //initialize second spinner
        MakeaTransferSpinnerAccountTo = (Spinner) v.findViewById(R.id.transfer_to_spinner);
        //same as above
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, list);
        //set the adapter and the prompt for the user
        MakeaTransferSpinnerAccountTo.setAdapter(adapter2);
        MakeaTransferSpinnerAccountTo.setPrompt("Select an account");

        final EditText amount = (EditText)v.findViewById(R.id.transfer_amount);
        final EditText reference = (EditText)v.findViewById(R.id.transfer_reference);
        final Button reviewButton = (Button)v.findViewById(R.id.transfer_confirm);

        final TextWatcher watcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(amount.length() >= 1 && reference.length() >= 5){
                    reviewButton.setEnabled(true);
                    reviewButton.setBackgroundColor(Color.parseColor("#369742"));
                } else  {
                    reviewButton.setEnabled(false);
                    reviewButton.setBackgroundColor(Color.parseColor("#ffcacaca"));
                }
            }
        };

        amount.addTextChangedListener(watcher);
        reference.addTextChangedListener(watcher);

        v.findViewById(R.id.transfer_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //total money of the account in the 'From' spinner
                float from = Float.parseFloat(d.accounts.get(makeaTransferSpinnerAccountFrom.getSelectedItemPosition()).get("total_money"));
                float fromOverdraft = Float.parseFloat(d.accounts.get(makeaTransferSpinnerAccountFrom.getSelectedItemPosition()).get("overdraft"));
                //total money of the account in the 'To' spinner
                float to = Float.parseFloat(d.accounts.get(MakeaTransferSpinnerAccountTo.getSelectedItemPosition()).get("total_money"));
                //the amount to transfer
                float transferAmount = Float.parseFloat(amount.getText().toString());

                //checks to make sure that the current money + overdraft do not go less than 0 before opening confirmation box
                if((from + fromOverdraft) - transferAmount > 0) {

                    HomeTransferConfirm fragment = new HomeTransferConfirm();

                    Bundle args = new Bundle();
                    args.putString("spinnerAccountFrom", makeaTransferSpinnerAccountFrom.getSelectedItem().toString());
                    fragment.setArguments(args);

                    args.putString("spinnerAccountTo", MakeaTransferSpinnerAccountTo.getSelectedItem().toString());
                    fragment.setArguments(args);

                    args.putString("spinnerAccountAmount", amount.getText().toString());
                    fragment.setArguments(args);

                    args.putString("spinnerAccountReference", reference.getText().toString());
                    fragment.setArguments(args);

                    //sets the account number 'From'
                    args.putString("from", d.accounts.get(makeaTransferSpinnerAccountFrom.getSelectedItemPosition()).get("account_number"));
                    fragment.setArguments(args);
                    //sets the money 'From'
                    args.putString("from_money", Float.toString(from));
                    fragment.setArguments(args);
                    //sets the account number 'To'
                    args.putString("to", d.accounts.get(MakeaTransferSpinnerAccountTo.getSelectedItemPosition()).get("account_number"));
                    fragment.setArguments(args);
                    //sets the money 'To'
                    args.putString("to_money", Float.toString(to));
                    fragment.setArguments(args);
                    //the transfer amount
                    args.putString("transferAmount", Float.toString(transferAmount));
                    fragment.setArguments(args);

                    fragment.show(getFragmentManager(), "make a transfer dialog");

                }
                else {
                    //creates an alert dialog
                    AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                    alertDialog.setTitle("Not Enough Money");
                    alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
                    alertDialog.show();
                }
            }
        });

        return v;
    }
    /**
     * method for testing purpose to see what the user has selected from the drop down list
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

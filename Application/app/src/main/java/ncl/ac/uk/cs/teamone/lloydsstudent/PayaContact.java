package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.graphics.Color;
import android.os.Bundle;
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

import java.util.ArrayList;

/**
 * Created by Jomin on 12/03/2015.
 */
public class PayaContact extends Fragment {

    Spinner spinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
    // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.paya_contact, container, false);

        ArrayList<String> list = new ArrayList<String>();
        Data d = new Data();

        for(int i = 0; i < d.accounts.size(); i++) {
            list.add(d.accounts.get(i).get("type_of_account"));
        }

        spinner = (Spinner) v.findViewById(R.id.payacontacts_pinner);
        //initialize adapter with the contents of the list
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, list);
        //set the adapt and prompt
        spinner.setAdapter(adapter);
        spinner.setPrompt("Select an account");

        final EditText payacontactphoneNumber = (EditText)v.findViewById(R.id.payacontact_contactphonenumber);
        final EditText payacontactsortCode = (EditText)v.findViewById(R.id.payacontact_sortcodepayacontact);
        final EditText payacontactamount = (EditText)v.findViewById(R.id.payacontact_userInputAmount);
        final EditText payacontactreference = (EditText)v.findViewById(R.id.payacontact_userInputReference);
        final Button payacontact_reviewButton = (Button)v.findViewById(R.id.payacontactReviewButton);

        final TextWatcher watcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (payacontactphoneNumber.getText().toString().matches("[^0]")){
                    payacontactphoneNumber.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(payacontactphoneNumber.length() == 11 && payacontactsortCode.length() == 6 && payacontactamount.length() >=1 && payacontactreference.length() >= 3) {
                    payacontact_reviewButton.setEnabled(true);
                    payacontact_reviewButton.setBackgroundColor(Color.parseColor("#369742"));
                } else  {
                    payacontact_reviewButton.setEnabled(false);
                    payacontact_reviewButton.setBackgroundColor(Color.parseColor("#ffcacaca"));
                }

            }
        };

        payacontactphoneNumber.addTextChangedListener(watcher);
        payacontactsortCode.addTextChangedListener(watcher);
        payacontactamount.addTextChangedListener(watcher);
        payacontactreference.addTextChangedListener(watcher);

        v.findViewById(R.id.payacontactReviewButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PayaContactConfirm payacontactScreen = new PayaContactConfirm();
                Bundle args = new Bundle();

                args.putString("payacontactspinnerAccountFrom", spinner.getSelectedItem().toString());
                payacontactScreen.setArguments(args);

                args.putString("payacontactphonenumber", payacontactphoneNumber.getText().toString());
                payacontactScreen.setArguments(args);

                args.putString("payacontactsortcode", payacontactsortCode.getText().toString());
                payacontactScreen.setArguments(args);

                args.putString("payacontactamount", payacontactamount.getText().toString());
                payacontactScreen.setArguments(args);

                args.putString("payacontactreference", payacontactreference.getText().toString());
                payacontactScreen.setArguments(args);

                payacontactScreen.show(getFragmentManager(), "make a transfer dialog");
            }
        });
        return v;
    }
}
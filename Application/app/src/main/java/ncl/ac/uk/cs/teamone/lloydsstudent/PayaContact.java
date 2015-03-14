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
import android.widget.Toast;

/**
 * Created by Jomin on 12/03/2015.
 */
public class PayaContact extends Fragment {

    Spinner spinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
    // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.paya_contact, container, false);


        spinner = (Spinner)v.findViewById(R.id.spinner);
        final ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.accounts,R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_items);
        spinner.setAdapter(adapter);
        spinner.setPrompt("Select an account");

        final EditText phoneNumber = (EditText)v.findViewById(R.id.contactphonenumber);
        final EditText sortCode = (EditText)v.findViewById(R.id.sortcodepayacontact);
        final EditText amount = (EditText)v.findViewById(R.id.userInputAmount);
        final EditText reference = (EditText)v.findViewById(R.id.userInputReference);
        final Button reviewButton = (Button)v.findViewById(R.id.maketransferReviewButton);

        final TextWatcher watcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (phoneNumber.getText().toString().matches("[^0]")){
                    phoneNumber.setText("");
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

                if(phoneNumber.length() == 11 && sortCode.length() == 6 && amount.length() >1 && reference.length() >= 5) {
                    reviewButton.setEnabled(true);
                    reviewButton.setBackgroundColor(Color.parseColor("#369742"));
                } else  {
                    reviewButton.setEnabled(false);
                    reviewButton.setBackgroundColor(Color.parseColor("#ffcacaca"));
                }



                if(sortCode.length() == 6) {
                    String tmp = sortCode.getText().toString().replaceAll("([0-9]{2})([0-9]{2})([0-9]{2})", "$1-$2-$3");
                    sortCode.setText(tmp);
                    reviewButton.setEnabled(true);
                    reviewButton.setBackgroundColor(Color.parseColor("#369742"));
                }else  {
                    reviewButton.setEnabled(false);
                    reviewButton.setBackgroundColor(Color.parseColor("#ffcacaca"));
                }



                if(amount.length() >=1){
                    reviewButton.setEnabled(true);
                    reviewButton.setBackgroundColor(Color.parseColor("#369742"));
                } else  {
                    reviewButton.setEnabled(false);
                    reviewButton.setBackgroundColor(Color.parseColor("#ffcacaca"));
                }



                if(reference.length() >=5 ){
                    reviewButton.setEnabled(true);
                    reviewButton.setBackgroundColor(Color.parseColor("#369742"));
                } else  {
                    reviewButton.setEnabled(false);
                    reviewButton.setBackgroundColor(Color.parseColor("#ffcacaca"));
                }
            }
        };

        phoneNumber.addTextChangedListener(watcher);
        sortCode.addTextChangedListener(watcher);
        amount.addTextChangedListener(watcher);
        reference.addTextChangedListener(watcher);


        v.findViewById(R.id.maketransferReviewButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Review button is clicked", Toast.LENGTH_SHORT).show();
            }
        });


        return v;
    }
}

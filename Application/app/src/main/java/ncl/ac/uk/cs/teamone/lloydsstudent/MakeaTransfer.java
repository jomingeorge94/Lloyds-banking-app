package ncl.ac.uk.cs.teamone.lloydsstudent;

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

/**
 * Created by Jomin on 07/03/2015.
 */
public class MakeaTransfer extends Fragment implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.makea_transfer, container, false);


        spinner = (Spinner)v.findViewById(R.id.spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.accounts,R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_items);
        spinner.setAdapter(adapter);
        spinner.setPrompt("Select an account");

        spinner = (Spinner)v.findViewById(R.id.spinner2);
        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.accounts,R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_items);
        spinner.setAdapter(adapter2);
        spinner.setPrompt("Select an account");

        final EditText amount = (EditText)v.findViewById(R.id.userInputAmount);
        final EditText reference = (EditText)v.findViewById(R.id.userInputReference);
        final Button reviewButton = (Button)v.findViewById(R.id.maketransferReviewButton);


        final TextWatcher watcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(amount.length() >=1 && reference.length() >=5){
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

        v.findViewById(R.id.maketransferReviewButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MakeaTransferConfirm makeaTransferConfirm = new MakeaTransferConfirm();
                makeaTransferConfirm.show(getFragmentManager(), "Horse");
            }
        });

        return v;
    }

    /**
     * method for testing purpose to see what the user has selected from the drop down list
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {




       /* TextView mytext = (TextView) view;
        Toast.makeText(getActivity(), "You have selected" +mytext.getText(), Toast.LENGTH_SHORT).show();*/
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

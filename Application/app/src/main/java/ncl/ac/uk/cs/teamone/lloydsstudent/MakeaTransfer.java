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

    Spinner makeaTransferSpinnerAccountFrom;
    Spinner MakeaTransferSpinnerAccountTo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.makea_transfer, container, false);


        makeaTransferSpinnerAccountFrom = (Spinner)v.findViewById(R.id.spinnermakeaTransferAccountFrom);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.accounts,R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_items);
        makeaTransferSpinnerAccountFrom.setAdapter(adapter);
        makeaTransferSpinnerAccountFrom.setPrompt("Select an account");

        MakeaTransferSpinnerAccountTo = (Spinner)v.findViewById(R.id.spinnermakeaTransferAccountTo);
        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.accounts,R.layout.spinner_item);
        adapter2.setDropDownViewResource(R.layout.spinner_dropdown_items);
        MakeaTransferSpinnerAccountTo.setAdapter(adapter2);
        MakeaTransferSpinnerAccountTo.setPrompt("Select an account");

        final EditText amount = (EditText)v.findViewById(R.id.spinnermakeaTransferAccountamount);
        final EditText reference = (EditText)v.findViewById(R.id.spinnermakeaTransferAccountreference);
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

                MakeaTransferConfirm fragment = new MakeaTransferConfirm();

                Bundle args = new Bundle();
                args.putString("spinnerAccountFrom", makeaTransferSpinnerAccountFrom.getSelectedItem().toString());
                fragment.setArguments(args);

                args.putString("spinnerAccountTo", MakeaTransferSpinnerAccountTo.getSelectedItem().toString());
                fragment.setArguments(args);

                args.putString("spinnerAccountAmount", amount.getText().toString());
                fragment.setArguments(args);

                args.putString("spinnerAccountReference", reference.getText().toString());
                fragment.setArguments(args);

                fragment.show(getFragmentManager(), "make a transfer dialog");

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

package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
        //spinner.setOnItemSelectedListener(this);

        spinner = (Spinner)v.findViewById(R.id.spinner2);
        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.accounts,R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_items);
        spinner.setAdapter(adapter2);
        spinner.setPrompt("Select an account");







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

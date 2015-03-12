package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.regex.Pattern;

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
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.accounts,R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_items);
        spinner.setAdapter(adapter);
        spinner.setPrompt("Select an account");

        Pattern p = Pattern.compile("Hello, (\\S+)");


        return v;
    }
}

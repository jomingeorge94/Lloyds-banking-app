package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Arrays;

import javax.mail.Quota;

/**
 * Created by Dan on 19/03/2015.
 */
public class BudgetCashEntry extends Fragment {

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

        final EditText name = (EditText) v.findViewById(R.id.cash_name_input);
        final Spinner category = (Spinner) v.findViewById(R.id.cash_category_input);
        final EditText purchase = (EditText) v.findViewById(R.id.cash_purchase_input);

        final Button add = (Button) v.findViewById(R.id.cash_add_button);

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

        return v;
    }

}
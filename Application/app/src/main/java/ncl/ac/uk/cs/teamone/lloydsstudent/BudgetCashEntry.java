package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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

        Spinner spinner = (Spinner) v.findViewById(R.id.cash_category_spinner);
        Resources res = getResources();
        ArrayAdapter adapter = new ArrayAdapter<>(getActivity(), R.layout.cash_entry_dropdown, res.getStringArray(R.array.cash_categories) );
        adapter.setDropDownViewResource(R.layout.cash_entry_item);
        spinner.setAdapter(adapter);
        spinner.setPrompt("Select an account");

        return v;
    }

}
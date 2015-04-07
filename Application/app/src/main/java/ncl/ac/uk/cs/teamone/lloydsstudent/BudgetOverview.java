package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.widget.RelativeLayout;

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

/**
 * Created by Dan on 19/03/2015.
 */
public class BudgetOverview extends Fragment {

    //method to switch the fragment, this method will switch the fragment to the budget layout xml file
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.budget_overview, container, false);

        ArrayList<String> XVAL = new ArrayList<>(Arrays.asList("Last Week", "This Week"));

        BarChart chart = (BarChart) v.findViewById(R.id.bar_chart);
        ArrayList<BarEntry> values = new ArrayList<BarEntry>();

        values.add(new BarEntry(69.23f, 0));
        values.add(new BarEntry(41.88f, 1));

        BarDataSet data = new BarDataSet(values, "Spending");

        ArrayList<BarDataSet> sets = new ArrayList<BarDataSet>();
        sets.add(data);

        BarData chartData = new BarData(XVAL, sets);
        chartData.setValueFormatter(new CurrencyFormatter());

        chart.setData(chartData);
        chart.setDescription(null);
        chart.setDrawGridBackground(false);
        chart.setPinchZoom(false);
        chart.setDoubleTapToZoomEnabled(false);
        chart.animateXY(1000, 1000);

        XAxis xAxis = chart.getXAxis();
        xAxis.setSpaceBetweenLabels(1);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(true);
        xAxis.setDrawAxisLine(true);

        YAxis yLeft = chart.getAxisLeft();
        YAxis yRight = chart.getAxisRight();

        yLeft.setEnabled(true);
        yLeft.setValueFormatter(new NoDecimalFormatter());

        yRight.setEnabled(true);
        yRight.setValueFormatter(new NoDecimalFormatter());

        Legend legend = chart.getLegend();
        legend.setEnabled(false);

        chart.invalidate();

        Button cash = (Button) v.findViewById(R.id.budget_enter_button);
        // Create button listner
        cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return v;
    }

    }
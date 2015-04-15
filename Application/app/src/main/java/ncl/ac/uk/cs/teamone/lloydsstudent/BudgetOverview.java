package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.v4.app.FragmentManager;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Fragment that generates the view for the overview graph. It generates the layout in the onCreate
 * method and styles the data and graph
 *
 * Created by Dan on 19/03/2015.
 */
public class BudgetOverview extends Fragment {

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        // Creates the view for the fragment
        View v = inflater.inflate(R.layout.budget_overview, container, false);

        // Creates an array to store the raw X-Axis data
        ArrayList<String> XVAL = new ArrayList<>(Arrays.asList("Last Week", "This Week"));

        // Creates a BarChart from the XML file
        BarChart chart = (BarChart) v.findViewById(R.id.bar_chart);
        // Creates an ArrayList to store the values for the chart data
        ArrayList<BarEntry> values = new ArrayList<BarEntry>();

        // Stores the received data
        Data d = new Data();

        // Add the received data to the values array
        //last week
        values.add(new BarEntry(Float.parseFloat(d.previousBudget.get("spend")), 0));
        //this week
        values.add(new BarEntry(Float.parseFloat(d.budget.get("spend")), 1));

        // Creates a BarDataSet to store the data array
        BarDataSet data = new BarDataSet(values, "Spending");

        // Adds the BarDataSets to a newly created ArrayList which can be applied to the chart
        ArrayList<BarDataSet> sets = new ArrayList<BarDataSet>();
        sets.add(data);

        // Generates the final ChartData from the X and Y values to be applied to the chart
        BarData chartData = new BarData(XVAL, sets);
        // Formats the ChartData as currency to 2 decimal places suing the CurrencyFormatter class
        chartData.setValueFormatter(new CurrencyFormatter());

        // Applies the data to the chart
        chart.setData(chartData);
        // Prevents the chart description from being drawn
        chart.setDescription(null);
        // Prevents the grid lines from being drawn
        chart.setDrawGridBackground(false);
        // Disables zooming on the graph
        chart.setDoubleTapToZoomEnabled(false);
        chart.setPinchZoom(false);
        // Animates the X and Y axis of the graph over a one second period
        chart.animateXY(1000, 1000);

        // Generates an X-Axis object from the graph
        XAxis xAxis = chart.getXAxis();
        // Sets the frequency of the X labels to be drawn
        xAxis.setSpaceBetweenLabels(1);
        // Prevents the grid lines on the X axis being drawn
        xAxis.setDrawGridLines(false);
        // Draws the labels for X axis
        xAxis.setDrawLabels(true);
        // Draws the line of the X axis
        xAxis.setDrawAxisLine(true);

        // Generates two Y-Axis objects from the graph
        YAxis yLeft = chart.getAxisLeft();
        YAxis yRight = chart.getAxisRight();

        // Enables the Y axis
        yLeft.setEnabled(true);
        // Applies a value formatter to draw the label as whol pound values
        yLeft.setValueFormatter(new NoDecimalFormatter());

        // Enables the Y axis
        yRight.setEnabled(true);
        // Applies a value formatter to draw the label as whol pound values
        yRight.setValueFormatter(new NoDecimalFormatter());

        // Generates a Legend form the chart
        Legend legend = chart.getLegend();
        // Draws the legend
        legend.setEnabled(false);

        // Calls the invalidate method to redraw chart
        chart.invalidate();

        // Creates a fragment manager to manage the fragment transitions
        FragmentManager fm = getFragmentManager();

        // Creates the transition which opens the Dialog view
        final FragmentTransaction ft = fm.beginTransaction();

        // Sets the previous fragment
        Fragment prev = this;
        if (prev != null) {
            // Removes the previous fragment from the manager variable
            ft.remove(prev);
        }
        // Adds tge fragment to the back of the stack so it can be called back
        ft.addToBackStack(null);

        // Generates the Button object from the XML file
        Button cash = (Button) v.findViewById(R.id.budget_enter_button);

        // Creates a Listener to take the user to the cash entry dialog
        cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creates a new DialogFragment variable and assigns the Cash Entry dialog to it
                DialogFragment cashEntry = new BudgetCashEntry();
                // Show the new dialog
                cashEntry.show(ft, "Add Cash Entry");
            }
        });

        Button settings = (Button) v.findViewById(R.id.budget_settings_button);

        // Creates a Listener and adds it to the settings page
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getActivity(), BudgetSettings.class);
                startActivity(nextScreen);
            }
        });

        // Returns the fragment View
        return v;

    }

}
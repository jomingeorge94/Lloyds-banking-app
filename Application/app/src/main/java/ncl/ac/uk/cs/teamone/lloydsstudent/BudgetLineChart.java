package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Fragment that generates the view and data for the Line Chart fragment. All the GUI creation is
 * done in the onCreateView method, all of the data is assigned to the chart defined in XML and the
 * appearance changed programmatically
 *
 * Created by Dan on 19/03/2015.
 */
public class BudgetLineChart extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.budget_line_chart, container, false);

        // Create LineChart object which is created from the XML file
        LineChart chart = (LineChart) v.findViewById(R.id.line_chart);

        // Create variables to store chart data
        ArrayList<Entry> valuesCurrent = new ArrayList<Entry>();
        ArrayList<Entry> valuesPrevious = new ArrayList<Entry>();

        // Creates a final array of the days of the week for the X-axis of the chart
        final ArrayList<String> DAYSOFWEEK = new ArrayList<>(Arrays.asList("Mon", "Tue", "Wed",
                                                                           "Thu", "Fri", "Sat",
                                                                           "Sun"));

        Data d = new Data();

        // Defines a float for each day and assigns a value
        float monday = Float.parseFloat(d.budget.get("monday_spend"));
        float tuesday = Float.parseFloat(d.budget.get("tuesday_spend"));
        float wednesday = Float.parseFloat(d.budget.get("wednesday_spend"));
        float thursday = Float.parseFloat(d.budget.get("thursday_spend"));
        float friday = Float.parseFloat(d.budget.get("friday_spend"));
        float saturday = Float.parseFloat(d.budget.get("saturday_spend"));
        float sunday = Float.parseFloat(d.budget.get("sunday_spend"));

        // Fills the arrays witht he raw data
        float[] currentData = new float[]{monday, tuesday, wednesday, thursday, friday, saturday, sunday};
        float[] previousData = new float[]{12.1f, 7.02f, 5.11f, 1.27f, 8.56f, 4.69f, 2.45f};

        // Converts raw data to Entry objects for first set of data
        for(int i = 0; i < currentData.length; i++) {
            valuesCurrent.add(new Entry(currentData[i], i));
        }
        // Converts raw data to Entry objects for second set of data
        for(int i = 0; i < previousData.length; i++) {
            valuesPrevious.add(new Entry(previousData[i], i));
        }

        // Generates a LineDataSet which can be applied to the chart
        LineDataSet current = new LineDataSet(valuesCurrent, "Current Week");
        // Sets a value formatter to format the chart labels
        current.setValueFormatter(new CurrencyFormatter());
        // Sets the text size of the label
        current.setValueTextSize(10f);
        // Sets the colour for the line
        current.setColor(getResources().getColor(R.color.dark_green));
        // Sets the colour for the data point circle
        current.setCircleColor(getResources().getColor(R.color.dark_green));
        // Sets the colour for the data point inner circle
        current.setCircleColorHole(getResources().getColor(R.color.dark_green));

        // Generates a LineDataSet which can be applied to the chart
        LineDataSet previous = new LineDataSet(valuesPrevious, "Previous Week");
        // Sets a value formatter to format the chart labels
        previous.setValueFormatter(new CurrencyFormatter());
        // Sets the text size of the label
        previous.setValueTextSize(10f);
        // Sets the colour for the line
        previous.setColor(getResources().getColor(R.color.leisure));
        // Sets the colour for the data point circle
        previous.setCircleColor(getResources().getColor(R.color.leisure));
        // Sets the colour for the data point inner circle
        previous.setCircleColorHole(getResources().getColor(R.color.leisure));

        // Creates a set of LineData which include both this and the previous weeks data
        ArrayList<LineDataSet> sets = new ArrayList<>();
        // Add the data to the sets
        sets.add(current);
        sets.add(previous);

        // Creates a LineData object which combines the chart data and the X-Axis data
        LineData chartData = new LineData(DAYSOFWEEK, sets);
        // Removes the chart description
        chart.setDescription(null);
        // Prevents the chart from being scrolled
        chart.setDoubleTapToZoomEnabled(false);
        chart.setPinchZoom(false);
        // Stops the solid border being drawn around the chart
        chart.setDrawBorders(false);
        // Applies the chart data to the chart
        chart.setData(chartData);
        // Animates the X and the Y axis for 1 second when the chart is created
        chart.animateXY(1000, 1000);

        // Creates a X-Axis object from the chart object which controls the layout of the X-Axis
        XAxis xAxis = chart.getXAxis();
        // Sets the frequency of the X-Axis labels to be drawn
        xAxis.setSpaceBetweenLabels(1);
        // Prevents the grid lines from being drawn
        xAxis.setDrawGridLines(false);
        // Enables the drawing of the X-Axis labels
        xAxis.setDrawLabels(true);
        // Draws the axis line
        xAxis.setDrawAxisLine(true);

        // Creates two Y-Axis objects, one for each side of the graph
        YAxis yLeft = chart.getAxisLeft();
        YAxis yRight = chart.getAxisRight();

        // Enables the axis
        yLeft.setEnabled(true);
        // Sets a formatter to display the labels as whole pounds
        yLeft.setValueFormatter(new NoDecimalFormatter());

        // Enables the axis
        yRight.setEnabled(true);
        // Sets a formatter to display the labels as whole pounds
        yRight.setValueFormatter(new NoDecimalFormatter());

        // Creates the label from the chart Object
        Legend legend = chart.getLegend();
        // Enables the legend
        legend.setEnabled(true);

        // Calls the invalidate method which refreshes the chart view
        chart.invalidate();

        // Returns the view
        return v;

    }

}

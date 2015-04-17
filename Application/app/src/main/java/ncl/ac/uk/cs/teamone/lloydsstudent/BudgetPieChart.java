package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.PercentFormatter;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Fragment that generates the Pie chart for the chart ViewPager. All of the GUI is styled and
 * generated, the charts are dynamically filled in the fragment from arrays
 *
 * Created by Dan on 19/03/2015.
 */
public class BudgetPieChart extends Fragment {

    // Creates an array of categories to be used as Y-Axis values
    private ArrayList<String> CATEGORIES = new ArrayList<>(Arrays.asList("Food", "Travel",
                                                                         "Beauty & Hygiene",
                                                                         "Entertainment", "Home",
                                                                         "Clothes", "Leisure",
                                                                         "Other"));
    // Creates and array to hold the data
    private ArrayList<Entry> spend = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Creates a variable to initialise the view for the fragment
        View v = inflater.inflate(R.layout.budget_pie_chart, container, false);

        // Creates a variable to store the data received
        Data d = new Data();

        // Defines a float for each category and assigns a value
        float food = Float.parseFloat(d.budget.get("groceries_spend")) + Float.parseFloat(d.budget.get("eating_out_spend"));
        float travel = Float.parseFloat(d.budget.get("travel_spend"));
        float beauty = Float.parseFloat(d.budget.get("beauty_and_hygiene_spend"));
        float entertainment = Float.parseFloat(d.budget.get("entertainment_spend"));
        float home = Float.parseFloat(d.budget.get("home_spend"));
        float clothes = Float.parseFloat(d.budget.get("clothes_spend"));
        float leisure = Float.parseFloat(d.budget.get("leisurely_activities_spend"));
        float other = Float.parseFloat(d.budget.get("other_spend"));

        if(!mutex){
            // Calls the format method to put the data in the correct format for the chart
            formatData(new float[]{food, travel, beauty, entertainment, home, clothes, leisure, other});
            mutex = true;
        }


        // Calls the create chart method to generate the chart in the view
        createChart(v);

        // Returns the view
        return v;

    }

    /**
     * Method that fills the spend array with newly generated Entry Objects ready to add toa  chart
     *
     * @param data an array of 8 floats to be added to the chart
     */
    public void formatData(float[] data) {
        // Loops through the array of 8 floats
        for(int i = 0; i < 8; i++) {
            // Adds and generates Entry Objects to the spend array
            spend.add(new Entry(data[i], i));
        }
    }

    private static boolean mutex = false;
    PieDataSet pie = null;

    /**
     * Method which takes a view and searches for a PieChart holder and formats the data in the
     * fragment and generates a visual chart
     *
     * @param v the view where the Pie Chart holder is
     */
    public void createChart(View v) {

        // Searches the XML file for the PieChart holder and assigns it to a variable
        PieChart chart = (PieChart) v.findViewById(R.id.pie_chart);

        pie = formatData();


        // Creates a PieData variable which can be added to a PieChart
        PieData data = new PieData(CATEGORIES, pie);

        // Assigns the values to the charts
        chart.setData(data);
        // Animates the X and Y axis of the chart
        chart.animateXY(1000, 1000);
        // Makes the center of the pie chart transparent
        chart.setHoleColorTransparent(true);
        // Displays the label for each sector as a percentage
        chart.setUsePercentValues(true);
        // Draws the X axis lables for each segment
        chart.setDrawSliceText(true);
        // Disables the chart description
        chart.setDescription(null);

        // Gets the legend from the chart and assigns it to a variable
        Legend legend = chart.getLegend();
        // Prevents the legend from being drawn
        legend.setEnabled(false);

    }

    /**
     * Takes the partially formatted data stored in the fragment and adds formatting to the
     * entries. It then returns a PieDataSet object which can be added to a PieChart
     *
     * @return a PieChartData set which can be added to a PieChart
     */
    public PieDataSet formatData() {

        // Creates and label's a set of data ready to be added to a chart
        PieDataSet weeklyData = new PieDataSet(spend, "Pie Data");

        // Sets the colour of the labels
        weeklyData.setValueTextColor(getResources().getColor(R.color.white));
        // Formats the labels as percentage values
        weeklyData.setValueFormatter(new PercentFormatter());

        // Assignes each category a colour
        weeklyData.setColors(new int[] {getResources().getColor(R.color.food),
                getResources().getColor(R.color.travel),
                getResources().getColor(R.color.beauty),
                getResources().getColor(R.color.entertainment),
                getResources().getColor(R.color.home),
                getResources().getColor(R.color.clothes),
                getResources().getColor(R.color.leisure),
                getResources().getColor(R.color.other)});


        // Returns the data ready to be added to the chart
        return weeklyData;

    }

}

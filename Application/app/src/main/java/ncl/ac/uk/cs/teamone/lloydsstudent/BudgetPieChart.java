package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.support.v4.app.Fragment;
import android.os.Bundle;
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

/**
 * Created by Dan on 19/03/2015.
 */
public class BudgetPieChart extends Fragment {

    //method to switch the fragment, this method will switch the fragment to the budget layout xml file
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.budget_pie_chart, container, false);



        ArrayList<String> categoryNames = new ArrayList<>();
        categoryNames.add("Food");
        categoryNames.add("Travel");
        categoryNames.add("Beauty & Hygiene");
        categoryNames.add("Entertainment");
        categoryNames.add("Home");
        categoryNames.add("Clothes");
        categoryNames.add("Leisure");
        categoryNames.add("Other");

        ArrayList<Entry> categorySpend = new ArrayList<>();

        Entry food = new Entry(15.99f, 0);
        categorySpend.add(food);

        Entry travel = new Entry(3.5f, 1);
        categorySpend.add(travel);

        Entry beauty = new Entry(15.99f, 2);
        categorySpend.add(beauty);

        Entry entertainment = new Entry(3.99f, 3);
        categorySpend.add(entertainment);

        Entry home = new Entry(1.99f, 4);
        categorySpend.add(home);

        Entry clothes = new Entry(21.5f, 5);
        categorySpend.add(clothes);

        Entry leisure = new Entry(13.47f, 6);
        categorySpend.add(leisure);

        Entry other = new Entry(12.12f, 7);
        categorySpend.add(other);

        PieDataSet weeklyData = new PieDataSet(categorySpend, "Pie Data");

        PieData weeklySpend = new PieData(categoryNames, weeklyData);

        PieChart chart = (PieChart) v.findViewById(R.id.pieChart);




        chart.animateXY(1000, 1000);
        chart.setData(weeklySpend);
        chart.setHoleColorTransparent(true);
        chart.setUsePercentValues(true);
        chart.setDrawSliceText(false);
        chart.setDescription(null);

        weeklyData.setColors(new int[] {getResources().getColor(R.color.food),
                getResources().getColor(R.color.travel),
                getResources().getColor(R.color.beauty),
                getResources().getColor(R.color.entertainment),
                getResources().getColor(R.color.home),
                getResources().getColor(R.color.clothes),
                getResources().getColor(R.color.leisure),
                getResources().getColor(R.color.other)});
        weeklyData.setValueTextColor(getResources().getColor(R.color.white));
        weeklyData.setValueFormatter(new PercentFormatter());

        Legend pieLegend = chart.getLegend();
        pieLegend.setEnabled(false);

        return v;
    }

}

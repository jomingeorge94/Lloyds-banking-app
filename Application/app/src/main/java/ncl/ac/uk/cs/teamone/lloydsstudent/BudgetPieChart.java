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
import java.util.Arrays;

/**
 * Created by Dan on 19/03/2015.
 */
public class BudgetPieChart extends Fragment {

    private ArrayList<String> CATEGORIES = new ArrayList<>(Arrays.asList("Food",
                                                                         "Travel",
                                                                         "Beauty & Hygiene",
                                                                         "Entertainment",
                                                                         "Home",
                                                                         "Clothes",
                                                                         "Leisure",
                                                                         "Other"));
    private ArrayList<Entry> spend = new ArrayList<>();

    //method to switch the fragment, this method will switch the fragment to the budget layout xml file
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.budget_pie_chart, container, false);

        Data d = new Data();

        float food = Float.parseFloat(d.budget.get("groceries_spend")) + Float.parseFloat(d.budget.get("eating_out_spend"));
        float travel = Float.parseFloat(d.budget.get("travel_spend"));
        float beauty = Float.parseFloat(d.budget.get("beauty_and_hygiene_spend"));
        float entertainment = Float.parseFloat(d.budget.get("entertainment_spend"));
        float home = Float.parseFloat(d.budget.get("home_spend"));
        float clothes = Float.parseFloat(d.budget.get("clothes_spend"));
        float leisure = Float.parseFloat(d.budget.get("leisurely_activities_spend"));
        float other = Float.parseFloat(d.budget.get("other_spend"));

        formatData(new float[]{food, travel, beauty, entertainment, home, clothes, leisure, other});
        createChart(v);
        return v;
    }

    public void formatData(float[] data) {
        for(int i = 0; i < 8; i++) {
            spend.add(new Entry(data[i], i));
        }
    }

    public void createChart(View v) {
        PieChart chart = (PieChart) v.findViewById(R.id.pie_chart);

        PieData data = new PieData(CATEGORIES, formatData());

        chart.setData(data);
        chart.animateXY(1000, 1000);
        chart.setHoleColorTransparent(true);
        chart.setUsePercentValues(true);
        chart.setDrawSliceText(true);
        chart.setDescription(null);

        Legend legend = chart.getLegend();

        legend.setEnabled(false);
    }

    public PieDataSet formatData() {
        PieDataSet weeklyData = new PieDataSet(spend, "Pie Data");

        weeklyData.setValueTextColor(getResources().getColor(R.color.white));
        weeklyData.setValueFormatter(new PercentFormatter());

        weeklyData.setColors(new int[] {getResources().getColor(R.color.food),
                getResources().getColor(R.color.travel),
                getResources().getColor(R.color.beauty),
                getResources().getColor(R.color.entertainment),
                getResources().getColor(R.color.home),
                getResources().getColor(R.color.clothes),
                getResources().getColor(R.color.leisure),
                getResources().getColor(R.color.other)});

        return weeklyData;
    }

}

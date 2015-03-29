package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.PercentFormatter;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Dan on 19/03/2015.
 */
public class BudgetLineChart extends Fragment {

    //method to switch the fragment, this method will switch the fragment to the budget layout xml file
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.budget_line_chart, container, false);

        LineChart chart = (LineChart) v.findViewById(R.id.line_chart);
        ArrayList<Entry> valuesCurrent = new ArrayList<Entry>();
        ArrayList<Entry> valuesPrevious = new ArrayList<Entry>();
        ArrayList<String> DAYSOFWEEK = new ArrayList<>(Arrays.asList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"));

        float[] currentData = new float[]{8.56f, 4.69f, 2.45f};
        float[] previousData = new float[]{12.1f, 7.02f, 5.11f, 1.27f, 8.56f, 4.69f, 2.45f};

        for(int i = 0; i < currentData.length; i++) {
            valuesCurrent.add(new Entry(currentData[i], i));
        }

        for(int i = 0; i < previousData.length; i++) {
            valuesPrevious.add(new Entry(previousData[i], i));
        }

        LineDataSet current = new LineDataSet(valuesCurrent, "Current Week");
        current.setValueFormatter(new CurrencyFormatter());
        current.setValueTextSize(10f);
        current.setColor(getResources().getColor(R.color.dark_green));
        current.setCircleColor(getResources().getColor(R.color.dark_green));
        current.setCircleColorHole(getResources().getColor(R.color.dark_green));

        LineDataSet previous = new LineDataSet(valuesPrevious, "Previous Week");
        previous.setValueFormatter(new CurrencyFormatter());
        previous.setValueTextSize(10f);
        previous.setColor(getResources().getColor(R.color.leisure));
        previous.setCircleColor(getResources().getColor(R.color.leisure));
        previous.setCircleColorHole(getResources().getColor(R.color.leisure));


        ArrayList<LineDataSet> sets = new ArrayList<LineDataSet>();
        sets.add(current);
        sets.add(previous);

        LineData chartData = new LineData(DAYSOFWEEK, sets);
        chart.setData(chartData);
        chart.setDescription(null);
        chart.setDoubleTapToZoomEnabled(false);
        chart.setDrawBorders(false);
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
        //legend.setEnabled(false);

        chart.invalidate();

        return v;
    }

}

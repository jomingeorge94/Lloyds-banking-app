package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

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
public class BudgetBarChart extends Fragment {

    //method to switch the fragment, this method will switch the fragment to the budget layout xml file
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.budget_bar_chart, container, false);

        return v;
    }

    }
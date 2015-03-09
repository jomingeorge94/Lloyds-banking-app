package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by Jomin on 26/02/2015.
 */
public class BudgetFragment extends Fragment {

    private View v;
    private TextView total;
    private ProgressBar summary;
    private TextView rightLabel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.budget_test, container, false);
        total = (TextView) v.findViewById(R.id.budget_total_budget);
        summary = (ProgressBar) v.findViewById(R.id.summary_bar);
        rightLabel = (TextView) v.findViewById(R.id.right_label);

        budgetUpdate(88, 62.18);

        return v;
    }

    public void budgetUpdate(double budget, double spent) {

        total.setText("£" + String.format("%.2f", budget));
        rightLabel.setText("£" + String.format("%.2f", budget));

        summary.setMax((int) budget);
        summary.setProgress((int) spent);
    }



}


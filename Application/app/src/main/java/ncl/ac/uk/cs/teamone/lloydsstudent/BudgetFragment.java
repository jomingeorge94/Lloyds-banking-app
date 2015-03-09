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

/**
 * Created by Jomin on 26/02/2015.
 */
public class BudgetFragment extends Fragment {

    private View v;
    private TextView total;
    private ProgressBar summary;
    private TextView rightLabel;
    private Resources res;
    private Rect bounds;
    private TextView remaining;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.budget_test, container, false);
        total = (TextView) v.findViewById(R.id.budget_total_budget);
        summary = (ProgressBar) v.findViewById(R.id.summary_bar);
        rightLabel = (TextView) v.findViewById(R.id.right_label);
        remaining = (TextView) v.findViewById(R.id.budget_remaining);

        budgetUpdate(88, 44.11);

        return v;
    }

    public void budgetUpdate(double budget, double spent) {

        total.setText("£" + String.format("%.2f", budget));
        rightLabel.setText("£" + String.format("%.2f", budget));
        remaining.setText("£" + String.format("%.2f", budget - spent));

        summary.setMax((int) budget);
        if(spent <= budget) {
            summary.setProgress((int) spent);
        } else {
            summary.setProgress(100);
        }

        res = getResources();
        bounds = summary.getProgressDrawable().getBounds();

        if(spent >= budget) {
            summary.setProgressDrawable(res.getDrawable(R.drawable.progress_over));
            remaining.setTextColor(getResources().getColor(R.color.spend_over));
        }
        else if (spent >= budget * 0.9) {
            summary.setProgressDrawable(res.getDrawable(R.drawable.progress_close));
            remaining.setTextColor(getResources().getColor(R.color.spend_close));
        }
        else if (spent >= budget * 0.8) {
            summary.setProgressDrawable(res.getDrawable(R.drawable.progress_high));
            remaining.setTextColor(getResources().getColor(R.color.spend_high));
        }
        else if (spent >= budget * 0.5) {
            summary.setProgressDrawable(res.getDrawable(R.drawable.progress_half));
            remaining.setTextColor(getResources().getColor(R.color.spend_half));
        }
        else if (spent >= budget * 0.3) {
            summary.setProgressDrawable(res.getDrawable(R.drawable.progress_low));
            remaining.setTextColor(getResources().getColor(R.color.spend_low));
        }
        else {
            summary.setProgressDrawable(res.getDrawable(R.drawable.progress_normal));
            remaining.setTextColor(getResources().getColor(R.color.dark_green));
        }

        summary.getProgressDrawable().setBounds(bounds);
        summary.setProgress((int) spent);
    }



}


package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Fragment which generates a GUI
 *
 * Created by Jomin on 26/02/2015.
 */
public class BudgetFragment extends Fragment {

    // Variables to be used in the fragment to create the layout
    private View v;
    private TextView total;
    private ProgressBar summary;
    private TextView rightLabel;
    private Resources resource;
    private Rect bounds;
    private TextView remaining;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Create the view for this fragment
        v =  inflater.inflate(R.layout.budget_fragment, container, false);

        // Initialise layout variables from XML file
        total = (TextView) v.findViewById(R.id.budget_total_budget);
        summary = (ProgressBar) v.findViewById(R.id.summary_bar);
        rightLabel = (TextView) v.findViewById(R.id.right_label);
        remaining = (TextView) v.findViewById(R.id.budget_remaining);

        // Variable to hold data received
        Data d = new Data();

        // Update Summary section of the page
        summaryUpdate(Float.parseFloat(d.budget.get("amount")), Float.parseFloat(d.budget.get("spend")));

        // Creates a list of fragments which will be dispalyed in the scrolling pane
        ArrayList<Fragment>  fragmentList = new ArrayList<Fragment>();

        // Add fragments to the list
        fragmentList.add(new BudgetOverview());
        fragmentList.add(new BudgetLineChart());
        fragmentList.add(new BudgetPieChart());

        // Create fragment manager to control popup fragment
        FragmentManager manager = getChildFragmentManager();

        // Create chart adapter to fill ViewPager
        ChartAdapter adapter = new ChartAdapter(manager, fragmentList);

        // Create ViewPager form XML file
        ViewPager page = (ViewPager) v.findViewById(R.id.chart_view);

        // Dictates the number of fragments to keep loaded
        page.setOffscreenPageLimit(1);
        // Sets the start position of the ViewPager
        page.setCurrentItem(1);

        // Add adapter to ViewPager which shows the fragment
        page.setAdapter(adapter);


        // Returns the view
        return v;

    }

    /**
     * A method that updates the GUI summary sections. In order to do this it must know the
     * budget of the user and the amount that has been spent in the current week, once it has
     * these it updates any display elements so the show correctly
     *
     * @param budget the current weekly budget of the user
     * @param spent the amount spent in the current week by the user
     */
    public void summaryUpdate(double budget, double spent) {

        // Update text labels
        total.setText("£" + String.format("%.2f", budget));
        rightLabel.setText("£" + String.format("%.2f", budget));
        remaining.setText("£" + String.format("%.2f", budget - spent));

        // Gets the resource files for the progress bar
        resource = getResources();
        bounds = summary.getProgressDrawable().getBounds();

        // GUI settings if amount spent is over budget
        if(spent >= budget) {
            summary.setProgressDrawable(resource.getDrawable(R.drawable.progress_over));
            remaining.setTextColor(getResources().getColor(R.color.spend_over));
        }
        // GUI settings if amount spent is over 90% of budget
        else if (spent >= budget * 0.9) {
            summary.setProgressDrawable(resource.getDrawable(R.drawable.progress_close));
            remaining.setTextColor(getResources().getColor(R.color.spend_close));
        }
        // GUI settings if amount spent is over 70% of budget
        else if (spent >= budget * 0.7) {
            summary.setProgressDrawable(resource.getDrawable(R.drawable.progress_high));
            remaining.setTextColor(getResources().getColor(R.color.spend_high));
        }
        // GUI settings if amount spent is over 50% of budget
        else if (spent >= budget * 0.5) {
            summary.setProgressDrawable(resource.getDrawable(R.drawable.progress_half));
            remaining.setTextColor(getResources().getColor(R.color.spend_half));
        }
        // GUI settings if amount spent is over 30% of budget
        else if (spent >= budget * 0.3) {
            summary.setProgressDrawable(resource.getDrawable(R.drawable.progress_low));
            remaining.setTextColor(getResources().getColor(R.color.spend_low));
        }
        // GUI settings if amount spent is under 30% of budget
        else {
            summary.setProgressDrawable(resource.getDrawable(R.drawable.progress_normal));
            remaining.setTextColor(getResources().getColor(R.color.dark_green));
        }

        // Sets the range of the progress bar to the weekly budget
        summary.setMax((int) budget);

        // Sets the progress of the bar to the amount spent
        if(spent <= budget) {
            summary.setProgress((int) spent);
            summary.getProgressDrawable().setBounds(bounds);
        }
        // If the amount spent is greater than the budget 100 is set for the progress
        else {
            summary.setProgress(100);
            summary.getProgressDrawable().setBounds(bounds);
        }

    }

}


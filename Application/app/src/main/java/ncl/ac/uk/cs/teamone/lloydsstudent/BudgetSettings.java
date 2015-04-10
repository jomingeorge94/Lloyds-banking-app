package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;


public class BudgetSettings extends ActionBarActivity {

    // Create the variables to store the values and layout components for the fragment
    private float budgetValue;
    private float[] values = new float[8];

    private SeekBar budgetSlider;
    private SeekBar[] sliders = new SeekBar[8];

    private Button discard;
    private Button save;

    private TextView budgetView;
    private TextView lowerView;
    private TextView middleView;
    private TextView upperView;
    private TextView unassignedView;
    private TextView[] views = new TextView[8];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Method call to super
        super.onCreate(savedInstanceState);
        // Sets the layout file to be used in the Fragment
        setContentView(R.layout.activity_budget_settings);
        // Calls the initialiseInterface method to update layout components
        initialiseInterface();

    }

    /**
     * Initialises all of the layout components and applies the default values. When this method is
     * called it is for the initial setup of the budget, when editing the budget a differnet method
     * will be called and filled from the existing data
     */
    private void initialiseInterface() {

        // Assigns the budget to a local value
        budgetValue = 88f;

        // Initialises the SeekBar's as local variables
        budgetSlider = (SeekBar) findViewById(R.id.bs_budget_seek);
        sliders[0] = (SeekBar) findViewById(R.id.bs_food_seek);
        sliders[1] = (SeekBar) findViewById(R.id.bs_travel_seek);
        sliders[2] = (SeekBar) findViewById(R.id.bs_beauty_seek);
        sliders[3] = (SeekBar) findViewById(R.id.bs_entertainment_seek);
        sliders[4] = (SeekBar) findViewById(R.id.bs_home_seek);
        sliders[5] = (SeekBar) findViewById(R.id.bs_clothes_seek);
        sliders[6] = (SeekBar) findViewById(R.id.bs_leisure_seek);
        sliders[7] = (SeekBar) findViewById(R.id.bs_other_seek);

        // Initialises the TextView to display the budget
        budgetView = (TextView) findViewById(R.id.bs_budget_value);
        // Sets the text of the label to the value of the budget
        budgetView.setText(currencyFormatter(budgetValue));

        // Initialises the TextView to display the lower slider bound
        lowerView = (TextView) findViewById(R.id.bs_lower_bound);
        // Sets the text of the lower slider bound by calling a method
        setLowerView();

        // Initialises the TextView to display to the mid value
        middleView = (TextView) findViewById(R.id.bs_mid_bound);
        // Sets the text of the mid bound to the budget value
        middleView.setText(currencyFormatter(budgetValue));

        // Initialises the TextView to display to the upper value
        upperView = (TextView) findViewById(R.id.bs_upper_bound);
        // Sets the text of the upper slider by adding 20 to the budget
        upperView.setText(currencyFormatter(budgetValue + 20));

        // Initialises the TextView to display to the unassigned budget remaining
        unassignedView = (TextView) findViewById(R.id.bs_remaining_value);
        // Calls method to find the remaining unassigned budget
        unassignedView.setText(currencyFormatter(getUnassignedValue()));

        // Initialises all of the text views for the categories
        views[0] = (TextView) findViewById(R.id.bs_food_progress);
        views[1] = (TextView) findViewById(R.id.bs_travel_progress);
        views[2] = (TextView) findViewById(R.id.bs_beauty_progress);
        views[3] = (TextView) findViewById(R.id.bs_entertainment_progress);
        views[4] = (TextView) findViewById(R.id.bs_home_progress);
        views[5] = (TextView) findViewById(R.id.bs_clothes_progress);
        views[6] = (TextView) findViewById(R.id.bs_leisure_progress);
        views[7] = (TextView) findViewById(R.id.bs_other_progress);

        // Assigns the category spend categories to the text labels
        for(int i = 0; i < 8; i++) {
            views[i].setText(currencyFormatter(values[i]));
        }

    }

    /**
     * Performs a check to see if the lower value will eb greater than 0 and if it is, assigns the
     * label as normal, and if not assigns it £0
     */
    private void setLowerView() {
        // Performs the check to see if lower bound will be greater than or equal to zero
        if(budgetValue - 20 <= 0) {
            // If yes assign the value
            lowerView.setText(currencyFormatter(0f));
        } else {
            // If no assign a 0 value
            lowerView.setText(currencyFormatter(budgetValue - 20));
        }
    }

    /**
     * Performs a calculation to find the remaining budget which is not yet assigned to a category
     * and returns it as a float
     *
     * @return a float that represents remaining unassigned values
     */
    private float getUnassignedValue() {
        // Creates temporary variable for calculation
        float temp = 0;
        // Loops through each category and works out how much cash is assigned in categories
        for(int i = 0; i < 8; i++) {
            temp += values[i];
        }
        // Subtracts the amount used in categories from the total budget
        return budgetValue - temp;
    }

    /**
     * Takes a float and returns a formatted crrency output in the form £xx.xx ready to be outputted
     * to the GUI
     *
     * @param value takes the float to be formatted
     * @return returns the formatted float as a String
     */
    private String currencyFormatter(float value) {
        return String.format("£%.2f", value);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_budget_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

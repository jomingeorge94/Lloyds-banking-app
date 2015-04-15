package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * A class which produces a GUI that give the user options to alter their budget settings using
 * sliders to control it
 *
 * Created by Dan Gardner
 */
public class BudgetSettings extends ActionBarActivity {

    // Create the variables to store the values and layout components for the fragment
    private float budgetValue;
    private float[] values = new float[8];

    // Variables which store the budget slider infomation
    private SeekBar budgetSlider;
    private SeekBar[] sliders = new SeekBar[8];
    private float max;
    private float min;

    // Variable to store buttons from XML
    private Button discard;
    private Button save;

    // Variables which store references to the XML TextViews
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
        // Calls the initialiseVariable method to update layout components
        initialiseVariables();

        budgetSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Changes the budget label in real time with the suer altering the slider
                budgetView.setText(currencyFormatter(budgetSlider.getProgress() + min));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Adds the oftest (lowerValue) to the slider value to get the true budget value
                budgetValue = budgetSlider.getProgress() + min;

                // Updates the minimum and maximum values
                min = getMin();
                max = budgetValue + 25 - min;

                // Centers the slider
                budgetSlider.setProgress((int) max / 2);
                budgetSlider.setMax((int) max);

                // Updates the rest of the GUI
                updateView();
            }
        });

        for(int i = 0; i < 8; i++) {
            sliders[i].setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    // Updates all of the labels while the user drags the seek bar
                    updateView();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        }

        // Adds a listener to the save button to save the budget changes
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Calls the update budget method to save the changes
                updateBudget();
            }
        });

        discard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    /**
     * Method that commits the vlad changes to the budget to the database so it can be recalled
     * later for other sections in the budgeting
     */
    private void updateBudget() {

    }

    /**
     * Initialises all of the layout components and applies the default values. When this method is
     * called it is for the initial setup of the budget, when editing the budget a differnet method
     * will be called and filled from the existing data
     */
    private void initialiseVariables() {

        /***** DEBUG *****/
        // Get the current budget from the database
        budgetValue = 88f;
        max = budgetValue + 25;
        min = getMin();

        // Initialises the SeekBar's as local variables
        budgetSlider = (SeekBar) findViewById(R.id.bs_budget_seek);
        budgetSlider.setProgress((int) max / 2);

        // Initialise all of the SeekBar variables from the XML files
        sliders[0] = (SeekBar) findViewById(R.id.bs_food_seek);
        sliders[1] = (SeekBar) findViewById(R.id.bs_travel_seek);
        sliders[2] = (SeekBar) findViewById(R.id.bs_beauty_seek);
        sliders[3] = (SeekBar) findViewById(R.id.bs_entertainment_seek);
        sliders[4] = (SeekBar) findViewById(R.id.bs_home_seek);
        sliders[5] = (SeekBar) findViewById(R.id.bs_clothes_seek);
        sliders[6] = (SeekBar) findViewById(R.id.bs_leisure_seek);
        sliders[7] = (SeekBar) findViewById(R.id.bs_other_seek);

        // Initialises TextView variables from the XML files
        budgetView = (TextView) findViewById(R.id.bs_budget_value);
        lowerView = (TextView) findViewById(R.id.bs_lower_bound);
        middleView = (TextView) findViewById(R.id.bs_mid_bound);
        upperView = (TextView) findViewById(R.id.bs_upper_bound);
        unassignedView = (TextView) findViewById(R.id.bs_remaining_value);

        // Find and assign to local variables buttons from XML file
        save = (Button) findViewById(R.id.bs_button_save);
        discard = (Button) findViewById(R.id.bs_button_discard);

        // Initialises all of the text views for the categories
        views[0] = (TextView) findViewById(R.id.bs_food_progress);
        views[1] = (TextView) findViewById(R.id.bs_travel_progress);
        views[2] = (TextView) findViewById(R.id.bs_beauty_progress);
        views[3] = (TextView) findViewById(R.id.bs_entertainment_progress);
        views[4] = (TextView) findViewById(R.id.bs_home_progress);
        views[5] = (TextView) findViewById(R.id.bs_clothes_progress);
        views[6] = (TextView) findViewById(R.id.bs_leisure_progress);
        views[7] = (TextView) findViewById(R.id.bs_other_progress);

        updateView();

    }

    /**
     * Method which updates all of the TextViews and progress bars in the GUI. Calling this method
     * also updates the values of the sliders to match the current budget
     */
    private void updateView() {

        // Updates the main text label at the top of the page
        budgetView.setText(currencyFormatter(budgetValue));

        // Updates the minimum value
        min = getMin();

        // Updates the text labels underneath the budget bar
        lowerView.setText(currencyFormatter(min));
        middleView.setText(currencyFormatter(budgetValue));
        upperView.setText(currencyFormatter(budgetValue + 25));

        // Updates the text label showing the remaining amount of the budget
        unassignedView.setText(currencyFormatter(getUnassignedValue()));
        // If there is remaining budget the save button will be disabled
        if(getUnassignedValue() > 0) {
            unassignedView.setTextColor(getResources().getColor(R.color.dark_green));
            save.setEnabled(false);
            save.setBackgroundColor(getResources().getColor(R.color.medium_grey));
        }
        // If the total value of the categories is over the budget save button will be disabled
        else if(getUnassignedValue() < 0) {
            unassignedView.setTextColor(getResources().getColor(R.color.spend_over));
            save.setEnabled(false);
            save.setBackgroundColor(getResources().getColor(R.color.medium_grey));
        }
        // If the budget matches the categories then the save button is enabled
        else {
            unassignedView.setTextColor(getResources().getColor(R.color.text_dark));
            save.setEnabled(true);
            save.setBackgroundColor(getResources().getColor(R.color.dark_green));
        }

        for(int i = 0; i < 8; i++) {
            values[i] = (float) sliders[i].getProgress() ;
        }

        // Updates the category spend categories to the text labels
        for(int i = 0; i < 8; i++) {
            sliders[i].setMax((int) budgetValue);
            views[i].setText(currencyFormatter((float) values[i]));
        }

    }

    /**
     * A method which ensures that the lower bound of the budget does not go negative. If the lower
     * bound does go into negative then the value will be ignored and set to 0, which is the lowest
     * possible valid value
     *
     * @return the minimum budget bound
     */
    private float getMin() {
        float min;
        // Performs the check to see if lower bound will be greater than or equal to zero
        if(budgetValue - 25 <= 0) {
            // If yes assign the value
            min = 0;
        } else {
            // If no assign a 0 value
            min = budgetValue - 25;
        }
        // Returns the minimum value
        return min;
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

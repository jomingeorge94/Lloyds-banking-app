package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;


public class BudgetSettings extends ActionBarActivity {

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

    public BudgetSettings() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_settings);
        initialiseInterface();

    }

    private void initialiseInterface() {

        budgetValue = 88f;
        for(int i = 0; i < 8; i++) {
            values[i] = budgetValue / 8;
        }

        budgetSlider = (SeekBar) findViewById(R.id.bs_budget_seek);
        sliders[0] = (SeekBar) findViewById(R.id.bs_food_seek);
        sliders[1] = (SeekBar) findViewById(R.id.bs_travel_seek);
        sliders[2] = (SeekBar) findViewById(R.id.bs_beauty_seek);
        sliders[3] = (SeekBar) findViewById(R.id.bs_entertainment_seek);
        sliders[4] = (SeekBar) findViewById(R.id.bs_home_seek);
        sliders[5] = (SeekBar) findViewById(R.id.bs_clothes_seek);
        sliders[6] = (SeekBar) findViewById(R.id.bs_leisure_seek);
        sliders[7] = (SeekBar) findViewById(R.id.bs_other_seek);

        budgetView = (TextView) findViewById(R.id.bs_budget_value);
        budgetView.setText(currencyFormatter(budgetValue));

        lowerView = (TextView) findViewById(R.id.bs_lower_bound);
        setLowerView();

        middleView = (TextView) findViewById(R.id.bs_mid_bound);
        middleView.setText(currencyFormatter(budgetValue));

        upperView = (TextView) findViewById(R.id.bs_upper_bound);
        upperView.setText(currencyFormatter(budgetValue + 20));

        unassignedView = (TextView) findViewById(R.id.bs_remaining_value);
        unassignedView.setText(currencyFormatter(getUnassignedValue()));

        views[0] = (TextView) findViewById(R.id.bs_food_progress);
        views[1] = (TextView) findViewById(R.id.bs_travel_progress);
        views[2] = (TextView) findViewById(R.id.bs_beauty_progress);
        views[3] = (TextView) findViewById(R.id.bs_entertainment_progress);
        views[4] = (TextView) findViewById(R.id.bs_home_progress);
        views[5] = (TextView) findViewById(R.id.bs_clothes_progress);
        views[6] = (TextView) findViewById(R.id.bs_leisure_progress);
        views[7] = (TextView) findViewById(R.id.bs_other_progress);

        for(int i = 0; i < 8; i++) {
            views[i].setText(currencyFormatter(values[i]));
        }

    }

    private void setLowerView() {
        if(budgetValue - 20 <= 0) {
            lowerView.setText(currencyFormatter(0f));
        } else {
            lowerView.setText(currencyFormatter(budgetValue - 20));
        }
    }

    private float getUnassignedValue() {
        float temp = 0;
        for(int i = 0; i < 8; i++) {
            temp += values[i];
        }
        return budgetValue - temp;
    }

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

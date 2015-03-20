package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    public FragmentTabHost tabHost;
    private TextView click;
    FragmentManager manager;

    /**
     * Implemented using the FragmentTabHost, associated fragments within each tabs are coded using the add method
     * Also with the help of a method removes the current fragment with the latest fragment so that overlaying is prevented
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_tab_main);

        // Refactor at your will.
        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);

        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("", getResources().getDrawable(R.drawable.ic_budget)),
                BudgetFragment.class, null);

        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("", getResources().getDrawable(R.drawable.ic_home)),
                HomeFragment.class, null);


        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("", getResources().getDrawable(R.drawable.ic_deals)),
                DealsFragment.class, null);

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if(tabHost.getCurrentTabTag().equals("tab1")) {

                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    ((FrameLayout)findViewById(android.R.id.tabcontent)).removeAllViews();
                    transaction.addToBackStack(null);
                    transaction.commit();
                }

                if(tabHost.getCurrentTabTag().equals("tab2")) {

                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(android.R.id.tabcontent, new HomeFragment(), "tab2");
                    transaction.addToBackStack(null);

                    transaction.commit();
                }

                if(tabHost.getCurrentTabTag().equals("tab3")) {

                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    ((FrameLayout)findViewById(android.R.id.tabcontent)).removeAllViews();
                    transaction.addToBackStack(null);

                    transaction.commit();
                }
            }
        });

        //loads up the home tab straight away
        tabHost.setCurrentTab(1);

        tabHost.findViewById(R.id.horsePointer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment entry = getSupportFragmentManager().findFragmentById(android.R.id.tabcontent);
                if(entry.getTag().equals("tab2")){
                    HorsePointer horse = new HorsePointer();
                    horse.show(getSupportFragmentManager(), "Horse");
                }else if(entry.getTag().equals("OverView")){
                    Toast.makeText(getApplication(), "Horse pointer - Button is in here in overview", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    /**
     * Back button method - currently nothing is implemented
     */
    @Override
    public void onBackPressed() {



    }

    //Simply prevents the app from being in the background, user has to enter passcode again!
    @Override
    protected void onUserLeaveHint() {
        this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*// Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);*/

        LinearLayout tabmainswitch = (LinearLayout)findViewById(R.id.tabmainswitch);

        switch (item.getItemId()){
            case R.id.menu_red:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                tabmainswitch.setBackgroundColor(Color.RED);
                return true;

            case R.id.menu_green:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                tabmainswitch.setBackgroundColor(Color.GREEN);
                return true;

            case R.id.menu_yellow:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                tabmainswitch.setBackgroundColor(Color.YELLOW);
                return true;

            default:
                return super.onOptionsItemSelected(item);


        }



    }
}
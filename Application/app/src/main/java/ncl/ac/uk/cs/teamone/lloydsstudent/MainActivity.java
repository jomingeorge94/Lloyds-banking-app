package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    public FragmentTabHost tabHost;
    private TextView click;
    FragmentManager manager;
    private boolean boolOverview = false;
    private boolean boolHome = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_tab_main);
/*
        Data data = new Data();
        Map<String, String> d = new HashMap<String, String>();

        */

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
                    transaction.replace(android.R.id.tabcontent, new HomeFragment());
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

    }



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

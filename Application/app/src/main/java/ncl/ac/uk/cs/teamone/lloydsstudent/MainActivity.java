package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    private FragmentTabHost tabHost;
    private TextView click;
    FragmentManager manager;
    private boolean boolOverview = false;
    private boolean boolHome = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_tab_main);

        manager = getFragmentManager();
        // Refactor at your will.
        final TabHost tabHost = (TabHost)findViewById(R.id.tabHost);
        tabHost.setCurrentTab(R.id.tab2);
        tabHost.setup();
        final TabHost.TabSpec tab1 = tabHost.newTabSpec("tab1");
        final TabHost.TabSpec tab2 = tabHost.newTabSpec("Second Tab");
        final TabHost.TabSpec tab3 = tabHost.newTabSpec("Third Tab");

        //three tabs with icons for each of them, also setting the content into the tabhost
        tab1.setIndicator("", getResources().getDrawable(R.drawable.ic_budget));
        tab1.setContent(R.id.tab1);

        tab2.setIndicator("", getResources().getDrawable(R.drawable.ic_home));
        tab2.setContent(R.id.tab2);

        tab3.setIndicator("", getResources().getDrawable(R.drawable.ic_deals));
        tab3.setContent(R.id.tab3);

        //adding the three tabs on to the tabhost
        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);

        //setting the tab to load up first between the three tabs
        tabHost.setCurrentTab(1);

        final OverviewFragment frag = new OverviewFragment();
        final HomeFragment fragHome = new HomeFragment();


        //fragment switching if the tab 2 has been pressed it set's the display back to the home screen of the tab 2 (home_tab_main)
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if(tabHost.getCurrentTabTag().equals("Second Tab")) {

                    android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();

                    transaction.replace(R.id.tabSwitch2, fragHome);
                    transaction.addToBackStack(null);

                    transaction.commit();
                }
            }
        });

        //this code below basically switches the fragment when the user clicks on the currentmoney id. Replace the fragment with the Account overview screen.
        tabHost.getTabContentView().findViewById(R.id.currentMoney).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                switch(v.getId()){
                    case R.id.currentMoney:
                        android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();

                        transaction.replace(R.id.tabSwitch2, frag);
                        transaction.addToBackStack(null);



                        transaction.commit();



                }
            }
        });



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

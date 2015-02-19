package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    private FragmentTabHost tabHost;
    private TextView click;
    FragmentManager manager;
    private boolean bool = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_tab_main);


        manager = getFragmentManager();
        // Refactor at your will.
        final TabHost tabHost = (TabHost)findViewById(R.id.tabHost);
        tabHost.setup();
        final TabHost.TabSpec tab1 = tabHost.newTabSpec("tab1");
        final TabHost.TabSpec tab2 = tabHost.newTabSpec("Second Tab");
        final TabHost.TabSpec tab3 = tabHost.newTabSpec("Third Tab");


        tab1.setIndicator("", getResources().getDrawable(R.drawable.ic_budget));
        tab1.setContent(R.id.tab1);

        tab2.setIndicator("", getResources().getDrawable(R.drawable.ic_home));
        tab2.setContent(R.id.tab2);

        tab3.setIndicator("", getResources().getDrawable(R.drawable.ic_deals));
        tab3.setContent(R.id.tab3);


        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);

        final OverviewFragment frag = new OverviewFragment();

        




        tabHost.getTabContentView().findViewById(R.id.currentMoney).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                switch(v.getId()){
                    case R.id.currentMoney:
                        android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        bool = true;

                        transaction.replace(R.id.tabSwitch2, frag);
                        transaction.addToBackStack(null);

                        //Debuggin
                        Log.i("Testing","Current Money Clicked");


                        transaction.commit();



                }
            }
        });



    }

    /*public void showCurrentAccountMoney (View v){

        HomeTabTransaction f1 = new HomeTabTransaction();
        FragmentTransaction transaction = manager.beginTransaction();
        Log.i("Jomin","hello");
        transaction.replace(R.id.tab2, f1);
        transaction.commit();
    }*/


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

package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    public FragmentTabHost tabHost;
    private TextView click;
    FragmentManager manager;
    private DrawerLayout drawerLayout;
    ListView listView;
    ArrayAdapter<String> adapter;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private String [] listentry;
    Boolean state=false;

    /**
     * Implemented using the FragmentTabHost, associated fragments within each tabs are coded using the add method
     * Also with the help of a method removes the current fragment with the latest fragment so that overlaying is prevented
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_tab_main);
        listentry=getResources().getStringArray(R.array.helpmenu);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer);
        listView=(ListView)findViewById(R.id.drawer_list);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.drawable.ic_launcher,R.string.app_name,R.string.hello_world){
            @Override
            public void onDrawerOpened(View drawerView) {
                // TODO Auto-generated method stub
                state=true;
                supportInvalidateOptionsMenu();
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                // TODO Auto-generated method stub
                state=false;
                supportInvalidateOptionsMenu();
            }
        };









        drawerLayout.setDrawerListener(actionBarDrawerToggle);

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



        adapter= new ArrayAdapter<String>(this,R.layout.drawer_layout,R.id.helpmenu,listentry) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // TODO Auto-generated method stub
                View view= super.getView(position, convertView, parent);
                if(view==null){
                    LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = vi.inflate(R.layout.drawer_layout, null,true);

                }
                return view;
            }
        };
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {


                if((( TextView) arg1.findViewById(R.id.helpmenu)).getText().equals("Account Management")){
                    Intent s=new Intent(getApplicationContext(),SecondDetail.class);

                    startActivity(s);
                }
                else  if((( TextView) arg1.findViewById(R.id.helpmenu)).getText().equals("Accessibility")){
                    Toast.makeText(MainActivity.this,"bad boy your ",Toast.LENGTH_LONG).show();
                }
                else  if((( TextView) arg1.findViewById(R.id.helpmenu)).getText().equals("Settings")){
                    Toast.makeText(MainActivity.this,"You are blind mate, go to specsavers",Toast.LENGTH_LONG).show();
                }


                else  if((( TextView) arg1.findViewById(R.id.helpmenu)).getText().equals("Log Out")){
                    Toast.makeText(MainActivity.this,"Log Out",Toast.LENGTH_LONG).show();
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
    public boolean onKeyDown(int keycode, KeyEvent e) {
        switch(keycode) {
            case KeyEvent.KEYCODE_MENU:
                if(state==true){
                    drawerLayout.closeDrawer(Gravity.LEFT);
                    state=false;
                }else {
                    drawerLayout.openDrawer(Gravity.LEFT);
                    state=true;
                }
                return true;
        }

        return super.onKeyDown(keycode, e);
    }

}
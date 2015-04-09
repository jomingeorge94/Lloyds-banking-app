package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
    public FragmentTabHost tabHost;
    private DrawerLayout drawerLayout;
    ListView listView;
    ArrayAdapter<String> adapter;
    private String [] listentry;
    Boolean state = false;

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



                ImageView iv= (ImageView)view.findViewById(R.id.flag);
                TextView title=(TextView)view.findViewById(R.id.helpmenu);
                if(title.getText().toString().equals("Account Management")){
                    iv.setImageResource(R.drawable.account_management_icon);
                }
                else if(title.getText().toString().equals("Settings")) {
                    iv.setImageResource(R.drawable.settings_icon);
                }
                else if(title.getText().toString().equals("Budget Notification")) {
                    iv.setImageResource(R.drawable.budget_icon);
                }
                else if(title.getText().toString().equals("Lloyd's Banking Tour")) {
                    iv.setImageResource(R.drawable.whatsnew_icon);
                }
                else if(title.getText().toString().equals("Contact Us")) {
                    iv.setImageResource(R.drawable.contactus_icon);
                }

                else if(title.getText().toString().equals("Other Products")) {
                    iv.setImageResource(R.drawable.icon_cart);
                }

                else if(title.getText().toString().equals("Terms and Conditions")) {
                    iv.setImageResource(R.drawable.termsandcondition_icon);
                }
                else if(title.getText().toString().equals("FAQs")) {
                    iv.setImageResource(R.drawable.faqs_icon);
                }
                else if(title.getText().toString().equals("About")) {
                    iv.setImageResource(R.drawable.i_icon);
                }
                else if(title.getText().toString().equals("Log Out")) {
                    iv.setImageResource(R.drawable.logout_icon);
                }
                else if(title.getText().toString().equals("Send feedback")) {
                    iv.setImageResource(R.drawable.feedback_icon);
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
                    drawerLayout.closeDrawer(Gravity.LEFT);
                    Intent accountmanagement =new Intent(getApplicationContext(),AccountManagementScreen.class);
                    startActivity(accountmanagement);
                }
                else  if((( TextView) arg1.findViewById(R.id.helpmenu)).getText().equals("Settings")){
                    Toast.makeText(MainActivity.this,"Settings",Toast.LENGTH_LONG).show();
                }

                else  if((( TextView) arg1.findViewById(R.id.helpmenu)).getText().equals("Budget Notification")){
                    Toast.makeText(MainActivity.this,"Budget Notification",Toast.LENGTH_LONG).show();
                }

                else  if((( TextView) arg1.findViewById(R.id.helpmenu)).getText().equals("Lloyd's Banking Tour")){
                    drawerLayout.closeDrawer(Gravity.LEFT);
                    Intent whatsnew =new Intent(getApplicationContext(),LloydsBankingTour.class);
                    startActivity(whatsnew);
                }

                else  if((( TextView) arg1.findViewById(R.id.helpmenu)).getText().equals("Contact Us")){
                    drawerLayout.closeDrawer(Gravity.LEFT);
                    Intent contactpage =new Intent(getApplicationContext(),ContactUs.class);
                    startActivity(contactpage);
                }

                else  if((( TextView) arg1.findViewById(R.id.helpmenu)).getText().equals("Other Products")){
                    drawerLayout.closeDrawer(Gravity.LEFT);
                    Intent otherproducts =new Intent(getApplicationContext(),OtherProducts.class);
                    startActivity(otherproducts);
                }

                else  if((( TextView) arg1.findViewById(R.id.helpmenu)).getText().equals("Terms and Conditions")){
                    drawerLayout.closeDrawer(Gravity.LEFT);
                    Intent termsandcondition =new Intent(getApplicationContext(),TermsandCondition.class);
                    startActivity(termsandcondition);
                }

                else  if((( TextView) arg1.findViewById(R.id.helpmenu)).getText().equals("FAQs")){
                    drawerLayout.closeDrawer(Gravity.LEFT);
                    Intent faqs =new Intent(getApplicationContext(),FAQs.class);
                    startActivity(faqs);
                }

                else  if((( TextView) arg1.findViewById(R.id.helpmenu)).getText().equals("Send feedback")){
                    drawerLayout.closeDrawer(Gravity.LEFT);
                    Intent feedback =new Intent(getApplicationContext(),FeedbackSettings.class);
                    startActivity(feedback);
                }


                else  if((( TextView) arg1.findViewById(R.id.helpmenu)).getText().equals("About")){
                    drawerLayout.closeDrawer(Gravity.LEFT);
                    Intent about =new Intent(getApplicationContext(),About_Us_Screen.class);
                    startActivity(about);
                }

                else  if((( TextView) arg1.findViewById(R.id.helpmenu)).getText().equals("Log Out")){
                    drawerLayout.closeDrawer(Gravity.LEFT);
                    LogOutScreen logout = new LogOutScreen();
                    logout.show(getSupportFragmentManager(),"LogOut");

                }
            }
        });
    }

    /**
     * Back button method - currently nothing is implemented
     */

    @Override
    public void onBackPressed() {

        drawerLayout.closeDrawer(Gravity.LEFT);
    }

    //Simply prevents the app from being in the background, user has to enter passcode again!
    @Override
    protected void onUserLeaveHint() {
        this.finish();
    }

    //Go back to login screen when phone goes to sleep
    @Override
    protected void onPause(){
        super.onPause();
        //Start new activity
        Intent I = new Intent(MainActivity.this, LoginActivity.class);
        this.startActivity(I);
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
package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

/**
 * Fragment that give the user a GUI which can initiate calls to different app related settings
 *
 * Created by Jomin on 27/03/2015.
 */
public class SettingsAccount extends FragmentActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Call to super
        super.onCreate(savedInstanceState);
        // Set layout from the XML file
        setContentView(R.layout.settings_account_management);

        // Add listener to back button on top bar to take user to previous Activity
        findViewById(R.id.settings_manager_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SettingsAccount.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        // Add listener to icon to take user to update their details
        findViewById(R.id.settings_manager_personal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SettingsAccount.this,UpdateDetails.class);
                startActivity(i);
                finish();
            }
        });

        // Add listener to icon to take user to update their passcode
        findViewById(R.id.settings_manager_passcode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent passcode = new Intent(SettingsAccount.this,SettingsPasscode.class);
                startActivity(passcode);
                finish();
            }
        });

        // Add listener to icon to take user to manage their moeny
        findViewById(R.id.settings_manager_money).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent name = new Intent(SettingsAccount.this,SettingsMoney.class);
                startActivity(name);
                finish();
            }
        });

        // Add listener to icon to take user to manage their statements
        findViewById(R.id.settings_manager_statement).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent statements = new Intent(SettingsAccount.this,ManageStatements.class);
                startActivity(statements);
                finish();
            }
        });

        // Add listener to icon to take report a lost card
        findViewById(R.id.settings_manager_lost).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lost = new Intent(SettingsAccount.this,LostorStole_Screen.class);
                startActivity(lost);
                finish();
            }
        });

        // Add listener to icon to take user to report an application issue
        findViewById(R.id.settings_manager_issue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent issue = new Intent(SettingsAccount.this,HavingIssues.class);
                startActivity(issue);
                finish();
            }
        });

    }

    /**
     * Method which gives an action to the back soft key, the intent is the same the back button
     * at the top of the page - it takes the user to the previous fragment
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(SettingsAccount.this,MainActivity.class);
        startActivity(i);
        finish();
    }
 }

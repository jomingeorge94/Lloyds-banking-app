package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

/**
 * FragmentActivity which generates a GUI to show information about the application
 *
 * Created by Jomin on 27/03/2015.
 * Reference - http://lloydsbank.creativevirtual.com/Lloyds/bot.htm?isJSEnabled=1&isJSEnabled=1&entry=Enter%20your%20question%20here
 */
public class SettingsAboutUs extends FragmentActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Call to super
        super.onCreate(savedInstanceState);
        // Set the XML layout file to generate view from
        setContentView(R.layout.settings_about_us);

        // Set on click listener to back button and take the user back to previous fragment
        findViewById(R.id.settings_about_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent taking user to previous fragment
                Intent i=new Intent(SettingsAboutUs.this,MainActivity.class);
                // Start the intent
                startActivity(i);
                // Close current fragment
                finish();
            }
        });
    }

    /**
     * Method which controls activity executed when the back soft button is pressed on the phone
     * for this fragment it performs the same intent as pressing the back button in the layout
     */
    @Override
    public void onBackPressed() {
        // Call to super
        super.onBackPressed();
        // Intent taking user to previous fragment
        Intent i=new Intent(SettingsAboutUs.this,MainActivity.class);
        // Start the intent
        startActivity(i);
        // Close current fragment
        finish();
    }

    //Simply prevents the app from being in the background, user has to enter passcode again!
    @Override
    protected void onUserLeaveHint() {
        this.finish();
    }
 }

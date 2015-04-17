package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

/**
 * Fragment that generates a GUI from an XML file and displays information to the user
 *
 * Created by Jomin on 21/03/2015.
 * Reference - http://www.lloydsbank.com/online-banking/benefits-online-banking/money-manager.asp
 */
public class SettingsMoney extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Call to super
        super.onCreate(savedInstanceState);
        // Set the XML layout file to generate view from
        setContentView(R.layout.settings_money);

        // Set on click listener to back button and take the user back to previous fragment
        findViewById(R.id.settings_money_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent taking user to previous fragment
                Intent i=new Intent(SettingsMoney.this,SettingsAccount.class);
                startActivity(i);
                // Close current fragment
                finish();
            }
        });

        // Set onlclicklistener to listen for a click to a webpage
        findViewById(R.id.settings_money_link).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create intent and parse URL into object ready to open web broswer
                Intent link = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.lloydsbank.com/online-banking/benefits-online-banking/money-manager.asp"));
                // Start intent to open webpage
                startActivity(link);
            }
        });
    }

    @Override
    public void onBackPressed() {
        // Call to super
        super.onBackPressed();
        // Intent taking user to previous fragment
        Intent i=new Intent(SettingsMoney.this,SettingsAccount.class);
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
package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

/**
 * Fragment which generates a simple GUI to give the user information about contacting the bank. It
 * also handles some user input to allow the user to call or email the bank from within the
 * application
 *
 * Created by Jomin on 21/03/2015.
 */

public class ContactUs extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Call to super
        super.onCreate(savedInstanceState);
        // Defines the XML file to be inflate to give the view to the fragment
        setContentView(R.layout.contact_us_screen);

        // Assign listener to detect back button click
        findViewById(R.id.settings_change_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create intent to go to home screen
                Intent i = new Intent(ContactUs.this,MainActivity.class);
                // Start the intent
                startActivity(i);
                // Finish current activity
                finish();
            }
        });

        // Assign listener to detect email press and start a the EmailDialog
        findViewById(R.id.contact_email).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Create and assign the new object
                EmailDialog email = new EmailDialog();
                // Display dialog
                email.show(getSupportFragmentManager(), "email");
            }
        });

        // Assign listener to detect clicks on the call button
        findViewById(R.id.contact_call).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Create new CallDialog object to initialise call
                CallDialog CallUs = new CallDialog();
                // Display dialog
                CallUs.show(getSupportFragmentManager(), "call");
            }
        });
    }

    /**
     * Defines an activity for when the soft back key is pressed, in this case the activity takes
     * the user back to the home screen
     */
    @Override
    public void onBackPressed() {
        // Call to super
        super.onBackPressed();
        // Create intent to go to home screen
        Intent i=new Intent(ContactUs.this,MainActivity.class);
        // Start the intent
        startActivity(i);
        // Finish current activity
        finish();
    }
}
package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    /*******
     Debug variables, depending on which mode you want to be in alter the states of these variables.
     One variable should be set to true - this should be the debug option you wish to enter - all
     the others should be set to false
     *******/

    // Normal Start takes you to the login as if you are using the app as normal
    private boolean normalStart = false;
    // First Time Start takes you into the app as if you're a first time user
    private boolean firstTimeStart = false;
    // Skip Start takes you through to the home screen (Tabbed Screen)
    private boolean skipStart = true;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_splash);

        /* Creates New handler to start the login screen after wait */
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                // Creates new intent to change view
                Intent mainIntent;

                if(normalStart == true) {
                    mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
                } else if(firstTimeStart == true) {
                    mainIntent = new Intent(SplashActivity.this, FirstLoginActivity.class);
                } else {
                    mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                }

                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

    }
}

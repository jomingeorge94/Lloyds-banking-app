package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;

public class SplashActivity extends Activity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    /*******
     Debug variables, depending on which mode you want to be in alter the states of these variables.
     One variable should be set to true - this should be the debug option you wish to enter - all
     the others should be set to false
     *******/

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_splash);

        final Context activity = SplashActivity.this;

        final TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);

        /* Creates New handler to start the login screen after wait */

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //url to connect to
                String url = "http://www.abunities.co.uk/t2022t1/check_mobile_banking.php";

                //values to send to the PHP file
                String[] keys = {"imei"};
                String[] values = {telephonyManager.getDeviceId()};

                //create an asynchronous object
                final PHPHandler handler = new PHPHandler(activity, keys, values);

                //execute the object
                handler.execute(url);
            }
        }, SPLASH_DISPLAY_LENGTH);

    }
}
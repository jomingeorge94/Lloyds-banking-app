package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;

/**
 * A class that deals with the initial loading of the application. As well as loading the scripts
 * the page creates a basic GUI which dismays a logo to the user and a loading animation so they
 * are aware the application is loading
 *
 * This Activity is the one declared in the AppManifest as the first one to run so the application
 * always loads to this page when started
 */
public class SplashActivity extends Activity {

    // Length of delay for splash screen, currently 1 second
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    final Context activity = SplashActivity.this;

    @Override
    public void onCreate(Bundle icicle) {

        // Call to the super class
        super.onCreate(icicle);
        // Creates the view from the XML file specified
        setContentView(R.layout.activity_splash);

        final TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);

        // Create handler which creates the delay for the Activity
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // Check if WiFi, 3G, 4G or any internet connection is available
                if(isNetworkAvailable()) {
                    // Url to connect to
                    String url = "http://www.abunities.co.uk/t2022t1/check_mobile_banking.php";

                    // Values to send to the PHP file
                    String[] keys = {"imei"};
                    String[] values = {telephonyManager.getDeviceId()};

                    // Create an asynchronous object
                    PHPHandler handler = new PHPHandler(activity, keys, values, 0);

                    // Execute the object
                    handler.execute(url);
                }
                // If no connectivity found go to Error Activity
                else {
                    // Create intent to start Error Activity class
                    Intent otherIntent = new Intent(activity, NetworkErrorActivity.class);
                    // Start the intent
                    activity.startActivity(otherIntent);
                    // Finish current activity
                    ((Activity) activity).finish();
                }
            }
            
        }, SPLASH_DISPLAY_LENGTH);

    }

    /**
     * Reads the phones network status and returns a boolean depending whether the
     * phone has any internet connection or not
     *
     * @return whether the phone has network connection or not
     *
     * credit: http://stackoverflow.com/questions/4238921/detect-whether-there-is-an-internet-connection-available-on-android
     */

    private boolean isNetworkAvailable() {

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(activity.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }
}
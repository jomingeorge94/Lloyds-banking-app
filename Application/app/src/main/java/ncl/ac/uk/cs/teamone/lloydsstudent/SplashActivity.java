package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;

public class SplashActivity extends Activity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    final Context activity = SplashActivity.this;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_splash);

        final TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);

        /* Creates New handler to start the login screen after wait */

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(isNetworkAvailable()) {
                    //url to connect to
                    String url = "http://www.abunities.co.uk/t2022t1/check_mobile_banking.php";

                    //values to send to the PHP file
                    String[] keys = {"imei"};
                    String[] values = {telephonyManager.getDeviceId()};

                    //create an asynchronous object
                    PHPHandler handler = new PHPHandler(activity, keys, values, 0);

                    //execute the object
                    handler.execute(url);
                }
                //go to an error activity
                else {
                    Intent otherIntent;
                    otherIntent = new Intent(activity, NetworkErrorActivity.class);
                    activity.startActivity(otherIntent);
                    ((Activity) activity).finish();
                }
            }
        }, SPLASH_DISPLAY_LENGTH);

    }

    /**
     *
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
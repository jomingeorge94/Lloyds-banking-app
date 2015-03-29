package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.Activity;
import android.os.Bundle;


public class NetworkErrorActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_error);
    }

    //Simply prevents the app from being in the background, user has to enter passcode again!
    @Override
    protected void onUserLeaveHint() {
        this.finish();
    }
}

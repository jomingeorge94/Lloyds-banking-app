package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class FirstLoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.initial_setup_login);

        // Finds the editable text and assigns them variables
        final EditText user = (EditText) findViewById(R.id.userID);
        final EditText pass = (EditText) findViewById(R.id.userPass);

        // Find the next button and assigns it a variable
        final Button nextButton = (Button) findViewById(R.id.first_next);

        // Creates a Text listener to detect if the both fields are filled to enable finished button
        final TextWatcher watcher = new TextWatcher() {
            // Ignore
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {}
            // Ignore
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {}
            // Once text has changed checks if both fields are full
            @Override
            public void afterTextChanged(Editable s) {
                // Compares the length of both fields
                if (user.length() > 0 && pass.length() >= 6) {
                    // Enables finished buttons
                    nextButton.setEnabled(true);
                    nextButton.setBackgroundColor(Color.parseColor("#369742"));
                }
                else {
                    // Disables finished buttons
                    nextButton.setEnabled(false);
                    nextButton.setBackgroundColor(Color.parseColor("#888888"));
                }
            }
        };

        // Adds the listener to the text fields
        user.addTextChangedListener(watcher);
        pass.addTextChangedListener(watcher);

        // Create button listner
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check Credentials Against Database
                loginCheck(user, pass);
            }
        });
    }

    public void loginCheck(EditText user, EditText pass) {
        //url to connect to
        String url = "http://www.abunities.co.uk/t2022t1/initial_setup.php";

        Data d = new Data();
        d.setUid(user.getText().toString());
        //values to send to the PHP file
        String[] keys = {"uid", "online_password"};
        String[] values = {d.getUid(), pass.getText().toString()};

        //create an asynchronous object
        final PHPHandler handler = new PHPHandler(FirstLoginActivity.this, keys, values) ;

        //execute the object
        handler.execute(url);
    }

    //Simply prevents the app from being in the background, user has to enter passcode again!
    @Override
    protected void onUserLeaveHint() {
        this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first_time, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

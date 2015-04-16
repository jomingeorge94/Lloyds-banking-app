package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.text.*;

/**
 * A class which provides a graphical login interface for the application, as well as dealing with
 * sending the request to the server to verify the password.
 */
public class LoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        // Locates the text edit box and creates an editable object
        final EditText passCode = (EditText) findViewById(R.id.login_passcode_one);

        // Get an input manager to mange soft keyboard during inputs
        final InputMethodManager keyboard = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);

        // Assign each input box to a local variable
        final EditText box[] = new EditText[4];
        box[0] = (EditText) findViewById(R.id.login_passcode_one);
        box[1] = (EditText) findViewById(R.id.login_passcode_two);
        box[2] = (EditText) findViewById(R.id.login_passcode_three);
        box[3] = (EditText) findViewById(R.id.login_passcode_four);

        //Create the activity
        final Context activity = LoginActivity.this;

        for(int i = 0; i < 3; i++) {
            // Temporary integer for passing into embedded class
            final int j = i;
            // Adds listener to change focus when "next" key is pressed
            box[i].addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    // Clears the focus of the current box
                    box[j].clearFocus();
                    // Request the focus of the next box
                    box[j + 1].requestFocus();
                    // SHows the keyboard for the next box
                    keyboard.showSoftInput(box[j + 1], InputMethodManager.SHOW_IMPLICIT);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }

        /*
           Adds a final listener which deals with the final character being inputted and sends the
           PHP request
         */
        box[3].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(box[0].length() == 1 && box[1].length() == 1 && box[2].length() == 1 && box[3].length() == 1) {

                    TelephonyManager telephonyManager = (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);

                    // Url to connect to
                    String url = "http://www.abunities.co.uk/t2022t1/validation.php";

                    // Values to send to the PHP file
                    String[] keys = {"imei", "passcode"};
                    String[] values = {telephonyManager.getDeviceId(), String.format("%s%s%s%s", box[0].getText().toString(), box[1].getText().toString(), box[2].getText().toString(), box[3].getText().toString())};

                    // Create an asynchronous object
                    PHPHandler handler = new PHPHandler(activity, keys, values, 4);

                    // Execute the object
                    handler.execute(url);

                    // Requests focus to box[0] and shows keyboard
                    box[0].requestFocus();
                    box[0].setText("");
                    keyboard.showSoftInput(box[0], InputMethodManager.SHOW_IMPLICIT);

                    // Resets the passcode boxes
                    box[1].setText("");
                    box[2].setText("");
                    box[3].setText("");

                    // Allows box[0] to get the focus
                    box[3].clearFocus();

                    // Set the info within the data class
                    Data d = new Data();
                    d.customer = handler.getData();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    // Simply prevents the app from being in the background, user has to enter passcode again!
    @Override
    protected void onUserLeaveHint() {
        this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        /*
           Handle action bar item clicks here. The action bar will
           automatically handle clicks on the Home/Up button, so long
           as you specify a parent activity in AndroidManifest.xml.
         */
        int id = item.getItemId();

        // Simple if statement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

}

package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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
        setContentView(R.layout.activity_first_time);

        setupOne();


    }

    public void setupOne() {
        // Creates invalid credentials popup message
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Sets the error message for the popup
        builder.setMessage(R.string.first_failed_login)
                // Sets the buttons
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {}
                });
        // Creates the alert object ready to be called
        final AlertDialog loginFailed = builder.create();

        // Finds the editable text and assigns them variables
        final EditText user = (EditText) findViewById(R.id.userID);
        final EditText pass = (EditText) findViewById(R.id.userPass);

        // Find the next button and assigns it a variable
        final Button nextButton = (Button) findViewById(R.id.first_next);

        // Create button listner
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check Credentials Against Database
                if(loginCheck()) {
                    // Change view to next set of inputs
                    setContentView(R.layout.activity_first_time_passcode);
                    setupTwo();
                } else {
                    // Show alert to tell user the wrong credentials have been entered
                    loginFailed.show();
                    // Sets them back to null
                    user.setText(null);
                    pass.setText(null);
                }
            }
        });
    }



    public void setupTwo() {

        // Creates invalid credentials popup message
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Sets the error message for the popup
        builder.setMessage(R.string.first_failed_passcode)
                // Sets the buttons
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {}
                });
        // Creates the alert object ready to be called
        final AlertDialog passNotMatch = builder.create();

        // Finds the editable text and assigns them variables
        final EditText pass = (EditText) findViewById(R.id.first_passcode);
        final EditText conf = (EditText) findViewById(R.id.first_confirm);

        // Find the next button and assigns it a variable
        final Button finishedButton = (Button) findViewById(R.id.first_finished);

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
                if (pass.length() == 4 && conf.length() == 4) {
                    // Enables finished buttons
                    finishedButton.setEnabled(true);
                    finishedButton.setBackgroundColor(Color.parseColor("#369742"));
                }
                else {
                    // Disables finished buttons
                    finishedButton.setEnabled(false);
                    finishedButton.setBackgroundColor(Color.parseColor("#888888"));
                }
            }
        };

        // Adds the listener to the text fields
        pass.addTextChangedListener(watcher);
        conf.addTextChangedListener(watcher);

        // Create button listener
        finishedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check Credentials Against Database
                if(passCheck(pass.getText().toString(), conf.getText().toString()) && pass.length() == 4) {
                    Intent I = new Intent(FirstLoginActivity.this, MainActivity.class);
                    startActivity(I);
                } else {
                    // Show alert to tell user the wrong credentials have been entered
                    passNotMatch.show();
                    // Sets them back to null
                    pass.setText(null);
                    conf.setText(null);
                }
            }
        });

    }

    public boolean loginCheck() {
        return true;
    }

    public boolean passCheck(String pass, String confirm) {
        if(pass.equals(confirm)) {
            // Passcodes match so added to database
            return true;
        } else {
            // Passcodes don't match don't add to database
            return false;
        }
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

package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Artemiy on 25/03/2015.
 */
public class PassCodeActivity {
    private Activity a;

    public PassCodeActivity(Activity a) {
        this.a = a;
        passCodeSetup();
    }

    public void passCodeSetup() {
        final Activity ac = (Activity) a;
        // Creates invalid credentials popup message
        AlertDialog.Builder builder = new AlertDialog.Builder(a);
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
        final EditText pass = (EditText) ac.findViewById(R.id.first_passcode);
        final EditText conf = (EditText) ac.findViewById(R.id.first_confirm);

        // Find the next button and assigns it a variable
        final Button finishedButton = (Button) ac.findViewById(R.id.first_finished);

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
                    final TelephonyManager telephonyManager = (TelephonyManager) a.getSystemService(Context.TELEPHONY_SERVICE);
                    //url to connect to
                    String url = "http://www.abunities.co.uk/t2022t1/initial_setup_passcode.php";
                    //values to send to the PHP file
                    String[] keys = {"uid", "passcode", "imei"};
                    Data d = new Data();
                    String[] values = {d.getUid(), pass.getText().toString(), telephonyManager.getDeviceId()};
                    //create an asynchronous object
                    PHPHandler handler = new PHPHandler(ac, keys, values, 2) ;
                    //execute the object
                    handler.execute(url);
                    //set the info within the data class
                    d.customer = handler.getData();
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


    private boolean passCheck(String pass, String confirm) {
        if(pass.equals(confirm)) {
            // Passcodes match so added to database
            return true;
        } else {
            // Passcodes don't match don't add to database
            return false;
        }
    }
}

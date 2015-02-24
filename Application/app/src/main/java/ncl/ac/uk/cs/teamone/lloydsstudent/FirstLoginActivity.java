package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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

        // Creates invalid credentials popup message
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.first_failed_login)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                    }
                });
        final AlertDialog alert = builder.create();

        // Finds the editable text and assigns them variables
        final EditText user = (EditText) findViewById(R.id.userID);
        final EditText pass = (EditText) findViewById(R.id.userPass);

        final Button nextButton = (Button) findViewById(R.id.first_next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check Credentials Against Database
                if(loginCheck()) {
                    // Change view to next set of inputs
                    setContentView(R.layout.activity_first_time_passcode);
                } else {
                    // Show alert to tell user the wrong credentials have been entered
                    alert.show();

                    // Sets them back to null
                    user.setText(null);
                    pass.setText(null);
                }
            }
        });
    }

    public boolean loginCheck() {
        return true;
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

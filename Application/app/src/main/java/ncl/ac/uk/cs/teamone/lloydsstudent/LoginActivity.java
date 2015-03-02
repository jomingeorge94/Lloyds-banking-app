package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.text.*;

import java.util.List;
import java.util.Map;


public class LoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button skipButton = (Button) findViewById(R.id.skip);
        //Locates the text edit box and creates an editable object
        final EditText passCode = (EditText) findViewById(R.id.login_passcode_input);
        //Create the activity
        final Context activity = LoginActivity.this;

        //Skip button listener - Temporary
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(I);
            }
        });

        passCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //checks if the passcode has been entered
                if(s.length() == 4) {
                    //url to connect to
                    String url = "http://www.abunities.co.uk/Student_Banking_App/validation.php";
                    //values to send to the PHP file
                    String[] keys = {"uid", "passcode"};
                    String[] values = {"1", passCode.getText().toString()};
                    //create an asynchronous object
                    PHPHandler handler = new PHPHandler(activity, keys, values) ;
                    //execute the object
                    handler.execute(url);
                    //Resets text
                    passCode.setText("");
                }
            }

            /**
             * Ignore
             */

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            /**
             * Ignore
             */

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    //Simply prevents the app from being in the background, user has to enter passcode again!
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

package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.text.*;

import java.util.Map;


public class LoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button skipButton = (Button) findViewById(R.id.skip);
        //Locates the text edit box and creates an editable object
        final EditText passcode = (EditText) findViewById(R.id.login_passcode_input);
        //Locates and creates the login button
        final Button loginButton = (Button) findViewById(R.id.login);

        //Skip button listener - Temporary
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(I);
            }
        });

        //Disable login
        loginButton.setEnabled(false);

        //Login button event listener
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String[] k = { "passcode" };
            String[] va = { passcode.getText().toString() };
            PHPHandler handler = new PHPHandler("localhost/validation.php", k, va);

            Map d = handler.getData();

            if(d != null) {
                Intent I = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(I);
            }
            }
        });

        passcode.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //When user has entered the entire passcode
                if(s.length() == 4) {
                    //Enable the login button
                    loginButton.setEnabled(true);
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

package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

/**
 * Fragment that creates a GUI which allows the user to input new details to be associated with their
 * bank account. The fragment takes the input form the user and then pushes it to the database
 *
 * Created by Jomin on 21/03/2015.
 */
public class UpdateDetails extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Call to super
        super.onCreate(savedInstanceState);
        // Create layout from XML file
        setContentView(R.layout.settings_update_details);

        // Assign layout components from XML to local variables
        final EditText email = (EditText) findViewById(R.id.settings_update_email);
        final EditText number = (EditText) findViewById(R.id.settings_update_number);
        final Button update = (Button) findViewById(R.id.settings_update_button);
        RadioGroup preferred = (RadioGroup) findViewById(R.id.settings_update_preference);

        // Create new text watcher to enable button when text changes
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Checks if reasonable length inputs are received
                if(email.length() >=4 && number.length() == 11) {
                    // Enable Button
                    update.setEnabled(true);
                    update.setBackgroundColor(getResources().getColor(R.color.dark_green));
                } else {
                    // Disable button
                    update.setEnabled(false);
                    update.setBackgroundColor(getResources().getColor(R.color.medium_grey));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        // Add listener to varaibles
        email.addTextChangedListener(watcher);
        number.addTextChangedListener(watcher);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDetails();
            }
        });

        // Set on click listener to back button and take the user back to previous fragment
        findViewById(R.id.settings_update_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create intent to open previous fragment
                Intent i=new Intent(UpdateDetails.this,SettingsAccount.class);
                startActivity(i);
                // Close current fragment
                finish();
            }
        });

    }

    /**
     * Method that takes the inputs entered into the GUI and then puts the changes to the server
     */
    private void updateDetails() {

    }

    /**
     * Method which controls activity executed when the back soft button is pressed on the phone
     * for this fragment it performs the same intent as pressing the back button in the layout
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Create intent to open previous fragment
        Intent i=new Intent(UpdateDetails.this, SettingsAccount.class);
        startActivity(i);
        // Close current fragment
        finish();
    }

}
package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by Jomin on 21/03/2015.
 */
public class UpdateYourDetails extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_your_details);

        final Spinner dropdown = (Spinner)findViewById(R.id.spinnerUpdateContactMethod);
        String[] items = new String[]{"Phone", "Email"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);


        findViewById(R.id.update_selectacontactmethod).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.spinnerUpdateContactMethod).setVisibility(View.VISIBLE);
                findViewById(R.id.update_selectacontactmethod).setVisibility(View.GONE);
                dropdown.performClick();
            }
        });



        findViewById(R.id.settings_change_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(UpdateYourDetails.this,SettingsAccountManagement.class);
                startActivity(i);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(UpdateYourDetails.this,SettingsAccountManagement.class);
        startActivity(i);
        finish();
    }

}
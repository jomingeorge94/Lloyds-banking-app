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
public class ManageStatements extends FragmentActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_statements_screen);

        final Spinner dropdown = (Spinner)findViewById(R.id.spinner_statements);
        String[] items = new String[]{"Phone", "Email"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>  (this,R.layout.spinner_layout, items);
        dropdown.setAdapter(adapter);

        findViewById(R.id.statementtextdropdown).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.spinner_statements).setVisibility(View.VISIBLE);
                findViewById(R.id.statementtextdropdown).setVisibility(View.GONE);
                dropdown.performClick();
            }
        });


        findViewById(R.id.settings_change_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ManageStatements.this,SettingsAccountManagement.class);
                startActivity(i);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(ManageStatements.this,SettingsAccountManagement.class);
        startActivity(i);
        finish();
    }

}
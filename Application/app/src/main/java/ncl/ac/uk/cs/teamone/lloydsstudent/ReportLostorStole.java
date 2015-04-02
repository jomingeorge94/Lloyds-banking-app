package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

/**
 * Created by Jomin on 21/03/2015.
 */
public class ReportLostorStole extends FragmentActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_lost_stole_screen);

        findViewById(R.id.backbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ReportLostorStole.this,AccountManagementScreen.class);
                startActivity(i);
                finish();
            }
        });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(ReportLostorStole.this,AccountManagementScreen.class);
        startActivity(i);
        finish();
    }

}
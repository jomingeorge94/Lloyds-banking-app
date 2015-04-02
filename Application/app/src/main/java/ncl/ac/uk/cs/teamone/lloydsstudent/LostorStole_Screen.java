package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

/**
 * Created by Jomin on 21/03/2015.
 */
public class LostorStole_Screen extends FragmentActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lost_stole_screen);

        findViewById(R.id.backbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LostorStole_Screen.this,AccountManagementScreen.class);
                startActivity(i);
                finish();
            }
        });

        findViewById(R.id.lost_stole_call).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LostorStoleDialogFragment lostorstole = new LostorStoleDialogFragment();
                lostorstole.show(getSupportFragmentManager(), "lostorstole");
            }
        });

        findViewById(R.id.lost_stole_reportnow).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent report=new Intent(LostorStole_Screen.this,ReportLostorStole.class);
                startActivity(report);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(LostorStole_Screen.this,AccountManagementScreen.class);
        startActivity(i);
        finish();
    }

}
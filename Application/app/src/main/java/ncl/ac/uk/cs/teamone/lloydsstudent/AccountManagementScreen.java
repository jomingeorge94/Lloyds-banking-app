package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Jomin on 27/03/2015.
 */
public class AccountManagementScreen extends FragmentActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_management_screen);

        findViewById(R.id.backbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AccountManagementScreen.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        findViewById(R.id.AccountManagement_Personal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AccountManagementScreen.this,UpdateYourDetails.class);
                startActivity(i);
                finish();
            }
        });

        findViewById(R.id.AccountManagement_Security).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AccountManagementScreen.this, "Change your Passcode ", Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.AccountManagement_AccountName).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AccountManagementScreen.this, "Change your Account Name ", Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.AccountManagement_ChangeBackground).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AccountManagementScreen.this, "Change the background ", Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.AccountManagement_Lost_Stole).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lostorstole=new Intent(AccountManagementScreen.this,LostorStole_Screen.class);
                startActivity(lostorstole);
                finish();
            }
        });

        findViewById(R.id.AccountManagement_Having_Issue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AccountManagementScreen.this, "Having Issue ", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(AccountManagementScreen.this,MainActivity.class);
        startActivity(i);
        finish();
    }
 }

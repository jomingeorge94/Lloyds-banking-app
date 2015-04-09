package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

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
                Intent changepasscode=new Intent(AccountManagementScreen.this,ChangePasscode_Screen.class);
                startActivity(changepasscode);
                finish();
            }
        });

        findViewById(R.id.AccountManagement_AccountName).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cahngeaccountname=new Intent(AccountManagementScreen.this,ChangeAccountName_Screen.class);
                startActivity(cahngeaccountname);
                finish();
            }
        });

        findViewById(R.id.AccountManagement_ManageStatements).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent managestatements=new Intent(AccountManagementScreen.this,ManageStatements.class);
                startActivity(managestatements);
                finish();
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
                Intent havingissue=new Intent(AccountManagementScreen.this,HavingIssues.class);
                startActivity(havingissue);
                finish();
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

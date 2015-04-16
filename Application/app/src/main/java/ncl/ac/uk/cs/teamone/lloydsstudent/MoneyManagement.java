package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

/**
 * Created by Jomin on 21/03/2015.
 * Reference - http://www.lloydsbank.com/online-banking/benefits-online-banking/money-manager.asp
 */
public class MoneyManagement extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.money_management);

        findViewById(R.id.backbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MoneyManagement.this,AccountManagementScreen.class);
                startActivity(i);
                finish();
            }
        });

        findViewById(R.id.money_managementlink).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent moneymanagementlink = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.lloydsbank.com/online-banking/benefits-online-banking/money-manager.asp"));
                startActivity(moneymanagementlink);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(MoneyManagement.this,AccountManagementScreen.class);
        startActivity(i);
        finish();
    }

}
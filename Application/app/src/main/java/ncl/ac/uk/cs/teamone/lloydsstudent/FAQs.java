package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

/**
 * Created by Jomin on 27/03/2015.
 * Reference - http://lloydsbank.creativevirtual.com/Lloyds/bot.htm?isJSEnabled=1&isJSEnabled=1&entry=Enter%20your%20question%20here
 */
public class FAQs extends FragmentActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faqs_screen);

        findViewById(R.id.settings_change_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(FAQs.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(FAQs.this,MainActivity.class);
        startActivity(i);
        finish();
    }
 }

package ncl.ac.uk.cs.teamone.lloydsstudent;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;


/**
 * Created by Jomin on 21/03/2015.
 */

public class ContactUs extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_us_screen);

       findViewById(R.id.backbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ContactUs.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        findViewById(R.id.email).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EmailDialogFragment email = new EmailDialogFragment();
                email.show(getSupportFragmentManager(), "email");
            }
        });


        findViewById(R.id.callus).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                CallUsDialogFragment CallUs = new CallUsDialogFragment();
                CallUs.show(getSupportFragmentManager(), "call");
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(ContactUs.this,MainActivity.class);
        startActivity(i);
        finish();
    }
}
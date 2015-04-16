package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

/**
 * Created by Jomin on 27/03/2015.
 * Reference - http://www.lloydsbank.com/assets/media/pdfs/banking_with_us/personal_banking_terms_and_conditions_from14August2013.pdf
 */
public class TermsandCondition extends FragmentActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.termsandcondition_screen);

        findViewById(R.id.settings_change_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(TermsandCondition.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(TermsandCondition.this,MainActivity.class);
        startActivity(i);
        finish();
    }
 }

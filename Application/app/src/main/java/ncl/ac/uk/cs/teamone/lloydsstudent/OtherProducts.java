package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

/**
 * Created by Jomin on 21/03/2015.
 */
public class OtherProducts extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_products_screen_main);

        findViewById(R.id.backbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(OtherProducts.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        findViewById(R.id.feature_mortgage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mortgage=new Intent(OtherProducts.this,Mortgages.class);
                startActivity(mortgage);
                finish();
            }
        });

        findViewById(R.id.feature_creditcards).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent creditcard=new Intent(OtherProducts.this,CreditCard.class);
                startActivity(creditcard);
                finish();
            }
        });

        findViewById(R.id.feature_loan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Loans=new Intent(OtherProducts.this,Loans.class);
                startActivity(Loans);
                finish();
            }
        });

        findViewById(R.id.feature_insurance).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent insurance=new Intent(OtherProducts.this,Insurance.class);
                startActivity(insurance);
                finish();
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(OtherProducts.this,MainActivity.class);
        startActivity(i);
        finish();
    }

}
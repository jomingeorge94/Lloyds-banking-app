package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

/**
 * Created by Jomin on 21/03/2015.
 */
public class CreditCard extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creditcard_screen);

        findViewById(R.id.settings_change_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(CreditCard.this,OtherProducts.class);
                startActivity(i);
                finish();
            }
        });

       findViewById(R.id.creditcard_link).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent creditcard = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.lloydsbank.com/help-guidance/product-guides/credit-cards/credit-card-guide.asp"));
                startActivity(creditcard);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(CreditCard.this,OtherProducts.class);
        startActivity(i);
        finish();
    }

}
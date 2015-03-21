package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Jomin on 21/03/2015.
 */
public class SecondDetail extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_second);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(SecondDetail.this,MainActivity.class);
        startActivity(i);
        finish();
    }
}

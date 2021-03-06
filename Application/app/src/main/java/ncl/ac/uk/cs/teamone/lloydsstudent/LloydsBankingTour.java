package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by Jomin on 27/03/2015.
 */
public class LloydsBankingTour extends FragmentActivity  {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lloyds_banking_tour_main);

        viewPager = (ViewPager) findViewById(R.id.pager);
        PagerAdapter padaper = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(padaper);

        findViewById(R.id.settings_change_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LloydsBankingTour.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(LloydsBankingTour.this,MainActivity.class);
        startActivity(i);
        finish();
    }

    //Simply prevents the app from being in the background, user has to enter passcode again!
    @Override
    protected void onUserLeaveHint() {
        this.finish();
    }
 }

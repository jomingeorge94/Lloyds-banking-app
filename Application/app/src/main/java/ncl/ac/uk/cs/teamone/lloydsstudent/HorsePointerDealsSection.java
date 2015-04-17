package ncl.ac.uk.cs.teamone.lloydsstudent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.Target;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

/**
 * Created by Jomin on 12/03/2015.
 */
public class HorsePointerDealsSection extends ActionBarActivity implements View.OnClickListener {

    private ShowcaseView showcaseView;
    private int counter = 0;
    private Target t1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.horse_deals);

        t1 = new ViewTarget(R.id.deals_loathed,this);


        showcaseView = new ShowcaseView.Builder(this).setTarget(Target.NONE).setOnClickListener(this).setContentTitle("Welcome to Deals Tutorial mode").setStyle(R.style.Transparency).build();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public void onClick(View v) {
        switch (counter){
            case 0:
                showcaseView.setShowcase(t1, true);
                showcaseView.setContentTitle("This is the main screen of the app, where you have a basic overview and functionality");
                showcaseView.setButtonText("Close");
                break;

            case 1:
                showcaseView.hide();
                Intent home =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(home);
                break;
        }
        counter ++;

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(HorsePointerDealsSection.this,MainActivity.class);
        startActivity(i);
        finish();
    }
}

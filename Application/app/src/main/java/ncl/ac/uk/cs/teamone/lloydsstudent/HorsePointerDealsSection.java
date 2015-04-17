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
    private Target t1,t2,t3,t4,t5,t6,t7,t8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.horse_deals);

        t1 = new ViewTarget(R.id.deals_newdeals_horse,this);
        t2 = new ViewTarget(R.id.deals_loathed,this);
        t3 = new ViewTarget(R.id.deals_loved_horse,this);
        t4 = new ViewTarget(R.id.deals_dealicon,this);
        t5 = new ViewTarget(R.id.deal_dealtitle,this);
        t6 = new ViewTarget(R.id.deals_heart,this);
        t7 = new ViewTarget(R.id.deals_brokenheart,this);
        t8 = new ViewTarget(R.id.deals_milestext,this);

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
                showcaseView.setContentTitle("This is where you will see all the deals that are available to you ");

                break;

            case 1:
                showcaseView.setShowcase(t2, true);
                showcaseView.setContentTitle("This is the section where the deals that you don't like will appear and you can remove the deals from this section by holding it down on the deal  ");
                break;

            case 2:
                showcaseView.setShowcase(t3, true);
                showcaseView.setContentTitle("This is the section where the deals that you like will appear and you can remove the deals from this section by holding it down on the deal  ");
                break;

            case 3:
                showcaseView.setShowcase(t4, true);
                showcaseView.setContentTitle("Each deal is presented with a deal icon that will be linked with what category it is linked with ");
                break;

            case 4:
                showcaseView.setShowcase(t5, true);
                showcaseView.setContentTitle("Each deal will have a title and within the title you will know what the deal is all about ");
                break;

            case 5:
                showcaseView.setShowcase(t6, true);
                showcaseView.setContentTitle("To send a deal to your Loved section, click on the heart icon. You will also get to see on top of the icon how many people liked this deal  ");
                break;

            case 6:
                showcaseView.setShowcase(t7, true);
                showcaseView.setContentTitle("To send a deal to your Loathed section, click on the broken heart icon. You will also get to see on top of the icon how many people disliked this deal  ");
                break;

            case 7:
                showcaseView.setShowcase(t8, true);
                showcaseView.setContentTitle("This part of the deal will tell you how far you are away from getting this deal. You have fully completed the tutorial, to go back to the Main Screen click on the close button  ");
                showcaseView.setButtonText("Close");
                break;

            case 8:
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
